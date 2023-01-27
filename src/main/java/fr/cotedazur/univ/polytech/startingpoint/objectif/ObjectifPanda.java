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
    public Couleur getCouleur() {
        return couleur;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return super.toString() + " pour "
                + nombreBambousAManger + " bambous de couleur " + couleur + " à posséder";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifPanda that = (ObjectifPanda) o;
        return getNombreBambousAManger() == that.getNombreBambousAManger() && getCouleur() == that.getCouleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNombreBambousAManger(), getCouleur());
    }
}
