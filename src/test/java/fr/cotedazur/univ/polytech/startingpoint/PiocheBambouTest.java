package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiocheBambouTest {
    PiocheBambou piocheBambou;

    @BeforeEach
    void setUp() {
        piocheBambou = new PiocheBambou();
    }

    @Test
    void getNombreBambousRestants() {
        assertEquals(90, piocheBambou.getNombreBambousRestants());
        piocheBambou.pioche();
        piocheBambou.pioche();
        assertEquals(88, piocheBambou.getNombreBambousRestants());
    }

    @Test
    void isEmpty() {
        assertFalse(piocheBambou.isEmpty());
    }

    @Test
    void pioche() {
        assertEquals(new SectionBambou().getClass(), piocheBambou.pioche().getClass());
        assertEquals(new SectionBambou().getClass(), piocheBambou.pioche().getClass());
    }
}