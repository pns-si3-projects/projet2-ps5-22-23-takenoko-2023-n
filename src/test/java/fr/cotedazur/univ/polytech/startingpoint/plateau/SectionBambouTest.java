package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
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
        bambouVert = new SectionBambou(Couleur.VERT);
        bambouJaune = new SectionBambou(Couleur.JAUNE);
        bambouRose = new SectionBambou(Couleur.ROSE);
        bambouVertBis = new SectionBambou(Couleur.VERT);
    }

    @Test
    void getCouleur() {
        assertEquals(Couleur.VERT,bambouVert.couleur());
        assertNotEquals(Couleur.JAUNE,bambouRose.couleur());
        assertEquals(Couleur.ROSE,bambouRose.couleur());
        assertEquals(Couleur.JAUNE,bambouJaune.couleur());
        assertEquals(Couleur.VERT,bambouVertBis.couleur());
    }

    @Test
    void testEquals() {
        assertEquals(bambouVert,bambouVertBis);
        assertNotEquals(bambouVert,bambouJaune);
        assertNotEquals(bambouVert,bambouRose);
        assertThrows(IllegalArgumentException.class, () -> {new SectionBambou(null);});
    }
}