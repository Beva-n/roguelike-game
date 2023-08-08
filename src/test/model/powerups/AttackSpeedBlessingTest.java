package model.powerups;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttackSpeedBlessingTest {
    Game game;
    PowerUp atkSpdBlessing;

    @BeforeEach
    void runBefore() {
        game = new Game();
        atkSpdBlessing = new AttackSpeedBlessing(game);
    }

    @Test
    void testConstructor() {
        assertTrue(atkSpdBlessing.getOneUse());
        assertEquals(AttackSpeedBlessing.DESCRIPTION, atkSpdBlessing.getDescription());
        assertEquals(AttackSpeedBlessing.NAME, atkSpdBlessing.getName());
    }

    @Test
    void testApply() {
        assertEquals(15, game.getPlayer().getMaxShootCd());
        atkSpdBlessing.apply();
        assertEquals(13, game.getPlayer().getMaxShootCd());
    }
}
