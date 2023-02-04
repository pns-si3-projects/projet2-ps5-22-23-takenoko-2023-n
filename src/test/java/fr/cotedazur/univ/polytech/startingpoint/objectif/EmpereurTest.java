package fr.cotedazur.univ.polytech.startingpoint.objectif;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpereurTest {
    Empereur empereur;

    @BeforeEach
    void setUp() {
        empereur = new Empereur();
    }


    @Test
    void getNombrePoints() {
        assertEquals(2, empereur.getNombrePoints());
    }


    @Test
    void testToString() {
        assertEquals("Empereur de 2 points", empereur.toString());
    }
}