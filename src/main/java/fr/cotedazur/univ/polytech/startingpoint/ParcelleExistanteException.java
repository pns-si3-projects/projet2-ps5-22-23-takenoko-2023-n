package fr.cotedazur.univ.polytech.startingpoint;

public class ParcelleExistanteException extends Exception{
    public ParcelleExistanteException(Parcelle parcelle){
        super("La parcelle est deja existante");
    }
}
