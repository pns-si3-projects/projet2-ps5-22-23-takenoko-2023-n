package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

/**
 * Permet de renvoyer les déplacements possibles du plateau
 * @author equipe N
 * @version 1.0
 */
public class GestionnairePossibilitePlateau {
    Plateau plateau;
    public GestionnairePossibilitePlateau(Plateau plateau){
        this.plateau = plateau;
    }

    /**
     * Méthode privé qui renvoie vrai si la position de la parcelle est dans la diagonale droite du personnage
     * @param positionPersonnage La position du personnage
     * @param positionParcelleACheck La position de la parcelle à vérifier
     * @return Renvoie vrai si la parcelle est dans la diagonale droite du personnage
     */
    private boolean possibleDeplacementDiagonaleDroite(Position positionPersonnage, Position positionParcelleACheck){
        int xPerso = positionPersonnage.getX();
        int yPerso = positionPersonnage.getY();
        int xParcelle = positionParcelleACheck.getX();
        int yParcelle = positionParcelleACheck.getY();

        if(Math.abs(xPerso-xParcelle) == Math.abs(yPerso-yParcelle)){
            if((xParcelle > xPerso && yParcelle > yPerso) || (xParcelle < xPerso && yParcelle < yPerso)){
                return true;
            }
        }

        return false;
    }

    /**
     * Renvoie la liste de Position de déplacement possible en diagonale droite dans le plateau
     * @param personnage Le panda ou le Jardinier
     * @return Renvoie la liste de Position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageDiagonaleDroite(Personnage personnage){
        List<Position> listPositionDeplacementPossible = new ArrayList<>();
        Set<Parcelle> listParcelleMap = this.plateau.getListParcelle();
        Iterator<Parcelle> iterateurMap = listParcelleMap.iterator();
        Position positionPersonnage = personnage.getPosition();

        while (iterateurMap.hasNext()){
            Position positionParcelle = iterateurMap.next().getPosition();
            if(possibleDeplacementDiagonaleDroite(positionPersonnage,positionParcelle)){
                listPositionDeplacementPossible.add(positionParcelle);
            }
        }

        return listPositionDeplacementPossible;
    }

    /**
     * Méthode privé qui renvoie vrai si la position de la parcelle est dans la diagonale gauche du personnage
     * @param positionPersonnage La position du personnage
     * @param positionParcelleACheck La position de la parcelle à vérifier
     * @return Renvoie vrai si la parcelle est dans la diagonale gauche du personnage
     */
    private boolean possibleDeplacementDiagonaleGauche(Position positionPersonnage, Position positionParcelleACheck){
        int xPerso = positionPersonnage.getX();
        int yPerso = positionPersonnage.getY();
        int xParcelle = positionParcelleACheck.getX();
        int yParcelle = positionParcelleACheck.getY();

        if(Math.abs(xPerso-xParcelle) == Math.abs(yPerso-yParcelle)){
            if((xParcelle > xPerso && yParcelle < yPerso) || (xParcelle < xPerso && yParcelle > yPerso)){
                return true;
            }
        }

        return false;
    }

    /**
     * Renvoie la liste de Position de déplacement possible en diagonale gauche dans le plateau
     * @param personnage Le panda ou le Jardinier
     * @return Renvoie la liste de Position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageDiagonaleGauche(Personnage personnage){
        List<Position> listPositionDeplacementPossible = new ArrayList<>();
        Set<Parcelle> listParcelleMap = this.plateau.getListParcelle();
        Iterator<Parcelle> iterateurMap = listParcelleMap.iterator();
        Position positionPersonnage = personnage.getPosition();

        while (iterateurMap.hasNext()){
            Position positionParcelle = iterateurMap.next().getPosition();
            if(possibleDeplacementDiagonaleGauche(positionPersonnage,positionParcelle)){
                listPositionDeplacementPossible.add(positionParcelle);
            }
        }

        return listPositionDeplacementPossible;
    }

    /**
     * Méthode privé qui renvoie vrai si la position de la parcelle est dans la même ligne que celle du personnage
     * @param positionPersonnage La position du personnage
     * @param positionParcelleACheck La position de la parcelle à vérifier
     * @return Renvoie vrai si la parcelle est dans la même ligne que le personnage
     */
    private boolean possibleDeplacementHorizontal(Position positionPersonnage, Position positionParcelleACheck){
        int xPerso = positionPersonnage.getX();
        int yPerso = positionPersonnage.getY();
        int xParcelle = positionParcelleACheck.getX();
        int yParcelle = positionParcelleACheck.getY();

        return (xPerso != xParcelle && yParcelle == yPerso && (xPerso - xParcelle)%2 == 0);
    }

    /**
     * Renvoie la liste de Position de déplacement possible dans la même ligne dans le plateau
     * @param personnage Le panda ou le Jardinier
     * @return Renvoie la liste de Position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageHorizontal(Personnage personnage){
        List<Position> listPositionDeplacementPossible = new ArrayList<>();
        Set<Parcelle> listParcelleMap = plateau.getListParcelle();
        Iterator<Parcelle> iterateurMap = listParcelleMap.iterator();
        Position positionPersonnage = personnage.getPosition();

        while (iterateurMap.hasNext()){
            Position positionParcelle = iterateurMap.next().getPosition();
            if(possibleDeplacementHorizontal(positionPersonnage,positionParcelle)){
                listPositionDeplacementPossible.add(positionParcelle);
            }
        }

        return listPositionDeplacementPossible;
    }
}
