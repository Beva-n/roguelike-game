package model.powerups;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.screen.Screen;
import model.Game;
import model.PowerUp;

public class AttackBlessing extends PowerUp {

    private static final String NAME = "ATK BOOST";
    private static final String DESCRIPTION = "Increase attack by 5";

    public AttackBlessing(Game g) {
        super(true, NAME, DESCRIPTION, g);
    }

    @Override
    public void apply() {
        game.getPlayer().editAttack(5);
    }

//    @Override
//    public void drawCard(Screen screen) {
//    }
}
