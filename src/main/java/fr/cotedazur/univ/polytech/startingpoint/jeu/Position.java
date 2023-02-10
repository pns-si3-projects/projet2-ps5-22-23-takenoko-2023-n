package fr.cotedazur.univ.polytech.startingpoint.jeu;

import java.util.Objects;

/**
 * Représente la position d'une pièce du jeu.
 * @author equipe N
 */
public class Position  implements Comparable<Position> {
    // Définition des attributs

    private final int x;
    private final int y;


    // Définition des constructeurs

    /**
     * Construit la position désignée pour l'étang (0,0)
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Construit une position en prenant 2 coordonnées
     * @param x la coordonnée horizontale
     * @param y la coordonnée verticale
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Accesseurs

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


    // Méthodes toString et equals

    @Override
    public int compareTo(Position position){
        if(x > position.x) return 1;
        else if(x < position.x) return -1;
        else if(y > position.y) return 1;
        else if(y < position.y) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
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