package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JardinierTest {
    Jardinier jardinier;
    Position position_1_0;
    Position position_0_0;

    @BeforeEach
    void setUp(){
        jardinier=new Jardinier();
        position_1_0=new Position(1,0);
        position_0_0=new Position(0,0);
    }

    @Test
    void move() {
        assertEquals(jardinier.position(),position_0_0);
        jardinier.move(position_1_0);
        assertNotEquals(jardinier.position(),position_0_0);
        assertEquals(jardinier.position(), new Position(1,0));
    }

    @Test
    void getPosition() {
        assertEquals(jardinier.position(), new Position(0,0));
        jardinier.move((position_1_0));
        assertEquals(jardinier.position(),new Position(1,0));
        assertNotEquals(jardinier.position(),new Position(0,0));
    }
}