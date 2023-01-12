package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Colorable;
import fr.cotedazur.univ.polytech.startingpoint.Couleur;

import java.util.Objects;

/**
 * Record représentant une section de bambou
 * @param couleur est la couleur de la section de bambou
 * @author equipe N
 */
public record SectionBambou(Couleur couleur) implements Colorable {
    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param couleur la couleur de la section du bambou
     */
    public SectionBambou {
        if (couleur == null) throw new IllegalArgumentException("La couleur ne doit pas être null");
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
        return couleur == that.couleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(couleur);
    }
}
