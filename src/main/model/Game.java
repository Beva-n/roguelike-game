package model;

import com.googlecode.lanterna.TextColor;
import model.manager.EnemyManager;
import model.manager.EnemyProjectileManager;
import model.manager.PlayerProjectileManager;
import model.map.*;
import model.powerups.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

// Represents a game that manages all the elements of the game and updates them
// has a projectile/enemy/power up manager, selection screen player, gamestate, map, and floor level
public class Game {
    public static final TextColor.RGB TEXT_COLOR = new TextColor.RGB(255, 255, 255);

    private final Random rand = new Random();

    // true of ongoing, false for paused
    private boolean gameState = true;
    private boolean selectionState = false;
    private int floorLevel = 1;
    private final Player player;
    private Dungeon map;
    private EnemyManager enemyManager;
    private PlayerProjectileManager playerProjectileManager;
    private EnemyProjectileManager enemyProjectileManager;
    private final PowerUpManager powerUpManager;

    //Effects: Constructs a new paused game with a new player, map, enemy manager, projectile manager
    // and selection screen, and a base floor level of 1
    public Game() {
        player = new Player(this);
        map = new Map1();
        enemyManager = new EnemyManager(this, floorLevel);
        playerProjectileManager = new PlayerProjectileManager(this);
        enemyProjectileManager = new EnemyProjectileManager(this);
        powerUpManager = new PowerUpManager();
    }

    //Effects: Checks whether all the enemies are defeated in the map
    public boolean roomCleared() {
        return enemyManager.getEntities().isEmpty();
    }

    //Effects: Checks whether the room is cleared and player is interacting with the door
    public boolean nextLevel() {
        return roomCleared() && getMap().checkCollisionDoor(player);
    }

    //Modifies: this
    //Effects: resets the player location, regenerates a map, and resets projectile
    // and enemy manager
    public void newRoom() {
        player.reset();
        player.heal(15);
        map = getRandomMap();
        playerProjectileManager = new PlayerProjectileManager(this);
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
            flipGameState();
            flipSelectionState();
        }
        powerUpManager.update();
        player.update();
        enemyManager.updateAll();
        playerProjectileManager.updateAll();
        enemyProjectileManager.updateAll();
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

    //Requires: name corresponding to one of power ups
    //Effects: Creates a power up with the corresponding name
    public PowerUp getPowerUp(String name) {
        switch (name) {
            case "ATK SPD BOOST":
                return new AttackSpeedBlessing(this);
            case "SPEED BOOST":
                return new SpeedBlessing(this);
            case "RANGE BOOST":
                return new RangeBlessing(this);
            case "LIFE BLESSING":
                return new LifeBlessing(this);
            case "DEF BOOST":
                return new DefenseBlessing(this);
            case "ATK BOOST":
                return new AttackBlessing(this);
            default:
                return new HealingBlessing(this);
        }
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("level", floorLevel);
        json.put("buffs", buffsToJson());
        return json;
    }

    // EFFECTS: returns buffs obtained by the player as a JSON array
    private JSONArray buffsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : powerUpManager.getLog()) {
            jsonArray.put(s);
        }

        return jsonArray;
    }

    public void flipGameState() {
        gameState = !gameState;
    }

    public void flipSelectionState() {
        selectionState = !selectionState;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public boolean getGameState() {
        return gameState;
    }

    public boolean getSelectionState() {
        return selectionState;
    }

    public Player getPlayer() {
        return player;
    }

    public Dungeon getMap() {
        return map;
    }

    public int getPlayerX() {
        return player.getPosition().getX();
    }

    public int getPlayerY() {
        return player.getPosition().getY();
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public PlayerProjectileManager getPlayerProjectileManager() {
        return playerProjectileManager;
    }

    public EnemyProjectileManager getEnemyProjectileManager() {
        return enemyProjectileManager;
    }

    public PowerUpManager getPowerUpManager() {
        return powerUpManager;
    }

    public void setGameState(boolean state) {
        gameState = state;
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    public void setMap(Dungeon map) {
        this.map = map;
    }

}
