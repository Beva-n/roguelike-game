package model.powerups;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LifeBlessingTest {


    Game game;
    PowerUp lifeBlessing;

    @BeforeEach
    void runBefore() {
        game = new Game();
        lifeBlessing = new LifeBlessing(game);
    }

    @Test
    void testConstructor() {
        assertTrue(lifeBlessing.getOneUse());
        assertEquals(LifeBlessing.DESCRIPTION, lifeBlessing.getDescription());
        assertEquals(LifeBlessing.NAME, lifeBlessing.getName());
    }

    @Test
    void testApply() {
        assertEquals(100, game.getPlayer().getHealth());
        lifeBlessing.apply();
        assertEquals(120, game.getPlayer().getHealth());
    }
}
