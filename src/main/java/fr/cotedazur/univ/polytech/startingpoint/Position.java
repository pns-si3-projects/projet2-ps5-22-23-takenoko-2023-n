package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * Classe permettant d'avoir une position
 * @author equipe N
 */
public class Position {
    // Définition des attributs
    private int x;
    private int y;


    // Définition des constructeurs
    /**
     * Contructeur pour l'etang
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Constructeur par défaut
     * @param x coordonnée horizontale
     * @param y coordonnée verticale
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    // Accesseurs et méthode toString et equals
    /**
     * Renvoie la valeur de l'attribut x
     * @return la valeur de x
     */
    public int getX() {
        return x;
    }

    /**
     * Renvoie la valeur de l'attribut y
     * @return la valeur de y
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

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
}