package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * Représente les objectifs des parcelles avec un nombre de parcelles à placer et irriguer si besoin
 * @author équipe N
 */
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


    // Accesseurs et méthodes toString et equals
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

    /**
     * Compare 2 ObjectifParcelle par le nombre de points et le nombre de parcelles
     * @param o est l'objet à comparer avec celui actuel
     * @return si l'ObjectifParcelle en paramètre a le même nombre de points et de parcelles
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifParcelle that = (ObjectifParcelle) o;
        return getNombreParcelles() == that.getNombreParcelles();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNombreParcelles());
    }
}
