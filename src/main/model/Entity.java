package model;

import com.googlecode.lanterna.screen.Screen;

public abstract class Entity {

    private final int maxMoveCooldown;
    protected Position position;
    protected int moveCooldown = 0;
    protected Game game;

    public Entity(Position position, int moveCooldown, Game game) {
        this.position = position;
        this.maxMoveCooldown = moveCooldown;
        this.game = game;
    }

    public abstract void update();

    public abstract void draw(Screen screen);

    public void move(char direction) {
        if (moveCooldown != 0) {
            return;
        }
        moveCooldown = maxMoveCooldown;
        switch (direction) {
            case 'w':
                moveUp();
                return;
            case 'd':
                moveRight();
                return;
            case 's':
                moveDown();
                return;
            case 'a':
                moveLeft();
        }
    }

    public void moveRight() {
        if (game.getMap().checkCollisionWall(new Position(position.getX() + 1, position.getY()))) {
            touchWallAction();
            return;
        }
        position.editPosX(1);
    }

    public void moveLeft() {
        if (game.getMap().checkCollisionWall(new Position(position.getX() - 1, position.getY()))) {
            touchWallAction();
            return;
        }
        position.editPosX(-1);
    }

    public void moveUp() {
        if (game.getMap().checkCollisionWall(new Position(position.getX(), position.getY() - 1))) {
            touchWallAction();
            return;
        }
        position.editPosY(-1);
    }

    public void moveDown() {
        if (game.getMap().checkCollisionWall(new Position(position.getX(), position.getY() + 1))) {
            touchWallAction();
            return;
        }
        position.editPosY(1);
    }

    //EFFECTS: no effect by default, can be overridden to modify fields
    public void touchWallAction() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
