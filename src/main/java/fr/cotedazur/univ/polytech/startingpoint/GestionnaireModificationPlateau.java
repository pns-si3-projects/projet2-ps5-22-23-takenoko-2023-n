package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Classe qui permet de gérer l'ajout et la modification des parcelles du plateau
 * @author equipe N
 */
public class GestionnaireModificationPlateau {
    // Méthodes d'utilisation
    /**
     * Cherche la liste des parcelles déjà placées qui seront voisines de la parcelle à ajouter
     * @param parcelleAAjouter est la parcelle qu'on souhaite ajouter
     * @return la liste de futures voisines de la parcelle à ajouter
     * @throws ParcelleExistanteException si la parcelle à ajouter est déjà existante sur le plateau
     */
    public List<Parcelle> chercheFuturesVoisines(Parcelle parcelleAAjouter) throws ParcelleExistanteException {
        Set<Parcelle> listParcelle= Main.PLATEAU.getListParcelle();
        Iterator<Parcelle> iterateurParcelle = listParcelle.iterator();
        List<Parcelle> parcelleVoisineList = new ArrayList<>();

        while (iterateurParcelle.hasNext()) {
            Parcelle parcellePossibleVoisine = iterateurParcelle.next();
            if (parcelleAAjouter.equals(parcellePossibleVoisine)) throw new ParcelleExistanteException(parcelleAAjouter);
            else if (peutEtreVoisine(parcelleAAjouter, parcellePossibleVoisine)) {
                parcelleVoisineList.add(parcellePossibleVoisine);
            }
        }
        return parcelleVoisineList;
    }

    /**
     * Vérifie si une parcelle peut être voisine de la parcelle ciblée
     * @param parcelleCible est la parcelle ciblée
     * @param parcellePossibleVoisine est la possible voisine de la parcelle cible
     * @return si la parcelle donnée est voisine de la parcelle ciblée
     */
    private boolean peutEtreVoisine(Parcelle parcelleCible, Parcelle parcellePossibleVoisine) {
        int xV = parcellePossibleVoisine.getPosition().getX();
        int yV = parcellePossibleVoisine.getPosition().getY();
        int xC = parcelleCible.getPosition().getX();
        int yC = parcelleCible.getPosition().getY();

        if (yV == yC && (xV-2 == xC || xV+2 == xC)) return true; // Si proche et sur la même ligne
        return (yV - 1 == yC || yV + 1 == yC) && (xV - 1 == xC || xV + 1 == xC); // Si proche au-dessus ou en-dessous
    }

    /**
     * Crée la liste des parcelles voisines de la parcelle à ajouter complétée par des ParcelleDisponible
     * @param parcelleAAjouter la parcelle à ajouter
     * @param parcelleVoisineList la liste des voisines déjà existantes
     * @return la liste de toutes les voisines de la parcelle à ajouter
     * @throws ParcelleNonVoisineException si une parcelle donnée n'est pas une voisine de la parcelle à ajouter
     */
    public Parcelle[] addVoisinParcelle(Parcelle parcelleAAjouter, List<Parcelle> parcelleVoisineList) throws ParcelleNonVoisineException {
        Parcelle[] parcellesVoisines = new Parcelle[6];

        for (Parcelle parcelleVoisine : parcelleVoisineList) {
            int positionTabVoisin = positionTabVoisin(parcelleAAjouter.getPosition(), parcelleVoisine.getPosition());
            if (positionTabVoisin == -1) throw new ParcelleNonVoisineException(parcelleAAjouter, parcelleVoisine);
            parcellesVoisines[positionTabVoisin] = parcelleVoisine;
        }

        for (int i=0; i<6; i++) {
            if (parcellesVoisines[i] == null) {
                parcellesVoisines[i] = addParcelleVide(i, parcelleAAjouter.getPosition());
            }
        }

        return parcellesVoisines;
    }

    /**
     * Renvoie l'indice d'une parcelle voisine donnée dans le tableau de voisines
     * @param positionParcelleCible la position de la parcelle ciblée
     * @param positionParcelleVoisine la position de la voisine de la parcelle ciblée
     * @return l'indice où mettre la parcelle voisine dans le tableau de voisines
     */
    private int positionTabVoisin(Position positionParcelleCible, Position positionParcelleVoisine) {
        int xV = positionParcelleVoisine.getX();
        int yV = positionParcelleVoisine.getY();
        int xC = positionParcelleCible.getX();
        int yC = positionParcelleCible.getY();

        if (yV == yC) { // Si proche et sur la même ligne
            if (xV - 2 == xC) return 1;
            else if (xV+2 == xC) return 4;
        }
        else if (yV - 1 == yC) { // Si proche en-dessous
            if (xV - 1 == xC) return 0;
            else if (xV + 1 == xC) return 5;
        }
        else if (yV + 1 == yC) { // Si proche au-dessus
            if (xV - 1 == xC) return 2;
            else if (xV + 1 == xC) return 3;
        }
        return -1;
    }

    /**
     * Permet de renvoyer une ParcelleDisponible à un indice particulier donc à une position particulière
     * @param indiceTab (entre 0 et 5) permet de savoir dans quelle direction placer la ParcelleDisponible
     * @param positionRepere sert de repère pour le positionnement de la ParcelleDisponible
     * @return une parcelleDisponible donc une parcelle avec une position mais pas jouable
     */
    public ParcelleDisponible addParcelleVide(int indiceTab, Position positionRepere) {
        int x = positionRepere.getX();
        int y = positionRepere.getY();
        return switch (indiceTab) {
            case 0 -> new ParcelleDisponible(new Position(x+1, y+1));
            case 1 -> new ParcelleDisponible(new Position(x+2, y));
            case 2 -> new ParcelleDisponible(new Position(x+1, y-1));
            case 3 -> new ParcelleDisponible(new Position(x-1, y-1));
            case 4 -> new ParcelleDisponible(new Position(x-2, y));
            case 5 -> new ParcelleDisponible(new Position(x-1, y+1));
            default -> throw new IllegalArgumentException("L'indice donné n'est pas accepté");
        };
    }
}
