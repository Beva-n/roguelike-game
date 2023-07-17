package model.map;

import model.Game;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Map1Test {

    Map1 map1;

    @BeforeEach
    void runBefore(){
        map1 = new Map1();
    }

    @Test
    void testConstructor() {
        assertTrue(map1.getWallTile().contains(new Position(0, 1)));
        assertTrue(map1.getWallTile().contains(new Position(0, 5)));
        assertTrue(map1.getWallTile().contains(new Position(0, 14)));

    }


}
