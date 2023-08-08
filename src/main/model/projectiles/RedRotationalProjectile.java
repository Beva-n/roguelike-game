package model.projectiles;

import model.Game;
import model.Position;
import ui.Vector;

// Represents a projectile with preset modified colour and behaviour
public class RedRotationalProjectile extends Projectile {

    //Effects: Constructs a modified projectile with that greatly slows down after a burst of speed
    //         The projectile is slightly larger than a default projectile
    public RedRotationalProjectile(Position position, double dx, double dy,
                                   int damage, int lifetime, int limit, Game game) {
        super(position, damage, lifetime, game);
        setVector(new Vector(dx, dy, 25, limit));
        this.position = position;
        width = 18;
        height = 18;
    }

      // not winnable rotation
//    @Override
//    public void move() {
//        if (!getVector().manualUpdate()) {
//            super.move();
//        } else {
//            double centerX = position.getX();
//            double centerY = position.getY();
//            double point2x = getX1() + getWidth() / 2;
//            double point2y = getY1() + getWidth() / 2;
//            angle -= 10;
//            double x = Math.toRadians(angle);
//
//            double newX = centerX + (point2x - centerX) * Math.cos(x) - (point2y - centerY) * Math.sin(x);
//            double newY = centerY + (point2x - centerX) * Math.sin(x) + (point2y - centerY) * Math.cos(x);
////            setX(centerX + (point2x - centerX) * Math.cos(x) - (point2y - centerY) * Math.sin(x));
////            setY(centerY + (point2x - centerX) * Math.sin(x) + (point2y - centerY) * Math.cos(x));
//
//            position.setPosX((int) Math.round(newX));
//            position.setPosY((int)Math.round(newY));
//            if (game.getMap().checkCollisionWall(this)) {
//                touchWallAction();
//            }
//        }
//    }
}
