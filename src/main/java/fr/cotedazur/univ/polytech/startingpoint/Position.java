package fr.cotedazur.univ.polytech.startingpoint;

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
}
