package model;

import model.manager.EnemyProjectileManager;
import model.projectiles.BlueRotationalProjectile;
import model.projectiles.RedRotationalProjectile;
import ui.Vector;

import java.awt.*;

public class Boss extends Enemy {

    private static int DOWNTIME = 10;
    private int downtime = 0;
    private int moveset = 0;
    private int moveDowntime = 0;
    private boolean moveable = false;

    public Boss(Game game) {
        super(new Position(800, 260), game);
        setHealth(500);
        setDefense(0);
        setSpeed(3);
        width = 30;
        height = 50;
    }

    @Override
    public void update() {
        //check ded
        if (getHealth() <= 0) {
            game.getEnemyManager().remove(this);
            return;
        }

        //downtime stuff
        if (downtime > 0) {
            return;
        }

        //movement
        if (moveable) {
            move();
        } else {
            moveable = true;
        }

        //bullet pattern
        if (moveDowntime <= 0) {
            doShoot();
        }
        downtime--;
        moveDowntime--;
    }

    @Override
    public void move() {
        double dx = game.getPlayerX() - position.getX();
        double dy = game.getPlayerY() - position.getY();
        Vector movement = new Vector(dx, dy, getSpeed());
        position.editPosX(movement.getVelocityX());
        position.editPosY(movement.getVelocityY());
        Rectangle aroundPlayer = new Rectangle(game.getPlayerX() - 60, game.getPlayerY() - 60,
                120 + game.getPlayer().getWidth(), 120 + game.getPlayer().getHeight());
        if (game.getMap().checkCollisionWall(this) || aroundPlayer.intersects(getHitBox())) {
            position.editPosX(-movement.getVelocityX());
            position.editPosY(-movement.getVelocityY());
        }
    }

    //Effects: picks a action in the moveset to perform
    public void doShoot() {
        System.out.println(moveset);
        switch (moveset) {
//            case 5:
//                blueRing();
//            case 4:
//                redRing();
//            case 3:
//                redRingFirst();
//            case 2:
//                pinkRing();
            case 1:
                redRotational();
                return;
            case 0:
                blueRotational();
                return;
            default:
        }
    }

    public void blueRotational() {
        spawnBlueRotational(-10.0, -2.5);
        spawnBlueRotational(10.0, -2.5);
        spawnBlueRotational(2.5, 10.0);
        spawnBlueRotational(-2.5, -10.0);
        spawnBlueRotational(2.5, -10);
        spawnBlueRotational(10, 2.5);
        spawnBlueRotational(-10, 2.5);
        spawnBlueRotational(-2.5, 10);
        moveDowntime = 45;
        moveset = 1;
    }

    public void redRotational() {
        spawnRedRotational(-10.0, 0);
        spawnRedRotational(10.0, 0);
        spawnRedRotational(0, 10.0);
        spawnRedRotational(0, -10.0);
        spawnRedRotational(-10, -10);
        spawnRedRotational(10, -10);
        spawnRedRotational(-10, 10);
        spawnRedRotational(10, 10);
        moveDowntime = 100;
        moveset = 0;
    }

    public void spawnRedRotational(double x, double y) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, 10, 120, 10, game));
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, 10, 120, 7,game));
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, 10, 120, 4, game));
    }

    public void spawnBlueRotational(double x, double y) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new BlueRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, 10, 120, 10, game));
        enemyProjectileManager.spawn(new BlueRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, 10, 120, 7,game));
        enemyProjectileManager.spawn(new BlueRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, 10, 120, 4, game));
    }





}
