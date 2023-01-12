package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;

import java.util.Objects;

/**
 * Classe représentant une section de bambou
 * @author equipe N
 */
public class SectionBambou {
    // Définition des attributs
    private final Couleur couleur;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param couleur La couleur de la section du bambou
     */
    public SectionBambou(Couleur couleur) {
        if(couleur == null) throw new NullPointerException("La coulour ne dois pas être null");
        this.couleur = couleur;
    }

    // Accesseurs et méthode toString et equals

    /**
     * Renvoie la couleur du Bambou
     * @return la couleur du Bambou
     */
    public Couleur getCouleur(){
        return couleur;
    }

    @Override
    public String toString(){
        return "La couleur de cette section de Bambou est " + getCouleur();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        else if (obj == null || obj.getClass() != this.getClass()) return false;
        else{
            SectionBambou sectionBambou = (SectionBambou) obj;
            return sectionBambou.getCouleur().equals(this.getCouleur());
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(couleur);
    }
}
