package model;

import java.util.Random;

public class EnemyManager extends EntityManager {
    private Random rand;

    public EnemyManager(Game game, int count) {
        super(game);
        rand = new Random();
        spawn(count + 2);
    }

    public void spawn(int count) {
        while (count > 0) {
            int y = rand.nextInt(20) + 1;
            int x = rand.nextInt(33) + 5;
            if (game.getMap().checkCollisionWall(new Position(x, y))) {
                continue;
            }
            super.spawn(new Enemy(new Position(x, y), game));
            count--;
        }
    }

    public void checkCollisionAll() {
        for (int i = 0; i < getEntities().size(); i++) {
            Enemy e = ((Enemy) getEntities().get(i));
            if (e.getPosition().equals(game.getPlayer().getPosition())) {
                e.attackPlayer();
            }
        }
    }

}
