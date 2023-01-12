package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;

import java.util.Objects;

/**
 * Record représentant une parcelle dans la pioche → parcelle sans position
 * @param couleur est la couleur de la parcelle
 * @author equipe N
 */
public record ParcellePioche(Couleur couleur) {
    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param couleur est la couleur de la parcelle
     */
    public ParcellePioche {
        if (couleur == null) throw new IllegalArgumentException("La couleur ne doit pas être null");
    }


    // Méthodes toString et equals
    @Override
    public String toString() {
        return "Parcelle de couleur " + couleur;
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
