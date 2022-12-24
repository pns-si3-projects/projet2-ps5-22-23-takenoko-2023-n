package fr.cotedazur.univ.polytech.startingpoint;

public class ObjectifParcelle extends Objectif {
    // Définition des attributs
    private int nombreParcelles;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param nbPoints > 0 est le nombre de points de l'objectif
     * @param nbParcelles > 0 est le nombre de parcelles à poser pour finir l'objectif
     */
    public ObjectifParcelle(int nbPoints, int nbParcelles) {
        nombrePoints = nbPoints;
        nombreParcelles = nbParcelles;
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de parcelles irriguées qui doivent se retrouver sur le plateau
     * @return le nombre de parcelles iriguées qui doivent être sur le plateau
     */
    public int getNombreParcelles() {
        return nombreParcelles;
    }

    @Override
    public String toString() {
        String s = "Objectif de ";
        s += nombreParcelles + " parcelles et vaut ";
        s += nombrePoints + " points";
        return s;
    }
}
