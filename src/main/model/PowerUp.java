package model;

import com.googlecode.lanterna.screen.Screen;

public abstract class PowerUp {

    private final boolean oneUse;
    protected final Game game;
    protected final String name;
    protected final String description;

    public PowerUp(boolean oneUse, String name, String description, Game game) {
        this.oneUse = oneUse;
        this.name = name;
        this.description = description;
        this.game = game;
    }

    public boolean getOneUse() {
        return oneUse;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void apply();

//    public abstract void drawCard(Screen screen);
}
