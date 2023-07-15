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

    public Projectile(Position position, char direction, int damage, Game game) {
        super(position, MOVECOOLDOWN, game);
        this.direction = direction;
        this.damage = damage;
        lifeTime = game.getPlayer().getRange();
    }

    public void update() {
        if (lifeTime == 0) {
            game.getProjectileManager().remove(this);
        }
        move(direction);
        moveCooldown--;
        lifeTime--;
    }

    public void draw(Screen screen) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(projectileColor);
        text.putString(position.getX() * 2, position.getY() + 1, Game.BALL);
    }

    @Override
    public void touchWallAction() {
        game.getProjectileManager().remove(this);
    }

    public int getDamage() {
        return damage;
    }
}
