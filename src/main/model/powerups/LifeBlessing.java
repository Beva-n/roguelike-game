package model.powerups;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.screen.Screen;
import model.Game;
import model.PowerUp;

public class LifeBlessing extends PowerUp {
    public static final String NAME = "LIFE BLESSING";
    public static final String DESCRIPTION = "increase max hp by 10 and heal 10 hp";


    //Effects: Construct a one time use hp powerup with a name, description
    public LifeBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }


    //Modifies: game
    //Effects: Increases the player's max hp by 10 and heals by 10
    @Override
    public void apply() {
        game.getPlayer().editHealth(10);
        game.getPlayer().heal(10);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
