package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Irrigation {
    List<Position> positions;

    public Irrigation(List<Position> positions) {
        this.positions = positions;
    }

    /**
     * Renvoie les positions de l'irrigation si il existe
     * @return les positions de l'irrigation si il existe
     */
    public Optional<List<Position>> getPositions() {
        if (!positions.isEmpty()) {
            return Optional.of(positions);
        }
        return Optional.empty();
    }

    /**
     * Ajout de position à l'irrigation si il n'y a pas de position
     * @param position1 Position n°1 de l'irrigation
     * @param position2 Position n°2 de l'irrigation
     */
    public void addPosition(Position position1, Position position2) {
        if (!positions.isEmpty()) {
            positions.add(position1);
            positions.add(position2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Irrigation that)) return false;
        return Objects.equals(positions, that.positions) || (positions.get(0).equals(that.positions.get(1))
                && positions.get(1).equals(that.positions.get(0)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }

    @Override
    public String toString() {
        String str = "";
        if(!positions.isEmpty()) {
            str = " entre les parcelles en " + positions.get(0) + " et en " + positions.get(1);
        }
        return "Irrigation" + str;
    }
}
