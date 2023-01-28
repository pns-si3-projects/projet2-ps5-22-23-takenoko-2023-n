package fr.cotedazur.univ.polytech.startingpoint.pieces;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Colorable;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Positionable;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Représente l'ensemble des sections de bambou pouvant être placées sur une parcelle de couleur.
 * @author equipe N
 */
public class Bambou implements Positionable, Colorable {
    // Définition des attributs

    public static final int TAILLE_MAXIMUM_BAMBOU = 4;
    private final Position position;
    private final Couleur couleur;
    private final SectionBambou[] sectionsBambou;
    private int tailleBambou;


    // Définition des constructeurs

    /**
     * Construit le bambou par la parcelle de référence
     * @param parcelleCouleur la parcelle de couleur qui est sous le bambou
     */
    public Bambou(@NotNull ParcelleCouleur parcelleCouleur) {
        position = parcelleCouleur.getPosition();
        couleur = parcelleCouleur.getCouleur();
        sectionsBambou = new SectionBambou[TAILLE_MAXIMUM_BAMBOU];
        tailleBambou = 0;
    }


    // Accesseurs

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Renvoie le nombre de sections de bambou
     * @return le nombre de sections de bambou
     */
    public int getTailleBambou() {
        return tailleBambou;
    }

    /**
     * Renvoie s'il n'y a plus de sections de bambou
     * @return {@code true} s'il n'y a plus de sections de bambou
     */
    public boolean isEmpty() {
        return tailleBambou == 0;
    }


    // Méthodes d'utilisation

    /**
     * Renvoie si le bambou a atteint sa taille maximum
     * @return {@code true} si le bambou a atteint sa taille maximum
     */
    public boolean isTailleMaximum() {
        return tailleBambou == TAILLE_MAXIMUM_BAMBOU;
    }

    /**
     * Renvoie une section de bambou
     * @return une section de bambou
     * @implNote le bambou doit contenir au moins une section de bambou
     */
    public SectionBambou prendSectionBambou() {
        assert !isEmpty() : "Le bambou ne contient pas de section de bambou";
        return sectionsBambou[--tailleBambou];
    }

    /**
     * Ajoute une section de bambou
     * @param sectionBambou la section de bambou à ajouter
     * @implNote le bambou ne doit pas être à sa taille maximum
     */
    public void ajouteSectionBambou(@NotNull SectionBambou sectionBambou) throws AjoutCouleurException {
        assert !isTailleMaximum() : "Le bambou a atteint sa taille maximum";
        if (sectionBambou.getCouleur() != getCouleur()) {
            throw new AjoutCouleurException(this, sectionBambou);
        }

        sectionsBambou[tailleBambou++] = sectionBambou;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return "Bambou de couleur " + couleur + " en " + position + " de " + tailleBambou + " sections de bambou";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bambou bambou = (Bambou) o;
        return getTailleBambou() == bambou.getTailleBambou()
                && getPosition().equals(bambou.getPosition())
                && getCouleur() == bambou.getCouleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getCouleur(), getTailleBambou());
    }
}
