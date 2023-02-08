package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.joueur.AfficheurJoueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;

import java.util.logging.Logger;

public class AfficheurPioche {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurJoueur.class.getPackageName());
    private static final String SEPARATION = "\t\t\t";


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurPioche() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Affiche l'objectif pioché
     * @param objectifPioche l'objectif pioché
     */
    public static void piocheObjectif(Objectif objectifPioche) {
        String str = SEPARATION + "L'" + objectifPioche + " est pioche";
        LOGGER.info(str);
    }
}
