package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Represente le plateau de jeu
 * @author equipe N
 */
public class Plateau {
    private final List<ParcelleEtVoisines> parcelles;

    /**
     * Constructeur par défaut qui permet d'initialiser le jeu avec un Etang
     */
    public Plateau() {
        Etang etang = new Etang();
        ParcelleEtVoisines etangEtVoisines = new ParcelleEtVoisines(etang);
        parcelles = new ArrayList<>();
        addParcelle(etangEtVoisines);
    }

    public List<ParcelleEtVoisines> getParcelles() {
        return parcelles;
    }

    public void addParcelle(ParcelleEtVoisines parcelleEtVoisines) {
        parcelles.add(parcelleEtVoisines);
    }

    public void addParcelle(ParcelleCouleur parcelleCouleur, List<Parcelle> voisines) {
        try {
            ParcelleEtVoisines parcelleEtVoisines = new ParcelleEtVoisines(parcelleCouleur, voisines);
            addParcelle(parcelleEtVoisines);
        } catch (ParcelleNonVoisineException e) {
            System.out.println(e);
        }
    }
}
