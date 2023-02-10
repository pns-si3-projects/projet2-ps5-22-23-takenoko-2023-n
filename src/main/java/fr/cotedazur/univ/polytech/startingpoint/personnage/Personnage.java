package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Positionable;

/**
 * Représente un personnage du jeu (panda et jardinier).
 * @author equipe N
 */
public interface Personnage extends Positionable {
    /**
     * Déplace le personnage à la nouvelle position
     * @param position la nouvelle position
     */
    void move(Position position) ;
}
