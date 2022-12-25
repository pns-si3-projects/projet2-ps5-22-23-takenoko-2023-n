package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * Représente les objectifs du jardinier avec un nombre de bambous à faire pousser
 * @author équipe N
 */
public class ObjectifJardinier extends Objectif {
    // Définition des attributs
    private int nombreBambousAFairePousser;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param nbPoints > 0 est le nombre de points de l'objectif
     * @param nbBambous > 0 est le nombre de bambous que le joueur faire pousser
     */
    public ObjectifJardinier(int nbPoints, int nbBambous) {
        nombrePoints = nbPoints;
        nombreBambousAFairePousser = nbBambous;
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de bambous que le joueur doit faire pousser
     * @return le nombre de bambous que le joueur doit faire pousser
     */
    public int getNombreBambousAFairePousser() {
        return nombreBambousAFairePousser;
    }

    @Override
    public String toString() {
        String s = "Objectif de ";
        s += nombreBambousAFairePousser + " bambous à faire pousser et vaut ";
        s += nombrePoints + " points";
        return s;
    }

    /**
     * Compare 2 ObjectifJardinier par le nombre de points et le nombre de bambous à faire pousser
     * @param o est l'objet à comparer avec celui actuel
     * @return si l'ObjectifJardinier en paramètre a le même nombre de points et de bambous à faire pousser
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifJardinier that = (ObjectifJardinier) o;
        return getNombreBambousAFairePousser() == that.getNombreBambousAFairePousser();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNombreBambousAFairePousser());
    }
}
