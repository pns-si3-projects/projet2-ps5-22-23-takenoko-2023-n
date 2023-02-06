package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

/**
 * Classe représentant le motif Decalé
 * @author equipe N
 */
public class MotifDecale extends Motif{

    /**
     * Constructeur par defaut
     * @param parcelleCouleur00 Parcelle de couleur de coordonnees 0, 0 (par exemple, peut être 2, 0)
     * @param parcelleCouleur11 Parcelle de couleur de coordonnees 1, 1 (par exemple, peut être 3, 1)
     * @param parcelleCouleur02 Parcelle de couleur de coordonnees 0, 2 (par exemple, peut être 2, 2)
     */
    public MotifDecale(ParcelleCouleur parcelleCouleur00, ParcelleCouleur parcelleCouleur11, ParcelleCouleur parcelleCouleur02) {
        if (parcelleCouleur00 == null || parcelleCouleur11 == null || parcelleCouleur02 == null) {
            throw new IllegalArgumentException("Les arguments ne doivent pas être null");
        }
        tabParcelles = new ParcelleCouleur[3];
        tabParcelles[0] = parcelleCouleur00;
        tabParcelles[1] = parcelleCouleur11;
        tabParcelles[2] = parcelleCouleur02;
    }

    @Override
    public ParcelleCouleur[][] getOrientation() {
        ParcelleCouleur[][] allOrientation = new ParcelleCouleur[12][3];
        allOrientation[0] = tabParcelles;

        for (int i = 1; i < 6; i++) {
            allOrientation[i][0] = tabParcelles[0];
            allOrientation[i][1] = getVoisin(i, tabParcelles[0].position(), tabParcelles[1].couleur());
            allOrientation[i][2] = getVoisin(i - 1, allOrientation[i][1].position(), tabParcelles[2].couleur());
        }

        allOrientation[6][0] = tabParcelles[0];
        allOrientation[6][1] = getVoisin(4, allOrientation[0][1].position(), tabParcelles[1].couleur());
        allOrientation[6][2] = tabParcelles[2];

        for (int i = 7; i < 12; i++) {
            allOrientation[i][0] = tabParcelles[0];
            allOrientation[i][1] = allOrientation[i-7][1];
            allOrientation[i][2] = allOrientation[i-6][2];
        }

        return allOrientation;
    }

    @Override
    public ParcelleCouleur[][] getOrientationForIA() {
        ParcelleCouleur[][] allOrientation = new ParcelleCouleur[12][3];
        allOrientation[0][0] = tabParcelles[1];
        allOrientation[0][1] = tabParcelles[0];
        allOrientation[0][2] = tabParcelles[2];

        for (int i = 1; i < 6; i++) {
            allOrientation[i][0] = getVoisin(i, tabParcelles[0].position(), tabParcelles[1].couleur());
            allOrientation[i][1] = tabParcelles[0];
            allOrientation[i][2] = getVoisin(i - 1, allOrientation[i][0].position(), tabParcelles[2].couleur());
        }

        allOrientation[6][0] = allOrientation[5][0];
        allOrientation[6][1] = tabParcelles[0];
        allOrientation[6][2] = tabParcelles[2];

        for (int i = 7; i < 12; i++) {
            allOrientation[i][0] = allOrientation[i - 7][0];
            allOrientation[i][1] = tabParcelles[0];
            allOrientation[i][2] = allOrientation[i - 6][2];
        }

        return allOrientation;
    }
}
