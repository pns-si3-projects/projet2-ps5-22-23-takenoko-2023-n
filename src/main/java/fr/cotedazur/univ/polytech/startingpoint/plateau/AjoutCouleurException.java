package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.jetbrains.annotations.NotNull;

/**
 * Exception si la couleur de la section de bambou n'est pas la même que le bambou
 * @author equipe N
 */
public class AjoutCouleurException extends Exception{
    public AjoutCouleurException(@NotNull Couleur couleurBambou, @NotNull Couleur couleurSectionBambouAAdd){
        super("La couleur du bambou (" + couleurBambou
                + ") n'est pas la même couleur que la section de bambou à ajouter ("
                + couleurSectionBambouAAdd + ")");
    }
}
