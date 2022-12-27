package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ObjectifBambouTest {
    ObjectifBambou objBambou;

    @BeforeEach
    void setUp() {
        objBambou = new ObjectifBambou(2);
    }

    @Test
    void getNombreBambous() {
        assertEquals(2, objBambou.getNombreBambous());
        assertNotEquals(1, objBambou.getNombreBambous());
    }
}
