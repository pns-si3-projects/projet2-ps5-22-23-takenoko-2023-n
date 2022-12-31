package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
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
    void getNombreBambousRestants() {
        piocheBambou = new PiocheBambou(new Random());
        assertEquals(90, piocheBambou.getNombreBambousRestants());
        piocheBambou.pioche();
        assertEquals(89, piocheBambou.getNombreBambousRestants());
        piocheBambou.pioche();
        piocheBambou.pioche();
        assertEquals(87, piocheBambou.getNombreBambousRestants());
    }

    @Test
    void isEmpty() {
        piocheBambou = new PiocheBambou(new Random());
        assertFalse(piocheBambou.isEmpty());
        piocheBambou.pioche();
        assertFalse(piocheBambou.isEmpty());
    }

    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(90);
        piocheBambou = new PiocheBambou(mockRandom);
        assertThrows(RuntimeException.class, () -> {piocheBambou.pioche();});
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheBambou = new PiocheBambou(mockRandom);
        assertThrows(RuntimeException.class, () -> {piocheBambou.pioche();});
    }

    @Test
    void pioche() {
        // retirer le ".getClass()" lorsque le equals de la classe SectionBambou sera faisable et fait (avec la couleur)
        when(mockRandom.nextInt(anyInt())).thenReturn(89, 1, 4, 28, 28);
        piocheBambou = new PiocheBambou(mockRandom);
        assertEquals(new SectionBambou().getClass(), piocheBambou.pioche().getClass());
        assertEquals(new SectionBambou().getClass(), piocheBambou.pioche().getClass());
        // car en supprimant 1, les suivant sont décalés donc on prend ancien 5
        assertEquals(new SectionBambou().getClass(), piocheBambou.pioche().getClass());
        assertEquals(new SectionBambou().getClass(), piocheBambou.pioche().getClass());
        assertEquals(new SectionBambou().getClass(), piocheBambou.pioche().getClass());
    }
}