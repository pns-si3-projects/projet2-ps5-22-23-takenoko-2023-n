package fr.cotedazur.univ.polytech.startingpoint;

public abstract class Objectif {
    // Définition des attributs
    protected int nombrePoints;


    // Définition des constructeurs
    /**
     * Constructeur pour un Objectif
     * @param nbPoints > 0 est le nombre de points de l'Objectif
     */
    protected Objectif(int nbPoints) {
        nombrePoints = nbPoints;
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de points de l'Objectif
     * @return le nombre de points
     */
    protected int getNombrePoints() {
        return nombrePoints;
    }

    /**
     * Renvoie l'objectif à réaliser
     * @return l'objet désignant l'objectif à réaliser
     */
    protected abstract Object getObjectifs();

    @Override
    public String toString() {
        return "Objectif de " + nombrePoints + " points";
    }
}