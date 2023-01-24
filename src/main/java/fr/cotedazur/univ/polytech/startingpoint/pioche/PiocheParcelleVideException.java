package fr.cotedazur.univ.polytech.startingpoint.pioche;

public class PiocheParcelleVideException extends Exception {
    public PiocheParcelleVideException() {
        super("Aucune pioche de parcelle n'a été effectuée, veuillez commencer par piocher");
    }
}
