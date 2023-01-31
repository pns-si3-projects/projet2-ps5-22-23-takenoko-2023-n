package fr.cotedazur.univ.polytech.startingpoint.objectif;

/**
 * Exception si le nombre maximum d'objectifs en main est atteint.
 * @author equipe N
 */
public class NombreObjectifsEnCoursException extends Exception {
    /**
     * Construit le message de l'exception
     */
    public NombreObjectifsEnCoursException() {
        super("Le joueur ne peut pas avoir plus de 5 objectifs en main");
    }
}
