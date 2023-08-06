package ui;

import model.Game;
import model.Projectile;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler extends MouseAdapter implements MouseListener {
    private Game game;

    public MouseHandler(Game game, GamePanel gamePanel) {
        gamePanel.addMouseMotionListener(this);
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mouse = e.getPoint();
        int x = mouse.x;
        int y = mouse.y;

        if (game.getGameState() && game.getPlayer().getShootCd() <= 0) {
            int x2 = game.getPlayer().getPosition().getX() + game.getPlayer().getWidth() / 2;
            int y2 = game.getPlayer().getPosition().getY() + + game.getPlayer().getHeight() / 2;
            game.getPlayerProjectileManager().spawn(new Projectile(game.getPlayer().getCenter(),
                    new Vector(x - x2, y - y2, 10), game.getPlayer().getAttack(),
                    game.getPlayer().getRange(), game));
            game.getPlayer().resetShootCd();
        }
    }


}
