package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.joueur.AfficheurJoueur;

import java.util.logging.Logger;

/**
 * Affiche les changements effectués sur les personnages.
 * @author equipe N
 */
public class AfficheurPersonnage {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurPersonnage.class.getPackageName());
    private static final String SEPARATION = "\t\t\t";


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurPersonnage() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Affiche le déplacement d'un personnage
     * @param personnage le personnage déplacé
     */
    public static void deplacePersonnage(Personnage personnage) {
        String str =  SEPARATION + "Le " + personnage + " est deplace en " + personnage.getPosition();
        LOGGER.info(str);
    }
}
