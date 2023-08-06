package model.powerups;

import model.Game;

// Represents a one time power up that increases player's attack
public class AttackBlessing extends PowerUp {

    public static final String NAME = "ATK BOOST";
    public static final String DESCRIPTION = "Increase attack by 5";

    //Effects: Construct a one time use attack up powerup with a name, description
    public AttackBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    //Modifies: game
    //Effects: Increases the player's attack by 5
    @Override
    public void apply() {
        game.getPlayer().editAttack(5);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
