package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotifTest {
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

    @BeforeEach
    void setUp(){
        parcelleCouleur00 = new ParcelleCouleur(new Position(0,0), Couleur.VERT);
        parcelleCouleur11 = new ParcelleCouleur(new Position(1,1), Couleur.VERT);
        parcelleCouleur22 = new ParcelleCouleur(new Position(2,2),Couleur.VERT);

        parcelleCouleurm9m7 = new ParcelleCouleur(new Position(-9,-7),Couleur.VERT);
        parcelleCouleurm8m6 = new ParcelleCouleur(new Position(-8,-6),Couleur.VERT);
        parcelleCouleurm10m8 = new ParcelleCouleur(new Position(-10,-8),Couleur.VERT);

        try {
            motifACree2Parcelle = new Motif(parcelleCouleur00,parcelleCouleur11);
            motifACree3Parcelle = new Motif(parcelleCouleur00,parcelleCouleur11,parcelleCouleur22);
            motifACree2ParcelleBis = new Motif(parcelleCouleurm9m7,parcelleCouleurm8m6);
            motifACree3ParcelleBis = new Motif(parcelleCouleurm9m7,parcelleCouleurm10m8,parcelleCouleurm8m6);
        }
        catch (MotifNonValideException mNVE){
            assert false: "Chaque Motif doit être voisin";
        }
    }

    @Test
    void constructorErrorNonVoisin(){
        assertThrows(MotifNonValideException.class, () -> new Motif(parcelleCouleur00, parcelleCouleur22));
        assertThrows(MotifNonValideException.class, () -> new Motif(parcelleCouleur00,parcelleCouleur11,parcelleCouleurm10m8));
    }

    @Test
    void constructorErrorTropDeVoisin(){
        assertThrows(IllegalArgumentException.class, () -> new Motif(parcelleCouleur00,parcelleCouleur11,parcelleCouleur22,parcelleCouleurm8m6, parcelleCouleurm9m7));
        assertThrows(MotifNonValideException.class, () -> new Motif(parcelleCouleur00,parcelleCouleur00));
    }

    @Test
    void constructorErrorPasAssezVoisin(){
        assertThrows(IllegalArgumentException.class, Motif::new);
        assertThrows(IllegalArgumentException.class, () -> new Motif(parcelleCouleur00));
    }

    @Test
    void checkMotifsParcelle() {
        assertTrue(motifACree2Parcelle.checkMotifsParcelle());
        assertTrue(motifACree2ParcelleBis.checkMotifsParcelle());
        assertTrue(motifACree3Parcelle.checkMotifsParcelle());
        assertTrue(motifACree3ParcelleBis.checkMotifsParcelle());
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
    void testEquals() {
        assertEquals(motifACree2Parcelle, motifACree2ParcelleBis);
        assertNotEquals(motifACree2Parcelle, motifACree3ParcelleBis);
        assertEquals(motifACree3Parcelle, motifACree3ParcelleBis);
        assertNotEquals(motifACree3Parcelle, motifACree2Parcelle);
    }
}