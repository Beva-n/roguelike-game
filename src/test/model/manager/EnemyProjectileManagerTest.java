package model.manager;

import model.Enemy;
import model.Game;
import model.Position;
import model.projectiles.Projectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Vector;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyProjectileManagerTest {

    EnemyProjectileManager enemyProjectileManager;
    Game game;
    Projectile projectile1;
    Projectile projectile2;

    @BeforeEach
    void runBefore() {
        game = new Game();
        enemyProjectileManager = new EnemyProjectileManager(game);
        projectile1 = new Projectile(new Position(80, 80), new Vector(0, 1, 1), 10, 20, game);
        projectile2 = new Projectile(new Position(800, 800), new Vector(0, -1, 1), 10, 20, game);
    }

    @Test
    void testCollision() {
        EnemyManager enemyManager = game.getEnemyManager();
        enemyManager.clearEntities();
        enemyProjectileManager.spawn(projectile1);
        game.getPlayer().setPosition(new Position(80, 80));
        enemyProjectileManager.checkCollisionAll();
        assertTrue(enemyProjectileManager.getEntities().isEmpty());


        enemyProjectileManager.spawn(projectile2);
        game.getPlayer().setPosition(new Position(80, 80));
        enemyProjectileManager.checkCollisionAll();
        assertFalse(enemyProjectileManager.getEntities().isEmpty());

//        playerProjectileManager.spawn(new Projectile(new Position(1, 1), 'w', 10000, game));
//        enemyManager.spawn(new Enemy(new Position(1, 1), game));
//        playerProjectileManager.checkCollisionAll();
//        assertTrue(playerProjectileManager.getEntities().isEmpty());
//        assertTrue(enemyManager.getEntities().isEmpty());
    }
}
