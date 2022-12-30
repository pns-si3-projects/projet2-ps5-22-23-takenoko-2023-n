package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Classe permettant de Gérer l'ajout et la modification des parcelles du plateau
 * @author equipe N
 * @version 1.0
 */
public class GestionnaireModificationPlateau {
    public GestionnaireModificationPlateau(){

    }
    /**
     * Permet de renvoyer une ParcelleDisponible a un indice particulier donc a une position particuliere
     * @param indiceTab indice pour renvoyer une certaine ParcelleDisponible
     * @param positionParcelle cette position sert a adapter l'ajout de la parcelle disponible
     * @return Retourne une parcelleDisponible donc une parcelle avec une position
     */
    public ParcelleDisponible addParcelleVide(int indiceTab, Position positionParcelle){
        int x = positionParcelle.getX();
        int y = positionParcelle.getY();
        return switch (indiceTab){
            case 0 -> new ParcelleDisponible(new Position(x+1,y+1));
            case 1 -> new ParcelleDisponible(new Position(x+2,y));
            case 2 -> new ParcelleDisponible(new Position(x+1,y-1));
            case 3 -> new ParcelleDisponible(new Position(x-1,y-1));
            case 4 -> new ParcelleDisponible(new Position(x-2,y));
            case 5 -> new ParcelleDisponible(new Position(x-1,y+1));
            default -> throw new IllegalArgumentException("Pas bon argument en parametre");
        };
    }

    /**
     * Verifie si la Parcelle en parametre peut être voisine de la Parcelle cible
     * @param parcelle est la Parcelle a verifier si elle est voisine de la cible
     * @return si la Parcelle en parametre est la voisine de la Parcelle cible
     */
    private boolean peutEtreVoisine(Parcelle parcelle,Parcelle parcellePossibleVoisin) {
        int xV = parcellePossibleVoisin.getPosition().getX();
        int yV = parcellePossibleVoisin.getPosition().getY();
        int xC = parcelle.getPosition().getX();
        int yC = parcelle.getPosition().getY();

        if (yV == yC && (xV-2 == xC || xV+2 == xC)) return true; // Si proche et sur la même ligne
        return (yV - 1 == yC || yV + 1 == yC) && (xV - 1 == xC || xV + 1 == xC); // Si proche au-dessus ou en-dessous
    }

    /**
     * Renvoi une liste de voisin d'une parcelle
     * @param parcelle
     * @return Retourne la liste de voisin
     * @throws ParcelleExistanteException Renvoi une exception si la parcelle est existante
     */
    public List<Parcelle> getParcelleVoisin(Parcelle parcelle) throws ParcelleExistanteException {
        Map<Parcelle,Parcelle[]> listParcelle = Main.PLATEAU.getMap();
        Iterator<Parcelle> iterateurParcelle = listParcelle.keySet().iterator();
        List<Parcelle> parcelleVoisin = new ArrayList<>();

        while (iterateurParcelle.hasNext()){
            Parcelle parcellePossibleVoisin = iterateurParcelle.next();
            if(parcelle.equals(parcellePossibleVoisin)) throw new ParcelleExistanteException(parcelle);
            else if(peutEtreVoisine(parcelle,parcellePossibleVoisin)){
                parcelleVoisin.add(parcellePossibleVoisin);
            }
        }

        return parcelleVoisin;
    }

    /**
     * Renvoi la position a mettre dans le tableau des voisins de la parcelle
     * @param positionParcelleCible La position de la parcelle
     * @param positionParcelleVoisin La position du voisin de la parcelle principal
     * @return Renvoi l'indice a mettre dans le tableau des voisins
     */
    private int positionTabVoisin(Position positionParcelleCible,Position positionParcelleVoisin){
        int xV = positionParcelleVoisin.getX();
        int yV = positionParcelleVoisin.getY();
        int xC = positionParcelleCible.getX();
        int yC = positionParcelleCible.getY();

        if(yV == yC){ // Si proche et sur la même ligne
            if(xV - 2 == xC) return 1;
            else if(xV+2 == xC) return 4;
        }
        else{ // Si proche au-dessus ou en-dessous
            if(yV - 1 == yC){
                if(xV - 1 == xC) return 0;
                else if(xV + 1 == xC) return 5;
            }
            else if(yV + 1 == yC){
                if(xV - 1 == xC) return 2;
                else if(xV + 1 == xC) return 3;
            }
        }
        return -1;
    }

    /**
     * Crée la liste des parcelles de voisins à l'aide des voisins dans le plateau et rempli les autres parcelles de ParcelleDisponible
     * @param parcelle la parcelle a ajouté
     * @param listParcelleVoisin La liste des voisins existant dans le plateau
     * @return Renvoie la liste des voisins de la parcelle
     * @throws ParcelleNonVoisineException Renvoie une exception si la parcelle ne peut pas etre voisine
     */
    public Parcelle[] addVoisinParcelle(Parcelle parcelle, List<Parcelle> listParcelleVoisin) throws ParcelleNonVoisineException{
        Parcelle[] parcellesVoisines = new Parcelle[6];

        for(Parcelle parcelleVoisin : listParcelleVoisin){
            int positionTabVoisin = positionTabVoisin(parcelle.getPosition(),parcelleVoisin.getPosition());
            parcellesVoisines[positionTabVoisin] = parcelleVoisin;
        }

        for(int i = 0;i<6;i++){
            if(parcellesVoisines[i] == null){
                parcellesVoisines[i] = addParcelleVide(i,parcelle.getPosition());
            }
        }

        return parcellesVoisines;
    }
}
