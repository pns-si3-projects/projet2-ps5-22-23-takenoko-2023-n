package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ObjectifParcelleTest {
    ObjectifParcelle objP1_2;
    ObjectifParcelle objP2_2;
    ObjectifParcelle objP2_2_bis;
    ObjectifParcelle objP3_2;
    ObjectifParcelle objP2_3;
    ObjectifParcelle objP3_3;
    ObjectifParcelle objP3_3_bis;
    Motif motif2Parcelle = null;
    Motif motif3Parcelle = null;

    @BeforeEach
    void setUp() {
        try {
            ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0,0), Couleur.ROSE);
            ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1,1),Couleur.ROSE);
            ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(new Position(2,2),Couleur.ROSE);
            motif2Parcelle = new Motif(parcelleCouleur00,parcelleCouleur11);
            motif3Parcelle = new Motif(parcelleCouleur00,parcelleCouleur11,parcelleCouleur22);
        }
        catch (MotifNonValideException mNVE){
            assert false : "Ne doit pas renvoyer d'exception car les parcelles sont voisines";
        }
        objP1_2 = new ObjectifParcelle(1, motif2Parcelle);
        objP2_2 = new ObjectifParcelle(2, motif2Parcelle);
        objP2_2_bis = new ObjectifParcelle(2, motif2Parcelle);
        objP3_2 = new ObjectifParcelle(3, motif2Parcelle);
        objP2_3 = new ObjectifParcelle(2, motif3Parcelle);
        objP3_3 = new ObjectifParcelle(3,motif3Parcelle);
        objP3_3_bis = new ObjectifParcelle(3,motif3Parcelle);
    }

    @Test
    void testGetMotif(){
        assertEquals(motif2Parcelle,objP1_2.getMotif());
        assertEquals(motif2Parcelle,objP2_2.getMotif());
        assertEquals(motif2Parcelle,objP2_2_bis.getMotif());
        assertNotEquals(motif3Parcelle,objP3_2.getMotif());
        assertEquals(motif2Parcelle,objP3_2.getMotif());
        assertNotEquals(motif2Parcelle,objP2_3.getMotif());
        assertEquals(motif3Parcelle,objP2_3.getMotif());
        assertEquals(motif3Parcelle,objP3_3.getMotif());
        assertEquals(motif3Parcelle,objP3_3_bis.getMotif());
    }

    @Test
    void testGetPoints(){
        assertEquals(1,objP1_2.getNombrePoints());
        assertEquals(2,objP2_2.getNombrePoints());
        assertEquals(2,objP2_2_bis.getNombrePoints());
        assertNotEquals(2,objP3_2.getNombrePoints());
        assertEquals(3,objP3_2.getNombrePoints());
        assertNotEquals(3,objP2_3.getNombrePoints());
        assertEquals(2,objP2_3.getNombrePoints());
        assertEquals(3,objP3_3.getNombrePoints());
        assertEquals(3,objP3_3_bis.getNombrePoints());
    }
    @Test
    void testEquals() {
        assertNotEquals(objP1_2,objP2_2);
        assertEquals(objP2_2,objP2_2_bis);
        assertNotEquals(objP2_2,objP3_2);
        assertEquals(objP3_3,objP3_3_bis);
        assertNotEquals(objP1_2,objP3_3);
        assertNotEquals(objP2_2_bis,objP3_3);
    }
}