package fr.cotedazur.univ.polytech.startingpoint.objectif;

import java.util.Objects;

/**
 * Représente les objectifs de jardinier.
 * Un objectif de jardinier est un schéma de bambous à refaire.
 * @author équipe N
 */
public class ObjectifJardinier extends Objectif {
    // Définition des attributs

    private final int nombreBambousAFairePousser;
    private int nombreBambousRestant;


    // Définition des constructeurs

    /**
     * Construit un objectif de jardinier par le nombre de points et le schéma de bambous
     * @param nbPoints le nombre de points de l'objectif
     * @param schema le schéma de bambous que le joueur doit refaire
     */
    public ObjectifJardinier(int nbPoints, int schema) {
        if (nbPoints <= 0) {
            throw new IllegalArgumentException("Le nombre de points doit être supérieur à 0");
        }
        if (schema <= 0) {
            throw new IllegalArgumentException("Le schéma doit être supérieur à 0");
        }

        nombrePoints = nbPoints;
        nombreBambousAFairePousser = schema;
        nombreBambousRestant = nombreBambousAFairePousser;
    }


    // Accesseurs
    /**
     * Renvoie le schéma de bambous demandé
     * @return le schéma de bambous demandé
     */
    public int getNombreBambousAFairePousser() {
        return nombreBambousAFairePousser;
    }

    public int getNombreBambousRestant(){return nombreBambousRestant;}

    public void soustraitNombreBambousPoses(int nombreBambousPoses){
        nombreBambousRestant -= nombreBambousPoses;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        String s = "Objectif de ";
        s += nombreBambousAFairePousser + " bambous à faire pousser et vaut ";
        s += nombrePoints + " points";
        return s;
    }

    /**
     * Compare 2 ObjectifJardinier par le nombre de points et le schéma à réaliser
     * @param o l'objet à comparer avec {@code this}
     * @return {@code true} si l'objectifJardinier donné a le même nombre de points et le même schéma que {@code this}
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
