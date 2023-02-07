package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Positionable;

/**
 * Représente un personnage du jeu (panda et jardinier).
 * @author equipe N
 */
public interface Personnage extends Positionable {
    /**
     * deplace le personnage a la nouvelle position
     * @param position la nouvelle position
     * @return <true> le deplacement a ete effectuer </true> <false> le deplacement n'a pas ete effectuer </false>
     */
    boolean move(Position position) ;
}
