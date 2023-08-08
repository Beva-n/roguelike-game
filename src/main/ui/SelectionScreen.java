package ui;

import model.Game;
import model.powerups.PowerUp;
import model.powerups.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// Represents the selection screen
// Holds and displays the pickable upgrades in the beginning of every turn
public class SelectionScreen {

    private CustomButton button1;
    private CustomButton button2;
    private CustomButton button3;
    private final Random rand = new Random();
    private final PowerUp[] choices = new PowerUp[3];
    private final Game game;
    private final GamePanel gamePanel;
    private boolean visibility = false;


    //Effects: Constructs a new selection screen that generates random power ups for the player to
    // choose from, it has 3 button for the user to use to pick the power ups
    public SelectionScreen(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        randomPowerUps();

        button1 = new CustomButton("Pick Me!");
        button1.addActionListener(e -> choose(0));
        button1.setBounds(new Rectangle(230, 390, 100, 50));

        button2 = new CustomButton("Pick Me!");
        button2.addActionListener(e -> choose(1));
        button2.setBounds(new Rectangle(450, 390, 100, 50));

        button3 = new CustomButton("Pick Me!");
        button3.addActionListener(e -> choose(2));
        button3.setBounds(new Rectangle(670, 390, 100, 50));

        gamePanel.add(button1);
        gamePanel.add(button2);
        gamePanel.add(button3);

        setVisibility();
    }

    //Modifies: g
    //Effects: draws 3 cards with name and effect of the power up it holds
    public void draw(Graphics g) {
        g.setColor(new Color(9, 121, 105));
        g.fillRoundRect(200, 140, 160, 320, 50, 50);
        g.fillRoundRect(420, 140, 160, 320, 50, 50);
        g.fillRoundRect(640, 140, 160, 320, 50, 50);

        g.setColor(Color.white);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        g.drawString(choices[0].getName(), 220, 160);
        g.drawString(choices[1].getName(), 440, 160);
        g.drawString(choices[2].getName(), 660, 160);

        //maybe draw sprite????

        g.setFont(new Font("Comic Sans MS", Font.BOLD, 8));
        g.drawString(choices[0].getDescription(), 205, 375);
        g.drawString(choices[1].getDescription(), 425, 375);
        g.drawString(choices[2].getDescription(), 645, 375);

        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    }

    //Modifies: this
    //Effects: Resets the set for power ups  and chooses 3 new random power ups
    // from a pool of 5 power ups
    public void randomPowerUps() {
        Set<Integer> randomChoices = new HashSet<>();
        while (randomChoices.size() < 3) {
            randomChoices.add(rand.nextInt(7));
        }
        int index = 0;
        for (int i : randomChoices) {
            choices[index] = getPowerUp(i);
            index++;
        }
    }

    //Requires: 0 <= i <= 6
    //Effects: Gets a power up depending on the number given
    public PowerUp getPowerUp(int i) {
        switch (i) {
            case 6:
                return new AttackSpeedBlessing(game);
            case 5:
                return new SpeedBlessing(game);
            case 4:
                return new RangeBlessing(game);
            case 3:
                return new LifeBlessing(game);
            case 2:
                return new HealingBlessing(game);
            case 1:
                return new DefenseBlessing(game);
            default:
                return new AttackBlessing(game);
        }
    }

    //Modifies: game
    //Effects: Adds a power up to the player's obtained power ups based on the player input
    //         Then returns the game to an un-paused state
    public void choose(int i) {
        game.getPowerUpManager().addPowerUp(choices[i]);
        System.out.println("Power up selected!");
        game.flipSelectionState();
        game.flipGameState();
        flipVisibility();
    }

    //Modifies: this
    //Effects: sets the visibility of the selection button to the visibility field of the
    //         selection screen
    public void setVisibility() {
        button1.setVisible(visibility);
        button2.setVisible(visibility);
        button3.setVisible(visibility);
    }

    //Modifies: this
    //Effects: Toggles the visibility of the buttons, true -> false and false -> true
    public void flipVisibility() {
        visibility = !visibility;
        setVisibility();
    }
}
