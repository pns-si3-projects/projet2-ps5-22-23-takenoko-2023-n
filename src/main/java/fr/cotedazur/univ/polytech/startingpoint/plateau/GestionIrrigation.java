package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GestionIrrigation {
    // Définition d'un constructeur privé pour éviter les instanciations

    private GestionIrrigation() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Renvoie les nouvelles irrigations disponibles
     * @param parcellesEtVoisines la liste des parcelles et leurs voisines
     * @param irrigation l'irrigation à partir de laquelle il faut rechercher les nouvelles irrigations possibles
     * @param irrigationsPosees la liste des irrigations du plateau
     */
    public static Set<Irrigation> addIrrigationDisponible(@NotNull Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                               Irrigation irrigation, @NotNull Set<Irrigation> irrigationsPosees) {
        if (irrigation.getPositions().isEmpty()) {
            return new HashSet<>();
        }

        List<Position> posIrrigation = irrigation.getPositions();
        Optional<Parcelle> optParcelle1 = GestionParcelles
                .chercheParcelle(GestionParcelles.getParcelles(parcellesEtVoisines), posIrrigation.get(0));
        Optional<Parcelle> optParcelle2 = GestionParcelles
                .chercheParcelle(GestionParcelles.getParcelles(parcellesEtVoisines), posIrrigation.get(1));

        if (optParcelle1.isEmpty() || optParcelle2.isEmpty()) {
            throw new AssertionError("Parcelle introuvable");
        }
        ParcelleCouleur parcelleC1 = (ParcelleCouleur) optParcelle1.get();
        ParcelleCouleur parcelleC2 = (ParcelleCouleur) optParcelle2.get();

        List<ParcelleCouleur> parcellesPossibles = voisinesCommunes(parcellesEtVoisines, parcelleC1, parcelleC2);
        ParcelleCouleur[] parcelles = new ParcelleCouleur[]{parcelleC1, parcelleC2};

        return nouvellesIrrigations(irrigationsPosees, parcelles, parcellesPossibles);
    }

    /**
     * Renvoie la liste des voisines communes aux 2 parcelles données
     * @param parcellesEtVoisines la liste des parcelles et leurs voisines
     * @param parcelleC1 la première parcelle
     * @param parcelleC2 la deuxième parcelle
     * @return la liste des voisines communes aux 2 parcelles données
     */
    private static List<ParcelleCouleur> voisinesCommunes(Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                                          ParcelleCouleur parcelleC1, ParcelleCouleur parcelleC2) {
        Parcelle[] voisinesTab1;
        Parcelle[] voisinesTab2;
        try {
            voisinesTab1 = GestionParcelles.getVoisinesParcelle(parcellesEtVoisines, parcelleC1);
            voisinesTab2 = GestionParcelles.getVoisinesParcelle(parcellesEtVoisines, parcelleC2);
        } catch (ParcelleNonPoseeException e) {
            throw new AssertionError(e);
        }

        List<Parcelle> voisinesP1 = GestionParcelles.voisinesPosees(voisinesTab1);
        List<Parcelle> voisinesP2 = GestionParcelles.voisinesPosees(voisinesTab2);

        List<ParcelleCouleur> parcellesPossibles = new ArrayList<>();

        for (Parcelle voisine1 : voisinesP1) {
            for (Parcelle voisine2 : voisinesP2) {
                if (voisine1.equals(voisine2) && !voisine1.getClass().equals(Etang.class)) {
                    parcellesPossibles.add((ParcelleCouleur) voisine1);
                }
            }
        }
        return parcellesPossibles;
    }

    /**
     * Crée les nouvelles irrigations par les 2 parcelles et leurs voisines en vérifiant si pas encore posées
     * @param irrigationsPosees la liste des irrigations posées
     * @param parcelles les deux parcelles
     * @param voisines les voisines des 2 parcelles
     * @return la liste des nouvelles irrigations
     */
    private static Set<Irrigation> nouvellesIrrigations(Set<Irrigation> irrigationsPosees,
                                                         ParcelleCouleur[] parcelles, List<ParcelleCouleur> voisines) {
        Set<Irrigation> irrigations = new HashSet<>();
        Irrigation irrigation;

        for (ParcelleCouleur voisine : voisines) {
            for (ParcelleCouleur parcelle : parcelles) {
                irrigation = creeIrrigation(parcelle, voisine);

                if (!irrigationsPosees.contains(irrigation)) {
                    irrigations.add(irrigation);
                }
            }
        }
        return irrigations;
    }

    /**
     * Crée une irrigation à partir d'une parcelle et sa voisine
     * @param parcelle la parcelle
     * @param voisine la voisine de la parcelle
     * @return l'irrigation crée
     */
    private static Irrigation creeIrrigation(ParcelleCouleur parcelle, ParcelleCouleur voisine) {
        Position positionPar = parcelle.getPosition();
        Position positionVoi = voisine.getPosition();
        Irrigation irrigation = new Irrigation();

        if (irrigation.addPosition(positionPar, positionVoi)) {
            return irrigation;
        }
        throw new AssertionError("Les positions de l'irrigation doivent etre ajoutees");
    }

    /**
     * Vérifie s'il y a des irrigations autour de la parcelle donnée
     * puis ajoute des irrigations disponibles par rapport aux irrigations trouvées
     * @param parcelleCouleur parcelle pour laquelle on veut verifier s'il y a des irrigations autour
     */
    public static Set<Irrigation> checkIrrigationsAutour(@NotNull Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                                         ParcelleCouleur parcelleCouleur,
                                                         @NotNull Set<Irrigation> irrigationsPosees) {
        Parcelle[] voisines;
        try {
            voisines = GestionParcelles.getVoisinesParcelle(parcellesEtVoisines, parcelleCouleur);
        } catch (ParcelleNonPoseeException e) {
            throw new AssertionError(e);
        }
        List<Parcelle> voisinesNonDispo = GestionParcelles.voisinesPosees(voisines);

        return irrigationsDisponibles(parcellesEtVoisines, irrigationsPosees, parcelleCouleur, voisinesNonDispo);
    }

    /**
     * Renvoie les nouvelles irrigations disponibles par la parcelle posée et ses voisines
     * @param parcellesEtVoisines la liste des parcelles et leurs voisines
     * @param irrigationsPosees la liste des irrigations posées
     * @param parcellePosee la parcelle posée
     * @param voisines les voisines de la parcelle posée
     * @return la liste des nouvelles irrigations disponibles
     */
    private static Set<Irrigation> irrigationsDisponibles(Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                                          Set<Irrigation> irrigationsPosees,
                                                          ParcelleCouleur parcellePosee, List<Parcelle> voisines) {
        Set<Irrigation> irrigationsDispo = new HashSet<>();

        int nbParcelles = voisines.size();
        int j;
        for (int i=0; i<nbParcelles; i++) {
            if (i == nbParcelles-1) {
                j = 0;
            } else {
                j = i+1;
            }

            Parcelle voisineI = voisines.get(i);
            Parcelle voisineJ = voisines.get(j);
            Optional<Irrigation> optIrrigation = irrigationDispoEtang(parcellePosee, voisineI, voisineJ);

            if (optIrrigation.isPresent()) {
                irrigationsDispo.add(optIrrigation.get());
            } else {
                optIrrigation = chercheIrrigation(voisineI.getPosition(), voisineJ.getPosition(), irrigationsPosees);

                if (optIrrigation.isPresent()) {
                    Irrigation irrigation = optIrrigation.get();
                    irrigationsDispo.addAll(addIrrigationDisponible(parcellesEtVoisines, irrigation, irrigationsPosees));
                }
            }
        }
        return irrigationsDispo;
    }

    /**
     * Renvoie une irrigation disponible dans le cas d'une voisine Etang
     * @param parcellePosee la parcelle posée
     * @param voisine1 la première voisine
     * @param voisine2 la deuxième voisine
     * @return un optional de l'irrigation disponible
     */
    private static Optional<Irrigation> irrigationDispoEtang(ParcelleCouleur parcellePosee,
                                                             Parcelle voisine1, Parcelle voisine2) {
        Position positionVoisine;
        if (voisine1.getClass().equals(Etang.class) && voisine2.getClass().equals(Etang.class)) {
            return Optional.empty();
        } else if (voisine1.getClass().equals(Etang.class)) {
            positionVoisine = voisine2.getPosition();
        } else if (voisine2.getClass().equals(Etang.class)) {
            positionVoisine = voisine1.getPosition();
        } else {
            return Optional.empty();
        }

        Irrigation irrigation = new Irrigation();
        irrigation.addPosition(parcellePosee.getPosition(), positionVoisine);
        return Optional.of(irrigation);
    }

    /**
     * Cherche s'il y a une irrigation entre les positions données
     * @param position1 position de la 1ere parcelle entre laquelle on cherche une irrigation
     * @param position2 position de la 2eme parcelle entre laquelle on cherche une irrigation
     * @return un optional de l'irrigation trouvée, sinon un optional vide
     */
    public static Optional<Irrigation> chercheIrrigation(Position position1, Position position2,
                                                         @NotNull Set<Irrigation> irrigationsPosees){
        Irrigation irrigationRecherchee = new Irrigation();
        irrigationRecherchee.addPosition(position1, position2);

        for (Irrigation irrigation : irrigationsPosees){
            if (irrigation.equals(irrigationRecherchee)) return Optional.of(irrigationRecherchee);
        }
        return Optional.empty();
    }

}
