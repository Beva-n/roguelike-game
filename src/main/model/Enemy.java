package model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.sun.security.auth.module.LdapLoginModule;
import ui.TerminalGame;

import java.util.Random;

public class Enemy extends Entity {

    private static final int MOVECOOLDOWN = TerminalGame.FPS / 1;
    private static final TextColor.RGB COLOR = new TextColor.RGB(255, 0, 0);
    private static final int ATTACKCD = 30;
    private Random rand = new Random();
    private int health = 30;
    private int defense = 0;
    private int attack = 40;
    private int attackCd = 0;

    public Enemy(Position position, Game game) {
        super(position, MOVECOOLDOWN, game);
    }

    public void update() {
        if (health <= 0) {
            game.getEnemyManager().remove(this);
        }
        if (moveCooldown > 0) {
            moveCooldown--;
        } else {
            int randomDirection = rand.nextInt(4);
            move("wasd".charAt(randomDirection));
        }
        attackCd--;
    }

    public void draw(Screen screen) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(COLOR);
        text.putString(position.getX() * 2, position.getY() + 1, Game.BLOCK);
        text.putString(position.getX() * 2 + 1, position.getY() + 1, Game.BLOCK);
    }

    public void reduceHealth(int damage) {
        health -= damage;
    }

    public void attackPlayer() {
        if (attackCd <= 0) {
            game.getPlayer().decreaseHealth(attack);
            attackCd = ATTACKCD;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

}
