package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifJardinierTest {
    ObjectifJardinier objJ1_1;
    ObjectifJardinier objJ2_2;
    ObjectifJardinier objJ2_2_bis;
    ObjectifJardinier objJ3_2;
    ObjectifJardinier objJ2_3;

    @BeforeEach
    void setUp() {
        objJ1_1 = new ObjectifJardinier(1, 1);
        objJ2_2 = new ObjectifJardinier(2, 2);
        objJ2_2_bis = new ObjectifJardinier(2, 2);
        objJ3_2 = new ObjectifJardinier(3, 2);
        objJ2_3 = new ObjectifJardinier(2, 3);
    }

    @Test
    void getNombreBambousAFairePousser() {
        assertEquals(1, objJ1_1.getNombreBambousAFairePousser());
        assertEquals(2, objJ2_2.getNombreBambousAFairePousser());
        assertEquals(3, objJ2_3.getNombreBambousAFairePousser());
        assertNotEquals(3, objJ3_2.getNombreBambousAFairePousser());
    }

    @Test
    void getNombreBambousRestant(){
        assertEquals(1,objJ1_1.getNombreBambousRestant());
        assertEquals(2, objJ2_2.getNombreBambousRestant());
        assertEquals(3, objJ2_3.getNombreBambousRestant());
        assertNotEquals(3, objJ3_2.getNombreBambousRestant());
    }

    @Test
    void testEquals() {
        assertEquals(objJ2_2, objJ2_2_bis);
        assertNotEquals(objJ1_1, objJ2_2_bis);
        assertNotEquals(objJ3_2, objJ2_2);
        assertNotEquals(objJ2_3, objJ2_2);
    }
}