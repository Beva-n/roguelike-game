package model.powerups;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpeedBlessingTest {

    Game game;
    PowerUp speedBlessing;

    @BeforeEach
    void runBefore() {
        game = new Game();
        speedBlessing = new SpeedBlessing(game);
    }

    @Test
    void testConstructor() {
        assertTrue(speedBlessing.getOneUse());
        assertEquals(SpeedBlessing.DESCRIPTION, speedBlessing.getDescription());
        assertEquals(SpeedBlessing.NAME, speedBlessing.getName());
    }

    @Test
    void testApply() {
        assertEquals(3, game.getPlayer().getSpeed());
        speedBlessing.apply();
        assertEquals(4, game.getPlayer().getSpeed());
    }
}
