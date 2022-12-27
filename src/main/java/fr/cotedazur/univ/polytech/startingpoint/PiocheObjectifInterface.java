package fr.cotedazur.univ.polytech.startingpoint;

public interface PiocheObjectifInterface {
    /**
     * Renvoie le nombre de cartes que contient la pioche
     * @return le nombre de cartes dans la pioche
     */
    public int getNombreObjectifsRestants();

    /**
     * Renvoie si la pioche d'objectifs ne contient plus de cartes
     * @return true la pioche est vide, false sinon
     */
    public boolean isEmpty();

    /**
     * Renvoie une carte objectif désignée dans la pioche
     * @return la carte objectif piochée
     * @implNote La pioche ne doit pas être vide
     */
    public Objectif pioche();
}
