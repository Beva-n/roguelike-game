package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyManagerTest {

    EnemyManager enemyManager;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        enemyManager = new EnemyManager(game, game.getFloorLevel());
    }

    @Test
    void testConstructor() {
        assertEquals(3, enemyManager.getEntities().size());
    }

    @Test
    void testSpawn() {
        enemyManager.clearEntities();
        enemyManager.spawn(3);
        assertEquals(3, enemyManager.getEntities().size());
        enemyManager.spawn(8);
        assertEquals(11, enemyManager.getEntities().size());
    }

    @Test
    void testCollision() {
        enemyManager.clearEntities();
        enemyManager.spawn(new Enemy(new Position(1, 1), game));
        game.getPlayer().setPosition(new Position(1, 1));
        enemyManager.checkCollisionAll();
        assertEquals(80, game.getPlayer().getHealth());
        game.getPlayer().setPosition(new Position(2, 1));
        enemyManager.checkCollisionAll();
        assertEquals(80, game.getPlayer().getHealth());
    }

    @Test
    void testValidPosition() {
        Position p2 = enemyManager.makeValidPosition(15 ,15);
        assertEquals(new Position(15, 15), p2);
        Position p1 = enemyManager.makeValidPosition(0 ,0);
        assertEquals(new Position(38, 10), p1);
    }
}
