package model;

import com.googlecode.lanterna.TextColor;
import model.map.Map1;
import model.map.Map2;
import ui.SelectionScreen;

import java.util.Random;

// Represents a game that manages all the elements of the game and updates them
// has a projectile/enemy/power up manager, selection screen player, gamestate, map, and floor level
public class Game {

    @SuppressWarnings({"checkstyle:AvoidEscapedUnicodeCharacters", "checkstyle:SuppressWarnings"})
    public static final String BLOCK = String.valueOf('\u2588');
    @SuppressWarnings({"checkstyle:AvoidEscapedUnicodeCharacters", "checkstyle:SuppressWarnings"})
    public static final String BALL = String.valueOf('\u2B24');
    public static final TextColor.RGB TEXT_COLOR = new TextColor.RGB(255, 255, 255);

    private final Random rand = new Random();

    // true of ongoing, false for paused
    private boolean gameState = false;
    private int floorLevel = 1;
    private final Player player;
    private Dungeon map;
    private EnemyManager enemyManager;
    private ProjectileManager projectileManager;
    private final PowerUpManager powerUpManager;
    private SelectionScreen selectionScreen;

    //Effects: Constructs a new paused game with a new player, map, enemy manager, projectile manager
    // and selection screen, and a base floor level of 1
    public Game() {
        player = new Player(this);
        map = new Map1();
        enemyManager = new EnemyManager(this, floorLevel);
        projectileManager = new ProjectileManager(this);
        powerUpManager = new PowerUpManager();
        selectionScreen = new SelectionScreen(this);
        selectionScreen.printChoices();
    }

    //Effects: Checks whether all the enemies are defeated in the map
    public boolean roomCleared() {
        return enemyManager.getEntities().isEmpty();
    }

    //Effects: Checks whether the room is cleared and player is interacting with the door
    public boolean nextLevel() {
        return roomCleared() && getMap().checkCollisionDoor(player.getPosition());
    }

    //Modifies: this
    //Effects: resets the player location, regenerates a map, and resets projectile
    // and enemy manager
    public void newRoom() {
        player.reset();
        map = getRandomMap();
        projectileManager = new ProjectileManager(this);
        enemyManager = new EnemyManager(this, floorLevel);
    }

    //Modifies: this
    //Effects: if game state is false, nothing happens
    // if the player enters the next level, create a new room and enter selection mode where player
    // picks a new upgrade
    // if game state is true, updates all the power ups and entities in the game
    public void updateGame() {
        if (!gameState) {
            return;
        }
        if (nextLevel()) {
            floorLevel++;
            newRoom();
            gameState = false;
            selectionScreen = new SelectionScreen(this);
            selectionScreen.printChoices();
        }
        powerUpManager.update();
        player.update();
        enemyManager.updateAll();
        projectileManager.updateAll();
    }


    //Effects: Creates a random map from the available map pool
    public Dungeon getRandomMap() {
        int i = rand.nextInt(2);
        switch (i) {
            case 1:
                return new Map2();
            default:
                return new Map1();
        }
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public boolean getGameState() {
        return gameState;
    }

    public Player getPlayer() {
        return player;
    }

    public Dungeon getMap() {
        return map;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }

    public PowerUpManager getPowerUpManager() {
        return powerUpManager;
    }

    public SelectionScreen getSelectionScreen() {
        return selectionScreen;
    }

    public void setGameState(boolean state) {
        gameState = state;
    }

}
