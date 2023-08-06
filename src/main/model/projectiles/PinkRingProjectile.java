package model.projectiles;

import model.Game;
import model.Position;
import ui.Vector;

import java.awt.*;

public class PinkRingProjectile extends Projectile {
    public PinkRingProjectile(Position position, double dx, double dy,
                                   int damage, int lifetime, Game game) {
        super(position, damage, lifetime, game);
        setVector(new Vector(0, 0, dx, dy, 2));
        this.position = position;
        width = 18;
        height = 18;
    }

    @Override
    public Color getColor() {
        return new Color(255,20,147);
    }
}
