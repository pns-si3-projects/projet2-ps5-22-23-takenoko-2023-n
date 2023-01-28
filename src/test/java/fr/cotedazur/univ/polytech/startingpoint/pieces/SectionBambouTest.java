package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SectionBambouTest {
    SectionBambou sectionBambouVert;
    SectionBambou sectionBambouVertBis;
    SectionBambou sectionbambouRose;
    SectionBambou sectionbambouJaune;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
        sectionBambouVert = new SectionBambou(verte);
        sectionBambouVertBis = new SectionBambou(verte);
        sectionbambouRose = new SectionBambou(rose);
        sectionbambouJaune = new SectionBambou(jaune);
    }


    @Test
    void getCouleur() {
        assertEquals(verte, sectionBambouVert.getCouleur());
        assertEquals(rose, sectionbambouRose.getCouleur());
        assertEquals(jaune, sectionbambouJaune.getCouleur());
    }


    @Test
    void testToString() {
        assertEquals("Section de bambou de couleur verte", sectionBambouVert.toString());
        assertEquals("Section de bambou de couleur rose", sectionbambouRose.toString());
        assertEquals("Section de bambou de couleur jaune", sectionbambouJaune.toString());
    }

    @Test
    void testEquals() {
        assertEquals(sectionBambouVert, sectionBambouVertBis);
        assertNotEquals(sectionBambouVert, sectionbambouRose);
        assertNotEquals(sectionbambouRose, sectionbambouJaune);
    }
}