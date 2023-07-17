package model.map;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Map2Test {
    Map2 map2;

    @BeforeEach
    void runBefore() {
        map2 = new Map2();
    }

    @Test
    void testConstructor() {
        assertFalse(map2.getWallTile().contains(new Position(1, 1)));
        assertTrue(map2.getWallTile().contains(new Position(7, 5)));
        assertTrue(map2.getWallTile().contains(new Position(7, 14)));
        assertTrue(map2.getWallTile().contains(new Position(17, 5)));
        assertTrue(map2.getWallTile().contains(new Position(17, 14)));
        assertTrue(map2.getWallTile().contains(new Position(27, 5)));
        assertTrue(map2.getWallTile().contains(new Position(27, 14)));
    }

    @Test
    void testAddPillar() {
        map2.clear();
        map2.addPillar4x4(new Position(1, 1));
        assertTrue(map2.getWallTile().contains(new Position(1, 1)));
        assertTrue(map2.getWallTile().contains(new Position(1, 4)));
        assertTrue(map2.getWallTile().contains(new Position(4, 1)));
        assertTrue(map2.getWallTile().contains(new Position(4, 4)));
        assertFalse(map2.getWallTile().contains(new Position(1, 5)));
    }
}
