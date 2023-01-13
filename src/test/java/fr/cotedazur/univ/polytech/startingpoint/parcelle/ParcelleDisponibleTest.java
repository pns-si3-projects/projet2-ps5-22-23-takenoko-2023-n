package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ParcelleDisponibleTest {
    Position position10;
    Position position01;
    ParcelleDisponible parD10;
    ParcelleDisponible parD01;

    @BeforeEach
    void setUp(){
        position10 = new Position(1,0);
        position01 = new Position(0,1);
        parD10 = new ParcelleDisponible(position10);
        parD01 = new ParcelleDisponible(position01);
    }

    @Test
    void getPosition(){
        assertEquals(parD10.position(),position10);
        assertNotEquals(parD01.position(),position10);
        assertEquals(parD01.position(),position01);
    }

    @Test
    void testEquals(){
        ParcelleDisponible parD10bis = new ParcelleDisponible(position10);
        assertEquals(parD10bis,parD10);
        assertNotEquals(parD01,parD10bis);
        assertNotEquals(parD01,parD10);
    }
}