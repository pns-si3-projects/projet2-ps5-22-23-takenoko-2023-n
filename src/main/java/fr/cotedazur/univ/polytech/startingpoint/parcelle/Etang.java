package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

import java.util.Objects;

/**
 * Représente l'étang, la pièce au centre du jeu
 * @author equipe N
 */
public class Etang implements Parcelle {
    // Définition des attributs

    private final Position position;


    // Définition des constructeurs

    /**
     * Construit l'étang avec une position par défaut en (0,0)
     */
    public Etang() {
        position = new Position();
    }


    // Accesseurs
    @Override
    public Position position() {
        return position;
    }


    // Méthodes toString et equals
    @Override
    public String toString() {
        return "Etang en " + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etang etang = (Etang) o;
        return position().equals(etang.position());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position());
    }
}
