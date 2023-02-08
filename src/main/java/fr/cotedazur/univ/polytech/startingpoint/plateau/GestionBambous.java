package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class GestionBambous {
    // Définition d'un constructeur privé pour éviter les instanciations

    private GestionBambous() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Cherche un bambou à une certaine position
     * @param bambouList la liste de bambou posés sur le plateau
     * @param position position à laquelle on cherche le bambou
     * @return un optional de bambou si trouvé, sinon un optional vide
     */
    public static Optional<Bambou> chercheBambou(@NotNull Bambou[] bambouList, Position position){
        for (Bambou bambou : bambouList){
            if (bambou.getPosition().equals(position)) return Optional.of(bambou);
        }
        return Optional.empty();
    }
}
