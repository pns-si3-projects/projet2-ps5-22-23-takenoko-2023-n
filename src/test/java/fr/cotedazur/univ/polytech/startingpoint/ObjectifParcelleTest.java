package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ObjectifParcelleTest {
    ObjectifParcelle objP1_1;
    ObjectifParcelle objP2_2;
    ObjectifParcelle objP2_2_bis;
    ObjectifParcelle objP3_2;
    ObjectifParcelle objP2_3;

    @BeforeEach
    void setUp() {
        objP1_1 = new ObjectifParcelle(1, 1);
        objP2_2 = new ObjectifParcelle(2, 2);
        objP2_2_bis = new ObjectifParcelle(2, 2);
        objP3_2 = new ObjectifParcelle(3, 2);
        objP2_3 = new ObjectifParcelle(2, 3);
    }

    @Test
    void getNombreParcelles() {
        assertEquals(1, objP1_1.getNombreParcelles());
        assertEquals(2, objP2_2.getNombreParcelles());
        assertEquals(3, objP2_3.getNombreParcelles());
        assertNotEquals(3, objP3_2.getNombreParcelles());
    }

    @Test
    void testEquals() {
        assertEquals(objP2_2, objP2_2_bis);
        assertNotEquals(objP1_1, objP2_2_bis);
        assertNotEquals(objP3_2, objP2_2);
        assertNotEquals(objP2_3, objP2_2);
    }
}