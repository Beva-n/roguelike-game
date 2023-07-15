package model;

public class Position {

    private int posX;
    private int posY;

    public Position(int x, int y) {
        posX = x;
        posY = y;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void editPosX(int x) {
        posX += x;
    }

    public void editPosY(int y) {
        posY += y;
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
}
