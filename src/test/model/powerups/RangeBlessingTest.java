package model.powerups;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RangeBlessingTest {

    Game game;
    PowerUp rangeBlessing;

    @BeforeEach
    void runBefore() {
        game = new Game();
        rangeBlessing = new RangeBlessing(game);
    }

    @Test
    void testConstructor() {
        assertTrue(rangeBlessing.getOneUse());
        assertEquals(RangeBlessing.DESCRIPTION, rangeBlessing.getDescription());
        assertEquals(RangeBlessing.NAME, rangeBlessing.getName());
    }

    @Test
    void testApply() {
        assertEquals(40, game.getPlayer().getRange());
        rangeBlessing.apply();
        assertEquals(45, game.getPlayer().getRange());
    }
}
