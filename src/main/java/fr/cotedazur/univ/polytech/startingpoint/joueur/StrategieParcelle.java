package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;

import java.util.List;

/**
 * Représente la stratégie de jeu "favoriser les objectifs de parcelle"
 * @author equipe N
 */
public class StrategieParcelle implements Strategie {
    // Méthodes d'utilisation

    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsPossiblesTour, List<Objectif> objectifs) {
        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (actionsPossiblesTour[parcelle.ordinal()]) {
            return parcelle;
        }

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (actionsPossiblesTour[objectif.ordinal()]) {
            return objectif;
        }

        Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
        if (actionsPossiblesTour[panda.ordinal()]) {
            return panda;
        }

        return Plaquette.ActionPossible.JARDINIER;
    }
}
