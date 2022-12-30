package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Renvoie une exception si la parcelle n'est pas voisine a celle qu'on veut ajouter
 * @author equipe N
 */
public class ParcelleNonVoisineException extends Exception {
    public ParcelleNonVoisineException(Parcelle parcelleCible, Parcelle parcelleNonVoisine) {
        super("La parcelle en " + parcelleNonVoisine.getPosition()
                + " n'est pas voisine de la parcelle en " + parcelleCible.getPosition());
    }
}

