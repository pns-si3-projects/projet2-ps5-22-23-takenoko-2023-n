package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Renvoi une exception si la parcelle existe
 * @author equipe N
 */
public class ParcelleExistanteException extends Exception{
    public ParcelleExistanteException(Parcelle parcelle){
        super("La parcelle de position "+parcelle.getPosition()+" est deja existante");
    }
}