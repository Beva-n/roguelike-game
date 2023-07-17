package model;

import com.googlecode.lanterna.screen.Screen;

public abstract class Entity {

    private final int maxMoveCooldown;
    protected Position position;
    protected int moveCooldown = 0;
    protected Game game;

    //Effects: Constructs a entity with given position, a given max movecooldown and
    //         a initial movecooldown of 0
    public Entity(Position position, int moveCooldown, Game game) {
        this.position = position;
        this.maxMoveCooldown = moveCooldown;
        this.game = game;
    }

    //Modifies: this
    //Effects: updates some fields of the entity
    public abstract void update();

    //Modifies: screen
    //Effects: Draws the entity on the screen
    public abstract void draw(Screen screen);

    //REQUIRES: direction == 'a' or 's' or 'w' or 'd'
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
            case 'a':
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
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getMoveCooldown() {
        return moveCooldown;
    }

    public void setMovecooldown(int cooldown) {
        moveCooldown = cooldown;
    }
}
