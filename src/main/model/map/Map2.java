package model.map;

import model.Dungeon;
import model.Position;

// represents a map with basic walls and additional wall tiles in the middle
public class Map2 extends Dungeon {

    //Effects: Constructs a map with default walls and doors and an additional
    //         6 4x4 walls in middle of the map
    public Map2() {
        super();
        addPillar4x4(new Position(7, 5));
        addPillar4x4(new Position(7, 14));
        addPillar4x4(new Position(17, 5));
        addPillar4x4(new Position(17, 14));
        addPillar4x4(new Position(27, 5));
        addPillar4x4(new Position(27, 14));

    }

    //Modifies: this
    //Effects: Adds a new 4x4 solid block in wall tiles, with the input being the input
    public void addPillar4x4(Position position) {
        int x = position.getX();
        int y = position.getY();
        for (int i = x; i < x + 4; i++) {
            for (int j = y; j < y + 4; j++) {
                wallTile.add(new Position(i, j));
            }
        }
    }
}
