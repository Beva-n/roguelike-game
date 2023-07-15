package model;

public abstract class Item {

    private final boolean oneUse;
    private boolean used = false;
    protected final Game game;

    public Item(boolean oneUse, Game game) {
        this.oneUse = oneUse;
        this.game = game;
    }

    public boolean getOneUse() {
        return oneUse;
    }

    public abstract void apply();

}
