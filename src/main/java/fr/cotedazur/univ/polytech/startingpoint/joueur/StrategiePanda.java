package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.GestionTours;
import fr.cotedazur.univ.polytech.startingpoint.jeu.MaitreDuJeu;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.List;

/**
 * Représente la stratégie de jeu favorisant la réalisation des objectifs de panda
 * @author equipe N
 */
public class StrategiePanda implements Strategie {
    // Méthodes d'utilisation

    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs) {
        Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
        /*if( plateau.getBambous().length > 0 ) {
            if (!actionsRealiseesTour[panda.ordinal()] && plateau.getParcelles().length > 3) {
                return panda;
            }
            Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
            if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size() < Joueur.NOMBRE_OBJECTIFS_MAX)) {
                return objectif;
            }
            Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
            if (!actionsRealiseesTour[parcelle.ordinal()] && (plateau.getParcelles().length < 10))
                return parcelle;
            Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
            int irrigationPossable = plateau.getIrrigationsPosees().size()-plateau.getIrrigationsDisponibles().size();
            if (!actionsRealiseesTour[irrigation.ordinal()] && (irrigationPossable==3))
                return irrigation;
        }*/
        return Plaquette.ActionPossible.JARDINIER;
    }



}
