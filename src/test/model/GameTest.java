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
        Dungeon m = game.getRandomMap();
        Map1 m1 = new Map1(game);
        Map2 m2 = new Map2(game);
        assertTrue(m.getClass() == m2.getClass() || m.getClass() == m1.getClass());
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
