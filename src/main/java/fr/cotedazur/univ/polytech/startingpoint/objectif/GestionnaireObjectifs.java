package fr.cotedazur.univ.polytech.startingpoint.objectif;


import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotif;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;

public class GestionnaireObjectifs {

    // Définition d'un constructeur privé pour éviter les instanciations
    private GestionnaireObjectifs() {
        throw new IllegalStateException("Utility Class");
    }

    // Méthode d'utilisation

    /**
     * Renvoie {@code true} si les objectifs jardinie sont bien réalisées
     * @param bambous Le tableau des bambous présent en jeu
     * @param objectifJardinier L'objectif jardinier à réaliser
     * @return {@code true} si les objectifs jardinie sont bien réalisées
     */
    public static boolean checkObjectifJardinier(Bambou[] bambous, ObjectifJardinier objectifJardinier) {
        int count = 0;
        int nombreBambousAFaireBambouObjectif = (objectifJardinier.getSchema() > 4)?
                objectifJardinier.getSchema() / 3 : 1;
        int tailleBambouObjectif = (objectifJardinier.getSchema() > 4)?
                3 : objectifJardinier.getSchema();
        Couleur couleurBambouObjectif = objectifJardinier.getCouleur();

        for (Bambou bambou: bambous) {
            if (bambou.getTailleBambou() == tailleBambouObjectif && bambou.getCouleur() == couleurBambouObjectif) {
                count++;
            }
        }
        return count >= nombreBambousAFaireBambouObjectif;
    }

    /**
     * Renvoie {@code true} si l'objectif parcelle est bien réaliser
     * @param tableauParcellePlateau Le tableau contenant toutes les parcelles du Plateau
     * @param objectifParcelle L'objectif parcelle à réaliser
     * @return {@code true} si l'objectif parcelle est bien réaliser
     */
    public static boolean checkObjectifParcelle(Parcelle[] tableauParcellePlateau, ObjectifParcelle objectifParcelle) {
        return GestionnairePossibiliteMotif.checkMotifInBoard(tableauParcellePlateau, objectifParcelle);
    }
}
