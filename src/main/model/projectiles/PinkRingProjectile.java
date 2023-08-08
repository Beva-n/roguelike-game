package model.projectiles;

import model.Game;
import model.Position;
import ui.Vector;

import java.awt.*;

// Represents a projectile with preset modified colour and behaviour
public class PinkRingProjectile extends Projectile {

    //Effects: Constructs a modified projectile with an initial velocity of 0 and a slow maximum speed
    //         The projectile is larger than the default projectile
    public PinkRingProjectile(Position position, double dx, double dy,
                                   int damage, int lifetime, Game game) {
        super(position, damage, lifetime, game);
        setVector(new Vector(0, 0, dx, dy, 2));
        this.position = position;
        width = 18;
        height = 18;
    }

    //Effects: returns the color of the projectile, which in this case is pink
    @Override
    public Color getColor() {
        return new Color(255,20,147);
    }
}
