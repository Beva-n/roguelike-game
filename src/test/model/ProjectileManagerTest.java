package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectileManagerTest {

    ProjectileManager projectileManager;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        projectileManager = new ProjectileManager(game);
    }

    @Test
    void testCollision() {
        EnemyManager enemyManager = game.getEnemyManager();
        enemyManager.clearEntities();
        projectileManager.spawn(new Projectile(new Position(1, 1), 'w', 10, game));
        enemyManager.spawn(new Enemy(new Position(1, 1), game));
        projectileManager.checkCollisionAll();
        assertTrue(projectileManager.getEntities().isEmpty());

        enemyManager.clearEntities();
        projectileManager.spawn(new Projectile(new Position(1, 1), 'w', 10, game));
        enemyManager.spawn(new Enemy(new Position(2, 1), game));
        projectileManager.checkCollisionAll();
        assertFalse(projectileManager.getEntities().isEmpty());
    }
}
