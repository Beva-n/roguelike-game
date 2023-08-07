package model.projectiles;

import model.Game;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedRotationalProjectileTest {

    RedRotationalProjectile redRotationalProjectile;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        redRotationalProjectile = new RedRotationalProjectile(new Position(0, 0), 1, 1, 10, 100, 10, game);
    }

    @Test
    void testConstructor() {
        assertEquals(18, redRotationalProjectile.getWidth());
        assertEquals(18, redRotationalProjectile.getHeight());
    }
}
