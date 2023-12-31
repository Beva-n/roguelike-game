package model;

import java.awt.*;

// represents a entity with a position and move cooldown and the game its part of
public abstract class Entity {

    protected Position position;
    protected int width;
    protected int height;
    protected Game game;

    //Effects: Constructs an entity with given position, a given max movecooldown and
    //         an initial movecooldown of 0
    public Entity(Position position, Game game) {
        this.position = position;
        this.game = game;
        width = 24;
        height = 24;
    }

    //Modifies: this
    //Effects: updates some fields of the entity
    public abstract void update();

/*    //REQUIRES: direction == 'a' or 's' or 'w' or 'd'
    //Modifies: this
    //Effects: Move the entity in a direction if move cooldown is smaller or equal to 0
    public void move(char direction) {
        if (moveCooldown > 0) {
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
            default:
                moveLeft();
        }
    }

    //Modifies: this
    //Effects: move the entity to the right unless the move will result in entity hitting wall
    public void moveRight() {
        if (game.getMap().checkCollisionWall(new Position(position.getX() + 1, position.getY()))) {
            return;
        }
        position.editPosX(1);
    }

    //Modifies: this
    //Effects: move the entity to the left unless the move will result in entity hitting wall
    public void moveLeft() {
        if (game.getMap().checkCollisionWall(new Position(position.getX() - 1, position.getY()))) {
            return;
        }
        position.editPosX(-1);
    }

    //Modifies: this
    //Effects: move the entity up unless the move will result in entity hitting wall
    public void moveUp() {
        if (game.getMap().checkCollisionWall(new Position(position.getX(), position.getY() - 1))) {
            return;
        }
        position.editPosY(-1);
    }

    //Modifies: this
    //Effects: move the entity down unless the move will result in entity hitting wall
    public void moveDown() {
        if (game.getMap().checkCollisionWall(new Position(position.getX(), position.getY() + 1))) {
            return;
        }
        position.editPosY(1);
    }*/


    public Position getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Rectangle getHitBox() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }

    public Position getCenter() {
        return new Position(position.getX() + width / 2, position.getY() + height / 2);
    }
}
