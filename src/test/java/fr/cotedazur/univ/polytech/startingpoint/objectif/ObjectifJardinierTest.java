package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ObjectifJardinierTest {
    ObjectifJardinier objJ5_4_J;
    ObjectifJardinier objJ5_4_JBis;
    ObjectifJardinier objJ5_4_V;
    ObjectifJardinier objJ6_4_R;
    ObjectifJardinier objJ6_6_R;
    Couleur verte;
    Couleur rose;
    Couleur jaune;

    @BeforeEach
    void setUp() {
        objJ5_4_J = new ObjectifJardinier(5, 4, jaune);
        objJ5_4_JBis = new ObjectifJardinier(5, 4, jaune);
        objJ5_4_V = new ObjectifJardinier(5, 4, verte);
        objJ6_4_R = new ObjectifJardinier(6, 4, rose);
        objJ6_6_R = new ObjectifJardinier(6, 6, rose);
    }

    @Test
    void getNombreBambousAFairePousser() {
        assertEquals(4, objJ5_4_J.getSchema());
        assertEquals(4, objJ5_4_V.getSchema());
        assertEquals(4, objJ6_4_R.getSchema());
        assertEquals(6, objJ6_6_R.getSchema());
    }

    @Test
    void getCouleur() {
        assertEquals(jaune, objJ5_4_J.getCouleur());
        assertEquals(verte, objJ5_4_V.getCouleur());
        assertEquals(rose, objJ6_4_R.getCouleur());
        assertEquals(rose, objJ6_6_R.getCouleur());
    }

    @Test
    void testEquals() {
        assertEquals(objJ5_4_J, objJ5_4_JBis);
        assertNotEquals(objJ5_4_J, objJ5_4_V);
        assertNotEquals(objJ5_4_V, objJ6_4_R);
        assertNotEquals(objJ6_4_R, objJ6_6_R);
    }
}