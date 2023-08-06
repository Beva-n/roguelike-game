package ui;

import java.awt.geom.Line2D;

public class Vector {

    private double limit;
    private int antiAccerlerationTime;
    private double velocityX;
    private double velocityY;
    private double accelerationX;
    private double accelerationY;

    //Effects: contstructs a new vector with a velocity and no acceleration
    //         automatically scales to the limit if it is over
    public Vector(double x, double y, int limit) {
        this.velocityX = x;
        this.velocityY = y;
        this.limit = limit;
        this.antiAccerlerationTime = 100000;
        accelerationX = 0;
        accelerationY = 0;
        limit(limit);
    }

    public Vector(double x, double y, int time, int limit) {
        this.velocityX = x;
        this.velocityY = y;
        this.limit = limit;
        this.antiAccerlerationTime = time;
        this.accelerationX = 0;
        this.accelerationY = 0;
        limit(limit);
    }

    public Vector(double x, double y, double accelerationX, double accelerationY, int limit) {
        this.velocityX = x;
        this.velocityY = y;
        this.limit = limit;
        this.antiAccerlerationTime = 100000;
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
        limit(limit);
    }

    // later me issue to find out if need those
//    public void add(Vector v) {
//        velocityX = velocityX + v.velocityX;
//        velocityY = velocityY + v.velocityY;
//    }
//
//    public static Vector sub(Vector v1, Vector v2) {
//        return new Vector(v1.velocityX - v2.velocityX, v1.velocityY - v2.velocityY);
//    }

    public void update() {
        if (antiAccerlerationTime == 0) {
            limit = 1;
            limit(1);
        }
        if (antiAccerlerationTime > 0) {
            velocityX += accelerationX;
            velocityY += accelerationY;
            limit(limit);
        }
        antiAccerlerationTime--;
    }

    public void normalize() {
        double len = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        velocityX /= len;
        velocityY /= len;
    }

    public void mult(double factor) {
        velocityX *= factor;
        velocityY *= factor;
    }


    // might be useful
    public void limit(double max) {
        double len = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        if (len > max) {
            normalize();
            mult(max);
        }
    }

    public int getVelocityX() {
        return  (int) Math.round(velocityX);
    }

    public int getVelocityY() {
        return (int) Math.round(velocityY);
    }

    public boolean manualUpdate() {
        return antiAccerlerationTime <= 0;
    }
}
