package fr.cotedazur.univ.polytech.startingpoint.plateau;

import java.util.Objects;

/**
 * Classe représentant une section de bambou
 * @author equipe N
 */
public class SectionBambou {
    // Définition des attributs
    private String couleur;



    // Définition des constructeurs
    /**
     * Constructeur par défaut
     */
    public SectionBambou(String couleur) {
        // A compléter quand on ajoutera la couleur
        this.couleur = couleur;
    }


    // Accesseurs et méthode toString et equals

    public String getCouleur(){
        return couleur;
    }

    @Override
    public String toString() {
        return "SectionBambou{" +
                "couleur='" + couleur + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionBambou that)) return false;
        return Objects.equals(couleur, that.couleur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couleur);
    }
}
