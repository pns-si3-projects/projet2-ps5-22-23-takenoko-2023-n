package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IrrigationTest {
    Position position11;
    Position position20;
    Irrigation irrigation1120;
    Irrigation irrigation11m11;
    Irrigation irrigation1120Bis;

    @BeforeEach
    void setUp() {
        position11 = new Position(1, 1);
        position20 = new Position(2, 0);
        irrigation1120 = new Irrigation();
        irrigation11m11 = new Irrigation();
        irrigation1120Bis = new Irrigation();
    }
    @Test
    void addPosition() {
        assertTrue(irrigation1120.addPosition(position11, position20));
        assertFalse(irrigation1120.addPosition(position11, position20));

        assertFalse(irrigation11m11.addPosition(position11, new Position()));
        assertTrue(irrigation11m11.getPositions().isEmpty());
    }

    @Test
    void testEquals() {
        assertEquals(irrigation1120, irrigation1120);
        assertEquals(irrigation1120, irrigation1120Bis);
        assertEquals(irrigation1120, irrigation11m11);

        irrigation1120.addPosition(position11, position20);
        assertEquals(irrigation1120, irrigation11m11);

        irrigation11m11.addPosition(position11, new Position(1, -1));
        assertNotEquals(irrigation1120, irrigation11m11);
    }

    @Test
    void testToString() {
        assertEquals("Irrigation", irrigation1120.toString());

        Position position11 = new Position(1, 1);
        Position position20 = new Position(2, 0);
        irrigation1120.addPosition(position11, position20);
        assertEquals("Irrigation entre les parcelles en " + position11 + " et en " + position20, irrigation1120.toString());
    }
}