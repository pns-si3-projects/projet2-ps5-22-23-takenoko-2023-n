package fr.cotedazur.univ.polytech.startingpoint.personnage;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.jetbrains.annotations.NotNull;

/**
 * Représente le panda.
 * @author equipe N
 */
public class Panda implements Personnage {
    // Définition des attributs

    private Position position;


    // Définition des constructeurs

    /**
     * Construit le panda, commence sur l'étang
     */
    public Panda() {
        position = new Position();
    }


    // Accesseurs

    /**
     * Renvoie la position du panda
     * @return la position du panda
     */
    @Override
    public Position getPosition() {
        return position;
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Panda";
    }

    @Override
    public void move(@NotNull Position position) {
        this.position = position;
    }
}
