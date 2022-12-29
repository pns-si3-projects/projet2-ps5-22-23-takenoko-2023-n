package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Une classe qui permet de remplacer les valeur null a des parcelles vide mais avec une position
 * @author N
 * @version 1.0
 */
public class ParcelleDisponible implements Parcelle{
    private final Position position;

    /**
     * Constructeur par default de la classe Parcelle Disponible
     * @param p Position de la parcelle
     */
    public ParcelleDisponible(Position p){
        if(p == null) throw new NullPointerException("La position ne doit pas etre vide");
        position = p;
    }

    /**
     * Getter de la Postion
     * @return La position de la Parcelle
     */
    @Override
    public Position getPosition(){
        return position;
    }
}
