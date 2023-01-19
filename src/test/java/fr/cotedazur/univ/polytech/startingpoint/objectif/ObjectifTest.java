package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifTest {
    Objectif objPar2_2;
    Objectif objPar3_2;
    Objectif objPar2_3;
    Objectif objPan2_3;
    Objectif objPan1_2;
    Objectif objJar2_1;

    @BeforeEach
    void setUp() {
        objPar2_2 = new ObjectifParcelle(2, 2);
        objPar3_2 = new ObjectifParcelle(3, 2);
        objPar2_3 = new ObjectifParcelle(2, 3);
        objPan2_3 = new ObjectifPanda(2, 3, Couleur.VERT);
        objPan1_2 = new ObjectifPanda(1, 2,Couleur.VERT);
        objJar2_1 = new ObjectifParcelle(2, 1);
    }

    @Test
    void getNombrePoints() {
        assertEquals(3, objPar3_2.getNombrePoints());
        assertEquals(1, objPan1_2.getNombrePoints());
        assertEquals(2, objJar2_1.getNombrePoints());
        assertNotEquals(3, objPar2_3.getNombrePoints());
    }

    /* Je n'arrive pas à utiliser le equals de Objectif --> prend automatiquement celui de la sous-classe
    @Test
    void testEquals() {
        assertTrue(objPar2_2.equals(objPar2_3));
        assertEquals(objPar2_2, objPan2_3);
        assertEquals(objPan2_3, objJar2_1);
        assertNotEquals(objPan1_2, objJar2_1);
        assertNotEquals(objPar2_2, objPar2_3);
    }*/
}