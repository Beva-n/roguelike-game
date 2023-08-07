package model.projectiles;

import model.Game;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlueRingProjectileTest {

    BlueRingProjectile blueRingProjectile;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        blueRingProjectile = new BlueRingProjectile(new Position(0, 0), 1, 1, 10, 100, game);
    }

    @Test
    void testConstructor() {
        assertEquals(new Color(50, 100, 200), blueRingProjectile.getColor());
        assertEquals(10, blueRingProjectile.getWidth());
        assertEquals(10, blueRingProjectile.getHeight());
    }
}
