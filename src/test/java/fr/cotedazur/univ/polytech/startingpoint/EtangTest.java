package fr.cotedazur.univ.polytech.startingpoint;

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
        assertEquals(etang.getPosition().getX(),0);
        assertEquals(etang.getPosition().getY(),0);
        assertEquals(etang.getPosition(),new Position());
    }
}