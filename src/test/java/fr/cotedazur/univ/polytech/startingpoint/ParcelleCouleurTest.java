package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ParcelleCouleurTest {
    ParcelleCouleur pC1_1;
    ParcelleCouleur pC2_1;
    ParcelleCouleur pC1_1bis;
    Position p1_1;
    Position p2_1;

    @BeforeEach
    void setUp() {
        p1_1 = new Position(1,1);
        p2_1 = new Position(2,1);
        pC1_1 = new ParcelleCouleur(p1_1);
        pC1_1bis = new ParcelleCouleur(p1_1);
        pC2_1 = new ParcelleCouleur(p2_1);
    }

    @Test
    void getPosition() {
        assertEquals(pC1_1.getPosition(),p1_1);
        assertEquals(pC2_1.getPosition(),p2_1);
        assertNotEquals(pC1_1bis.getPosition(),p2_1);
    }

    @Test
    void testEquals() {
        assertEquals(pC1_1,pC1_1bis);
        assertNotEquals(pC1_1,pC2_1);
        assertNotEquals(pC1_1bis,pC2_1);
    }
}