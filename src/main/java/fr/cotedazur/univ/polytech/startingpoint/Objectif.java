package fr.cotedazur.univ.polytech.startingpoint;

public abstract class Objectif {
    // Définition des attributs
    protected int nombrePoints;


    // Accesseurs et méthode toString
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
}