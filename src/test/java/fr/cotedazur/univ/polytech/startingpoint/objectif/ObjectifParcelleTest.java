package fr.cotedazur.univ.polytech.startingpoint.objectif;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ObjectifParcelleTest {
    ObjectifParcelle objP2_3;
    ObjectifParcelle objP2_3Bis;
    ObjectifParcelle objP3_4;
    ObjectifParcelle objP4_3;


    @BeforeEach
    void setUp() {
        objP2_3 = new ObjectifParcelle(2, 3);
        objP2_3Bis = new ObjectifParcelle(2, 3);
        objP3_4 = new ObjectifParcelle(3, 4);
        objP4_3 = new ObjectifParcelle(4, 3);
    }


    @Test
    void getSchema() {
        assertEquals(3, objP2_3.getSchema());
        assertEquals(4, objP3_4.getSchema());
        assertEquals(3, objP4_3.getSchema());
    }


    @Test
    void testEquals() {
        assertEquals(objP2_3, objP2_3Bis);
        assertNotEquals(objP2_3, objP3_4);
        assertNotEquals(objP3_4, objP4_3);
        assertNotEquals(objP2_3, objP4_3);
    }
}