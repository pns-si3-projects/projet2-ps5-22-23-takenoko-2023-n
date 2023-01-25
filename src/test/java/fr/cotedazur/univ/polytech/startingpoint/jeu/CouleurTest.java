package fr.cotedazur.univ.polytech.startingpoint.jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouleurTest {
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
    }


    @Test
    void getCouleur() {
        assertEquals("verte", verte.getCouleur());
        assertEquals("rose", rose.getCouleur());
        assertEquals("jaune", jaune.getCouleur());
    }

    @Test
    void isVerte() {
        assertTrue(verte.isVerte());
        assertFalse(rose.isVerte());
        assertFalse(jaune.isVerte());
    }

    @Test
    void isRose() {
        assertFalse(verte.isRose());
        assertTrue(rose.isRose());
        assertFalse(jaune.isRose());
    }

    @Test
    void isJaune() {
        assertFalse(verte.isJaune());
        assertFalse(rose.isJaune());
        assertTrue(jaune.isJaune());
    }


    @Test
    void testToString() {
        assertEquals("verte", verte.toString());
        assertEquals("rose", rose.toString());
        assertEquals("jaune", jaune.toString());
    }
}