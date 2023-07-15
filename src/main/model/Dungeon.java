package model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;

public abstract class Dungeon {

    protected ArrayList<Position> wallTile;
    protected ArrayList<Position> doorTile;
    private static final TextColor.RGB wallColor = new TextColor.RGB(150, 75,0);
    private static final TextColor.RGB doorColor1 = new TextColor.RGB(255, 0, 0);
    private static final TextColor.RGB doorColor2 = new TextColor.RGB(255, 255, 0);
    private Game game;

    public Dungeon(Game game) {
        wallTile = new ArrayList<>();
        doorTile = new ArrayList<>();
        this.game = game;
        buildBasicWalls();
    }

    public boolean checkCollisionDoor(Position p) {
        for (Position pos : doorTile) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionWall(Position p) {
        for (Position pos : wallTile) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    public void draw(Screen screen) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(wallColor);
        for (Position tile : wallTile) {
            text.putString(tile.getX() * 2, tile.getY() + 1, Game.BLOCK);
            text.putString(tile.getX() * 2 + 1, tile.getY() + 1, Game.BLOCK);
        }
        if (game.roomCleared()) {
            text.setForegroundColor(doorColor2);
        } else {
            text.setForegroundColor(doorColor1);
        }
        for (Position tile : doorTile) {
            text.putString(tile.getX() * 2, tile.getY() + 1, Game.BLOCK);
            text.putString(tile.getX() * 2 + 1, tile.getY() + 1, Game.BLOCK);
        }
    }

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
}
