package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Classe du jardinier
 * @author equipe N
 * @version 1.0
 */
public class Jardinier implements Positionnable{
    private Position position;

    /**
     * Constructeur par défaut du panda qui commence à la position de depart soit 0
     */
    public Jardinier(){
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
