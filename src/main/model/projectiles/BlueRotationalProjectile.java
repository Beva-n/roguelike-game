package model.projectiles;

import model.Game;
import model.Position;
import ui.Vector;

import java.awt.*;

public class BlueRotationalProjectile extends Projectile {
    public BlueRotationalProjectile(Position position, double dx, double dy, int damage, int lifetime, int limit, Game game) {
        super(position, damage, lifetime, game);
        setVector(new Vector(dx, dy, 25, limit));
        width = 18;
        height = 18;
    }

    @Override
    public Color getColor() {
        return new Color(50, 100, 200);
    }
}
