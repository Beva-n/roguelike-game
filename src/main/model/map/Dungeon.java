package model.map;

import com.googlecode.lanterna.TextColor;
import model.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Represents a map with lists of wall tiles and door tiles
public abstract class Dungeon {

//    protected ArrayList<Position> wallTile;
//    protected ArrayList<Position> doorTile;
    private ArrayList<Rectangle> wallTile;
    private ArrayList<Rectangle> doorTile;
    public static final Color WALL_COLOR = new Color(150, 75, 0);
    public static final Color DOOR_COLOR1 = new Color(255, 0, 0);
    public static final Color DOOR_COLOR2 = new Color(255, 255, 0);

    //Effects: Constructs a basic map with outer walls and a door
    public Dungeon() {
        wallTile = new ArrayList<>();
        doorTile = new ArrayList<>();
        buildBasicWalls();
    }

    //Effects: Checks whether the given position overlaps with door tiles on the map
    public boolean checkCollisionDoor(Entity e) {
        for (Rectangle r : doorTile) {
            if (r.intersects(e.getHitBox())) {
                return true;
            }
        }
        return false;
    }

    //Effects: Checks whether the given position overlaps with wall tiles on the map
    public boolean checkCollisionWall(Entity e) {
        for (Rectangle r : wallTile) {
            if (r.intersects(e.getHitBox())) {
                return true;
            }
        }
        return false;
    }

    //Modifies: this
    //Effects: Adds the outer walls and a door to the map
    public void buildBasicWalls() {
        wallTile.add(new Rectangle(0, 40, 40, 560));
        wallTile.add(new Rectangle(960, 40, 40, 230));
        wallTile.add(new Rectangle(960, 370, 50, 230));
        wallTile.add(new Rectangle(40, 40, 920, 40));
        wallTile.add(new Rectangle(40, 560, 920, 40));

        doorTile.add(new Rectangle(960, 270, 40, 100));
//        // up wall
//        for (int i = 0; i < 40; i++) {
//            wallTile.add(new Position(i, 0));
//        }
//        // left wall
//        for (int i = 0; i < 23; i++) {
//            wallTile.add(new Position(0, i));
//        }
//        // down wall
//        for (int i = 0; i < 40; i++) {
//            wallTile.add(new Position(i, 22));
//        }
//        // right wall
//        for (int i = 0; i < 23; i++) {
//            if (i >= 10 && i <= 12) {
//                continue;
//            }
//            wallTile.add(new Position(39, i));
//        }
//
//        // door
//        doorTile.add(new Position(39, 10));
//        doorTile.add(new Position(39, 11));
//        doorTile.add(new Position(39, 12));
    }

    //Modifies: this
    //Effects: clears all the walls and doors in the map
    public void clear() {
        wallTile = new ArrayList<>();
        doorTile = new ArrayList<>();
    }

    public List<Rectangle> getWallTile() {
        // later issue
        return wallTile;
    }

    public List<Rectangle> getDoorTile() {
        // later issue
        return doorTile;
    }
}
