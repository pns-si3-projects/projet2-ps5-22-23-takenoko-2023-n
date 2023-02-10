package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelleCouleurTest {
    ParcelleCouleur pC1_1V;
    ParcelleCouleur pC1_1VBis;
    ParcelleCouleur pC1_1R;
    ParcelleCouleur pC1_1J;
    ParcelleCouleur pC2_0R;
    Position p1_1;
    Position p2_0;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        p1_1 = new Position(1,1);
        p2_0 = new Position(2,0);
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
        pC1_1V = new ParcelleCouleur(p1_1, verte);
        pC1_1VBis = new ParcelleCouleur(p1_1, verte);
        pC1_1R = new ParcelleCouleur(p1_1, rose);
        pC1_1J = new ParcelleCouleur(p1_1, jaune);
        pC2_0R = new ParcelleCouleur(p2_0, rose);
    }


    @Test
    void getPosition() {
        assertEquals(p1_1, pC1_1V.getPosition());
        assertEquals(p1_1, pC1_1R.getPosition());
        assertEquals(p1_1, pC1_1J.getPosition());
        assertEquals(p2_0, pC2_0R.getPosition());
    }

    @Test
    void getCouleur() {
        assertEquals(verte, pC1_1V.getCouleur());
        assertEquals(rose, pC1_1R.getCouleur());
        assertEquals(jaune, pC1_1J.getCouleur());
        assertEquals(rose, pC2_0R.getCouleur());
    }


    @Test
    void testToString() {
        assertEquals("Parcelle de couleur verte en (1,1)", pC1_1V.toString());
        assertEquals("Parcelle de couleur rose en (2,0)", pC2_0R.toString());
    }

    @Test
    void testEquals() {
        assertEquals(pC1_1V, pC1_1VBis);
        assertNotEquals(pC1_1V, pC1_1R);
        assertNotEquals(pC1_1V, pC1_1J);
        assertNotEquals(pC1_1R, pC1_1J);
        assertNotEquals(pC1_1V, pC2_0R);
    }
}