package fr.cotedazur.univ.polytech.startingpoint;

public class Position {
    //attribut
    private int x;
    private int y;

    //constructeur
    public Position(){
        x=0;
        y=0;
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

    //mutateur
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
