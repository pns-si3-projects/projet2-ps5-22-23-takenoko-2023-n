package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;

import java.util.logging.Logger;

/**
 * Affiche les changements dans le jeu.
 * @author equipe N
 */
public class AfficheurJeu {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurJeu.class.getPackageName());
    private static final String SEPARATION = "**********".repeat(6);


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurJeu() {
        throw new IllegalStateException("Utility class");
    }
    

    // Méthodes d'utilisation

    /**
     * Affiche l'état d'initialisation du jeu
     */
    public static void initialisation() {
        LOGGER.info("Initialisation du jeu : étang, panda et jardinier en (0,0).");
    }

    /**
     * Affiche le début du tour donné
     * @param nbTours le numéro du tour
     */
    public static void debutTour(int nbTours) {
        String str = "\n\n" + SEPARATION +
                "\tTour n°" + nbTours + "\t" +
                SEPARATION;
        LOGGER.info(str);
    }

    /**
     * Affiche le début du dernier tour
     */
    public static void debutDernierTour() {
        String str = "\n\n" + SEPARATION +
                "\tDernier tour\t" +
                SEPARATION;
        LOGGER.info(str);
    }

    /**
     * Affiche la victoire du joueur gagnant
     * @param joueur le joueur gagnant
     */
    public static void victoire(Joueur joueur) {
        String str = "\n\nBravo à " + joueur.getNom() + " d'avoir remporté la partie avec "
                + joueur.nombreObjectifsTermines() + " objectifs terminés pour " + joueur.nombrePoints() + " points !";
        LOGGER.info(str);
    }

    /**
     * Affiche le partage de la victoire par tous les joueurs
     */
    public static void victoire() {
        LOGGER.info("\n\nBravo à tous les joueurs d'avoir participé et à bientôt pour une autre partie !");
    }
}
