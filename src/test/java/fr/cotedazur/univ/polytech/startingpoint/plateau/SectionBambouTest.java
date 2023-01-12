package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
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
        assertEquals(Couleur.VERT,bambouVert.getCouleur());
        assertNotEquals(Couleur.JAUNE,bambouRose.getCouleur());
        assertEquals(Couleur.ROSE,bambouRose.getCouleur());
        assertEquals(Couleur.JAUNE,bambouJaune.getCouleur());
        assertEquals(Couleur.VERT,bambouVertBis.getCouleur());
    }

    @Test
    void testEquals() {
        assertEquals(bambouVert,bambouVertBis);
        assertNotEquals(bambouVert,bambouJaune);
        assertNotEquals(bambouVert,bambouRose);
        assertThrows(NullPointerException.class, () -> {new SectionBambou(null);});
    }
}