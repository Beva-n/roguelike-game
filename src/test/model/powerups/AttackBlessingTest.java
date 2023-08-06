package model.powerups;

import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttackBlessingTest {

    Game game;
    PowerUp atkBlessing;

    @BeforeEach
    void runBefore() {
        game = new Game();
        atkBlessing = new AttackBlessing(game);
    }

    @Test
    void testConstructor() {
        assertTrue(atkBlessing.getOneUse());
        assertEquals(AttackBlessing.DESCRIPTION, atkBlessing.getDescription());
        assertEquals(AttackBlessing.NAME, atkBlessing.getName());
    }

    @Test
    void testApply() {
        assertEquals(15, game.getPlayer().getAttack());
        atkBlessing.apply();
        assertEquals(20, game.getPlayer().getAttack());
    }
}
