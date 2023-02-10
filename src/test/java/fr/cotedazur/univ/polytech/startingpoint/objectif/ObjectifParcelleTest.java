package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ObjectifParcelleTest {
    ObjectifParcelle objP2_Tr;
    ObjectifParcelle objP2_TrBis;
    ObjectifParcelle objP3_D;
    ObjectifParcelle objP4_V;
    ObjectifParcelle objP5_L;
    Motif motifD;
    Motif motifL;
    Motif motifTr;
    Motif motifV;
    Couleur couleurDefaut;

    @BeforeEach
    void setUp() {
        couleurDefaut = Couleur.VERTE;
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), couleurDefaut);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), couleurDefaut);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), couleurDefaut);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), couleurDefaut);
        motifD = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        motifL = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        motifTr = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        motifV = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);

        objP2_Tr = new ObjectifParcelle(2, motifTr);
        objP2_TrBis = new ObjectifParcelle(2, motifTr);
        objP3_D = new ObjectifParcelle(3, motifD);
        objP4_V = new ObjectifParcelle(4, motifV);
        objP5_L = new ObjectifParcelle(5, motifL);
    }


    @Test
    void getSchema() {
        assertEquals(motifTr, objP2_Tr.getSchema());
        assertEquals(motifD, objP3_D.getSchema());
        assertEquals(motifV, objP4_V.getSchema());
        assertEquals(motifL, objP5_L.getSchema());
    }


    @Test
    void testToString() {
        ParcelleCouleur[] tabParMotifTr = objP2_Tr.getSchema().getTableauParcelles();
        assertEquals("Objectif de parcelle de 2 points", objP2_Tr.toString());

        ParcelleCouleur[] tabParMotifD = objP3_D.getSchema().getTableauParcelles();
        assertEquals("Objectif de parcelle de 3 points",  objP3_D.toString());
    }

    @Test
    void testEquals() {
        assertEquals(objP2_Tr, objP2_TrBis);
        assertNotEquals(objP2_Tr, objP3_D);
        assertNotEquals(objP3_D, objP4_V);
        assertNotEquals(objP2_Tr, objP4_V);
    }
}