package model.manager;

import model.Entity;
import model.Game;
import model.Position;
import model.manager.EntityManager;
import model.manager.PlayerProjectileManager;
import model.projectiles.Projectile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerTest {

    EntityManager entityManager;
    Game game;
    Entity projectile1;
    Entity projectile2;

    @BeforeEach
    void runBefore() {
        game = new Game();
        entityManager = new PlayerProjectileManager(game);
        projectile1 = new Projectile(new Position(80, 80), new Vector(0, 1, 1), 10, 20, game);
        projectile2 = new Projectile(new Position(80, 80), new Vector(0, -1, 1), 10, 20, game);

    }

    @Test
    void testSpawn() {
        entityManager.spawn(projectile1);
        assertEquals(1, entityManager.getEntities().size());
        entityManager.spawn(projectile2);
        assertEquals(2, entityManager.getEntities().size());
    }

    @Test
    void testUpdateAll() {
        entityManager.spawn(projectile1);
        entityManager.spawn(projectile2);
        entityManager.updateAll();
        Assertions.assertEquals(entityManager.getEntities().get(0).getPosition(), new Position(80, 81));
        assertEquals(entityManager.getEntities().get(1).getPosition(), new Position(80, 79));
    }

    @Test
    void testRemove() {
        entityManager.spawn(projectile1);
        entityManager.remove(projectile1);
        assertTrue(entityManager.getEntities().isEmpty());
    }
}
