package ui;

import model.Game;
import model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Represents the key handler that handles all the key inputs for the game
public class KeyHandler implements KeyListener {

    private Game game;
    private GamePanel gamePanel;

    //Constructs a key handler with a game and game panel field
    public KeyHandler(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
    }

    //Effect: nothing
    @Override
    public void keyTyped(KeyEvent e) {
        // useless
    }

    //Modifies: gamePanel
    //Effects: enables player movement based on the key the user inputs
    @Override
    public void keyPressed(KeyEvent e) {
        if (!game.getGameState()) {
            return;
        }

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            gamePanel.setMoveUp(true);
        }
        if (key == KeyEvent.VK_A) {
            gamePanel.setMoveLeft(true);
        }
        if (key == KeyEvent.VK_S) {
            gamePanel.setMoveDown(true);
        }
        if (key == KeyEvent.VK_D) {
            gamePanel.setMoveRight(true);
        }
    }

    //Modifies: gamePanel
    //Effects: disables player movement upon releasing the key
    //         also toggles the pause screen if the esc key is released
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            gamePanel.setMoveUp(false);
        }
        if (key == KeyEvent.VK_A) {
            gamePanel.setMoveLeft(false);
        }
        if (key == KeyEvent.VK_S) {
            gamePanel.setMoveDown(false);
        }
        if (key == KeyEvent.VK_D) {
            gamePanel.setMoveRight(false);
        }
        if (key == KeyEvent.VK_ESCAPE && !game.getSelectionState()) {
            game.flipGameState();
            gamePanel.getPauseScreen().flipVisibility();
        }
    }
}
