package fr.cotedazur.univ.polytech.startingpoint.pioche;

import java.util.Arrays;

/**
 * Enregistre les parcelles piochées dans le tour
 * Les cartes sont ajoutées lors de la pioche puis supprimer après le choix effectué
 */
public class ParcellesPiochees {
    // Définition des attributs

    private final ParcellePioche[] parcelles;
    private int nombreParcelles;


    // Définition des constructeurs

    /**
     * Construit l'ensemble qui enregistre les parcelles piochées dans le tour
     */
    public ParcellesPiochees() {
        parcelles = new ParcellePioche[3];
        nombreParcelles = 0;
    }


    // Accesseurs

    /**
     * Renvoie les 3 parcelles piochées
     * @return les 3 parcelles piochées
     */
    public ParcellePioche[] getParcellesPiochees() {
        return Arrays.copyOf(parcelles, 3);
    }

    /**
     * Renvoie si aucune parcelle n'a été piochée
     * @return {@code true} si aucune parcelle n'a été piochée
     */
    public boolean isEmpty() {
        return nombreParcelles == 0;
    }


    // Méthodes d'utilisation

    /**
     * Enregistre les parcelles piochées
     * @param parcelle1 la première parcelle piochée à ajouter
     * @param parcelle2 la deuxième parcelle piochée à ajouter
     * @param parcelle3 la troisième parcelle piochée à ajouter
     * @throws PiocheParcelleEnCoursException si une pioche de parcelles est déjà en cours
     */
    public void enregistre3ParcellesPiochees(ParcellePioche parcelle1,
                                                ParcellePioche parcelle2,
                                                ParcellePioche parcelle3)
            throws PiocheParcelleEnCoursException {

        if (!isEmpty()) {
            throw new PiocheParcelleEnCoursException();
        }
        parcelles[nombreParcelles++] = parcelle1;
        parcelles[nombreParcelles++] = parcelle2;
        parcelles[nombreParcelles++] = parcelle3;
    }

    /**
     * Enregistre 3 fois la parcelle piochée
     * @param parcelle la parcelle à ajouter 3 fois
     * @throws PiocheParcelleEnCoursException si une pioche de parcelles est déjà en cours
     */
    public void enregistreParcellePiochee(ParcellePioche parcelle) throws PiocheParcelleEnCoursException {
        enregistre3ParcellesPiochees(parcelle, parcelle, parcelle);
    }

    /**
     * Renvoie si la parcelle donnée fait partie des cartes piochées dans le tour
     * @param parcellePioche la parcelle demandée à vérifier
     * @return si la parcelle donnée fait partie des cartes piochées dans le tour
     */
    public boolean parcellePiochee(ParcellePioche parcellePioche) {
        for (ParcellePioche parcelle : parcelles) {
            if (parcellePioche.equals(parcelle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Oublie les parcelles piochées
     */
    public void oublieParcellesPiochees() {
        nombreParcelles = 0;
    }
}
