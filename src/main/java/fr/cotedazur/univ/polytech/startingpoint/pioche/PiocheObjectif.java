package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;

/**
 * Classe permettant de gérer les pioches d'objectifs (objectifs de parcelles, panda et jardinier)
 * @author équipe N
 */
public class PiocheObjectif {
    // Définition des attributs
    private final PiocheObjectifInterface[] piochesObjectif;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param pOPar est la pioche d'objectifs de parcelles
     * @param pOPan est la pioche d'objectifs de panda
     * @param pOJar est la pioche d'objectifs de jardinier
     */
    public PiocheObjectif(PiocheObjectifParcelle pOPar, PiocheObjectifPanda pOPan, PiocheObjectifJardinier pOJar) {
        if (pOPar == null || pOPan == null || pOJar == null) throw new NullPointerException("Les pioches ne doivent pas être null");
        piochesObjectif = new PiocheObjectifInterface[]{pOPar, pOPan, pOJar};
    }


    // Accesseurs et méthode toString
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
     * @return <code>true</code> si la pioche est vide, <code>false</code> sinon
     */
    public boolean isEmptyPiocheObjectifParcelle() {
        return piochesObjectif[0].isEmpty();
    }

    /**
     * Renvoie si la pioche d'objectifs de panda ne contient plus de cartes
     * @return <code>true</code> si la pioche est vide, <code>false</code> sinon
     */
    public boolean isEmptyPiocheObjectifPanda() {
        return piochesObjectif[1].isEmpty();
    }

    /**
     * Renvoie si la pioche d'objectifs de jardinier ne contient plus de cartes
     * @return <code>true</code> si la pioche est vide, <code>false</code> sinon
     */
    public boolean isEmptyPiocheObjectifJardinier() {
        return piochesObjectif[2].isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (PiocheObjectifInterface pioche : piochesObjectif) {
            s.append(pioche).append("\n");
        }
        return s.toString();
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
}
