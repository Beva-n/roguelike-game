package model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.map.Map1;
import model.map.Map2;

import java.util.Random;

public class Game {

    public static final String BLOCK = String.valueOf('█');
    public static final String BALL = String.valueOf('⬤');
    public static final TextColor.RGB TEXT_COLOR = new TextColor.RGB(255, 255, 255);

    private final Random rand = new Random();

    // true of ongoing, false for paused
    private boolean gameState = false;
    private int floorLevel = 1;
    private Player player;
    private Dungeon map;
    private EnemyManager enemyManager;
    private ProjectileManager projectileManager;
    private Screen screen;

    public Game(Screen screen) {
        this.screen = screen;
        player = new Player(this);
        map = new Map1(this);
        enemyManager = new EnemyManager(this, floorLevel);
        projectileManager = new ProjectileManager(this);
    }

    public boolean roomCleared() {
        return enemyManager.getEntities().isEmpty();
    }

    public boolean nextLevel() {
        return roomCleared() && getMap().checkCollisionDoor(player.getPosition());
    }

    public void newRoom() {
        player.setPosition(new Position(1, 6));
        map = getRandomMap();
        projectileManager = new ProjectileManager(this);
        enemyManager = new EnemyManager(this, floorLevel);
    }

    public void updateGame() {
        if (nextLevel()) {
            floorLevel++;
            newRoom();
        }
        player.update();
        enemyManager.updateAll();
        projectileManager.updateAll();
    }

    public void drawGame() {
        projectileManager.drawAll(screen);
        player.draw(screen);
        enemyManager.drawAll(screen);
        map.draw(screen);
        drawStats(screen);
    }

    public void drawStats(Screen screen) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TEXT_COLOR);
        text.putString(1, 0, "Hp: ");
        text.putString(5, 0, String.valueOf(player.getHealth()));
        text.putString(10, 0, "Def: ");
        text.putString(16, 0, String.valueOf(player.getDefense()));
        text.putString(20, 0, "ATK: ");
        text.putString(26, 0, String.valueOf(player.getAttack()));
    }

    public Dungeon getRandomMap() {
        int i = rand.nextInt(2);
        switch (i) {
            case 1:
                return new Map2(this);
            default:
                return new Map1(this);
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

}
