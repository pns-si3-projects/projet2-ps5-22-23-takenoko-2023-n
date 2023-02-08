package fr.cotedazur.univ.polytech.startingpoint.pieces;

import org.jetbrains.annotations.NotNull;

/**
 * Exception si la couleur de la section de bambou n'est pas la même que celle du bambou
 * @author equipe N
 */
public class AjoutCouleurException extends Exception {
    /**
     * Construit le message de l'exception
     * @param bambou le bambou désigné pour recevoir la section de bambou
     * @param sectionBambou la section de bambou à ajouter au bambou
     */
    public AjoutCouleurException(@NotNull Bambou bambou, @NotNull SectionBambou sectionBambou){
        super("La couleur du bambou (" + bambou.getCouleur()
                + ") n'est pas la meme que celle de la section de bambou a ajouter ("
                + sectionBambou.getCouleur() + ")");
    }
}
