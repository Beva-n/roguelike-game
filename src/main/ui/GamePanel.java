package ui;

import model.Entity;
import model.Event;
import model.EventLog;
import model.Game;
import model.Player;
import model.manager.EnemyManager;
import model.manager.EnemyProjectileManager;
import model.manager.PlayerProjectileManager;
import model.map.Dungeon;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;
import model.projectiles.Projectile;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main gamepanel of the game
// Displays basic elements of the game on a jPanel
public class GamePanel extends JPanel implements Runnable {

    public static final int FPS = 30;
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;
    private static final String JSON_STORE = "./data/game.json";
    private PauseScreen pauseScreen;
    private SelectionScreen selectionScreen;
    private Thread gameThread;
    private Game game;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean saved = false;
    private boolean ended = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;
    private boolean lastSelectionState = false;

    //Effects: initiates a new game, starts in a paused state with ends by the user pressing esc key
    public GamePanel() {
        setLayout(null);

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(new Color(0, 0, 0));
        setDoubleBuffered(true);
        setFocusable(true);
        setVisible(true);

        game = new Game();
        jsonReader = new JsonReader(JSON_STORE, game);
        jsonWriter = new JsonWriter(JSON_STORE, game);
        pauseScreen = new PauseScreen(game, this);
        selectionScreen = new SelectionScreen(game, this);

        addMouseListener(new MouseHandler(game, this));
        addKeyListener(new KeyHandler(game, this));
    }

    //Modifies: this
    //Effects: starts updating/drawing the game
    public void startGameThread() {
        gameThread = new Thread(this);
        game.flipGameState();
        pauseScreen.flipVisibility();
        gameThread.start();
    }

    //Modifies: this
    //Effects: updates all the entities in the game, and clears and redraws
    //         everything on the screen based on the updates
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            validate();
            repaint();

            try {
                double remaining = nextDrawTime - System.nanoTime();
                remaining /= 1000000;

                if (remaining < 0) {
                    remaining = 0;
                }

                Thread.sleep((long) remaining);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Modifies: this
    //Effects: Updates all the elements in the game, including the new player movements
    public void update() {
        game.updateGame();
        if (game.getPlayer().getHealth() <= 0) {
            endGame();
        }
        if (!lastSelectionState && game.getSelectionState()) {
            selectionScreen.randomPowerUps();
            selectionScreen.flipVisibility();
        }
        // put here for now
        if (moveLeft) {
            game.getPlayer().move(-game.getPlayer().getSpeed(), 0);
        }
        if (moveRight) {
            game.getPlayer().move(game.getPlayer().getSpeed(), 0);
        }
        if (moveUp) {
            game.getPlayer().move(0, -game.getPlayer().getSpeed());
        }
        if (moveDown) {
            game.getPlayer().move(0, game.getPlayer().getSpeed());
        }
        lastSelectionState = game.getSelectionState();
    }

    //Modifies: this
    //Effects: Sets the game screen to be invisible as the game ends
    public void endGame() {
        ended = true;
        // who knows gonna keep this way for now
        // future big brain me will come up with something better
        for (Event e : EventLog.getInstance()) {
            System.out.println(e);
        }
        System.exit(0);
    }

    //Modifies: g
    //Effects: draws all the entities on the screen, and the pause/selection screen
    //         depending on the game state and selection state
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g2.setStroke(new BasicStroke(2));

        drawAllEntities(g2);
        drawPlayer(g2);
        drawDungeon(g2);
        drawStats(g2);

        if (!game.getGameState()) {
            pauseScreen.draw(g2);
        }
        if (game.getSelectionState()) {
            selectionScreen.draw(g2);
        }
    }

    //Effects: clears the saved data
    public void clear() {
        try {
            jsonWriter.open();
            jsonWriter.clear();
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the game to file
    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write();
            jsonWriter.close();
            System.out.println("success");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    public void loadGame() {
        try {
            jsonReader.read();
            saved = true;
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (JSONException e) {
            System.out.println("No existing save file");
        }
    }

    //Effects: displayed the attack/defense/health of the player on the screen
    // as well as the current floor level
    public void drawStats(Graphics g) {
        g.setColor(new Color(95, 158, 160));
        g.fillRect(0, 0, 1000, 40);
        g.setColor(Color.white);

        // Text
        g.drawString("HP:", 10, 25);
        g.drawString(game.getPlayer().getHealth() + "/" + game.getPlayer().getMaxHealth(), 315, 25);
        g.drawString("ATK:" + game.getPlayer().getAttack(), 415, 25);
        g.drawString("DEF:" + game.getPlayer().getDefense(), 510, 25);
        g.drawString("LEVEL:" + game.getFloorLevel(), 605, 25);

        // Health Bar
        g.setColor(new Color(0, 255, 0));
        g.fillRect(55, 10, 250, 20);
        g.setColor(new Color(255, 0, 0));
        int length = (int) (2.5 * game.getPlayer().getHealthLost());
        g.fillRect(305 - length, 10, length, 20);

//        TextGraphics text = screen.newTextGraphics();
//        text.setForegroundColor(Game.TEXT_COLOR);
//        text.putString(1, 0, "Hp: ");
//        text.putString(5, 0, String.valueOf(game.getPlayer().getHealth()));
//        text.putString(10, 0, "Def: ");
//        text.putString(16, 0, String.valueOf(game.getPlayer().getDefense()));
//        text.putString(20, 0, "ATK: ");
//        text.putString(26, 0, String.valueOf(game.getPlayer().getAttack()));
//        text.putString(70, 0, "LEVEL: ");
//        text.putString(78, 0, String.valueOf(game.getFloorLevel()));
    }

    //Modifies: g
    //Effects: Draws all the wall and door tiles on the screen, door is red coloured if
    //         enemies are not defeated and yellow coloured if they are
    public void drawDungeon(Graphics g) {
        g.setColor(Dungeon.WALL_COLOR);
        for (Rectangle wall : game.getMap().getWallTile()) {
            g.fillRect(wall.x, wall.y, wall.width, wall.height);
        }
        if (game.roomCleared()) {
            g.setColor(Dungeon.DOOR_COLOR2);
        } else {
            g.setColor(Dungeon.DOOR_COLOR1);
        }
        for (Rectangle door : game.getMap().getDoorTile()) {
            g.fillRect(door.x, door.y, door.width, door.height);
        }
    }

    //Modifies: g
    //Effects: draw the player on the screen, as a green tile
    public void drawPlayer(Graphics g) {
        Player player = game.getPlayer();
        g.setColor(Color.green);
        g.fillRoundRect(player.getPosition().getX(), player.getPosition().getY(),
            player.getWidth(), player.getHeight(), 8, 8);
    }

    //Modifies: g
    //Effects: draws the projectile on the screen, as a purple circle
    public void drawProjectile(Entity e, Graphics g, int mode) {
        if (mode == 1) {
            g.setColor(Projectile.PROJECTILE_COLOR);
        } else {
            g.setColor(((Projectile) e).getColor());
        }
        g.fillOval(e.getPosition().getX(), e.getPosition().getY(),
            e.getWidth(), e.getHeight());
    }

    //Modifies: g
    //Effects: draws the enemy on the screen, as a red tile
    public void drawEnemy(Entity e, Graphics g) {
        g.setColor(Color.red);
        g.fillRoundRect(e.getPosition().getX(), e.getPosition().getY(),
            e.getWidth(), e.getHeight(), 8, 8);
    }

    //Modifies: g
    //Effects: draws all the enemies and projectiles in list on the screen
    public void drawAllEntities(Graphics g) {
        PlayerProjectileManager playerProjectileManager = game.getPlayerProjectileManager();
        EnemyManager enemyManager = game.getEnemyManager();
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();

        for (int i = 0; i < playerProjectileManager.getEntities().size(); i++) {
            drawProjectile(playerProjectileManager.getEntities().get(i), g, 1);
        }

        for (int i = 0; i < enemyProjectileManager.getEntities().size(); i++) {
            drawProjectile(enemyProjectileManager.getEntities().get(i), g, 2);
        }

        for (int i = 0; i < enemyManager.getEntities().size(); i++) {
            drawEnemy(enemyManager.getEntities().get(i), g);
        }
    }

    public boolean getSaved() {
        return saved;
    }

    public PauseScreen getPauseScreen() {
        return pauseScreen;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setMoveLeft(boolean move) {
        this.moveLeft = move;
    }

    public void setMoveUp(boolean move) {
        this.moveUp = move;
    }

    public void setMoveDown(boolean move) {
        this.moveDown = move;
    }

    public void setMoveRight(boolean move) {
        this.moveRight = move;
    }
}
