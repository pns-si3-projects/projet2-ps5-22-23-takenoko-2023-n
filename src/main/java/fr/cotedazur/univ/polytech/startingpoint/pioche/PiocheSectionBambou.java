package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.jetbrains.annotations.NotNull;

/**
 * Représente la pioche de sections de bambou.
 * @author équipe N
 */
public class PiocheSectionBambou {
    // Méthodes d'utilisation

    /**
     * Renvoie une section de bambou de la couleur désignée
     * @param couleur la couleur de la section de bambou
     * @return la section de bambou piochée
     */
    public SectionBambou pioche(@NotNull Couleur couleur) {
        return new SectionBambou(couleur);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche de sections de bambou";
    }
}
