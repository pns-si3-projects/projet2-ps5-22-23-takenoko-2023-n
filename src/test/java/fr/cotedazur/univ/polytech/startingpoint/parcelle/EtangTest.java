package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtangTest {
    Etang etang1;
    Etang etang2;


    @BeforeEach
    void setUp(){
        etang1 = new Etang();
        etang2 = new Etang();
    }


    @Test
    void getPosition(){
        assertEquals(new Position(), etang1.getPosition());
        assertEquals(0, etang1.getPosition().getX());
        assertEquals(0, etang1.getPosition().getY());
        assertEquals(new Position(), etang2.getPosition());
    }


    @Test
    void testToString(){
        assertEquals("Etang en (0,0)", etang1.toString());
        assertEquals("Etang en (0,0)", etang2.toString());
    }

    @Test
    void testEquals(){
        assertEquals(etang1, etang2);
    }
}