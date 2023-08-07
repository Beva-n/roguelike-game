package ui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.*;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the terminal game
// Displays all the elements of the game
//public class TerminalGame {
//
//    private static final String JSON_STORE = "./data/game.json";
//    public static final int FPS = 30;
//    private Screen screen;
//    private Game game;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//    private boolean saved = false;
//    private boolean ended = false;
//    private char keyPressed;
//
//    /**
//     * Begins the game and method does not leave execution
//     * until game is complete.
//     */
//
//    //Modifies: this
//    //Effects: initiates a new game, start by asking the user to pick a starting power up
//    public void start() throws IOException, InterruptedException {
//        screen = new DefaultTerminalFactory().createScreen();
//        screen.startScreen();
//        game = new Game();
//        jsonReader = new JsonReader(JSON_STORE, game);
//        jsonWriter = new JsonWriter(JSON_STORE, game);
//        // TerminalSize terminalSize = screen.getTerminalSize();
//        startTick();
//    }
//
//    //Modifies: this
//    //Effects: Start updating the game frame by frame
//    public void startTick() throws IOException, InterruptedException {
//        while (!ended) {
//            tick();
//            Thread.sleep(1000L / FPS);
//        }
//    }
//
//    //Modifies: this
//    //Effects: Handles all the user input, updates all the entities in the game,
//    // and clears and redraws everything on the screen based on the updates
//    public void tick() throws IOException {
//        handleUserInput();
//        screen.clear();
//        game.updateGame();
//        if (game.getPlayer().getHealth() <= 0) {
//            endGame();
//        }
//        render();
//        screen.refresh();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Changes the state of the game / moves the player / printing power up obtained
//    // depending on key inputs
//    private void handleUserInput() throws IOException {
//        KeyStroke stroke = screen.pollInput();
//        try {
//            keyPressed = stroke.getCharacter();
//        } catch (NullPointerException e) {
//            return;
//        }
//        if (keyPressed == 'r') {
//            persistence();
//        }
//        if (game.getGameState()) {
//            if ("wasd".indexOf(keyPressed) != -1) {
//                game.getPlayer().move(keyPressed);
//            } else if (keyPressed == 'e') {
//                printPowerUps();
//            } else if (keyPressed == ' ') {
//                game.getPlayer().shoot();
//            }
//        } else {
//            if (keyPressed == '1' || keyPressed == '2' || keyPressed == '3') {
//                game.getSelectionScreen().choose(keyPressed);
//                game.setGameState(true);
//            }
//        }
//    }
//
//    //Modifies: this
//    //Effects: Terminates the game
//    private void endGame() throws IOException {
//        ended = true;
//        screen.close();
//    }
//
//    //Modifies: this
//    //Effects: determines whether to not do anything/save the game/load from save based
//    //         on the current state of the game
//    private void persistence() throws IOException {
//        if (game.roomCleared() && game.getGameState() && !saved) {
//            saveGame();
//            endGame();
//        } else if (game.getFloorLevel() == 1 && game.getPowerUpManager().getLog().isEmpty()) {
//            loadGame();
//            clear();
//            game.setGameState(true);
//        } else {
//            System.out.println("Unable to perform this action");
//        }
//    }
//
//    //Effects: clears the saved data
//    private void clear() {
//        try {
//            jsonWriter.open();
//            jsonWriter.clear();
//            jsonWriter.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // EFFECTS: saves the workroom to file
//    private void saveGame() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write();
//            jsonWriter.close();
//            System.out.println("success");
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads game workroom from file
//    private void loadGame() {
//        try {
//            jsonReader.read();
//            saved = true;
//            System.out.println("Success");
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        } catch (JSONException e) {
//            System.out.println("No existing save file");
//        }
//    }
//
//
//    //Modifies: this
//    //Effects: Draws all the element of the game, including enemies, projectiles,
//    // player, stats, and the map
//    public void render() {
//        drawAllEntities();
//        drawPlayer();
//        drawDungeon();
//        drawStats();
//
//    }
//
//    //Effects: displayed the attack/defense/health of the player on the screen
//    // as well as the current floor level
//    public void drawStats() {
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
//    }
//
//    //Modifies: this
//    //Effects: Draws all the wall and door tiles on the screen, door is red coloured if
//    //         enemies are not defeated and yellow coloured if they are
//    public void drawDungeon() {
//        TextGraphics text = screen.newTextGraphics();
//        text.setForegroundColor(Dungeon.WALL_COLOR);
//        for (Position tile : game.getMap().getWallTile()) {
//            text.putString(tile.getX() * 2, tile.getY() + 1, Game.BLOCK);
//            text.putString(tile.getX() * 2 + 1, tile.getY() + 1, Game.BLOCK);
//        }
//        if (game.roomCleared()) {
//            text.setForegroundColor(Dungeon.DOOR_COLOR2);
//        } else {
//            text.setForegroundColor(Dungeon.DOOR_COLOR1);
//        }
//        for (Position tile : game.getMap().getDoorTile()) {
//            text.putString(tile.getX() * 2, tile.getY() + 1, Game.BLOCK);
//            text.putString(tile.getX() * 2 + 1, tile.getY() + 1, Game.BLOCK);
//        }
//    }
//
//    //Modifies: this
//    //Effects: draw the player on the screen, as a green tile
//    public void drawPlayer() {
//        Player p = game.getPlayer();
//        TextGraphics text = screen.newTextGraphics();
//        text.setForegroundColor(Player.PLAYER_COLOR);
//        text.putString(p.getPosition().getX() * 2, p.getPosition().getY() + 1, Game.BLOCK);
//        text.putString(p.getPosition().getX() * 2 + 1, p.getPosition().getY() + 1, Game.BLOCK);
//    }
//
//    //Modifies: this
//    //Effects: draws the projectile on the screen, as a purple circle
//    public void drawProjectile(Entity e) {
//        TextGraphics text = screen.newTextGraphics();
//        text.setForegroundColor(Projectile.PROJECTILECOLOR);
//        text.putString(e.getPosition().getX() * 2, e.getPosition().getY() + 1, Game.BALL);
//    }
//
//    //Modifies: this
//    //Effects: draws the enemy on the screen, as a red tile
//    public void drawEnemy(Entity e) {
//        TextGraphics text = screen.newTextGraphics();
//        text.setForegroundColor(Enemy.ENEMYCOLOR);
//        text.putString(e.getPosition().getX() * 2,e.getPosition().getY() + 1, Game.BLOCK);
//        text.putString(e.getPosition().getX() * 2 + 1, e.getPosition().getY() + 1, Game.BLOCK);
//    }
//
//    //Modifies: screen
//    //Effects: draws all the enemies and projectiles in list on the screen
//    public void drawAllEntities() {
//        ProjectileManager projectileManager = game.getProjectileManager();
//        EnemyManager enemyManager = game.getEnemyManager();
//
//        for (int i = 0; i < projectileManager.getEntities().size(); i++) {
//            drawProjectile(projectileManager.getEntities().get(i));
//        }
//
//        for (int i = 0; i < enemyManager.getEntities().size(); i++) {
//            drawEnemy(enemyManager.getEntities().get(i));
//        }
//    }
//
//    //Effects: Prints out the name of all the power ups obtained in the console
//    public void printPowerUps() {
//        System.out.println("Power ups obtained so far: ");
//        for (String names : game.getPowerUpManager().getLog()) {
//            System.out.println(" -" + names);
//        }
//    }
//}
