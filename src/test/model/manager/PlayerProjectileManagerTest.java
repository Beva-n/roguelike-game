package model.manager;

import model.Enemy;
import model.Game;
import model.Position;
import model.manager.EnemyManager;
import model.manager.PlayerProjectileManager;
import model.projectiles.Projectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Vector;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerProjectileManagerTest {

    PlayerProjectileManager playerProjectileManager;
    Game game;
    Projectile projectile1;
    Projectile projectile2;

    @BeforeEach
    void runBefore() {
        game = new Game();
        playerProjectileManager = new PlayerProjectileManager(game);
        projectile1 = new Projectile(new Position(80, 80), new Vector(0, 1, 1), 10, 20, game);
        projectile2 = new Projectile(new Position(800, 800), new Vector(0, -1, 1), 10, 20, game);
    }

    @Test
    void testCollision() {
        EnemyManager enemyManager = game.getEnemyManager();
        enemyManager.clearEntities();
        playerProjectileManager.spawn(projectile1);
        enemyManager.spawn(new Enemy(new Position(80, 80), game));
        playerProjectileManager.checkCollisionAll();
        assertTrue(playerProjectileManager.getEntities().isEmpty());

        enemyManager.clearEntities();

        playerProjectileManager.spawn(projectile2);
        enemyManager.spawn(new Enemy(new Position(80, 80), game));
        playerProjectileManager.checkCollisionAll();
        assertFalse(playerProjectileManager.getEntities().isEmpty());

        playerProjectileManager.clearEntities();
        enemyManager.clearEntities();

//        playerProjectileManager.spawn(new Projectile(new Position(1, 1), 'w', 10000, game));
//        enemyManager.spawn(new Enemy(new Position(1, 1), game));
//        playerProjectileManager.checkCollisionAll();
//        assertTrue(playerProjectileManager.getEntities().isEmpty());
//        assertTrue(enemyManager.getEntities().isEmpty());
    }
}
