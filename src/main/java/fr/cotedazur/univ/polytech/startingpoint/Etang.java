package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

public class Etang implements Parcelle{

    private final Position position;
    public Etang(){
        position = new Position();
    }

    /**
     * getter de l'attribut position
     * @return la valeur de la position
     */
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Etang etang)) return false;
        return Objects.equals(getPosition(), etang.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
