package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.Motif;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifDiagonale;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.plateau.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaquetteTest {
    SectionBambou secBa;
    ObjectifParcelle objPar2_3;
    ObjectifParcelle objPar3_4;
    ObjectifPanda objPan3_2;
    ObjectifPanda objPan4_2;
    ObjectifJardinier objJar3_2;
    ObjectifJardinier objJar6_4;
    Plaquette plaquette;
    Motif motifParDefaut;

    @BeforeEach
    void setUp() {
        motifParDefaut = new MotifDiagonale(new ParcelleCouleur(new Position(0,0),Couleur.VERT),new ParcelleCouleur(new Position(1,1),Couleur.VERT));

        secBa = new SectionBambou(Couleur.VERT);
        objPar2_3 = new ObjectifParcelle(2, motifParDefaut);
        objPar3_4 = new ObjectifParcelle(3, motifParDefaut);
        objPan3_2 = new ObjectifPanda(3, 2,Couleur.VERT);
        objPan4_2 = new ObjectifPanda(4, 2,Couleur.VERT);
        objJar3_2 = new ObjectifJardinier(3, 2);
        objJar6_4 = new ObjectifJardinier(6, 4);
        plaquette = new Plaquette(objPar2_3, objPan3_2, objJar3_2);
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.VERT)); // modifier pour vert
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.VERT)); // modifier pour rose
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.VERT)); // modifier pour jaune
    }

    @Test
    void getNombreBambousVerts() {
        assertEquals(3, plaquette.getNombreBambousVerts());
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.VERT));
        assertEquals(4, plaquette.getNombreBambousVerts());
    }

    @Test
    void getNombreBambousRoses() {
        assertEquals(0, plaquette.getNombreBambousRoses()); // A modifier
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.ROSE));
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.ROSE));
        assertEquals(2, plaquette.getNombreBambousRoses());
    }

    @Test
    void getNombreBambousJaunes() {
        assertEquals(0, plaquette.getNombreBambousJaunes());
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.JAUNE));
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.JAUNE));
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.JAUNE));
        assertEquals(3, plaquette.getNombreBambousJaunes());
    }

    @Test
    void getNombreObjectifParcelle() {
        assertEquals(1, plaquette.getNombreObjectifParcelle());
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objPan4_2);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(2, plaquette.getNombreObjectifParcelle());
    }

    @Test
    void getNombreObjectifPanda() {
        assertEquals(1, plaquette.getNombreObjectifPanda());
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objPan4_2);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(2, plaquette.getNombreObjectifPanda());
    }

    @Test
    void getNombreObjectifJardinier() {
        assertEquals(1, plaquette.getNombreObjectifJardinier());
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objJar6_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(2, plaquette.getNombreObjectifJardinier());
    }

    @Test
    void getNombreObjectifs() {
        assertEquals(3, plaquette.getNombreObjectifs());
        try {
            plaquette.ajouteObjectif(objPan4_2);
            plaquette.ajouteObjectif(objJar6_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(5, plaquette.getNombreObjectifs());
    }

    @Test
    void getNombreObjectifsMax() {
        assertEquals(5, plaquette.getNombreObjectifsMax());
        assertEquals(Plaquette.NOMBRE_OBJECTIFS_MAX, plaquette.getNombreObjectifsMax());
    }

    @Test
    void getNombreActionsTourRealisees() {
        assertEquals(0, plaquette.getNombreActionsTourRealisees());
        plaquette.realiseAction(Plaquette.ActionPossible.PARCELLE);
        assertEquals(1, plaquette.getNombreActionsTourRealisees());
        plaquette.realiseAction(Plaquette.ActionPossible.PANDA);
        plaquette.realiseAction(Plaquette.ActionPossible.OBJECTIF);
        plaquette.realiseAction(Plaquette.ActionPossible.JARDINIER);
        assertEquals(4, plaquette.getNombreActionsTourRealisees());
    }

    @Test
    void getActionsTourRealisees() {
        Plaquette.ActionPossible[] actions = plaquette.getActionsTourRealisees();
        assertEquals(0, actions.length);
        plaquette.realiseAction(Plaquette.ActionPossible.OBJECTIF);
        actions = plaquette.getActionsTourRealisees();
        assertEquals(1, actions.length);
        assertEquals(Plaquette.ActionPossible.OBJECTIF, actions[0]);
        plaquette.realiseAction(Plaquette.ActionPossible.PARCELLE);
        plaquette.realiseAction(Plaquette.ActionPossible.PANDA);
        plaquette.realiseAction(Plaquette.ActionPossible.JARDINIER);
        actions = plaquette.getActionsTourRealisees();
        assertEquals(4, actions.length);
        assertEquals(Plaquette.ActionPossible.PANDA, actions[2]);
        assertEquals(Plaquette.ActionPossible.PARCELLE, actions[3]); // rendu sens inverse des actions possibles
        assertEquals(Plaquette.ActionPossible.JARDINIER,actions[0]);
    }

    @Test
    void isActionRealisee() {
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.PANDA));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.OBJECTIF));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.JARDINIER));

        plaquette.realiseAction(Plaquette.ActionPossible.PANDA);

        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE));
        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.PANDA));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.OBJECTIF));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.JARDINIER));

        plaquette.realiseAction(Plaquette.ActionPossible.PARCELLE);
        plaquette.realiseAction(Plaquette.ActionPossible.OBJECTIF);

        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE));
        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.OBJECTIF));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.JARDINIER));

        plaquette.realiseAction(Plaquette.ActionPossible.JARDINIER);

        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE));
        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.OBJECTIF));
        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.JARDINIER));
    }

    @Test
    void getObjectifsParcelle() {
        assertEquals(objPar2_3, plaquette.getObjectifsParcelle()[0]);
        try {
            plaquette.ajouteObjectif(objPar3_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        // Parce que pris dans l'ordre inverse d'ajout
        assertNotEquals(objPar2_3, plaquette.getObjectifsParcelle()[0]);
        assertEquals(objPar3_4, plaquette.getObjectifsParcelle()[0]);
        assertEquals(objPar2_3, plaquette.getObjectifsParcelle()[1]);
    }

    @Test
    void getObjectifsPanda() {
        assertEquals(objPan3_2, plaquette.getObjectifsPanda()[0]);
        try {
            plaquette.ajouteObjectif(objPan4_2);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        // Parce que pris dans l'ordre inverse d'ajout
        assertNotEquals(objPan3_2, plaquette.getObjectifsPanda()[0]);
        assertEquals(objPan4_2, plaquette.getObjectifsPanda()[0]);
        assertEquals(objPan3_2, plaquette.getObjectifsPanda()[1]);
    }

    @Test
    void getObjectifsJardinier() {
        assertEquals(objJar3_2, plaquette.getObjectifsJardinier()[0]);
        try {
            plaquette.ajouteObjectif(objJar6_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        // Parce que pris dans l'ordre inverse d'ajout
        assertNotEquals(objJar3_2, plaquette.getObjectifsJardinier()[0]);
        assertEquals(objJar6_4, plaquette.getObjectifsJardinier()[0]);
        assertEquals(objJar3_2, plaquette.getObjectifsJardinier()[1]);
    }

    @Test
    void getObjectifs() {
        // Parce que pris dans l'ordre inverse d'ajout
        assertEquals(objJar3_2, plaquette.getObjectifs()[0]);
        assertEquals(objPan3_2, plaquette.getObjectifs()[1]);
        assertEquals(objPar2_3, plaquette.getObjectifs()[2]);
        try {
            plaquette.ajouteObjectif(objPan4_2);
            plaquette.ajouteObjectif(objJar6_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(objJar6_4, plaquette.getObjectifs()[0]);
        assertEquals(objPan4_2, plaquette.getObjectifs()[1]);
        assertEquals(objPar2_3, plaquette.getObjectifs()[4]);
    }

    @Test
    void ajouteSectionBambou() {
        assertEquals(3, plaquette.getNombreBambousVerts());
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.VERT));
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.JAUNE));
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.ROSE));
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.VERT));
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.VERT));
        plaquette.ajouteSectionBambou(new SectionBambou(Couleur.JAUNE));
        assertEquals(6, plaquette.getNombreBambousVerts());
        assertEquals(1, plaquette.getNombreBambousRoses());
        assertEquals(2, plaquette.getNombreBambousJaunes());
    }

    @Test
    void ajouteObjectif() {
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objPan4_2);
            assertEquals(5, plaquette.getNombreObjectifs());
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertThrows(NombreObjectifsEnCoursException.class, () -> plaquette.ajouteObjectif(objJar6_4));
    }

    @Test
    void realiseAction() {
        // Vérifie l'état de la plaquette
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.PANDA));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.OBJECTIF));
        assertFalse(plaquette.isActionRealisee(Plaquette.ActionPossible.JARDINIER));
        // Réalise des actions
        assertTrue(plaquette.realiseAction(Plaquette.ActionPossible.PARCELLE));
        assertTrue(plaquette.realiseAction(Plaquette.ActionPossible.PANDA));
        assertTrue(plaquette.realiseAction(Plaquette.ActionPossible.OBJECTIF));
        assertTrue(plaquette.realiseAction(Plaquette.ActionPossible.JARDINIER));
        assertFalse(plaquette.realiseAction(Plaquette.ActionPossible.PANDA));
        // Vérifie l'état de la plaquette
        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE));
        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.PANDA));
        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.OBJECTIF));
        assertTrue(plaquette.isActionRealisee(Plaquette.ActionPossible.JARDINIER));
    }

    @Test
    void supprimeObjectif() {
        // Ajoute 2 objectifs
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objPan4_2);
            assertEquals(5, plaquette.getNombreObjectifs());
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        // Supprime 3 objectifs de la plaquette
        assertTrue(plaquette.supprimeObjectif(objPar2_3));
        assertTrue(plaquette.supprimeObjectif(objPan3_2));
        assertTrue(plaquette.supprimeObjectif(objJar3_2));
        // Essaie de resupprimer un objectif déjà supprimé
        assertFalse(plaquette.supprimeObjectif(objPar2_3));
        // Supprime les 2 derniers objectifs
        assertTrue(plaquette.supprimeObjectif(objPar3_4));
        assertTrue(plaquette.supprimeObjectif(objPan4_2));
    }
}