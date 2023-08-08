package model.powerups;

import model.Game;

// Represents a one time power up that increases player's speed
public class SpeedBlessing extends PowerUp {

    public static final String NAME = "SPEED BOOST";
    public static final String DESCRIPTION = "Increase speed by 1";

    //Effects: Construct a one time use attack up powerup with a name, description
    public SpeedBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    //Modifies: game
    //Effects: Increases the player's speed by 1
    @Override
    public void apply() {
        game.getPlayer().editSpeed(1);
    }
}
