package model.map;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Map1Test {

    Map1 map1;

    @BeforeEach
    void runBefore(){
        map1 = new Map1();
    }

    @Test
    void testConstructor() {
        assertTrue(map1.getWallTile().contains(new Rectangle(0, 40, 40, 560)));
        assertTrue(map1.getWallTile().contains(new Rectangle(960, 40, 40, 230)));
        assertTrue(map1.getWallTile().contains(new Rectangle(960, 370, 40, 230)));

    }


}
