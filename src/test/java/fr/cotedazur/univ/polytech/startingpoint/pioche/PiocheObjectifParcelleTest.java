package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.*;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
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
        when(mockRandom.nextInt(anyInt())).thenReturn(14, 8, 11, 3, 0);
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), Couleur.VERT);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), Couleur.VERT);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.VERT);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.VERT);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.VERT);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), Couleur.VERT);
        Motif motifTriangle = new MotifTriangle(parcelleCouleur00, parcelleCouleur20, parcelleCouleur11);
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Motif motifDecale = new MotifDecale(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);

        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        assertEquals(new ObjectifParcelle(5, motifLosange), piocheObjectifParcelle.pioche());
        // car en supprimant 14, les suivant sont décalés donc on prend ancien 10
        assertEquals(new ObjectifParcelle(4, motifDecale), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(5, motifLosange), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(2, motifDiagonale), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(3, motifTriangle), piocheObjectifParcelle.pioche());
    }
}