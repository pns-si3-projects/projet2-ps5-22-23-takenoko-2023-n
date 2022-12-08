package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    @Test
    void testGetNom() {
        Joueur joueur1 = new Joueur("Robot1");
        assertEquals("Robot1", joueur1.getNom());
        assertNotEquals("Robot2", joueur1.getNom());
    }

    @Test
    void testSetNom() {
        Joueur joueur1 = new Joueur("Robot1");
        joueur1.setNom("Robot2");
        assertEquals("Robot2", joueur1.getNom());
        assertNotEquals("Robot1", joueur1.getNom());
    }
}