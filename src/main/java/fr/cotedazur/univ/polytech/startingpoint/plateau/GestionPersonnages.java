package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleDisponible;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Permet de gérer les personnages du plateau
 * @author équipe N
 */
public class GestionPersonnages {
    // Définition d'un constructeur privé pour éviter les instanciations

    private GestionPersonnages() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Renvoie la liste des positions de déplacement possible
     * @param parcellesEtVoisines la map des parcelles et leurs voisines
     * @param position la position de départ
     * @return la liste des positions de déplacement possible
     */
    public static List<Position> deplacementsPossibles(@NotNull Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                                       @NotNull Position position) {
        List<Position> positionsDeplacement = new ArrayList<>();
        Parcelle[] parcelles = GestionParcelles.getParcelles(parcellesEtVoisines);

        Optional<Parcelle> parcelleOpt = GestionParcelles.chercheParcelle(parcelles, position);
        if (parcelleOpt.isEmpty()) {
            return new ArrayList<>();
        }

        for (int i=0; i<6; i++) {
            positionsDeplacement.addAll(positionsPossiblesDirection(parcellesEtVoisines, parcelleOpt.get(), i));
        }

        return positionsDeplacement;
    }

    /**
     * Renvoie la liste des positions de déplacement possible selon la direction demandée
     * @param parcellesEtVoisines la map des parcelles et leurs voisines
     * @param parcelle la parcelle de départ
     * @param indiceDirection la direction demandée
     * @return la liste des positions de déplacement possible selon une direction demandée
     */
    private static List<Position> positionsPossiblesDirection(Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                                              Parcelle parcelle, int indiceDirection) {
        List<Position> positions = new ArrayList<>();
        Parcelle parcelleSuivante = parcelleSuivante(parcellesEtVoisines, parcelle, indiceDirection);

        while (!(parcelleSuivante instanceof ParcelleDisponible)) {
            positions.add(parcelleSuivante.getPosition());
            parcelleSuivante = parcelleSuivante(parcellesEtVoisines, parcelleSuivante, indiceDirection);
        }

        return positions;
    }

    /**
     * Renvoie la parcelle suivante à la parcelle donnée selon la direction demandée
     * @param parcellesEtVoisines la map des parcelles et leurs voisines
     * @param parcelle la parcelle de départ
     * @param indiceDirection la direction demandée
     * @return la parcelle suivante à la parcelle donnée selon la direction demandée
     */
    private static Parcelle parcelleSuivante(Map<Parcelle, Parcelle[]> parcellesEtVoisines,
                                             Parcelle parcelle, int indiceDirection) {
        try {
            Parcelle[] voisines = GestionParcelles.getVoisinesParcelle(parcellesEtVoisines, parcelle);
            return GestionParcelles.parcelleSuivante(voisines, indiceDirection);
        } catch (ParcelleNonPoseeException e) {
            throw new AssertionError("La parcelle doit etre sur le plateau");
        }
    }
}
