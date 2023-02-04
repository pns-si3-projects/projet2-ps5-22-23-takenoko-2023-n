package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GestionToursTest {
    GestionTours gestionTours;
    @Mock
    Joueur joueurMock1;
    @Mock
    Joueur joueurMock2;
    @Mock
    Joueur joueurMock3;
    @Mock
    Joueur joueurMock4;
    Joueur[] joueurs;


    @BeforeEach
    void setUp() {
        gestionTours = new GestionTours();
        joueurMock1 = mock(Joueur.class);
        joueurMock2 = mock(Joueur.class);
        joueurMock3 = mock(Joueur.class);
        joueurMock4 = mock(Joueur.class);
    }


    @Test
    void tours2Joueurs() {
        Joueur joueurFinObjectifs;

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(7, 9);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(6, 15);
        joueurs = new Joueur[]{joueurMock1, joueurMock2};
        joueurFinObjectifs = gestionTours.tours(joueurs, 9);
        assertEquals(joueurMock1, joueurFinObjectifs);

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(7, 8);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(6, 9);
        joueurs = new Joueur[]{joueurMock1, joueurMock2};
        joueurFinObjectifs = gestionTours.tours(joueurs, 9);
        assertEquals(joueurMock2, joueurFinObjectifs);
    }

    @Test
    void tours3Joueurs() {
        Joueur joueurFinObjectifs;

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(3, 6, 7, 7);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(0, 1, 5, 8);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(2, 5, 6, 8);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3};
        joueurFinObjectifs = gestionTours.tours(joueurs, 8);
        assertEquals(joueurMock2, joueurFinObjectifs);

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(3, 5, 6, 8);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(5, 5, 7, 12);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(2, 4, 6, 11);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3};
        joueurFinObjectifs = gestionTours.tours(joueurs, 8);
        assertEquals(joueurMock1, joueurFinObjectifs);

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(3, 5, 5, 6);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(5, 5, 7, 7);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(2, 4, 6, 8);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3};
        joueurFinObjectifs = gestionTours.tours(joueurs, 8);
        assertEquals(joueurMock3, joueurFinObjectifs);
    }

    @Test
    void tours4Joueurs() {
        Joueur joueurFinObjectifs;

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(3, 5, 6, 6);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(0, 1, 5, 7);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(2, 5, 5, 8);
        when(joueurMock4.nombreObjectifsTermines()).thenReturn(2, 5, 6, 10);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3, joueurMock4};
        joueurFinObjectifs = gestionTours.tours(joueurs, 7);
        assertEquals(joueurMock2, joueurFinObjectifs);

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(0, 0, 4, 6);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(0, 1, 5, 5);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(2, 2, 5, 6);
        when(joueurMock4.nombreObjectifsTermines()).thenReturn(2, 5, 6, 7);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3, joueurMock4};
        joueurFinObjectifs = gestionTours.tours(joueurs, 7);
        assertEquals(joueurMock4, joueurFinObjectifs);

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(3, 4, 5, 6);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(0, 1, 4, 4);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(2, 5, 6, 7);
        when(joueurMock4.nombreObjectifsTermines()).thenReturn(5, 6, 6, 10);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3, joueurMock4};
        joueurFinObjectifs = gestionTours.tours(joueurs, 7);
        assertEquals(joueurMock3, joueurFinObjectifs);

        when(joueurMock1.nombreObjectifsTermines()).thenReturn(3, 6, 7, 7);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(0, 1, 5, 8);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(2, 5, 6, 10);
        when(joueurMock4.nombreObjectifsTermines()).thenReturn(2, 5, 6, 9);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3, joueurMock4};
        joueurFinObjectifs = gestionTours.tours(joueurs, 7);
        assertEquals(joueurMock1, joueurFinObjectifs);
    }

    @Test
    void dernierTour() {
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3, joueurMock4};

        gestionTours.dernierTour(joueurs, joueurMock2);
        verify(joueurMock1, times(1)).joueTour();
        verify(joueurMock2, never()).joueTour();
        verify(joueurMock3, times(1)).joueTour();
        verify(joueurMock4, times(1)).joueTour();
    }

    @Test
    void gagnantPasPointsPanda() {
        Optional<Joueur> joueurGagnantOpt;

        when(joueurMock1.nombrePoints()).thenReturn(43);
        when(joueurMock1.nombrePointsObjectifsPanda())
                .thenThrow(new AssertionError("Pas besoin des points des objectifs de panda"));
        when(joueurMock2.nombrePoints()).thenReturn(52);
        when(joueurMock2.nombrePointsObjectifsPanda())
                .thenThrow(new AssertionError("Pas besoin des points des objectifs de panda"));
        when(joueurMock3.nombrePoints()).thenReturn(51);
        when(joueurMock3.nombrePointsObjectifsPanda())
                .thenThrow(new AssertionError("Pas besoin des points des objectifs de panda"));
        when(joueurMock4.nombrePoints()).thenReturn(43);
        when(joueurMock4.nombrePointsObjectifsPanda())
                .thenThrow(new AssertionError("Pas besoin des points des objectifs de panda"));
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3, joueurMock4};

        joueurGagnantOpt = gestionTours.gagnant(joueurs);
        assertTrue(joueurGagnantOpt.isPresent());
        assertEquals(joueurMock2, joueurGagnantOpt.get());
    }

    @Test
    void gagnantPointsPanda() {
        Optional<Joueur> joueurGagnantOpt;

        when(joueurMock1.nombrePoints()).thenReturn(43);
        when(joueurMock1.nombrePointsObjectifsPanda())
                .thenThrow(new AssertionError("Pas besoin des points des objectifs de panda"));
        when(joueurMock2.nombrePoints()).thenReturn(52);
        when(joueurMock2.nombrePointsObjectifsPanda()).thenReturn(18);
        when(joueurMock3.nombrePoints()).thenReturn(51);
        when(joueurMock3.nombrePointsObjectifsPanda())
                .thenThrow(new AssertionError("Pas besoin des points des objectifs de panda"));
        when(joueurMock4.nombrePoints()).thenReturn(52);
        when(joueurMock4.nombrePointsObjectifsPanda()).thenReturn(20);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3, joueurMock4};

        joueurGagnantOpt = gestionTours.gagnant(joueurs);
        assertTrue(joueurGagnantOpt.isPresent());
        assertEquals(joueurMock4, joueurGagnantOpt.get());
    }

    @Test
    void gagnantAucun() {
        Optional<Joueur> joueurGagnantOpt;

        when(joueurMock1.nombrePoints()).thenReturn(52);
        when(joueurMock1.nombrePointsObjectifsPanda()).thenReturn(20);
        when(joueurMock2.nombrePoints()).thenReturn(52);
        when(joueurMock2.nombrePointsObjectifsPanda()).thenReturn(18);
        when(joueurMock3.nombrePoints()).thenReturn(51);
        when(joueurMock3.nombrePointsObjectifsPanda())
                .thenThrow(new AssertionError("Pas besoin des points des objectifs de panda"));
        when(joueurMock4.nombrePoints()).thenReturn(52);
        when(joueurMock4.nombrePointsObjectifsPanda()).thenReturn(20);
        joueurs = new Joueur[]{joueurMock1, joueurMock2, joueurMock3, joueurMock4};

        joueurGagnantOpt = gestionTours.gagnant(joueurs);
        assertTrue(joueurGagnantOpt.isEmpty());
    }
}