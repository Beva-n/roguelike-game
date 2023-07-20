package model;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;

// Represents a map with lists of wall tiles and door tiles
public abstract class Dungeon {

    protected ArrayList<Position> wallTile;
    protected ArrayList<Position> doorTile;
    public static final TextColor.RGB WALL_COLOR = new TextColor.RGB(150, 75,0);
    public static final TextColor.RGB DOOR_COLOR1 = new TextColor.RGB(255, 0, 0);
    public static final TextColor.RGB DOOR_COLOR2 = new TextColor.RGB(255, 255, 0);

    //Effects: Constructs a basic map with outer walls and a door
    public Dungeon() {
        wallTile = new ArrayList<>();
        doorTile = new ArrayList<>();
        buildBasicWalls();
    }

    //Effects: Checks whether the given position overlaps with door tiles on the map
    public boolean checkCollisionDoor(Position p) {
        for (Position pos : doorTile) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    //Effects: Checks whether the given position overlaps with wall tiles on the map
    public boolean checkCollisionWall(Position p) {
        for (Position pos : wallTile) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    //Modifies: this
    //Effects: Adds the outer walls and a door to the map
    public void buildBasicWalls() {
        // up wall
        for (int i = 0; i < 40; i++) {
            wallTile.add(new Position(i, 0));
        }
        // left wall
        for (int i = 0; i < 23; i++) {
            wallTile.add(new Position(0, i));
        }
        // down wall
        for (int i = 0; i < 40; i++) {
            wallTile.add(new Position(i, 22));
        }
        // right wall
        for (int i = 0; i < 23; i++) {
            if (i >= 10 && i <= 12) {
                continue;
            }
            wallTile.add(new Position(39, i));
        }

        // door
        doorTile.add(new Position(39, 10));
        doorTile.add(new Position(39, 11));
        doorTile.add(new Position(39, 12));
    }

    //Modifies: this
    //Effects: clears all the walls and doors in the map
    public void clear() {
        wallTile = new ArrayList<>();
        doorTile = new ArrayList<>();
    }

    public List<Position> getWallTile() {
        return wallTile;
    }

    public List<Position> getDoorTile() {
        return doorTile;
    }
}
