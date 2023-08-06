package model.powerups;

import model.Game;

// represents a power up that increases the player's range
public class RangeBlessing extends PowerUp {
    public static final String NAME = "RANGE BOOST";
    public static final String DESCRIPTION = "Slightly increase max projectile range";

    //Effects: Construct a one time use range up powerup with a name, description
    public RangeBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    //Modifies: game
    //Effects: Slightly increases the player's max projectile range(speed is unchanged)
    @Override
    public void apply() {
        game.getPlayer().editRange(5);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
