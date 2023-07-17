package ui;

import model.Game;
import model.PowerUp;
import model.powerups.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SelectionScreen {
    private Random rand = new Random();
    private PowerUp[] choices = new PowerUp[3];
    private Game game;

    public SelectionScreen(Game game) {
        this.game = game;
        randomPowerUps();
    }

    public void randomPowerUps() {
        Set<Integer> randomChoices = new HashSet<>();
        while (randomChoices.size() < 3) {
            randomChoices.add(rand.nextInt(5));
        }
        int index = 0;
        for (int i : randomChoices) {
            System.out.println(i);
            choices[index] = getPowerUp(i);
            index++;
        }
    }

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

    public void printChoices() {
        System.out.println("Confirm the power up choice by pressing number keys");
        for (int i = 1; i <= 3; i++) {
            System.out.println(i + " - " + choices[i - 1].getName());
            System.out.println(choices[i - 1].getDescription());
        }
    }
}
