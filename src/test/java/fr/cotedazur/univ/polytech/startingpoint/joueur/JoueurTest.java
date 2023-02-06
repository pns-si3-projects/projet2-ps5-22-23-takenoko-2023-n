package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Empereur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    Joueur joueurParcelle;
    Joueur joueurPanda;
    Joueur joueurJardinier;
    Plateau plateau;
    boolean[] piochesVides;


    @BeforeEach
    void setUp() {
        joueurParcelle = new Joueur("joueur1", Strategie.StrategiePossible.PARCELLE);
        joueurPanda = new Joueur("joueur2", Strategie.StrategiePossible.PANDA);
        joueurJardinier = new Joueur("joueur3", Strategie.StrategiePossible.JARDINIER);
        plateau = new Plateau();
        piochesVides = new boolean[] {false, false, false, false, false};
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
    void nombreObjectifsTermines() {
        assertEquals(0, joueurParcelle.nombreObjectifsTermines());
        assertNotEquals(1, joueurPanda.nombreObjectifsTermines());
    }

    @Test
    void nombrePoints() {
        assertEquals(0, joueurParcelle.nombrePoints());
        assertNotEquals(1, joueurPanda.nombrePoints());
    }

    @Test
    void nombrePointsObjectifsPanda() {
        assertEquals(0, joueurParcelle.nombrePointsObjectifsPanda());
        assertNotEquals(1, joueurPanda.nombrePointsObjectifsPanda());
    }

    @Test
    void choisiAction() {
        assertEquals(Plaquette.ActionPossible.OBJECTIF, joueurParcelle.choisiAction(plateau, piochesVides));
        assertEquals(Plaquette.ActionPossible.PARCELLE, joueurParcelle.choisiAction(plateau, piochesVides));

        assertEquals(Plaquette.ActionPossible.PARCELLE, joueurJardinier.choisiAction(plateau, piochesVides));
        assertEquals(Plaquette.ActionPossible.OBJECTIF, joueurJardinier.choisiAction(plateau, piochesVides));

        assertEquals(Plaquette.ActionPossible.OBJECTIF, joueurPanda.choisiAction(plateau, piochesVides));
        assertEquals(Plaquette.ActionPossible.PARCELLE, joueurPanda.choisiAction(plateau, piochesVides));
    }

    @Test
    void joueParcelle() {
    }

    @Test
    void joueIrrigation() {
    }

    @Test
    void deplaceJardinier() {
    }

    @Test
    void deplacePanda() {
    }

    @Test
    void piocheObjectif() {
    }

    @Test
    void recoitEmpereur() {
        Objectif empereur = new Empereur();
        Objectif objectif = new ObjectifParcelle(3, 3);

        joueurParcelle.recoitEmpereur(empereur);
        Objectif[] objectifs = joueurParcelle.getObjectifsTermines();
        assertEquals(empereur, objectifs[0]);

        assertThrows(IllegalArgumentException.class, () -> joueurParcelle.recoitEmpereur(objectif));
        assertThrows(IllegalArgumentException.class, () -> joueurJardinier.recoitEmpereur(objectif));
        assertThrows(IllegalArgumentException.class, () -> joueurPanda.recoitEmpereur(objectif));
    }


    @Test
    void testToString() {
        assertEquals(joueurParcelle.getNom(), joueurParcelle.toString());
        assertEquals(joueurPanda.getNom(), joueurPanda.toString());
        assertEquals(joueurJardinier.getNom(), joueurJardinier.toString());
        assertNotEquals(joueurParcelle.getNom(), joueurPanda.toString());
    }
}