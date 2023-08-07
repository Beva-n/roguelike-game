package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    Enemy enemy;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        enemy = new Enemy(new Position(100, 100), game);
    }

    @Test
    void testConstructor() {
        assertEquals(30, enemy.getHealth());
        assertEquals(20, enemy.getContactDamage());
        assertEquals(0, enemy.getDefense());
        assertEquals(new Position(100, 100), enemy.getPosition());
    }

    @Test
    void testMove() {
        enemy.move();
        assertNotEquals(new Position(100, 100), enemy.getPosition());
        game.getMap().getWallTile().add(new Rectangle(0, 0, 1000, 1000));
        Position newPos = new Position(enemy.getPosition().getX(), enemy.getPosition().getY());
        enemy.move();
        assertEquals(newPos, enemy.getPosition());
    }

    @Test
    void testScale() {
        enemy.scale(2);
        assertEquals(33, enemy.getHealth());
        assertEquals(22, enemy.getContactDamage());
        assertEquals(1, enemy.getDefense());
    }

    @Test
    void testUpdate() {
        game.getEnemyManager().clearEntities();
        game.getEnemyManager().spawn(enemy);
        assertEquals(1, game.getEnemyManager().getEntities().size());
        enemy.update();
        assertEquals(new Position(100, 100), enemy.getPosition());
        assertEquals(1, game.getEnemyProjectileManager().getEntities().size());
        assertEquals(59, enemy.getShootCd());
        assertEquals(-1, enemy.getAttackcd());
        enemy.update();
        assertNotEquals(new Position(100, 100), enemy.getPosition());
        assertEquals(1, game.getEnemyProjectileManager().getEntities().size());
        assertEquals(58, enemy.getShootCd());
        assertEquals(-2, enemy.getAttackcd());

        enemy.reduceHealth(30);
        enemy.update();
        assertEquals(0, game.getEnemyManager().getEntities().size());
    }

    @Test
    void testReduceHealth() {
        enemy.reduceHealth(10);
        assertEquals(20, enemy.getHealth());
        enemy.reduceHealth(15);
        assertEquals(5, enemy.getHealth());
    }

    @Test
    void testAttackPlayer() {
        assertEquals(100, game.getPlayer().getHealth());
        enemy.attackPlayer();
        assertEquals(80, game.getPlayer().getHealth());
        assertEquals(30, enemy.getAttackcd());
        enemy.attackPlayer();
        assertEquals(80, game.getPlayer().getHealth());
    }
}
