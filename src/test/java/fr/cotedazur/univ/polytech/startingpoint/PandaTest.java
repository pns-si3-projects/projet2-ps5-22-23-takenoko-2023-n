package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PandaTest {
    Panda panda;
    Position pos0_0;
    Position pos2_2;
    Position pos6_0;
    Position pos3_m3;
    Position posm1_m1;
    Position pos2_0;
    Position posm2_2;
    Position pos0_2;

    @BeforeEach
    void setUp(){
        panda = new Panda();
        pos0_0 = new Position(0,0);
        pos2_2 = new Position(2,2);
        pos6_0 = new Position(6,0);
        pos3_m3 = new Position(3,-3);
        posm1_m1 = new Position(1,-1);
        pos2_0 = new Position(2,0);
        posm2_2 = new Position(2,2);
        pos0_2 = new Position(0,2);
    }

    @Test
    void getPosition(){
        assertEquals(new Position(), panda.getPosition());
    }

    @Test
    void deplacementPossible() {
        assertTrue(panda.deplacementPossible(pos2_2));
        assertTrue(panda.deplacementPossible(pos6_0));
        assertTrue(panda.deplacementPossible(pos3_m3));
        assertTrue(panda.deplacementPossible(posm1_m1));
        assertTrue(panda.deplacementPossible(pos2_0));
        assertTrue(panda.deplacementPossible(posm2_2));
        assertFalse(panda.deplacementPossible(pos0_2));
        assertFalse(panda.deplacementPossible(pos0_0));
    }

    @Test
    void deplacePanda() {
        assertEquals(new Position(), panda.getPosition());
        panda.deplacePanda(2,2);
        assertEquals(new Position(2,2), panda.getPosition());
        assertNotEquals(new Position(), panda.getPosition());
    }
}
