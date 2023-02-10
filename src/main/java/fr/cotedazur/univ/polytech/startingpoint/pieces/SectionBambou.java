package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Colorable;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Représente une section de bambou.
 * @param couleur la couleur de la section de bambou
 * @author equipe N
 */
public record SectionBambou(@NotNull Couleur couleur) implements Colorable {
    // Accesseurs

    @Override
    public Couleur getCouleur() {
        return couleur;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return "Section de bambou de couleur " + couleur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionBambou that = (SectionBambou) o;
        return couleur == that.getCouleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(couleur);
    }
}
