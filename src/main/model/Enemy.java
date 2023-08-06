package model;

import model.projectiles.Projectile;
import ui.Vector;

import java.util.logging.Level;

import static javax.swing.Spring.scale;

// Represents a enemy with properties of an entity
// with additional fields health/attack/defense and attack cooldown
public class Enemy extends Entity {

    private static final int HEALTH = 30;
    private static final int DEFENSE = 1;
    private static final int SPEED = 1;
    private static final int BULLET_DAMAGE = 10;
    private static final int CONTACT_DAMAGE = 20;
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

    //Effects: Constructs an enemy with scaled stats based on the level
    public Enemy(Position position, int level, Game game) {
        super(position, game);
        scale(level);
    }

    //Effects: Constructs an enemy with an initial start position, health of 30, defense of 0
    //         attack of 40, move cooldown of 1s, and attack cooldown of 1s
    public Enemy(Position position,  Game game) {
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
                new Vector(dx, dy, 7), bulletDamage,
                100, game));
    }

    public void scale(int level) {
        level = level - 1;
        bulletDamage += Math.round(BULLET_DAMAGE * level * 0.1);
        health += Math.round(HEALTH * level * 0.1);
        contactDamage += Math.round(CONTACT_DAMAGE * level * 0.1);
        defense += Math.round(DEFENSE * level);
        speed += Math.floor(level / 4);
        System.out.println(bulletDamage);
        System.out.println(health);
        System.out.println(contactDamage);
        System.out.println(defense);
        System.out.println(speed);
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
