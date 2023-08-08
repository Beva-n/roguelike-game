package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossTest {

    Boss boss;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        boss = new Boss(5, game);
    }

    @Test
    void testConstructor() {
        assertEquals(500, boss.getHealth());
        assertEquals(0, boss.getDefense());
        assertEquals(3, boss.getSpeed());
        assertEquals(30, boss.getWidth());
        assertEquals(50, boss.getHeight());
    }

    @Test
    void testUpdate() {
        game.getEnemyManager().clearEntities();
        game.getEnemyManager().spawn(boss);
        assertFalse(game.getEnemyManager().getEntities().isEmpty());
        boss.setHealth(-1);
        boss.update();
        assertTrue(game.getEnemyManager().getEntities().isEmpty());
    }

    @Test
    void testUpdate2() {
        assertEquals(new Position(800, 260), boss.getPosition());
        assertEquals(0, boss.getMoveDowntime());
        boss.update();
        assertEquals(44, boss.getMoveDowntime());
        boss.update();
        assertEquals(43, boss.getMoveDowntime());
    }

    @Test
    void testMove() {
        assertEquals(new Position(800, 260), boss.getPosition());
        boss.move();
        assertNotEquals(new Position(800, 260), boss.getPosition());
        game.getPlayer().setPosition(new Position(800, 260));
        Position newPos = new Position(boss.getPosition().getX(), boss.getPosition().getY());
        boss.move();
        assertEquals(newPos, boss.getPosition());
    }

    @Test
    void testDoShoot() {
        assertEquals(0, boss.getMoveset());
        boss.doShoot();
        assertEquals(1, boss.getMoveset());
        boss.doShoot();
        assertEquals(2, boss.getMoveset());
        boss.doShoot();
        assertEquals(3, boss.getMoveset());
        boss.doShoot();
        assertEquals(4, boss.getMoveset());
        boss.doShoot();
        assertEquals(5, boss.getMoveset());
        boss.doShoot();
        assertEquals(6, boss.getMoveset());
        boss.doShoot();
        assertEquals(0, boss.getMoveset());
    }
}
