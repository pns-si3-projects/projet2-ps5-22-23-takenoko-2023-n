package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.GestionTours;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionPersonnages;
import fr.cotedazur.univ.polytech.startingpoint.plateau.ParcelleNonPoseeException;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.ArrayList;
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
        if (!actionsRealiseesTour[jardinier.ordinal()] && plateau.getParcelles().length > 3) {
            return jardinier;
        }

        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()] && plateau.getParcelles().length < 10) {
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
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
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
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation,
                                 PiocheSectionBambou piocheSectionBambou) {
        Irrigation[] irrigationsDisponibles = plateau.getIrrigationsDisponibles();
        if (irrigationsDisponibles.length > 0){
            Position positionIrrigation1 = irrigationsDisponibles[0].getPositions().get(0);
            Position positionIrrigation2 = irrigationsDisponibles[0].getPositions().get(1);

            if (!piocheIrrigation.isEmpty()) {
                Irrigation irrigationAAdd = piocheIrrigation.pioche(positionIrrigation1, positionIrrigation2);
                plateau.poseIrrigation(irrigationAAdd);
            }
        }
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
        List<ObjectifJardinier> objectifsJardinierList = new ArrayList<>();
        Position futurePositionJardinier = null;
        ParcelleCouleur futureParcelleCouleurJardinier = null;
        //Liste des objectfsJardiniers
        for (Objectif objectif: objectifs) {
            if (objectif.getClass().equals(ObjectifJardinier.class)) objectifsJardinierList.add((ObjectifJardinier) objectif);
        }

        //Déplacements possibles
        List<Position> deplacementsPossibles = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(), plateau.getJardinier().getPosition());
        boolean parcellePourDeplacementTrouvee = false;
        for (Position position : deplacementsPossibles){
            Optional<Parcelle> parcelle = GestionParcelles.chercheParcelle(plateau.getParcelles(), position);
            if (parcelle.isPresent() && parcelle.get().getClass().equals(ParcelleCouleur.class)) {
                futureParcelleCouleurJardinier = (ParcelleCouleur) parcelle.get();
                futurePositionJardinier = futureParcelleCouleurJardinier.getPosition();
                if (futureParcelleCouleurJardinier.isIrriguee()) {
                    for (ObjectifJardinier objectifJardinier : objectifsJardinierList) {
                        if (futureParcelleCouleurJardinier.getCouleur().equals(objectifJardinier.getCouleur())) {
                            parcellePourDeplacementTrouvee = true;
                            break;
                        }
                    }
                    if (parcellePourDeplacementTrouvee) break;
                }
            }
        }

        //Déplacement du Jardinier
        if (futurePositionJardinier!=null) {
            try {
                plateau.deplacementJardinier(futurePositionJardinier);
            } catch (ParcelleNonPoseeException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs, SectionBambou[] listeBambouMange) {
        List<Position> deplacementsPossibles = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(), plateau.getJardinier().getPosition());
        plateau.deplacementPanda(deplacementsPossibles.get(0));
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
