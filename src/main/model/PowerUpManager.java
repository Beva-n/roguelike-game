package model;

import java.util.*;

public class PowerUpManager {

    private Queue<PowerUp> oneTimePowerUps;
    private List<PowerUp> powerUps;
    private List<String> log;
    private Game game;

    public PowerUpManager(Game game) {
        this.game = game;
        oneTimePowerUps = new LinkedList<>();
        powerUps = new ArrayList<>();
        log = new ArrayList<>();
    }

    public void update() {
        while (!oneTimePowerUps.isEmpty()) {
            oneTimePowerUps.poll().apply();
        }
        // none one time power ups not implemented
//        for (int i = 0; i < powerUps.size(); i++) {
//            powerUps.get(i).apply();
//        }
    }

    public void logPowerUps(PowerUp powerUp) {
        log.add(powerUp.getName());
    }

    public void addPowerUp(PowerUp powerUp) {
        if (powerUp.getOneUse()) {
            oneTimePowerUps.add(powerUp);
        } else {
            powerUps.add(powerUp);
        }
        log.add(powerUp.name);
    }

    public void printPowerUps() {
        System.out.println("Power ups obtained so far: ");
        for (String names : log) {
            System.out.println(" -" + names);
        }
    }

}
