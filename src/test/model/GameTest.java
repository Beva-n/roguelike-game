package model;

import model.map.Dungeon;
import model.map.Map1;
import model.map.Map2;
import model.powerups.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
    }

    @Test
    void testConstructor() {
        assertTrue(game.getPowerUpManager().getPowerUps().isEmpty());
        assertTrue(game.getGameState());
        assertEquals(1, game.getFloorLevel());
    }

    @Test
    void testRoomCleared() {
        assertFalse(game.roomCleared());
        game.getEnemyManager().clearEntities();
        assertTrue(game.roomCleared());
    }

    @Test
    void testNextLevel() {
        assertFalse(game.nextLevel());
        game.getEnemyManager().clearEntities();
        assertFalse(game.nextLevel());
        game.getPlayer().setPosition(new Position(960, 270));
        assertTrue(game.nextLevel());
    }

    @Test
    void testNewRoom() {
        game.setFloorLevel(6);
        game.newRoom();
        assertEquals(4, game.getPlayer().getDefense());
        assertEquals(27, game.getPlayer().getAttack());
        assertEquals(130, game.getPlayer().getHealth());
    }

    @Test
    void testUpdateGame() {
        assertEquals(0, game.getPlayer().getShootCd());
        game.setGameState(false);
        game.updateGame();
        assertEquals(0, game.getPlayer().getShootCd());

        game.setGameState(true);
        game.updateGame();
        assertEquals(-1, game.getPlayer().getShootCd());

        game.getEnemyManager().clearEntities();
        game.getPlayer().setPosition(new Position(960, 270));
        game.updateGame();
        assertEquals(new Position(60, 232), game.getPlayer().getPosition());
    }

    @Test
    void testGetRandomMap() {
        Dungeon m3 = game.getRandomMap();
        Dungeon m4 = game.getRandomMap();
        Dungeon m5 = game.getRandomMap();
        Dungeon m6 = game.getRandomMap();
        Dungeon m7 = game.getRandomMap();
        Map1 m1 = new Map1();
        Map2 m2 = new Map2();
        assertTrue(m3.getClass() == m2.getClass() || m3.getClass() == m1.getClass());
        assertTrue(m4.getClass() == m2.getClass() || m4.getClass() == m1.getClass());
        assertTrue(m5.getClass() == m2.getClass() || m5.getClass() == m1.getClass());
        assertTrue(m6.getClass() == m2.getClass() || m6.getClass() == m1.getClass());
        assertTrue(m7.getClass() == m2.getClass() || m7.getClass() == m1.getClass());
    }

    @Test
    void testSetGameState() {
        assertTrue(game.getGameState());
        game.setGameState(true);
        assertTrue(game.getGameState());
        game.setGameState(false);
        assertFalse(game.getGameState());
    }

    @Test
    void testGetPowerUp() {
        assertTrue(game.getPowerUp("ATK BOOST").getClass() == new AttackBlessing(game).getClass());
        assertTrue(game.getPowerUp("DEF BOOST").getClass() == new DefenseBlessing(game).getClass());
        assertTrue(game.getPowerUp("RANGE BOOST").getClass() == new RangeBlessing(game).getClass());
        assertTrue(game.getPowerUp("LIFE BLESSING").getClass() == new LifeBlessing(game).getClass());
        assertTrue(game.getPowerUp("HEALING BLESSING").getClass() == new HealingBlessing(game).getClass());
    }
}
