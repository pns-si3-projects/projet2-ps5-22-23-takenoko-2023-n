package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Exception dans le cas où le nombre de voisines désigné est incorrecte
 * @author equipe N
 */
public class NombreParcelleVoisineException extends Exception {
    public NombreParcelleVoisineException(int nombreVoisines) {
        super((nombreVoisines > 6) ? "Il y a trop de voisines" : "Il manque des voisines");
    }
}
