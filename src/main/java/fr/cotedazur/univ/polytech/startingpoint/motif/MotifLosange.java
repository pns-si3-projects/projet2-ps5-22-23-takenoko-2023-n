package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

/**
 * Classe représentant le motif Losange
 * @author equipe N
 */
public class MotifLosange extends Motif{

    /**
     * Constructeur par defaut
     * @param parcelleCouleur00 Parcelle de couleur de coordonnees 0, 0 (par exemple, peut être 2, 0)
     * @param parcelleCouleur11 Parcelle de couleur de coordonnees 1, 1 (par exemple, peut être 3, 1)
     * @param parcelleCouleurm11 Parcelle de couleur de coordonnees -1, 1 (par exemple, peut être 1, 1)
     * @param parcelleCouleur02 Parcelle de couleur de coordonnees 0, 2 (par exemple, peut être 2, 2)
     */
    public MotifLosange(ParcelleCouleur parcelleCouleur00, ParcelleCouleur parcelleCouleur11, ParcelleCouleur parcelleCouleurm11, ParcelleCouleur parcelleCouleur02) {
        if (parcelleCouleur00 == null || parcelleCouleur11 == null || parcelleCouleurm11 == null || parcelleCouleur02 == null) {
            throw new IllegalArgumentException("Les arguments ne doivent pas être nuls");
        }
        tabParcelles = new ParcelleCouleur[4];
        tabParcelles[0] = parcelleCouleur00;
        tabParcelles[1] = parcelleCouleur11;
        tabParcelles[2] = parcelleCouleurm11;
        tabParcelles[3] = parcelleCouleur02;
    }

    @Override
    public ParcelleCouleur[][] getOrientation() {
        ParcelleCouleur[][] allOrientation = new ParcelleCouleur[6][4];
        allOrientation[0] = tabParcelles;
        for (int i = 1; i < 6; i++) {
            allOrientation[i][0] = allOrientation[0][0];
            allOrientation[i][1] = getVoisin(i, allOrientation[i][0].getPosition(), tabParcelles[1].getCouleur());
            allOrientation[i][2] = allOrientation[i - 1][1];
            allOrientation[i][3] = getVoisin(i - 1, allOrientation[i][1].getPosition(), tabParcelles[3].getCouleur());
        }
        return allOrientation;
    }

    @Override
    public ParcelleCouleur[][] getOrientationForIA() {
        return getOrientation();
    }
}
