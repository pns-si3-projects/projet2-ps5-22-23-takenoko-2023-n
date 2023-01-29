package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheSectionBambouTest {
    PiocheSectionBambou piocheSectionBambou;
    @Mock
    Random mockRandom;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        piocheSectionBambou = new PiocheSectionBambou(new Random());
        mockRandom = mock(Random.class);
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
    }


    @Test
    void getNombreSectionsBambou() {
        assertEquals(36, piocheSectionBambou.getNombreSectionsBambou(verte));
        assertEquals(24, piocheSectionBambou.getNombreSectionsBambou(rose));
        assertEquals(30, piocheSectionBambou.getNombreSectionsBambou(jaune));
    }

    @Test
    void isEmptySectionsBambou() {
        assertFalse(piocheSectionBambou.isEmpty(verte));
        assertFalse(piocheSectionBambou.isEmpty(rose));
        assertFalse(piocheSectionBambou.isEmpty(jaune));
    }


    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(36, 24, 30);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.pioche(verte));
        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.pioche(rose));
        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.pioche(jaune));
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.pioche(verte));
        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.pioche(rose));
        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.pioche(jaune));
    }

    @Test
    void piocheSectionBambouVert() {
        SectionBambou sectionBambouVert = new SectionBambou(verte);

        when(mockRandom.nextInt(anyInt())).thenReturn(35, 1, 32, 32);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertEquals(sectionBambouVert, piocheSectionBambou.pioche(verte));
        assertEquals(sectionBambouVert, piocheSectionBambou.pioche(verte));
        assertEquals(sectionBambouVert, piocheSectionBambou.pioche(verte));
        assertEquals(sectionBambouVert, piocheSectionBambou.pioche(verte));
    }

    @Test
    void piocheSectionBambouRose() {
        SectionBambou sectionBambouRose = new SectionBambou(rose);
        
        when(mockRandom.nextInt(anyInt())).thenReturn(23, 1, 20, 20);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertEquals(sectionBambouRose, piocheSectionBambou.pioche(rose));
        assertEquals(sectionBambouRose, piocheSectionBambou.pioche(rose));
        assertEquals(sectionBambouRose, piocheSectionBambou.pioche(rose));
        assertEquals(sectionBambouRose, piocheSectionBambou.pioche(rose));
    }

    @Test
    void piocheSectionBambouJaune() {
        SectionBambou sectionBambouJaune = new SectionBambou(jaune);

        when(mockRandom.nextInt(anyInt())).thenReturn(29, 1, 26, 26);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertEquals(sectionBambouJaune, piocheSectionBambou.pioche(jaune));
        assertEquals(sectionBambouJaune, piocheSectionBambou.pioche(jaune));
        assertEquals(sectionBambouJaune, piocheSectionBambou.pioche(jaune));
        assertEquals(sectionBambouJaune, piocheSectionBambou.pioche(jaune));
    }


    @Test
    void testToString() {
        assertEquals("Pioche de bambous : 36 verts, 24 roses et 30 jaunes", piocheSectionBambou.toString());
    }
}