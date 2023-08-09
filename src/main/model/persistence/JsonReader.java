package model.persistence;

import model.Game;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads game from JSON data stored in file
public class JsonReader {
    private String source;

    private Game game;

    // EFFECTS: constructs reader to read from source file and load into game
    public JsonReader(String source, Game game) {
        this.source = source;
        this.game = game;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    // throws JSONException if reading from an empty file
    public void read() throws IOException, JSONException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game from JSON object and load it into current game
    private void parseGame(JSONObject jsonObject) {
        game.setFloorLevel(jsonObject.getInt("level"));
        game.getPlayer().scaleWithLevel(jsonObject.getInt("level"));
        JSONArray jsonArray = jsonObject.getJSONArray("buffs");
        for (Object o : jsonArray) {
            game.getPowerUpManager().addPowerUp(game.getPowerUp(((String) o)));
        }
        game.getEnemyManager().clearEntities();
    }
}
