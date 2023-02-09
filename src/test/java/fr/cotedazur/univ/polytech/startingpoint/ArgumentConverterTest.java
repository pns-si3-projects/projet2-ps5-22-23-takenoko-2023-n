package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentConverterTest {
    ArgumentConverter argumentConverter;


    @BeforeEach
    void setUp() {
        argumentConverter = new ArgumentConverter();
    }


    @Test
    void convert() {
        assertEquals(ArgumentPossibleMain.THOUSANDS, argumentConverter.convert("--2thousands"));
        assertEquals(ArgumentPossibleMain.DEMO, argumentConverter.convert("--demo"));
        assertEquals(ArgumentPossibleMain.CSV, argumentConverter.convert("--csv"));
        assertEquals(ArgumentPossibleMain.DEMO, argumentConverter.convert(""));
        assertEquals(ArgumentPossibleMain.DEMO, argumentConverter.convert("zeofn"));
    }
}