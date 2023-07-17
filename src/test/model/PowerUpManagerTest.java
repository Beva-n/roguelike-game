package model;

import model.powerups.AttackBlessing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PowerUpManagerTest {

    PowerUpManager powerUpManager;
    Game game;

    @BeforeEach
    void runBefore() {
        powerUpManager = new PowerUpManager();
        game = new Game();
    }

    @Test
    void testAddPowerUps() {
        assertEquals(0, powerUpManager.getPowerUps().size());
        powerUpManager.addPowerUp(new AttackBlessing(game));
        assertEquals(1, powerUpManager.getPowerUps().size());
    }

    @Test
    void testUpdate() {
        powerUpManager.addPowerUp(new AttackBlessing(game));
        assertEquals(1, powerUpManager.getPowerUps().size());
        powerUpManager.update();
        assertEquals(0, powerUpManager.getPowerUps().size());
    }
}
