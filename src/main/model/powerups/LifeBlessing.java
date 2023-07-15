package model.powerups;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.screen.Screen;
import model.Game;
import model.PowerUp;

public class LifeBlessing extends PowerUp {
    private static final String NAME = "LIFE BLESSING";
    private static final String DESCRIPTION = "increase max hp by 10 and heal 10 hp";

    public LifeBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    @Override
    public void apply() {
        game.getPlayer().editHealth(10);
        game.getPlayer().heal(10);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
