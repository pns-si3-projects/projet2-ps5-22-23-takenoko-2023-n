package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheObjectifParcelleTest {
    PiocheObjectifParcelle piocheObjectifParcelle;
    @Mock
    Random mockRandom;


    @BeforeEach
    void setUp() {
        piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        mockRandom = mock(Random.class);
    }


    @Test
    void getNombreObjectifsRestants() {
        assertEquals(15, piocheObjectifParcelle.getNombreObjectifsRestants());

        piocheObjectifParcelle.pioche();
        piocheObjectifParcelle.pioche();
        assertEquals(13, piocheObjectifParcelle.getNombreObjectifsRestants());
    }

    @Test
    void isEmpty() {
        assertFalse(piocheObjectifParcelle.isEmpty());
        piocheObjectifParcelle.pioche();
        assertFalse(piocheObjectifParcelle.isEmpty());
    }


    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(15);
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);

        assertThrows(ArithmeticException.class, () -> piocheObjectifParcelle.pioche());
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);

        assertThrows(ArithmeticException.class, () -> piocheObjectifParcelle.pioche());
    }

    @Test
    void pioche() {
        when(mockRandom.nextInt(anyInt())).thenReturn(14, 10, 11, 0, 0);
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);

        assertEquals(new ObjectifParcelle(3, 3), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(4, 3), piocheObjectifParcelle.pioche());
        // car en supprimant 10, les suivants sont décalés donc on prend ancien 12
        assertEquals(new ObjectifParcelle(5, 4), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(2, 3), piocheObjectifParcelle.pioche());
        // car en supprimant 0, les suivants sont décalés donc on prend ancien 1
        assertEquals(new ObjectifParcelle(3, 4), piocheObjectifParcelle.pioche());
    }
}