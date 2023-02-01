package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.objectif.MotifNonValideException;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

public class MotifDiagonale extends Motif{

    /**
     * Constructeur par défaut
     * @param parcellesCouleurs liste de Parcelles pour le motifs
     * @throws MotifNonValideException Renvoie cette erreur si 2 parcelles ne sont pas voisines
     */
    public MotifDiagonale(ParcelleCouleur... parcellesCouleurs) throws MotifNonValideException {
        if(parcellesCouleurs.length < 2 || parcellesCouleurs.length > 3) throw new IllegalArgumentException("Trop d'arguments en paramètres");
        tabParcelles = parcellesCouleurs;
    }
}
