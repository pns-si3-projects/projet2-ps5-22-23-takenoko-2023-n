package fr.cotedazur.univ.polytech.startingpoint.pioche;

/**
 * Exception s'il est demandé de piocher alors que la précédente pioche n'est terminée
 * @author equipe N
 */
public class PiocheParcelleEnCoursException extends Exception {
    public PiocheParcelleEnCoursException() {
        super("Une pioche de parcelles est déjà en cours");
    }
}
