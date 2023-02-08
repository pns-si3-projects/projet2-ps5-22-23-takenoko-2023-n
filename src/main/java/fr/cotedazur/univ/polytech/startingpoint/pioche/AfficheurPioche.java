package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.joueur.AfficheurJoueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;

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
    public static void piocheObjectif(Objectif objectifPiochee){
        Class classObjectif = objectifPiochee.getClass();
        String typeObjectif;
        if (classObjectif.equals(ObjectifPanda.class)) typeObjectif = "Panda";
        else if (classObjectif.equals(ObjectifJardinier.class)) typeObjectif = "Jardinier";
        else typeObjectif = "Parcelle";
        String str = SEPARATION + "Un objectif " + typeObjectif + " est pioché";
        LOGGER.info(str);
    }

    // A compléter
}
