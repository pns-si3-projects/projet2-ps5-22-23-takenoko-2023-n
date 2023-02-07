package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.joueur.AfficheurJoueur;

import java.util.logging.Logger;

public class AfficheurPersonnage {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurJoueur.class.getPackageName());
    private static final String SEPARATION = "\t\t\t";


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurPersonnage() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation
    /**
     * Affiche le deplacement d'un presonnage
     * @param personnage le personnage deplacé
     */
    public static void deplacePersonnage(Personnage personnage) {
        String str =  SEPARATION +"Le " + personnage + " est déplacé a la position : " + personnage.getPosition() + ". ";
        LOGGER.info(str);

    }
}
