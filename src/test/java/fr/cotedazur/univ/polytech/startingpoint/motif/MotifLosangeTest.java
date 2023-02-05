package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotifLosangeTest {
    Motif motifLosange;
    ParcelleCouleur parcelleCouleur00;
    ParcelleCouleur parcelleCouleur11;
    ParcelleCouleur parcelleCouleurm11;
    ParcelleCouleur parcelleCouleur02;
    Couleur couleurDefaut;


    @BeforeEach
    void setUp() {
        couleurDefaut = Couleur.VERT;
        parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), couleurDefaut);
        parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), couleurDefaut);
        parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), couleurDefaut);
        motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
    }

    @Test
    void getOrientation() {
        ParcelleCouleur[][] allOrientation = motifLosange.getOrientation();
        ParcelleCouleur[] orientation = new ParcelleCouleur[4];
        orientation[0] = parcelleCouleur00;
        orientation[1] = parcelleCouleur11;
        orientation[2] = parcelleCouleurm11;
        orientation[3] = parcelleCouleur02;
        assertArrayEquals(orientation, allOrientation[0]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[1]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[2]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(0, -2), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[3]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(-2, 0), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(-3, -1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[4]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(-3, 1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[5]);
    }

    @Test
    void getOrientationForIA() {
        ParcelleCouleur[][] allOrientation = motifLosange.getOrientation();
        ParcelleCouleur[] orientation = new ParcelleCouleur[4];
        orientation[0] = parcelleCouleur00;
        orientation[1] = parcelleCouleur11;
        orientation[2] = parcelleCouleurm11;
        orientation[3] = parcelleCouleur02;
        assertArrayEquals(orientation, allOrientation[0]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[1]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[2]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(0, -2), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[3]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(-2, 0), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(-3, -1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[4]);

        orientation[2] = orientation[1];
        orientation[1] = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        orientation[3] = new ParcelleCouleur(new Position(-3, 1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[5]);
    }

    @Test
    void compareMotif() {
        Motif motifLosangeRessemblant = new MotifLosange(new ParcelleCouleur(new Position(2, 2), couleurDefaut), new ParcelleCouleur(new Position(1, 3), couleurDefaut), new ParcelleCouleur(new Position(0, 2), couleurDefaut), new ParcelleCouleur(new Position(-1, 3), couleurDefaut));
        assertTrue(motifLosange.compareMotif(motifLosange));
        assertTrue(motifLosange.compareMotif(motifLosangeRessemblant));
    }

    @Test
    void getTableauParcelle() {
        ParcelleCouleur[] tableauParcelleCouleurMotif = motifLosange.getTableauParcelles();
        assertEquals(parcelleCouleur00, tableauParcelleCouleurMotif[0]);
        assertEquals(parcelleCouleur11, tableauParcelleCouleurMotif[1]);
        assertEquals(parcelleCouleurm11, tableauParcelleCouleurMotif[2]);
        assertEquals(parcelleCouleur02, tableauParcelleCouleurMotif[3]);
    }

    @Test
    void equalsSansOrientation() {
        ParcelleCouleur[] motifRessemblant = new ParcelleCouleur[4];
        motifRessemblant[0] = new ParcelleCouleur(new Position(-1, -3), couleurDefaut);
        motifRessemblant[1] = new ParcelleCouleur(new Position(1, -3), couleurDefaut);
        motifRessemblant[2] = new ParcelleCouleur(new Position(0, -2), couleurDefaut);
        motifRessemblant[3] = new ParcelleCouleur(new Position(2, -2), couleurDefaut);
        Motif motifPositionDifferent = new MotifLosange(motifRessemblant[0], motifRessemblant[1], motifRessemblant[2], motifRessemblant[3]);
        assertEquals(motifLosange, motifPositionDifferent);
    }
    @Test
    void equalsAvecOrientation() {
        ParcelleCouleur[][] allOrientation = motifLosange.getOrientation();
        for (ParcelleCouleur[] orientation : allOrientation) {
            Motif motifOrientation = new MotifLosange(orientation[0], orientation[1], orientation[2], orientation[3]);
            assertEquals(motifLosange, motifOrientation);
        }
    }
}