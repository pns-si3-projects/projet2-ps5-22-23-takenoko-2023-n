package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

/**
 * Classe représentant une parcelle posée sur le plateau
 * @author equipe N
 */
public class ParcelleCouleur implements Parcelle {
    // Définition des attributs
    private final Position position;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param position position finale de la parcelle
     */
    public ParcelleCouleur(Position position) {
        if (position == null) throw new NullPointerException("La position ne doit pas être null");
        this.position = position;
    }


    // Accesseurs et méthodes toString et equals
    /**
     * Renvoie la position de la parcelle
     * @return la position de la parcelle
     */
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Parcelle de couleur en " + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcelleCouleur that = (ParcelleCouleur) o;
        return Objects.equals(getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
