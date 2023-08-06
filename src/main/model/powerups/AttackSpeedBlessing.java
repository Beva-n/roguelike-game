package model.powerups;

import model.Game;

public class AttackSpeedBlessing extends PowerUp {

    public static final String NAME = "ATK SPD BOOST";
    public static final String DESCRIPTION = "Increase attack speed by a little bit";

    //Effects: Construct a one time use attack up powerup with a name, description
    public AttackSpeedBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    //Modifies: game
    //Effects: Increases the player's attack by 5
    @Override
    public void apply() {
        game.getPlayer().editAttackSpeed(2);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
