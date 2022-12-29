package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Classe permettant de Gérer l'ajout et la modification des parcelles du plateau
 * @author equipe N
 * @version 1.0
 */
public class GestionnaireModificationPlateau {
    public GestionnaireModificationPlateau(){

    }
    /**
     * Permet de renvoyer une ParcelleDisponible a un indice particulier donc a une position particuliere
     * @param indiceTab indice pour renvoyer une certaine ParcelleDisponible
     * @param positionParcelle cette position sert a adapter l'ajout de la parcelle disponible
     * @return Retourne une parcelleDisponible donc une parcelle avec une position
     */
    public ParcelleDisponible addParcelleVide(int indiceTab, Position positionParcelle){
        int x = positionParcelle.getX();
        int y = positionParcelle.getY();
        return switch (indiceTab){
            case 0 -> new ParcelleDisponible(new Position(x+1,y+1));
            case 1 -> new ParcelleDisponible(new Position(x+2,y));
            case 2 -> new ParcelleDisponible(new Position(x+1,y-1));
            case 3 -> new ParcelleDisponible(new Position(x-1,y-1));
            case 4 -> new ParcelleDisponible(new Position(x-2,y));
            case 5 -> new ParcelleDisponible(new Position(x-1,y+1));
            default -> throw new IllegalArgumentException("Pas bon argument en parametre");
        };
    }
}
