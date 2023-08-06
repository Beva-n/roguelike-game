package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    Enemy enemy;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        enemy = new Enemy(new Position(15, 15), game);
    }

    @Test
    void testConstructor() {
        assertEquals(30, enemy.getHealth());
        assertEquals(40, enemy.getContactDamage());
        assertEquals(0, enemy.getDefense());
        assertEquals(0, enemy.getMoveCooldown());
        assertEquals(new Position(15, 15), enemy.getPosition());
    }

    @Test
    void testUpdate() {
        game.getEnemyManager().clearEntities();
        game.getEnemyManager().spawn(enemy);
        assertEquals(1, game.getEnemyManager().getEntities().size());
        enemy.update();
        assertNotEquals(new Position(15, 15), enemy.getPosition());
        assertNotEquals(0, enemy.getMoveCooldown());
        assertEquals(-1, enemy.getAttackcd());
        Position newPos = new Position(enemy.getPosition().getX(), enemy.getPosition().getY());
        int i = enemy.getMoveCooldown();
        enemy.update();
        assertEquals(newPos, enemy.getPosition());
        assertEquals(i-1, enemy.getMoveCooldown());
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
