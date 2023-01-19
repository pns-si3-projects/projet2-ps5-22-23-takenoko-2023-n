package fr.cotedazur.univ.polytech.startingpoint.objectif;

import java.util.Objects;

/**
 * Représente les objectifs du jardinier avec un nombre de bambous à faire pousser
 * @author équipe N
 */
public class ObjectifJardinier extends Objectif {
    // Définition des attributs
    private final int nombreBambousAFairePousser;
    private final String couleur;



    private final int nombreDeParcelle;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param nbPoints est le nombre de points de l'objectif
     * @param nbBambous est le nombre de bambous que le joueur faire pousser
     * @implSpec <code>nbPoints > 0</code>, <code>nbBambous > 0</code>
     */
    public ObjectifJardinier(int nbPoints, int nbBambous, String couleur,int nombreDeParcelle) {
        nombrePoints = nbPoints;
        nombreBambousAFairePousser = nbBambous;
        this.couleur = couleur;
        this.nombreDeParcelle=nombreDeParcelle;
    }


    // Accesseurs et méthodes toString et equals
    /**
     * Renvoie le nombre de bambous que le joueur doit faire pousser
     * @return le nombre de bambous que le joueur doit faire pousser
     */
    public int getNombreBambousAFairePousser() {
        return nombreBambousAFairePousser;
    }

    /**
     * Renvoie la couleur de bambous que le joueur doit faire pousser
     * @return la couleur de bambous que le joueur doit faire pousser
     */
    public String getCouleur(){
        return couleur;
    }

    /**
     * Renvoie le nombre de parcelle ou le joueur doit faire pousser du bambous
     * @return le nombre de parcelle ou le joueur doit faire pousser du bambous
     */
    public int getNombreDeParcelle() {
        return nombreDeParcelle;
    }

    @Override
    public String toString() {
        String s = "Objectif de ";
        s += nombreBambousAFairePousser + " bambous à faire pousser de couleur"+couleur+" sur "+nombreDeParcelle+"parcelle et vaut ";
        s += nombrePoints + " points";
        return s;
    }

    /**
     * Compare 2 ObjectifJardinier par le nombre de points et le nombre de bambous à faire pousser
     * @param o est l'objet à comparer avec celui actuel
     * @return <code>true</code> si l'ObjectifJardinier donné a le même nombre de points et de bambous à faire pousser, <code>false</code> sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifJardinier that = (ObjectifJardinier) o;
        return getNombreBambousAFairePousser() == that.getNombreBambousAFairePousser();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNombreBambousAFairePousser());
    }
}
