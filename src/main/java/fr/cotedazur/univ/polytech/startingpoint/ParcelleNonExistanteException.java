package fr.cotedazur.univ.polytech.startingpoint;

public class ParcelleNonExistanteException extends Exception{
    public ParcelleNonExistanteException(Parcelle parcelle){
        super("La parcelle de position "+ parcelle.getPosition()+" n'existe pas dans la map");
    }
}
