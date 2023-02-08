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
     * Ajoute les irrigations à la suite de celle en paramètre au set des irrigations disponibles si elles sont possible d'être posées
     * @param irrigation l'irrigation à partir de laquelle il faut rechercher les nouvelles irrigations possible
     */
    public static Optional<Set<Irrigation>> addIrrigationDisponible(@NotNull Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                               Irrigation irrigation, @NotNull Set<Irrigation> irrigationsDisponibles,
                                                @NotNull Set<Irrigation> irrigationsPosees) {

        List<Position> positionsIrrigation = irrigation.getPositions();
        Position position1 = positionsIrrigation.get(0);
        Position position2 = positionsIrrigation.get(1);
        ParcelleCouleur parcelle1 = (ParcelleCouleur) GestionParcelles.chercheParcelle(GestionParcelles.getParcelles(parcellesEtVoisines), position1).get();
        ParcelleCouleur parcelle2 = (ParcelleCouleur) GestionParcelles.chercheParcelle(GestionParcelles.getParcelles(parcellesEtVoisines), position2).get();

        try {
            //on récupere les voisins des 2 parcelles
            Parcelle[] voisinsP1 = GestionParcelles.getVoisinesParcelle(parcellesEtVoisines, parcelle1);
            Parcelle[] voisinsP2 =GestionParcelles.getVoisinesParcelle(parcellesEtVoisines, parcelle2);
            List<Parcelle> parcellesPossiblePourIrrigation = new ArrayList<>();

            //on regarde les voisins communs
            for (Parcelle voisin1 : voisinsP1) {
                for (Parcelle voisin2 : voisinsP2)
                    if (voisin1 == voisin2 && voisin1.getClass() != Etang.class) {
                        parcellesPossiblePourIrrigation.add(voisin1);
                    }
            }
            for (Parcelle parcelle : parcellesPossiblePourIrrigation) {
                if (parcelle.getClass() == ParcelleCouleur.class) {
                    ParcelleCouleur parcelleAIrrigee = (ParcelleCouleur) parcelle;

                    //pour chaque position de l'irrigation, on chereche si il y a dejà une irrigation avec la parcelle à irriguer
                    for (Position position : positionsIrrigation) {
                        Optional<Irrigation> irrigationDejaPosee =
                                chercheIrrigation(position, parcelleAIrrigee.getPosition(), irrigationsPosees);

                        //si il n'y a pas d'irrigation, on ajoute cette position d'irrigation au set d'irrigations disponibles
                        if (irrigationDejaPosee.isEmpty()){
                            List<Position> positionsIrrigationPotentielle = new ArrayList<>();
                            positionsIrrigationPotentielle.add(position);
                            positionsIrrigationPotentielle.add(parcelleAIrrigee.getPosition());
                            Irrigation irrigationPotentielle = new Irrigation(positionsIrrigationPotentielle);
                            boolean dejaDisponible = false;
                            //on vérifie que l'irrigation n'appartient pas déjà au set d'irrigations disponibles
                            for (Irrigation irrigationDispo : irrigationsDisponibles) {
                                if (irrigationDispo.equals(irrigationPotentielle)) {
                                    dejaDisponible = true;
                                    break;
                                }
                            }
                            if (!dejaDisponible) irrigationsDisponibles.add(irrigationPotentielle);
                        }
                    }
                }
            }
            return Optional.of(irrigationsDisponibles);
        }
        catch (ParcelleNonPoseeException e){
            throw new AssertionError(e);
        }
    }

    /**
     * Vérifie si il y a des irrigations autour de la parcelle en paramètre
     * puis ajoute des irrigations disponibles par rapport aux irrigations trouvées
     * @param parcelleCouleur parcelle pour laquelle on veut verifier si il y a des irrigations autour
     */
    public static Optional<Set<Irrigation>> checkIrrigationsAutour(@NotNull Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                                                   ParcelleCouleur parcelleCouleur,
                                                                   @NotNull Set<Irrigation> irrigationsDisponibles,
                                                                   @NotNull Set<Irrigation> irrigationsPosees){
        try {
            Parcelle[] voisins = GestionParcelles.getVoisinesParcelle(parcellesEtVoisines, parcelleCouleur);
            boolean voisinEtang = false;
            Optional<Set<Irrigation>> irrigationsDisponiblesSet = Optional.of(irrigationsDisponibles);

            for (int i = 0; i < voisins.length - 1; i++) {
                // cherche si il y a une irrigation entre les 2 voisins
                Optional<Irrigation> irrigation =
                        chercheIrrigation(voisins[i].getPosition(), voisins[i + 1].getPosition(), irrigationsPosees);

                // si il en existe une, on met à jour le set d'irrigations disponible à partir de cette irrigation
                if (irrigation.isPresent() && irrigationsDisponiblesSet.isPresent()) {
                    irrigationsDisponiblesSet = addIrrigationDisponible(parcellesEtVoisines, irrigation.get(), irrigationsDisponiblesSet.get(), irrigationsPosees);
                }
                if (voisins[i].equals(GestionParcelles.ETANG)) voisinEtang = true;
            }

            Optional<Irrigation> irrigation =
                    chercheIrrigation(voisins[5].getPosition(), voisins[0].getPosition(), irrigationsPosees);
            if (irrigation.isPresent() && irrigationsDisponiblesSet.isPresent()) {
                irrigationsDisponiblesSet = addIrrigationDisponible(parcellesEtVoisines, irrigation.get(), irrigationsDisponiblesSet.get(), irrigationsPosees);
            }
            if (voisins[5].equals(GestionParcelles.ETANG)) voisinEtang = true;

            // si 2 parcelles voisines sont autour de l'étang, alors on ajoute la possibilité d'irrigation dans le set des irrigations disponibles
            if (voisinEtang){
                for (Parcelle parcelle : voisins){
                    if (parcelle.getClass() == ParcelleCouleur.class) {
                        Parcelle[] voisinsParcelle = GestionParcelles.getVoisinesParcelle(parcellesEtVoisines, parcelle);
                        for (Parcelle voisin : voisinsParcelle) {
                            if (voisin.equals(GestionParcelles.ETANG)) {
                                List<Position> positionsIrrigationDispo = new ArrayList<>();
                                positionsIrrigationDispo.add(parcelleCouleur.getPosition());
                                positionsIrrigationDispo.add(parcelle.getPosition());
                                Irrigation irrigationDispo = new Irrigation(positionsIrrigationDispo);
                                irrigationsDisponiblesSet.ifPresent(irrigations -> irrigations.add(irrigationDispo));
                            }
                        }
                        if (irrigationsDisponiblesSet.isPresent()) return irrigationsDisponiblesSet;
                    }
                }
            }
        }
        catch (ParcelleNonPoseeException e){
            throw new AssertionError(e);
        }
        return Optional.empty();
    }

    /**
     * Cherche s'il y a une irrigation entre les positions données
     * @param position1 position de la 1ere parcelle entre laquelle on cherche une irrigation
     * @param position2 position de la 2eme parcelle entre laquelle on cherche une irrigation
     * @return un optional de l'irrigation trouvée, sinon un optional vide
     */
    public static Optional<Irrigation> chercheIrrigation(Position position1, Position position2,
                                                         @NotNull Set<Irrigation> irrigationsPosees){
        List<Position> positionsIrrigationRecherchee = new ArrayList<>();
        positionsIrrigationRecherchee.add(position1);
        positionsIrrigationRecherchee.add(position2);
        Irrigation irrigationRecherchee = new Irrigation(positionsIrrigationRecherchee);

        for (Irrigation irrigation : irrigationsPosees){
            if (irrigation.equals(irrigationRecherchee)) return Optional.of(irrigationRecherchee);
        }
        return Optional.empty();
    }

}
