package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
    void testGetHitBox() {
        assertEquals(new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), entity.getWidth(), entity.getHeight()),
                entity.getHitBox());
    }
}
