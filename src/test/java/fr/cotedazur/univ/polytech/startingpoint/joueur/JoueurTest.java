package fr.cotedazur.univ.polytech.startingpoint.joueur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    // Attributs
    Joueur joueur1;
    Joueur joueur2;


    // Constructeur
    @BeforeEach
    void setUp() {
        joueur1 = new Joueur("joueur1");
        joueur2 = new Joueur("joueur2");
    }


    // Accesseurs
    @Test
    void getNom() {
        assertEquals("joueur1", joueur1.getNom());
        assertNotEquals("joueur1", joueur2.getNom());
    }

    @Test
    void getPlaquette() {
        // À faire avec la plaquette
        //assertEquals(..., joueur1.getPlaquette());
        //assertNotEquals(..., joueur2.getPlaquette());
    }

    @Test
    void getObjectifARealiserList() {
        assertEquals(new ArrayList<>(), joueur1.getObjectifEnMainList());
        assertEquals(new ArrayList<>(), joueur2.getObjectifEnMainList());
    }

    @Test
    void getObjectifTermineList() {
        assertEquals(new ArrayList<>(), joueur1.getObjectifTermineList());
        assertEquals(new ArrayList<>(), joueur2.getObjectifTermineList());
    }


    // Autres méthodes
    @Test
    void nombreObjectifsEnMain() {
        assertEquals(0, joueur1.nombreObjectifsEnMain());
        assertNotEquals(1, joueur2.nombreObjectifsEnMain());
    }

    @Test
    void nombrePoints() {
        assertEquals(0, joueur1.nombrePoints());
        assertNotEquals(1, joueur2.nombrePoints());
    }


    // Méthode toString
    @Test
    void testToString() {
        assertEquals(joueur1.getNom(), joueur1.toString());
        assertEquals(joueur2.getNom(), joueur2.toString());
        assertNotEquals(joueur1.getNom(), joueur2.toString());
    }
}