package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.List;

public class StrategieComplete implements Strategie {
    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs, Plateau plateau, boolean[] piochesVides) {

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size()<Joueur.NOMBRE_OBJECTIFS_MAX)) {
            return objectif;
        }

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        if ( plateau.getBambous().length == 0 /* ou parcelle adversaire autre methode en plus */ && !actionsRealiseesTour[irrigation.ordinal()] ) {
            return irrigation;
        }

        Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
        if (!actionsRealiseesTour[panda.ordinal()] && plateau.getParcelles().length > 3) {
            //jardinier et panda adversai  --> faire dans actionPanda
            return panda;

        }

        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()] ) {
            return parcelle;
        }

        return Plaquette.ActionPossible.JARDINIER;
    }

    @Override
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {

    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, PiocheSectionBambou piocheSectionBambou) {

    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {

    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs, Plaquette plaquette) {

    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle, PiocheObjectifJardinier piocheObjectifJardinier, PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {

    }
}
