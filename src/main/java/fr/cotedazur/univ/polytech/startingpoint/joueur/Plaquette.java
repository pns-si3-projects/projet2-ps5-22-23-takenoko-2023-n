package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente la plaquette individuelle d'un joueur.
 * Contient les actions du tour et la liste des bambous mangés.
 * @author équipe N
 */
public class Plaquette {
    // Définition des attributs

    public enum ActionPossible {PARCELLE, JARDINIER, PANDA, OBJECTIF}
    private final List<SectionBambou> reserveBambousManges;
    private final boolean[] actionsTour;


    // Définition des constructeurs

    /**
     * Construit la plaquette d'un joueur
     */
    public Plaquette() {
        reserveBambousManges = new ArrayList<>();
        actionsTour = new boolean[]{false, false, false, false};
    }


    // Accesseurs

    /**
     * Renvoie les sections de bambou mangées
     * @return un tableau des sections de bambous mangées
     */
    public SectionBambou[] getReserveBambousManges() {
        int nbBambous = reserveBambousManges.size();
        SectionBambou[] bambous = new SectionBambou[nbBambous];

        for (int i=0; i<nbBambous; i++) {
            bambous[i] = reserveBambousManges.get(i);
        }

        return bambous;
    }

    /**
     * Renvoie les actions réalisées dans le tour
     * @return un tableau des actions réalisées dans le tour
     */
    public boolean[] getActionsTour() {
        return actionsTour;
    }

    /**
     * Renvoie si l'action est jouée dans le tour
     * @param actionPossible l'action à vérifier si jouée dans le tour
     * @return {@code true} si l'action est jouée dans le tour
     */
    public boolean isActionTour(@NotNull ActionPossible actionPossible) {
        return actionsTour[actionPossible.ordinal()];
    }


    // Autres méthodes

    /**
     * Renvoie le nombre de sections de bambou mangées de la couleur demandée
     * @param couleurBambou la couleur demandée pour le bambou
     * @return le nombre de sections de bambou mangées de la couleur demandée
     */
    public int nombreBambouCouleur(Couleur couleurBambou) {
        int somme = 0;

        for (SectionBambou sectionBambou : reserveBambousManges) {
            if (sectionBambou.getCouleur() == couleurBambou) {
                somme++;
            }
        }

        return somme;
    }

    /**
     * Ajoute la section de bambou mangée à la réserve dans la plaquette
     * @param sectionBambou la section de bambou à ajouter à réserve de la plaquette
     */
    public void mangeSectionBambou(@NotNull SectionBambou sectionBambou) {
        reserveBambousManges.add(sectionBambou);
    }

    /**
     * Permet de jouer une action
     * @param actionJouee l'action à jouer
     * @return {@code true} si l'action est jouée
     */
    public boolean joueActionTour(ActionPossible actionJouee) {
        if (nombreActionTour() < 2 && !isActionTour(actionJouee)) {
            actionsTour[actionJouee.ordinal()] = true;
            return true;
        }
        return false;
    }

    /**
     * Renvoie le nombre d'actions jouées dans le tour
     * @return le nombre d'actions jouées dans le tour
     */
    private int nombreActionTour() {
        int res = 0;

        for (boolean actionJouee : actionsTour) {
            if (actionJouee) {
                res++;
            }
        }

        return res;
    }

    /**
     * Termine le tour en retirant les actions jouées
     */
    public void termineTour() {
        for (ActionPossible action : ActionPossible.values()) {
            actionsTour[action.ordinal()] = false;
        }
    }
}
