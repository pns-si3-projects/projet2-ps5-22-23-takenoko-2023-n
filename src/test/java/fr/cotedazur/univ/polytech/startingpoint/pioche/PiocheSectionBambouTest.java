package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
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
    Random mockRandom = mock(Random.class);

    @Test
    void getNombreBambousVertsRestants() {
        piocheSectionBambou = new PiocheSectionBambou(new Random());
        assertEquals(36, piocheSectionBambou.getNombreBambousVertsRestants());
        piocheSectionBambou.piocheSectionBambouVert();
        assertEquals(35, piocheSectionBambou.getNombreBambousVertsRestants());
    }

    @Test
    void getNombreBambousRosesRestants() {
        piocheSectionBambou = new PiocheSectionBambou(new Random());
        assertEquals(24, piocheSectionBambou.getNombreBambousRosesRestants());
        piocheSectionBambou.piocheSectionBambouRose();
        piocheSectionBambou.piocheSectionBambouRose();
        piocheSectionBambou.piocheSectionBambouRose();
        assertEquals(21, piocheSectionBambou.getNombreBambousRosesRestants());
    }

    @Test
    void getNombreBambousJaunesRestants() {
        piocheSectionBambou = new PiocheSectionBambou(new Random());
        assertEquals(30, piocheSectionBambou.getNombreBambousJaunesRestants());
        piocheSectionBambou.piocheSectionBambouJaune();
        piocheSectionBambou.piocheSectionBambouJaune();
        assertEquals(28, piocheSectionBambou.getNombreBambousJaunesRestants());
    }

    @Test
    void isEmptyBambousVerts() {
        piocheSectionBambou = new PiocheSectionBambou(new Random());
        assertFalse(piocheSectionBambou.isEmptyBambousVerts());
        piocheSectionBambou.piocheSectionBambouVert();
        assertFalse(piocheSectionBambou.isEmptyBambousVerts());
    }

    @Test
    void isEmptyBambousRoses() {
        piocheSectionBambou = new PiocheSectionBambou(new Random());
        assertFalse(piocheSectionBambou.isEmptyBambousRoses());
        piocheSectionBambou.piocheSectionBambouRose();
        assertFalse(piocheSectionBambou.isEmptyBambousRoses());
    }

    @Test
    void isEmptyBambousJaunes() {
        piocheSectionBambou = new PiocheSectionBambou(new Random());
        assertFalse(piocheSectionBambou.isEmptyBambousJaunes());
        piocheSectionBambou.piocheSectionBambouJaune();
        assertFalse(piocheSectionBambou.isEmptyBambousJaunes());
    }

    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(36, 24, 30);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);
        assertThrows(RuntimeException.class, () -> {
            piocheSectionBambou.piocheSectionBambouVert();});
        assertThrows(RuntimeException.class, () -> {
            piocheSectionBambou.piocheSectionBambouRose();});
        assertThrows(RuntimeException.class, () -> {
            piocheSectionBambou.piocheSectionBambouJaune();});
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);
        assertThrows(RuntimeException.class, () -> {
            piocheSectionBambou.piocheSectionBambouVert();});
        assertThrows(RuntimeException.class, () -> {
            piocheSectionBambou.piocheSectionBambouRose();});
        assertThrows(RuntimeException.class, () -> {
            piocheSectionBambou.piocheSectionBambouJaune();});
    }

    @Test
    void piocheSectionBambouVert() {
        // retirer le ".getClass()" lorsque le equals de la classe SectionBambou sera faisable et fait (avec la couleur)
        when(mockRandom.nextInt(anyInt())).thenReturn(35, 1, 32, 32);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouVert().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouVert().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouVert().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouVert().getClass());
    }

    @Test
    void piocheSectionBambouRose() {
        // retirer le ".getClass()" lorsque le equals de la classe SectionBambou sera faisable et fait (avec la couleur)
        when(mockRandom.nextInt(anyInt())).thenReturn(23, 1, 20, 20);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouRose().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouRose().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouRose().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouRose().getClass());
    }

    @Test
    void piocheSectionBambouJaune() {
        // retirer le ".getClass()" lorsque le equals de la classe SectionBambou sera faisable et fait (avec la couleur)
        when(mockRandom.nextInt(anyInt())).thenReturn(29, 1, 26, 26);
        piocheSectionBambou = new PiocheSectionBambou(mockRandom);
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouJaune().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouJaune().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouJaune().getClass());
        assertEquals(SectionBambou.class, piocheSectionBambou.piocheSectionBambouJaune().getClass());
    }
}