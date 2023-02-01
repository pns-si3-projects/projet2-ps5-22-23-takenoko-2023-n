package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

public class MotifDiagonale extends Motif{

    /**
     * Constructeur par défaut
     * @param parcellesCouleurs liste de Parcelles pour le motifs
     */
    public MotifDiagonale(ParcelleCouleur... parcellesCouleurs) {
        if (parcellesCouleurs.length < 2 || parcellesCouleurs.length > 3) throw new IllegalArgumentException("Trop d'arguments en paramètres");
        tabParcelles = parcellesCouleurs;
    }

    /**
     * Renvoie une nouvelle parcelle de Couleur en fonction de l'indice du voisin, de la position de la Parcelle et la couleur de la parcelle suivante
     * @param indiceVoisin Indice du voisin compris entre 1 et 6
     * @param positionParcelleCible La position de la parcelle d'origin
     * @param couleurParcelleVoisine La couleur de la Parcelle Voisine
     * @return une nouvelle parcelle de Couleur
     */
    private ParcelleCouleur getVoisin(int indiceVoisin, Position positionParcelleCible, Couleur couleurParcelleVoisine) {
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

    @Override
    public ParcelleCouleur[][] getOrientationForIA() {
        ParcelleCouleur[][] orientationBaseMotif = getOrientation();
        if (tabParcelles.length == 2) {
            return orientationBaseMotif;
        }

        ParcelleCouleur[][] orientationMotif = new ParcelleCouleur[9][tabParcelles.length];
        System.arraycopy(orientationBaseMotif, 0, orientationMotif, 0, 6);
        for (int i = 6, j = 0; j < 3; i++, j++) {
            orientationMotif[i][0] = tabParcelles[1];
            orientationMotif[i][1] = getVoisin(j, tabParcelles[1].position(), tabParcelles[2].couleur());
            orientationMotif[i][2] = getVoisin(j + 3, tabParcelles[1].position(), tabParcelles[0].couleur());
        }

        return orientationMotif;
    }


    @Override
    public ParcelleCouleur[][] getOrientation() {
        ParcelleCouleur[][] orientationsMotif = new ParcelleCouleur[6][tabParcelles.length];

        for (int i = 0; i < 6; i++) {
            orientationsMotif[i][0] = tabParcelles[0];
            orientationsMotif[i][1] = getVoisin(i, tabParcelles[0].position(), tabParcelles[1].couleur());
        }

        if (tabParcelles.length == 3) {
            for (int i = 0; i < 6; i++) {
                orientationsMotif[i][2] = getVoisin(i , orientationsMotif[i][1].position(), tabParcelles[2].couleur());
            }
        }

        return orientationsMotif;
    }
}
