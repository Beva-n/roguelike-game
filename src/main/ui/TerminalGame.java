package ui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.Game;

import java.io.IOException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class TerminalGame {

    public static final int FPS = 30;
    private Screen screen;
    private Game game;

    /**
     * Begins the game and method does not leave execution
     * until game is complete.
     */
    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        game = new Game();
        // TerminalSize terminalSize = screen.getTerminalSize();
        startTick();
    }

    public void startTick() throws IOException, InterruptedException {
        while (true) {
            tick();
            Thread.sleep(1000L / FPS);
        }
    }

    public void tick() throws IOException {
        handleUserInput();
        screen.clear();
        game.updateGame();
        game.drawGame(screen);
        screen.refresh();
    }

    // MODIFIES: this
    // EFFECTS: Changes the state of the game / moves the player depending on
    //          key inputs
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
                game.getPowerUpManager().printPowerUps();
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

    public Screen getScreen() {
        return screen;
    }

}
