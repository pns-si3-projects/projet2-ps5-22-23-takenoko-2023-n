package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Représente les objectifs de jardinier.
 * Un objectif de jardinier est un schéma de bambous à refaire.
 * @author équipe N
 */
public class ObjectifJardinier extends Objectif {
    // Définition des attributs

    private final int nombreBambousAFairePousser;
    private final Couleur couleur;


    // Définition des constructeurs

    /**
     * Construit un objectif de jardinier par le nombre de points et le schéma de bambous
     * @param nbPoints le nombre de points de l'objectif
     * @param schema le schéma de bambous que le joueur doit refaire
     * @param couleur la couleur des bambous à faire pousser
     */
    public ObjectifJardinier(int nbPoints, int schema, @NotNull Couleur couleur) {
        if (nbPoints <= 0) {
            throw new IllegalArgumentException("Le nombre de points doit être supérieur à 0");
        }
        if (schema <= 0) {
            throw new IllegalArgumentException("Le schéma doit être supérieur à 0");
        }

        nombrePoints = nbPoints;
        nombreBambousAFairePousser = schema;
        this.couleur = couleur;
    }


    // Accesseurs

    /**
     * Renvoie le schéma de bambous demandé
     * @return le schéma de bambous demandé
     */
    public int getSchema() {
        return nombreBambousAFairePousser;
    }

    /**
     * Renvoie la couleur des bambous que le joueur doit faire pousser
     * @return la couleur des bambous à faire pousser
     */
    public Couleur getCouleur() {
        return couleur;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return super.toString() + " pour "
                + nombreBambousAFairePousser + " bambous de couleur " + couleur + " à faire pousser";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifJardinier that = (ObjectifJardinier) o;
        return getSchema() == that.getSchema() && getCouleur() == that.getCouleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSchema(), getCouleur());
    }
}
