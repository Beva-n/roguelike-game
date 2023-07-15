package model;

import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityManager {


    protected Game game;
    private List<Entity> entities;

    public EntityManager(Game game) {
        this.game = game;
        entities = new ArrayList<>();
    }

    public void spawn(Entity entity) {
        entities.add(entity);
    }

    public void updateAll() {
        checkCollisionAll();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    public void drawAll(Screen screen) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(screen);
        }
    }

    public abstract void checkCollisionAll();

    public void remove(Entity e) {
        entities.remove(e);
    }

    public List<Entity> getEntities() {
        return entities;
    }

}
