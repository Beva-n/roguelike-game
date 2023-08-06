package model;

import model.manager.EnemyManager;
import model.manager.PlayerProjectileManager;
import model.projectiles.Projectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerProjectileManagerTest {

    PlayerProjectileManager playerProjectileManager;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        playerProjectileManager = new PlayerProjectileManager(game);
    }

    @Test
    void testCollision() {
        EnemyManager enemyManager = game.getEnemyManager();
        enemyManager.clearEntities();
        playerProjectileManager.spawn(new Projectile(new Position(1, 1), 'w', 10, game));
        enemyManager.spawn(new Enemy(new Position(1, 1), game));
        playerProjectileManager.checkCollisionAll();
        assertTrue(playerProjectileManager.getEntities().isEmpty());

        enemyManager.clearEntities();
        playerProjectileManager.spawn(new Projectile(new Position(1, 1), 'w', 10, game));
        enemyManager.spawn(new Enemy(new Position(2, 1), game));
        playerProjectileManager.checkCollisionAll();
        assertFalse(playerProjectileManager.getEntities().isEmpty());

        playerProjectileManager.clearEntities();
        enemyManager.clearEntities();
        playerProjectileManager.spawn(new Projectile(new Position(1, 1), 'w', 10000, game));
        enemyManager.spawn(new Enemy(new Position(1, 1), game));
        playerProjectileManager.checkCollisionAll();
        assertTrue(playerProjectileManager.getEntities().isEmpty());
        assertTrue(enemyManager.getEntities().isEmpty());
    }
}
