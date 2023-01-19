package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtangTest {
    Etang etang;

    @BeforeEach
    void setUp(){
        etang=new Etang();
    }


    @Test
    void getPosition(){
        assertEquals(etang.position().getX(),0);
        assertEquals(etang.position().getY(),0);
        Assertions.assertEquals(etang.position(),new Position());
    }
}