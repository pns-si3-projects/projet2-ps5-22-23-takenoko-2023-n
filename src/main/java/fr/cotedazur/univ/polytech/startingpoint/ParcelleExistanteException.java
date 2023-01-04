package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Exception dans le cas où la parcelle à ajouter au plateau existe déjà
 * @author equipe N
 */
public class ParcelleExistanteException extends Exception {
    public ParcelleExistanteException(Parcelle parcelle) {
        super("La parcelle de position " + parcelle.getPosition() + " est déjà existante");
    }
}