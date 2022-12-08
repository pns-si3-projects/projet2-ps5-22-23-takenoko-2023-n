package fr.cotedazur.univ.polytech.startingpoint;

public class Etang implements Parcelle{

    private final Position position=new Position();
    public Etang(){}

    /**
     * getter de l'attribut position
     * @return la valeur de la position
     */
    @Override
    public Position getPosition() {
        return position;
    }
}
