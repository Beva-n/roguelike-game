package model.persistence;

import model.Game;
import model.powerups.AttackBlessing;
import model.powerups.DefenseBlessing;
import model.powerups.HealingBlessing;
import model.powerups.LifeBlessing;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json", new Game());
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteGame() {
        try {
            Game game = new Game();
            game.setFloorLevel(3);
            game.getPowerUpManager().addPowerUp(new AttackBlessing(game));
            game.getPowerUpManager().addPowerUp(new DefenseBlessing(game));
            game.getPowerUpManager().addPowerUp(new HealingBlessing(game));
            game.getPowerUpManager().addPowerUp(new LifeBlessing(game));
            JsonWriter writer = new JsonWriter("./data/testWriter.json", game);
            writer.open();
            writer.write();
            writer.close();

            Game game2 = new Game();
            JsonReader reader = new JsonReader("./data/testWriter.json", game2);
            reader.read();
            assertEquals(3, game2.getFloorLevel());
            assertTrue(game2.roomCleared());
            assertEquals(4, game2.getPowerUpManager().getPowerUps().size());
            assertEquals(4, game2.getPowerUpManager().getLog().size());
        } catch (IOException e) {
            fail("No exception expected");
        }
    }
}
