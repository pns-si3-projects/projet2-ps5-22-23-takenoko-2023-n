package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PandaTest {
    Panda panda;

    @BeforeEach
    void setUp(){
        panda = new Panda();
    }

    @Test
    void getPosition(){
        assertEquals(new Position(), panda.getPosition());
    }
}
