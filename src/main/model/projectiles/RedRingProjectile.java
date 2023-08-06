package model.projectiles;

import model.Game;
import model.Position;
import ui.Vector;

public class RedRingProjectile extends Projectile {

    public RedRingProjectile(Position position, double dx, double dy,
                              int damage, int lifetime, Game game) {
        super(position, damage, lifetime, game);
        setVector(new Vector(0, 0, dx / 100, dy / 100, 10));
        this.position = position;
        width = 10;
        height = 10;
    }
}
