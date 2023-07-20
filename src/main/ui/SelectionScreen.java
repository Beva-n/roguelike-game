package ui;

import model.Game;
import model.PowerUp;
import model.powerups.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// Represents the selection screen
// Holds and displays the pickable upgrades in the beginning of every turn
public class SelectionScreen {
    private final Random rand = new Random();
    private final PowerUp[] choices = new PowerUp[3];
    private final Game game;


    //Effects: Constructs a new selection screen that generates random power ups for the player to
    // choose from, creates 1 instance of selection upon creation
    public SelectionScreen(Game game) {
        this.game = game;
        randomPowerUps();
    }

    //Modifies: this
    //Effects: Resets the set for power ups  and chooses 3 new random power ups
    // from a pool of 5 power ups
    public void randomPowerUps() {
        Set<Integer> randomChoices = new HashSet<>();
        while (randomChoices.size() < 3) {
            randomChoices.add(rand.nextInt(5));
        }
        int index = 0;
        for (int i : randomChoices) {
            choices[index] = getPowerUp(i);
            index++;
        }
    }

    //Requires: 0 <= i <= 4
    //Effects: Gets a power up depending on the number given
    public PowerUp getPowerUp(int i) {
        switch (i) {
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
    public void choose(char i) {
        int index = Character.getNumericValue(i);
        game.getPowerUpManager().addPowerUp(choices[index - 1]);
        System.out.println("Power up selected!");
    }

    // next user story LMAO
//    public void draw(Screen screen) {
//        for (PowerUp p : choices) {
//            p.drawCard(screen);
//        }
//    }

    //Effects: Prints out all the power ups obtained so far by the player on the terminal screen
    public void printChoices() {
        System.out.println("Confirm the power up choice by pressing number keys");
        for (int i = 1; i <= 3; i++) {
            System.out.println(i + " - " + choices[i - 1].getName());
            System.out.println(choices[i - 1].getDescription());
        }
    }
}
