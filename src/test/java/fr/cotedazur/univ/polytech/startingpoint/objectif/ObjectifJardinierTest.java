package fr.cotedazur.univ.polytech.startingpoint.objectif;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifJardinierTest {
    ObjectifJardinier objJ5_4;
    ObjectifJardinier objJ5_4Bis;
    ObjectifJardinier objJ6_4;
    ObjectifJardinier objJ6_6;

    @BeforeEach
    void setUp() {
        objJ5_4 = new ObjectifJardinier(5, 4);
        objJ5_4Bis = new ObjectifJardinier(5, 4);
        objJ6_4 = new ObjectifJardinier(6, 4);
        objJ6_6 = new ObjectifJardinier(6, 6);
    }

    @Test
    void getNombreBambousAFairePousser() {
        assertEquals(4, objJ5_4.getSchema());
        assertEquals(4, objJ5_4.getSchema());
        assertEquals(6, objJ5_4.getSchema());
    }

    @Test
    void testEquals() {
        assertEquals(objJ5_4, objJ5_4Bis);
        assertNotEquals(objJ5_4, objJ6_4);
        assertNotEquals(objJ6_4, objJ6_6);
    }
}