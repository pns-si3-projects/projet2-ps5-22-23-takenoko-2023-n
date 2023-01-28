package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlaquetteTest {
    Plaquette plaquette;


    @BeforeEach
    void setUp() {
        plaquette = new Plaquette();
    }


    @Test
    void getReserveBambousManges() {
        assertEquals(0, plaquette.getReserveBambousManges().length);
    }

    @Test
    void isActionTour() {
        // À développer quand les tours seront joués
        assertFalse(plaquette.isActionTour(Plaquette.ActionPossible.OBJECTIF));
        assertFalse(plaquette.isActionTour(Plaquette.ActionPossible.JARDINIER));
        assertFalse(plaquette.isActionTour(Plaquette.ActionPossible.PANDA));
        assertFalse(plaquette.isActionTour(Plaquette.ActionPossible.PARCELLE));
    }


    @Test
    void nombreBambouCouleur() {
        assertEquals(0, plaquette.nombreBambouCouleur(Couleur.VERTE));
        assertEquals(0, plaquette.nombreBambouCouleur(Couleur.ROSE));
        assertEquals(0, plaquette.nombreBambouCouleur(Couleur.JAUNE));
    }

    @Test
    void mangeSectionBambou() {
        plaquette.mangeSectionBambou(new SectionBambou(Couleur.VERTE));
        plaquette.mangeSectionBambou(new SectionBambou(Couleur.ROSE));
        plaquette.mangeSectionBambou(new SectionBambou(Couleur.JAUNE));
        plaquette.mangeSectionBambou(new SectionBambou(Couleur.ROSE));
        plaquette.mangeSectionBambou(new SectionBambou(Couleur.VERTE));
        plaquette.mangeSectionBambou(new SectionBambou(Couleur.VERTE));

        assertEquals(3, plaquette.nombreBambouCouleur(Couleur.VERTE));
        assertEquals(2, plaquette.nombreBambouCouleur(Couleur.ROSE));
        assertEquals(1, plaquette.nombreBambouCouleur(Couleur.JAUNE));
    }
}