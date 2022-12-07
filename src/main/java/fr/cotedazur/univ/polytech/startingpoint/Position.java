package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

public class Position {
    //attribut
    private int x;
    private int y;

    //constructeur
    public Position(){
        this(0,0);
    }
    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    //acceseur
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
