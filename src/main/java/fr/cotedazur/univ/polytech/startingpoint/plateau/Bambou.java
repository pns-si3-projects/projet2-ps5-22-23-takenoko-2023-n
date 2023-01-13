package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.Positionable;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

import java.util.Objects;

/**
 * Classe représentant l'ensemble des sections de bambou pouvant être placées sur une parcelle de couleur
 * @author equipe N
 */
public class Bambou implements Positionable {
    // Définition des attributs
    private final Position position;
    private final SectionBambou[] sectionsBambou;
    private int tailleBambou;
    private final Couleur couleur;
    public static final int TAILLE_MAXIMUM_BAMBOU = 4;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     */
    public Bambou(ParcelleCouleur parcelleCouleur) {
        if (parcelleCouleur == null)  throw new IllegalArgumentException("La parcelle de couleur ne doit pas être null");
        sectionsBambou = new SectionBambou[TAILLE_MAXIMUM_BAMBOU];
        tailleBambou = 0;
        this.position = parcelleCouleur.position();
        this.couleur = parcelleCouleur.couleur();
    }


    // Accesseur et méthode toString et equals
    /**
     * Renvoie la position du Bambou
     * @return la position du Bambou
     */
    @Override
    public Position position() {
        return position;
    }

    /**
     * Renvoie le nombre de sections de bambou
     * @return le nombre de sections de bambou
     */
    public int getTailleBambou() {
        return tailleBambou;
    }

    /**
     * Renvoie la couleur du Bambou
     * @return la couleur du Bambou
     */
    public Couleur couleur() {
        return couleur;
    }

    /**
     * Renvoie la taille maximum du Bambou
     * @return la taille maximum du Bambou
     */
    public int getTailleMaximumBambou() {
        return TAILLE_MAXIMUM_BAMBOU;
    }

    /**
     * Renvoie s'il n'y a plus de sections de bambou
     * @return <code>true</code> si le Bambou n'a plus de section, <code>false</code> sinon
     */
    public boolean isEmptyBambou() {
        return tailleBambou == 0;
    }

    /**
     * Renvoie si le Bambou a atteint sa taille maximum
     * @return <code>true</code> si le Bambou a atteint sa taille maximum, <code>false</code> sinon
     */
    public boolean isTailleMaximum() {
        return tailleBambou == TAILLE_MAXIMUM_BAMBOU;
    }

    @Override
    public String toString() {
        return "Bambou " + couleur + " en " + position + " de " + tailleBambou + " sections de bambou";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bambou bambou = (Bambou) o;
        return getTailleBambou() == bambou.getTailleBambou() && position().equals(bambou.position()) && couleur() == bambou.couleur();
    }

    @Override
    public int hashCode() {
        return Objects.hash(position(), getTailleBambou(), couleur());
    }

    // Méthodes d'utilisation
    /**
     * Renvoie une section de bambou
     * @return la section de bambou
     * @implNote le Bambou doit contenir au moins une section de bambou
     */
    public SectionBambou prendSectionBambou() {
        assert !isEmptyBambou() : "Le Bambou ne contient pas de section de bambou";
        return sectionsBambou[--tailleBambou];
    }

    /**
     * Ajoute une section de bambou
     * @implNote le Bambou ne doit pas être à sa taille maximum
     */
    public void ajouteSectionBambou(SectionBambou sectionBambou) throws AjoutCouleurException{
        assert !isTailleMaximum() : "Le Bambou a atteint sa taille maximum";
        if (sectionBambou.couleur() != couleur()) throw new AjoutCouleurException(couleur(), sectionBambou.couleur());
        sectionsBambou[tailleBambou++] = sectionBambou;
    }
}
