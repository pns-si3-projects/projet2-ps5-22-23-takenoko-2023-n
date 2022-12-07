package fr.cotedazur.univ.polytech.startingpoint;

<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
import java.util.Objects;

>>>>>>> 47f8461 (modification de la classe Position)
public class Position {
    //attribut
    private int x;
    private int y;

    //constructeur
    public Position(){
        this(0,0);
    }
>>>>>>> 112726f (creation de la classe Position)
    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

<<<<<<< HEAD
    /**
     * getter de l'attribut x
     * @return la valeur de x
     */
=======
    //acceseur
>>>>>>> 112726f (creation de la classe Position)
    public int getX() {
        return x;
    }

<<<<<<< HEAD
    /**
     * getter de l'attribut y
     * @return la valeur de y
     */
=======
>>>>>>> 112726f (creation de la classe Position)
    public int getY() {
        return y;
    }
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
    /**
     * redefinition de la methode equals
     * @param o objet a comparer
     * @return si l'objet mit en parametre est equivalent a l'actuel
     */
=======

>>>>>>> 47f8461 (modification de la classe Position)
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

<<<<<<< HEAD
    /**
     * redefinition de la methode toString
     * @return une chaine de caractere
     */
    @Override
    public String toString() {
        return "("+x+","+y+")";
=======
    //mutateur
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
>>>>>>> 112726f (creation de la classe Position)
    }
=======
>>>>>>> d8cecf5 (modification classe Position)
=======
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
>>>>>>> 47f8461 (modification de la classe Position)
}
