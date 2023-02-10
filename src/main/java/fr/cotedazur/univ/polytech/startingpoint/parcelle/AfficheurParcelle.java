package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.joueur.AfficheurJoueur;

import java.util.logging.Logger;

/**
 * Affiche les changements effectués sur les parcelles.
 * @author equipe N
 */
public class AfficheurParcelle {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(AfficheurParcelle.class.getPackageName());
    private static final String SEPARATION = "\t\t\t";


    // Définition d'un constructeur privé pour éviter les instanciations

    private AfficheurParcelle() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Affiche la pose d'une parcelle
     * @param parcelleCouleur la parcelle posée
     */
    public static void parcellePose(ParcelleCouleur parcelleCouleur) {
        String str = SEPARATION + "Une parcelle de couleur "
                + parcelleCouleur.getCouleur() + " a ete posee en " + parcelleCouleur.getPosition();
        LOGGER.info(str);
    }
}
