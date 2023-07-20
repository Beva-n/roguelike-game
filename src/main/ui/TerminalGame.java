package ui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.*;

import java.io.IOException;

// Represents the terminal game
// Displays all the elements of the game
public class TerminalGame {

    public static final int FPS = 30;
    private Screen screen;
    private Game game;

    /**
     * Begins the game and method does not leave execution
     * until game is complete.
     */

    //Modifies: this
    //Effects: initiates a new game, start by asking the user to pick a starting power up
    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        game = new Game();
        // TerminalSize terminalSize = screen.getTerminalSize();
        startTick();
    }

    //Modifies: this
    //Effects: Start updating the game frame by frame
    public void startTick() throws IOException, InterruptedException {
        while (true) {
            tick();
            Thread.sleep(1000L / FPS);
        }
    }

    //Modifies: this
    //Effects: Handles all the user input, updates all the entities in the game,
    // and clears and redraws everything on the screen based on the updates
    public void tick() throws IOException {
        handleUserInput();
        screen.clear();
        game.updateGame();
        render();
        screen.refresh();
    }

    // MODIFIES: this
    // EFFECTS: Changes the state of the game / moves the player / printing power up obtained
    // depending on key inputs
    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();
        char keyPressed;
        try {
            keyPressed = stroke.getCharacter();
        } catch (NullPointerException e) {
            return;
        }
        if (game.getGameState()) {
            if ("wasd".indexOf(keyPressed) != -1) {
                game.getPlayer().move(keyPressed);
            } else if (keyPressed == 'e') {
                printPowerUps();
            } else if (keyPressed == ' ') {
                game.getPlayer().shoot();
            }
        } else {
            if (keyPressed == '1' || keyPressed == '2' || keyPressed == '3') {
                game.getSelectionScreen().choose(keyPressed);
                game.setGameState(true);
            }
        }

    }


    //Modifies: this
    //Effects: Draws all the element of the game, including enemies, projectiles,
    // player, stats, and the map
    public void render() {
        drawAllEntities();
        drawPlayer();
        drawDungeon();
        drawStats();

    }

    //Effects: displayed the attack/defense/health of the player on the screen
    // as well as the current floor level
    public void drawStats() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(Game.TEXT_COLOR);
        text.putString(1, 0, "Hp: ");
        text.putString(5, 0, String.valueOf(game.getPlayer().getHealth()));
        text.putString(10, 0, "Def: ");
        text.putString(16, 0, String.valueOf(game.getPlayer().getDefense()));
        text.putString(20, 0, "ATK: ");
        text.putString(26, 0, String.valueOf(game.getPlayer().getAttack()));
        text.putString(70, 0, "LEVEL: ");
        text.putString(78, 0, String.valueOf(game.getFloorLevel()));
    }

    //Modifies: this
    //Effects: Draws all the wall and door tiles on the screen, door is red coloured if
    //         enemies are not defeated and yellow coloured if they are
    public void drawDungeon() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(Dungeon.WALL_COLOR);
        for (Position tile : game.getMap().getWallTile()) {
            text.putString(tile.getX() * 2, tile.getY() + 1, Game.BLOCK);
            text.putString(tile.getX() * 2 + 1, tile.getY() + 1, Game.BLOCK);
        }
        if (game.roomCleared()) {
            text.setForegroundColor(Dungeon.DOOR_COLOR2);
        } else {
            text.setForegroundColor(Dungeon.DOOR_COLOR1);
        }
        for (Position tile : game.getMap().getDoorTile()) {
            text.putString(tile.getX() * 2, tile.getY() + 1, Game.BLOCK);
            text.putString(tile.getX() * 2 + 1, tile.getY() + 1, Game.BLOCK);
        }
    }

    //Modifies: this
    //Effects: draw the player on the screen, as a green tile
    public void drawPlayer() {
        Player p = game.getPlayer();
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(Player.PLAYER_COLOR);
        text.putString(p.getPosition().getX() * 2, p.getPosition().getY() + 1, Game.BLOCK);
        text.putString(p.getPosition().getX() * 2 + 1, p.getPosition().getY() + 1, Game.BLOCK);
    }

    //Modifies: this
    //Effects: draws the projectile on the screen, as a purple circle
    public void drawProjectile(Entity e) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(Projectile.PROJECTILECOLOR);
        text.putString(e.getPosition().getX() * 2, e.getPosition().getY() + 1, Game.BALL);
    }

    //Modifies: this
    //Effects: draws the enemy on the screen, as a red tile
    public void drawEnemy(Entity e) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(Enemy.ENEMYCOLOR);
        text.putString(e.getPosition().getX() * 2,e.getPosition().getY() + 1, Game.BLOCK);
        text.putString(e.getPosition().getX() * 2 + 1, e.getPosition().getY() + 1, Game.BLOCK);
    }

    //Modifies: screen
    //Effects: draws all the enemies and projectiles in list on the screen
    public void drawAllEntities() {
        ProjectileManager projectileManager = game.getProjectileManager();
        EnemyManager enemyManager = game.getEnemyManager();

        for (int i = 0; i < projectileManager.getEntities().size(); i++) {
            drawProjectile(projectileManager.getEntities().get(i));
        }

        for (int i = 0; i < enemyManager.getEntities().size(); i++) {
            drawEnemy(enemyManager.getEntities().get(i));
        }
    }

    //Effects: Prints out the name of all the power ups obtained in the console
    public void printPowerUps() {
        System.out.println("Power ups obtained so far: ");
        for (String names : game.getPowerUpManager().getLog()) {
            System.out.println(" -" + names);
        }
    }
}
