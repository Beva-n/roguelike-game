package model;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProjectileManager extends EntityManager {

    public ProjectileManager(Game game) {
        super(game);
    }

    public void checkCollisionAll() {
        List<Entity> enemies = game.getEnemyManager().getEntities();
        List<Projectile> removeList = new ArrayList<>();
        for (int i = 0; i < getEntities().size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                Projectile p = ((Projectile) getEntities().get(i));
                Enemy e = ((Enemy) enemies.get(j));
                if (p.getPosition().equals(e.getPosition())) {
                    e.reduceHealth(p.getDamage());
                    removeList.add(p);
                    if (e.getHealth() <= 0) {
                        game.getProjectileManager().remove(e);
                    }
                }
            }
        }
        for (Projectile p : removeList) {
            remove(p);
        }
    }

}
