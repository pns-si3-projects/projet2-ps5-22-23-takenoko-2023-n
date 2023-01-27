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
    }


    // Accesseurs

    /**
     * Renvoie le schéma de bambous demandé
     * @return le schéma de bambous demandé
     */
    public int getSchema() {
        return nombreBambousAFairePousser;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return super.toString() + " pour " + nombreBambousAFairePousser + " bambous à faire pousser.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifJardinier that = (ObjectifJardinier) o;
        return getSchema() == that.getSchema();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSchema());
    }
}
