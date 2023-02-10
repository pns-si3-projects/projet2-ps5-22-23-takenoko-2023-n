package fr.cotedazur.univ.polytech.startingpoint.joueur;

import java.util.logging.Logger;

/**
 * Affiche les changements effectués par le joueur.
 * @author equipe N
 */
public class AfficheurJoueur {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurJoueur.class.getPackageName());
    private static final String SEPARATION = "\t\t\t";


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurJoueur() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Affiche le début du tour du joueur
     * @param nom le nom du joueur
     */
    public static void debutTour(String nom) {
        String str = "\n" + nom + " :";
        LOGGER.info(str);
    }

    /**
     * Affiche l'action choisie par le joueur
     * @param actionChoisie l'action choisie par le joueur
     */
    public static void actionChoisie(Plaquette.ActionPossible actionChoisie) {
        String str = SEPARATION + "Choix de faire l'action " + actionChoisie.toString().toLowerCase();
        LOGGER.info(str);
    }
}
