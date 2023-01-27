package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheObjectifJardinierTest {
    PiocheObjectifJardinier piocheObjectifJardinier;
    @Mock
    Random mockRandom = mock(Random.class);

    @Test
    void getNombreObjectifsRestants() {
        piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        assertEquals(15, piocheObjectifJardinier.getNombreObjectifsRestants());
        piocheObjectifJardinier.pioche();
        assertEquals(14, piocheObjectifJardinier.getNombreObjectifsRestants());
    }

    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(15);
        piocheObjectifJardinier = new PiocheObjectifJardinier(mockRandom);
        assertThrows(ArithmeticException.class, () -> {piocheObjectifJardinier.pioche();});
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheObjectifJardinier = new PiocheObjectifJardinier(mockRandom);
        assertThrows(ArithmeticException.class, () -> {piocheObjectifJardinier.pioche();});
    }

    @Test
    void pioche() {
        when(mockRandom.nextInt(anyInt())).thenReturn(14, 10, 11, 0, 1);
        piocheObjectifJardinier = new PiocheObjectifJardinier(mockRandom);
        assertEquals(new ObjectifJardinier(3, 4, Couleur.VERTE), piocheObjectifJardinier.pioche());
        assertEquals(new ObjectifJardinier(5, 4, Couleur.JAUNE), piocheObjectifJardinier.pioche());
        // car en supprimant 10, les suivants sont décalés donc on prend ancien 12
        assertEquals(new ObjectifJardinier(6, 4, Couleur.ROSE), piocheObjectifJardinier.pioche());
        assertEquals(new ObjectifJardinier(5, 4, Couleur.JAUNE), piocheObjectifJardinier.pioche());
        // car en supprimant 0, les suivants sont décalés donc on prend ancien 2
        assertEquals(new ObjectifJardinier(6, 6, Couleur.JAUNE), piocheObjectifJardinier.pioche());
    }
}