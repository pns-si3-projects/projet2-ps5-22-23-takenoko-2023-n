package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Renvoie une exception si un joueur a déjà une pioche de parcelles en cours
 * @author equipe N
 */
public class PiocheParcelleEnCoursException extends Exception {
    public PiocheParcelleEnCoursException() {
        super("Une pioche de parcelles est déjà en cours");
    }
}
