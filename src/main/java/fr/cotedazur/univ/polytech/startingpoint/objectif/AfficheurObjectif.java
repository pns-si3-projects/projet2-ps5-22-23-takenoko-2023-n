package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.joueur.AfficheurJoueur;

import java.util.logging.Logger;

/**
 * Affiche les changements effectués sur les objectifs.
 * @author equipe N
 */
public class AfficheurObjectif {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurJoueur.class.getPackageName());
    private static final String SEPARATION = "\t\t\t";


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurObjectif() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Affiche l'objectif terminé
     * @param objectifRealise l'objectif réalisé
     */
    public static void finObjectif(Objectif objectifRealise) {
        String str = SEPARATION + "L'" + objectifRealise + " est realise";
        LOGGER.info(str);
    }
}
