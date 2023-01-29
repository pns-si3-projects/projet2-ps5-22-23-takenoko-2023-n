package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import org.jetbrains.annotations.NotNull;

/**
 * Exception si la parcelle donnée ne se trouve par sur le plateau.
 * @author equipe N
 */
public class ParcelleNonPoseeException extends Exception {
    public ParcelleNonPoseeException(@NotNull Parcelle parcelle) {
        super("\"" + parcelle + "\"" + " ne se trouve pas sur le plateau");
    }
}
