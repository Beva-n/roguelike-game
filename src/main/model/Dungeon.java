package model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public abstract class Dungeon {

    protected ArrayList<Position> getWallTile;
    protected ArrayList<Position> doorTile;
    public static final TextColor.RGB WALLCOLOR = new TextColor.RGB(150, 75,0);
    public static final TextColor.RGB DOORCOLOR1 = new TextColor.RGB(255, 0, 0);
    public static final TextColor.RGB DOORCOLOR2 = new TextColor.RGB(255, 255, 0);
    private Game game;

    //Effects: Constructs a basic map with outer walls and a door
    public Dungeon(Game game) {
        getWallTile = new ArrayList<>();
        doorTile = new ArrayList<>();
        this.game = game;
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
        for (Position pos : getWallTile) {
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
            getWallTile.add(new Position(i, 0));
        }
        // left wall
        for (int i = 0; i < 23; i++) {
            getWallTile.add(new Position(0, i));
        }
        // down wall
        for (int i = 0; i < 40; i++) {
            getWallTile.add(new Position(i, 22));
        }
        // right wall
        for (int i = 0; i < 23; i++) {
            if (i >= 10 && i <= 12) {
                continue;
            }
            getWallTile.add(new Position(39, i));
        }

        // door
        doorTile.add(new Position(39, 10));
        doorTile.add(new Position(39, 11));
        doorTile.add(new Position(39, 12));
    }

    //Modifies: this
    //Effects: clears all the walls and doors in the map
    public void clear() {
        getWallTile = new ArrayList<>();
        doorTile = new ArrayList<>();
    }

    public List<Position> getWallTile() {
        return getWallTile;
    }

    public List<Position> getDoorTile() {
        return doorTile;
    }
}
