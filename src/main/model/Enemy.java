package model;

import com.googlecode.lanterna.TextColor;
import ui.Vector;

import java.awt.*;
import java.util.Random;

// Represents a enemy with properties of an entity
// with additional fields health/attack/defense and attack cooldown
public class Enemy extends Entity {
    private static final int ATTACKCD = 30;
    private static final int SHOOTCD = 60;
    private int health = 30;
    private int defense = 0;
    private int contactDamage = 20;
    private int bulletDamage = 10;
    private int attackCd = 0;
    private int shootCd = 0;
    private int speed = 5;
    private boolean moveAble = false;

    //Effects: Constructs an enemy with an initial start position, health of 30, defense of 0
    //         attack of 40, move cooldown of 1s, and attack cooldown of 1s
    public Enemy(Position position, Game game) {
        super(position, game);
    }

    //Modifies: this, game
    //Effects: if health is 0, deletes itself from game's enemymanager
    //         if movecooldown is 0, moves 1 tile in a random direction, else lowers movecooldown
    //         lowers attack cooldown by 1
    public void update() {

        if (health <= 0) {
            game.getEnemyManager().remove(this);
            return;
        }

        if (!moveAble) {
            moveAble = true;
        } else {
            move();
            moveAble = false;
        }

        if (shootCd <= 0) {
            shoot();
            shootCd = SHOOTCD;
        }

        shootCd--;
        attackCd--;
    }

    public void move() {
        double dx = game.getPlayerX() - position.getX();
        double dy = game.getPlayerY() - position.getY();
        Vector movement = new Vector(dx, dy, speed);
        position.editPosX(movement.getVelocityX());
        position.editPosY(movement.getVelocityY());
        if (game.getMap().checkCollisionWall(this)) {
            position.editPosX(-movement.getVelocityX());
            position.editPosY(-movement.getVelocityY());
        }
    }

    public void shoot() {
        double dx = game.getPlayerX() - position.getX();
        double dy = game.getPlayerY() - position.getY();
        game.getEnemyProjectileManager().spawn(new Projectile(getCenter(),
                new Vector(dx, dy, 10), bulletDamage,
                10, game));
    }

    //Requires: damage > 0
    //Modifies: this
    //Effects: reduces the enemy's health by the higher between damage - defense
    //         and 10% of damage
    public void reduceHealth(int damage) {
        health -= Math.max(damage - defense, damage * 0.1);
    }

    //Modifies: game
    //Effects: attacks the player and resets its own attack cooldown to max
    public void attackPlayer() {
        if (attackCd <= 0) {
            game.getPlayer().decreaseHealth(contactDamage);
            attackCd = ATTACKCD;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getContactDamage() {
        return contactDamage;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttackcd() {
        return attackCd;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
