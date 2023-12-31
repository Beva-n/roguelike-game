package model.manager;

import model.Entity;
import model.Game;

import java.util.ArrayList;
import java.util.List;

// represents a collection of entity managed using a list
public abstract class EntityManager {


    protected Game game;
    private List<Entity> entities;

    //Effects: Constructs an entity manager that keep track of a certain kind of entity using a list
    public EntityManager(Game game) {
        this.game = game;
        entities = new ArrayList<>();
    }

    //Modifies: this
    //Effects: Adds a new entity to the list
    public void spawn(Entity entity) {
        entities.add(entity);
    }

    //Modifies: this
    //Effects: updates all the entities in the list
    public void updateAll() {
        checkCollisionAll();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }


    //Modifies: this, game
    //Effects: Checks collision between entities
    public abstract void checkCollisionAll();

    //Requires: entities contains e
    //Modifies: this
    //Effects: removes entity e from the list
    public void remove(Entity e) {
        entities.remove(e);
    }

    //Modifies: this
    //Effects: clears all the entities from the list
    public void clearEntities() {
        entities = new ArrayList<>();
    }

    public List<Entity> getEntities() {
        return entities;
    }


}
