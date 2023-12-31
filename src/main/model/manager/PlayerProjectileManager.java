package model.manager;

import model.Enemy;
import model.Entity;
import model.Game;
import model.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

// Represents the collection of all player projectiles in the game
// has a list of entity that stores the projectiles
public class PlayerProjectileManager extends EntityManager {


    //Effects: Constructs a Projectile Manager that keep track of all the player projectiles in the game
    //         using a list
    public PlayerProjectileManager(Game game) {
        super(game);
    }

    //Modifies: this, game
    //Effects: Checks collision between projectiles and enemies in the game, if collided
    //         the enemy takes projectiles damage and projectile is deleted
    public void checkCollisionAll() {
        List<Entity> enemies = game.getEnemyManager().getEntities();
        List<Projectile> removeList = new ArrayList<>();
        for (int i = 0; i < getEntities().size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                Projectile p = ((Projectile) getEntities().get(i));
                Enemy e = ((Enemy) enemies.get(j));
                if (p.getHitBox().intersects(e.getHitBox())) {
                    e.reduceHealth(p.getDamage());
                    removeList.add(p);
                    if (e.getHealth() <= 0) {
                        game.getEnemyManager().remove(e);
                    }
                }
            }
        }
        for (Projectile p : removeList) {
            remove(p);
        }
    }

}
