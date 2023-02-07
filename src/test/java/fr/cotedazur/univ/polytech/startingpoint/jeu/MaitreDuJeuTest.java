package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Plaquette;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Empereur;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

class MaitreDuJeuTest {
    MaitreDuJeu maitreDuJeu;
    @Mock
    Joueur joueurMock1;
    @Mock
    Joueur joueurMock2;
    @Mock
    Joueur joueurMock3;
    @Mock
    Joueur joueurMock4;
    Plaquette.ActionPossible parcelle;
    Plaquette.ActionPossible irrigation;
    Plaquette.ActionPossible jardinier;
    Plaquette.ActionPossible panda;
    Plaquette.ActionPossible objectif;


    @BeforeEach
    void setUp() {
        joueurMock1 = mock(Joueur.class);
        joueurMock2 = mock(Joueur.class);
        joueurMock3 = mock(Joueur.class);
        joueurMock4 = mock(Joueur.class);
        parcelle = Plaquette.ActionPossible.PARCELLE;
        irrigation = Plaquette.ActionPossible.IRRIGATION;
        jardinier = Plaquette.ActionPossible.JARDINIER;
        panda = Plaquette.ActionPossible.PANDA;
        objectif = Plaquette.ActionPossible.OBJECTIF;
    }


    @Test
    void jeu2Joueurs() {
        when(joueurMock1.choisiAction(any(Plateau.class), any())).thenReturn(parcelle, objectif);
        when(joueurMock2.choisiAction(any(Plateau.class), any())).thenReturn(jardinier, objectif);
        when(joueurMock1.nombreObjectifsTermines()).thenReturn(7, 9);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(6, 15);
        when(joueurMock1.nombrePoints()).thenReturn(52);
        when(joueurMock2.nombrePoints()).thenReturn(56);
        maitreDuJeu = new MaitreDuJeu(joueurMock1, joueurMock2);

        maitreDuJeu.jeu();
        verify(joueurMock1, times(1)).recoitEmpereur(new Empereur()); // Premier pour objectifs
        verify(joueurMock1, times(1)).nombrePoints();
        verify(joueurMock2, atLeastOnce()).nombrePoints();
        verify(joueurMock1, never()).nombrePointsObjectifsPanda();
        verify(joueurMock2, never()).nombrePointsObjectifsPanda();
        verify(joueurMock2, times(1)).getNom(); // joueur2 gagne la partie
    }

    @Test
    void jeu3Joueurs() {
        when(joueurMock1.choisiAction(any(Plateau.class), any())).thenReturn(objectif, parcelle);
        when(joueurMock2.choisiAction(any(Plateau.class), any())).thenReturn(jardinier, objectif);
        when(joueurMock3.choisiAction(any(Plateau.class), any())).thenReturn(panda, objectif);
        when(joueurMock1.nombreObjectifsTermines()).thenReturn(5, 7);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(6, 8);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(4, 7);
        when(joueurMock1.nombrePoints()).thenReturn(46);
        when(joueurMock2.nombrePoints()).thenReturn(49);
        when(joueurMock3.nombrePoints()).thenReturn(49);
        when(joueurMock2.nombrePointsObjectifsPanda()).thenReturn(20);
        when(joueurMock3.nombrePointsObjectifsPanda()).thenReturn(22);
        maitreDuJeu = new MaitreDuJeu(joueurMock1, joueurMock2, joueurMock3);

        maitreDuJeu.jeu();
        verify(joueurMock2, times(1)).recoitEmpereur(new Empereur()); // Premier pour objectifs
        verify(joueurMock1, times(1)).nombrePoints();
        verify(joueurMock2, times(1)).nombrePoints();
        verify(joueurMock3, atLeastOnce()).nombrePoints();
        verify(joueurMock1, never()).nombrePointsObjectifsPanda();
        verify(joueurMock2, times(1)).nombrePointsObjectifsPanda();
        verify(joueurMock3, times(1)).nombrePointsObjectifsPanda();
        verify(joueurMock3, times(1)).getNom(); // joueur3 gagne la partie
    }

    @Test
    void jeu4Joueurs() {
        when(joueurMock1.choisiAction(any(Plateau.class), any())).thenReturn(parcelle, objectif);
        when(joueurMock2.choisiAction(any(Plateau.class), any())).thenReturn(jardinier, objectif);
        when(joueurMock3.choisiAction(any(Plateau.class), any())).thenReturn(panda, objectif);
        when(joueurMock4.choisiAction(any(Plateau.class), any())).thenReturn(parcelle, objectif, jardinier, panda, irrigation);
        when(joueurMock1.nombreObjectifsTermines()).thenReturn(3, 7);
        when(joueurMock2.nombreObjectifsTermines()).thenReturn(5, 8);
        when(joueurMock3.nombreObjectifsTermines()).thenReturn(5, 8);
        when(joueurMock4.nombreObjectifsTermines()).thenReturn(4, 9);
        when(joueurMock1.nombrePoints()).thenReturn(35);
        when(joueurMock2.nombrePoints()).thenReturn(44);
        when(joueurMock3.nombrePoints()).thenReturn(35);
        when(joueurMock4.nombrePoints()).thenReturn(44);
        when(joueurMock2.nombrePointsObjectifsPanda()).thenReturn(21);
        when(joueurMock4.nombrePointsObjectifsPanda()).thenReturn(21);
        maitreDuJeu = new MaitreDuJeu(joueurMock1, joueurMock2, joueurMock3, joueurMock4);

        maitreDuJeu.jeu();
        verify(joueurMock1, times(1)).recoitEmpereur(new Empereur()); // Premier pour objectifs
        verify(joueurMock1, times(1)).nombrePoints();
        verify(joueurMock2, times(1)).nombrePoints();
        verify(joueurMock3, times(1)).nombrePoints();
        verify(joueurMock4, times(1)).nombrePoints();
        verify(joueurMock1, never()).nombrePointsObjectifsPanda();
        verify(joueurMock2, times(1)).nombrePointsObjectifsPanda();
        verify(joueurMock3, never()).nombrePointsObjectifsPanda();
        verify(joueurMock4, times(1)).nombrePointsObjectifsPanda();
    }
}