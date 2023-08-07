package model.projectiles;

import model.Game;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlueRotationalProjectileTest {

    BlueRotationalProjectile blueRotationalProjectile;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        blueRotationalProjectile = new BlueRotationalProjectile(new Position(0, 0), 1, 1, 10, 100, 10, game);
    }

    @Test
    void testConstructor() {
        assertEquals(new Color(50, 100, 200), blueRotationalProjectile.getColor());
        assertEquals(18, blueRotationalProjectile.getWidth());
        assertEquals(18, blueRotationalProjectile.getHeight());
    }
}
