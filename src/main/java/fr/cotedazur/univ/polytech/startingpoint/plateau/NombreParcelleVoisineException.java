package fr.cotedazur.univ.polytech.startingpoint.plateau;

/**
 * Exception si le nombre de voisines désigné est incorrect.
 * @author equipe N
 */
public class NombreParcelleVoisineException extends Exception {
    /**
     * Construit le message de l'exception
     * @param nombreVoisines le nombre de parcelles voisines
     */
    public NombreParcelleVoisineException(int nombreVoisines) {
        super((nombreVoisines > 6) ? "Il y a trop de voisines" : "Il manque des voisines");
    }
}
