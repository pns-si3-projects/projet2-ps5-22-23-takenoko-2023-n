package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Représente les objectifs de panda.
 * Un objectif de panda est un nombre de bambous à manger.
 * @author équipe N
 */
public class ObjectifPanda extends Objectif {
    // Définition des attributs

    private final int nombreBambousAManger;
    private final Couleur couleur;


    // Définition des constructeurs

    /**
     * Construit un objectif de panda par le nombre de points, la couleur et le nombre de bambous à manger
     * @param nbPoints le nombre de points de l'objectif
     * @param nbBambous le nombre de bambous que le joueur doit posséder
     * @param couleur la couleur demandée pour les bambous
     * @implSpec {@code nbPoints > 0}, {@code nbBambous > 0}
     */
    public ObjectifPanda(int nbPoints, int nbBambous, @NotNull Couleur couleur) {
        if (nbPoints <= 0) {
            throw new IllegalArgumentException("Le nombre de points doit être supérieur à 0");
        }
        if (nbBambous <= 0) {
            throw new IllegalArgumentException("Le nombre de bambous doit être supérieur à 0");
        }

        nombrePoints = nbPoints;
        nombreBambousAManger = nbBambous;
        this.couleur = couleur;
    }


    // Accesseurs
    /**
     * Renvoie le nombre de bambous que le joueur doit posséder
     * @return le nombre de bambous que le joueur doit posséder
     */
    public int getNombreBambousAManger() {
        return nombreBambousAManger;
    }

    /**
     * Renvoie la couleur des bambous que le joueur doit faire manger au panda
     * @return la couleur des bambous à faire manger au panda
     */
    public Couleur getCouleurBambousAManger() {
        return couleur;
    }


    // Méthodes toString et equals
    @Override
    public String toString() {
        String s = "Objectif de ";
        s += nombreBambousAManger + " bambous à posséder et vaut ";
        s += nombrePoints + " points";
        return s;
    }

    /**
     * Compare 2 ObjectifPanda par le nombre de points et le nombre de bambous à manger
     * @param o l'objet à comparer avec {@code this}
     * @return {@code true} si l'ObjectifPanda donné a le même nombre de points et de bambous à manger que {@code this}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifPanda that = (ObjectifPanda) o;
        return getNombreBambousAManger() == that.getNombreBambousAManger() && getCouleurBambousAManger() == that.getCouleurBambousAManger();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNombreBambousAManger(), getCouleurBambousAManger());
    }
}
