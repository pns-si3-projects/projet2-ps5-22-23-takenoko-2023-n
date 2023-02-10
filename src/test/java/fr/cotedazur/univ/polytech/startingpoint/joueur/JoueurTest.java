package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifV;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Empereur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class JoueurTest {
    Joueur joueurParcelle;
    Joueur joueurPanda;
    Joueur joueurJardinier;
    Joueur joueurComplet;
    Plateau plateau;
    PiocheSectionBambou piocheSectionBambou;
    PiocheParcelle piocheParcelle;
    PiocheIrrigation piocheIrrigation;
    PiocheObjectifParcelle piocheObjectifParcelle;
    PiocheObjectifPanda piocheObjectifPanda;
    PiocheObjectifJardinier piocheObjectifJardinier;
    boolean[] piochesVides;


    @BeforeEach
    void setUp() {
        joueurParcelle = new Joueur("joueur1", Strategie.StrategiePossible.PARCELLE);
        joueurPanda = new Joueur("joueur2", Strategie.StrategiePossible.PANDA);
        joueurJardinier = new Joueur("joueur3", Strategie.StrategiePossible.JARDINIER);
        joueurComplet = new Joueur("joueur4", Strategie.StrategiePossible.COMPLET);
        piocheSectionBambou = new PiocheSectionBambou();
        plateau = new Plateau(piocheSectionBambou);
        piocheParcelle = new PiocheParcelle(new Random());
        piocheIrrigation = new PiocheIrrigation();
        piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        piochesVides = new boolean[] {false, false, false, false, false};
    }


    @Test
    void getNom() {
        assertEquals("joueur1", joueurParcelle.getNom());
        assertNotEquals("joueur1", joueurPanda.getNom());
    }

    @Test
    void getPlaquette() {
        assertEquals(Plaquette.class, joueurParcelle.getPlaquette().getClass());
        assertEquals(Plaquette.class, joueurPanda.getPlaquette().getClass());
    }

    @Test
    void getObjectifsEnMain() {
        // À modifier quand le joueur aura des objectifs
        assertEquals(0, joueurParcelle.getObjectifsEnMain().length);
        assertEquals(0, joueurPanda.getObjectifsEnMain().length);
    }

    @Test
    void getObjectifsTermines() {
        // À modifier quand le joueur aura des objectifs
        assertEquals(0, joueurParcelle.getObjectifsTermines().length);
        assertEquals(0, joueurPanda.getObjectifsTermines().length);
    }

    @Test
    void initialiseObjectifs() {
        assertEquals(0, joueurParcelle.nombreObjectifsEnMain());
        Objectif[] objectifs = new Objectif[3];
        objectifs[0] = piocheObjectifParcelle.pioche();
        objectifs[1] = piocheObjectifPanda.pioche();
        objectifs[2] = piocheObjectifJardinier.pioche();
        joueurParcelle.initialiseObjectifs(objectifs);
        assertEquals(3, joueurParcelle.nombreObjectifsEnMain());
    }

    @Test
    void nombreObjectifsEnMain() {
        assertEquals(0, joueurParcelle.nombreObjectifsEnMain());
        assertNotEquals(1, joueurPanda.nombreObjectifsEnMain());
    }

    @Test
    void nombreObjectifsTermines() {
        assertEquals(0, joueurParcelle.nombreObjectifsTermines());
        assertNotEquals(1, joueurPanda.nombreObjectifsTermines());
    }

    @Test
    void nombrePoints() {
        assertEquals(0, joueurParcelle.nombrePoints());
        assertNotEquals(1, joueurPanda.nombrePoints());
    }

    @Test
    void nombrePointsObjectifsPanda() {
        assertEquals(0, joueurParcelle.nombrePointsObjectifsPanda());
        assertNotEquals(1, joueurPanda.nombrePointsObjectifsPanda());
    }

    @Test
    void choisiAction() {
        assertEquals(Plaquette.ActionPossible.OBJECTIF, joueurParcelle.choisiAction(plateau, piochesVides));
        assertEquals(Plaquette.ActionPossible.PANDA, joueurParcelle.choisiAction(plateau, piochesVides));

        assertEquals(Plaquette.ActionPossible.PARCELLE, joueurJardinier.choisiAction(plateau, piochesVides));
        assertEquals(Plaquette.ActionPossible.OBJECTIF, joueurJardinier.choisiAction(plateau, piochesVides));

        assertEquals(Plaquette.ActionPossible.OBJECTIF, joueurPanda.choisiAction(plateau, piochesVides));
        assertEquals(Plaquette.ActionPossible.PARCELLE, joueurPanda.choisiAction(plateau, piochesVides));
    }

    @Test
    void joueParcelle() {
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));

        assertEquals(1, spyPlateau.getParcelles().length);

        for (int i=0; i<2; i++){
            joueurPanda.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurJardinier.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurParcelle.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurComplet.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
        }

        assertEquals(9, spyPlateau.getParcelles().length);

        verify(spyPlateau, times(8)).poseParcelle(any(ParcelleCouleur.class));
    }

    @Test
    void joueIrrigation() {
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));
        assertEquals(0, spyPlateau.getIrrigationsPosees().length);
        for (int i=0; i<2; i++){
            joueurParcelle.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurPanda.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurComplet.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurJardinier.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
        }

        joueurParcelle.actionIrrigation(spyPlateau, piocheIrrigation);
        verify(spyPlateau, times(1)).poseIrrigation(any(Irrigation.class));
        assertEquals(1, spyPlateau.getIrrigationsPosees().length);
        joueurParcelle.actionIrrigation(spyPlateau, piocheIrrigation);
    }

    @Test
    void deplaceJardinier() {
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));

        for (int i=0; i<2; i++){
            joueurParcelle.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurPanda.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurComplet.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurJardinier.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
        }
        for (int i=0; i<2; i++) {
            joueurJardinier.actionJardinier(spyPlateau, piocheSectionBambou);
            joueurComplet.actionJardinier(spyPlateau, piocheSectionBambou);
            joueurParcelle.actionJardinier(spyPlateau, piocheSectionBambou);
            joueurPanda.actionJardinier(spyPlateau, piocheSectionBambou);
        }

        verify(spyPlateau, times(8)).deplacementJardinier(any(Position.class));
    }

    @Test
    void deplacePanda() {
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));

        for (int i=0; i<2; i++){
            joueurParcelle.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurPanda.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurComplet.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
            joueurJardinier.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou);
        }
        for (int i=0; i<2; i++) {
            joueurPanda.actionPanda(spyPlateau);
            joueurComplet.actionPanda(spyPlateau);
            joueurParcelle.actionPanda(spyPlateau);
            joueurJardinier.actionPanda(spyPlateau);
        }
        verify(spyPlateau, times(8)).deplacementPanda(any(Position.class));

    }

    @Test
    void piocheObjectif() {
        joueurPanda.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        joueurParcelle.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        joueurJardinier.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        joueurComplet.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);

        assertEquals(1, joueurPanda.nombreObjectifsEnMain());
        assertEquals(1, joueurParcelle.nombreObjectifsEnMain());
        assertEquals(1, joueurJardinier.nombreObjectifsEnMain());
        assertEquals(1, joueurComplet.nombreObjectifsEnMain());
    }

    @Test
    void recoitEmpereur() {
        Objectif empereur = new Empereur();
        ParcelleCouleur parcelle0_0V = new ParcelleCouleur(new Position(), Couleur.VERTE);
        ParcelleCouleur parcelle1_m1V = new ParcelleCouleur(new Position(1, -1), Couleur.JAUNE);
        ParcelleCouleur parcelle3_m1V = new ParcelleCouleur(new Position(3, -1), Couleur.ROSE);
        Objectif objectif = new ObjectifParcelle(3, new MotifV(parcelle0_0V, parcelle1_m1V, parcelle3_m1V));

        joueurParcelle.recoitEmpereur(empereur);
        Objectif[] objectifs = joueurParcelle.getObjectifsTermines();
        assertEquals(empereur, objectifs[0]);

        assertThrows(IllegalArgumentException.class, () -> joueurParcelle.recoitEmpereur(objectif));
        assertThrows(IllegalArgumentException.class, () -> joueurJardinier.recoitEmpereur(objectif));
        assertThrows(IllegalArgumentException.class, () -> joueurPanda.recoitEmpereur(objectif));
    }


    @Test
    void testToString() {
        assertEquals(joueurParcelle.getNom() + " : "
                + joueurParcelle.nombreObjectifsTermines() + " objectifs termines pour "
                + joueurParcelle.nombrePoints() + " points", joueurParcelle.toString());
        assertEquals(joueurPanda.getNom() + " : "
                + joueurPanda.nombreObjectifsTermines() + " objectifs termines pour "
                + joueurPanda.nombrePoints() + " points", joueurPanda.toString());
        assertEquals(joueurJardinier.getNom() + " : "
                + joueurJardinier.nombreObjectifsTermines() + " objectifs termines pour "
                + joueurJardinier.nombrePoints() + " points", joueurJardinier.toString());
    }

    @Test
    void gestionObjectifParcelle() {
        Random mockRandom = mock(Random.class);
        PiocheObjectifParcelle piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        PiocheObjectifJardinier piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        PiocheObjectifPanda piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        when(mockRandom.nextInt(anyInt())).thenReturn(0, 0);

        joueurParcelle.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        joueurParcelle.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.VERTE);
        plateau.poseParcelle(parcelleCouleur11);
        joueurParcelle.gestionObjectif(plateau);
        assertEquals(0, joueurParcelle.nombreObjectifsTermines());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), Couleur.VERTE);
        plateau.poseParcelle(parcelleCouleur20);
        joueurParcelle.gestionObjectif(plateau);
        assertEquals(0, joueurParcelle.nombreObjectifsTermines());

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), Couleur.VERTE);
        plateau.poseParcelle(parcelleCouleur31);
        joueurParcelle.gestionObjectif(plateau);
        assertEquals(0, joueurParcelle.nombreObjectifsTermines());

        parcelleCouleur11.setIrriguee(true);
        parcelleCouleur20.setIrriguee(true);
        parcelleCouleur31.setIrriguee(true);

        joueurParcelle.gestionObjectif(plateau);
        assertEquals(1, joueurParcelle.nombreObjectifsTermines());

        ParcelleCouleur parcelleCouleur40 = new ParcelleCouleur(new Position(4, 0), Couleur.VERTE);
        plateau.poseParcelle(parcelleCouleur40);
        parcelleCouleur40.setIrriguee(true);
        joueurParcelle.gestionObjectif(plateau);
        assertEquals(2, joueurParcelle.nombreObjectifsTermines());
    }

    @Test
    void gestionObjectifJardinier() {
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt(anyInt())).thenReturn(1, 8);
        PiocheObjectifJardinier piocheObjectifJardinier = new PiocheObjectifJardinier(mockRandom);
        PiocheObjectifParcelle piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        PiocheObjectifPanda piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        PiocheSectionBambou piocheSectionBambou = new PiocheSectionBambou();

        joueurJardinier.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        joueurJardinier.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        plateau.poseParcelle(new ParcelleCouleur(new Position(1,1), Couleur.JAUNE));
        plateau.poseParcelle(new ParcelleCouleur(new Position(2, 0), Couleur.ROSE));

        for (int i = 0; i < 5; i++) {
            joueurJardinier.gestionObjectif(plateau);
            assertEquals(0, joueurJardinier.nombreObjectifsTermines());
            assertEquals(2, joueurJardinier.nombreObjectifsEnMain());
            joueurJardinier.actionJardinier(plateau, piocheSectionBambou);
        }

        joueurJardinier.actionJardinier(plateau, piocheSectionBambou);
        joueurJardinier.gestionObjectif(plateau);
        assertEquals(0, joueurJardinier.nombreObjectifsEnMain());
        assertEquals(2, joueurJardinier.nombreObjectifsTermines());
    }

    @Test
    void gestionObjectifPanda() {
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt(anyInt())).thenReturn(2, 4);
        PiocheObjectifJardinier piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        PiocheObjectifParcelle piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        PiocheObjectifPanda piocheObjectifPanda = new PiocheObjectifPanda(mockRandom);
        PiocheSectionBambou piocheSectionBambou = new PiocheSectionBambou();

        joueurPanda.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        joueurPanda.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        assertEquals(2, joueurPanda.nombreObjectifsEnMain());
        assertEquals(0, joueurPanda.nombreObjectifsTermines());
        assertEquals(0, joueurPanda.getPlaquette().nombreBambouCouleur(Couleur.VERTE));

        plateau.poseParcelle(new ParcelleCouleur(new Position(1,1), Couleur.VERTE));
        plateau.poseParcelle(new ParcelleCouleur(new Position(2, 0), Couleur.VERTE));

        for (int i = 0; i < 5; i++) {
            joueurJardinier.actionJardinier(plateau, piocheSectionBambou);
        }

        joueurPanda.actionPanda(plateau);
        assertEquals(1, joueurPanda.getPlaquette().nombreBambouCouleur(Couleur.VERTE));

        joueurPanda.actionPanda(plateau);
        assertEquals(2, joueurPanda.getPlaquette().nombreBambouCouleur(Couleur.VERTE));

        joueurPanda.gestionObjectif(plateau);
        assertEquals(1, joueurPanda.nombreObjectifsEnMain());
        assertEquals(1, joueurPanda.nombreObjectifsTermines());
        assertEquals(0, joueurPanda.getPlaquette().nombreBambouCouleur(Couleur.VERTE));
    }
}