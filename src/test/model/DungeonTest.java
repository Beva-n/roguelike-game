package model;

import model.map.Dungeon;
import model.map.Map1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DungeonTest {

    Dungeon map;

    @BeforeEach
    void runBefore(){
        map = new Map1();
    }

    @Test
    void testBuildBasicWalls() {
        assertEquals(5, map.getWallTile().size());

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
