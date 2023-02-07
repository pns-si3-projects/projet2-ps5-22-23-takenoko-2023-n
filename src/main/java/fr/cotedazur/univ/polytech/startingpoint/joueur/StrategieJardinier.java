package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Représente la stratégie de jeu favorisant la réalisation des objectifs de jardinier
 * @author equipe N
 */
public class StrategieJardinier implements Strategie {
    // Méthodes d'utilisation

    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs,
                                                     Plateau plateau, boolean[] piochesVides) {

        Plaquette.ActionPossible jardinier = Plaquette.ActionPossible.JARDINIER;
        if (!actionsRealiseesTour[jardinier.ordinal()] && plateau.getParcelles().length > 3) {
            return jardinier;
        }

        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()] && plateau.getParcelles().length < 10) {
            return parcelle;
        }

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        if (!actionsRealiseesTour[irrigation.ordinal()] && plateau.getParcelles().length > 4) {
            return irrigation;
        }

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size() < Joueur.NOMBRE_OBJECTIFS_MAX)) {
            return objectif;
        }
        
        return Plaquette.ActionPossible.PANDA;
    }

    @Override
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle,
                               PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) throws PiocheParcelleVideException, PiocheParcelleEnCoursException {
        ParcellePioche[] pioche3parcelles = piocheParcelle.pioche();
        Position positionChoisie  = plateau.getPositionsDisponibles()[0];
        ParcelleCouleur parcelleChoisie = piocheParcelle.choisiParcelle(pioche3parcelles[0],positionChoisie);
        plateau.poseParcelle(parcelleChoisie);
    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation,
                                 PiocheSectionBambou piocheSectionBambou) {
        Set<Irrigation> irrigationsDisponibles = plateau.getIrrigationsDisponibles();
        Irrigation irrigationAAdd = null;
        for (Irrigation irrigation: irrigationsDisponibles){
            irrigationAAdd = irrigation;
            for (Position positionIrrigation : irrigation.getPositions()){
                Optional<Parcelle> optParcelle = GestionParcelles.chercheParcelle(plateau.getParcelles(), positionIrrigation);
                if (optParcelle.isPresent()) {
                    ParcelleCouleur pc = (ParcelleCouleur) optParcelle.get();
                    if (!pc.isIrriguee()) irrigationAAdd = irrigation;
                    break;
                }
            }
        }
        plateau.addIrrigation(irrigationAAdd.getPositions().get(0), irrigationAAdd.getPositions().get(1));
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {

    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs) {

    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle,
                               PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {

    }
}
