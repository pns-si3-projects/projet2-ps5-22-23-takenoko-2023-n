package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.joueur.AfficheurJoueur;

import java.util.logging.Logger;

public class AfficheurParcelle {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurJoueur.class.getPackageName());
    private static final String SEPARATION = "\t\t\t";


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurParcelle() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Affiche la pose d'une parcelle
     * @param couleur la couleur de la parcelle pose
     * @param position la position de la parcelle pose
     */
    public static void parcellePose(Couleur couleur, Position position) {
        String str = SEPARATION + "Une parcelle de couleur " + couleur + " a été posé a la position : " + position;
        LOGGER.info(str);
    }
}
