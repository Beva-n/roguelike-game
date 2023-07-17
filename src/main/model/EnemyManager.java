package model;

import java.util.Random;

public class EnemyManager extends EntityManager {
    private Random rand;

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
        while (count > 0) {
            int y = rand.nextInt(20) + 1;
            int x = rand.nextInt(33) + 5;
            Position validPosition = makeValidPosition(x, y);
            super.spawn(new Enemy(validPosition, game));
            count--;
        }
    }

    //Modifies: game
    //Effects: checks collision between enemies and the player, the there is collision,
    //         the enemies attacks the player
    public void checkCollisionAll() {
        for (int i = 0; i < getEntities().size(); i++) {
            Enemy e = ((Enemy) getEntities().get(i));
            if (e.getPosition().equals(game.getPlayer().getPosition())) {
                e.attackPlayer();
            }
        }
    }

    public Position makeValidPosition(int x, int y) {
        if (game.getMap().checkCollisionWall(new Position(x, y))) {
            return new Position(38, 10);
        } else {
            return new Position(x, y);
        }
    }


}
