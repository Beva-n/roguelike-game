package model;

import com.googlecode.lanterna.TextColor;
import ui.TerminalGame;

// Represents a projectile with properties of an entity
// with additional fields including a fixed direction, damage, and lifetime
public class Projectile extends Entity {

    public static final TextColor.RGB PROJECTILECOLOR = new TextColor.RGB(128, 0, 128);
    // may change speed to be none constant later
    private static final int MOVECOOLDOWN = TerminalGame.FPS / 5;
    private final char direction;
    private int lifeTime;
    private final int damage;

    //REQUIRES: direction == 'w' or 'a' 'or 's' or 'd'
    //Effects: constructs a projectile with given direction, position, and damage
    //         its lifetime is equal to the player's
    public Projectile(Position position, char direction, int damage, Game game) {
        super(position, MOVECOOLDOWN, game);
        this.direction = direction;
        this.damage = damage;
        lifeTime = game.getPlayer().getRange();
    }

    //Modifies: this, game
    //Effects: if lifeTime is 0, deletes itself from the list of projectiles in game
    //         moves in its set direction if move cooldown is <= 0
    //         lowers move cooldown and lifetime by 1 respectively
    public void update() {
        if (lifeTime == 0) {
            game.getProjectileManager().remove(this);
        }
        move(direction);
        moveCooldown--;
        lifeTime--;
    }

    //Modifies: this
    //Effects: moves the projectile to the right, deletes itself from the list of projectiles
    //         if it collides in a wall
    @Override
    public void moveRight() {
        if (game.getMap().checkCollisionWall(new Position(position.getX() + 1, position.getY()))) {
            touchWallAction();
            return;
        }
        position.editPosX(1);
    }

    //Modifies: this
    //Effects: moves the projectile to the left, deletes itself from the list of projectiles
    //         if it collides in a wall
    @Override
    public void moveLeft() {
        if (game.getMap().checkCollisionWall(new Position(position.getX() - 1, position.getY()))) {
            touchWallAction();
            return;
        }
        position.editPosX(-1);
    }

    //Modifies: this
    //Effects: moves the projectile up, deletes itself from the list of projectiles
    //         if it collides in a wall
    @Override
    public void moveUp() {
        if (game.getMap().checkCollisionWall(new Position(position.getX(), position.getY() - 1))) {
            touchWallAction();
            return;
        }
        position.editPosY(-1);
    }


    //Modifies: this
    //Effects: moves the projectile down, deletes itself from the list of projectiles
    //         if it collides in a wall
    @Override
    public void moveDown() {
        if (game.getMap().checkCollisionWall(new Position(position.getX(), position.getY() + 1))) {
            touchWallAction();
            return;
        }
        position.editPosY(1);
    }

    //Modifies: this
    //Effects: deletes the projectile from the game list of projectiles
    public void touchWallAction() {
        game.getProjectileManager().remove(this);
    }

    public int getDamage() {
        return damage;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
