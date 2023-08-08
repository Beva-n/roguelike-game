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
        assertEquals(20, player.getAttack());
        assertEquals(0, player.getDefense());
        assertEquals(new Position(60, 232), player.getPosition());
        assertEquals(40, player.getRange());
        assertEquals(3, player.getSpeed());
        assertEquals(0, player.getHealthLost());
    }

    @Test
    void testScaleWithLevel() {
        player.scaleWithLevel();
        assertEquals(130, player.getHealth());
        assertEquals(27, player.getAttack());
        assertEquals(4, player.getDefense());
        assertEquals(4, player.getSpeed());
    }

    @Test
    void testScaleWithLevel2() {
        player.scaleWithLevel(11);
        assertEquals(160, player.getHealth());
        assertEquals(34, player.getAttack());
        assertEquals(8, player.getDefense());
        assertEquals(5, player.getSpeed());
    }

    @Test
    void testMove() {
        player.move(1, 1);
        assertEquals(new Position(61, 233), player.getPosition());
        player.move(-40, 0);
        assertEquals(new Position(61, 233), player.getPosition());
    }

    @Test
    void testReset() {
        player.setPosition(new Position(15 ,15));
        player.reset();
        assertEquals(new Position(60, 232), player.getPosition());
    }

    @Test
    void testUpdate() {
        assertEquals(0, player.getShootCd());
        player.update();
        assertEquals(-1, player.getShootCd());
    }


    @Test
    void testDecreaseHealth() {
        player.decreaseHealth(40);
        assertEquals(60, player.getHealth());
        player.decreaseHealth(10);
        assertEquals(50, player.getHealth());
    }

    @Test
    void testEditAttackSpeed() {
        assertEquals(15, player.getMaxShootCd());
        player.editAttackSpeed(2);
        assertEquals(13, player.getMaxShootCd());
        assertEquals(0, player.getShootCd());
        player.resetShootCd();
        assertEquals(13, player.getShootCd());
    }

    @Test
    void testEditAttack() {
        player.editAttack(5);
        assertEquals(25, player.getAttack());
    }

    @Test
    void testEditDefense() {
        player.editDefense(100);
        assertEquals(100, player.getDefense());
    }

    @Test
    void testEditHealth() {
        player.editHealth(20);
        assertEquals(100, player.getHealth());
        assertEquals(120, player.getMaxHealth());
    }

    @Test
    void testHeal() {
        player.decreaseHealth(90);
        assertEquals(10, player.getHealth());
        player.heal(20);
        assertEquals(30, player.getHealth());
        player.heal(1000000);
        assertEquals(100, player.getHealth());
    }
}
