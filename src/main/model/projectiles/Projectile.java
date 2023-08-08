package model.projectiles;

import model.Entity;
import model.Game;
import model.Position;
import ui.Vector;

import java.awt.*;

// Represents a projectile with properties of an entity
// with additional fields including a fixed direction, damage, and lifetime
public class Projectile extends Entity {

    public static final Color PROJECTILE_COLOR = new Color(93, 63, 211);
    private Vector vector;
    private double x1;
    private double y1;

    private int lifeTime;
    private final int damage;

    //Effects: constructs a projectile with given direction, position, and damage
    public Projectile(Position position, Vector vector, int damage, int lifetime, Game game) {
        super(position, game);
        this.vector = vector;
        this.damage = damage;
        x1 = position.getX();
        y1 = position.getY();
        width = 16;
        height = 16;
        this.lifeTime = lifetime;
    }


    public Projectile(Position position, int damage, int lifetime, Game game) {
        super(position, game);
        this.damage = damage;
        x1 = position.getX();
        y1 = position.getY();
        this.lifeTime = lifetime;
    }


    //Modifies: this, game
    //Effects: if lifeTime is 0, deletes itself from the list of projectiles in game
    //         moves in its set direction if move cooldown is <= 0
    //         lowers move cooldown and lifetime by 1 respectively
    public void update() {
        if (lifeTime <= 0) {
            game.getPlayerProjectileManager().remove(this);
            game.getEnemyProjectileManager().remove(this);
        }
        move();
        lifeTime--;
    }

    public void move() {
        vector.update();
        x1 += vector.getVelocityX();
        y1 += vector.getVelocityY();
        position.setPosX((int) Math.round(x1));
        position.setPosY((int)Math.round(y1));
        if (game.getMap().checkCollisionWall(this)) {
            touchWallAction();
        }
    }

//    //Modifies: this
//    //Effects: moves the projectile to the right, deletes itself from the list of projectiles
//    //         if it collides in a wall
//    @Override
//    public void moveRight() {
//        if (game.getMap().checkCollisionWall(new Position(position.getX() + 1, position.getY()))) {
//            touchWallAction();
//            return;
//        }
//        position.editPosX(1);
//    }
//
//    //Modifies: this
//    //Effects: moves the projectile to the left, deletes itself from the list of projectiles
//    //         if it collides in a wall
//    @Override
//    public void moveLeft() {
//        if (game.getMap().checkCollisionWall(new Position(position.getX() - 1, position.getY()))) {
//            touchWallAction();
//            return;
//        }
//        position.editPosX(-1);
//    }
//
//    //Modifies: this
//    //Effects: moves the projectile up, deletes itself from the list of projectiles
//    //         if it collides in a wall
//    @Override
//    public void moveUp() {
//        if (game.getMap().checkCollisionWall(new Position(position.getX(), position.getY() - 1))) {
//            touchWallAction();
//            return;
//        }
//        position.editPosY(-1);
//    }
//
//
//    //Modifies: this
//    //Effects: moves the projectile down, deletes itself from the list of projectiles
//    //         if it collides in a wall
//    @Override
//    public void moveDown() {
//        if (game.getMap().checkCollisionWall(new Position(position.getX(), position.getY() + 1))) {
//            touchWallAction();
//            return;
//        }
//        position.editPosY(1);
//    }

    //Modifies: this
    //Effects: deletes the projectile from the game list of projectiles
    public void touchWallAction() {
        game.getPlayerProjectileManager().remove(this);
        game.getEnemyProjectileManager().remove(this);
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        x1 = position.getX();
        y1 = position.getY();
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

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public Color getColor() {
        return Color.red;
    }

}
