package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelleCouleurTest {
    Couleur vert = Couleur.VERT;
    Couleur rose = Couleur.ROSE;
    Couleur jaune = Couleur.JAUNE;
    ParcelleCouleur pC1_1V;
    ParcelleCouleur pC1_1VBis;
    ParcelleCouleur pC1_1R;
    ParcelleCouleur pC1_1J;
    ParcelleCouleur pC2_1R;
    Position p1_1;
    Position p2_1;

    @BeforeEach
    void setUp() {
        p1_1 = new Position(1,1);
        p2_1 = new Position(2,1);
        pC1_1V = new ParcelleCouleur(p1_1, vert);
        pC1_1VBis = new ParcelleCouleur(p1_1, vert);
        pC1_1R = new ParcelleCouleur(p1_1, rose);
        pC1_1J = new ParcelleCouleur(p1_1, jaune);
        pC2_1R = new ParcelleCouleur(p2_1, rose);
    }

    @Test
    void position() {
        assertEquals(p1_1, pC1_1V.position());
        assertEquals(p1_1, pC1_1VBis.position());
        assertEquals(pC1_1V.position(), pC1_1VBis.position());
        assertEquals(p2_1, pC2_1R.position());
    }

    @Test
    void couleur() {
        assertEquals(vert, pC1_1V.couleur());
        assertEquals(vert, pC1_1VBis.couleur());
        assertEquals(pC1_1V.couleur(), pC1_1VBis.couleur());
        assertEquals(rose, pC1_1R.couleur());
        assertEquals(jaune, pC1_1J.couleur());
        assertEquals(rose, pC2_1R.couleur());
    }

    @Test
    void testEquals() {
        assertEquals(pC1_1V, pC1_1VBis);
        assertNotEquals(pC1_1V, pC1_1R);
        assertNotEquals(pC1_1V, pC1_1J);
        assertNotEquals(pC1_1R, pC1_1J);
        assertNotEquals(pC1_1V, pC2_1R);
        assertNotEquals(pC1_1VBis, pC2_1R);
    }
}