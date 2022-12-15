package fr.cotedazur.univ.polytech.startingpoint;


import java.util.List;

/**
 * Permet d'enregistrer une Parcelle avec ses parcelles voisines
 * @author equipe N
 * @version 2.0
 */
public class ParcelleEtVoisines {
    /** Parcelle cible qui peut être un Etang ou une ParcelleCouleur */
    private final Parcelle parcelleCible;

    /** Liste des voisines de la parcelle cible */
    private final Parcelle[] parcellesVoisines;

    /**
     * Constructeur par defaut
     * Creer l'ensemble pour enregistrer la Parcelle avec ses voisines
     * @param parcelle qui est la Parcelle cible
     */
    public ParcelleEtVoisines(Parcelle parcelle, List<ParcelleEtVoisines> parcelleEtVoisines) throws ParcelleNonVoisineException{
        parcelleCible = parcelle;
        this.parcellesVoisines = ajoutVoisin(parcelle,parcelleEtVoisines);

    }

    /**
     * Constructeur pour l'Etang qui ne contient pas de voisines au debut du jeu
     * etang qui est l'Etang du jeu et ne contient pas de voisines au debut
     */
    public ParcelleEtVoisines(Etang etang) {
        parcelleCible = etang;
        parcellesVoisines = new Parcelle[6];
        for (int i = 0; i < 6; i++){
            parcellesVoisines[i] = addParcelleVide(i,etang.getPosition());
        }
    }

    /**
     * Permet de renvoyer une ParcelleDisponible a un indice particulier donc a une position particuliere
     * @param i indice pour renvoyer une certaine ParcelleDisponible
     * @param positionParcelle cette position sert a adapter l'ajout de la parcelle disponible
     * @return Retourne une parcelleDisponible donc une parcelle avec une position
     */
    private ParcelleDisponible addParcelleVide(int i, Position positionParcelle){
        int x = positionParcelle.getX();
        int y = positionParcelle.getY();
        return switch (i){
            case 0 -> new ParcelleDisponible(new Position(x+1,y+1));
            case 1 -> new ParcelleDisponible(new Position(x+2,y));
            case 2 -> new ParcelleDisponible(new Position(x+1,y-1));
            case 3 -> new ParcelleDisponible(new Position(x-1,y-1));
            case 4 -> new ParcelleDisponible(new Position(x-2,y));
            case 5 -> new ParcelleDisponible(new Position(x-1,y+1));
            default -> throw new IllegalArgumentException("Pas bon argument en parametre");
        };
    }

    /**
     * Permet d'ajouter les voisins existants et rempli de classe ParcelleDisponible, les cases du tableau ou il n'y a pas de parcelle
     * @param parcelle Parcelle principal
     * @param voisines Les voisins existant a cote de la parcelle
     * @return Retourne une liste de parcelles avec les voisins existants ou pas autour de parcelle principal
     */
    private Parcelle[] ajoutVoisin(Parcelle parcelle, List<ParcelleEtVoisines> voisines) throws ParcelleNonVoisineException{
        Parcelle[] parcellesVoisinesDisponible = new Parcelle[6];
        for (ParcelleEtVoisines parcelleVoisin : voisines){
            int indicePlaceVoisin = indiceAddVoisine(parcelleVoisin.getParcelleCible());
            parcellesVoisinesDisponible[indicePlaceVoisin] = parcelleVoisin.getParcelleCible();
            parcelleVoisin.addVoisine(parcelle);
        }
        for(int i = 0; i < 6; i++){
            if(parcellesVoisinesDisponible[i] == null){
                parcellesVoisinesDisponible[i] = addParcelleVide(i,parcelle.getPosition());
            }
        }
        return parcellesVoisinesDisponible;
    }

    /**
     * Getter de la parcelle principal
     * @return Retourne la parcelle principal
     */
    public Parcelle getParcelleCible() {
        return parcelleCible;
    }

    /**
     * Getter des parcelles voisins
     * @return Retourne les parcelles voisines de la parcelle principal
     */
    public Parcelle[] getParcellesVoisines() {
        return parcellesVoisines;
    }

    /**
     * Renvoie l'indice de la parcelleVoisine ou cette parcelle peut se mettre dans le tableau des voisins
     * @param parcelle parcelleVoisine qui peut etre ajouter
     * @return
     */
    public int indiceAddVoisine(Parcelle parcelle) {
        int xV = parcelle.getPosition().getX();
        int yV = parcelle.getPosition().getY();
        int xC = parcelleCible.getPosition().getX();
        int yC = parcelleCible.getPosition().getY();

        if(yV == yC){ // Si proche et sur la même ligne
            if(xV - 2 == xC) return 1;
            else if(xV+2 == xC) return 4;
        }
        else{ // Si proche au-dessus ou en-dessous
            if(yV - 1 == yC){
                if(xV - 1 == xC) return 0;
                else if(xV + 1 == xC) return 5;
            }
            else if(yV + 1 == yC){
                if(xV - 1 == xC) return 2;
                else if(xV + 1 == xC) return 3;
            }
        }
        return -1;
    }

    /**
     * Verifie si la Parcelle en parametre peut être voisine de la Parcelle cible
     * @param parcelle est la Parcelle a verifier si elle est voisine de la cible
     * @return si la Parcelle en parametre est la voisine de la Parcelle cible
     */
    public boolean peutEtreVoisine(Parcelle parcelle) {
        int xV = parcelle.getPosition().getX();
        int yV = parcelle.getPosition().getY();
        int xC = parcelleCible.getPosition().getX();
        int yC = parcelleCible.getPosition().getY();

        if (yV == yC && (xV-2 == xC || xV+2 == xC)) return true; // Si proche et sur la même ligne
        return (yV - 1 == yC || yV + 1 == yC) && (xV - 1 == xC || xV + 1 == xC); // Si proche au-dessus ou en-dessous
    }

    /**
     * Permet d'ajouter une Parcelle voisine a la Parcelle cible
     * @param parcelleVoisin est une Parcelle voisine
     */
    public void addVoisine(Parcelle parcelleVoisin) throws ParcelleNonVoisineException {
        int indiceA = indiceAddVoisine(parcelleVoisin);
        if(indiceA == -1) throw new ParcelleNonVoisineException(parcelleCible, parcelleVoisin);
        parcellesVoisines[indiceA] = parcelleVoisin;
    }
}
