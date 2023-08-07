package model.powerups;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefenseBlessingTest {
    Game game;
    PowerUp defBlessing;

    @BeforeEach
    void runBefore() {
        game = new Game();
        defBlessing = new DefenseBlessing(game);
    }

    @Test
    void testConstructor() {
        assertTrue(defBlessing.getOneUse());
        assertEquals(DefenseBlessing.DESCRIPTION, defBlessing.getDescription());
        assertEquals(DefenseBlessing.NAME, defBlessing.getName());
    }

    @Test
    void testApply() {
        assertEquals(0, game.getPlayer().getDefense());
        defBlessing.apply();
        assertEquals(4, game.getPlayer().getDefense());
    }
}
