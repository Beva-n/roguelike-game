package model.projectiles;

import model.Game;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Vector;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectileTest {

    Projectile projectile;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        projectile = new Projectile(new Position(15 ,15), new Vector(0, 1, 1), 10, 10, game);
    }

    @Test
    void testConstructor() {
        assertEquals(10, projectile.getLifeTime());
        assertEquals(10, projectile.getDamage());
        assertEquals(new Position(15, 15), projectile.getPosition());
        assertEquals(Color.red, projectile.getColor());
    }

    @Test
    void testUpdate() {
        game.getPlayerProjectileManager().spawn(projectile);
        projectile.update();
        assertEquals(new Position(15, 16), projectile.getPosition());
        assertEquals(9, projectile.getLifeTime());
        projectile.setLifeTime(0);
        projectile.update();
        assertEquals(0, game.getPlayerProjectileManager().getEntities().size());
    }

    @Test
    void testMove() {
        game.getPlayerProjectileManager().spawn(projectile);
        assertEquals(1, game.getPlayerProjectileManager().getEntities().size());
        projectile.move();
        assertEquals(new Position(15, 16), projectile.getPosition());
        projectile.setPosition(new Position(40, 39));
        assertTrue(game.getMap().checkCollisionWall(projectile));
        projectile.move();
        assertTrue(game.getMap().checkCollisionWall(projectile));
        assertEquals(0, game.getPlayerProjectileManager().getEntities().size());
    }

}
