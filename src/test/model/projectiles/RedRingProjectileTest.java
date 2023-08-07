package model.projectiles;

import model.Game;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedRingProjectileTest {

    RedRingProjectile redRingProjectile;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        redRingProjectile = new RedRingProjectile(new Position(0, 0), 1, 1, 10, 100, game);
    }

    @Test
    void testConstructor() {
        assertEquals(10, redRingProjectile.getWidth());
        assertEquals(10, redRingProjectile.getHeight());
    }
}
