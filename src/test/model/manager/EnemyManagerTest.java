package model.manager;

import model.Enemy;
import model.Game;
import model.Position;
import model.manager.EnemyManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        enemyManager.clearEntities();
        enemyManager.spawn(7);
        assertEquals(1, enemyManager.getEntities().size());
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
    void testMakeValidEnemy() {
        Enemy enemy1 = enemyManager.makeValidEnemy(40, 40);
        Enemy enemy2 = enemyManager.makeValidEnemy(80, 80);
        assertEquals(500, enemy1.getPosition().getX());
        assertEquals(400, enemy1.getPosition().getY());
        assertEquals(80, enemy2.getPosition().getX());
        assertEquals(80, enemy2.getPosition().getY());
    }
}
