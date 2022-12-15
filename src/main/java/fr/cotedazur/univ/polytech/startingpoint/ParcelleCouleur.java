package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

public class ParcelleCouleur implements Parcelle{
    private final Position position;

    /**
     * Constructeur de la classe ParcelleCouleur
     * @param p position unique de la parcelle
     */
    public ParcelleCouleur(Position p){
        if(p == null) throw new NullPointerException("La position ne doit pas etre vide");
        position = p;
    }

    /**
     * Renvoie la position de la parcelle
     * @return Retourne la position de la parcelle
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Compare si l'objet donnee en parametre est le meme que lui meme
     * @param obj objet a compare avec cette Parcelle couleur
     * @return Retourne true si ils ont la meme position sinon false
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        else if(obj == null) return false;
        if(obj instanceof Parcelle) {
            Parcelle parcelleToCompare = (Parcelle) obj;
            return position.equals(parcelleToCompare.getPosition());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(position);
    }
}
