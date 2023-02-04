package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Empereur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    private final Strategie strategie;


    // Définition des constructeurs

    /**
     * Construit un joueur par son nom
     * @param nom le nom du joueur
     */
    public Joueur(@NotNull String nom, Strategie.StrategiePossible strategieChoisie) {
        this.nom = nom;
        plaquette = new Plaquette();
        objectifEnMainList = new ArrayList<>(3);
        objectifTermineList = new ArrayList<>();
        strategie = switch (strategieChoisie) {
            case PARCELLE -> new StrategieParcelle();
            case PANDA -> new StrategiePanda();
            case JARDINIER -> new StrategieJardinier();
        };
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
     * Renvoie les objectifs en main (non terminés)
     * @return un tableau des objectifs en main
     */
    public Objectif[] getObjectifsEnMain() {
        int nbObjectifs = objectifEnMainList.size();
        Objectif[] objectifs = new Objectif[nbObjectifs];

        for (int i=0; i<nbObjectifs; i++) {
            objectifs[i] = objectifEnMainList.get(i);
        }

        return objectifs;
    }

    /**
     * Renvoie les objectifs terminés
     * @return un tableau des objectifs terminés
     */
    public Objectif[] getObjectifsTermines() {
        int nbObjectifs = objectifTermineList.size();
        Objectif[] objectifs = new Objectif[nbObjectifs];

        for (int i=0; i<nbObjectifs; i++) {
            objectifs[i] = objectifTermineList.get(i);
        }

        return objectifs;
    }


    // Méthodes d'utilisation

    /**
     * Renvoie le nombre d'objectifs possédés, mais pas terminés
     * @return le nombre d'objectifs possédés, mais pas terminés
     */
    public int nombreObjectifsEnMain() {
        return objectifEnMainList.size();
    }

    /**
     * Renvoie le nombre d'objectifs terminés
     * @return le nombre d'objectifs terminés
     */
    public int nombreObjectifsTermines() {
        return objectifTermineList.size();
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

    /**
     * Renvoie le nombre de points remportés par les objectifs de panda terminés
     * @return le nombre de points des objectifs de panda du joueur
     */
    public int nombrePointsObjectifsPanda() {
        int somme = 0;

        for (Objectif objectif : objectifTermineList) {
            if (objectif instanceof ObjectifPanda) {
                somme += objectif.getNombrePoints();
            }
        }

        return somme;
    }

    /**
     * Effectue les actions du tour
     */
    public void joueTour() {
        Plaquette.ActionPossible actionChoisie =
                strategie.choisiActionTour(plaquette.getActionsTour(), objectifEnMainList);
        switch (actionChoisie) {
            case PARCELLE -> joueParcelle();
            case IRRIGATION -> joueIrrigation();
            case JARDINIER -> deplaceJardinier();
            case PANDA -> deplacePanda();
            case OBJECTIF -> piocheObjectif();
        }
    }

    private void joueParcelle() {

    }

    private void joueIrrigation() {

    }

    private void deplaceJardinier() {

    }

    private void deplacePanda() {

    }

    private void piocheObjectif() {

    }

    /**
     * Permet de recevoir la carte Empereur en étant le premier joueur à terminer le nombre d'objectifs demandé
     * @param empereur la carte Empereur
     */
    public void recoitEmpereur(Objectif empereur) {
        if (empereur instanceof Empereur) {
            objectifTermineList.add(empereur);
        } else {
            throw new IllegalArgumentException("L'objectif donné n'est pas la carte Empereur");
        }
    }


    // Méthode toString

    @Override
    public String toString() {
        return nom;
    }
}
