package ui;

import model.Game;

import java.awt.*;

//Represents a pause screen that shows up when the game is paused
public class PauseScreen {

    private GamePanel gamePanel;
    private Game game;
    private CustomButton saveButton;
    private CustomButton loadButton;
    private CustomButton powerUpsButton;
    private boolean visibility = false;

    //Effects: Constructs a pause screen with game and gamePanel, comes with 3 buttons with
    //         functions including saving, loading, and viewing buff obtained so far
    public PauseScreen(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;

        saveButton = new CustomButton("Save");
        saveButton.addActionListener(e -> save());
        saveButton.setBounds(new Rectangle(100, 500, 100, 50));

        loadButton = new CustomButton("Load");
        loadButton.addActionListener(e -> load());
        loadButton.setBounds(new Rectangle(450, 500, 100, 50));

        powerUpsButton = new CustomButton("Buffs");
        powerUpsButton.addActionListener(e -> openPowerUps());
        powerUpsButton.setBounds(new Rectangle(800, 500, 100, 50));

        gamePanel.add(saveButton);
        gamePanel.add(loadButton);
        gamePanel.add(powerUpsButton);

        setVisibility();
    }

    //Modifies: this
    //Effects: Changes the color of the load/save button depending on the current state of the game
    //         green means can be used and grey means cannot
    public void setButtonColor() {
        if (game.roomCleared() && !gamePanel.getSaved()) {
            saveButton.setColor(new Color(0, 128, 0));
        } else {
            saveButton.setColor(new Color(64, 64, 64));
        }

        if (game.getFloorLevel() == 1 && game.getPowerUpManager().getLog().isEmpty()) {
            loadButton.setColor(new Color(0, 128, 0));
        } else {
            loadButton.setColor(new Color(64, 64, 64));
        }
    }

    //Modifies: g
    //Effects: draws a translucent black overlay on the game screen to show paused state
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, 1000, 600);
    }

    //Modifies: gamePanel
    //Effects: saves the current game state to file and ends the game
    public void save() {
        if (game.roomCleared() && !gamePanel.getSaved()) {
            gamePanel.saveGame();
            gamePanel.endGame();
            gamePanel.setSaved(true);
        }
    }

    //Modifies: gamePanel
    //Effects: loads data from the current save file to gamePanel
    public void load() {
        if (game.getFloorLevel() == 1 && game.getPowerUpManager().getLog().isEmpty()) {
            gamePanel.loadGame();
            gamePanel.clear();
            game.flipGameState();
            gamePanel.getPauseScreen().flipVisibility();
        }
    }

    //Effects: Opens up a new frame that displays all the buffs obtained so far
    public void openPowerUps() {
        PowerUpsPanel powerUpsPanel = new PowerUpsPanel(game);
        powerUpsPanel.setVisible(true);
        gamePanel.requestFocusInWindow();
    }

    //Modifies: this
    //Effects: sets the visibility of all the buttons to the visibility field of
    //         the pause screen
    public void setVisibility() {
        saveButton.setVisible(visibility);
        loadButton.setVisible(visibility);
        powerUpsButton.setVisible(visibility);
    }

    //Modifies: this
    //Effects: flips(true -> false, false -> true) of the visibility variable and
    //         sets the visibility of all the buttons to it
    public void flipVisibility() {
        setButtonColor();
        visibility = !visibility;
        setVisibility();
    }


}
