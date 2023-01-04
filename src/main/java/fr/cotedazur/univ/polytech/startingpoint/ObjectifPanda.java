package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * Représente les objectifs du panda avec un nombre de bambous à manger
 * @author équipe N
 */
public class ObjectifPanda extends Objectif {
    // Définition des attributs
    private final int nombreBambousAManger;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param nbPoints est le nombre de points de l'objectif
     * @param nbBambous est le nombre de bambous que le joueur doit posséder
     * @implSpec <code>nbPoints > 0</code>, <code>nbBambous > 0</code>
     */
    public ObjectifPanda(int nbPoints, int nbBambous) {
        nombrePoints = nbPoints;
        nombreBambousAManger = nbBambous;
    }


    // Accesseurs et méthodes toString et equals
    /**
     * Renvoie le nombre de bambous que le joueur doit posséder
     * @return le nombre de bambous que le joueur doit posséder
     */
    public int getNombreBambousAManger() {
        return nombreBambousAManger;
    }

    @Override
    public String toString() {
        String s = "Objectif de ";
        s += nombreBambousAManger + " bambous à posséder et vaut ";
        s += nombrePoints + " points";
        return s;
    }

    /**
     * Compare 2 ObjectifPanda par le nombre de points et le nombre de bambous à manger
     * @param o est l'objet à comparer avec celui actuel
     * @return <code>true</code> si l'ObjectifPanda donné a le même nombre de points et de bambous à manger, <code>false</code> sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifPanda that = (ObjectifPanda) o;
        return getNombreBambousAManger() == that.getNombreBambousAManger();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNombreBambousAManger());
    }
}
