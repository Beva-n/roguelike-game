package model;


// represents a position with a x and y coordinate
public class Position {

    private int posX;
    private int posY;

    //Effects: Constructs a position with x and y
    public Position(int x, int y) {
        posX = x;
        posY = y;
    }

    //Modifies: this
    //Effects: increases the x value of the position by x
    public void editPosX(int x) {
        posX += x;
    }

    //Modifies: this
    //Effects: increases the y value of the position by y
    public void editPosY(int y) {
        posY += y;
    }

    public void setPosX(int x) {
        posX = x;
    }

    public void setPosY(int y) {
        posY = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position node = (Position) o;
        return posX == node.posX && posY == node.posY;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }
}
