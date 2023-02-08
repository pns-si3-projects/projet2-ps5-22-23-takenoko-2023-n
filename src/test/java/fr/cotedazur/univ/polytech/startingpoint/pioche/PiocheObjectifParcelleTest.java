package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.*;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
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
        when(mockRandom.nextInt(anyInt())).thenReturn(11, 6, 9, 0, 1);
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        ParcelleCouleur parcelleCouleur00R = new ParcelleCouleur(new Position(0, 0), Couleur.ROSE);
        ParcelleCouleur parcelleCouleur11R = new ParcelleCouleur(new Position(1, 1), Couleur.ROSE);
        ParcelleCouleur parcelleCouleurm11R = new ParcelleCouleur(new Position(-1, 1), Couleur.ROSE);
        ParcelleCouleur parcelleCouleur02R = new ParcelleCouleur(new Position(0, 2), Couleur.ROSE);
        ParcelleCouleur parcelleCouleurm1m1R = new ParcelleCouleur(new Position(-1, -1), Couleur.ROSE);
        ParcelleCouleur parcelleCouleur00V = new ParcelleCouleur(new Position(0, 0), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleur11V = new ParcelleCouleur(new Position(1, 1), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), Couleur.VERTE);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.VERTE);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), Couleur.VERTE);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE);

        Motif motifDR = new MotifDiagonale(parcelleCouleurm1m1R, parcelleCouleur00R, parcelleCouleur11R);
        Motif motifV = new MotifV(parcelleCouleur00V, parcelleCouleur11V, parcelleCouleur02);
        Motif motifL = new MotifLosange(parcelleCouleur00R, parcelleCouleur11R, parcelleCouleurm11R, parcelleCouleur02R);
        Motif motifTr = new MotifTriangle(parcelleCouleur00, parcelleCouleur20, parcelleCouleur11);
        Motif motifDV = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);

        assertEquals(new ObjectifParcelle(4, motifDR), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(3, motifV), piocheObjectifParcelle.pioche());
        // car en supprimant 10, les suivants sont décalés donc on prend ancien 12
        assertEquals(new ObjectifParcelle(5, motifL), piocheObjectifParcelle.pioche());
        assertEquals(new ObjectifParcelle(2, motifTr), piocheObjectifParcelle.pioche());
        // car en supprimant 0, les suivants sont décalés donc on prend ancien 1
        assertEquals(new ObjectifParcelle(2, motifDV), piocheObjectifParcelle.pioche());
    }
}