package model.projectiles;

import model.Game;
import model.Position;
import ui.Vector;

import java.awt.*;

public class BlueRingProjectile extends Projectile {

    public BlueRingProjectile(Position position, double dx, double dy,
                             int damage, int lifetime, Game game) {
        super(position, damage, lifetime, game);
        setVector(new Vector(0, 0, dx / 100, dy / 100, 10));
        this.position = position;
        width = 10;
        height = 10;
    }

    @Override
    public Color getColor() {
        return new Color(50, 100, 200);
    }
}
