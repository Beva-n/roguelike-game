package model;

import model.manager.EnemyProjectileManager;
import model.projectiles.*;
import ui.Vector;

import java.awt.*;

// Represents a special enemy with enhanced stats and a unique attack behaviour
public class Boss extends Enemy {

    private static final int HEALTH = 500;
    private static final int DEFENSE = 10;

    private static final int DAMAGE = 10;
    private int moveset = 0;
    private int moveDowntime = 0;
    private int damage = 10;

    //Effects: constructs a boss with a fixed position, and size, the boss's stats scales with
    //         the level it is encountered in
    public Boss(int level, Game game) {
        super(new Position(800, 260), game);
        scale(level);
        setSpeed(3);
        width = 30;
        height = 50;
    }

    //Modifies: this
    //Effects: sets the damage, defense, and health of the boss based on the level
    @Override
    public void scale(int level) {
        setHealth((int) Math.round(HEALTH * (1 + ((level - 5.0) / 10.0))));
        setDefense((int) Math.round(DEFENSE * ((level - 5.0) / 5.0)));
        damage = DAMAGE * (level / 5);
    }

    //Modifies: this, game
    //Effects: deletes, moves, and shoots depending on the current scenario
    //         if health is 0, deletes the object from game
    //         if moveDowntime is 0, performs the next action in the move cycle
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

    //Modifies: this
    //Effects: moves the boss based the player's current location, the move will not take place
    //         if the boss is sufficiently close to the player
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

    //Modifies: this, game
    //Effects: picks an action in the moveset to perform
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

    //Modifies: this, game
    //Effects: summons a ring of blue accelerating projectile at the given x and y relative to the bosses
    //         advances the moveset and resets move cooldown
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

    //Modifies: this, game
    //Effects: summons a ring of red accelerating projectile at the given x and y relative to the bosses
    //         advances the moveset and resets move cooldown
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

    //Modifies: this, game
    //Effects: summons a ring of pink lingering projectile at the boss's position
    //         advances the moveset and resets move cooldown
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

    //Modifies: this, game
    //Effects: summons a burst of decelerating blue projectile in 8 directions
    //         advances the moveset and resets move cooldown
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

    //Modifies: this, game
    //Effects: summons a burst of decelerating red projectile in 8 directions
    //         advances the moveset and resets move cooldown
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

    //Modifies: game
    //Effects: Adds a blue accelerating projectile to the game's projectile manager
    //         the projectile's velocity depends on x and y, and location by posX and posY
    public void spawnBlueRing(double x, double y, int posX, int posY) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new BlueRingProjectile(
                new Position(getPosition().getX() + posX, getPosition().getY() + posY), x, y, damage, 150, game));
    }

    //Modifies: game
    //Effects: Adds a red accelerating projectile to the game's projectile manager
    //         the projectile's vector depends on x and y, and location by posX and posY
    public void spawnRedRing(double x, double y, int posX, int posY) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new RedRingProjectile(
                new Position(getPosition().getX() + posX, getPosition().getY() + posY), x, y, damage, 150, game));
    }

    //Modifies: game
    //Effects: Adds pink lingering projectile to the game's projectile manager
    //         the projectile's velocity is represented by x and y
    public void spawnPinkRing(double x, double y) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new PinkRingProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 180, game));
    }

    //Modifies: game
    //Effects: Adds 3 decelerating red projectile to the game's projectile manager
    //         the projectile's vector is depends on x and y
    public void spawnRedRotational(double x, double y) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 10, game));
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 7, game));
        enemyProjectileManager.spawn(new RedRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 4, game));
    }

    //Modifies: game
    //Effects: Adds 3 decelerating blue projectile to the game's projectile manager
    //         the projectile's vector is depends on
    public void spawnBlueRotational(double x, double y) {
        EnemyProjectileManager enemyProjectileManager = game.getEnemyProjectileManager();
        enemyProjectileManager.spawn(new BlueRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 10, game));
        enemyProjectileManager.spawn(new BlueRotationalProjectile(
                new Position(getPosition().getX(), getPosition().getY() + 20), x, y, damage, 60, 7, game));
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
