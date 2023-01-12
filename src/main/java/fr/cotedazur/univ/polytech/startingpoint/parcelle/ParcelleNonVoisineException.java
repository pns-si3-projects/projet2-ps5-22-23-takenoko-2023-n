package fr.cotedazur.univ.polytech.startingpoint.parcelle;

/**
 * Exception dans le cas où la parcelle donnée en tant que voisine n'est pas voisine de la parcelle ciblée
 * @author equipe N
 */
public class ParcelleNonVoisineException extends Exception {
    public ParcelleNonVoisineException(Parcelle parcelleCible, Parcelle parcelleNonVoisine) {
        super("La parcelle en " + parcelleNonVoisine.getPosition()
                + " n'est pas voisine de la parcelle en " + parcelleCible.getPosition());
    }
}

