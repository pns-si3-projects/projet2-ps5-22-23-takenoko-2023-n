package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SectionBambouTest {
    SectionBambou bambouVert;
    SectionBambou bambouJaune;
    SectionBambou bambouRose;
    SectionBambou bambouVertBis;

    @BeforeEach
    void setUp() {
        bambouVert = new SectionBambou(Couleur.VERTE);
        bambouJaune = new SectionBambou(Couleur.JAUNE);
        bambouRose = new SectionBambou(Couleur.ROSE);
        bambouVertBis = new SectionBambou(Couleur.VERTE);
    }

    @Test
    void getCouleur() {
        assertEquals(Couleur.VERTE,bambouVert.getCouleur());
        assertNotEquals(Couleur.JAUNE,bambouRose.getCouleur());
        assertEquals(Couleur.ROSE,bambouRose.getCouleur());
        assertEquals(Couleur.JAUNE,bambouJaune.getCouleur());
        assertEquals(Couleur.VERTE,bambouVertBis.getCouleur());
    }

    @Test
    void testEquals() {
        assertEquals(bambouVert,bambouVertBis);
        assertNotEquals(bambouVert,bambouJaune);
        assertNotEquals(bambouVert,bambouRose);
        assertThrows(IllegalArgumentException.class, () -> {new SectionBambou(null);});
    }
}