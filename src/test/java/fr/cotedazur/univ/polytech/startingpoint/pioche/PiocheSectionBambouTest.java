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
    // Attributs

    PiocheSectionBambou piocheSectionBambou;
    @Mock
    Random mockRandom = mock(Random.class);
    Couleur vert = Couleur.VERT;
    Couleur rose = Couleur.ROSE;
    Couleur jaune = Couleur.JAUNE;


    // Constructeur

    @BeforeEach
    void setUp() {
        piocheSectionBambou = new PiocheSectionBambou(new Random());
    }


    // Accesseurs

    @Test
    void getNombreSectionsBambou() {
        assertEquals(36, piocheSectionBambou.getNombreSectionsBambou(vert));
        assertEquals(36, piocheSectionBambou.getNombreSectionsBambou(rose));
        assertEquals(36, piocheSectionBambou.getNombreSectionsBambou(jaune));
    }

    @Test
    void isEmptySectionsBambou() {
        assertFalse(piocheSectionBambou.isEmptySectionsBambou(vert));
        assertFalse(piocheSectionBambou.isEmptySectionsBambou(rose));
        assertFalse(piocheSectionBambou.isEmptySectionsBambou(jaune));
    }


    // Méthodes d'utilisation

    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(36, 24, 30);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.piocheSectionBambou(vert));
        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.piocheSectionBambou(rose));
        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.piocheSectionBambou(jaune));
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.piocheSectionBambou(vert));
        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.piocheSectionBambou(rose));
        assertThrows(ArithmeticException.class, () -> piocheSectionBambou.piocheSectionBambou(jaune));
    }

    @Test
    void piocheSectionBambouVert() {
        SectionBambou sectionBambouVert = new SectionBambou(vert);

        when(mockRandom.nextInt(anyInt())).thenReturn(35, 1, 32, 32);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertEquals(sectionBambouVert, piocheSectionBambou.piocheSectionBambou(vert));
        assertEquals(sectionBambouVert, piocheSectionBambou.piocheSectionBambou(vert));
        assertEquals(sectionBambouVert, piocheSectionBambou.piocheSectionBambou(vert));
        assertEquals(sectionBambouVert, piocheSectionBambou.piocheSectionBambou(vert));
    }

    @Test
    void piocheSectionBambouRose() {
        SectionBambou sectionBambouRose = new SectionBambou(rose);
        
        when(mockRandom.nextInt(anyInt())).thenReturn(35, 1, 32, 32);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertEquals(sectionBambouRose, piocheSectionBambou.piocheSectionBambou(rose));
        assertEquals(sectionBambouRose, piocheSectionBambou.piocheSectionBambou(rose));
        assertEquals(sectionBambouRose, piocheSectionBambou.piocheSectionBambou(rose));
        assertEquals(sectionBambouRose, piocheSectionBambou.piocheSectionBambou(rose));
    }

    @Test
    void piocheSectionBambouJaune() {
        SectionBambou sectionBambouJaune = new SectionBambou(jaune);

        when(mockRandom.nextInt(anyInt())).thenReturn(35, 1, 32, 32);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);

        assertEquals(sectionBambouJaune, piocheSectionBambou.piocheSectionBambou(jaune));
        assertEquals(sectionBambouJaune, piocheSectionBambou.piocheSectionBambou(jaune));
        assertEquals(sectionBambouJaune, piocheSectionBambou.piocheSectionBambou(jaune));
        assertEquals(sectionBambouJaune, piocheSectionBambou.piocheSectionBambou(jaune));
    }
}