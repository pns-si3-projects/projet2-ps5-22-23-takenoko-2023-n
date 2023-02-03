package fr.cotedazur.univ.polytech.startingpoint.pioche;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PiocheIrrigationTest {
    PiocheIrrigation piocheIrrigation;
    Random random;

    @BeforeEach
    void setup(){
        random = new Random();
        piocheIrrigation = new PiocheIrrigation(random);
    }

    @Test
    void getNombreIrrigation() {
        assertEquals(20, piocheIrrigation.getNombreIrrigation());
    }

    @Test
    void isEmptyIrrigation() {
        assertFalse(piocheIrrigation.isEmptyIrrigation());
        for (int i = 0; i<20; i++){
            piocheIrrigation.poseIrrigation();
        }
        assertTrue(piocheIrrigation.isEmptyIrrigation());
    }

    @Test
    void poseIrrigation() {
        piocheIrrigation.poseIrrigation();
        assertEquals(19, piocheIrrigation.getNombreIrrigation());
    }
}