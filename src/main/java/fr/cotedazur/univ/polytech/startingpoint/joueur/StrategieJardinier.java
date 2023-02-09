package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.GestionTours;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionBambous;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionPersonnages;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.List;
import java.util.Optional;

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
        if (!actionsRealiseesTour[jardinier.ordinal()] && plateau.getParcelles().length > 2) {
            return jardinier;
        }

        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()] && plateau.getParcelles().length < 2) {
            return parcelle;
        }

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        if (!actionsRealiseesTour[irrigation.ordinal()] && plateau.getParcelles().length > 4 &&
                !piochesVides[GestionTours.PiochesPossibles.IRRIGATION.ordinal()]) {
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
                               PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
        ParcellePioche[] pioche3parcelles;
        Position positionChoisie  = plateau.getPositionsDisponibles()[0];
        ParcelleCouleur parcelleChoisie;

        try {
            pioche3parcelles = piocheParcelle.pioche();
            parcelleChoisie = piocheParcelle.choisiParcelle(pioche3parcelles[0],positionChoisie);
            plateau.poseParcelle(parcelleChoisie);
        } catch (PiocheParcelleEnCoursException | PiocheParcelleVideException  e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, Plaquette plaquette) {
        Irrigation[] irrigationsDisponibles = plateau.getIrrigationsDisponibles();
        if (irrigationsDisponibles.length > 0){
            List<Position> positionIrrigation = irrigationsDisponibles[0].getPositions();

            if (!piocheIrrigation.isEmpty()) {
                Irrigation irrigationAAdd = piocheIrrigation.pioche();
                irrigationAAdd.addPosition(positionIrrigation.get(0), positionIrrigation.get(1));
                plateau.poseIrrigation(irrigationAAdd);
            }
        }
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
        Position futurePosition;
        //Déplacements possibles
        List<Position> deplacementsPossibles = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(), plateau.getJardinier().getPosition());
        List<Position> positionsAvecBambou = GestionBambous.positionAvecBambou(deplacementsPossibles, plateau, true);

        if (positionsAvecBambou.isEmpty()) {
            futurePosition = deplacementsPossibles.get(0);
        } else {
            futurePosition = positionsAvecBambou.get(0);
        }

        /*for (Position position : deplacementsPossibles){
            Optional<Parcelle> parcelle = GestionParcelles.chercheParcelle(plateau.getParcelles(), position);
            if (parcelle.isPresent() && parcelle.get().getClass().equals(ParcelleCouleur.class)) {
                ParcelleCouleur futureParcelleCouleurJardinier = (ParcelleCouleur) parcelle.get();
                futurePositionJardinier = futureParcelleCouleurJardinier.getPosition();
                if (futureParcelleCouleurJardinier.isIrriguee()) {
                    break;
                }
            }
        }*/

        plateau.deplacementJardinier(futurePosition);
    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs, Plaquette plaquette) {
        Position futurePosition;
        List<Position> deplacementsPossibles = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(), plateau.getJardinier().getPosition());
        List<Position> positionsAvecBambou =
                GestionBambous.positionAvecBambou(deplacementsPossibles, plateau, false);

        if (positionsAvecBambou.isEmpty()) {
            futurePosition = deplacementsPossibles.get(0);
        } else {
            futurePosition = positionsAvecBambou.get(0);
        }
        Optional<SectionBambou> sectionBambou = plateau.deplacementPanda(futurePosition);
        sectionBambou.ifPresent(plaquette::mangeSectionBambou);
    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle,
                               PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {
        Objectif objectif;
        if (!piocheObjectifJardinier.isEmpty()) objectif = piocheObjectifJardinier.pioche();
        else if (!piocheObjectifParcelle.isEmpty()) objectif = piocheObjectifParcelle.pioche();
        else objectif = piocheObjectifPanda.pioche();
        objectifs.add(objectif);
    }
}
