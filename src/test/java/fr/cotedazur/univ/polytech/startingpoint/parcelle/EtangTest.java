package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
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
        assertEquals(etang.getPosition().getX(),0);
        assertEquals(etang.getPosition().getY(),0);
        Assertions.assertEquals(etang.getPosition(),new Position());
    }
}