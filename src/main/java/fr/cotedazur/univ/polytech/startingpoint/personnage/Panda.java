package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

/**
 * Classe représentant le Panda
 * @author equipe N
 */
public class Panda implements Personnage {
    // Définition des attributs
    private final Position position;


    // Définition des constructeurs
    /**
     * Constructeur par défaut du panda qui commence sur l'étang
     */
    public Panda() {
        position = new Position();
    }

    public void move(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie la position du Panda
     * @return la position
     */
    @Override
    public Position position() {
        return position;
    }

    @Override
    public String toString() {
        return "Panda en " + position;
    }
}
