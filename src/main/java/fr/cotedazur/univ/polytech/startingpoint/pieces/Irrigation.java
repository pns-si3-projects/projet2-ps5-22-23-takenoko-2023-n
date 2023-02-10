package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Représente une irrigation.
 * @author equipe N
 */
public class Irrigation {
    // Définition des attributs

    private final Set<Position> positions;


    // Définition des constructeurs

    /**
     * Construit une irrigation
     */
    public Irrigation() {
        positions = new HashSet<>(2);
    }


    // Accesseurs

    /**
     * Renvoie les positions de l'irrigation s'il existe
     * @return les positions de l'irrigation s'il existe
     */
    public List<Position> getPositions() {
        return positions.stream().toList();
    }


    /**
     * Ajout de positions à l'irrigation s'il n'y en a pas encore
     * @param position1 première position de l'irrigation
     * @param position2 deuxième position de l'irrigation
     */
    public boolean addPosition(Position position1, Position position2) {
        if (positions.isEmpty()) {
            Position positionEtang = new Position();
            if (position1.equals(positionEtang) || position2.equals(positionEtang)) {
                return false;
            }

            positions.add(position1);
            positions.add(position2);
            if (positions.size() == 2) {
                return true;
            }
            positions.clear();
            return false;
        }
        return false;
    }


    // Méthodes toString et equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Irrigation that)) return false;
        if (positions.isEmpty() && that.getPositions().isEmpty()) return true;
        return positions.containsAll(that.getPositions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }

    @Override
    public String toString() {
        String str = "";

        if (!positions.isEmpty()) {
            List<Position> positionList = getPositions();
            str = " entre les parcelles en " + positionList.get(0) + " et en " + positionList.get(1);
        }
        return "Irrigation" + str;
    }
}
