package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheObjectifParcelleTest {
    PiocheObjectifParcelle piocheObjectifParcelle;
    @Mock
    Random mockRandom = mock(Random.class);

    @Test
    void getNombreObjectifsRestants() {
        piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        assertEquals(15, piocheObjectifParcelle.getNombreObjectifsRestants());
        piocheObjectifParcelle.pioche();
        piocheObjectifParcelle.pioche();
        assertEquals(13, piocheObjectifParcelle.getNombreObjectifsRestants());
    }

    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(15);
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        assertThrows(ArithmeticException.class, () -> {piocheObjectifParcelle.pioche();});
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        assertThrows(ArithmeticException.class, () -> {piocheObjectifParcelle.pioche();});
    }

    @Test
    void pioche() {
        when(mockRandom.nextInt(anyInt())).thenReturn(14, 10, 11, 0, 0);
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        Assertions.assertEquals(new ObjectifParcelle(3, 3), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(4, 3), piocheObjectifParcelle.pioche());
        // car en supprimant 10, les suivant sont décalés donc on prend ancien 12
        assertEquals(new ObjectifParcelle(5, 4), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(2, 3), piocheObjectifParcelle.pioche());
        // car en supprimant 0, les suivant sont décalés donc on prend ancien 1
        assertEquals(new ObjectifParcelle(3, 4), piocheObjectifParcelle.pioche());
    }
}