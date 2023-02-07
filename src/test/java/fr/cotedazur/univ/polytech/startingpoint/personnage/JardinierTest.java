package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JardinierTest {
    Jardinier jardinier1;
    Jardinier jardinier2;
    Position position0_0;
    Position position1_1;


    @BeforeEach
    void setUp(){
        jardinier1 = new Jardinier();
        jardinier2 = new Jardinier();
        position0_0 = new Position(0,0);
        position1_1 = new Position(1,1);
    }


    @Test
    void getPosition() {
        assertEquals(new Position(), jardinier1.getPosition());
        assertEquals(new Position(), jardinier2.getPosition());
    }

    @Test
    void moveTest () {
        jardinier1.move(position1_1);
        assertEquals(new Position(1,1), jardinier1.getPosition());
        jardinier1.move(position0_0);
        assertEquals(new Position(0,0),jardinier1.getPosition());
    }

    @Test
    void testToString() {
        assertEquals("Jardinier en (0,0)", jardinier1.toString());
        assertEquals("Jardinier en (0,0)", jardinier2.toString());
    }
}