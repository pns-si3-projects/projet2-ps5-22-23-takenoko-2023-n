package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente les éléments que le joueur possède et joue (bambous mangés, objectifs à réaliser)
 * @author équipe N
 */
public class Plaquette {
    // Définition des attributs

    // Ajouter les objectifs à réaliser et les bambous mangés
    private final List<SectionBambou> bambousManges;
    private final List<Objectif> objectifsARealiser;
    public static final int NOMBRE_OBJECTIFS_MAX = 5;


    // Définition des constructeurs

    /**
     * Constructeur unique de la Plaquette
     */
    public Plaquette() {
        bambousManges = new ArrayList<>();
        objectifsARealiser = new ArrayList<>();
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de bambous verts récupérés
     * @return le nombre de bambous verts récupérés
     */
    public int getNombreBambousVerts() {
        // Modifier la méthode pour renvoyer le nombre de bambous verts
        return bambousManges.size();
    }

    /**
     * Renvoie le nombre de bambous roses récupérés
     * @return le nombre de bambous roses récupérés
     */
    public int getNombreBambousRoses() {
        // Modifier la méthode pour renvoyer le nombre de bambous roses
        return bambousManges.size();
    }

    /**
     * Renvoie le nombre de bambous jaunes récupérés
     * @return le nombre de bambous jaunes récupérés
     */
    public int getNombreBambousJaunes() {
        // Modifier la méthode pour renvoyer le nombre de bambous jaunes
        return bambousManges.size();
    }

    /**
     * Renvoie le nombre d'objectifs de parcelle
     * @return le nombre d'objectifs de parcelle
     */
    public int getNombreObjectifParcelle() {
        int nbObjPar = 0;
        for (Objectif objectif : objectifsARealiser) {
            if (objectif.getClass().equals(ObjectifParcelle.class)) nbObjPar++;
        }
        return nbObjPar;
    }

    /**
     * Renvoie le nombre d'objectifs de panda
     * @return le nombre d'objectifs de panda
     */
    public int getNombreObjectifPanda() {
        int nbObjPan = 0;
        for (Objectif objectif : objectifsARealiser) {
            if (objectif.getClass().equals(ObjectifPanda.class)) nbObjPan++;
        }
        return nbObjPan;
    }

    /**
     * Renvoie le nombre d'objectifs de jardinier
     * @return le nombre d'objectifs de jardinier
     */
    public int getNombreObjectifJardinier() {
        int nbObjJar = 0;
        for (Objectif objectif : objectifsARealiser) {
            if (objectif.getClass().equals(ObjectifJardinier.class)) nbObjJar++;
        }
        return nbObjJar;
    }

    /**
     * Renvoie le nombre d'objectifs
     * @return le nombre d'objectifs
     */
    public int getNombreObjectifs() {
        return objectifsARealiser.size();
    }

    /**
     * Renvoie le nombre d'objectifs maximum pouvant être possédés
     * @return le nombre d'objectifs maximum pouvant être possédés
     */
    public int getNombreObjectifsMax() {
        return NOMBRE_OBJECTIFS_MAX;
    }

    /**
     * Renvoie tous les objectifs de parcelle à réaliser
     * @return un tableau d'objectifs de parcelle
     */
    public ObjectifParcelle[] getObjectifsParcelle() {
        int nbObjPar = getNombreObjectifParcelle();
        ObjectifParcelle[] objectifs = new ObjectifParcelle[nbObjPar];
        for (Objectif objectif : objectifsARealiser) {
            if (objectif.getClass().equals(ObjectifParcelle.class)) {
                objectifs[--nbObjPar] = (ObjectifParcelle) objectif;
            }
        }
        return objectifs;
    }

    /**
     * Renvoie tous les objectifs de panda à réaliser
     * @return un tableau d'objectifs de panda
     */
    public ObjectifPanda[] getObjectifsPanda() {
        int nbObjPan = getNombreObjectifPanda();
        ObjectifPanda[] objectifs = new ObjectifPanda[nbObjPan];
        for (Objectif objectif : objectifsARealiser) {
            if (objectif.getClass().equals(ObjectifPanda.class)) {
                objectifs[--nbObjPan] = (ObjectifPanda) objectif;
            }
        }
        return objectifs;
    }

    /**
     * Renvoie tous les objectifs de jardinier à réaliser
     * @return un tableau d'objectifs de jardinier
     */
    public ObjectifJardinier[] getObjectifsJardinier() {
        int nbObjJar = getNombreObjectifJardinier();
        ObjectifJardinier[] objectifs = new ObjectifJardinier[nbObjJar];
        for (Objectif objectif : objectifsARealiser) {
            if (objectif.getClass().equals(ObjectifJardinier.class)) {
                objectifs[--nbObjJar] = (ObjectifJardinier) objectif;
            }
        }
        return objectifs;
    }

    /**
     * Renvoie tous les objectifs à réaliser
     * @return un tableau d'objectifs
     */
    public Objectif[] getObjectifs() {
        Objectif[] objectifs = new Objectif[objectifsARealiser.size()];
        for (int i=0; i<objectifsARealiser.size(); i++) {
            objectifs[i] = objectifsARealiser.get(i);
        }
        return objectifs;
    }

    /**
     * Ajoute une section de bambou donnée à la plaquette
     * @param sectionBambou est la section de bambou à ajouter à la plaquette
     */
    public void ajouteSectionBambou(SectionBambou sectionBambou) {
        bambousManges.add(sectionBambou);
    }

    /**
     * Ajoute un objectif donné à la plaquette
     * @param objectif est l'objectif à ajouter à la plaquette
     */
    public void ajouteObjectif(Objectif objectif) {
        if (objectifsARealiser.size() < NOMBRE_OBJECTIFS_MAX) objectifsARealiser.add(objectif);
    }
}
