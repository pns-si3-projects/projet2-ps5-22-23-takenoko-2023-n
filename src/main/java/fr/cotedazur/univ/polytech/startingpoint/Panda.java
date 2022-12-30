package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Classe du panda
 * @author equipe N
 * @version 1.0
 */
public class Panda implements Positionnable{
    private Position position;

    /**
     * Constructeur par défaut du panda qui commence à la position de depart soit 0
     */
    public Panda(){
        position = new Position();
    }

    /**
     * Renvoie la position du panda
     * @return Renvoie la position
     */
    @Override
    public Position getPosition(){
        return position;
    }
}
