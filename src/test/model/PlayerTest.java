package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    Game game;
    Player player;

    @BeforeEach
    void runBefore() {
        game = new Game();
        player = new Player(game);
    }

    @Test
    void testConstructor() {
        assertEquals(100, player.getHealth());
        assertEquals(15, player.getAttack());
        assertEquals(20, player.getDefense());
        assertEquals(new Position(1, 6), player.getPosition());
        assertEquals(50, player.getRange());
        assertEquals("right", player.getFaceDirection());
    }

    @Test
    void testReset() {
        player.setPosition(new Position(15 ,15));
        player.reset();
        assertEquals(new Position(1, 6), player.getPosition());
    }

    @Test
    void testUpdate() {
        assertEquals(0, player.getShootCd());
        assertEquals(0, player.getMoveCooldown());
        player.update();
        assertEquals(-1, player.getShootCd());
        assertEquals(-1, player.getMoveCooldown());
    }

    @Test
    void testShoot() {
        assertEquals(0, game.getProjectileManager().getEntities().size());
        player.shoot();
        assertEquals(1, game.getProjectileManager().getEntities().size());
        player.shoot();
        assertEquals(1, game.getProjectileManager().getEntities().size());
        player.setShootCd(0);
        player.setFaceDirection("left");
        player.shoot();
        player.setShootCd(0);
        player.setFaceDirection("up");
        player.shoot();
        player.setShootCd(0);
        player.setFaceDirection("down");
        player.shoot();
        assertEquals(4, game.getProjectileManager().getEntities().size());
    }

    @Test
    void testMoveRight() {
        player.setFaceDirection("left");
        player.moveRight();
        assertEquals("right", player.getFaceDirection());
    }

    @Test
    void testMoveLeft() {
        player.moveLeft();
        assertEquals("left", player.getFaceDirection());
    }

    @Test
    void testMoveUp() {
        player.moveUp();
        assertEquals("up", player.getFaceDirection());
    }

    @Test
    void testMoveDown() {
        player.moveDown();
        assertEquals("down", player.getFaceDirection());
    }

    @Test
    void testDecreaseHealth() {
        player.decreaseHealth(40);
        assertEquals(80, player.getHealth());
        player.decreaseHealth(10);
        assertEquals(79, player.getHealth());
    }

    @Test
    void testEditAttack() {
        player.editAttack(5);
        assertEquals(20, player.getAttack());
    }

    @Test
    void testEditDefense() {
        player.editDefense(100);
        assertEquals(120, player.getDefense());
    }

    @Test
    void testEditHealth() {
        player.editHealth(20);
        assertEquals(100, player.getHealth());
        assertEquals(120, player.getMaxHealth());
    }

    @Test
    void testHeal() {
        player.decreaseHealth(110);
        assertEquals(10, player.getHealth());
        player.heal(20);
        assertEquals(30, player.getHealth());
        player.heal(1000000);
        assertEquals(100, player.getHealth());
    }
}
