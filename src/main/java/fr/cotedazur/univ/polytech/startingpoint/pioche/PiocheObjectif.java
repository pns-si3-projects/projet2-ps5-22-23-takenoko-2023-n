package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;

import java.util.Random;

/**
 * Représente l'ensemble des pioches d'objectif (objectifs de parcelles, panda et jardinier)
 * @author équipe N
 */
public class PiocheObjectif {
    // Définition des attributs

    private final PiocheObjectifInterface[] piochesObjectif;


    // Définition des constructeurs

    /**
     * Construit la pioche des objectifs
     */
    public PiocheObjectif(PiocheObjectifInterface pOPar, PiocheObjectifInterface pOPan, PiocheObjectifInterface pOJar) {
        if (pOPar == null) {
            pOPar = new PiocheObjectifParcelle(new Random());
        }
        if (pOPan == null) {
            pOPan = new PiocheObjectifPanda(new Random());
        }
        if (pOJar == null) {
            pOJar = new PiocheObjectifJardinier(new Random());
        }
        piochesObjectif = new PiocheObjectifInterface[]{pOPar, pOPan, pOJar};
    }


    // Accesseurs

    /**
     * Renvoie le nombre de cartes que contient la pioche d'objectifs de parcelles
     * @return le nombre de cartes restantes dans la pioche
     */
    public int getNombreObjectifsParcelleRestants() {
        return piochesObjectif[0].getNombreObjectifsRestants();
    }

    /**
     * Renvoie le nombre de cartes que contient la pioche d'objectifs de panda
     * @return le nombre de cartes restantes dans la pioche
     */
    public int getNombreObjectifsPandaRestants() {
        return piochesObjectif[1].getNombreObjectifsRestants();
    }

    /**
     * Renvoie le nombre de cartes que contient la pioche d'objectifs de jardinier
     * @return le nombre de cartes restantes dans la pioche
     */
    public int getNombreObjectifsJardinierRestants() {
        return piochesObjectif[2].getNombreObjectifsRestants();
    }

    /**
     * Renvoie si la pioche d'objectifs de parcelles ne contient plus de cartes
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmptyPiocheObjectifParcelle() {
        return piochesObjectif[0].isEmpty();
    }

    /**
     * Renvoie si la pioche d'objectifs de panda ne contient plus de cartes
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmptyPiocheObjectifPanda() {
        return piochesObjectif[1].isEmpty();
    }

    /**
     * Renvoie si la pioche d'objectifs de jardinier ne contient plus de cartes
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmptyPiocheObjectifJardinier() {
        return piochesObjectif[2].isEmpty();
    }


    // Méthodes d'utilisation

    /**
     * Renvoie une carte objectif désignée aléatoirement dans la pioche d'objectifs de parcelles
     * @return la carte objectif piochée
     * @implNote la pioche ne doit pas être vide
     */
    public ObjectifParcelle piocheObjectifParcelle() {
        assert !isEmptyPiocheObjectifParcelle() : "La pioche d'objectifs de parcelles est vide";
        return (ObjectifParcelle) piochesObjectif[0].pioche();
    }

    /**
     * Renvoie une carte objectif désignée aléatoirement dans la pioche d'objectifs de panda
     * @return la carte objectif piochée
     * @implNote la pioche ne doit pas être vide
     */
    public ObjectifPanda piocheObjectifPanda() {
        assert !isEmptyPiocheObjectifPanda() : "La pioche d'objectifs de panda est vide";
        return (ObjectifPanda) piochesObjectif[1].pioche();
    }

    /**
     * Renvoie une carte objectif désignée aléatoirement dans la pioche d'objectifs de jardinier
     * @return la carte objectif piochée
     * @implNote la pioche ne doit pas être vide
     */
    public ObjectifJardinier piocheObjectifJardinier() {
        assert !isEmptyPiocheObjectifJardinier() : "La pioche d'objectifs de jardinier est vide";
        return (ObjectifJardinier) piochesObjectif[2].pioche();
    }


    // Méthode toString

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (PiocheObjectifInterface pioche : piochesObjectif) {
            s.append(pioche).append("\n");
        }
        return s.toString();
    }
}
