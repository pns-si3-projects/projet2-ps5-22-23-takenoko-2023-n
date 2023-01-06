package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EtangTest {
    Etang etang;

    @BeforeEach
    void setUp() {
        etang = new Etang();
    }


    @Test
    void getPosition(){
        assertEquals(0, etang.getPosition().getX());
        assertEquals(0, etang.getPosition().getY());
        assertEquals(new Position(), etang.getPosition());
    }
}