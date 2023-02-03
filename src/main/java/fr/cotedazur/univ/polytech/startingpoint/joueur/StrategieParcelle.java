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
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs) {
        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()]) {
            return parcelle;
        }

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size() < Joueur.NOMBRE_OBJECTIFS_MAX)) {
            return objectif;
        }

        Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
        if (!actionsRealiseesTour[panda.ordinal()]) {
            return panda;
        }

        return Plaquette.ActionPossible.JARDINIER;
    }
}
