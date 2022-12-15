package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Represente le plateau de jeu
 * @author equipe N
 * @version 2.0
 */
public class Plateau {
    private final List<ParcelleEtVoisines> parcelles;
    private final List<Position> positionDisponible;

    /**
     * Constructeur par défaut qui permet d'initialiser le jeu avec un Etang
     */
    public Plateau() {
        Etang etang = new Etang();
        ParcelleEtVoisines etangEtVoisines = new ParcelleEtVoisines(etang);
        parcelles = new ArrayList<>();
        positionDisponible = new ArrayList<>();
        parcelles.add(etangEtVoisines);
        for(int i = 0; i<6;i++){
            Parcelle parcellePossible = etangEtVoisines.getParcellesVoisines()[i];
            positionDisponible.add(parcellePossible.getPosition());
        }
    }

    /**
     * Getter des Parcelles en jeu
     *
     * @return Retourne la liste de Parcelle en jeu
     */
    public List<ParcelleEtVoisines> getParcelles() {
        return parcelles;
    }

    /**
     * Getter de la liste de Position disponible en jeu
     * @return Retourne la liste de Position disponible pour mettre une parcelle
     */
    public List<Position> getPositionDisponible() {
        return positionDisponible;
    }

    /**
     * Renvoi la liste de parcelleVoisine a cote de la parcelle qu'on veut ajouter
     * @param parcelle parcelle a ajouter
     * @return Retourne la liste de ParcelleVoisine
     */
    public List<ParcelleEtVoisines> getVoisinPlateau(ParcelleCouleur parcelle){
        List<ParcelleEtVoisines> listParcelle = new ArrayList<>();
        for(ParcelleEtVoisines parcelleListe : parcelles){
            if(parcelleListe.peutEtreVoisine(parcelle)){
                listParcelle.add(parcelleListe);
            }
        }
        return listParcelle;
    }

    /**
     * Ajoute a la liste de position disponible, les nouvelles positions possibles avec la nouvelle parcelle
     * @param parcelle parcelle ajouter
     */
    private void addNewPosition(ParcelleEtVoisines parcelle){
        Parcelle[] listParcelleVoisin = parcelle.getParcellesVoisines();
        for(int i = 0;i < 3; i++){
            if(listParcelleVoisin[i].getClass() == ParcelleDisponible.class){
                Position positionParcelleVoisin = listParcelleVoisin[i].getPosition();
                if(listParcelleVoisin[i+3].getClass() == ParcelleCouleur.class && !positionDisponible.contains(positionParcelleVoisin)){
                    positionDisponible.add(positionParcelleVoisin);
                }
            }
        }
        for(int i = 3;i < 6; i++){
            if(listParcelleVoisin[i].getClass() == ParcelleDisponible.class){
                Position positionParcelleVoisin = listParcelleVoisin[i].getPosition();
                if(listParcelleVoisin[i-3].getClass() == ParcelleCouleur.class && !positionDisponible.contains(positionParcelleVoisin)){
                    positionDisponible.add(positionParcelleVoisin);
                }
            }
        }
    }

    /**
     * Ajoute une nouvelle Parcelle au plateau
     * @param parcelle qu'on souhaite ajouter
     */
    public void addParcelle(ParcelleCouleur parcelle) {
        try {
            List<ParcelleEtVoisines> listVoisins = getVoisinPlateau(parcelle);
            ParcelleEtVoisines parcelleEtVoisines = new ParcelleEtVoisines(parcelle,listVoisins);
            parcelles.add(parcelleEtVoisines);
            positionDisponible.remove(parcelle.getPosition());
            addNewPosition(parcelleEtVoisines);

        } catch (ParcelleNonVoisineException e) {
            System.out.println(e);
        }
    }
}
