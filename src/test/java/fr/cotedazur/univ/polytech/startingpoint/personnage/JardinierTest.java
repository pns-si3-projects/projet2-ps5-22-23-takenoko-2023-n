package fr.cotedazur.univ.polytech.startingpoint.personnage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JardinierTest {
    Jardinier jardinier1;
    Jardinier jardinier2;


    @BeforeEach
    void setUp(){
        jardinier1 = new Jardinier();
        jardinier2 = new Jardinier();
    }


    @Test
    void testToString() {
        assertEquals("Jardinier en (0,0)", jardinier1.toString());
        assertEquals("Jardinier en (0,0)", jardinier2.toString());
    }
}