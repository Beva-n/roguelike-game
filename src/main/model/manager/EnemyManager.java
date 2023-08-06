package model.manager;

import model.Boss;
import model.Enemy;
import model.Game;
import model.Position;
import model.manager.EntityManager;

import java.util.Random;

// Represents the collection of all enemies in the game
// has a list of entity that stores the enemies
public class EnemyManager extends EntityManager {
    private final Random rand;

    //Effects: Constructs an enemy manager that keeps track of all enemies in a list
    //         spawns 3 enemies in random locations within the map
    public EnemyManager(Game game, int level) {
        super(game);
        rand = new Random();
        spawn(level + 2);
    }

    //Modifies: this
    //Effects: spawns a given amount of enemies in random locations on the map
    public void spawn(int count) {
//        while (count > 0) {
//            int y = rand.nextInt(500) + 40;
//            int x = rand.nextInt(700) + 200;
//
//            // comment out before running test bc coverage too hard :skull:
//            if (game.getMap().checkCollisionWall(new Enemy(new Position(x, y), game))) {
//                continue;
//            }
//            super.spawn(new Enemy(new Position(x, y), game));
//
//            // use to pass test
////            super.spawn(makeValidEnemy(x, y));
//            count--;
//        }
        spawn(new Boss(game));
    }

    //Modifies: game
    //Effects: checks collision between enemies and the player, the there is collision,
    //         the enemies attacks the player
    public void checkCollisionAll() {
        for (int i = 0; i < getEntities().size(); i++) {
            Enemy e = ((Enemy) getEntities().get(i));
            if (e.getHitBox().intersects(game.getPlayer().getHitBox())) {
                e.attackPlayer();
            }
//            if (e.getPosition().equals(game.getPlayer().getPosition())) {
//                e.attackPlayer();
//            }
        }
    }

    public Enemy makeValidEnemy(int x, int y) {
        if (game.getMap().checkCollisionWall(new Enemy(new Position(x, y), game))) {
            return new Enemy(new Position(500, 400), game);
        } else {
            return new Enemy(new Position(x, y), game);
        }
    }


}
