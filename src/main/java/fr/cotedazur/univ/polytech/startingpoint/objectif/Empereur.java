package fr.cotedazur.univ.polytech.startingpoint.objectif;

public class Empereur extends Objectif {
    // Définition des constructeurs

    /**
     * Construit la carte Empereur par son nombre de points
     */
    public Empereur() {
        nombrePoints = 2;
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Empereur de " + nombrePoints + " points";
    }
}
