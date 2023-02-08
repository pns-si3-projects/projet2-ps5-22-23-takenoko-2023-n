package fr.cotedazur.univ.polytech.startingpoint.joueur;


import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.GestionTours;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotifJoueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import fr.cotedazur.univ.polytech.startingpoint.plateau.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StrategieComplete implements Strategie {
    private boolean premierTour = true;
    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs, Plateau plateau, boolean[] piochesVides) {

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size()<Joueur.NOMBRE_OBJECTIFS_MAX) && (!piochesVides[GestionTours.PiochesPossibles.OBJ_PANDA.ordinal()]  |
                !piochesVides[GestionTours.PiochesPossibles.OBJ_PARCELLE.ordinal()] | !piochesVides[GestionTours.PiochesPossibles.OBJ_JARDINIER.ordinal()] )) {
            return objectif;
        }

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        if ( (plateau.getBambous().length == 0 /* ou parcelle adversaire autre methode en plus */ && !actionsRealiseesTour[irrigation.ordinal()]) || premierTour ) {
            if(premierTour) premierTour = false;
            return irrigation;
        }

        Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
        if (!actionsRealiseesTour[panda.ordinal()] && plateau.getParcelles().length > 3) {
            //jardinier et panda adversai --> faire dans actionPanda
            return panda;
        }

        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()] && !getObjectifParcelle(objectifs).isEmpty() && !piochesVides[GestionTours.PiochesPossibles.PARCELLE.ordinal()]) {
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

            Optional<Position> optPosition = GestionnairePossibiliteMotifJoueur.positionPossiblePrendrePourMotif(plateau.getParcelles(), positionsDisponible, objectifParcellesMax);

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
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, Plaquette plaquette) {
        Irrigation irrigation = piocheIrrigation.pioche(new ArrayList<>());
        plaquette.ajoutIrrigation(irrigation);
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
        List<ObjectifPanda> objectifPandas = getObjectifPanda(objectifs);
        for (ObjectifPanda objectifPanda : objectifPandas) {
            // si objectifPanda est de trois sections bambou de different couleur
            if(objectifPanda.getBambousAManger().size() == 3) {
                Couleur couleurVoulue = plaquetteCouleurManquante(plaquette);
                Position positionDeplacer = parcelleCouleurVoulue(plateau, couleurVoulue);
                plateau.deplacementPanda(positionDeplacer);
            }
            // objectifPanda avec deux sectionBambou de meme couleur
            else {
                Couleur couleur = objectifPanda.getBambousAManger().get(0).getCouleur();
                Position positionDeplacer = parcelleCouleurVoulue(plateau, couleur);
                plateau.deplacementPanda(positionDeplacer);
            }
            break;
        }
    }

    /**
     * retourne une position disponible pour le deplacement a la couleur souhaitee
     * @param plateau le plateau
     * @param couleur la couleur voulue
     * @return la position qui et disponible pour le deplecement et de la couleur voulue.
     * Si aucune parcelle de la couluer voulue n'est disponible on renvoie le premier position
     */
    public Position parcelleCouleurVoulue(Plateau plateau, Couleur couleur) {
        Position positionVoulue = null;
        //recuperation des deplacement possible
        List<Position> possitionPossible = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(),plateau.getPanda().getPosition());
        for(Position position : possitionPossible) {
            Optional<Parcelle> parcelleOptional = GestionParcelles.chercheParcelle(plateau.getParcelles(), position);
            if(parcelleOptional.isPresent() && !parcelleOptional.get().getClass().equals(Etang.class)) {
                ParcelleCouleur parcelleCouleur = (ParcelleCouleur) parcelleOptional.get();
                if(parcelleCouleur.getCouleur().equals(couleur)) {
                    positionVoulue = parcelleCouleur.getPosition();
                    break;
                }
            }
        }
        // si aucune parcelle de la couleur voulue n'est disponible
        positionVoulue = possitionPossible.get(0);
        return positionVoulue;
    }

    /**
     * renvoie la couleur manquante pour objectifPanda de 3 bambou
     * @param plaquette la plaquette
     * @return la couleur manquante
     */
    public Couleur plaquetteCouleurManquante(Plaquette plaquette) {
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.VERTE) == 0) {return Couleur.VERTE; }
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.ROSE) == 0) {return Couleur.ROSE; }
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.JAUNE) == 0) {return Couleur.JAUNE; }
        return null;


    }

    /**
     * retourne les objectifPandas d'une liste d'objectif
     * @param objectifs la liste des objectifs mis en parametre
     * @return la liste des objectifPanda issu de la liste d'objectif
     */
    public List<ObjectifPanda> getObjectifPanda(List<Objectif> objectifs) {
        List<ObjectifPanda> objectifsPandas = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            if(objectif.getClass().equals(ObjectifPanda.class)) {
                objectifsPandas.add((ObjectifPanda) objectif);
            }
        }
        return objectifsPandas;
    }

    /**
     * retourne les objectifJardiniers d'une liste d'objectif
     * @param objectifs la liste des objectifs mis en parametre
     * @return la liste des objectifJardinier issu de la liste d'objectif
     */
    public List<ObjectifJardinier> getObjectifJardinier(List<Objectif> objectifs) {
        List<ObjectifJardinier> objectifsJardiniers = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            if(objectif.getClass().equals(ObjectifJardinier.class)) {
                objectifsJardiniers.add((ObjectifJardinier) objectif);
            }
        }
        return objectifsJardiniers;
    }

    /**
     * retourne les objectifParcelles d'une liste d'objectif
     * @param objectifs la liste des objectifs mis en parametre
     * @return la liste des objectifParcelle issu de la liste d'objectif
     */
    public List<ObjectifParcelle> getObjectifParcelle(List<Objectif> objectifs) {
        List<ObjectifParcelle> objectifsParcelle = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            if(objectif.getClass().equals(ObjectifParcelle.class)) {
                objectifsParcelle.add((ObjectifParcelle) objectif);
            }
        }
        return objectifsParcelle;
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
        if (objectifsPandaList.size() < 2 && !piocheObjectifPanda.isEmpty()) objectif = piocheObjectifPanda.pioche();
        else if (objectifParcelleList.size() < 2 && !piocheObjectifParcelle.isEmpty()) objectif = piocheObjectifParcelle.pioche();
        else objectif = piocheObjectifJardinier.pioche();

        objectifs.add(objectif);
    }

}
