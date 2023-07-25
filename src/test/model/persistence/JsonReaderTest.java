package model.persistence;

import model.Game;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReadNonExistentFile() {
        JsonReader jsonReader = new JsonReader("./data/noSuchFile.json", new Game());
        try {
            jsonReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReadEmptyFile() {
        JsonReader jsonReader = new JsonReader("./data/testEmptyReader.json", new Game());
        try {
            jsonReader.read();
            fail("JSONException expected");
        } catch (IOException e) {
            fail("JSONException expected");
        } catch (JSONException e) {
            // pass
        }
    }

    @Test
    void testReadGame() {
        Game game = new Game();
        JsonReader jsonReader = new JsonReader("./data/testReader.json", game);
        try {
            jsonReader.read();
            assertEquals(4, game.getFloorLevel());
            assertTrue(game.roomCleared());
            assertEquals(3, game.getPowerUpManager().getPowerUps().size());
            assertEquals(3, game.getPowerUpManager().getLog().size());
        } catch (IOException e) {
            fail("No Exception expected");
        } catch (JSONException e) {
            fail("No Exception expected");
        }
    }
}
