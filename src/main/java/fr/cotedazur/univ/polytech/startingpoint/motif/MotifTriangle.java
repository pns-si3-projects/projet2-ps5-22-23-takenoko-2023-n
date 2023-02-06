package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

/**
 * Classe représentant le motif Triangle
 * @author equipe N
 */
public class MotifTriangle extends Motif{

    /**
     * Constructeur par defaut
     * @param parcelleCouleur00 Parcelle de couleur de coordonnees 0, 0 (par exemple, peut être 2, 0)
     * @param parcelleCouleur11 Parcelle de couleur de coordonnees 1, 1 (par exemple, peut être 3, 1)
     * @param parcelleCouleur20 Parcelle de couleur de coordonnees 2, 0 (par exemple, peut être 4, 0)
     */
    public MotifTriangle(ParcelleCouleur parcelleCouleur00, ParcelleCouleur parcelleCouleur20, ParcelleCouleur parcelleCouleur11) {
        if (parcelleCouleur00 == null || parcelleCouleur20 == null || parcelleCouleur11 == null) {
            throw new IllegalArgumentException("Les arguments ne doivent pas être nuls");
        }
        tabParcelles = new ParcelleCouleur[3];
        tabParcelles[0] = parcelleCouleur00;
        tabParcelles[1] = parcelleCouleur20;
        tabParcelles[2] = parcelleCouleur11;
    }

    @Override
    public ParcelleCouleur[][] getOrientation() {
        ParcelleCouleur[][] allOrientation = new ParcelleCouleur[6][3];
        allOrientation[0] = tabParcelles;

        for (int i = 1; i < 5; i++) {
            allOrientation[i][0] = tabParcelles[0];
            allOrientation[i][1] = getVoisin(i + 1, tabParcelles[0].getPosition(), tabParcelles[1].getCouleur());
            allOrientation[i][2] = allOrientation[i - 1][1];
        }

        allOrientation[5][0] = tabParcelles[0];
        allOrientation[5][1] = tabParcelles[2];
        allOrientation[5][2] = allOrientation[4][1];

        return allOrientation;
    }

    @Override
    public ParcelleCouleur[][] getOrientationForIA() {
        ParcelleCouleur[][] allOrientation = new ParcelleCouleur[12][3];
        System.arraycopy(getOrientation(), 0, allOrientation, 0, 6);
        allOrientation[6][0] = tabParcelles[0];
        allOrientation[6][1] = tabParcelles[2];
        allOrientation[6][2] = tabParcelles[1];

        for (int i = 7; i < 12; i++) {
            allOrientation[i][0] = tabParcelles[0];
            allOrientation[i][1] = allOrientation[i - 6][2];
            allOrientation[i][2] = allOrientation[i - 6][1];
        }

        return allOrientation;
    }
}
