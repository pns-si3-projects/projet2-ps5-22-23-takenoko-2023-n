package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * Généralise les objectifs du jeu par le nombre de points qui est commun
 * @author équipe N
 */
public abstract class Objectif {
    // Définition des attributs
    protected int nombrePoints;


    // Accesseurs et méthodes toString et equals
    /**
     * Renvoie le nombre de points de l'Objectif
     * @return le nombre de points
     */
    protected int getNombrePoints() {
        return nombrePoints;
    }

    @Override
    public String toString() {
        return "Objectif qui vaut " + nombrePoints + " points";
    }

    /**
     * Compare 2 Objectif par le nombre de points
     * @param o est l'objet à comparer avec celui actuel
     * @return si l'Objectif en paramètre a le même nombre de points
     */
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