package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Applique la stratégie de jeu demandée.
 * Peut déplacer le panda, le jardinier et compléter des objectifs.
 * @author équipe N
 */
public class Joueur {
    // Définition des attributs

    private final String nom;
    private final Plaquette plaquette;
    private final List<Objectif> objectifARealiserList;
    private final List<Objectif> objectifTermineList;


    // Définition des constructeurs

    /**
     * Constructeur un joueur par son nom
     * @param nom le nom du joueur
     */
    public Joueur(String nom) {
        Objects.requireNonNull(nom);
        this.nom = nom;
        plaquette = new Plaquette();
        objectifARealiserList = new ArrayList<>(3);
        objectifTermineList = new ArrayList<>();
    }


    // Accesseurs et méthode toString

    /**
     * Renvoie le nom du joueur
     * @return le nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie la plaquette du joueur
     * @return la plaquette du joueur
     */
    public Plaquette getPlaquette() {
        return plaquette;
    }

    /**
     * Renvoie la liste des objectifs à réaliser
     * @return la liste des objectifs à réaliser
     */
    public List<Objectif> getObjectifARealiserList() {
        return objectifARealiserList;
    }

    /**
     * Renvoie la liste des objectifs terminés
     * @return la liste des objectifs terminés
     */
    public List<Objectif> getObjectifTermineList() {
        return objectifTermineList;
    }

    @Override
    public String toString() {
        return nom;
    }
}
