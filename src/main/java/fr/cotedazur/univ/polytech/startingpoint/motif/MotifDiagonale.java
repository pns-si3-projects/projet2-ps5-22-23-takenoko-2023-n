package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

/**
 * Classe représentant le motif Diagonale
 * @author equipe N
 */
public class MotifDiagonale extends Motif{

    /**
     * Constructeur par défaut
     * @param parcellesCouleurs liste de Parcelles pour le motifs
     */
    public MotifDiagonale(ParcelleCouleur... parcellesCouleurs) {
        if (parcellesCouleurs.length < 2 || parcellesCouleurs.length > 3) throw new IllegalArgumentException("Trop d'arguments en paramètres");
        tabParcelles = parcellesCouleurs;
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
