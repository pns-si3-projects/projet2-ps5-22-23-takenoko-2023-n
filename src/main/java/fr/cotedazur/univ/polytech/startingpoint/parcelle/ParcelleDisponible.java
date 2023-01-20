package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Représente un emplacement de parcelle disponible
 * @author N
 */
public class ParcelleDisponible implements Parcelle {
    // Définition des attributs

    private final Position position;


    // Définition des constructeurs

    /**
     * Construit une représentation de parcelle disponible avec une position
     * @param position la position de la parcelle
     */
    public ParcelleDisponible(@NotNull Position position) {
        this.position = position;
    }


    // Accesseurs

    /**
     * Renvoie la position de la parcelle
     * @return la position de la parcelle
     */
    @Override
    public Position position() {
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
        return Objects.equals(position(), that.position());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position());
    }
}
