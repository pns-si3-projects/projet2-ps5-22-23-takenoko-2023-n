package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet d'enregistrer une Parcelle avec ses parcelles voisines
 * @author equipe N
 */
public class ParcelleEtVoisines {
    /** Parcelle cible qui peut être un Etang ou une ParcelleCouleur */
    private final Parcelle parcelleCible;

    /** Liste des voisines de la parcelle cible */
    private final List<Parcelle> parcellesVoisines;

    /**
     * Constructeur par defaut
     * Creer l'ensemble pour enregistrer la Parcelle avec ses voisines
     * @param parcelle qui est la Parcelle cible
     * @param parcellesVoisines qui sont les parcelles voisines de la Parcelle
     */
    public ParcelleEtVoisines(Parcelle parcelle, List<Parcelle> parcellesVoisines) throws ParcelleNonVoisineException {
        parcelleCible = parcelle;
        this.parcellesVoisines = new ArrayList<>();
        for (Parcelle parcelleVoisine : parcellesVoisines) {
            addVoisine(parcelleVoisine);
        }
    }

    /**
     * Constructeur pour l'Etang qui ne contient pas de voisines au debut du jeu
     * @param etang qui est l'Etang du jeu et ne contient pas de voisines au debut
     */
    public ParcelleEtVoisines(Etang etang) {
        parcelleCible = etang;
        parcellesVoisines = new ArrayList<>();
    }

    public Parcelle getParcelleCible() {
        return parcelleCible;
    }

    public List<Parcelle> getParcellesVoisines() {
        return parcellesVoisines;
    }

    /**
     * Verifie si la Parcelle en parametre peut être voisine de la Parcelle cible
     * @param parcelle est la Parcelle a verifier si elle est voisine de la cible
     * @return si la Parcelle en parametre est la voisine de la Parcelle cible
     */
    public boolean peutEtreVoisine(Parcelle parcelle) {
        int xV = parcelle.getPosition().getX();
        int yV = parcelle.getPosition().getY();
        int xC = parcelleCible.getPosition().getX();
        int yC = parcelleCible.getPosition().getY();

        if (yV == yC && (xV-2 == xC || xV+2 == xC)) return true; // Si proche et sur la même ligne
        return (yV - 1 == yC || yV + 1 == yC) && (xV - 1 == xC || xV + 1 == xC); // Si proche au-dessus ou en-dessous
    }

    /**
     * Permet d'ajouter une Parcelle voisine a la Parcelle cible
     * @param parcelle est une Parcelle voisine
     */
    public void addVoisine(Parcelle parcelle) throws ParcelleNonVoisineException {
        if (peutEtreVoisine(parcelle)) parcellesVoisines.add(parcelle);
        else throw new ParcelleNonVoisineException(parcelleCible, parcelle);
    }
}
