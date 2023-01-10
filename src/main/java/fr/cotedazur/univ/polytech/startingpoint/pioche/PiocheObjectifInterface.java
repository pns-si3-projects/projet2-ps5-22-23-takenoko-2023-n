package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.Objectif;

/**
 * Interface représentant une pioche d'objectifs (parcelle, panda ou jardinier)
 * @author equipe N
 */
public interface PiocheObjectifInterface {
    /**
     * Renvoie le nombre de cartes que contient la pioche
     * @return le nombre de cartes dans la pioche
     */
    int getNombreObjectifsRestants();

    /**
     * Renvoie si la pioche d'objectifs ne contient plus de cartes
     * @return <code>true</code> si la pioche est vide, <code>false</code> sinon
     */
    boolean isEmpty();

    /**
     * Renvoie une carte objectif désignée dans la pioche
     * @return la carte objectif piochée
     * @implNote la pioche ne doit pas être vide
     */
    Objectif pioche();
}
