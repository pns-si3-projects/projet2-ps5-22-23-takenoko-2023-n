package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet d'enregistrer une ParcelleCouleur avec ses parcelles voisines
 * @author equipe N
 */
public class ParcelleEtVoisines {
    /**
     * C'est la ParcelleCouleur cible
     */
    private ParcelleCouleur parcelleCouleur;

    /**
     * Liste des voisines de la ParcelleCouleur
     */
    private List<ParcelleCouleur> parcellesVoisines;

    /**
     * Constructeur par défaut
     * Créer l'ensemble pour enregistrer la ParcelleCouleur avec ses voisines
     * @param parcelleCouleur qui est la ParcelleCouleur cible
     * @param parcellesVoisines qui sont les parcelles voisines de la ParcelleCouleur
     */
    public ParcelleEtVoisines(ParcelleCouleur parcelleCouleur, List<ParcelleCouleur> parcellesVoisines) {
        this.parcelleCouleur = parcelleCouleur;
        this.parcellesVoisines = parcellesVoisines;
    }

    /**
     * Constructeur pour l'Etang qui ne contient pas de voisines au début
     * @param parcelleCouleur qui devrait être l'Etang car les autres parcelles auront forcément une(des) voisine(s)
     */
    public ParcelleEtVoisines(ParcelleCouleur parcelleCouleur) {
        this(parcelleCouleur, new ArrayList<>());
    }

    public ParcelleCouleur getParcelleCouleur() {
        return parcelleCouleur;
    }

    public List<ParcelleCouleur> getParcellesVoisines() {
        return parcellesVoisines;
    }
}
