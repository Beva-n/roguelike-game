package model;

import com.googlecode.lanterna.TextColor;
import ui.TerminalGame;

import java.util.Random;

public class Enemy extends Entity {

    private static final int MOVECOOLDOWN = TerminalGame.FPS;
    public static final TextColor.RGB ENEMYCOLOR = new TextColor.RGB(255, 0, 0);
    private static final int ATTACKCD = 30;
    private final Random rand = new Random();
    private int health = 30;
    private int defense = 0;
    private int attack = 40;
    private int attackCd = 0;

    //Effects: Constructs an enemy with an initial start position, health of 30, defense of 0
    //         attack of 40, move cooldown of 1s, and attack cooldown of 1s
    public Enemy(Position position, Game game) {
        super(position, MOVECOOLDOWN, game);
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
        if (moveCooldown > 0) {
            moveCooldown--;
        } else {
            int randomDirection = rand.nextInt(4);
            move("wasd".charAt(randomDirection));
        }
        attackCd--;
    }

    //Requires: damage > 0
    //Modifies: this
    //Effects: lowers the enemy's health by damage
    public void reduceHealth(int damage) {
        health -= damage;
    }

    //Modifies: game
    //Effects: attacks the player and resets its own attack cooldown to max
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

    public int getAttackcd() {
        return attackCd;
    }

}
