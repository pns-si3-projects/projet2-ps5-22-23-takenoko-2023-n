package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Optional;
import java.util.Set;

public class GestionBambous {

    // Définition d'un constructeur privé pour éviter les instanciations
    private GestionBambous() {
        throw new IllegalStateException("Utility class");
    }

    // Méthodes d'utilisation

    public static Optional<Bambou> chercheBambou(@NotNull Bambou[] bambouList, Position position){
        for (Bambou bambou : bambouList){
            if (bambou.getPosition().equals(position)) return Optional.of(bambou);
        }
        return Optional.empty();
    }
}
