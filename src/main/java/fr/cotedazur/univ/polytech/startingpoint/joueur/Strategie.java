package fr.cotedazur.univ.polytech.startingpoint.joueur;

/**
 * Permet d'appliquer une stratégie de jeu
 * @author equipe N
 */
public interface Strategie {
    /** L'ensemble des stratégies de jeu possibles */
    enum StrategiePossible {PARCELLE, JARDINIER, PANDA}


    /**
     * Renvoie l'action choisie pour le tour
     * @return l'action choisie
     */
    Plaquette.ActionPossible choisiActionTour(Plaquette.ActionPossible[] actionsPossiblesTour);
}
