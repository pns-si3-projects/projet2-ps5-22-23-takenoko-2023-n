package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Positionable;

/**
 * Représente une carte parcelle du jeu ou un emplacement disponible.
 * @author equipe N
 */
public interface Parcelle extends Positionable {
    @Override
    boolean equals(Object o);
}
