package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifPandaTest {
    ObjectifPanda objP1_1;
    ObjectifPanda objP2_2;
    ObjectifPanda objP2_2_bis;
    ObjectifPanda objP3_2;
    ObjectifPanda objP2_3;

    @BeforeEach
    void setUp() {
        objP1_1 = new ObjectifPanda(1, 1);
        objP2_2 = new ObjectifPanda(2, 2);
        objP2_2_bis = new ObjectifPanda(2, 2);
        objP3_2 = new ObjectifPanda(3, 2);
        objP2_3 = new ObjectifPanda(2, 3);
    }

    @Test
    void getNombreBambousAManger() {
        assertEquals(1, objP1_1.getNombreBambousAManger());
        assertEquals(2, objP2_2.getNombreBambousAManger());
        assertEquals(3, objP2_3.getNombreBambousAManger());
        assertNotEquals(3, objP3_2.getNombreBambousAManger());
    }

    @Test
    void testEquals() {
        assertEquals(objP2_2, objP2_2_bis);
        assertNotEquals(objP1_1, objP2_2_bis);
        assertNotEquals(objP3_2, objP2_2);
        assertNotEquals(objP2_3, objP2_2);
    }
}