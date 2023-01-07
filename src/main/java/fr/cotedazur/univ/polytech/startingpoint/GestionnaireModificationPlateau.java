package fr.cotedazur.univ.polytech.startingpoint;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Classe qui permet de gérer l'ajout et la modification des parcelles du plateau
 * @author equipe N
 */
public class GestionnaireModificationPlateau {
    // Méthodes d'utilisation
    /**
     * Cherche la liste des parcelles déjà placées qui seront voisines de la parcelle à ajouter
     * @param parcelleAAjouter est la parcelle qu'on souhaite ajouter
     * @param parcelles est un tableau des parcelles positionnées sur le plateau
     * @return la liste des futures voisines de la parcelle à ajouter
     * @throws ParcelleExistanteException si la parcelle à ajouter est déjà existante sur le plateau
     */
    public List<Parcelle> chercheFuturesVoisines(Parcelle parcelleAAjouter, Parcelle[] parcelles) throws ParcelleExistanteException {
        List<Parcelle> parcellesVoisines = new ArrayList<>();

        for (Parcelle parcellePossibleVoisine : parcelles) {
            if (parcelleAAjouter.equals(parcellePossibleVoisine)) throw new ParcelleExistanteException(parcelleAAjouter);
            else if (peutEtreVoisine(parcelleAAjouter, parcellePossibleVoisine)) {
                parcellesVoisines.add(parcellePossibleVoisine);
            }
        }
        return parcellesVoisines;
    }

    /**
     * Vérifie si une parcelle peut être voisine de la parcelle ciblée
     * @param parcelleCible est la parcelle ciblée
     * @param parcellePossibleVoisine est la possible voisine de la parcelle cible
     * @return <code>true</code> si la parcelle donnée est voisine de la parcelle ciblée, <code>false</code> sinon
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
    public Parcelle[] addVoisinesParcelle(Parcelle parcelleAAjouter, List<Parcelle> parcelleVoisineList) throws ParcelleNonVoisineException {
        Parcelle[] parcellesVoisines = new Parcelle[6];

        for (Parcelle parcelleVoisine : parcelleVoisineList) {
            int positionTabVoisin = positionTabVoisin(parcelleAAjouter.getPosition(), parcelleVoisine.getPosition());
            if (positionTabVoisin == -1) throw new ParcelleNonVoisineException(parcelleAAjouter, parcelleVoisine);
            parcellesVoisines[positionTabVoisin] = parcelleVoisine;
        }

        for (int i=0; i<6; i++) {
            if (parcellesVoisines[i] == null) {
                parcellesVoisines[i] = creeParcelleDisponible(i, parcelleAAjouter.getPosition());
            }
        }
        return parcellesVoisines;
    }

    /**
     * Renvoie l'indice d'une parcelle voisine donnée, pour le tableau de voisines
     * @param positionParcelleCible la position de la parcelle ciblée
     * @param positionParcelleVoisine la position de la voisine de la parcelle ciblée
     * @return l'indice où mettre la parcelle voisine dans le tableau de voisines
     */
    protected int positionTabVoisin(Position positionParcelleCible, Position positionParcelleVoisine) {
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
     * Renvoie une ParcelleDisponible avec la position demandée
     * @param indiceTab permet de donner une direction par rapport à une parcelle repère
     * @param positionParcelle est la position de la parcelle repère
     * @return une parcelleDisponible à la position demandée
     */
    public ParcelleDisponible creeParcelleDisponible(int indiceTab, Position positionParcelle) {
        int x = positionParcelle.getX();
        int y = positionParcelle.getY();
        return switch (indiceTab) {
            case 0 -> new ParcelleDisponible(new Position(x+1, y+1));
            case 1 -> new ParcelleDisponible(new Position(x+2, y));
            case 2 -> new ParcelleDisponible(new Position(x+1, y-1));
            case 3 -> new ParcelleDisponible(new Position(x-1, y-1));
            case 4 -> new ParcelleDisponible(new Position(x-2, y));
            case 5 -> new ParcelleDisponible(new Position(x-1, y+1));
            default -> throw new IllegalArgumentException("Indice de direction incorrecte");
        };
    }

    public Optional<Parcelle> getParcelle(Parcelle[] listParcelle,Position position){
        if(listParcelle == null || position == null) return Optional.empty();
        for (int i = 0;i< listParcelle.length;i++){
            Parcelle parcelleActuel = listParcelle[i];
            if(position.equals(parcelleActuel.getPosition())){
                return Optional.of(parcelleActuel);
            }
        }
        return Optional.empty();
    }
}
