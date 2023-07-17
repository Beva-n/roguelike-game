package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Port;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectileTest {

    Projectile leftProjectile;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        leftProjectile = new Projectile(new Position(15 ,15), 'a', 10, game);
    }

    @Test
    void testConstructor() {
        assertEquals(50, leftProjectile.getLifeTime());
        assertEquals(10, leftProjectile.getDamage());
        assertEquals(0, leftProjectile.getMoveCooldown());
        assertEquals(new Position(15, 15), leftProjectile.getPosition());

    }

    @Test
    void testUpdate() {
        leftProjectile.update();
        assertEquals(new Position(14, 15), leftProjectile.getPosition());
        assertEquals(5, leftProjectile.getMoveCooldown());
        assertEquals(49, leftProjectile.getLifeTime());
        leftProjectile.update();
        assertEquals(new Position(14, 15), leftProjectile.getPosition());
        assertEquals(4, leftProjectile.getMoveCooldown());
        assertEquals(48, leftProjectile.getLifeTime());
        leftProjectile.setLifeTime(0);
        leftProjectile.update();
        assertEquals(new Position(14, 15), leftProjectile.getPosition());
    }

    @Test
    void testMoveInBound() {
        leftProjectile.moveRight();
        assertEquals(new Position(16, 15), leftProjectile.getPosition());
        leftProjectile.setMovecooldown(0);
        leftProjectile.moveLeft();
        assertEquals(new Position(15, 15), leftProjectile.getPosition());
        leftProjectile.setMovecooldown(0);
        leftProjectile.moveDown();
        assertEquals(new Position(15, 16), leftProjectile.getPosition());
        leftProjectile.setMovecooldown(0);
        leftProjectile.moveUp();
        assertEquals(new Position(15, 15), leftProjectile.getPosition());
    }

    @Test
    void testMoveOutBound() {
        leftProjectile.setPosition(new Position(1, 1));
        leftProjectile.moveUp();
        assertEquals(new Position(1, 1), leftProjectile.getPosition());
        leftProjectile.setMovecooldown(0);
        leftProjectile.moveLeft();
        assertEquals(new Position(1, 1), leftProjectile.getPosition());
        leftProjectile.setMovecooldown(0);

        leftProjectile.setPosition(new Position(38, 21));
        leftProjectile.moveDown();
        assertEquals(new Position(38, 21), leftProjectile.getPosition());
        leftProjectile.setMovecooldown(0);
        leftProjectile.moveRight();
        assertEquals(new Position(38, 21), leftProjectile.getPosition());
        leftProjectile.setMovecooldown(0);
    }

    @Test
    void testTouchWallAction() {
        game.getProjectileManager().spawn(leftProjectile);
        assertEquals(1, game.getProjectileManager().getEntities().size());
        leftProjectile.setPosition(new Position(38, 21));
        leftProjectile.moveDown();
        assertEquals(0, game.getProjectileManager().getEntities().size());
    }
}
