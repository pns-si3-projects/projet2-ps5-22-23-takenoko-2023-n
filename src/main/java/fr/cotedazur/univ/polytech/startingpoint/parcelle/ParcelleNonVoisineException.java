package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import org.jetbrains.annotations.NotNull;

/**
 * Exception si la parcelle donnée en tant que voisine n'est pas voisine de la parcelle ciblée
 * @author equipe N
 */
public class ParcelleNonVoisineException extends Exception {
    public ParcelleNonVoisineException(@NotNull Parcelle parcelleCible, @NotNull Parcelle parcelleNonVoisine) {
        super("La parcelle en " + parcelleNonVoisine.position()
                + " n'est pas voisine de la parcelle en " + parcelleCible.position());
    }
}

