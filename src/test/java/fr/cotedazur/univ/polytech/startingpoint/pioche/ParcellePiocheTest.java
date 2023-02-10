package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ParcellePiocheTest {
    ParcellePioche parcellePiocheV;
    ParcellePioche parcellePiocheVBis;
    ParcellePioche parcellePiocheR;
    ParcellePioche parcellePiocheJ;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
        parcellePiocheV = new ParcellePioche(verte);
        parcellePiocheVBis = new ParcellePioche(verte);
        parcellePiocheR = new ParcellePioche(rose);
        parcellePiocheJ = new ParcellePioche(jaune);
    }


    @Test
    void getCouleur() {
        assertEquals(verte, parcellePiocheV.getCouleur());
        assertEquals(rose, parcellePiocheR.getCouleur());
        assertEquals(jaune, parcellePiocheJ.getCouleur());
    }


    @Test
    void testToString() {
        assertEquals("Parcelle verte dans la pioche", parcellePiocheV.toString());
        assertEquals("Parcelle rose dans la pioche", parcellePiocheR.toString());
        assertEquals("Parcelle jaune dans la pioche", parcellePiocheJ.toString());
    }

    @Test
    void testEquals() {
        assertEquals(parcellePiocheV, parcellePiocheVBis);
        assertNotEquals(parcellePiocheV, parcellePiocheR);
        assertNotEquals(parcellePiocheR, parcellePiocheJ);
    }
}