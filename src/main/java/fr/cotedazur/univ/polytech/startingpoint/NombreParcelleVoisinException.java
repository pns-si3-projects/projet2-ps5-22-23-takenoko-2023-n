package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Renvoi une exception si le nombre de parcelle n'est pas valide
 * @author equipe N
 */
public class NombreParcelleVoisinException extends Exception{
    public NombreParcelleVoisinException(int nombreVoisin){
        super((nombreVoisin > 6)? "Il y a trop de voisins" : "Il manque des voisins");
    }
}
