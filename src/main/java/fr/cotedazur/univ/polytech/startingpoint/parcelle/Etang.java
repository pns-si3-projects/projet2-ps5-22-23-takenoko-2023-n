package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.Position;

import java.util.Objects;

/**
 * Classe qui représente la piece centrale du jeu
 * @author equipe N
 */
public class Etang implements Parcelle {
    // Définition des attributs
    private final Position position;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     */
    public Etang() {
        position = new Position();
    }


    // Accesseurs et méthodes toString et equals
    @Override
    public Position position() {
        return position;
    }

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
