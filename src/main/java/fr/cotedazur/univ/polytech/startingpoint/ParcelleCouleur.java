package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

public class ParcelleCouleur implements Parcelle{
    private final Position position;
    private Bambou bambou;
    private int compteurBambou;

    /**
     * Constructeur de la classe ParcelleCouleur
     * @param p position unique de la parcelle
     */
    public ParcelleCouleur(Position p){
        if(p == null) throw new NullPointerException("La position ne doit pas etre vide");
        position = p;
        compteurBambou = 0;
    }

    /**
     * getter le l'attribut bambou
     * @return la valeur de l'attribuy bambou
     */
    public Bambou getBambou() {
        return new Bambou(position) ;
    }


    /**
     * setter de l'atribut bambou
     * @param bambou sur une parcelle
     */
    public void setBambou(Bambou bambou) {
        this.bambou = bambou;
    }

    /**
     * getter le l'attribut compteurBambou
     * @return compteurBambou, le nombre de bambou dans la parcelle
     */
    public int getCompteurBambou() {
        return compteurBambou;
    }

    /**
     * ajoute le position de la parcelle au bambou
     * @return true si on a ajouter un bambou a la parcelle, fasle si on  a pas pu ajouter un bambou a la parcelle
     */
    public boolean addBambou(){
        if(compteurBambou<4){
            compteurBambou++;
            return true;
        }else{
            return false;
        }
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
        else if(obj == null || obj.getClass() != this.getClass()) return false;
        ParcelleCouleur parcelleToCompare = (ParcelleCouleur) obj;
        return position.equals(parcelleToCompare.getPosition());
    }

    @Override
    public int hashCode(){
        return Objects.hash(position);
    }
}
