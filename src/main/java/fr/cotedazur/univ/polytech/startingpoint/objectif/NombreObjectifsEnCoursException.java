package fr.cotedazur.univ.polytech.startingpoint.objectif;

/**
 * Exception dans le cas où le nombre d'objectifs maximum en cours est déjà atteint
 * et qu'un ajout d'objectif à réaliser est effectué
 * @author equipe N
 */
public class NombreObjectifsEnCoursException extends Exception {
    public NombreObjectifsEnCoursException() {
        super("Le joueur ne peut pas avoir plus de 5 objectifs en cours de réalisation");
    }
}
