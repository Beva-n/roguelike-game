package model;

import model.map.Map1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DungeonTest {

    Game game;
    Dungeon map;

    @BeforeEach
    void runBefore(){
        game = new Game();
        map = new Map1(game);
    }

    @Test
    void testBuildBasicWalls() {
        // walls
        assertTrue(map.getWallTile().contains(new Position(0, 0)));
        assertTrue(map.getWallTile().contains(new Position(0, 22)));
        assertTrue(map.getWallTile().contains(new Position(39, 0)));
        assertTrue(map.getWallTile().contains(new Position(39, 22)));
        assertFalse(map.getWallTile().contains(new Position(1, 1)));

        // doors
        assertTrue(map.getDoorTile().contains(new Position(39, 10)));
        assertTrue(map.getDoorTile().contains(new Position(39, 11)));
        assertTrue(map.getDoorTile().contains(new Position(39, 12)));
        assertFalse(map.getDoorTile().contains(new Position(39, 13)));
    }

    @Test
    void testCheckCollisionWall() {
        assertTrue(map.checkCollisionWall(new Position(0, 0)));
        assertFalse(map.checkCollisionWall(new Position(1, 1)));
    }

    @Test
    void testCheckCollisionDoor() {
        assertTrue(map.checkCollisionDoor(new Position(39, 10)));
        assertFalse(map.checkCollisionDoor(new Position(39, 13)));
    }

    @Test
    void testClear() {
        map.clear();
        assertTrue(map.getDoorTile().isEmpty());
        assertTrue(map.getWallTile().isEmpty());
    }
}
