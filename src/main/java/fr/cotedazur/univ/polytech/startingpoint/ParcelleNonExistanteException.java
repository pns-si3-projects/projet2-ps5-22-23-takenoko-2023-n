package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Exception dans le cas où la parcelle recherchée n'a pas été crée ou pas ajoutée au plateau
 * @author equipe N
 */
public class ParcelleNonExistanteException extends Exception {
    public ParcelleNonExistanteException(Parcelle parcelle) {
        super("La parcelle de position " + parcelle.getPosition() + " n'existe pas sur le plateau");
    }
}
