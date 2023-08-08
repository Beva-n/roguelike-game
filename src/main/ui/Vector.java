package ui;

import java.awt.geom.Line2D;

// Represents a vector class with both velocity and acceleration, comes with a speed limit
public class Vector {

    private double limit;
    private int antiAccerlerationTime;
    private double velocityX;
    private double velocityY;
    private double accelerationX;
    private double accelerationY;

    //Effects: constructs a new vector with a velocity and no acceleration and no stop time
    //         automatically scales to the limit if it is over limit
    public Vector(double x, double y, int limit) {
        this.velocityX = x;
        this.velocityY = y;
        this.limit = limit;
        this.antiAccerlerationTime = 100000;
        accelerationX = 0;
        accelerationY = 0;
        limit(limit);
    }

    //Effects: constructs a vector with velocity and a stop time but with no acceleration
    //         automatically scales to the limit if it is over limit
    public Vector(double x, double y, int time, int limit) {
        this.velocityX = x;
        this.velocityY = y;
        this.limit = limit;
        this.antiAccerlerationTime = time;
        this.accelerationX = 0;
        this.accelerationY = 0;
        limit(limit);
    }

    //Effects: constructs a vector with velocity and acceleration but no stop time
    //         automatically scales to the limit if it is over limit
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

    //Modifies: this
    //Effects: if stop time equals to 0, scales to vector speed to 1 and limits it to 1
    //         if stop time greater than 0, increases velocity by acceleration and scales it if it is over limit
    //         decreases stop time by 1
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

    //Modifies: this
    //Effects: normalizes the vector, 1 = max, 0 = min
    public void normalize() {
        double len = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        velocityX /= len;
        velocityY /= len;
    }

    //Modifies: this
    //Effects: Multiplies the x and y component of the vector by a factor
    public void mult(double factor) {
        velocityX *= factor;
        velocityY *= factor;
    }


    // Modifies: this
    // Effects: if the vector is over max, normalizes the vector and multiplies it by max
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
}
