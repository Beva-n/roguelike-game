package model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ui.TerminalGame;

public class Player extends Entity {

    private static final TextColor color = TextColor.ANSI.GREEN;
    private static final int MOVECOOLDOWN = TerminalGame.FPS / 3;
    private String faceDirection;
    private int maxHealth = 100;
    private int health;
    private int maxDefense = 20;
    private int defense;
    private int attack;
    private int maxAttack = 15;
    private int shootCd = 0;
    private int maxShootCd = 15;

    public Player(Game game) {
        super(new Position(1, 6), MOVECOOLDOWN, game);
        faceDirection = "right";
        this.health = maxHealth;
        this.defense = maxDefense;
        this.attack = maxAttack;
    }

    public void update() {
        if (moveCooldown > 0) {
            moveCooldown--;
        }
        shootCd--;
    }

    public void draw(Screen screen) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(position.getX() * 2, position.getY() + 1, Game.BLOCK);
        text.putString(position.getX() * 2 + 1, position.getY() + 1, Game.BLOCK);
    }

    public void shoot() {
        if (shootCd > 0) {
            return;
        }
        shootCd = maxShootCd;
        switch (faceDirection) {
            case "down":
                game.getProjectileManager().spawn(new Projectile(
                        new Position(position.getX(), position.getY() + 1), 's', attack, game));
                return;
            case "up":
                game.getProjectileManager().spawn(new Projectile(
                        new Position(position.getX(), position.getY() - 1), 'w', attack, game));
                return;
            case "left":
                game.getProjectileManager().spawn(new Projectile(
                        new Position(position.getX() - 1, position.getY()), 'a', attack, game));
                return;
            case "right":
                game.getProjectileManager().spawn(new Projectile(
                        new Position(position.getX() + 1, position.getY()), 'd', attack, game));
        }
    }

    @Override
    public void moveRight() {
        super.moveRight();
        faceDirection = "right";
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
        faceDirection = "left";
    }

    @Override
    public void moveUp() {
        super.moveUp();
        faceDirection = "up";
    }

    @Override
    public void moveDown() {
        super.moveDown();
        faceDirection = "down";
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public void decreaseHealth(int damage) {
        health -= damage;
    }
}
