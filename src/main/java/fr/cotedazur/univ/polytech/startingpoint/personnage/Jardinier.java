package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.Position;

/**
 * Classe représentant le Jardinier
 * @author equipe N
 */
public class Jardinier implements Personnage {
    // Définition des attributs
    private final Position position;


    // Définition des constructeurs
    /**
     * Constructeur par défaut du Jardinier qui commence sur l'étang
     */
    public Jardinier() {
        position = new Position();
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie la position du Jardinier
     * @return la position
     */
    @Override
    public Position position() {
        return position;
    }

    @Override
    public String toString() {
        return "Jardinier en " + position;
    }
}
