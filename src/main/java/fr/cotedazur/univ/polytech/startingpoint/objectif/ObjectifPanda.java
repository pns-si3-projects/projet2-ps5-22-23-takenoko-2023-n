package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;

import java.util.List;
import java.util.Objects;

/**
 * Représente les objectifs de panda.
 * Un objectif de panda est un nombre de bambous à manger.
 * @author équipe N
 */
public class ObjectifPanda extends Objectif {
    // Définition des attributs

    private final List<SectionBambou> bambousAManger;


    // Définition des constructeurs

    /**
     * Construit un objectif de panda par le nombre de points et une liste de sections de bambou à manger
     * @param nbPoints le nombre de points de l'objectif
     * @param bambousAManger liste de sections de bambou à manger
     */
    public ObjectifPanda(int nbPoints, List<SectionBambou> bambousAManger) {
        if (nbPoints <= 0) {
            throw new IllegalArgumentException("Le nombre de points doit être supérieur à 0");
        }
        if (bambousAManger.isEmpty()) {
            throw new IllegalArgumentException("Le nombre de bambous doit être supérieur à 0");
        }

        this.nombrePoints = nbPoints;
        this.bambousAManger = bambousAManger;
    }


    // Accesseurs

    /**
     * Renvoie la liste de sections de bambous que le panda doit manger
     * @return la liste de sections bambous que le panda doit manger
     */
    public List<SectionBambou> getBambousAManger(){
        return bambousAManger;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return "Objectif de panda de " + nombrePoints + " points";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjectifPanda that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(bambousAManger, that.bambousAManger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bambousAManger);
    }
}
