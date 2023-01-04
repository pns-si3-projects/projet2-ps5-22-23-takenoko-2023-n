package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheObjectifPandaTest {
    PiocheObjectifPanda piocheObjectifPanda;
    @Mock
    Random mockRandom = mock(Random.class);

    @Test
    void isEmpty() {
        piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        assertFalse(piocheObjectifPanda.isEmpty());
        piocheObjectifPanda.pioche();
        assertFalse(piocheObjectifPanda.isEmpty());
    }

    @Test
    void getNombreObjectifsRestants() {
        piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        assertEquals(15, piocheObjectifPanda.getNombreObjectifsRestants());
        piocheObjectifPanda.pioche();
        assertEquals(14, piocheObjectifPanda.getNombreObjectifsRestants());
        piocheObjectifPanda.pioche();
        piocheObjectifPanda.pioche();
        assertEquals(12, piocheObjectifPanda.getNombreObjectifsRestants());
    }

    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(15);
        piocheObjectifPanda = new PiocheObjectifPanda(mockRandom);
        assertThrows(ArithmeticException.class, () -> {piocheObjectifPanda.pioche();});
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheObjectifPanda = new PiocheObjectifPanda(mockRandom);
        assertThrows(ArithmeticException.class, () -> {piocheObjectifPanda.pioche();});
    }

    @Test
    void pioche() {
        when(mockRandom.nextInt(anyInt())).thenReturn(14, 1, 4, 9, 9);
        piocheObjectifPanda = new PiocheObjectifPanda(mockRandom);
        assertEquals(new ObjectifPanda(6, 3), piocheObjectifPanda.pioche());
        assertEquals(new ObjectifPanda(3, 2), piocheObjectifPanda.pioche());
        // car en supprimant 1, les suivant sont décalés donc on prend ancien 5
        assertEquals(new ObjectifPanda(4, 2), piocheObjectifPanda.pioche());
        assertEquals(new ObjectifPanda(5, 2), piocheObjectifPanda.pioche());
        assertEquals(new ObjectifPanda(6, 3), piocheObjectifPanda.pioche());
    }
}