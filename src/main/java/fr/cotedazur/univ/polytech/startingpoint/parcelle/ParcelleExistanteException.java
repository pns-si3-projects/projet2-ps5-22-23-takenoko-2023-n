package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import org.jetbrains.annotations.NotNull;

/**
 * Exception si la parcelle à ajouter au plateau existe déjà.
 * @author equipe N
 */
public class ParcelleExistanteException extends Exception {
    /**
     * Construit le message de l'exception
     * @param parcelle la parcelle qui doit être ajoutée au plateau
     */
    public ParcelleExistanteException(@NotNull Parcelle parcelle) {
        super("La " + parcelle + " demandee est deja placee sur le plateau");
    }
}