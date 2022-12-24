package fr.cotedazur.univ.polytech.startingpoint;

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
}
