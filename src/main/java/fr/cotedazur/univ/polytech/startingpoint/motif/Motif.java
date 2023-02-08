package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

import java.util.Arrays;
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
     * Verifie si la parcelle se trouve a la même position que celle dans l'autre motif,
     * mais avec une distance de {@code differencePosition}
     * @param positionParcelleMotif      La position de la parcelle où se situe dans le motif-ci
     * @param positionParcelleOtherMotif La position de la parcelle où se situe dans l'autre motif
     * @param differencePositionX        la distance en X calculé auparavant entre les 2 motifs
     * @param differencePositionY        la distance en Y calculé auparavant entre les 2 motifs
     * @return <code> true </code> si les parcelles sont à la même position mais pas au même endroit
     */
    private boolean comparePosition(Position positionParcelleMotif, Position positionParcelleOtherMotif,
                                    int differencePositionX, int differencePositionY) {
        int xM = positionParcelleMotif.getX();
        int yM = positionParcelleMotif.getY();
        int xOM = positionParcelleOtherMotif.getX();
        int yOM = positionParcelleOtherMotif.getY();

        return Math.abs(xM - xOM) == differencePositionX && Math.abs(yM - yOM) == differencePositionY;
    }

    /**
     * Renvoie une nouvelle parcelle de Couleur en fonction de l'indice du voisin,
     * de la position de la Parcelle et la couleur de la parcelle suivante
     * @param indiceVoisin Indice du voisin compris entre 1 et 6
     * @param positionParcelleCible La position de la parcelle d'origin
     * @param couleurParcelleVoisine La couleur de la Parcelle Voisine
     * @return une nouvelle parcelle de Couleur
     */
    protected ParcelleCouleur getVoisin(int indiceVoisin, Position positionParcelleCible,
                                        Couleur couleurParcelleVoisine) {
        int xPC = positionParcelleCible.getX();
        int yPC = positionParcelleCible.getY();

        return switch (indiceVoisin) {
            case 0 -> new ParcelleCouleur( new Position(xPC + 1, yPC + 1), couleurParcelleVoisine);
            case 1 -> new ParcelleCouleur( new Position(xPC + 2, yPC), couleurParcelleVoisine);
            case 2 -> new ParcelleCouleur( new Position(xPC + 1, yPC - 1), couleurParcelleVoisine);
            case 3 -> new ParcelleCouleur( new Position(xPC - 1, yPC - 1), couleurParcelleVoisine);
            case 4 -> new ParcelleCouleur( new Position(xPC - 2, yPC), couleurParcelleVoisine);
            case 5 -> new ParcelleCouleur( new Position(xPC - 1, yPC + 1), couleurParcelleVoisine);
            default -> {
                assert false : "L'indice du voisin doit être entre 0 et 6";
                yield null;
            }
        };
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
        for (ParcelleCouleur[] parcelleCouleurs : orientationMotifActuel) {
            int differencePositionX =
                    Math.abs(parcelleCouleurs[0].getPosition().getX() - motifOther[0].getPosition().getX());
            int differencePositionY =
                    Math.abs(parcelleCouleurs[0].getPosition().getY() - motifOther[0].getPosition().getY());
            boolean memeMotif = true;

            for (int j = 1; j < motifOther.length; j++) {
                Position positionMotifActuel = parcelleCouleurs[j].getPosition();
                Position positionMotifOther = motifOther[j].getPosition();
                if (!comparePosition(positionMotifActuel, positionMotifOther,
                        differencePositionX, differencePositionY)) {
                    memeMotif = false;
                }
            }

            if (memeMotif) {
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
        return Arrays.copyOf(tabParcelles, tabParcelles.length);
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
        return "Motif de " + tabParcelles.length + " parcelles de couleur";
    }
}
