package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BambouTest {

    Bambou bambou0_1;
    Bambou bambou0_1b;
    Bambou bambou0_2;

    @BeforeEach
    void setUp(){
        bambou0_1=new Bambou(new Position(0,1));
        bambou0_1b=new Bambou(new Position(0,1));
        bambou0_2=new Bambou(new Position(0,2));
    }

    @Test
    void getPosition() {
        assertEquals(bambou0_1.getPosition(),new Position(0,1));
        assertNotEquals(bambou0_2.getPosition(), new Position(0,1));
    }

    @Test
    void testEquals() {
        assertEquals(bambou0_1,bambou0_1b);
        assertNotEquals(bambou0_2,bambou0_1);
    }
}