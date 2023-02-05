package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotifDecaleTest {
    Motif motifDecale;
    ParcelleCouleur parcelleCouleur00;
    ParcelleCouleur parcelleCouleur11;
    ParcelleCouleur parcelleCouleur02;
    Couleur couleurDefaut;

    @BeforeEach
    void setUp() {
        couleurDefaut = Couleur.VERT;
        parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), couleurDefaut);
        parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), couleurDefaut);
        parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), couleurDefaut);
        motifDecale = new MotifDecale(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
    }

    @Test
    void getOrientation() {
        ParcelleCouleur[][] allOrientation = motifDecale.getOrientation();
        ParcelleCouleur[] orientation =  new ParcelleCouleur[3];
        orientation[0] = parcelleCouleur00;
        orientation[1] = parcelleCouleur11;
        orientation[2] = parcelleCouleur02;
        assertArrayEquals(orientation, allOrientation[0]);

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        orientation[1] = parcelleCouleur20;
        orientation[2] = parcelleCouleur31;
        assertArrayEquals(orientation, allOrientation[1]);

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        orientation[1] = parcelleCouleur1m1;
        orientation[2] = parcelleCouleur3m1;
        assertArrayEquals(orientation, allOrientation[2]);

        ParcelleCouleur parcelleCouleur0m2 = new ParcelleCouleur(new Position(0, -2), couleurDefaut);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        orientation[1] = parcelleCouleurm1m1;
        orientation[2] = parcelleCouleur0m2;
        assertArrayEquals(orientation, allOrientation[3]);

        ParcelleCouleur parcelleCouleurm3m1 = new ParcelleCouleur(new Position(-3, -1), couleurDefaut);
        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(new Position(-2, 0), couleurDefaut);
        orientation[1] = parcelleCouleurm20;
        orientation[2] = parcelleCouleurm3m1;
        assertArrayEquals(orientation, allOrientation[4]);

        ParcelleCouleur parcelleCouleurm31 = new ParcelleCouleur(new Position(-3, 1), couleurDefaut);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        orientation[1] = parcelleCouleurm11;
        orientation[2] = parcelleCouleurm31;
        assertArrayEquals(orientation, allOrientation[5]);

        orientation[1] = parcelleCouleurm11;
        orientation[2] = parcelleCouleur02;
        assertArrayEquals(orientation, allOrientation[6]);

        orientation[1] = parcelleCouleur11;
        orientation[2] = parcelleCouleur31;
        assertArrayEquals(orientation, allOrientation[7]);

        orientation[1] = parcelleCouleur20;
        orientation[2] = parcelleCouleur3m1;
        assertArrayEquals(orientation, allOrientation[8]);

        orientation[1] = parcelleCouleur1m1;
        orientation[2] = parcelleCouleur0m2;
        assertArrayEquals(orientation, allOrientation[9]);

        orientation[1] = parcelleCouleurm1m1;
        orientation[2] = parcelleCouleurm3m1;
        assertArrayEquals(orientation, allOrientation[10]);

        orientation[1] = parcelleCouleurm20;
        orientation[2] = parcelleCouleurm31;
        assertArrayEquals(orientation, allOrientation[11]);
    }

    @Test
    void getOrientationForIA() {
        ParcelleCouleur[][] allOrientation = motifDecale.getOrientationForIA();
        ParcelleCouleur[] orientation =  new ParcelleCouleur[3];
        orientation[0] = parcelleCouleur11;
        orientation[1] = parcelleCouleur00;
        orientation[2] = parcelleCouleur02;
        assertArrayEquals(orientation, allOrientation[0]);

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        orientation[0] = parcelleCouleur20;
        orientation[2] = parcelleCouleur31;
        assertArrayEquals(orientation, allOrientation[1]);

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        orientation[0] = parcelleCouleur1m1;
        orientation[2] = parcelleCouleur3m1;
        assertArrayEquals(orientation, allOrientation[2]);

        ParcelleCouleur parcelleCouleur0m2 = new ParcelleCouleur(new Position(0, -2), couleurDefaut);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        orientation[0] = parcelleCouleurm1m1;
        orientation[2] = parcelleCouleur0m2;
        assertArrayEquals(orientation, allOrientation[3]);

        ParcelleCouleur parcelleCouleurm3m1 = new ParcelleCouleur(new Position(-3, -1), couleurDefaut);
        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(new Position(-2, 0), couleurDefaut);
        orientation[0] = parcelleCouleurm20;
        orientation[2] = parcelleCouleurm3m1;
        assertArrayEquals(orientation, allOrientation[4]);

        ParcelleCouleur parcelleCouleurm31 = new ParcelleCouleur(new Position(-3, 1), couleurDefaut);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        orientation[0] = parcelleCouleurm11;
        orientation[2] = parcelleCouleurm31;
        assertArrayEquals(orientation, allOrientation[5]);

        orientation[0] = parcelleCouleurm11;
        orientation[2] = parcelleCouleur02;
        assertArrayEquals(orientation, allOrientation[6]);

        orientation[0] = parcelleCouleur11;
        orientation[2] = parcelleCouleur31;
        assertArrayEquals(orientation, allOrientation[7]);

        orientation[0] = parcelleCouleur20;
        orientation[2] = parcelleCouleur3m1;
        assertArrayEquals(orientation, allOrientation[8]);

        orientation[0] = parcelleCouleur1m1;
        orientation[2] = parcelleCouleur0m2;
        assertArrayEquals(orientation, allOrientation[9]);

        orientation[0] = parcelleCouleurm1m1;
        orientation[2] = parcelleCouleurm3m1;
        assertArrayEquals(orientation, allOrientation[10]);

        orientation[0] = parcelleCouleurm20;
        orientation[2] = parcelleCouleurm31;
        assertArrayEquals(orientation, allOrientation[11]);
    }

    @Test
    void getTableau(){
        ParcelleCouleur[] tableauParcelleCouleurMotif = motifDecale.getTableauParcelles();
        assertEquals(parcelleCouleur00, tableauParcelleCouleurMotif[0]);
        assertEquals(parcelleCouleur11, tableauParcelleCouleurMotif[1]);
        assertEquals(parcelleCouleur02, tableauParcelleCouleurMotif[2]);
    }

    @Test
    void compareMotif() {
        ParcelleCouleur parcelleCouleur55 = new ParcelleCouleur(new Position(5, 5), couleurDefaut);
        ParcelleCouleur parcelleCouleur64 = new ParcelleCouleur(new Position(6, 4), couleurDefaut);
        ParcelleCouleur parcelleCouleur84 = new ParcelleCouleur(new Position(8, 4), couleurDefaut);
        Motif motifRessemblant556484 = new MotifDecale(parcelleCouleur55, parcelleCouleur64, parcelleCouleur84);
        assertTrue(motifDecale.compareMotif(motifRessemblant556484));

        ParcelleCouleur parcelleCouleur73 = new ParcelleCouleur(new Position(7, 3), couleurDefaut);
        Motif motifDiagonale = new MotifDecale(parcelleCouleur55, parcelleCouleur64, parcelleCouleur73);
        assertFalse(motifDecale.compareMotif(motifDiagonale));
    }

    @Test
    void equalsSansOrientation() {
        ParcelleCouleur parcelleCouleur3m3 = new ParcelleCouleur(new Position(3, -3), couleurDefaut);
        ParcelleCouleur parcelleCouleur4m2 = new ParcelleCouleur(new Position(4, -2), couleurDefaut);
        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        MotifDecale motifDecaleDifferentePosition = new MotifDecale(parcelleCouleur3m3, parcelleCouleur4m2, parcelleCouleur3m1);
        assertEquals(motifDecale, motifDecaleDifferentePosition);
    }

    @Test
    void equalsAvecOrientation() {
        ParcelleCouleur[][] allOrientation = motifDecale.getOrientation();

        for (ParcelleCouleur[] orientation : allOrientation) {
            Motif motifRessemblantDecale = new MotifDecale(orientation[0], orientation[1], orientation[2]);
            assertEquals(motifDecale, motifRessemblantDecale);
        }
    }
}