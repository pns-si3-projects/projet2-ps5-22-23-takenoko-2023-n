package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Colorable;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;

import java.util.Objects;

/**
 * Représente une parcelle posée sur le plateau.
 * @author equipe N
 */
public final class ParcelleCouleur implements Parcelle, Colorable {
    private final Position position;
    private final Couleur couleur;
    private boolean irriguee;

    /**
     * Constructeur par défaut
     *
     * @param position position finale de la parcelle
     * @param couleur  est la couleur de la parcelle
     */
    public ParcelleCouleur(Position position, Couleur couleur) {
        this(position,couleur,false);
    }

    /**
     * Constructeur par défaut
     *
     * @param position position finale de la parcelle
     * @param couleur  est la couleur de la parcelle
     * @param estIrriguee est le statut de la parcelle
     */
    public ParcelleCouleur(Position position, Couleur couleur,boolean estIrriguee) {
        if (position == null) throw new IllegalArgumentException("La position ne doit pas être null");
        if (couleur == null) throw new IllegalArgumentException("La couleur ne doit pas être null");
        this.position = position;
        this.couleur = couleur;
        this.irriguee = estIrriguee;
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
     * Getter de irriguee
     * @return retourne si la parcelle est irriguee
     */
    public boolean isIrriguee() {
        return irriguee;
    }

    /**
     * Setter de irriguee
     * @param irriguee le statut de la parcelle
     */
    public void setIrriguee(boolean irriguee) {
        this.irriguee = irriguee;
    }


    // Méthodes toString et equals

    @Override
    public String toString() {
        return "Parcelle de couleur " + couleur + " en " + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcelleCouleur that = (ParcelleCouleur) o;
        return getPosition().equals(that.getPosition()) && getCouleur() == that.getCouleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getCouleur());
    }
}
