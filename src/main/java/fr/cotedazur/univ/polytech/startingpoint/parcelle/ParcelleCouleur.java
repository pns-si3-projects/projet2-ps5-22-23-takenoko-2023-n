package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Colorable;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

import java.util.Objects;

/**
 * Représente une parcelle posée sur le plateau
 * @param position la position de la parcelle
 * @param couleur la couleur de la parcelle
 * @author equipe N
 */
public record ParcelleCouleur(Position position, Couleur couleur) implements Parcelle, Colorable {
    // Définition des constructeurs

    /**
     * Contruit une parcelle avec une position et une couleur
     * @param position la position de la parcelle
     * @param couleur la couleur de la parcelle
     */
    public ParcelleCouleur {
        if (position == null) throw new IllegalArgumentException("La position ne doit pas être null");
        if (couleur == null) throw new IllegalArgumentException("La couleur ne doit pas être null");
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
        return position.equals(that.position()) && couleur == that.couleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, couleur);
    }
}
