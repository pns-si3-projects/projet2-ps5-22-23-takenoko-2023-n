package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Joueur automatique capable d'ajouter une parcelle ou piocher un objectif
 * @author equipe N
 */
public class Joueur {
    // Définition des attributs
    private String nom;


    // Définition des constructeurs
    /**
     * Constructeur unique du Joueur
     * @param nom est le nom du Joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
    }

    // Accesseurs et méthode toString

    /**
     * Permet de renvoyer le nom du Joueur
     * @return le nom du Joueur
     */
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
