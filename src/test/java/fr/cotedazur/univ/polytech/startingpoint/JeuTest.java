package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {

    @Test
     void testGetJoueur() {
        Joueur joueur1 = new Joueur("Robot1");
        Joueur joueur2 = new Joueur("Robot2");
        Etang etang = new Etang();
        Jeu jeu = new Jeu(joueur1, etang);
        assertEquals(joueur1, jeu.getJoueur());
        assertNotEquals(joueur2, jeu.getJoueur());
    }

    @Test
    void testGetEtang() {
        Joueur joueur = new Joueur("Robot1");
        Etang etang1 = new Etang();
        Etang etang2 = new Etang();
        Jeu jeu = new Jeu(joueur, etang1);
        assertEquals(etang1, jeu.getEtang());
        assertNotEquals(etang2, jeu.getEtang());
    }

    @Test
    void testSetJoueur() {
        Joueur joueur1 = new Joueur("Robot1");
        Joueur joueur2 = new Joueur("Robot2");
        Etang etang = new Etang();
        Jeu jeu = new Jeu(joueur1, etang);
        jeu.setJoueur(joueur2);
        assertEquals(joueur2, jeu.getJoueur());
        assertNotEquals(joueur1, jeu.getJoueur());
    }

    @Test
    void testSetEtang() {
        Joueur joueur = new Joueur("Robot1");
        Etang etang1 = new Etang();
        Etang etang2 = new Etang();
        Jeu jeu = new Jeu(joueur, etang1);
        jeu.setEtang(etang2);
        assertEquals(etang2, jeu.getEtang());
        assertNotEquals(etang1, jeu.getEtang());
    }
}