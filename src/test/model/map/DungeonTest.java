package model.map;

import model.Enemy;
import model.Game;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DungeonTest {

    Dungeon map;
    Game game;

    @BeforeEach
    void runBefore(){
        map = new Map1();
        game = new Game();
    }

    @Test
    void testBuildBasicWalls() {
        assertEquals(5, map.getWallTile().size());

    }

    @Test
    void testCheckCollisionWall() {
        assertTrue(map.checkCollisionWall(new Enemy(new Position(40, 40), game)));
        assertFalse(map.checkCollisionWall(new Enemy(new Position(80, 80), game)));
    }

    @Test
    void testCheckCollisionDoor() {
        assertTrue(map.checkCollisionDoor(new Enemy(new Position(960, 270), game)));
        assertFalse(map.checkCollisionDoor(new Enemy(new Position(920, 270), game)));
    }

    @Test
    void testClear() {
        map.clear();
        assertTrue(map.getDoorTile().isEmpty());
        assertTrue(map.getWallTile().isEmpty());
    }
}
