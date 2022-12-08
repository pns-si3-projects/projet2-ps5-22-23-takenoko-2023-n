package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Represente le plateau de jeu
 * @author equipe N
 */
public class Plateau {
    private List<ParcelleEtVoisines> parcelles;

    /**
     * Constructeur par défaut qui permet d'initialiser le jeu avec un Etang
     */
    public Plateau() {
        Etang etang = new Etang();
        ParcelleEtVoisines etangEtVoisines = new ParcelleEtVoisines(etang);
        this.parcelles = new ArrayList<>();
    }

    public List<ParcelleEtVoisines> getParcelles() {
        return parcelles;
    }

    public void addParcelle(ParcelleEtVoisines parcelleEtVoisines) {
        parcelles.add(parcelleEtVoisines);
    }

    public void addParcelle(ParcelleCouleur parcelleCouleur, List<Parcelle> voisines) {
        addParcelle(new ParcelleEtVoisines(parcelleCouleur, voisines));
    }
}
