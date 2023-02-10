package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ParcelleDisponibleTest {
    ParcelleDisponible parD2_0;
    ParcelleDisponible parD2_0Bis;
    ParcelleDisponible parD0_2;
    Position position2_0;
    Position position0_2;


    @BeforeEach
    void setUp(){
        position2_0 = new Position(2,0);
        position0_2 = new Position(0,2);
        parD2_0 = new ParcelleDisponible(position2_0);
        parD2_0Bis = new ParcelleDisponible(position2_0);
        parD0_2 = new ParcelleDisponible(position0_2);
    }


    @Test
    void getPosition(){
        assertEquals(position2_0, parD2_0.getPosition());
        assertEquals(position0_2, parD0_2.getPosition());
    }


    @Test
    void testToString(){
        assertEquals("Parcelle disponible en (2,0)", parD2_0.toString());
        assertEquals("Parcelle disponible en (0,2)", parD0_2.toString());
    }

    @Test
    void testEquals(){
        assertEquals(parD2_0, parD2_0Bis);
        assertNotEquals(parD2_0, parD0_2);
    }
}