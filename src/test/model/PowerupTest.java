package model;

import model.powerups.AttackBlessing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PowerupTest {

    PowerUp p;
    Game g;

    @BeforeEach
    void runBefore() {
        g = new Game();
        p = new AttackBlessing(g);
    }

    @Test
    void testConstructor() {
        assertTrue(p.getOneUse());
        assertEquals("ATK BOOST", p.getName());
        assertEquals("Increase attack by 5", p.getDescription());
    }

}
