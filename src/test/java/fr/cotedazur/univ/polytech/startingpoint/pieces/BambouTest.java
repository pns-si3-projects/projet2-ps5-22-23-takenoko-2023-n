package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BambouTest {
    Bambou bambou1_1V;
    Bambou bambou1_1VBis;
    Bambou bambou2_0R;
    ParcelleCouleur pC1_1V;
    ParcelleCouleur pC2_0R;
    Position position1_1;
    Position position2_0;
    SectionBambou sectionBambouVerte;
    SectionBambou sectionBambouRose;
    SectionBambou sectionBambouJaune;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
        sectionBambouVerte = new SectionBambou(verte);
        sectionBambouRose = new SectionBambou(rose);
        sectionBambouJaune = new SectionBambou(jaune);
        position1_1 = new Position(1, 1);
        position2_0 = new Position(2, 0);
        pC1_1V = new ParcelleCouleur(position1_1, verte);
        pC2_0R = new ParcelleCouleur(position2_0, rose);
        bambou1_1V = new Bambou(pC1_1V);
        bambou1_1VBis = new Bambou(pC1_1V);
        bambou2_0R = new Bambou(pC2_0R);
    }


    @Test
    void getPosition() {
        assertEquals(position1_1, bambou1_1V.getPosition());
        assertEquals(position2_0, bambou2_0R.getPosition());
    }

    @Test
    void getCouleur() {
        assertEquals(verte, bambou1_1V.getCouleur());
        assertEquals(rose, bambou2_0R.getCouleur());
    }

    @Test
    void getTailleBambou() {
        assertEquals(0, bambou1_1V.getTailleBambou());
        assertEquals(0, bambou2_0R.getTailleBambou());
    }

    @Test
    void isEmptyBambou() {
        assertTrue(bambou1_1V.isEmpty());
        assertTrue(bambou2_0R.isEmpty());
    }


    @Test
    void isTailleMaximum() {
        assertFalse(bambou1_1V.isTailleMaximum());
        assertFalse(bambou2_0R.isTailleMaximum());
    }

    @Test
    void prendSectionBambou() {
        try {
            bambou1_1V.ajouteSectionBambou(sectionBambouVerte);
            bambou1_1V.ajouteSectionBambou(sectionBambouVerte);
            bambou2_0R.ajouteSectionBambou(sectionBambouRose);
        }
        catch (AjoutCouleurException e) {
            throw new AssertionError(e);
        }

        assertEquals(sectionBambouVerte, bambou1_1V.prendSectionBambou());
        assertEquals(sectionBambouVerte, bambou1_1V.prendSectionBambou());
        assertEquals(sectionBambouRose, bambou2_0R.prendSectionBambou());
    }

    @Test
    void ajouteSectionBambouAvecException() {
        assertThrows(AjoutCouleurException.class, () -> bambou1_1V.ajouteSectionBambou(sectionBambouRose));
        assertThrows(AjoutCouleurException.class, () -> bambou1_1V.ajouteSectionBambou(sectionBambouJaune));
        assertThrows(AjoutCouleurException.class, () -> bambou2_0R.ajouteSectionBambou(sectionBambouVerte));
        assertThrows(AjoutCouleurException.class, () -> bambou2_0R.ajouteSectionBambou(sectionBambouJaune));
    }

    @Test
    void ajouteSectionBambouSansException() {
        try {
            bambou1_1V.ajouteSectionBambou(sectionBambouVerte);
            bambou1_1V.ajouteSectionBambou(sectionBambouVerte);
            bambou2_0R.ajouteSectionBambou(sectionBambouRose);
        }
        catch (AjoutCouleurException e) {
            throw new AssertionError(e);
        }

        assertEquals(2, bambou1_1V.getTailleBambou());
        assertEquals(1, bambou2_0R.getTailleBambou());

        assertEquals(sectionBambouVerte, bambou1_1V.prendSectionBambou());
        assertEquals(sectionBambouVerte, bambou1_1V.prendSectionBambou());
        assertEquals(sectionBambouRose, bambou2_0R.prendSectionBambou());
    }


    @Test
    void testToString() {
        assertEquals("Bambou de couleur verte en (1,1) de 0 sections de bambou", bambou1_1V.toString());
        assertEquals("Bambou de couleur rose en (2,0) de 0 sections de bambou", bambou2_0R.toString());

        try {
            bambou1_1V.ajouteSectionBambou(sectionBambouVerte);
        } catch (AjoutCouleurException e) {
            throw new AssertionError(e);
        }
        assertEquals("Bambou de couleur verte en (1,1) de 1 sections de bambou", bambou1_1V.toString());
    }

    @Test
    void testEquals() {
        assertEquals(bambou1_1V, bambou1_1VBis);
        assertNotEquals(bambou1_1V, bambou2_0R);
    }
}