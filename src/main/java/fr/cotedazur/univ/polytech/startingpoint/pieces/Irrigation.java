package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Irrigation {
    List<Position> positions= new ArrayList<>(2);

    public Irrigation(List<Position> positions) {
        this.positions = positions;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Irrigation that)) return false;
        return Objects.equals(positions, that.positions) || (positions.get(0).equals(that.positions.get(1)) && positions.get(1).equals(that.positions.get(0)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }

    @Override
    public String toString() {
        return "Irrigation entre les parcelles en " + positions.get(0) + " et en " + positions.get(1);
    }
}
