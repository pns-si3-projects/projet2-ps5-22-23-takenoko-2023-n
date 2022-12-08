package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {
    Joueur joueur1;
    Joueur joueur2;
    Plateau plateauJeu;
    Jeu jeu;

    @BeforeEach
    void setUp(){
        joueur1 = new Joueur("Robot1");
        joueur2 = new Joueur("Robot2");
        jeu = new Jeu(joueur1);
        plateauJeu = jeu.getPlateau();
    }

    @Test
     void testGetJoueur() {
        assertEquals(joueur1, jeu.getJoueur());
        assertNotEquals(joueur2, jeu.getJoueur());
    }

    @Test
    void testGetPlateau(){
        Parcelle etang = new Etang();
        assertEquals(etang,plateauJeu.getParcelles().get(0).getParcelleCible());
    }

    @Test
    void testFinDePartie(){
        assertEquals("Le joueur Robot1 a gagne la partie !",jeu.finDePartie());
    }

    @Test
    void testSetJoueur() {
        Jeu jeu = new Jeu(joueur1);
        jeu.setJoueur(joueur2);
        assertEquals(joueur2, jeu.getJoueur());
        assertNotEquals(joueur1, jeu.getJoueur());
    }
}