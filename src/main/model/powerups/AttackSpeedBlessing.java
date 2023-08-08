package model.powerups;

import model.Game;


// Represents a one time power up that increases player's attack speed
public class AttackSpeedBlessing extends PowerUp {

    public static final String NAME = "ATK SPD BOOST";
    public static final String DESCRIPTION = "Increase attack speed by a little bit";

    //Effects: Construct a one time use attack up powerup with a name, description
    public AttackSpeedBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    //Modifies: game
    //Effects: Increases the players speed by a little bit
    @Override
    public void apply() {
        game.getPlayer().editAttackSpeed(2);
    }
}
