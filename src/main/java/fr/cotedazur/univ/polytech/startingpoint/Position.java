package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * classe Position, permenant d'avoir une position
 * @author equipe N
 */
public class Position {
    private int x;
    private int y;

    /**
     * contructeur par default pour l'Etang
     */
    public Position(){
        this(0,0);
    }

    /**
     * constructeur de Position avec deux coordonnées
     * @param x coordonnée de l'abscise
     * @param y coordonnée de l'ordonné
     */

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * getter de l'attribut x
     * @return la valeur de x
     */
    
    public int getX() {
        return x;
    }

    /**
     * getter de l'attribut y
     * @return la valeur de y
     */
    public int getY() {
        return y;
    }

    /**
     * redefinition de la methode equals
     * @param o objet a comparer
     * @return si l'objet mit en parametre est equivalent a l'actuel
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getX() == position.getX() && getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    /**
     * redefinition de la methode toString
     * @return une chaine de caractere
     */
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
