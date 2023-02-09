package fr.cotedazur.univ.polytech.startingpoint.joueur;


import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.GestionTours;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotifJoueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleDisponible;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionBambous;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionPersonnages;
import fr.cotedazur.univ.polytech.startingpoint.plateau.ParcelleNonPoseeException;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.*;

public class StrategieComplete implements Strategie {
    private boolean premierTour = true;

    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs,
                                                     Plateau plateau, boolean[] piochesVides) {

        if (choixObjectif(actionsRealiseesTour, objectifs, piochesVides)) {
            return Plaquette.ActionPossible.OBJECTIF;
        }

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        int irrigationPosable = plateau.getIrrigationsDisponibles().length;
        if ((!piochesVides[GestionTours.PiochesPossibles.IRRIGATION.ordinal()]
                && !actionsRealiseesTour[irrigation.ordinal()] && irrigationPosable >= 3)
               || premierTour) {
            if (premierTour) premierTour = false;
            return irrigation;
        }

        Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
        List<Position> positionsDeplacement = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(), plateau.getPanda().getPosition());
        List<Position> positionsAvecBambou =
                GestionBambous.positionAvecBambou(positionsDeplacement, plateau, false);
        if (!actionsRealiseesTour[panda.ordinal()] && !positionsAvecBambou.isEmpty()) {
            return panda;
        }

        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()] && !getObjectifParcelle(objectifs).isEmpty()
                && !piochesVides[GestionTours.PiochesPossibles.PARCELLE.ordinal()]) {
            return parcelle;
        }

        return Plaquette.ActionPossible.JARDINIER;
    }

    /**
     * Renvoie s'il faut faire le choix de l'action objectif
     * @param actionsRealiseesTour le tableau des actions réalisées lors du tour
     * @param objectifs la liste des objectifs à réaliser
     * @param piochesVides un tableau pour savoir quelle pioche est vide
     * @return {@code true} si l'action objectif est à réaliser
     */
    public static boolean choixObjectif(boolean[] actionsRealiseesTour,
                                        List<Objectif> objectifs, boolean[] piochesVides) {
        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;

        return (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size()<Joueur.NOMBRE_OBJECTIFS_MAX)
                && (!piochesVides[GestionTours.PiochesPossibles.OBJ_PANDA.ordinal()]
                || !piochesVides[GestionTours.PiochesPossibles.OBJ_PARCELLE.ordinal()]
                || !piochesVides[GestionTours.PiochesPossibles.OBJ_JARDINIER.ordinal()] ));
    }

    @Override
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
        ParcellePioche[] pioche3parcelles;

        try {
            pioche3parcelles = piocheParcelle.pioche();
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError(e);
        }

        Parcelle[] parcellesEtang;

        try {
            parcellesEtang = plateau.getVoisinesParcelle(new Etang());
        } catch (ParcelleNonPoseeException e) {
            throw new AssertionError(e);
        }

        Optional<ParcelleCouleur> optionalPosition = Optional.empty();
        Position positionChoisi;
        ParcellePioche parcellePiocheChoisi;
        if (countParcelleCouleur(parcellesEtang) < 6) {
            optionalPosition = actionParcelleEtang(parcellesEtang, pioche3parcelles);
        }

        if (optionalPosition.isEmpty()) {
            positionChoisi = actionParcelleAutre(plateau, objectifs);
            parcellePiocheChoisi = pioche3parcelles[0];
        }
        else{
            positionChoisi = optionalPosition.get().getPosition();
            parcellePiocheChoisi = findListParcellePioche(pioche3parcelles, optionalPosition.get());
        }

        try {
            ParcelleCouleur parcelleCouleurChoisie = piocheParcelle.choisiParcelle(parcellePiocheChoisi, positionChoisi);
            plateau.poseParcelle(parcelleCouleurChoisie);
        } catch (PiocheParcelleVideException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Fait une action Parcelle autre qu'à côté de l'etang
     * @param plateau Le plateau
     * @param objectifs les objectifs à réalisé
     * @return La Position choisi
     */
    public Position actionParcelleAutre(Plateau plateau, List<Objectif> objectifs) {
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
                        positionsDisponible, objParChoisi.getSchema().getTableauParcelles());
                if (optPosition.isPresent()) positionChoisie = optPosition.get();
                i++;
            } while (optPosition.isEmpty() && i<objectifParcelles.size());
        }

        if (positionChoisie == null) {
            positionChoisie = positionsDisponible[0];
        }

        return positionChoisie;
    }

    /**
     * Renvoie la parcelle couleur pour remplir les parcelles autour de l'étang
     * @param parcellesAutourEtang parcelles voisines autour de l'étang
     * @param parcellePioches le tableau de parcelle Pioche
     * @return la parcelle couleur pour remplir les parcelles autour de l'étang
     */
    public Optional<ParcelleCouleur> actionParcelleEtang(Parcelle[] parcellesAutourEtang, ParcellePioche[] parcellePioches) {
        Set<Couleur> setCouleur = getCouleurParcelleVoisineEtang(parcellesAutourEtang);
        return positionAPrendreAutourEtang(parcellesAutourEtang, setCouleur, parcellePioches);
    }

    /**
     * Renvoie le nombre de parcelles Couleur autour de l'étang
     * @param voisines les voisines d'une parcelle
     * @return le nombre de parcelles Couleur autour de l'étang
     */
    private int countParcelleCouleur(Parcelle[] voisines) {
        int count = 0;
        for (Parcelle parcelle : voisines) {
            if (ParcelleCouleur.class == parcelle.getClass()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Renvoie les couleurs des parcelles autour de l'étang
     * @param parcelleVoisineEtang les parcelles voisines autour de l'étang
     * @return les couleurs des parcelles autour de l'étang
     */
    private Set<Couleur> getCouleurParcelleVoisineEtang(Parcelle[] parcelleVoisineEtang) {
        Set<Couleur> setCouleur = new HashSet<>();

        for (Parcelle parcelle: parcelleVoisineEtang) {
            if (parcelle.getClass() == ParcelleCouleur.class) {
                setCouleur.add(((ParcelleCouleur) parcelle).getCouleur());
            }
        }
        return setCouleur;
    }

    /**
     * Renvoie la position à prendre autour de l'étang
     * @param parcelleVoisineEtang Les parcelles voisines autour de l'étang
     * @param couleurPresente les couleurs présentes dans les parcelles voisines de l'étang
     * @param parcellePioches le tableau de Parcelles pioche
     * @return la position à prendre autour de l'étang
     */
    private Optional<ParcelleCouleur> positionAPrendreAutourEtang(Parcelle[] parcelleVoisineEtang, Set<Couleur> couleurPresente, ParcellePioche[] parcellePioches) {
        Couleur[] couleurs = new Couleur[]{Couleur.VERTE, Couleur.ROSE, Couleur.JAUNE};
        Couleur[] couleurParcelles = new Couleur[]{null, null, null};

        for (Couleur couleur : couleurPresente) {
            couleurs[couleur.ordinal()] = null;
        }

        for (ParcellePioche parcellePioche : parcellePioches) {
            couleurParcelles[parcellePioche.getCouleur().ordinal()] = parcellePioche.getCouleur();
        }

        for (Parcelle parcelle: parcelleVoisineEtang) {
            for (int i = 0; i < 3; i++) {
                if (parcelle.getClass() == ParcelleDisponible.class && couleurs[i] != null && couleurParcelles[i] != null) {
                    ParcelleCouleur parcelleCouleur = new ParcelleCouleur(parcelle.getPosition(),couleurs[i]);
                    return Optional.of(parcelleCouleur);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Renvoie la parcellePioche en fonction de la couleur de la parcelle couleur
     * @param parcellePioches le tableau des 3 parcelles à piocher
     * @param parcelleCouleur la parcelle Couleur choisie
     * @return la parcellePioche en fonction de la couleur de la parcelle couleur
     */
    private ParcellePioche findListParcellePioche(ParcellePioche[] parcellePioches, ParcelleCouleur parcelleCouleur) {
        for (ParcellePioche parcellePioche : parcellePioches) {
            if (parcellePioche.getCouleur() == parcelleCouleur.getCouleur()) {
                return parcellePioche;
            }
        }
        return null;
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
     * Retourne une position disponible pour le déplacement de la couleur souhaitée
     * @param plateau le plateau
     * @param couleur la couleur voulue
     * @return la position disponible pour le déplacement de la couleur souhaitée ou la première position
     */
    private Position parcelleCouleurVoulue(Plateau plateau, Couleur couleur, boolean bambouMax) {
        //recuperation des deplacement possible
        List<Position> deplacementPossible = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(),plateau.getPanda().getPosition());
        List<Position> positionsAvecBambou = GestionBambous.positionAvecBambou(deplacementPossible, plateau, bambouMax);

        return GestionBambous.positionVoulueCouleur(plateau, deplacementPossible, positionsAvecBambou, couleur);
    }

    /**
     * Renvoie la couleur manquante pour objectifPanda de 3 bambous
     * @param plaquette la plaquette
     * @return la couleur manquante
     */
    private Couleur plaquetteCouleurManquante(Plaquette plaquette) {
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.VERTE) == 0) {
            return Couleur.VERTE;
        }
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.ROSE) == 0) {
            return Couleur.ROSE;
        }
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.JAUNE) == 0) {
            return Couleur.JAUNE;
        }
        return null;
    }

    /**
     * Retourne les objectifPandas d'une liste d'objectif
     * @param objectifs la liste des objectifs mis en paramètre
     * @return la liste des objectifPanda issu de la liste d'objectif
     */
    private List<ObjectifPanda> getObjectifPanda(List<Objectif> objectifs) {
        List<ObjectifPanda> objectifsPandas = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            if(objectif.getClass().equals(ObjectifPanda.class)) {
                objectifsPandas.add((ObjectifPanda) objectif);
            }
        }
        return objectifsPandas;
    }

    /**
     * Retourne les objectifJardiniers d'une liste d'objectif
     * @param objectifs la liste des objectifs mis en paramètre
     * @return la liste des objectifJardinier issu de la liste d'objectif
     */
    private List<ObjectifJardinier> getObjectifJardinier(List<Objectif> objectifs) {
        List<ObjectifJardinier> objectifsJardiniers = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            if(objectif.getClass().equals(ObjectifJardinier.class)) {
                objectifsJardiniers.add((ObjectifJardinier) objectif);
            }
        }
        return objectifsJardiniers;
    }

    /**
     * Retourne les objectifParcelles d'une liste d'objectif
     * @param objectifs la liste des objectifs mis en paramètre
     * @return la liste des objectifParcelle issu de la liste d'objectif
     */
    private List<ObjectifParcelle> getObjectifParcelle(List<Objectif> objectifs) {
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
