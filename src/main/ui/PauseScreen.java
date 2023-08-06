package ui;

import model.Game;

import java.awt.*;

public class PauseScreen {

    private GamePanel gamePanel;
    private Game game;
    private CustomButton saveButton;
    private CustomButton loadButton;
    private CustomButton powerUpsButton;
    private boolean visibility = false;

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

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, 1000, 600);
    }

    public void save() {
        if (game.roomCleared() && !gamePanel.getSaved()) {
            gamePanel.saveGame();
            gamePanel.endGame();
            gamePanel.setSaved(true);
            System.out.println("saved!");
        }
    }

    public void load() {
        if (game.getFloorLevel() == 1 && game.getPowerUpManager().getLog().isEmpty()) {
            gamePanel.loadGame();
            gamePanel.clear();
            game.flipGameState();
            gamePanel.getPauseScreen().flipVisibility();
        }
    }

    public void openPowerUps() {
        PowerUpsPanel powerUpsPanel = new PowerUpsPanel(game);
        powerUpsPanel.setVisible(true);
        gamePanel.requestFocusInWindow();
    }

    public void setVisibility() {
        saveButton.setVisible(visibility);
        loadButton.setVisible(visibility);
        powerUpsButton.setVisible(visibility);
    }

    public void flipVisibility() {
        setButtonColor();
        visibility = !visibility;
        setVisibility();
    }



}
