package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurStatsTest {
    JoueurStats joueurStats;


    @BeforeEach
    void setUp() {
        joueurStats = new JoueurStats("Joueur");
    }


    @Test
    void getNomJoueur() {
        assertEquals("Joueur", joueurStats.getNomJoueur());
    }

    @Test
    void getNombreParties() {
        assertEquals(0, joueurStats.getNombreParties());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);
        assertEquals(5, joueurStats.getNombreParties());
    }

    @Test
    void getNombrePartiesGagnees() {
        assertEquals(0, joueurStats.getNombrePartiesGagnees());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);
        assertEquals(2, joueurStats.getNombrePartiesGagnees());
    }

    @Test
    void getPourcentagePartiesGagnees() {
        assertEquals(0, joueurStats.getPourcentagePartiesGagnees());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);
        assertEquals(40, joueurStats.getPourcentagePartiesGagnees());
    }

    @Test
    void getNombrePartiesPerdues() {
        assertEquals(0, joueurStats.getNombrePartiesPerdues());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);
        assertEquals(1, joueurStats.getNombrePartiesPerdues());
    }

    @Test
    void getPourcentagePartiesPerdues() {
        assertEquals(0, joueurStats.getPourcentagePartiesPerdues());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);
        assertEquals(20, joueurStats.getPourcentagePartiesPerdues());
    }

    @Test
    void getNombrePartiesNulles() {
        assertEquals(0, joueurStats.getNombrePartiesNulles());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);
        assertEquals(2, joueurStats.getNombrePartiesNulles());
    }

    @Test
    void getPourcentagePartiesNulles() {
        assertEquals(0, joueurStats.getPourcentagePartiesNulles());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);
        assertEquals(40, joueurStats.getPourcentagePartiesNulles());
    }

    @Test
    void getScoreMoyen() {
        assertEquals(0, joueurStats.getScoreMoyen());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);
        assertEquals((27 + 24 + 32 + 29 + 26) / 5.0, joueurStats.getScoreMoyen());
    }

    @Test
    void ajoutePartie() {
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26);

        // Pourcentage prennent en compte le nombre de parties dans un état et le nombre total de partiess
        assertEquals(40, joueurStats.getPourcentagePartiesGagnees());
        assertEquals(20, joueurStats.getPourcentagePartiesPerdues());
        assertEquals(40, joueurStats.getPourcentagePartiesNulles());
        assertEquals((27 + 24 + 32 + 29 + 26) / 5.0, joueurStats.getScoreMoyen());
    }
}