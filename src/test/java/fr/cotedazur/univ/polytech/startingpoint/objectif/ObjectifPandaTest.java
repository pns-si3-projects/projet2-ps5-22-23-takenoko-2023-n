package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifPandaTest {
    ObjectifPanda objP1_1;
    ObjectifPanda objP2_2;
    ObjectifPanda objP2_2_bis_vert;
    ObjectifPanda objP2_2_bis_jaune;
    ObjectifPanda objP3_2;
    ObjectifPanda objP2_3;

    @BeforeEach
    void setUp() {
        objP1_1 = new ObjectifPanda(1, 1,Couleur.ROSE);
        objP2_2 = new ObjectifPanda(2, 2, Couleur.VERT);
        objP2_2_bis_vert = new ObjectifPanda(2, 2,Couleur.VERT);
        objP2_2_bis_jaune = new ObjectifPanda(2,2,Couleur.ROSE);
        objP3_2 = new ObjectifPanda(3, 2,Couleur.JAUNE);
        objP2_3 = new ObjectifPanda(2, 3,Couleur.JAUNE);
    }

    @Test
    void getCouleurObjectifPanda(){
        assertEquals(Couleur.ROSE,objP1_1.getCouleurBambousAManger());
        assertNotEquals(Couleur.JAUNE,objP2_2.getCouleurBambousAManger());
        assertEquals(Couleur.VERT,objP2_2.getCouleurBambousAManger());
        assertEquals(Couleur.VERT,objP2_2_bis_vert.getCouleurBambousAManger());
        assertEquals(Couleur.JAUNE,objP3_2.getCouleurBambousAManger());
    }

    @Test
    void getNombreBambousAManger() {
        assertEquals(1, objP1_1.getNombreBambousAManger());
        assertEquals(2, objP2_2.getNombreBambousAManger());
        assertEquals(3, objP2_3.getNombreBambousAManger());
        assertNotEquals(3, objP3_2.getNombreBambousAManger());
    }

    @Test
    void testEquals() {
        assertEquals(objP2_2, objP2_2_bis_vert);
        assertNotEquals(objP2_2,objP2_2_bis_jaune);
        assertNotEquals(objP1_1, objP2_2_bis_vert);
        assertNotEquals(objP3_2, objP2_2);
        assertNotEquals(objP2_3, objP2_2);
    }
}