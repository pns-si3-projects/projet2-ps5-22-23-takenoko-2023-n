package fr.cotedazur.univ.polytech.startingpoint.joueur;


import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.GestionTours;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotifJoueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionBambous;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionPersonnages;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StrategieComplete implements Strategie {
    private boolean premierTour = true;
    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs, Plateau plateau, boolean[] piochesVides) {

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size()<Joueur.NOMBRE_OBJECTIFS_MAX) && (!piochesVides[GestionTours.PiochesPossibles.OBJ_PANDA.ordinal()]  ||
                !piochesVides[GestionTours.PiochesPossibles.OBJ_PARCELLE.ordinal()] || !piochesVides[GestionTours.PiochesPossibles.OBJ_JARDINIER.ordinal()] )) {
            return objectif;
        }

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        if (!piochesVides[GestionTours.PiochesPossibles.IRRIGATION.ordinal()] &&
                (plateau.getBambous().length == 0 /* ou parcelle adversaire autre methode en plus */ && !actionsRealiseesTour[irrigation.ordinal()]) || premierTour ) {
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
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle,
                               PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
        Position[] positionsDisponible = plateau.getPositionsDisponibles();
        Position positionChoisie = null;
        List<ObjectifParcelle> objectifParcelles = getObjectifParcelle(objectifs);

        if (!objectifParcelles.isEmpty()) {
            ObjectifParcelle objParChoisi;
            Optional<Position> optPosition;
            int i = 0;

            do {
                objParChoisi = objectifParcelles.get(i);
                optPosition = GestionnairePossibiliteMotifJoueur.cherchePositionPossibilitePourFaireMotif(
                        plateau.getPositionsDisponibles(), objParChoisi.getSchema().getTableauParcelles());
                if (optPosition.isPresent()) positionChoisie = optPosition.get();
                i++;
            } while (optPosition.isEmpty() && i<objectifParcelles.size());
        }

        if (positionChoisie == null) {
            positionChoisie = positionsDisponible[0];
        }

        try {
            ParcellePioche[] pioche3parcelles = piocheParcelle.pioche();

            ParcelleCouleur parcelleCouleurChoisie = piocheParcelle.choisiParcelle(pioche3parcelles[0], positionChoisie);
            plateau.poseParcelle(parcelleCouleurChoisie);
        } catch (PiocheParcelleVideException | PiocheParcelleEnCoursException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, Plaquette plaquette) {
        Irrigation irrigation = piocheIrrigation.pioche();
        plaquette.ajoutIrrigation(irrigation);
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
        List<ObjectifJardinier> objectifJardiniers = getObjectifJardinier(objectifs);

        Position futurePosition;

        if (objectifJardiniers.isEmpty()) {
            futurePosition = futurePosition(plateau, true);
        }
        else {
            ObjectifJardinier objectifJardinierChoisi = objectifJardiniers.get(0);
            for (int i=1; i<objectifJardiniers.size(); i++) {
                ObjectifJardinier objectifJardinier = objectifJardiniers.get(i);
                if (objectifJardinierChoisi.getNombrePoints() < objectifJardinier.getNombrePoints()) {
                    objectifJardinierChoisi = objectifJardinier;
                }
            }

            Couleur couleurVoulue = objectifJardinierChoisi.getCouleur();
            futurePosition = parcelleCouleurVoulue(plateau, couleurVoulue, true);
        }

        plateau.deplacementJardinier(futurePosition);
    }

    /**
     * Renvoie la future position du personnage
     * @param plateau le plateau du jeu
     * @param bambouMax si on vérifie pour des bambous de taille maximum
     */
    private Position futurePosition(Plateau plateau, boolean bambouMax) {
        List<Position> deplacementsPossibles = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(), plateau.getJardinier().getPosition());
        List<Position> positionsAvecBambou =
                GestionBambous.positionAvecBambou(deplacementsPossibles, plateau, bambouMax);

        if (positionsAvecBambou.isEmpty()) {
            return deplacementsPossibles.get(0);
        } else {
            return positionsAvecBambou.get(0);
        }
    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs, Plaquette plaquette) {
        Optional<SectionBambou> bambouMange;
        List<ObjectifPanda> objectifPandas = getObjectifPanda(objectifs);

        Position futurePosition;

        if (objectifPandas.isEmpty()) {
            futurePosition = futurePosition(plateau, false);
        }

        else {
            ObjectifPanda objectifPandaChoisi = objectifPandas.get(0);
            for (int i=1; i<objectifPandas.size(); i++) {
                ObjectifPanda objectifPanda = objectifPandas.get(i);
                if (objectifPandaChoisi.getNombrePoints() < objectifPanda.getNombrePoints()) {
                    objectifPandaChoisi = objectifPanda;
                }
            }

            // si objectifPanda est de trois sections bambou de different couleur
            if (objectifPandaChoisi.getBambousAManger().size() == 3) {
                Couleur couleurVoulue = plaquetteCouleurManquante(plaquette);
                futurePosition = parcelleCouleurVoulue(plateau, couleurVoulue, false);
            }
            // objectifPanda avec deux sectionBambou de meme couleur
            else {
                Couleur couleur = objectifPandaChoisi.getBambousAManger().get(0).getCouleur();
                futurePosition = parcelleCouleurVoulue(plateau, couleur, false);
            }
        }

        bambouMange = plateau.deplacementPanda(futurePosition);
        bambouMange.ifPresent(plaquette::mangeSectionBambou);
    }

    /**
     * retourne une position disponible pour le deplacement a la couleur souhaitee
     * @param plateau le plateau
     * @param couleur la couleur voulue
     * @return la position qui et disponible pour le deplecement et de la couleur voulue.
     * Si aucune parcelle de la couluer voulue n'est disponible on renvoie le premier position
     */
    public Position parcelleCouleurVoulue(Plateau plateau, Couleur couleur, boolean bambouMax) {
        //recuperation des deplacement possible
        List<Position> deplacementPossible = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(),plateau.getPanda().getPosition());
        List<Position> positionsAvecBambou = GestionBambous.positionAvecBambou(deplacementPossible, plateau, bambouMax);

        return GestionBambous.positionVoulueCouleur(plateau, deplacementPossible, positionsAvecBambou, couleur);
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
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle, PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {
        List<Objectif> objectifsPandaList = new ArrayList<>();
        List<Objectif> objectifParcelleList = new ArrayList<>();

        for (Objectif objectif : objectifs){
            if (objectif.getClass().equals(ObjectifPanda.class)) objectifsPandaList.add(objectif);
            else if (objectif.getClass().equals(ObjectifParcelle.class)) objectifParcelleList.add(objectif);
        }

        boolean piocheParcelleVide = piocheObjectifParcelle.isEmpty();
        boolean piocheJardinierVide = piocheObjectifJardinier.isEmpty();
        boolean piochePandaVide = piocheObjectifPanda.isEmpty();

        if (!piochePandaVide && objectifsPandaList.size() < 2) objectifs.add(piocheObjectifPanda.pioche());
        else if (!piocheParcelleVide && objectifParcelleList.size() < 2) objectifs.add(piocheObjectifParcelle.pioche());
        else if (!piocheJardinierVide) objectifs.add(piocheObjectifJardinier.pioche());
        else if (!piochePandaVide) objectifs.add(piocheObjectifPanda.pioche());
        else objectifs.add(piocheObjectifParcelle.pioche());
    }
}
