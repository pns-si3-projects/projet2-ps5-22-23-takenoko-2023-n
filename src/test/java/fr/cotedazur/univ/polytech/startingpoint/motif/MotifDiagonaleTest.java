package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotifDiagonaleTest {
    ParcelleCouleur parcelleCouleur00;
    ParcelleCouleur parcelleCouleur11;
    ParcelleCouleur parcelleCouleur22;

    ParcelleCouleur parcelleCouleurm9m7;
    ParcelleCouleur parcelleCouleurm8m6;
    ParcelleCouleur parcelleCouleurm10m8;
    Motif motifACree2Parcelle;
    Motif motifACree3Parcelle;
    Motif motifACree2ParcelleBis; // DifferentePosition
    Motif motifACree3ParcelleBis; // DifferentePosition
    Couleur couleurDefaut;

    @BeforeEach
    void setUp() {
        couleurDefaut = Couleur.VERTE;
        parcelleCouleur00 = new ParcelleCouleur(new Position(0,0), couleurDefaut);
        parcelleCouleur11 = new ParcelleCouleur(new Position(1,1), couleurDefaut);
        parcelleCouleur22 = new ParcelleCouleur(new Position(2,2), couleurDefaut);

        parcelleCouleurm9m7 = new ParcelleCouleur(new Position(-9,-7), couleurDefaut);
        parcelleCouleurm8m6 = new ParcelleCouleur(new Position(-8,-6), couleurDefaut);
        parcelleCouleurm10m8 = new ParcelleCouleur(new Position(-10,-8), couleurDefaut);

        motifACree2Parcelle = new MotifDiagonale(parcelleCouleur00,parcelleCouleur11);
        motifACree3Parcelle = new MotifDiagonale(parcelleCouleur00,parcelleCouleur11,parcelleCouleur22);
        motifACree2ParcelleBis = new MotifDiagonale(parcelleCouleurm9m7, parcelleCouleurm8m6);
        motifACree3ParcelleBis = new MotifDiagonale(parcelleCouleurm10m8, parcelleCouleurm9m7, parcelleCouleurm8m6);
    }

    @Test
    void getOrientationForIA2Parcelles() {
        ParcelleCouleur[][] allOrientation = motifACree2ParcelleBis.getOrientationForIA();
        ParcelleCouleur[] orientation = new ParcelleCouleur[2];
        orientation[0] = parcelleCouleurm9m7;
        orientation[1] = parcelleCouleurm8m6;
        assertArrayEquals(orientation, allOrientation[0]);

        orientation[1] =  new ParcelleCouleur(new Position(-7, -7), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[1]);

        orientation[1] =  new ParcelleCouleur(new Position(-8, -8), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[2]);

        orientation[1] =  new ParcelleCouleur(new Position(-10, -8), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[3]);

        orientation[1] =  new ParcelleCouleur(new Position(-11, -7), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[4]);

        orientation[1] =  new ParcelleCouleur(new Position(-10, -6), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[5]);
    }

    @Test
    void getOrientationForIA3Parcelles() {
        ParcelleCouleur[][] allOrientation = motifACree3Parcelle.getOrientationForIA();
        ParcelleCouleur[] orientation = new ParcelleCouleur[3];
        orientation[0] = parcelleCouleur00;
        orientation[1] = parcelleCouleur11;
        orientation[2] = parcelleCouleur22;
        assertArrayEquals(orientation, allOrientation[0]);

        orientation[1] =  new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(4, 0), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[1]);

        orientation[1] =  new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(2, -2), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[2]);

        orientation[1] =  new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-2, -2), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[3]);

        orientation[1] =  new ParcelleCouleur(new Position(-2, 0), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-4, 0), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[4]);

        orientation[1] =  new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-2, 2), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[5]);

        orientation[0] = parcelleCouleur11;
        orientation[1] = parcelleCouleur22;
        orientation[2] = parcelleCouleur00;
        assertArrayEquals(orientation, allOrientation[6]);

        orientation[1] =  new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[7]);

        orientation[1] =  new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(0, 2), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[8]);
    }

    @Test
    void getOrientation2Parcelles() {
        ParcelleCouleur[][] allOrientation = motifACree2Parcelle.getOrientation();
        ParcelleCouleur[] orientation = new ParcelleCouleur[2];
        orientation[0] = parcelleCouleur00;
        orientation[1] = parcelleCouleur11;
        assertArrayEquals(orientation, allOrientation[0]);

        orientation[1] =  new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[1]);

        orientation[1] =  new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[2]);

        orientation[1] =  new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[3]);

        orientation[1] =  new ParcelleCouleur(new Position(-2, 0), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[4]);

        orientation[1] =  new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[5]);
    }

    @Test
    void getOrientation3Parcelles() {
        ParcelleCouleur[][] allOrientation = motifACree3ParcelleBis.getOrientation();
        ParcelleCouleur[] orientation = new ParcelleCouleur[3];
        orientation[0] = parcelleCouleurm10m8;
        orientation[1] = parcelleCouleurm9m7;
        orientation[2] = parcelleCouleurm8m6;
        assertArrayEquals(orientation, allOrientation[0]);

        orientation[1] =  new ParcelleCouleur(new Position(-8, -8), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-6, -8), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[1]);

        orientation[1] =  new ParcelleCouleur(new Position(-9, -9), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-8, -10), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[2]);

        orientation[1] =  new ParcelleCouleur(new Position(-11, -9), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-12, -10), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[3]);

        orientation[1] =  new ParcelleCouleur(new Position(-12, -8), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-14, -8), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[4]);

        orientation[1] =  new ParcelleCouleur(new Position(-11, -7), couleurDefaut);
        orientation[2] =  new ParcelleCouleur(new Position(-12, -6), couleurDefaut);
        assertArrayEquals(orientation, allOrientation[5]);
    }

    @Test
    void compareMotif() {
        assertTrue(motifACree2Parcelle.compareMotif(motifACree2ParcelleBis));
        assertFalse(motifACree2Parcelle.compareMotif(motifACree3Parcelle));
        assertTrue(motifACree3Parcelle.compareMotif(motifACree3ParcelleBis));
        assertFalse(motifACree3Parcelle.compareMotif(motifACree2ParcelleBis));
    }

    @Test
    void getTableauParcelles() {
        ParcelleCouleur[] tableauParcelleMotif = motifACree2Parcelle.getTableauParcelles();
        assertEquals(2, tableauParcelleMotif.length);
        assertEquals(parcelleCouleur00, tableauParcelleMotif[0]);
        assertEquals(parcelleCouleur11, tableauParcelleMotif[1]);

        tableauParcelleMotif = motifACree3ParcelleBis.getTableauParcelles();
        assertEquals(3,tableauParcelleMotif.length);
        assertEquals(parcelleCouleurm10m8, tableauParcelleMotif[0]);
        assertEquals(parcelleCouleurm9m7, tableauParcelleMotif[1]);
        assertEquals(parcelleCouleurm8m6, tableauParcelleMotif[2]);
    }

    @Test
    void testEqualsSansOrientation() {
        assertEquals(motifACree2Parcelle, motifACree2ParcelleBis);
        assertNotEquals(motifACree2Parcelle, motifACree3ParcelleBis);
        assertEquals(motifACree3Parcelle, motifACree3ParcelleBis);
        assertNotEquals(motifACree3Parcelle, motifACree2Parcelle);
    }

    @Test
    void testEqualsAvecOrientation2Parcelles() {
        ParcelleCouleur[][] allOrientation = motifACree2ParcelleBis.getOrientation();
        Motif motif0011 = new MotifDiagonale(allOrientation[0]);
        assertEquals(motifACree2Parcelle, motif0011);

        Motif motif0020 = new MotifDiagonale(allOrientation[1]);
        assertEquals(motifACree2Parcelle, motif0020);

        Motif motif001m1 = new MotifDiagonale(allOrientation[2]);
        assertEquals(motifACree2Parcelle, motif001m1);

        Motif motif00m1m1 = new MotifDiagonale(allOrientation[3]);
        assertEquals(motifACree2Parcelle, motif00m1m1);

        Motif motif00m20 = new MotifDiagonale(allOrientation[4]);
        assertEquals(motifACree2Parcelle, motif00m20);

        Motif motif00m11 = new MotifDiagonale(allOrientation[5]);
        assertEquals(motifACree2Parcelle, motif00m11);
    }

    @Test
    void testEqualsAvecOrientation3Parcelles() {
        ParcelleCouleur[][] allOrientation = motifACree3Parcelle.getOrientation();
        Motif motif001122 = new MotifDiagonale(allOrientation[0]);
        assertEquals(motifACree3ParcelleBis, motif001122);

        Motif motif002040 = new MotifDiagonale(allOrientation[1]);
        assertEquals(motifACree3ParcelleBis, motif002040);

        Motif motif001m12m2 = new MotifDiagonale(allOrientation[2]);
        assertEquals(motifACree3ParcelleBis, motif001m12m2);

        Motif motif00m1m1m2m2 = new MotifDiagonale(allOrientation[3]);
        assertEquals(motifACree3ParcelleBis, motif00m1m1m2m2);

        Motif motif00m20m40 = new MotifDiagonale(allOrientation[4]);
        assertEquals(motifACree3ParcelleBis, motif00m20m40);

        Motif motif00m11m22 = new MotifDiagonale(allOrientation[5]);
        assertEquals(motifACree3ParcelleBis, motif00m11m22);
    }
}