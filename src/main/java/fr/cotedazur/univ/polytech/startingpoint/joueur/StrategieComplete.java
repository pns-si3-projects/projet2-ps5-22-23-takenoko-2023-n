package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        try {
            //récupère l'objectifParcelle avec le plus de points
            ObjectifParcelle objectifParcellesMax = getObjectifParcelleMaxPts(objectifs);

            //positions disponibles pour poser la parcelle
            Position[] positionsDisponible = plateau.getPositionsDisponibles();

            Optional<Position> optPosition = GestionnairePossibiliteMotif
                    .positionPossiblePrendrePourMotif(plateau.getParcelles(), positionsDisponible, objectifParcellesMax);

            Position positionChoisie = null;
            if (optPosition.isPresent()) positionChoisie = optPosition.get();

            //si positionChoisie == null, alors pas de possibilités pour compléter le motif donc 1er position disponible
            if (positionChoisie == null) positionChoisie = positionsDisponible[0];

            //pioche 3 parcelles
            ParcellePioche[] pioche3parcelles = piocheParcelle.pioche();

            //Ajouter une condition avec la couleur de l'objectif

            ParcelleCouleur parcelleCouleurChoisie = piocheParcelle.choisiParcelle(pioche3parcelles[0], positionChoisie);

            //pose de la parcelle choisie
            plateau.poseParcelle(parcelleCouleurChoisie);

        } catch (PiocheParcelleVideException | PiocheParcelleEnCoursException e) {
            throw new AssertionError(e);
        }

    }

    /**
     * Retourne l'objectif Parcelle de la liste ayant le nombre de points le plus élevé
     * @param objectifs la liste des objectifs
     * @return l'objectif Parcelle de nombre de points le plus élevé
     */
    public ObjectifParcelle getObjectifParcelleMaxPts(List<Objectif> objectifs) {
        ObjectifParcelle objectifParcelleMax = null;
        int maxPtsObjectifsParcelle = 0;

        for (Objectif objectif : objectifs) {
            if (objectif.getClass().equals(ObjectifParcelle.class)) {
                if (objectifParcelleMax == null || maxPtsObjectifsParcelle < objectif.getNombrePoints()) {
                    objectifParcelleMax = (ObjectifParcelle) objectif;
                    maxPtsObjectifsParcelle = objectifParcelleMax.getNombrePoints();
                }
            }
        }
        return objectifParcelleMax;
    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, PiocheSectionBambou piocheSectionBambou) {

    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
        Jardinier jardinier = plateau.getJardinier();
        List<Position> deplacementPossible = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(), jardinier.getPosition());
        Position futurePositionJardinier = deplacementPossible.get(0);
        for (Position position : deplacementPossible){
            Optional<Bambou> bambou = GestionBambous.chercheBambou(plateau.getBambous(), position);
            if (bambou.isPresent() && bambou.get().getTailleBambou() < 2){
                futurePositionJardinier = position;
                break;
            }
        }

        try {
            plateau.deplacementJardinier(futurePositionJardinier);
        } catch (ParcelleNonPoseeException e) {
            throw new AssertionError(e);
        }

    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs, Plaquette plaquette) {

    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle, PiocheObjectifJardinier piocheObjectifJardinier, PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {
        List<Objectif> objectifsPandaList = new ArrayList<>();
        List<Objectif> objectifParcelleList = new ArrayList<>();
        List<Objectif> objectifsJardinierList = new ArrayList<>();
        for (Objectif objectif : objectifs){
            if (objectif.getClass().equals(ObjectifPanda.class)) objectifsPandaList.add(objectif);
            else if (objectif.getClass().equals(ObjectifParcelle.class)) objectifParcelleList.add(objectif);
            else objectifsJardinierList.add(objectif);
        }

        Objectif objectif;
        if (objectifsPandaList.size() < 2) objectif = piocheObjectifPanda.pioche();
        else if (objectifParcelleList.size() < 2) objectif = piocheObjectifParcelle.pioche();
        else objectif = piocheObjectifJardinier.pioche();

        objectifs.add(objectif);
    }

}
