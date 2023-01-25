package fr.cotedazur.univ.polytech.startingpoint.jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PositionTest {
    Position position0_0;
    Position position0_0Bis;
    Position position1_0;


    @BeforeEach
    void setUp() {
        position0_0 = new Position();
        position0_0Bis = new Position(0, 0);
        position1_0 = new Position(1, 0);
    }


    @Test
    void getX() {
        assertEquals(0, position0_0.getX());
        assertEquals(0, position0_0Bis.getX());
        assertEquals(1, position1_0.getX());
        assertNotEquals(0, position1_0.getX());
    }

    @Test
    void getY() {
        assertEquals(0, position0_0.getY());
        assertEquals(0, position0_0Bis.getY());
        assertEquals(0, position1_0.getY());
        assertNotEquals(1, position1_0.getY());
    }

    
    @Test
    void testToString() {
        assertEquals("(0,0)", position0_0.toString());
        assertEquals("(1,0)", position1_0.toString());
    }

    @Test
    void testEquals() {
        assertEquals(position0_0, position0_0Bis);
        assertNotEquals(position0_0, position1_0);
    }
}