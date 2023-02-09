package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentPossibleMainTest {
    ArgumentPossibleMain thousands;
    ArgumentPossibleMain demo;
    ArgumentPossibleMain csv;


    @BeforeEach
    void setUp() {
        thousands = ArgumentPossibleMain.THOUSANDS;
        demo = ArgumentPossibleMain.DEMO;
        csv = ArgumentPossibleMain.CSV;
    }


    @Test
    void isThousands() {
        assertTrue(thousands.isThousands());
        assertFalse(demo.isThousands());
        assertFalse(csv.isThousands());
    }

    @Test
    void isDemo() {
        assertFalse(thousands.isDemo());
        assertTrue(demo.isDemo());
        assertFalse(csv.isDemo());
    }

    @Test
    void isCsv() {
        assertFalse(thousands.isCsv());
        assertFalse(demo.isCsv());
        assertTrue(csv.isCsv());
    }


    @Test
    void testToString() {
        assertEquals("--2thousands", thousands.toString());
        assertEquals("--demo", demo.toString());
        assertEquals("--csv", csv.toString());
    }
}