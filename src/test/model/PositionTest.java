package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    Position pos;

    @BeforeEach
    void runBefore() {
        pos = new Position(1, 1);
    }

    @Test
    void testEdit() {
        pos.editPosX(5);
        assertEquals(6, pos.getX());
        pos.editPosX(-1);
        assertEquals(5, pos.getX());
        pos.editPosY(5);
        assertEquals(6, pos.getY());
        pos.editPosY(-1);
        assertEquals(5, pos.getY());
    }

    @Test
    void testEquals() {
        assertEquals(pos, new Position(1, 1));
        assertNotEquals(pos, new Position(1, 0));
    }
}
