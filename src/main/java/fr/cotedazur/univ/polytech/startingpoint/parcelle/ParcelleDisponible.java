package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

import java.util.Objects;

/**
 * Classe permettant d'avoir une parcelle vide à la place d'un objet null
 * @author N
 */
public class ParcelleDisponible implements Parcelle {
    // Définition des attributs
    private final Position position;

    /**
     * Constructeur par defaut
     * @param position position finale de la parcelle
     */
    public ParcelleDisponible(Position position) {
        if (position == null) throw new NullPointerException("La position ne doit pas être null");
        this.position = position;
    }

    /**
     * Renvoie la position de la parcelle
     * @return la position
     */
    @Override
    public Position position() {
        return position;
    }

    @Override
    public String toString() {
        return "Parcelle disponible en " + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcelleDisponible that = (ParcelleDisponible) o;
        return Objects.equals(position(), that.position());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position());
    }
}
