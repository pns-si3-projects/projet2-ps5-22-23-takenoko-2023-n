package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;

import java.util.List;

public class StrategieJardinier implements Strategie {
    // Méthodes d'utilisation

    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs) {
        Plaquette.ActionPossible jardinier = Plaquette.ActionPossible.JARDINIER;
        if (!actionsRealiseesTour[jardinier.ordinal()]) {
            return jardinier;
        }

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size() < Joueur.NOMBRE_OBJECTIFS_MAX)) {
            return objectif;
        }

        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()]) {
            return parcelle;
        }

        return Plaquette.ActionPossible.PANDA;
    }
}
