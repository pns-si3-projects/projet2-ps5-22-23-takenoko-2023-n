package fr.cotedazur.univ.polytech.startingpoint.pioche;

/**
 * Exception dans le cas où il est demandé de piocher alors qu'une pioche n'a pas été terminée
 * @author equipe N
 */
public class PiocheParcelleEnCoursException extends Exception {
    public PiocheParcelleEnCoursException() {
        super("Une pioche de parcelles est déjà en cours");
    }
}
