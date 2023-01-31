package fr.cotedazur.univ.polytech.startingpoint.joueur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JoueurTest {
    Joueur joueurParcelle;
    Joueur joueurPanda;
    Joueur joueurJardinier;


    @BeforeEach
    void setUp() {
        joueurParcelle = new Joueur("joueur1", Strategie.StrategiePossible.PARCELLE);
        joueurPanda = new Joueur("joueur2", Strategie.StrategiePossible.PANDA);
        joueurJardinier = new Joueur("joueur3", Strategie.StrategiePossible.JARDINIER);
    }


    @Test
    void getNom() {
        assertEquals("joueur1", joueurParcelle.getNom());
        assertNotEquals("joueur1", joueurPanda.getNom());
    }

    @Test
    void getPlaquette() {
        assertEquals(Plaquette.class, joueurParcelle.getPlaquette().getClass());
        assertEquals(Plaquette.class, joueurPanda.getPlaquette().getClass());
    }

    @Test
    void getObjectifsEnMain() {
        // À modifier quand le joueur aura des objectifs
        assertEquals(0, joueurParcelle.getObjectifsEnMain().length);
        assertEquals(0, joueurPanda.getObjectifsEnMain().length);
    }

    @Test
    void getObjectifsTermines() {
        // À modifier quand le joueur aura des objectifs
        assertEquals(0, joueurParcelle.getObjectifsTermines().length);
        assertEquals(0, joueurPanda.getObjectifsTermines().length);
    }


    @Test
    void nombreObjectifsEnMain() {
        assertEquals(0, joueurParcelle.nombreObjectifsEnMain());
        assertNotEquals(1, joueurPanda.nombreObjectifsEnMain());
    }

    @Test
    void nombrePoints() {
        assertEquals(0, joueurParcelle.nombrePoints());
        assertNotEquals(1, joueurPanda.nombrePoints());
    }


    @Test
    void testToString() {
        assertEquals(joueurParcelle.getNom(), joueurParcelle.toString());
        assertEquals(joueurPanda.getNom(), joueurPanda.toString());
        assertEquals(joueurJardinier.getNom(), joueurJardinier.toString());
        assertNotEquals(joueurParcelle.getNom(), joueurPanda.toString());
    }
}