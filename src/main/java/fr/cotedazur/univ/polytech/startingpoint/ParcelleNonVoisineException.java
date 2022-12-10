package fr.cotedazur.univ.polytech.startingpoint;

public class ParcelleNonVoisineException extends Exception {
    public ParcelleNonVoisineException(Parcelle parcelleCible, Parcelle parcelleNonVoisine) {
        super("La parcelle en " + parcelleNonVoisine.getPosition()
                + " n'est pas voisine de la parcelle en " + parcelleCible.getPosition());
    }
}
