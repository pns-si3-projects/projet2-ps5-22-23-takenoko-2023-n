package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifDiagonale;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifTriangle;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifV;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Empereur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JoueurTest {
    Joueur joueurParcelle;
    Joueur joueurPanda;
    Joueur joueurJardinier;
    Plateau plateau;
    boolean[] piochesVides;


    @BeforeEach
    void setUp() {
        joueurParcelle = new Joueur("joueur1", Strategie.StrategiePossible.PARCELLE);
        joueurPanda = new Joueur("joueur2", Strategie.StrategiePossible.PANDA);
        joueurJardinier = new Joueur("joueur3", Strategie.StrategiePossible.JARDINIER);
        plateau = new Plateau(new PiocheSectionBambou());
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
    }

    @Test
    void joueIrrigation() {
    }

    @Test
    void deplaceJardinier() {
    }

    @Test
    void deplacePanda() {
    }

    @Test
    void piocheObjectif() {
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
        assertEquals(joueurParcelle.getNom(), joueurParcelle.toString());
        assertEquals(joueurPanda.getNom(), joueurPanda.toString());
        assertEquals(joueurJardinier.getNom(), joueurJardinier.toString());
        assertNotEquals(joueurParcelle.getNom(), joueurPanda.toString());
    }

    @Test
    void gestionObjectifParcelle() {
        Random mockRandom = mock(Random.class);
        PiocheObjectifParcelle piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        PiocheObjectifJardinier piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        PiocheObjectifPanda piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        when(mockRandom.nextInt()).thenReturn(0, 0);

        joueurParcelle.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);
        joueurParcelle.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda);

        plateau.poseParcelle(new ParcelleCouleur(new Position(1, 1), Couleur.VERTE));
        joueurParcelle.gestionObjectif(plateau);
        assertEquals(0, joueurParcelle.nombreObjectifsTermines());

        plateau.poseParcelle(new ParcelleCouleur(new Position(2, 0), Couleur.VERTE));
        joueurParcelle.gestionObjectif(plateau);
        assertEquals(0, joueurParcelle.nombreObjectifsTermines());

        plateau.poseParcelle(new ParcelleCouleur(new Position(3, 1), Couleur.VERTE));
        joueurParcelle.gestionObjectif(plateau);
        assertEquals(1, joueurParcelle.nombreObjectifsTermines());

        plateau.poseParcelle(new ParcelleCouleur(new Position(-1, 1), Couleur.VERTE));
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

        joueurJardinier.gestionObjectif(plateau);
        assertEquals(0, joueurJardinier.nombreObjectifsEnMain());
        assertEquals(2, joueurJardinier.nombreObjectifsTermines());
    }

    @Test
    void gestionObjectifPanda() {
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt()).thenReturn(2, 4);
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

        joueurPanda.actionPanda(plateau);
        assertEquals(1, joueurPanda.getPlaquette().nombreBambouCouleur(Couleur.VERTE));

        joueurPanda.actionPanda(plateau);
        assertEquals(2, joueurPanda.getPlaquette().nombreBambouCouleur(Couleur.VERTE));

        joueurPanda.gestionObjectif(plateau);
        assertEquals(0, joueurPanda.nombreObjectifsEnMain());
        assertEquals(2, joueurPanda.nombreObjectifsTermines());
        assertEquals(0, joueurPanda.getPlaquette().nombreBambouCouleur(Couleur.VERTE));
    }
}