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
        addParcelle(etangEtVoisines);
    }

    /**
     * Getter des Parcelles en jeu
     * @return Retourne la liste de Parcelle en jeu
     */
    public List<ParcelleEtVoisines> getParcelles() {
        return parcelles;
    }

    /**
     * Getter de la liste de Position disponible en jeu
     * @return Retourne la liste de Position disponible pour mettre une parcelle
     */
    public List<Position> getPositionDisponible(){
        return positionDisponible;
    }

    /**
     * Change a chacune des deux parcelles disponibles leur voisins (soit de changer une parcelleDisponible en parcelleCouleur)
     * @param parcelle qui vient d'etre ajoute a la liste
     * @param parcelleVoisine qui est dans la liste
     * @throws ParcelleNonVoisineException
     */
    private void setVoisin(ParcelleEtVoisines parcelle, ParcelleEtVoisines parcelleVoisine) throws ParcelleNonVoisineException{
        parcelle.addVoisine(parcelleVoisine);
        parcelleVoisine.addVoisine(parcelle);
    }

    /**
     * Verifie dans chaque parcelle de la liste si la parcelle ajouter peut etre un des voisins de ces parcelles
     * @param parcelleACheck
     * @throws ParcelleNonVoisineException
     */
    public void addVoisinParcelle(ParcelleEtVoisines parcelleACheck) throws ParcelleNonVoisineException{
        for(ParcelleEtVoisines parcelleVoisin : parcelles){
            Parcelle parcelle = parcelleVoisin.getParcelleCible();
            if(parcelleACheck.peutEtreVoisine(parcelle)){
                setVoisin(parcelleACheck,parcelleVoisin);
                break;
            }
        }
    }

    /**
     * Ajoute les nouvelles positions Disponible pour la parcelle ajouter dans la liste
     * @param parcelleEtVoisines qui vient d'etre ajouter a la liste
     */
    private void addNewPosition(ParcelleEtVoisines parcelleEtVoisines){
        for(Parcelle parcelle : parcelleEtVoisines.getParcellesVoisines()){
            if(parcelle.getClass() == ParcelleDisponible.class){
                if(!positionDisponible.contains(parcelle.getPosition())){
                    positionDisponible.add(parcelle.getPosition());
                }
            }
        }
    }

    /**
     * Ajoute une parcelle au plateau
     * @param parcelle a ajouter
     */

    public void addParcelle(ParcelleEtVoisines parcelle) {
        try {
            addVoisinParcelle(parcelle);
            positionDisponible.remove(parcelle.getParcelleCible().getPosition());
            addNewPosition(parcelle);

        } catch (ParcelleNonVoisineException e) {
            System.out.println(e);
        }
    }
}
