package model.powerups;

import model.Game;

// Represents a one time power up that boosts the player's defense
public class DefenseBlessing extends PowerUp {

    public static final String NAME = "DEF BOOST";
    public static final String DESCRIPTION = "Increase defense by 3";

    //Effects: Construct a one time use defense up powerup with a name, description
    public DefenseBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    //Modifies: game
    //Effects: Increases the player's defense by 3
    @Override
    public void apply() {
        game.getPlayer().editDefense(3);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
