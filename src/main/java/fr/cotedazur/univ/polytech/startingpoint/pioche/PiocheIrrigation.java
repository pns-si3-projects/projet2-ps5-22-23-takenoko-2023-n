package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;

import java.util.ArrayList;
import java.util.List;

public class PiocheIrrigation {
    // Définition des attributs

    private int nombreIrrigation;


    // Définition des constructeurs

    /**
     * Construit une pioche de 20 irrigations
     */
    public PiocheIrrigation() {
        nombreIrrigation = 20;
    }


    // Accesseurs

    /**
     * Renvoie le nombre d'irrigations que contient la pioche
     * @return le nombre d'irrigations restantes dans la pioche
     */
    public int getNombreIrrigationsRestantes() {
        return nombreIrrigation;
    }

    /**
     * Renvoie si la pioche d'irrigation est vide
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmpty() {
        return nombreIrrigation == 0;
    }


    // Méthodes d'utilisation

    /**
     * Renvoie une irrigation de la pioche
     * @param position1 La position n°1 de l'irrigation
     * @param position2 La position n°2 de l'irrigation
     * @implNote la pioche ne doit pas être vide
     */
    public Irrigation pioche(Position position1, Position position2) {
        if (isEmpty()) {
            throw new AssertionError("La pioche d'objectifs d'irrigation est vide");
        }
        nombreIrrigation--;
        List<Position> listPosition = new ArrayList<>(2);
        listPosition.add(position1);
        listPosition.add(position2);
        return new Irrigation(listPosition);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'irrigation : " + nombreIrrigation + " irrigations";
    }
}
