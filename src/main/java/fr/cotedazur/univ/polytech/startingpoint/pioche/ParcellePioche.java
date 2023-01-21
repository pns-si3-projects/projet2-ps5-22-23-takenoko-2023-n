package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Colorable;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Représente une parcelle dans la pioche.
 * Parcelle de couleur sans position.
 * @param couleur la couleur de la parcelle
 * @author equipe N
 */
public record ParcellePioche(@NotNull Couleur couleur) implements Colorable {
    // Accesseurs

    @Override
    public Couleur getCouleur() {
        return couleur;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return "Parcelle " + couleur + " dans la pioche";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcellePioche that = (ParcellePioche) o;
        return couleur == that.couleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(couleur);
    }
}
