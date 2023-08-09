package model.projectiles;

import model.Game;
import model.Position;
import ui.Vector;

// Represents a projectile with preset modified colour and behaviour
public class RedRingProjectile extends Projectile {

    //Effects: Constructs a modified projectile with an initial velocity of 0 and a gradual acceleration
    //         the projectile have a smaller size compared to a normal projectile
    public RedRingProjectile(Position position, double dx, double dy,
                             int damage, int lifetime, Game game) {
        super(position, damage, lifetime, game);
        setVector(new Vector(0, 0, dx / 100, dy / 100, 10));
        this.position = position;
        width = 10;
        height = 10;
    }
}
