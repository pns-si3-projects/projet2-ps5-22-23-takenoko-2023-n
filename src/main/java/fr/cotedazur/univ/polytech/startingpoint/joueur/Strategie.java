package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

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
     * @param plateau le plateau du jeu
     * @param piochesVides un tableau pour savoir quelle pioche est vide
     * @return l'action choisie
     */
    Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs,
                                              Plateau plateau, boolean[] piochesVides);

    /**
     * Pioche une parcelle et la pose sur le plateau
     * @param plateau le plateau du jeu
     * @param piocheParcelle la pioche de parcelles du jeu
     */

    void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle,
                        PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs);

    /**
     * Pioche une irrigation et choisi où la placer (plateau ou plaquette)
     * @param plateau le plateau du jeu
     * @param piocheIrrigation la pioche d'irrigation du jeu
     */
    void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, PiocheSectionBambou piocheSectionBambou);

    /**
     * Permet de déplacer le jardinier sur le plateau
     * @param plateau le plateau du jeu
     */
    void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs);

    /**
     * Permet de déplacer le panda sur le plateau
     * @param plateau le plateau du jeu
     */
    void actionPanda(Plateau plateau, List<Objectif> objectifs, SectionBambou[] listeBambouMange);

    /**
     * Pioche l'objectif de son choix
     * @param piocheObjectifParcelle la pioche d'objectifs de parcelle du jeu
     * @param piocheObjectifJardinier la pioche d'objectifs de jardinier du jeu
     * @param piocheObjectifPanda la pioche d'objectifs de panda du jeu
     */
    void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle,
                               PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs);
}
