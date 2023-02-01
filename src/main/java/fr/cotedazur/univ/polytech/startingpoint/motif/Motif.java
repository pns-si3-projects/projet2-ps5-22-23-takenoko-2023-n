package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

import java.util.Objects;


/**
 * Classe représentant le motif d'un objectif Parcelle
 * @author equipe N
 */
public abstract class Motif {
    protected ParcelleCouleur[] tabParcelles;

    /**
     * Renvoie toutes les orientations d'un motif
     * @return toutes les orientations d'un motif
     */
    public abstract ParcelleCouleur[][] getOrientation();

    /**
     * Renvoie toutes les orientations d'un motif pour L'IA
     * @return toutes les orientations d'un motif pour L'IA
     */
    public abstract ParcelleCouleur[][] getOrientationForIA();


    /**
     * Verifie si la parcelle se trouve a la même position que celle dans l'autre motifs mais avec une distance de <code> differencePosition </code>
     * @param positionParcelleMotif      La position de la parcelle où se situe dans le motif-ci
     * @param positionParcelleOtherMotif La position de la parcelle où se situe dans l'autre motif
     * @param differencePositionX        la distance en X calculé auparavant entre les 2 motifs
     * @param differencePositionY        la distance en Y calculé auparavant entre les 2 motifs
     * @return <code> true </code> si les parcelles sont à la même position mais pas au même endroit
     */
    private boolean comparePosition(Position positionParcelleMotif, Position positionParcelleOtherMotif, int differencePositionX, int differencePositionY) {
        int xM = positionParcelleMotif.getX();
        int yM = positionParcelleMotif.getY();
        int xOM = positionParcelleOtherMotif.getX();
        int yOM = positionParcelleOtherMotif.getY();

        return Math.abs(xM - xOM) == differencePositionX && Math.abs(yM - yOM) == differencePositionY;
    }


    /**
     * Compare les 2 motifs pour le equals
     * @param otherMotif Le motif à comparer
     * @return <code> true </code> si le motif est le même
     */
    public boolean compareMotif(Motif otherMotif) {
        if (otherMotif.tabParcelles.length != tabParcelles.length) {
            return false;
        }

        ParcelleCouleur[][] orientationMotifActuel = getOrientation();
        ParcelleCouleur[] motifOther = otherMotif.tabParcelles;
        for (int i = 0; i < orientationMotifActuel.length; i++) {
            int differencePositionX = Math.abs(orientationMotifActuel[i][0].position().getX() - motifOther[0].position().getX());
            int differencePositionY = Math.abs(orientationMotifActuel[i][0].position().getY() - motifOther[0].position().getY());
            boolean memeMotif = true;

            for (int j = 1; j < motifOther.length; j++) {
                Position positionMotifActuel = orientationMotifActuel[i][j].position();
                Position positionMotifOther = motifOther[j].position();
                if (!comparePosition(positionMotifActuel, positionMotifOther, differencePositionX, differencePositionY)) {
                    memeMotif = false;
                }
            }

            if (memeMotif){
                return true;
            }
        }
        return false;
    }

    /**
     * Renvoie une copie du tableau de Parcelle du motif
     * @return une copie du tableau de Parcelle du motif
     */
    public ParcelleCouleur[] getTableauParcelles() {
        ParcelleCouleur[] copyParcelleCouleur = new ParcelleCouleur[tabParcelles.length];
        System.arraycopy(tabParcelles, 0, copyParcelleCouleur, 0, tabParcelles.length);
        return tabParcelles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || o.getClass() != this.getClass()) {
            return false;
        } else {
            Motif motifOther = (Motif) o;
            return compareMotif(motifOther);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object) tabParcelles);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Motif : ");
        for (ParcelleCouleur parcelleCouleur : tabParcelles) {
            stringBuilder.append(parcelleCouleur + " ");
        }
        return stringBuilder.toString();
    }
}
