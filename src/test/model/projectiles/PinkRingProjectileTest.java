package model.projectiles;

import model.Game;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PinkRingProjectileTest {
    PinkRingProjectile pinkRingProjectile;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        pinkRingProjectile = new PinkRingProjectile(new Position(0, 0), 1, 1, 10, 100, game);
    }

    @Test
    void testConstructor() {
        assertEquals(new Color(255,20,147), pinkRingProjectile.getColor());
        assertEquals(18, pinkRingProjectile.getWidth());
        assertEquals(18, pinkRingProjectile.getHeight());
    }
}
