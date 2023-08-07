package model;

import model.manager.EnemyProjectileManager;
import model.projectiles.*;
import ui.Vector;

import java.awt.*;

public class Boss extends Enemy {

    private static final int HEALTH = 500;
    private static final int DEFENSE = 10;

    private static final int DAMAGE = 10;
    private int moveset = 0;
    private int moveDowntime = 0;
    private int damage = 10;

    public Boss(int level, Game game) {
        super(new Position(800, 260), game);
        scale(level);
        setSpeed(3);
        width = 30;
        height = 50;
    }

    @Override
    public void scale(int level) {
        setHealth((int)Math.round(HEALTH * (1 + ((level - 5.0) / 10.0))));
        setDefense((int)Math.round(DEFENSE * ((level - 5.0) / 5.0)));
        damage = DAMAGE * (level / 5);
    }

    @Override
    public void update() {
        //check ded
        if (getHealth() <= 0) {
            game.getEnemyManager().remove(this);
            return;
        }

        //movement
        move();

        //bullet pattern
        if (moveDowntime <= 0) {
            doShoot();
        }
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
        if (aroundPlayer.intersects(getHitBox())) {
            position.editPosX(-movement.getVelocityX());
            position.editPosY(-movement.getVelocityY());
        }
    }

    //Effects: picks a action in the moveset to perform
    public void doShoot() {
        switch (moveset) {
            case 6:
                blueRing(40 + getWidth(), 20 + getHeight());
                blueRing(20 + getWidth(), 40 + getHeight());
                break;
            case 5:
                redRing(-20, -40);
                redRing(-40, -20);
                break;
            case 4:
            case 1:
                redRotational();
                break;
            case 3:
            case 0:
                blueRotational();
                break;
            default:
                pinkRing();
        }
    }

    public void blueRing(int posX, int posY) {
        spawnBlueRing(-9.2388, -3.82683, posX, posY);
        spawnBlueRing(-9.2388, 3.82683, posX, posY);
        spawnBlueRing(9.2388, -3.82683, posX, posY);
        spawnBlueRing(9.2388, 3.82683, posX, posY);
        spawnBlueRing(3.82683, 9.2388, posX, posY);
        spawnBlueRing(-3.82683, 9.2388, posX, posY);
        spawnBlueRing(3.82683, -9.2388, posX, posY);
        spawnBlueRing(-3.82683, -9.2388, posX, posY);
        spawnBlueRing(-10.0, 0, posX, posY);
        spawnBlueRing(10.0, 0, posX, posY);
        spawnBlueRing(0, 10.0, posX, posY);
        spawnBlueRing(0, -10.0, posX, posY);
        spawnBlueRing(-10, -10, posX, posY);
        spawnBlueRing(10, -10, posX, posY);
        spawnBlueRing(-10, 10, posX, posY);
        spawnBlueRing(10, 10, posX, posY);
        if (posX == 20 + getWidth()) {
            moveset = 0;
            moveDowntime = 0;
        }
    }

    public void redRing(int posX, int posY) {
        spawnRedRing(-9.2388, -3.82683, posX, posY);
        spawnRedRing(-9.2388, 3.82683, posX, posY);
        spawnRedRing(9.2388, -3.82683, posX, posY);
        spawnRedRing(9.2388, 3.82683, posX, posY);
        spawnRedRing(3.82683, 9.2388, posX, posY);
        spawnRedRing(-3.82683, 9.2388, posX, posY);
        spawnRedRing(3.82683, -9.2388, posX, posY);
        spawnRedRing(-3.82683, -9.2388, posX, posY);
        spawnRedRing(-10.0, 0, posX, posY);
        spawnRedRing(10.0, 0, posX, posY);
        spawnRedRing(0, 10.0, posX, posY);
        spawnRedRing(0, -10.0, posX, posY);
        spawnRedRing(-10, -10, posX, posY);
        spawnRedRing(10, -10, posX, posY);
        spawnRedRing(-10, 10, posX, posY);
        spawnRedRing(10, 10, posX, posY);
        if (posX == -40) {
            moveDowntime = 20;
            moveset++;
        }
    }

    public void pinkRing() {
        spawnPinkRing(-9.2388, -3.82683);
        spawnPinkRing(-9.2388, 3.82683);
        spawnPinkRing(9.2388, -3.82683);
        spawnPinkRing(9.2388, 3.82683);
        spawnPinkRing(3.82683, 9.2388);
        spawnPinkRing(-3.82683, 9.2388);
        spawnPinkRing(3.82683, -9.2388);
        spawnPinkRing(-3.82683, -9.2388);
        spawnPinkRing(-10.0, 0);
        spawnPinkRing(10.0, 0);
        spawnPinkRing(0, 10.0);
        spawnPinkRing(0, -10.0);
        spawnPinkRing(-10, -10);
        spawnPinkRing(10, -10);
        spawnPinkRing(-10, 10);
        spawnPinkRing(10, 10);
        moveDowntime = 40;
        moveset++;
    }

    public void blueRotational() {
        spawnBlueRotational(-9.2388, -3.82683);
        spawnBlueRotational(-9.2388, 3.82683);
        spawnBlueRotational(9.2388, -3.82683);
        spawnBlueRotational(9.2388, 3.82683);
        spawnBlueRotational(3.82683, 9.2388);
        spawnBlueRotational(-3.82683, 9.2388);
        spawnBlueRotational(3.82683, -9.2388);
        spawnBlueRotational(-3.82683, -9.2388);
        moveDowntime = 45;
        moveset++;
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
        moveDowntime = 45;
        if (moveset == 4) {
            pinkRing();
            return;
        }
        moveset++;
    }

    public void spawnBlueRing(double x, double y, int posX, int posY) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new BlueRingProjectile(
                new Position(getPosition().getX() + posX, getPosition().getY() + posY), x, y, damage, 150, game));
    }

    public void spawnRedRing(double x, double y, int posX, int posY) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new RedRingProjectile(
                new Position(getPosition().getX() + posX, getPosition().getY() + posY), x, y, damage, 150, game));
    }

    public void spawnPinkRing(double x, double y) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new PinkRingProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 180, game));
    }

    public void spawnRedRotational(double x, double y) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 10, game));
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 7,game));
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 4, game));
    }

    public void spawnBlueRotational(double x, double y) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new BlueRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 10, game));
        enemyProjectileManager.spawn(new BlueRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 7,game));
        enemyProjectileManager.spawn(new BlueRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 4, game));
    }

    public int getMoveset() {
        return moveset;
    }

    public int getMoveDowntime() {
        return moveDowntime;
    }
}
