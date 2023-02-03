package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleDisponible;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Permet de gérer les parcelles du plateau
 * @author équipe N
 */
public class GestionParcelles {
    // Définition des attributs

    public static final Parcelle ETANG = new Etang();


    // Définition d'un constructeur privé pour éviter les instanciations

    private GestionParcelles() {
        throw new IllegalStateException("Utility class");
    }

    
    // Méthodes d'utilisation

    /**
     * Renvoie la parcelle à la position demandée
     * @param parcelles le tableau des parcelles posées
     * @param position la position de la parcelle demandée
     * @return la parcelle à la position demandée
     */
    public static Optional<Parcelle> chercheParcelle(@NotNull Parcelle[] parcelles, @NotNull Position position) {
        for (Parcelle parcelle : parcelles) {
            if (position.equals(parcelle.getPosition())) {
                return Optional.of(parcelle);
            }
        }
        return Optional.empty();
    }

    /**
     * Renvoie les futures voisines déjà posées de la parcelle ciblée
     * @param parcelles le tableau des parcelles posées
     * @param parcelle la parcelle cible
     * @return la liste des futures voisines de la parcelle ciblée
     */
    public static List<Parcelle> futuresVoisines(@NotNull Parcelle[] parcelles, @NotNull Parcelle parcelle)
            throws ParcelleExistanteException {

        if (chercheParcelle(parcelles, parcelle.getPosition()).isPresent()) {
            throw new ParcelleExistanteException(parcelle);
        }

        return chercheFuturesVoisines(parcelles, parcelle);
    }

    /**
     * Renvoie les parcelles qui seront voisines de la parcelle ciblée
     * @param parcelles le tableau des parcelles posées
     * @param parcelle la parcelle ciblée
     * @return la liste des futures voisines de la parcelle ciblée
     */
    private static List<Parcelle> chercheFuturesVoisines(Parcelle[] parcelles, Parcelle parcelle) {
        List<Parcelle> voisinesParcelle = new ArrayList<>();

        for (Parcelle possibleVoisineParcelle : parcelles) {
            Position posPar = parcelle.getPosition();
            Position posVoi = possibleVoisineParcelle.getPosition();

            if (estFutureVoisine(posPar, posVoi)) {
                voisinesParcelle.add(possibleVoisineParcelle);
            }
        }
        return voisinesParcelle;
    }

    /**
     * Vérifie si une parcelle est voisine de la parcelle ciblée
     * @param positionParcelle la position de la parcelle ciblée
     * @param positionVoisine la position de la possible voisine de la parcelle ciblée
     * @return {@code true} si la parcelle donnée est voisine de la parcelle ciblée
     */
    private static boolean estFutureVoisine(Position positionParcelle, Position positionVoisine) {
        int xC = positionParcelle.getX();
        int yC = positionParcelle.getY();
        int xV = positionVoisine.getX();
        int yV = positionVoisine.getY();

        if (yV == yC && (xV == xC + 2 || xV == xC - 2)) return true; // Si proche et sur la même ligne
        return (yV == yC + 1 || yV == yC - 1) && (xV == xC + 1 || xV == xC - 1); // Si proche au-dessus ou au-dessous
    }

    /**
     * Renvoie la direction de la voisine par rapport à la parcelle ciblée
     * @param parcelle la parcelle ciblée
     * @param voisine la voisine de la parcelle ciblée
     * @return la direction de la voisine par rapport à la parcelle ciblée
     */
    public static int indiceVoisine(Parcelle parcelle, Parcelle voisine) {
        int xC = parcelle.getPosition().getX();
        int yC = parcelle.getPosition().getY();
        int xV = voisine.getPosition().getX();
        int yV = voisine.getPosition().getY();

        if (yV == yC) { // Si proche et sur la même ligne
            if (xV == xC + 2) return 1;
            else if (xV == xC - 2) return 4;
        }
        else if (yV == yC + 1) { // Si proche au-dessus
            if (xV == xC + 1) return 0;
            else if (xV == xC - 1) return 5;
        }
        else if (yV == yC - 1) { // Si proche au-dessous
            if (xV == xC + 1) return 2;
            else if (xV == xC - 1) return 3;
        }
        return -1;
    }

    /**
     * Permet de compléter les voisines de la parcelle ciblée par des ParcelleDisponible
     * @param voisinesPosees la liste des voisines déjà posées de la parcelle ciblée
     * @param parcelle la parcelle ciblée
     * @return un tableau des voisines de la parcelle ciblée (complété par des ParcelleDisponible)
     */
    public static Parcelle[] ajouteVoisinesDisponibles(List<Parcelle> voisinesPosees, Parcelle parcelle) {
        Parcelle[] voisinesParcelle = new Parcelle[6];

        for (Parcelle voisine : voisinesPosees) {
            int indiceVoisine = indiceVoisine(parcelle, voisine);
            voisinesParcelle[indiceVoisine] = voisine;
        }

        for (int i=0; i<6; i++) {
            if (voisinesParcelle[i] == null) {
                voisinesParcelle[i] = creeVoisineDisponible(i, parcelle.getPosition());
            }
        }
        return voisinesParcelle;
    }

    /**
     * Renvoie une ParcelleDisponible à la position demandée
     * @param indiceDirection l'indice de direction par rapport à une position repère
     * @param positionParcelle la position repère
     * @return une parcelleDisponible à la position demandée
     */
    private static ParcelleDisponible creeVoisineDisponible(int indiceDirection, Position positionParcelle) {
        int x = positionParcelle.getX();
        int y = positionParcelle.getY();

        return switch (indiceDirection) {
            case 0 -> new ParcelleDisponible(new Position(x+1, y+1));
            case 1 -> new ParcelleDisponible(new Position(x+2, y));
            case 2 -> new ParcelleDisponible(new Position(x+1, y-1));
            case 3 -> new ParcelleDisponible(new Position(x-1, y-1));
            case 4 -> new ParcelleDisponible(new Position(x-2, y));
            case 5 -> new ParcelleDisponible(new Position(x-1, y+1));
            default -> throw new IllegalArgumentException("Indice de direction incorrecte");
        };
    }

    /**
     * Renvoie les nouvelles positions disponibles
     * @param parcelles le tableau des parcelles posées
     * @param voisines le tableau des voisines de la dernière parcelle posée
     * @return la liste des nouvelles positions disponibles
     */
    public static List<Position> positionsDisponibles(Parcelle[] parcelles, Parcelle[] voisines) {
        List<Position> positionsDisponibles = new ArrayList<>();

        for (Parcelle voisine : voisines) {
            if (voisine instanceof ParcelleDisponible parcelleDisponible
                    && estPositionDisponible(parcelles, parcelleDisponible)) {

                positionsDisponibles.add(voisine.getPosition());
            }
        }

        return positionsDisponibles;
    }

    /**
     * Renvoie si la position de la parcelle disponible est une position possible pour poser une parcelle
     * @param parcelles le tableau des parcelles posées
     * @param parcelleDisponible la parcelle disponible ciblée
     * @return {@code true} si la position de la parcelle disponible est posable
     */
    private static boolean estPositionDisponible(Parcelle[] parcelles, ParcelleDisponible parcelleDisponible) {
        List<Parcelle> parcellesVoisines = GestionParcelles.chercheFuturesVoisines(parcelles, parcelleDisponible);

        if (parcellesVoisines.contains(ETANG)) {
            return true;
        }
        int nbVoisines = parcellesVoisines.size();
        return nbVoisines >= 2 && nbVoisines < 6;
    }
}
