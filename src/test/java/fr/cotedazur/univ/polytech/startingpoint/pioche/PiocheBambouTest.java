package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheBambou;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheBambouTest {
    PiocheBambou piocheBambou;
    @Mock
    Random mockRandom = mock(Random.class);

    @Test
    void getNombreBambousVertsRestants() {
        piocheBambou = new PiocheBambou(new Random());
        assertEquals(36, piocheBambou.getNombreBambousVertsRestants());
        piocheBambou.piocheSectionBambouVert();
        assertEquals(35, piocheBambou.getNombreBambousVertsRestants());
    }

    @Test
    void getNombreBambousRosesRestants() {
        piocheBambou = new PiocheBambou(new Random());
        assertEquals(24, piocheBambou.getNombreBambousRosesRestants());
        piocheBambou.piocheSectionBambouRose();
        piocheBambou.piocheSectionBambouRose();
        piocheBambou.piocheSectionBambouRose();
        assertEquals(21, piocheBambou.getNombreBambousRosesRestants());
    }

    @Test
    void getNombreBambousJaunesRestants() {
        piocheBambou = new PiocheBambou(new Random());
        assertEquals(30, piocheBambou.getNombreBambousJaunesRestants());
        piocheBambou.piocheSectionBambouJaune();
        piocheBambou.piocheSectionBambouJaune();
        assertEquals(28, piocheBambou.getNombreBambousJaunesRestants());
    }

    @Test
    void isEmptyBambousVerts() {
        piocheBambou = new PiocheBambou(new Random());
        assertFalse(piocheBambou.isEmptyBambousVerts());
        piocheBambou.piocheSectionBambouVert();
        assertFalse(piocheBambou.isEmptyBambousVerts());
    }

    @Test
    void isEmptyBambousRoses() {
        piocheBambou = new PiocheBambou(new Random());
        assertFalse(piocheBambou.isEmptyBambousRoses());
        piocheBambou.piocheSectionBambouRose();
        assertFalse(piocheBambou.isEmptyBambousRoses());
    }

    @Test
    void isEmptyBambousJaunes() {
        piocheBambou = new PiocheBambou(new Random());
        assertFalse(piocheBambou.isEmptyBambousJaunes());
        piocheBambou.piocheSectionBambouJaune();
        assertFalse(piocheBambou.isEmptyBambousJaunes());
    }

    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(36, 24, 30);
        piocheBambou = new PiocheBambou(mockRandom);
        assertThrows(RuntimeException.class, () -> {piocheBambou.piocheSectionBambouVert();});
        assertThrows(RuntimeException.class, () -> {piocheBambou.piocheSectionBambouRose();});
        assertThrows(RuntimeException.class, () -> {piocheBambou.piocheSectionBambouJaune();});
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheBambou = new PiocheBambou(mockRandom);
        assertThrows(RuntimeException.class, () -> {piocheBambou.piocheSectionBambouVert();});
        assertThrows(RuntimeException.class, () -> {piocheBambou.piocheSectionBambouRose();});
        assertThrows(RuntimeException.class, () -> {piocheBambou.piocheSectionBambouJaune();});
    }

    @Test
    void piocheSectionBambouVert() {
        // retirer le ".getClass()" lorsque le equals de la classe SectionBambou sera faisable et fait (avec la couleur)
        when(mockRandom.nextInt(anyInt())).thenReturn(35, 1, 32, 32);
        piocheBambou = new PiocheBambou(mockRandom);
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouVert().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouVert().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouVert().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouVert().getClass());
    }

    @Test
    void piocheSectionBambouRose() {
        // retirer le ".getClass()" lorsque le equals de la classe SectionBambou sera faisable et fait (avec la couleur)
        when(mockRandom.nextInt(anyInt())).thenReturn(23, 1, 20, 20);
        piocheBambou = new PiocheBambou(mockRandom);
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouRose().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouRose().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouRose().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouRose().getClass());
    }

    @Test
    void piocheSectionBambouJaune() {
        // retirer le ".getClass()" lorsque le equals de la classe SectionBambou sera faisable et fait (avec la couleur)
        when(mockRandom.nextInt(anyInt())).thenReturn(29, 1, 26, 26);
        piocheBambou = new PiocheBambou(mockRandom);
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouJaune().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouJaune().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouJaune().getClass());
        assertEquals(SectionBambou.class, piocheBambou.piocheSectionBambouJaune().getClass());
    }
}