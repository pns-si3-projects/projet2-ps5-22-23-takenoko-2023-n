package fr.cotedazur.univ.polytech.startingpoint;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Enregistre les statistiques d'un joueur donné
 */
public class JoueurStats {
    // Définition des attributs

    public enum EtatPartie {GAGNEE, PERDUE, NULLE}
    private static final int GAGNEE = EtatPartie.GAGNEE.ordinal();
    private static final int PERDUE = EtatPartie.PERDUE.ordinal();
    private static final int NULLE = EtatPartie.NULLE.ordinal();
    private final String nomJoueur;
    private final int[] nombreParties;
    private final List<Double> scores;


    // Définition des constructeurs

    public JoueurStats(@NotNull String nomJoueur) {
        this.nomJoueur = nomJoueur;
        nombreParties = new int[] {0, 0, 0};
        scores = new ArrayList<>();
    }


    // Accesseurs

    /**
     * Renvoie le nom du joueur
     * @return le nom du joueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * Renvoie le nombre de parties jouées
     * @return le nombre de parties jouées
     */
    public int getNombreParties() {
        return nombreParties[GAGNEE]
                + nombreParties[EtatPartie.PERDUE.ordinal()]
                + nombreParties[EtatPartie.NULLE.ordinal()];
    }

    /**
     * Renvoie le nombre de parties gagnées par le joueur
     * @return le nombre de parties gagnées
     */
    public int getNombrePartiesGagnees() {
        return nombreParties[GAGNEE];
    }

    /**
     * Renvoie le pourcentage de parties gagnées par le joueur
     * @return le pourcentage de parties gagnées
     */
    public int getPourcentagePartiesGagnees() {
        int nbParties = getNombreParties();

        if (nbParties == 0) {
            return 0;
        }
        return nombreParties[GAGNEE] * 100 / nbParties;
    }

    /**
     * Renvoie le nombre de parties perdues par le joueur
     * @return le nombre de parties perdues
     */
    public int getNombrePartiesPerdues() {
        return nombreParties[PERDUE];
    }

    /**
     * Renvoie le pourcentage de parties perdues par le joueur
     * @return le pourcentage de parties perdues
     */
    public int getPourcentagePartiesPerdues() {
        int nbParties = getNombreParties();

        if (nbParties == 0) {
            return 0;
        }
        return nombreParties[PERDUE] * 100 / nbParties;
    }

    /**
     * Renvoie le nombre de parties nulles par le joueur
     * @return le nombre de parties nulles
     */
    public int getNombrePartiesNulles() {
        return nombreParties[NULLE];
    }

    /**
     * Renvoie le pourcentage de parties nulles par le joueur
     * @return le pourcentage de parties nulles
     */
    public int getPourcentagePartiesNulles() {
        int nbParties = getNombreParties();

        if (nbParties == 0) {
            return 0;
        }
        return nombreParties[NULLE] * 100 / nbParties;
    }

    /**
     * Renvoie le score moyen du joueur sur toutes les parties jouées
     * @return le score moyen du joueur
     */
    public double getScoreMoyen() {
        int nbScore = scores.size();
        if (nbScore == 0) {
            return 0;
        }

        double somme = 0;

        for (Double score : scores) {
            somme += score;
        }
        return somme / scores.size();
    }


    // Méthodes d'utilisation

    /**
     * Ajoute les statistiques d'une nouvelle partie
     * @param etatPartie l'état de la partie pour le joueur (gagnée, perdue ou nulle)
     * @param score le score du joueur sur la partie actuelle
     */
    public void ajoutePartie(EtatPartie etatPartie, Double score) {
        nombreParties[etatPartie.ordinal()]++;
        scores.add(score);

        if (getNombreParties() != scores.size()) {
            throw new AssertionError("Nombre de parties incorrecte");
        }
    }

    /**
     * Renvoie les statistiques du joueur
     * @return un tableau de string représentant les statistiques du joueur
     */
    public String[] envoieStatistiques() {
        String[] statistiques = new String[8];

        statistiques[0] = getNomJoueur();
        statistiques[1] = Integer.toString(getNombrePartiesGagnees());
        statistiques[2] = Integer.toString(getPourcentagePartiesGagnees());
        statistiques[3] = Integer.toString(getNombrePartiesPerdues());
        statistiques[4] = Integer.toString(getPourcentagePartiesPerdues());
        statistiques[5] = Integer.toString(getNombrePartiesNulles());
        statistiques[6] = Integer.toString(getPourcentagePartiesNulles());
        statistiques[7] = Double.toString(getScoreMoyen());
        return statistiques;
    }

    /**
     * Renvoie le JoueurStats avec les statistiques données
     * @param statistiques les statistiques à enregistrer
     * @return le JoueurStats avec les statistiques données
     */
    public static JoueurStats joueurAvecStatistiques(String[] statistiques) {
        int nbDonnees = statistiques.length;
        if (nbDonnees != 8) {
            throw new AssertionError("Nombre de données incorrecte");
        }

        JoueurStats joueurStats = new JoueurStats(statistiques[0]);
        int nbPartiesGagnees = Integer.parseInt(statistiques[1]);
        int nbPartiesPerdues = Integer.parseInt(statistiques[3]);
        int nbPartiesNulles = Integer.parseInt(statistiques[5]);
        double scoreMoyen = Double.parseDouble(statistiques[7]);

        ajouteDonneesJoueur(joueurStats, nbPartiesGagnees, nbPartiesPerdues, nbPartiesNulles, scoreMoyen);
        return joueurStats;
    }

    /**
     * Ajoute les données des parties au JoueurStats
     * @param joueurStats le JoueurStats à modifier
     * @param nbPartiesGagnees le nombre de parties gagnées
     * @param nbPartiesPerdues le nombre de parties perdues
     * @param nbPartiesNulles le nombre de parties nulles
     * @param scoreMoyen le score moyen
     */
    private static void ajouteDonneesJoueur(JoueurStats joueurStats, int nbPartiesGagnees,
                                                       int nbPartiesPerdues, int nbPartiesNulles, double scoreMoyen) {
        ajouteParties(joueurStats, nbPartiesGagnees, EtatPartie.GAGNEE, scoreMoyen);
        ajouteParties(joueurStats, nbPartiesPerdues, EtatPartie.PERDUE, scoreMoyen);
        ajouteParties(joueurStats, nbPartiesNulles, EtatPartie.NULLE, scoreMoyen);
    }

    /**
     * Ajoute les parties de l'etat demandée avec le score moyen
     * @param joueurStats le JoueurStats à modifier
     * @param nbParties le nombre de parties de l'état donné
     * @param etatPartie l'état de la partie
     * @param scoreMoyen le score moyen
     */
    private static void ajouteParties(JoueurStats joueurStats, int nbParties,
                                      EtatPartie etatPartie, double scoreMoyen) {
        for (int i=0; i<nbParties; i++) {
            joueurStats.ajoutePartie(etatPartie, scoreMoyen);
        }
    }
}
