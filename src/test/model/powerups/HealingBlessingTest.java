package model.powerups;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealingBlessingTest {

    Game game;
    PowerUp healingBlessing;

    @BeforeEach
    void runBefore() {
        game = new Game();
        healingBlessing = new HealingBlessing(game);
    }

    @Test
    void testConstructor() {
        assertTrue(healingBlessing.getOneUse());
        assertEquals(HealingBlessing.DESCRIPTION, healingBlessing.getDescription());
        assertEquals(HealingBlessing.NAME, healingBlessing.getName());
    }

    @Test
    void testApply() {
        game.getPlayer().decreaseHealth(50);
        assertEquals(70, game.getPlayer().getHealth());
        healingBlessing.apply();
        assertEquals(90, game.getPlayer().getHealth());
    }
}
