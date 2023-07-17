package model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ui.TerminalGame;

public class Projectile extends Entity {

    private static final TextColor.RGB projectileColor = new TextColor.RGB(128, 0, 128);
    // may change speed to be none constant later
    private static final int MOVECOOLDOWN = TerminalGame.FPS / 5;
    private final char direction;
    private int lifeTime;
    private int damage;

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

    //Modifies: screen
    //Effects: draws the projectile on the screen, as a purple circle
    public void draw(Screen screen) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(projectileColor);
        text.putString(position.getX() * 2, position.getY() + 1, Game.BALL);
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
            System.out.println("no");
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
}
