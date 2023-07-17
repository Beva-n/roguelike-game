package model;

import java.util.*;

public class PowerUpManager {

    private final Queue<PowerUp> oneTimePowerUps;
    // private List<PowerUp> powerUps;
    private final List<String> log;

    //Effects: Constructs a power up manager that keeps track of all the power ups the
    //         player have obtained
    public PowerUpManager() {
        oneTimePowerUps = new LinkedList<>();
        // powerUps = new ArrayList<>();
        log = new ArrayList<>();
    }

    //Modifies: this
    //Effects: Apply all the power ups stored to the player, removing them afterward
    public void update() {
        while (!oneTimePowerUps.isEmpty()) {
            oneTimePowerUps.poll().apply();
        }
        // non-onetime power ups not implemented
//        for (int i = 0; i < powerUps.size(); i++) {
//            powerUps.get(i).apply();
//        }
    }

    //Modifies: this
    //Effects: Add a power up to the queue and logs name of the power up added
    public void addPowerUp(PowerUp powerUp) {
        if (powerUp.getOneUse()) {
            oneTimePowerUps.add(powerUp);
        }
//        else {
//            powerUps.add(powerUp);
//        }
        log.add(powerUp.name);
    }

    //Effects: Prints out the name of all the power ups obtained in the console
    public void printPowerUps() {
        System.out.println("Power ups obtained so far: ");
        for (String names : log) {
            System.out.println(" -" + names);
        }
    }

    public Queue<PowerUp> getPowerUps() {
        return oneTimePowerUps;
    }

}
