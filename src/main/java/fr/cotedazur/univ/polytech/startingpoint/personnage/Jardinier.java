package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

/**
 * Représente le jardinier.
 * @author equipe N
 */
public class Jardinier implements Personnage {
    // Définition des attributs

    private Position position;


    // Définition des constructeurs

    /**
     * Construit le jardinier, commence sur l'étang
     */
    public Jardinier() {
        position = new Position();
    }


    // Accesseurs

    /**
     * Renvoie la position du jardinier
     * @return la position du jardinier
     */
    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition( Position nouvelPosition ) {
        position=nouvelPosition;
    }

    // Méthode toString

    @Override
    public String toString() {
        return "Jardinier en " + position;
    }
}
