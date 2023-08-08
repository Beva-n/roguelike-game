package model.manager;

import model.Game;
import model.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

// Represents the collection of all enemy projectiles in the game
// has a list of entity that stores the projectiles
public class EnemyProjectileManager extends EntityManager {

    //Effects: Constructs a Projectile Manager that keep track of all the enemy projectiles in the game
    //         using a list
    public EnemyProjectileManager(Game game) {
        super(game);
    }

    //Modifies: this, game
    //Effects: Checks collision between projectiles and the player, if collided
    //         the player takes projectiles damage and projectile is deleted
    @Override
    public void checkCollisionAll() {
        List<Projectile> removeList = new ArrayList<>();
        for (int i = 0; i < getEntities().size(); i++) {
            Projectile p = (Projectile) getEntities().get(i);
            if (p.getHitBox().intersects(game.getPlayer().getHitBox())) {
                game.getPlayer().decreaseHealth(p.getDamage());
                removeList.add(p);
            }
        }
        for (Projectile p : removeList) {
            remove(p);
        }
    }
}
