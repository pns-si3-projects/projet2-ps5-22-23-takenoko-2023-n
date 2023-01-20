package fr.cotedazur.univ.polytech.startingpoint.objectif;

import java.util.Objects;

/**
 * Représente les objectifs de parcelles.
 * Un objectif de parcelles est un nombre de parcelles à placer.
 * @author équipe N
 */
public class ObjectifParcelle extends Objectif {
    // Définition des attributs

    private final int nombreParcelles;
    private int nombreParcellePresenteEnJeu = 1;


    // Définition des constructeurs

    /**
     * Construit un objectif de parcelles par le nombre de points et le nombre de parcelles à poser
     * @param nbPoints le nombre de points de l'objectif
     * @param nbParcelles le nombre de parcelles à poser
     * @implSpec {@code nbPoints > 0}, {@code nbParcelles > 0}
     */
    public ObjectifParcelle(int nbPoints, int nbParcelles) {
        if (nbPoints <= 0) {
            throw new IllegalArgumentException("Le nombre de points doit être supérieur à 0");
        }
        if (nbParcelles <= 0) {
            throw new IllegalArgumentException("Le nombre de parcelles doit être supérieur à 0");
        }

        nombrePoints = nbPoints;
        nombreParcelles = nbParcelles;
    }


    // Accesseurs

    /**
     * Renvoie le nombre de parcelles irriguées qui doivent être sur le plateau
     * @return le nombre de parcelles irriguées qui doivent être sur le plateau
     */
    public int getNombreParcelles() {
        return nombreParcelles;
    }

    public void setNombreParcellePresenteEnJeu(int nombreParcellePresenteEnJeu){
        if(nombreParcellePresenteEnJeu >= 0) {
            this.nombreParcellePresenteEnJeu = nombreParcellePresenteEnJeu;
        }
    }
    public int getNombreParcellePresenteEnJeu(){
        return nombreParcellePresenteEnJeu;
    }


    // Méthodes to String et equals

    @Override
    public String toString() {
        String s = "Objectif de ";
        s += nombreParcelles + " parcelles et vaut ";
        s += nombrePoints + " points";
        return s;
    }

    /**
     * Compare 2 ObjectifParcelle par le nombre de points et le nombre de parcelles
     * @param o l'objet à comparer avec {@code this}
     * @return {@code true} si l'ObjectifParcelle donné a le même nombre de points et de parcelles que {@code this}
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
