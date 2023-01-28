package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtangTest {
    Etang etang;


    @BeforeEach
    void setUp(){
        etang = new Etang();
    }


    @Test
    void getPosition(){
        assertEquals(new Position(), etang.getPosition());
        assertEquals(0, etang.getPosition().getX());
        assertEquals(0, etang.getPosition().getY());
    }


    @Test
    void testToString(){
        assertEquals("Etang en (0,0)", etang.toString());
    }
}