package model.map;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;

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
        assertFalse(map2.getWallTile().contains(new Rectangle(180, 150, 100, 190)));
        assertTrue(map2.getWallTile().contains(new Rectangle(420, 150, 100, 100)));
        assertTrue(map2.getWallTile().contains(new Rectangle(660, 150, 100, 100)));
        assertTrue(map2.getWallTile().contains(new Rectangle(180, 390, 100, 100)));
        assertTrue(map2.getWallTile().contains(new Rectangle(420, 390, 100, 100)));
        assertTrue(map2.getWallTile().contains(new Rectangle(660, 390, 100, 100)));
    }
}
