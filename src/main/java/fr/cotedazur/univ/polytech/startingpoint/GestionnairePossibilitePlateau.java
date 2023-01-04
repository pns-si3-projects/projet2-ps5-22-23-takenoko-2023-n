package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet de renvoyer les déplacements possibles sur le plateau
 * @author equipe N
 */
public class GestionnairePossibilitePlateau {
    // Définition des attributs
    private final Plateau plateau;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param plateau est le plateau du jeu
     */
    public GestionnairePossibilitePlateau(Plateau plateau) {
        this.plateau = plateau;
    }


    // Méthodes d'utilisation
    /**
     * Renvoie la liste de position de déplacement possible en diagonale droite sur le plateau
     * @param positionPersonnage la position du personnage
     * @return la liste de position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageDiagonaleDroite(Position positionPersonnage) {
        Parcelle[] parcelles = plateau.getParcelles();
        List<Position> listPositions = new ArrayList<>();

        for (Parcelle parcelle : parcelles) {
            Position positionParcelle = parcelle.getPosition();
            if (possibleDeplacementDiagonaleDroite(positionPersonnage, positionParcelle)) {
                listPositions.add(positionParcelle);
            }
        }
        return listPositions;
    }

    /**
     * Renvoie si la position de la parcelle est dans la diagonale droite du personnage
     * @param positionPersonnage la position du personnage
     * @param positionParcelle la position de la parcelle à vérifier
     * @return <code>true</code> si la parcelle est dans la diagonale droite du personnage, <code>false</code> sinon
     */
    private boolean possibleDeplacementDiagonaleDroite(Position positionPersonnage, Position positionParcelle) {
        int xPerso = positionPersonnage.getX();
        int yPerso = positionPersonnage.getY();
        int xParcelle = positionParcelle.getX();
        int yParcelle = positionParcelle.getY();

        if (Math.abs(xPerso-xParcelle) == Math.abs(yPerso-yParcelle)) {
            return (xParcelle > xPerso && yParcelle > yPerso) || (xParcelle < xPerso && yParcelle < yPerso);
        }
        return false;
    }

    /**
     * Renvoie la liste de position de déplacement possible en diagonale gauche sur le plateau
     * @param positionPersonnage la position du personnage
     * @return la liste de position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageDiagonaleGauche(Position positionPersonnage) {
        Parcelle[] parcelles = plateau.getParcelles();
        List<Position> listPositions = new ArrayList<>();

        for (Parcelle parcelle : parcelles) {
            Position positionParcelle = parcelle.getPosition();
            if (possibleDeplacementDiagonaleGauche(positionPersonnage, positionParcelle)) {
                listPositions.add(positionParcelle);
            }
        }
        return listPositions;
    }

    /**
     * Renvoie si la position de la parcelle est dans la diagonale gauche du personnage
     * @param positionPersonnage la position du personnage
     * @param positionParcelle la position de la parcelle à vérifier
     * @return <code>true</code> si la parcelle est dans la diagonale gauche du personnage, <code>false</code> sinon
     */
    private boolean possibleDeplacementDiagonaleGauche(Position positionPersonnage, Position positionParcelle) {
        int xPerso = positionPersonnage.getX();
        int yPerso = positionPersonnage.getY();
        int xParcelle = positionParcelle.getX();
        int yParcelle = positionParcelle.getY();

        if (Math.abs(xPerso-xParcelle) == Math.abs(yPerso-yParcelle)) {
            return (xParcelle > xPerso && yParcelle < yPerso) || (xParcelle < xPerso && yParcelle > yPerso);
        }
        return false;
    }

    /**
     * Renvoie la liste de position de déplacement possible sur la même ligne du plateau
     * @param positionPersonnage la position du personnage
     * @return la liste de position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageHorizontal(Position positionPersonnage) {
        Parcelle[] parcelles = plateau.getParcelles();
        List<Position> listPositions = new ArrayList<>();

        for (Parcelle parcelle : parcelles) {
            Position positionParcelle = parcelle.getPosition();
            if (possibleDeplacementHorizontal(positionPersonnage, positionParcelle)) {
                listPositions.add(positionParcelle);
            }
        }
        return listPositions;
    }

    /**
     * Renvoie si la position de la parcelle est sur la même ligne que le personnage
     * @param positionPersonnage la position du personnage
     * @param positionParcelle la position de la parcelle à vérifier
     * @return <code>true</code> si la parcelle est sur la même ligne que le personnage, <code>false</code> sinon
     */
    private boolean possibleDeplacementHorizontal(Position positionPersonnage, Position positionParcelle) {
        int xPerso = positionPersonnage.getX();
        int yPerso = positionPersonnage.getY();
        int xParcelle = positionParcelle.getX();
        int yParcelle = positionParcelle.getY();

        return (xPerso != xParcelle && yParcelle == yPerso && (xPerso - xParcelle)%2 == 0);
    }
}
