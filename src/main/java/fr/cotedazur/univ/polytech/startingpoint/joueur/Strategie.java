package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;

import java.util.List;

/**
 * Permet d'appliquer une stratégie de jeu
 * @author equipe N
 */
public interface Strategie {
    /** L'ensemble des stratégies de jeu possibles */
    enum StrategiePossible {PARCELLE, JARDINIER, PANDA}


    /**
     * Renvoie l'action choisie pour le tour
     * @param actionsRealiseesTour le tableau des actions réalisées lors du tour
     * @param objectifs la liste des objectifs à réaliser
     * @return l'action choisie
     */
    Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs);
}
