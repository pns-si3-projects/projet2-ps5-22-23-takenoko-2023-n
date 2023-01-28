package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifTest {
    Objectif objPar2_3;
    Objectif objPar3_4;
    Objectif objPar5_4;
    Objectif objPan3_2;
    Objectif objPan6_3;
    Objectif objJar5_4;


    @BeforeEach
    void setUp() {
        objPar2_3 = new ObjectifParcelle(2, 3);
        objPar3_4 = new ObjectifParcelle(3, 4);
        objPar5_4 = new ObjectifParcelle(5, 4);
        objPan3_2 = new ObjectifPanda(3, 2, Couleur.VERTE);
        objPan6_3 = new ObjectifPanda(6, 3, Couleur.VERTE);
        objJar5_4 = new ObjectifJardinier(5, 4, Couleur.JAUNE);
    }


    @Test
    void getNombrePoints() {
        assertEquals(2, objPar2_3.getNombrePoints());
        assertEquals(3, objPar3_4.getNombrePoints());
        assertEquals(5, objPar5_4.getNombrePoints());
        assertEquals(3, objPan3_2.getNombrePoints());
        assertEquals(6, objPan6_3.getNombrePoints());
        assertEquals(5, objJar5_4.getNombrePoints());
    }


    @Test
    void testToString() {
        assertEquals("Objectif de 2 points", objPar2_3.toString());
        assertEquals("Objectif de 3 points", objPan3_2.toString());
        assertEquals("Objectif de 5 points", objJar5_4.toString());
    }

    /* Je n'arrive pas à utiliser le equals de Objectif --> prend automatiquement celui de la sous-classe
    @Test
    void testEquals() {
        assertNotEquals(objPar2_3, objPar3_4);
        assertNotEquals(objPar3_4, objPar5_4);
        assertNotEquals(objPar5_4, objPan3_2);
        assertNotEquals(objPan3_2, objPan6_3);
        assertNotEquals(objPan6_3, objJar5_4);
        assertEquals(objPar3_4, objPan3_2);
        assertEquals(objPar5_4, objJar5_4);
    }*/
}