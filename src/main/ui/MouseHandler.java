package ui;

import model.Game;
import model.projectiles.Projectile;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Represents the mouse handler that handles all the mouse inputs for the game
public class MouseHandler extends MouseAdapter implements MouseListener {
    private final Game game;

    //Constructs a mouse handler with game and gamePanel
    public MouseHandler(Game game, GamePanel gamePanel) {
        gamePanel.addMouseMotionListener(this);
        this.game = game;
    }

    //Modifies: game
    //Effects: spawns a new friendly projectile flying towards the cursor location if mouse is clicked
    //         will only take place if game is not paused and player shooting is not on cool down
    @Override
    public void mouseClicked(MouseEvent e) {
        Point mouse = e.getPoint();
        int x = mouse.x;
        int y = mouse.y;

        if (game.getGameState() && game.getPlayer().getShootCd() <= 0) {
            int x2 = game.getPlayer().getPosition().getX() + game.getPlayer().getWidth() / 2;
            int y2 = game.getPlayer().getPosition().getY() + +game.getPlayer().getHeight() / 2;
            game.getPlayerProjectileManager().spawn(new Projectile(game.getPlayer().getCenter(),
                    new Vector(x - x2, y - y2, 10), game.getPlayer().getAttack(),
                    game.getPlayer().getRange(), game));
            game.getPlayer().resetShootCd();
        }
    }


}
