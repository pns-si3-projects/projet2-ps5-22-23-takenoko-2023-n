package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Empereur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
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
     * Renvoie l'action à effectuer
     * @param plateau le plateau du jeu
     * @param piochesVides un tableau pour savoir quelle pioche est vide
     */
    public Plaquette.ActionPossible choisiAction(Plateau plateau, boolean[] piochesVides) {
        AfficheurJoueur.debutTour(this.getNom());

        Plaquette.ActionPossible actionChoisie =
                strategie.choisiActionTour(plaquette.getActionsTour(), objectifEnMainList, plateau, piochesVides);
        plaquette.joueActionTour(actionChoisie);
        AfficheurJoueur.actionChoisie(actionChoisie);
        return actionChoisie;
    }

    /**
     * Pioche une parcelle et la pose sur le plateau
     * @param plateau le plateau du jeu
     * @param piocheParcelle la pioche de parcelles du jeu
     */
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle,
                               PiocheSectionBambou piocheSectionBambou) {
        strategie.actionParcelle(plateau, piocheParcelle, piocheSectionBambou, objectifEnMainList);
    }

    /**
     * Pioche une irrigation et choisi où la placer (plateau ou plaquette)
     * @param plateau le plateau du jeu
     * @param piocheIrrigation la pioche d'irrigation du jeu
     */
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation,
                                 PiocheSectionBambou piocheSectionBambou) {
        strategie.actionIrrigation(plateau, piocheIrrigation, piocheSectionBambou);
    }

    /**
     * Permet de déplacer le jardinier sur le plateau
     * @param plateau le plateau du jeu
     */
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou) {
        strategie.actionJardinier(plateau, piocheSectionBambou, objectifEnMainList);
    }

    /**
     * Permet de déplacer le panda sur le plateau
     * @param plateau le plateau du jeu
     */
    public void actionPanda(Plateau plateau) {
        strategie.actionPanda(plateau, objectifEnMainList, plaquette.getReserveBambousManges());
    }

    /**
     * Pioche l'objectif de son choix
     * @param piocheObjectifParcelle la pioche d'objectifs de parcelle du jeu
     * @param piocheObjectifJardinier la pioche d'objectifs de jardinier du jeu
     * @param piocheObjectifPanda la pioche d'objectifs de panda du jeu
     */
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle,
                               PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda) {

        strategie.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier,
                piocheObjectifPanda, objectifEnMainList);
    }

    /**
     * Termine le tour de {@code this}
     */
    public void finDeTour() {
        plaquette.termineTour();
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
