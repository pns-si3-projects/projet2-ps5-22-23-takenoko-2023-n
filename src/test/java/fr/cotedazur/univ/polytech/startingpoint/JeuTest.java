package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {
    Joueur joueur1;
    Joueur joueur2;
    Jeu jeu;
    Etang etang;

    @BeforeEach
    void setUp(){
        joueur1 = new Joueur("Robot1");
        joueur2 = new Joueur("Robot2");
        jeu = new Jeu(joueur1);
        etang = new Etang();
    }

    @Test
     void testGetJoueur() {
        assertEquals(joueur1, jeu.getJoueur());
        assertNotEquals(joueur2, jeu.getJoueur());
    }

    @Test
    void testGetPlateau() {
        assertEquals(etang, jeu.getPlateau().getParcelles().get(0).getParcelleCible());
    }
}