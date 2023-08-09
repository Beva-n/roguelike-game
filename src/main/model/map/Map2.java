package model.map;

import java.awt.*;

// represents a map with basic walls and additional wall tiles in the middle
public class Map2 extends Dungeon {

    //Effects: Constructs a map with default walls and doors and an additional
    //         6 pillars scattered around the middle of the map
    public Map2() {
        super();
        getWallTile().add(new Rectangle(180, 150, 100, 100));
        getWallTile().add(new Rectangle(420, 150, 100, 100));
        getWallTile().add(new Rectangle(660, 150, 100, 100));
        getWallTile().add(new Rectangle(180, 390, 100, 100));
        getWallTile().add(new Rectangle(420, 390, 100, 100));
        getWallTile().add(new Rectangle(660, 390, 100, 100));
    }

}
