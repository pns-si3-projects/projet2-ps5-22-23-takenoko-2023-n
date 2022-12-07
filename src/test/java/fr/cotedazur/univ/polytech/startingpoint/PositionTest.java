package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    Position p0_0;
    Position p2_6;
    Position p2_6_bis;

    @BeforeEach
    void setUp(){
        p0_0=new Position();
        p2_6=new Position(2,6);
        p2_6_bis=new Position(2,6);
    }

    @Test
    void getX() {
        assertEquals(0, p0_0.getX());
        assertEquals(2, p2_6.getX());
        assertNotEquals(4,p2_6_bis.getX());
    }

    @Test
    void getY() {
        assertEquals(0, p0_0.getY());
        assertEquals(6, p2_6.getY());
        assertNotEquals(4,p2_6_bis.getY());
    }

    @Test
    void testEquals() {
        assertEquals(p2_6, p2_6_bis);
        assertNotEquals(p0_0, p2_6);
    }
}