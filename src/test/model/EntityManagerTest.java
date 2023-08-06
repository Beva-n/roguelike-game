package model;

import model.manager.EntityManager;
import model.manager.PlayerProjectileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
//        projectile1 = new Projectile(new Position(1, 1), 'd', 10, game);
//        projectile2 = new Projectile(new Position(2, 1), 'd', 10, game);
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
        assertEquals(entityManager.getEntities().get(0).getPosition(), new Position(2, 1));
        assertEquals(entityManager.getEntities().get(1).getPosition(), new Position(3, 1));
    }

    @Test
    void testRemove() {
        entityManager.spawn(projectile1);
        entityManager.remove(projectile1);
        assertTrue(entityManager.getEntities().isEmpty());
    }
}
