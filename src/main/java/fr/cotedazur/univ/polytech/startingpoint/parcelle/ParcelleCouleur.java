package fr.cotedazur.univ.polytech.startingpoint.parcelle;

import fr.cotedazur.univ.polytech.startingpoint.Colorable;
import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;

import java.util.Objects;

/**
 * Record représentant une parcelle posée sur le plateau
 *
 * @author equipe N
 */
public final class ParcelleCouleur implements Parcelle, Colorable {
    private final Position position;
    private final Couleur couleur;
    private boolean irriguee;

    // Définition des constructeurs

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
        this.irriguee =estIrriguee;
    }

    // Definition des getter et setter

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
        return position.equals(that.position()) && couleur == that.couleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, couleur);
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public Couleur couleur() {
        return couleur;
    }

}
