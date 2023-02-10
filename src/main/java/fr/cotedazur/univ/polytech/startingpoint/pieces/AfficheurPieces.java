package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.joueur.AfficheurJoueur;

import java.util.logging.Logger;

/**
 * Affiche les changements effectués sur les irrigations.
 * @author equipe N
 */
public class AfficheurPieces {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurJoueur.class.getPackageName());
    private static final String SEPARATION = "\t\t\t";


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurPieces() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Affiche la pose d'une irrigation
     * @param irrigation l'irrigation posée
     */
    public static void poseIrrigation(Irrigation irrigation) {
        String str = SEPARATION + irrigation + " posee";
        LOGGER.info(str);
    }
}
