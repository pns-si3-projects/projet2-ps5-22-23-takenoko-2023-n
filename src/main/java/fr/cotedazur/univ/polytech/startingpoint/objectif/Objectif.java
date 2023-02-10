package fr.cotedazur.univ.polytech.startingpoint.objectif;

import java.util.Objects;

/**
 * Généralise les objectifs par leur nombre de points.
 * @author équipe N
 */
public abstract class Objectif {
    // Définition des attributs

    protected int nombrePoints;


    // Accesseurs

    /**
     * Renvoie le nombre de points de l'objectif
     * @return le nombre de points de l'objectif
     */
    public int getNombrePoints() {
        return nombrePoints;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return "Objectif de " + nombrePoints + " points";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objectif objectif = (Objectif) o;
        return getNombrePoints() == objectif.getNombrePoints();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombrePoints());
    }
}