package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionPersonnages;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Représente la stratégie de jeu favorisant la réalisation des objectifs de parcelle
 * @author equipe N
 */
public class StrategieParcelle implements Strategie {
    // Méthodes d'utilisation

    /**
     * Renvoie le nombre d'objectif Parcelle
     * @param objectifs
     * @return le nombre d'objectif Parcelle
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
     * Renvoie le nombre d'objectif Jardinier
     * @param objectifs
     * @return le nombre d'objectif Jardinier
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
     * Renvoie {@code true} si la parcelle est de même couleur que celle des objectifs
     * @param objectifs Les objectifs à réaliser
     * @return {@code true} si la parcelle est de même couleur que celle des objectifs
     */
    private boolean checkCouleurObjectifs(ParcelleCouleur parcelleCouleur, List<Objectif> objectifs) {
        for (Objectif objectif : objectifs) {
            if (objectif.getClass() == ObjectifJardinier.class) {
                ObjectifJardinier objectifJardinier = (ObjectifJardinier) objectif;

                if (objectifJardinier.getCouleur() == parcelleCouleur.getCouleur()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Renvoie {@code true} si l'action Parcelle est possible
     * @param objectifs Les objectifs à réaliser
     * @return {@code true} si l'action Parcelle est possible
     */
    public boolean checkPossibiliteActionParcelle(List<Objectif> objectifs) {
        return countObjectifParcelle(objectifs) != 0;
    }

    /**
     * Renvoie {@code true} si l'action Irrigation est possible
     * @param piochesVides Le tableau boolean des piochesVides
     * @return {@code true} si l'action Irrigation est possible
     */
    public boolean checkPossibiliteActionIrrigation(boolean[] piochesVides) { // Vérifier Irrigation Possible
        return !piochesVides[4];
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

        List<Position> listDeplacementPossible = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(),
                plateau.getJardinier().getPosition());

        for (Position position : listDeplacementPossible) {
            if (!position.equals(new Position())) {
                Optional<Parcelle> optParcelle =  GestionParcelles.chercheParcelle(plateau.getParcelles(), position);

                if (optParcelle.isPresent()) {
                    ParcelleCouleur parcelleCouleur = (ParcelleCouleur) optParcelle.get();

                    if (parcelleCouleur.isIrriguee() && checkCouleurObjectifs(parcelleCouleur, objectifs)) {
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
        if (!actionsRealiseesTour[parcelle.ordinal()] && checkPossibiliteActionParcelle(objectifs)) {
            return parcelle;
        }

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size() < Joueur.NOMBRE_OBJECTIFS_MAX)) {
            return objectif;
        }

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        if (!actionsRealiseesTour[irrigation.ordinal()] && checkPossibiliteActionIrrigation(piochesVides)) {
            return irrigation;
        }

        Plaquette.ActionPossible jardinier = Plaquette.ActionPossible.JARDINIER;
        if (!actionsRealiseesTour[jardinier.ordinal()] && checkPossibiliteActionJardinier(plateau, objectifs)) {
            return jardinier;
        }

        return Plaquette.ActionPossible.PANDA;
    }

    /**
     * Renvoie une parcelle Couleur à la position mis en paramètre en piochant la première Parcelle de la Pioche Parcelle
     * @param piocheParcelle La pioche de parcelle
     * @param positionChoisi La position choisi
     * @return une parcelle Couleur à la position mis en paramètre
     */
    private ParcelleCouleur choisirParcelle(PiocheParcelle piocheParcelle, Position positionChoisi) {
        ParcellePioche[] tabChoixParcelles;

        try {
            tabChoixParcelles = piocheParcelle.pioche();
            return piocheParcelle.choisiParcelle(tabChoixParcelles[0], positionChoisi);
        }
        catch (PiocheParcelleEnCoursException pPECE) {
            assert false: "Ne doit pas être en demande 2 fois";
        }
        catch (PiocheParcelleVideException pPVE) {
            assert false: "La pioche parcelle ne doit pas être vide car vérifié avant par d'ancienne méthode";
        }

        return null;
    }

    @Override
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle, PiocheSectionBambou piocheSectionBambou) {
        Parcelle[] tableauParcellePlateau = plateau.getParcelles();
        Position[] tableauPositionDisponible = plateau.getPositionsDisponibles();
        Optional<Position> optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(tableauParcellePlateau, tableauPositionDisponible, null);
        Position positionChoisi = optPosition.orElseGet(() -> tableauPositionDisponible[0]);

        ParcelleCouleur parcelleCouleurChoisi = choisirParcelle(piocheParcelle, positionChoisi);
        plateau.poseParcelle(parcelleCouleurChoisi);
    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, PiocheSectionBambou piocheSectionBambou) {
        Set<Irrigation> setIrrigation = plateau.getIrrigationsDisponibles();

        for (Irrigation irrigation : setIrrigation) {
            List<Position> positionIrrigation = irrigation.getPositions();
            plateau.addIrrigation(positionIrrigation.get(0), positionIrrigation.get(1));
            break;
        }
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou) {

    }

    @Override
    public void actionPanda(Plateau plateau) {

    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle, PiocheObjectifJardinier piocheObjectifJardinier, PiocheObjectifPanda piocheObjectifPanda) {

    }
}
