package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Représente la plaquette individuelle d'un joueur.
 * Contient les actions du tour et la liste des bambous mangés.
 * @author équipe N
 */
public class Plaquette {
    // Définition des attributs

    public enum ActionPossible {PARCELLE, IRRIGATION, JARDINIER, PANDA, OBJECTIF}
    private final List<SectionBambou> reserveBambousManges;
    private final boolean[] actionsTour;
    private final List<Irrigation> reserveIrrigation;


    // Définition des constructeurs

    /**
     * Construit la plaquette d'un joueur
     */
    public Plaquette() {
        reserveBambousManges = new ArrayList<>();
        actionsTour = new boolean[]{false, false, false, false, false};
        reserveIrrigation = new ArrayList<>();
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
     * Renvoie les irrigations en possetion du joueur
     * @return un tableau d'irrigation
     */
    public Irrigation[] getReserveIrrigation() {
        int nbIrrigation = reserveIrrigation.size();
        Irrigation[] irrigations = new Irrigation[nbIrrigation];

        for (int i=0; i<nbIrrigation; i++) {
            irrigations[i] =reserveIrrigation.get(i);
        }
        return irrigations;
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
     * Ajout l'irrigation a la réserve dans la plaquette
     * @param irrigation l'irrigation a ajouter dans la reserve de la plaquette
     */
    public void ajoutIrrigation(@NotNull Irrigation irrigation) {
        reserveIrrigation.add(irrigation);
    }

    /**
     * Renvoie une irrigation de la plaquette
     * @return l'irrigation de la plaquette
     */
    public Optional<Irrigation> enleveIrrigation() {
        if (!reserveIrrigation.isEmpty()) {
            return Optional.of(reserveIrrigation.remove(0));
        }
        return Optional.empty();
    }

    /**
     * Supprime la section de Bambou de la liste
     * @param sectionBambou la section Bambou à supprimer
     */
    public void enleveSectionBambouList(SectionBambou sectionBambou) {
        int i = 0;

        for (; i < reserveBambousManges.size(); i++) {
            if ( sectionBambou.couleur().equals(reserveBambousManges.get(i).couleur()) ) {
                break;
            }
        }

        if (i != reserveBambousManges.size()) {
            reserveBambousManges.remove(i);
        }
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
