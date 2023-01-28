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


    // Définition des constructeurs

    /**
     * Construit un objectif de parcelles par le nombre de points et le schéma à obtenir sur le plateau
     * @param nbPoints le nombre de points de l'objectif
     * @param schema le schéma de parcelles à obtenir
     * @implSpec {@code nbPoints > 0}, {@code schema > 0}
     */
    public ObjectifParcelle(int nbPoints, int schema) {
        if (nbPoints <= 0) {
            throw new IllegalArgumentException("Le nombre de points doit être supérieur à 0");
        }
        if (schema <= 0) {
            throw new IllegalArgumentException("Le nombre de parcelles doit être supérieur à 0");
        }

        nombrePoints = nbPoints;
        nombreParcelles = schema;
    }


    // Accesseurs

    /**
     * Renvoie le schéma à obtenir sur le plateau
     * @return le schéma à obtenir sur le plateau
     */
    public int getSchema() {
        return nombreParcelles;
    }


    // Méthodes to String et equals

    @Override
    public String toString() {
        return super.toString() + " pour " + nombreParcelles + " parcelles";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifParcelle that = (ObjectifParcelle) o;
        return getSchema() == that.getSchema();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSchema());
    }
}
