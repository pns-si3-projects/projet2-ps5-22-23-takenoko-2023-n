package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaquetteTest {
    Plaquette plaquette;
    boolean[] actions;
    Plaquette.ActionPossible parcelle;
    Plaquette.ActionPossible jardinier;
    Plaquette.ActionPossible panda;
    Plaquette.ActionPossible objectif;


    @BeforeEach
    void setUp() {
        plaquette = new Plaquette();
        actions = new boolean[]{false, false, false, false};
        parcelle = Plaquette.ActionPossible.PARCELLE;
        jardinier = Plaquette.ActionPossible.JARDINIER;
        panda = Plaquette.ActionPossible.PANDA;
        objectif = Plaquette.ActionPossible.OBJECTIF;
    }


    @Test
    void getReserveBambousManges() {
        assertEquals(0, plaquette.getReserveBambousManges().length);
    }

    @Test
    void getActionsTour() {
        boolean[] actionsTour = plaquette.getActionsTour();

        assertEquals(actions[parcelle.ordinal()], actionsTour[parcelle.ordinal()]);
        assertEquals(actions[jardinier.ordinal()], actionsTour[jardinier.ordinal()]);
        assertEquals(actions[panda.ordinal()], actionsTour[panda.ordinal()]);
        assertEquals(actions[objectif.ordinal()], actionsTour[objectif.ordinal()]);
    }

    @Test
    void isActionTour() {
        assertFalse(plaquette.isActionTour(parcelle));
        assertFalse(plaquette.isActionTour(jardinier));
        assertFalse(plaquette.isActionTour(panda));
        assertFalse(plaquette.isActionTour(objectif));
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

    @Test
    void joueActionTour() {
        assertTrue(plaquette.joueActionTour(panda));
        assertTrue(plaquette.joueActionTour(jardinier));
        assertFalse(plaquette.isActionTour(parcelle));
        assertTrue(plaquette.isActionTour(jardinier));
        assertTrue(plaquette.isActionTour(panda));
        assertFalse(plaquette.isActionTour(objectif));

        assertFalse(plaquette.joueActionTour(parcelle));
        assertFalse(plaquette.joueActionTour(objectif));

        plaquette.termineTour();
        assertTrue(plaquette.joueActionTour(parcelle));
        assertTrue(plaquette.joueActionTour(objectif));
        assertTrue(plaquette.isActionTour(parcelle));
        assertFalse(plaquette.isActionTour(jardinier));
        assertFalse(plaquette.isActionTour(panda));
        assertTrue(plaquette.isActionTour(objectif));
    }

    @Test
    void termineTour() {
        plaquette.joueActionTour(parcelle);
        plaquette.joueActionTour(jardinier);
        assertTrue(plaquette.isActionTour(parcelle));
        assertTrue(plaquette.isActionTour(jardinier));
        plaquette.termineTour();
        assertFalse(plaquette.isActionTour(parcelle));
        assertFalse(plaquette.isActionTour(jardinier));

        plaquette.joueActionTour(panda);
        assertTrue(plaquette.isActionTour(panda));
        plaquette.termineTour();
        assertFalse(plaquette.isActionTour(panda));

        plaquette.joueActionTour(objectif);
        assertTrue(plaquette.isActionTour(objectif));
        plaquette.termineTour();
        assertFalse(plaquette.isActionTour(objectif));
    }
}