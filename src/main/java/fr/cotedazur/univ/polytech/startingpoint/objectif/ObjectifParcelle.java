package fr.cotedazur.univ.polytech.startingpoint.objectif;

import java.util.Objects;

/**
 * Représente les objectifs des parcelles avec un nombre de parcelles à placer et irriguer si besoin
 * @author équipe N
 */
public class ObjectifParcelle extends Objectif {
    // Définition des attributs
    private Motif motifParcelle;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param nbPoints est le nombre de points de l'objectif
     * @param motifParcelle est le motif a effectuer
     * @implSpec <code>nbPoints > 0</code>, <code>nbParcelles > 0</code>
     */
    public ObjectifParcelle(int nbPoints, Motif motifParcelle) {
        nombrePoints = nbPoints;
        this.motifParcelle = motifParcelle;
    }


    // Accesseurs et méthodes toString et equals
    /**
     * Renvoie le nombre de parcelles irriguées qui doivent se retrouver sur le plateau
     * @return le nombre de parcelles irriguées qui doivent être sur le plateau
     */
    public Motif getMotif() {
        return motifParcelle;
    }


    @Override
    public String toString() {
        String s = "Objectif de faire le ";
        s += motifParcelle + " et vaut ";
        s += nombrePoints + " points";
        return s;
    }

    /**
     * Compare 2 ObjectifParcelle par le nombre de points et le nombre de parcelles
     * @param o est l'objet à comparer avec celui actuel
     * @return <code>true</code> si l'ObjectifParcelle donné a le même nombre de points et de parcelles, <code>false</code> sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ObjectifParcelle that = (ObjectifParcelle) o;
        return motifParcelle.equals(that.motifParcelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), motifParcelle);
    }
}
