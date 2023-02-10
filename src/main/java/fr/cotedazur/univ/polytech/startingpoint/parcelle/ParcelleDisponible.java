package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Représente un emplacement de parcelle disponible.
 * @param position la position de la parcelle
 * @author N
 */
public record ParcelleDisponible(@NotNull Position position) implements Parcelle {
    // Accesseurs

    /**
     * Renvoie la position de la parcelle
     * @return la position de la parcelle
     */
    @Override
    public Position getPosition() {
        return position;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return "Parcelle disponible en " + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcelleDisponible that = (ParcelleDisponible) o;
        return getPosition().equals(that.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
