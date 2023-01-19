package fr.cotedazur.univ.polytech.startingpoint.joueur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertEquals(Plaquette.class, joueur1.getPlaquette().getClass());
        assertEquals(Plaquette.class, joueur2.getPlaquette().getClass());
    }

    @Test
    void getObjectifARealiserList() {
        assertEquals(0, joueur1.nombreObjectifsEnMain());
        assertEquals(0, joueur1.getObjectifsEnMain().length);
        assertEquals(0, joueur2.getObjectifsEnMain().length);
    }

    @Test
    void getObjectifTermineList() {
        assertEquals(0, joueur1.getObjectifsTermines().length);
        assertEquals(0, joueur2.getObjectifsTermines().length);
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