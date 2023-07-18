package model;

import model.map.Map1;
import model.map.Map2;
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
        game.getSelectionScreen();
        assertTrue(game.getPowerUpManager().getPowerUps().isEmpty());
        assertFalse(game.getGameState());
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
        game.getPlayer().setPosition(new Position(39, 10));
        assertTrue(game.nextLevel());
    }

    @Test
    void testUpdateGame() {
        assertEquals(0, game.getPlayer().getMoveCooldown());
        game.updateGame();
        assertEquals(0, game.getPlayer().getMoveCooldown());
        game.setGameState(true);
        game.updateGame();
        assertEquals(-1, game.getPlayer().getMoveCooldown());
        game.getEnemyManager().clearEntities();
        game.getPlayer().setPosition(new Position(39, 10));
        game.updateGame();
        assertEquals(new Position(1, 6), game.getPlayer().getPosition());
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
        assertFalse(game.getGameState());
        game.setGameState(true);
        assertTrue(game.getGameState());
        game.setGameState(false);
        assertFalse(game.getGameState());
    }
}
