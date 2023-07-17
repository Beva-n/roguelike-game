package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {

    Entity entity;
    Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
        entity = new Enemy(new Position(1,1), game);
    }

    @Test
    void testMoveRight() {
        entity.moveRight();
        assertEquals(new Position(2, 1), entity.getPosition());

        entity.setMovecooldown(0);
        entity.setPosition(new Position(38, 1));
        entity.moveRight();
        assertEquals(new Position(38, 1), entity.getPosition());
    }

    @Test
    void testMoveLeft() {
        entity.moveLeft();
        assertEquals(new Position(1, 1), entity.getPosition());

        entity.setPosition(new Position(3, 1));
        entity.moveLeft();
        assertEquals(new Position(2, 1), entity.getPosition());
    }

    @Test
    void testMoveUp() {
        entity.moveUp();
        assertEquals(new Position(1, 1), entity.getPosition());

        entity.setPosition(new Position(1, 3));
        entity.moveUp();
        assertEquals(new Position(1, 2), entity.getPosition());
    }

    @Test
    void testMoveDown() {
        entity.moveDown();
        assertEquals(new Position(1, 2), entity.getPosition());

        entity.setPosition(new Position(1, 21));
        entity.moveDown();
        assertEquals(new Position(1, 21), entity.getPosition());
    }

    @Test
    void testMove() {
        entity.setPosition(new Position(15, 15));
        entity.move('w');
        assertEquals(new Position(15, 14), entity.getPosition());
        assertEquals(30, entity.getMoveCooldown());
        entity.setMovecooldown(0);
        entity.move('s');
        assertEquals(new Position(15, 15), entity.getPosition());
        entity.setMovecooldown(0);
        entity.move('a');
        assertEquals(new Position(14, 15), entity.getPosition());
        entity.setMovecooldown(0);
        entity.move('d');
        assertEquals(new Position(15, 15), entity.getPosition());

        entity.move('w');
        assertEquals(new Position(15, 15), entity.getPosition());
    }
}
