package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * Class abstraite qui généralise les objectifs du jeu par le nombre de points
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
     * Renvoie si les 2 objectifs sont égaux
     * @param o est l'objet à comparer avec <code>this</code>
     * @return <code>true</code> si l'Objectif donné a le même nombre de points, <code>false</code> sinon
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