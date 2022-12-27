package fr.cotedazur.univ.polytech.startingpoint;

public interface PiocheObjectifInterface {
    /**
     * Renvoie le nombre de cartes que contient la pioche
     * @return le nombre de cartes dans la pioche
     */
    public int getNombreObjectifsRestants();

    public boolean isEmpty();

    /**
     * Renvoie une carte objectif désignée dans la pioche
     * @return la carte objectif piochée
     * @implNote La pioche ne doit pas être vide
     */
    public Objectif pioche();
}
