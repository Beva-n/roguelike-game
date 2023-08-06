package model.powerups;

import model.Game;

// Represents a power up that heals the player
public class HealingBlessing extends PowerUp {

    public static final String NAME = "HEAL BLESSING";
    public static final String DESCRIPTION = "Heal 40 hp";

    //Effects: Construct a one time use healing powerup with a name, description
    public HealingBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    //Modifies: game
    //Effects: Heals the player by 20 hp
    @Override
    public void apply() {
        game.getPlayer().heal(40);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
