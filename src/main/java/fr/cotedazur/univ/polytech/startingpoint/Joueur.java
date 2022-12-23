package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Joueur automatique capable d'ajouter une parcelle ou piocher un objectif
 * @author équipe N
 */
public class Joueur {
    // Définition des attributs
    private String nom;
    private Plaquette plaquette;
    // Ajouter les objectifs terminés et les accesseurs nécessaires


    // Définition des constructeurs
    /**
     * Constructeur unique du Joueur
     * @param nom est le nom du Joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        plaquette = new Plaquette();
    }


    // Accesseurs et méthode toString
    /**
     * Permet de renvoyer le nom du Joueur
     * @return le nom du Joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie la plaquette du Joueur
     * @return la plaquette du Joueur
     */
    public Plaquette getPlaquette() {
        return plaquette;
    }

    @Override
    public String toString() {
        return nom;
    }


    // Méthodes de jeu
}
