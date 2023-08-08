package model.projectiles;

import model.Game;
import model.Position;
import ui.Vector;

import java.awt.*;

// Represents a projectile with preset modified colour and behaviour
public class BlueRotationalProjectile extends Projectile {

    //Effects: Constructs a modified projectile with that greatly slows down after a burst of speed
    //         The projectile is slightly larger than a default projectile
    public BlueRotationalProjectile(Position position,
                                    double dx, double dy, int damage, int lifetime, int limit, Game game) {
        super(position, damage, lifetime, game);
        setVector(new Vector(dx, dy, 25, limit));
        width = 18;
        height = 18;
    }

    //Effects: returns the color of the projectile, which in this case is blue
    @Override
    public Color getColor() {
        return new Color(50, 100, 200);
    }
}
