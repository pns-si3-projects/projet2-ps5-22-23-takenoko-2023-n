package fr.cotedazur.univ.polytech.startingpoint;

public class ObjectifPanda extends Objectif {
    // Définition des attributs
    private int nombreBambousAManger;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param nbPoints > 0 est le nombre de points de l'objectif
     * @param nbBambous > 0 est le nombre de bambous que le joueur doit posséder
     */
    public ObjectifPanda(int nbPoints, int nbBambous) {
        nombrePoints = nbPoints;
        nombreBambousAManger = nbBambous;
    }


    // Accesseurs et méthode toString
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
}
