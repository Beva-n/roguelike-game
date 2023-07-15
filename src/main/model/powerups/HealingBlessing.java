package model.powerups;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.screen.Screen;
import model.Game;
import model.PowerUp;

public class HealingBlessing extends PowerUp {

    private static final String NAME = "HEAL BLESSING";
    private static final String DESCRIPTION = "Heal 20 hp";

    public HealingBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    @Override
    public void apply() {
        game.getPlayer().heal(20);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
