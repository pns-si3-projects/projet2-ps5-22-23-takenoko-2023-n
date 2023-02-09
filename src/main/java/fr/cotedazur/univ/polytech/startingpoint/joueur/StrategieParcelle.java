package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.GestionTours;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotifJoueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.*;

import java.util.List;
import java.util.Optional;

/**
 * Représente la stratégie de jeu favorisant la réalisation des objectifs de parcelle
 * @author equipe N
 */
public class StrategieParcelle implements Strategie {
    // Méthodes d'utilisation

    /**
     * Renvoie le nombre d'objectifs de parcelle
     * @param objectifs Les objectifs à réalisées
     * @return le nombre d'objectifs de parcelle
     */
    private int countObjectifParcelle(List<Objectif> objectifs) {
        int count = 0;
        for (Objectif objectif : objectifs) {
            if (objectif.getClass() == ObjectifParcelle.class) {
                count++;
            }
        }
        return count;
    }

    /**
     * Renvoie le nombre d'objectifs de jardinier
     * @param objectifs Les objectifs à réalisées
     * @return le nombre d'objectifs de jardinier
     */
    private int countObjectifJardinier(List<Objectif> objectifs) {
        int count = 0;
        for (Objectif objectif : objectifs) {
            if (objectif.getClass() == ObjectifJardinier.class) {
                count++;
            }
        }
        return count;
    }

    /**
     * Renvoie {@code true} si l'action Parcelle est possible
     * @param objectifs Les objectifs à réaliser
     * @return {@code true} si l'action Parcelle est possible
     */
    public boolean checkPossibiliteActionParcelle(List<Objectif> objectifs, boolean[] piochesVides) {
        return countObjectifParcelle(objectifs) != 0 && !piochesVides[GestionTours.PiochesPossibles.PARCELLE.ordinal()];
    }

    /**
     * Renvoie {@code true} si l'action Irrigation est possible
     * @param piochesVides Le tableau boolean des piochesVides
     * @param irrigationDisponible Les irrigations disponibles dans le plateau
     * @return {@code true} si l'action Irrigation est possible
     */
    public boolean checkPossibiliteActionIrrigation(boolean[] piochesVides, Irrigation[] irrigationDisponible) {
        return !piochesVides[GestionTours.PiochesPossibles.IRRIGATION.ordinal()] && irrigationDisponible.length != 0;
    }

    /**
     * Renvoie {@code true} si l'action Irrigation est possible
     * @param plateau Le plateau pour vérifier si il y a une parcelle irriguées et de même couleur qu'un des objetifs
     * @param objectifs Les objectifs à réalisées
     * @return {@code true} si l'action Irrigation est possible
     */
    public boolean checkPossibiliteActionJardinier(Plateau plateau, List<Objectif> objectifs) {
        if (countObjectifJardinier(objectifs) == 0) {
            return false;
        }

        List<Position> listDeplacementPossible = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(), plateau.getJardinier().getPosition());

        for (Position position : listDeplacementPossible) {
            if (!position.equals(new Position())) {
                Optional<Parcelle> optParcelle =  GestionParcelles.chercheParcelle(plateau.getParcelles(), position);

                if (optParcelle.isPresent()) {
                    ParcelleCouleur parcelleCouleur = (ParcelleCouleur) optParcelle.get();

                    if (parcelleCouleur.isIrriguee()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs,
                                                     Plateau plateau, boolean[] piochesVides) {

        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()] && checkPossibiliteActionParcelle(objectifs, piochesVides)) {
            return parcelle;
        }

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size() < Joueur.NOMBRE_OBJECTIFS_MAX)) {
            return objectif;
        }

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        if (!actionsRealiseesTour[irrigation.ordinal()]
                && checkPossibiliteActionIrrigation(piochesVides, plateau.getIrrigationsDisponibles())) {
            return irrigation;
        }

        Plaquette.ActionPossible jardinier = Plaquette.ActionPossible.JARDINIER;
        if (!actionsRealiseesTour[jardinier.ordinal()] && checkPossibiliteActionJardinier(plateau, objectifs)) {
            return jardinier;
        }

        else{
            Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
            if (!actionsRealiseesTour[panda.ordinal()]) {
                return Plaquette.ActionPossible.PANDA;
            }
            else {
                return Plaquette.ActionPossible.JARDINIER;
            }

        }

    }

    /**
     * Renvoie une parcelle Couleur à la position donnée en piochant la première Parcelle de la Pioche Parcelle
     * @param piocheParcelle La pioche de parcelle
     * @param positionChoisi La position choisi
     * @return une parcelle Couleur à la position donnée
     */
    private Optional<ParcelleCouleur> choisirParcelle(PiocheParcelle piocheParcelle, Position positionChoisi) {
        ParcellePioche[] tabChoixParcelles;

        try {
            tabChoixParcelles = piocheParcelle.pioche();
            return Optional.of(piocheParcelle.choisiParcelle(tabChoixParcelles[0], positionChoisi));
        }
        catch (PiocheParcelleEnCoursException pPECE) {
            assert false: "Ne doit pas être en demande 2 fois";
        }
        catch (PiocheParcelleVideException pPVE) {
            assert false: "La pioche parcelle ne doit pas être vide car vérifié avant par d'ancienne méthode";
        }

        return Optional.empty();
    }

    private ObjectifParcelle getMaxObjectifParcelle(List<Objectif> objectifs) {
        ObjectifParcelle objectifParcelleMax = null;

        for (Objectif objectif : objectifs) {
            if (objectif.getClass().equals(ObjectifParcelle.class)
                    && (objectifParcelleMax == null || objectifParcelleMax.getNombrePoints() < objectif.getNombrePoints())){

                objectifParcelleMax = (ObjectifParcelle) objectif;
            }
        }

        return objectifParcelleMax;
    }

    @Override
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle,
                               PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {

        Parcelle[] tableauParcellePlateau = plateau.getParcelles();
        Position[] tableauPositionDisponible = plateau.getPositionsDisponibles();
        ObjectifParcelle objectifParcelleChoisi = getMaxObjectifParcelle(objectifs);
        Optional<Position> optPosition = GestionnairePossibiliteMotifJoueur.positionPossiblePrendrePourMotif(
                tableauParcellePlateau, tableauPositionDisponible, objectifParcelleChoisi);

        Position positionChoisi = optPosition.orElseGet(() -> tableauPositionDisponible[0]);
        Optional<ParcelleCouleur> parcelleCouleurChoisi = choisirParcelle(piocheParcelle, positionChoisi);
        parcelleCouleurChoisi.ifPresent(plateau::poseParcelle);
    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, Plaquette plaquette) {
        Irrigation[] irrigationsDisponibles = plateau.getIrrigationsDisponibles();

        if (irrigationsDisponibles.length > 0) {
            List<Position> positionIrrigation = irrigationsDisponibles[irrigationsDisponibles.length/2].getPositions();

            if (!positionIrrigation.isEmpty()) {
                Irrigation irrigationPioche = piocheIrrigation.pioche();
                irrigationPioche.addPosition(positionIrrigation.get(0), positionIrrigation.get(1));
                plateau.poseIrrigation(irrigationPioche);
            }

        }
        else {
            assert false : "Aucune irrigation impossible";
        }
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {
        Jardinier jardinier = plateau.getJardinier();
        Position positionDeplacee = choixDeplacementPosition(plateau, jardinier.getPosition(), true);

        plateau.deplacementJardinier(positionDeplacee);
    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs, Plaquette plaquette) {
        Panda panda = plateau.getPanda();
        Position positionDeplacee = choixDeplacementPosition(plateau, panda.getPosition(), false);

        Optional<SectionBambou> sectionBambou = plateau.deplacementPanda(positionDeplacee);
        sectionBambou.ifPresent(plaquette::mangeSectionBambou);
    }

    public Position choixDeplacementPosition(Plateau plateau, Position position, boolean bambouMax) {
        List<Position> deplacementsPossibles = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(), position);
        List<Position> positionsAvecBambou =
                GestionBambous.positionAvecBambou(deplacementsPossibles, plateau, bambouMax);

        if (positionsAvecBambou.isEmpty()) {
            return deplacementsPossibles.get(deplacementsPossibles.size()/2);
        } else {
            return positionsAvecBambou.get(positionsAvecBambou.size()/2);
        }
    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle,
                               PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {

        if (!piocheObjectifParcelle.isEmpty()) {
            ObjectifParcelle objectifParcellePioche = piocheObjectifParcelle.pioche();
            objectifs.add(objectifParcellePioche);
        }
        else if (!piocheObjectifJardinier.isEmpty()) {
            ObjectifJardinier objectifJardinier = (ObjectifJardinier) piocheObjectifJardinier.pioche();
            objectifs.add(objectifJardinier);
        }
        else {
            ObjectifPanda objectifPanda = (ObjectifPanda) piocheObjectifPanda.pioche();
            objectifs.add(objectifPanda);
        }
    }
}
