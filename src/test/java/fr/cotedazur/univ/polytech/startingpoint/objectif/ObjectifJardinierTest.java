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
        objJ1_1 = new ObjectifJardinier(1, 1, "VERT",1);
        objJ2_2 = new ObjectifJardinier(2, 2, "VERT",2);
        objJ2_2_bis = new ObjectifJardinier(2, 2, "VERT",3);
        objJ3_2 = new ObjectifJardinier(3, 2, "VERT",1);
        objJ2_3 = new ObjectifJardinier(2, 3, "VERT",2);
    }

    @Test
    void getNombreBambousAFairePousser() {
        assertEquals(1, objJ1_1.getNombreBambousAFairePousser());
        assertEquals(2, objJ2_2.getNombreBambousAFairePousser());
        assertEquals(3, objJ2_3.getNombreBambousAFairePousser());
        assertNotEquals(3, objJ3_2.getNombreBambousAFairePousser());
    }

    @Test
    void getNombreDeParcelle(){
        assertEquals(1,objJ1_1.getNombreDeParcelle());
        assertEquals(2, objJ2_2.getNombreDeParcelle());
        assertEquals(2, objJ2_3.getNombreDeParcelle());
        assertNotEquals(2, objJ3_2.getNombreDeParcelle());
    }

    @Test
    void testEquals() {
        assertEquals(objJ2_2, objJ2_2_bis);
        assertNotEquals(objJ1_1, objJ2_2_bis);
        assertNotEquals(objJ3_2, objJ2_2);
        assertNotEquals(objJ2_3, objJ2_2);
    }
}