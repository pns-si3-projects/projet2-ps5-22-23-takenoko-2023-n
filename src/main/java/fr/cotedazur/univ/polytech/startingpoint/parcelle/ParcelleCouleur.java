package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Colorable;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Représente une parcelle posée sur le plateau.
 * @param position la position de la parcelle
 * @param couleur la couleur de la parcelle
 * @author equipe N
 */
public record ParcelleCouleur(@NotNull Position position, @NotNull Couleur couleur) implements Parcelle, Colorable {
    // Accesseurs

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Couleur getCouleur() {
        return couleur;
    }


    // Méthodes toString et equals
    @Override
    public String toString() {
        return "Parcelle de couleur " + couleur + " en " + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcelleCouleur that = (ParcelleCouleur) o;
        return position.equals(that.getPosition()) && couleur == that.getCouleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, couleur);
    }
}
