package model.powerups;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.screen.Screen;
import model.Game;
import model.PowerUp;

public class DefenseBlessing extends PowerUp {

    private static final String NAME = "DEF BOOST";
    private static final String DESCRIPTION = "Increase defense by 3";

    public DefenseBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    @Override
    public void apply() {
        game.getPlayer().editDefense(3);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
