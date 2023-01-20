package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import org.jetbrains.annotations.NotNull;

/**
 * Exception si la parcelle à ajouter au plateau existe déjà.
 * @author equipe N
 */
public class ParcelleExistanteException extends Exception {
    public ParcelleExistanteException(@NotNull Parcelle parcelle) {
        super("La parcelle demandée est déjà placée sur le plateau");
    }
}