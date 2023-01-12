package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandaTest {
    Panda panda;
    Position position_1_0;
    Position position_0_0;

    @BeforeEach
    void setUp(){
        panda=new Panda();
        position_1_0=new Position(1,0);
        position_0_0=new Position(0,0);
    }

    @Test
    void move() {
        assertEquals(panda.getPosition(),position_0_0);
        panda.move(position_1_0);
        assertNotEquals(panda.getPosition(),position_0_0);
        assertEquals(panda.getPosition(), new Position(1,0));
    }

    @Test
    void getPosition() {
        assertEquals(panda.getPosition(), new Position(0,0));
        panda.move((position_1_0));
        assertEquals(panda.getPosition(),new Position(1,0));
        assertNotEquals(panda.getPosition(),new Position(0,0));
    }
}