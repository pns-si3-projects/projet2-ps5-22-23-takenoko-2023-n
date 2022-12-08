package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet d'enregistrer une Parcelle avec ses parcelles voisines
 * @author equipe N
 */
public class ParcelleEtVoisines {
    /** Parcelle cible qui peut être un Etang ou une ParcelleCouleur */
    private Parcelle parcelle;

    /** Liste des voisines de la parcelle cible */
    private List<Parcelle> parcellesVoisines;

    /**
     * Constructeur par defaut
     * Creer l'ensemble pour enregistrer la Parcelle avec ses voisines
     * @param parcelle qui est la Parcelle cible
     * @param parcellesVoisines qui sont les parcelles voisines de la Parcelle
     */
    public ParcelleEtVoisines(Parcelle parcelle, List<Parcelle> parcellesVoisines) {
        this.parcelle = parcelle;
        this.parcellesVoisines = parcellesVoisines;
    }

    /**
     * Constructeur pour l'Etang qui ne contient pas de voisines au debut du jeu
     * @param etang qui est l'Etang du jeu et ne contient pas de voisines au debut
     */
    public ParcelleEtVoisines(Etang etang) {
        this(etang, new ArrayList<>());
    }

    public Parcelle getParcelle() {
        return parcelle;
    }

    public List<Parcelle> getParcellesVoisines() {
        return parcellesVoisines;
    }

    public void addVoisine(Parcelle parcelle) {
        parcellesVoisines.add(parcelle);
    }
}
