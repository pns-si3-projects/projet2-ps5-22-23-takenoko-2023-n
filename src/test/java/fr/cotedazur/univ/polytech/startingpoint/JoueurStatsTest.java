package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurStatsTest {
    JoueurStats joueurStats;
    String[] statistiques;


    @BeforeEach
    void setUp() {
        joueurStats = new JoueurStats("Joueur");
        statistiques = new String[] {"Joueur", "2", "40", "1", "20", "2", "40", "27.6"};
    }


    @Test
    void getNomJoueur() {
        assertEquals("Joueur", joueurStats.getNomJoueur());
    }

    @Test
    void getNombreParties() {
        assertEquals(0, joueurStats.getNombreParties());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);
        assertEquals(5, joueurStats.getNombreParties());
    }

    @Test
    void getNombrePartiesGagnees() {
        assertEquals(0, joueurStats.getNombrePartiesGagnees());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);
        assertEquals(2, joueurStats.getNombrePartiesGagnees());
    }

    @Test
    void getPourcentagePartiesGagnees() {
        assertEquals(0, joueurStats.getPourcentagePartiesGagnees());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);
        assertEquals(40, joueurStats.getPourcentagePartiesGagnees());
    }

    @Test
    void getNombrePartiesPerdues() {
        assertEquals(0, joueurStats.getNombrePartiesPerdues());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);
        assertEquals(1, joueurStats.getNombrePartiesPerdues());
    }

    @Test
    void getPourcentagePartiesPerdues() {
        assertEquals(0, joueurStats.getPourcentagePartiesPerdues());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);
        assertEquals(20, joueurStats.getPourcentagePartiesPerdues());
    }

    @Test
    void getNombrePartiesNulles() {
        assertEquals(0, joueurStats.getNombrePartiesNulles());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);
        assertEquals(2, joueurStats.getNombrePartiesNulles());
    }

    @Test
    void getPourcentagePartiesNulles() {
        assertEquals(0, joueurStats.getPourcentagePartiesNulles());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);
        assertEquals(40, joueurStats.getPourcentagePartiesNulles());
    }

    @Test
    void getScoreMoyen() {
        assertEquals(0, joueurStats.getScoreMoyen());
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);
        assertEquals((27 + 24 + 32 + 29 + 26) / 5.0, joueurStats.getScoreMoyen());
    }


    @Test
    void ajoutePartie() {
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);

        // Pourcentages prennent en compte le nombre de parties dans un état et le nombre total de parties
        assertEquals(40, joueurStats.getPourcentagePartiesGagnees());
        assertEquals(20, joueurStats.getPourcentagePartiesPerdues());
        assertEquals(40, joueurStats.getPourcentagePartiesNulles());
        assertEquals((27 + 24 + 32 + 29 + 26) / 5.0, joueurStats.getScoreMoyen());
    }

    @Test
    void envoieStatistiques() {
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 27.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 24.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.GAGNEE, 32.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.PERDUE, 29.0);
        joueurStats.ajoutePartie(JoueurStats.EtatPartie.NULLE, 26.0);

        String[] stats = joueurStats.envoieStatistiques();
        assertEquals(statistiques[0], stats[0]);
        assertEquals(statistiques[1], stats[1]);
        assertEquals(statistiques[2], stats[2]);
        assertEquals(statistiques[3], stats[3]);
        assertEquals(statistiques[4], stats[4]);
        assertEquals(statistiques[5], stats[5]);
        assertEquals(statistiques[6], stats[6]);
        assertEquals(statistiques[7], stats[7]);
    }

    @Test
    void joueurAvecStatistiques() {
        JoueurStats joueurStatistiques = JoueurStats.joueurAvecStatistiques(statistiques);

        // Pourcentages prennent en compte le nombre de parties dans un état et le nombre total de parties
        assertEquals(40, joueurStatistiques.getPourcentagePartiesGagnees());
        assertEquals(20, joueurStatistiques.getPourcentagePartiesPerdues());
        assertEquals(40, joueurStatistiques.getPourcentagePartiesNulles());
        assertEquals((27 + 24 + 32 + 29 + 26) / 5.0, joueurStatistiques.getScoreMoyen());
    }
}