package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * Classe Bambou, permenant de créé un bambou
 * @author equipe N
 */
public class Bambou {

    private Position position;
    private Position positionNull=new Position(0,0);

    /**
     * constructeur de Bambou avec un paramtre
     * @param position du bambou
     */
    public Bambou(Position position) {
        if(!position.equals(positionNull)){
            this.position = position;
        }
    }

    /**
     * getter de l'attibut position
     * @return la valeur de position
     */
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bambou bambou = (Bambou) o;
        return Objects.equals(getPosition(), bambou.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
