package fr.cotedazur.univ.polytech.startingpoint.plateau;

/**
 * Exception si le nombre de voisines désigné est incorrect.
 * @author equipe N
 */
public class NombreParcelleVoisineException extends Exception {
    public NombreParcelleVoisineException(int nombreVoisines) {
        super((nombreVoisines > 6) ? "Il y a trop de voisines" : "Il manque des voisines");
    }
}
