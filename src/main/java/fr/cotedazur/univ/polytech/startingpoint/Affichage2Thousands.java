package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.jeu.AfficheurJeu;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;

import java.util.logging.Logger;

public class Affichage2Thousands {
    private static final Logger LOGGER = Logger.getLogger(AfficheurJeu.class.getPackageName());
    private static JoueurStats[] joueursStats = null;

    private Affichage2Thousands() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Change le tableau de joueurs stats
     * @param joueursStats Les joeurs contenant leur points
     */
    public static void setJoueursStats(JoueurStats... joueursStats) {
        Affichage2Thousands.joueursStats = joueursStats;
    }

    /**
     * Ajoute les statistique des parties aux diverses joueurs
     * @param joueurGagnant Le joueur gagnant de la partie si il existe
     * @param joueursPresent Les joueurs présent en partie
     */
    public static void ajouteStats(Joueur joueurGagnant, Joueur... joueursPresent) {
        for (int i = 0; i < joueursStats.length; i++) {
            if (joueurGagnant != null && joueurGagnant.equals(joueursPresent[i])) {
                joueursStats[i].ajoutePartie(JoueurStats.EtatPartie.GAGNEE, (double) joueursPresent[i].nombrePoints());
            }
            else if (joueurGagnant != null) {
                joueursStats[i].ajoutePartie(JoueurStats.EtatPartie.PERDUE, (double) joueursPresent[i].nombrePoints());
            }
            else {
                joueursStats[i].ajoutePartie(JoueurStats.EtatPartie.NULLE, (double) joueursPresent[i].nombrePoints());
            }
        }
    }

    /**
     * Affiche les statistiques des joueurs après 1000 parties
     */
    public static void afficheJeu2thousands() {
        for (JoueurStats joueurActuel : joueursStats) {
            LOGGER.warning("Le " + joueurActuel.getNomJoueur() + " a gagner " + joueurActuel.getNombrePartiesGagnees()
                    + " parties soit " + joueurActuel.getPourcentagePartiesGagnees() + "% et perdu " + joueurActuel.getNombrePartiesPerdues()
                    + " parties soit " + joueurActuel.getPourcentagePartiesPerdues() + "% donc avec un score moyen de "
                    + joueurActuel.getScoreMoyen());
        }
    }
}
