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
    private List<ParcelleCouleur> parcelleAvecBambou;

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
        parcelleAvecBambou=new ArrayList<>();
        addParcelle(etangEtVoisines);
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
    public List<ParcelleEtVoisines> getVoisinPlateau(ParcelleCouleur parcelle) throws ParcelleExistanteException{
        List<ParcelleEtVoisines> listParcelle = new ArrayList<>();
        for(ParcelleEtVoisines parcelleListe : parcelles){
            if(parcelle.getPosition().equals(parcelle)) throw new ParcelleExistanteException(parcelle);
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
        for(int i = 0;i < 5; i++){
            if(listParcelleVoisin[i].getClass() == ParcelleDisponible.class){
                Position positionParcelleVoisin = listParcelleVoisin[i].getPosition();
                if(listParcelleVoisin[i+1].getClass() == ParcelleCouleur.class && !positionDisponible.contains(positionParcelleVoisin)){
                    positionDisponible.add(positionParcelleVoisin);
                }
            }
        }
        if(listParcelleVoisin[5].getClass() == ParcelleDisponible.class){ // Pour verifier si la parcelle peut etre ajouter au nouvelle position
            Position positionParcelleVoisin = listParcelleVoisin[5].getPosition();
            if(listParcelleVoisin[0].getClass() == ParcelleCouleur.class && !positionDisponible.contains(positionParcelleVoisin)){
                positionDisponible.add(positionParcelleVoisin);
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

        } catch (ParcelleNonVoisineException | ParcelleExistanteException e) {
            System.out.println(e);
        }
    }
    public void addParcelleAvecBambou(ParcelleCouleur parcelleCouleur){
        if(parcelleCouleur.addBambou(parcelleCouleur.getPosition())){
            parcelleAvecBambou.add(parcelleCouleur);
        }
    }
}
