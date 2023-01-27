package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifPandaTest {
    ObjectifPanda objP3_2;
    ObjectifPanda objP3_2Bis;
    ObjectifPanda objP4_2;
    ObjectifPanda objP5_2;
    ObjectifPanda objP6_3;
    Couleur verte;
    Couleur rose;
    Couleur jaune;

    @BeforeEach
    void setUp() {
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
        objP3_2 = new ObjectifPanda(3, 2, verte);
        objP3_2Bis = new ObjectifPanda(3, 2, verte);
        objP4_2 = new ObjectifPanda(4, 2, jaune);
        objP5_2 = new ObjectifPanda(5, 2, rose);
        objP6_3 = new ObjectifPanda(6, 3, verte);
    }

    @Test
    void getNombreBambousAManger() {
        assertEquals(2, objP3_2.getNombreBambousAManger());
        assertEquals(2, objP4_2.getNombreBambousAManger());
        assertEquals(2, objP5_2.getNombreBambousAManger());
        assertEquals(3, objP6_3.getNombreBambousAManger());
    }

    @Test
    void getCouleur(){
        assertEquals(verte, objP3_2.getCouleur());
        assertEquals(jaune, objP4_2.getCouleur());
        assertEquals(rose, objP5_2.getCouleur());
        assertEquals(verte, objP6_3.getCouleur());
    }

    @Test
    void testEquals() {
        assertEquals(objP3_2, objP3_2Bis);
        assertNotEquals(objP3_2, objP4_2);
        assertNotEquals(objP4_2, objP5_2);
        assertNotEquals(objP5_2, objP6_3);
        assertNotEquals(objP3_2, objP6_3);
    }
}