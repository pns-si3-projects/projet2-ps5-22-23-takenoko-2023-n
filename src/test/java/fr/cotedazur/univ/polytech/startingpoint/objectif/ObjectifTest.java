package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifTest {
    Objectif objPar2_2;
    Objectif objPar3_2;
    Objectif objPar2_3;
    Objectif objPar2_2Bis;
    Objectif objPan2_3;
    Objectif objPan1_2;
    Objectif objJar2_1;

    @BeforeEach
    void setUp() {
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0,0), Couleur.ROSE);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1,1),Couleur.ROSE);
        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(new Position(2,2), Couleur.ROSE);
        Motif motif2Parcelle = null;
        Motif motif3Parcelle = null;
        try {
            motif2Parcelle = new Motif(parcelleCouleur00,parcelleCouleur11);
            motif3Parcelle = new Motif(parcelleCouleur00,parcelleCouleur11,parcelleCouleur22);
        }
        catch (MotifNonValideException mNVE){
            assert false: "Les parcelles sont voisines donc ne doit pas renvoyer d'exception";
        }
        objPar2_2 = new ObjectifParcelle(2, motif2Parcelle);
        objPar3_2 = new ObjectifParcelle(3, motif2Parcelle);
        objPar2_3 = new ObjectifParcelle(2, motif3Parcelle);
        objPar2_2Bis = new ObjectifParcelle(2,motif2Parcelle);
        objPan2_3 = new ObjectifPanda(2, 3, Couleur.VERT);
        objPan1_2 = new ObjectifPanda(1, 2,Couleur.VERT);
        objJar2_1 = new ObjectifJardinier(2, 1);
    }

    @Test
    void getNombrePoints() {
        assertEquals(3, objPar3_2.getNombrePoints());
        assertEquals(1, objPan1_2.getNombrePoints());
        assertEquals(2, objJar2_1.getNombrePoints());
        assertNotEquals(3, objPar2_3.getNombrePoints());
    }

    @Test
    void testEquals() {
        assertNotEquals(objPar2_2,objPar2_3);
        assertNotEquals(objPar2_2, objPan2_3);
        assertNotEquals(objPan2_3, objJar2_1);
        assertNotEquals(objPan1_2, objJar2_1);
        assertNotEquals(objPar2_2, objPar2_3);
        assertEquals(objPar2_2Bis,objPar2_2);
        assertNotEquals(objPar2_2,objPar3_2);
    }
}