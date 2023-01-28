package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandaTest {
    Panda panda1;
    Panda panda2;


    @BeforeEach
    void setUp(){
        panda1 = new Panda();
        panda2 = new Panda();
    }


    @Test
    void getPosition() {
        assertEquals(new Position(), panda1.getPosition());
        assertEquals(new Position(), panda2.getPosition());
    }


    @Test
    void testToString() {
        assertEquals("Panda en (0,0)", panda1.toString());
        assertEquals("Panda en (0,0)", panda2.toString());
    }
}