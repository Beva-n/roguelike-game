package model.map;

import model.Dungeon;
import model.Game;
import model.Position;

public class Map2 extends Dungeon {
    public Map2(Game game) {
        super(game);
        addPillar4x4(new Position(7, 5));
        addPillar4x4(new Position(7, 14));
        addPillar4x4(new Position(17, 5));
        addPillar4x4(new Position(17, 14));
        addPillar4x4(new Position(27, 5));
        addPillar4x4(new Position(27, 14));

    }

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
