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

    public static final int NOMBRE_OBJECTIFS_MAX = 5;
    private final String nom;
    private final Plaquette plaquette;
    private final List<Objectif> objectifEnMainList;
    private final List<Objectif> objectifTermineList;


    // Définition des constructeurs

    /**
     * Construit un joueur par son nom
     * @param nom le nom du joueur
     */
    public Joueur(String nom) {
        Objects.requireNonNull(nom);
        this.nom = nom;
        plaquette = new Plaquette();
        objectifEnMainList = new ArrayList<>(3);
        objectifTermineList = new ArrayList<>();
    }


    // Accesseurs

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
    public List<Objectif> getObjectifEnMainList() {
        return objectifEnMainList;
    }

    /**
     * Renvoie la liste des objectifs terminés
     * @return la liste des objectifs terminés
     */
    public List<Objectif> getObjectifTermineList() {
        return objectifTermineList;
    }


    // Autres méthodes

    /**
     * Renvoie le nombre d'objectifs possédés, mais pas terminés
     * @return le nombre d'objectifs possédés, mais pas terminés
     */
    public int nombreObjectifsEnMain() {
        return objectifEnMainList.size();
    }

    /**
     * Renvoie le nombre de points remportés par les objectifs terminés
     * @return le nombre de points du joueur
     */
    public int nombrePoints() {
        int somme = 0;
        for (Objectif objectif : objectifTermineList) {
            somme += objectif.getNombrePoints();
        }
        return somme;
    }


    // Méthode toString

    @Override
    public String toString() {
        return nom;
    }
}
