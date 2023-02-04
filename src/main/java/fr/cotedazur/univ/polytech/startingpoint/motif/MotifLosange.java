package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

public class MotifLosange extends Motif{

    /**
     * Constructeur par defaut
     * @param parcelleCouleur00 Parcelle de couleur de coordonnees 0, 0 (par exemple, peut être 2, 0)
     * @param parcelleCouleur11 Parcelle de couleur de coordonnees 1, 1 (par exemple, peut être 3, 1)
     * @param parcelleCouleur1m1 Parcelle de couleur de coordonnees -1, 1 (par exemple, peut être 1, 1)
     * @param parcelleCouleur20 Parcelle de couleur de coordonnees 0, 2 (par exemple, peut être 2, 2)
     */
    public MotifLosange(ParcelleCouleur parcelleCouleur00, ParcelleCouleur parcelleCouleur11, ParcelleCouleur parcelleCouleur1m1, ParcelleCouleur parcelleCouleur20) {
        if (parcelleCouleur00 == null || parcelleCouleur11 == null || parcelleCouleur1m1 == null || parcelleCouleur20 == null) {
            throw new IllegalArgumentException("Les arguments ne doivent pas être nuls");
        }
        tabParcelles = new ParcelleCouleur[4];
        tabParcelles[0] = parcelleCouleur00;
        tabParcelles[1] = parcelleCouleur11;
        tabParcelles[2] = parcelleCouleur1m1;
        tabParcelles[3] = parcelleCouleur20;
    }

    @Override
    public ParcelleCouleur[][] getOrientation() {
        ParcelleCouleur[][] allOrientation = new ParcelleCouleur[6][4];
        allOrientation[0] = tabParcelles;
        for (int i = 1; i < 6; i++) {
            allOrientation[i][0] = allOrientation[0][0];
            allOrientation[i][1] = getVoisin(i, allOrientation[i][0].position(), tabParcelles[1].couleur());
            allOrientation[i][2] = getVoisin(4, allOrientation[i][1].position(), tabParcelles[2].couleur());
            allOrientation[i][3] = getVoisin(5, allOrientation[i][1].position(), tabParcelles[3].couleur());
        }
        return allOrientation;
    }

    @Override
    public ParcelleCouleur[][] getOrientationForIA() {
        ParcelleCouleur[][] allOrientation = new ParcelleCouleur[12][4];
        System.arraycopy(getOrientation(), 0, allOrientation, 0, 6);
        for (int i = 6; i < 12; i++) {
            allOrientation[i][0] = allOrientation[0][0];
            allOrientation[i][1] = allOrientation[i-6][2];
            allOrientation[i][2] = allOrientation[i-6][1];
            allOrientation[i][3] = allOrientation[i-6][3];
        }
        return allOrientation;
    }
}
