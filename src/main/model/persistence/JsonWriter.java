package model.persistence;

import model.Game;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of game to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;
    private final Game game;

    // EFFECTS: constructs writer to write to destination file about game
    public JsonWriter(String destination, Game game) {
        this.destination = destination;
        this.game = game;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of game to file
    public void write() {
        JSONObject json = game.toJson();
        saveToFile(json.toString(TAB));
    }

    //Effects: writes an empty json file
    public void clear() {
        writer.print(new JSONObject());
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
