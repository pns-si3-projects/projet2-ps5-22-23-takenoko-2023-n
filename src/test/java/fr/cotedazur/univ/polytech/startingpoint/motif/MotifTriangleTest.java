package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotifTriangleTest {
    MotifTriangle motifTriangle;
    ParcelleCouleur parcelleCouleur00;
    ParcelleCouleur parcelleCouleur20;
    ParcelleCouleur parcelleCouleur11;
    Couleur couleurDefaut;
    @BeforeEach
    void setUp() {
        couleurDefaut = Couleur.VERTE;
        parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), couleurDefaut);
        parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), couleurDefaut);
        motifTriangle = new MotifTriangle(parcelleCouleur00, parcelleCouleur20, parcelleCouleur11);
    }

    @Test
    void getOrientation() {
        ParcelleCouleur[][] allOrientation = motifTriangle.getOrientation();
        ParcelleCouleur[] motifTriangleRessemblant = new ParcelleCouleur[3];
        motifTriangleRessemblant[0] = parcelleCouleur00;
        motifTriangleRessemblant[1] = parcelleCouleur20;
        motifTriangleRessemblant[2] = parcelleCouleur11;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[0]);

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleur1m1;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[1]);

        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleurm1m1;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[2]);

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(new Position(-2, 0), couleurDefaut);
        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleurm20;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[3]);

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleurm11;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[4]);

        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleur11;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[5]);
    }

    @Test
    void getOrientationForIA() {
        ParcelleCouleur[][] allOrientation = motifTriangle.getOrientationForIA();
        ParcelleCouleur[] motifTriangleRessemblant = new ParcelleCouleur[3];
        motifTriangleRessemblant[0] = parcelleCouleur00;
        motifTriangleRessemblant[1] = parcelleCouleur20;
        motifTriangleRessemblant[2] = parcelleCouleur11;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[0]);

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleur1m1;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[1]);

        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleurm1m1;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[2]);

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(new Position(-2, 0), couleurDefaut);
        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleurm20;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[3]);

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleurm11;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[4]);

        motifTriangleRessemblant[2] = motifTriangleRessemblant[1];
        motifTriangleRessemblant[1] = parcelleCouleur11;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[5]);

        motifTriangleRessemblant[1] = parcelleCouleur11;
        motifTriangleRessemblant[2] = parcelleCouleur20;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[6]);

        motifTriangleRessemblant[1] = motifTriangleRessemblant[2];
        motifTriangleRessemblant[2] = parcelleCouleur1m1;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[7]);

        motifTriangleRessemblant[1] = motifTriangleRessemblant[2];
        motifTriangleRessemblant[2] = parcelleCouleurm1m1;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[8]);

        motifTriangleRessemblant[1] = motifTriangleRessemblant[2];
        motifTriangleRessemblant[2] = parcelleCouleurm20;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[9]);

        motifTriangleRessemblant[1] = motifTriangleRessemblant[2];
        motifTriangleRessemblant[2] = parcelleCouleurm11;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[10]);

        motifTriangleRessemblant[1] = motifTriangleRessemblant[2];
        motifTriangleRessemblant[2] = parcelleCouleur11;
        assertArrayEquals(motifTriangleRessemblant, allOrientation[11]);
    }

    @Test
    void compareMotif() {
        ParcelleCouleur parcelleCouleur2m2 = new ParcelleCouleur(new Position(2, -2), couleurDefaut);
        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        Motif motifTriangleRessemblant = new MotifTriangle(parcelleCouleur2m2, parcelleCouleur3m1, parcelleCouleur1m1);
        assertEquals(motifTriangle, motifTriangleRessemblant);
    }

    @Test
    void getTableauParcelles() {
        ParcelleCouleur[] tableauParcelles = motifTriangle.getTableauParcelles();
        assertEquals(parcelleCouleur00, tableauParcelles[0]);
        assertEquals(parcelleCouleur20, tableauParcelles[1]);
        assertEquals(parcelleCouleur11, tableauParcelles[2]);
    }

    @Test
    void equalsSansOrientation() {
        ParcelleCouleur parcelleCouleurm1m3 = new ParcelleCouleur(new Position(-1, -3), couleurDefaut);
        ParcelleCouleur parcelleCouleur1m3 = new ParcelleCouleur(new Position(1, -3), couleurDefaut);
        ParcelleCouleur parcelleCouleur0m2 = new ParcelleCouleur(new Position(0, -2), couleurDefaut);
        MotifTriangle motifTriangleDifferentePosition = new MotifTriangle(parcelleCouleurm1m3, parcelleCouleur1m3, parcelleCouleur0m2);
        assertEquals(motifTriangle, motifTriangleDifferentePosition);
    }

    @Test
    void equalsAvecOrientation() {
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), couleurDefaut);
        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(new Position(2, 2), couleurDefaut);
        ParcelleCouleur parcelleCouleur13 = new ParcelleCouleur(new Position(1, 3), couleurDefaut);
        MotifTriangle motifTriangleDifferentePosition = new MotifTriangle(parcelleCouleur02, parcelleCouleur22, parcelleCouleur13);
        ParcelleCouleur[][] tableauMotifTriangleDifferentePosition = motifTriangleDifferentePosition.getOrientation();

        for (int i = 0; i < 6; i++) {
            motifTriangleDifferentePosition = new MotifTriangle(tableauMotifTriangleDifferentePosition[i][0], tableauMotifTriangleDifferentePosition[i][1], tableauMotifTriangleDifferentePosition[i][2]);
            assertEquals(motifTriangle, motifTriangleDifferentePosition);
        }
    }
}