package fr.cotedazur.univ.polytech.startingpoint.pioche;

/**
 * Exception si un choix de parcelle piochée est effectué alors qu'aucune pioche n'est en cours.
 * @author equipe N
 */
public class PiocheParcelleVideException extends Exception {
    /**
     * Construit le message de l'exception
     */
    public PiocheParcelleVideException() {
        super("Aucune pioche de parcelle n'a été effectuée, veuillez commencer par piocher");
    }
}
