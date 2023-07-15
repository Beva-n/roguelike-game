package model.powerups;

import model.Game;
import model.PowerUp;

public class RangeBlessing extends PowerUp {
    private static final String NAME = "RANGE BOOST";
    private static final String DESCRIPTION = "Slightly increase max projectile range";

    public RangeBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    @Override
    public void apply() {
        game.getPlayer().editRange(5);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
