package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandaTest {
    Panda panda1;
    Panda panda2;
    Position position0_0;
    Position position1_1;


    @BeforeEach
    void setUp(){
        panda1 = new Panda();
        panda2 = new Panda();
        position0_0 = new Position(0,0);
        position1_1 = new Position(1,1);
    }


    @Test
    void getPosition() {
        assertEquals(new Position(), panda1.getPosition());
        assertEquals(new Position(), panda2.getPosition());
    }

    @Test
    void moveTest () {
        panda1.move(position1_1);
        assertEquals(new Position(1,1), panda1.getPosition());
        panda1.move(position0_0);
        assertEquals(new Position(0,0),panda1.getPosition());
    }


    @Test
    void testToString() {
        assertEquals("Panda", panda1.toString());
        assertEquals("Panda", panda2.toString());
    }
}