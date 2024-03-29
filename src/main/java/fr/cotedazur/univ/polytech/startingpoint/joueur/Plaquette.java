package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.plateau.*;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Représente les éléments que le joueur possède et joue (bambous mangés, objectifs à réaliser)
 * @author équipe N
 */
public class Plaquette {
    // Définition des attributs
    private final List<SectionBambou> bambousManges;
    private final List<Objectif> objectifsARealiser;
    private final Integer[] actionsTour;
    public enum ActionPossible {PARCELLE, PANDA, OBJECTIF,JARDINIER}
    public static final int NOMBRE_OBJECTIFS_MAX = 5;



    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param objPar est l'objectif de parcelle à ajouter
     * @param objPan est l'objectif de panda à ajouter
     * @param objJar est l'objectif de jardinier à ajouter
     */
    public Plaquette(ObjectifParcelle objPar, ObjectifPanda objPan, ObjectifJardinier objJar) {
        bambousManges = new ArrayList<>();
        objectifsARealiser = new ArrayList<>(3);
        objectifsARealiser.add(objPar);
        objectifsARealiser.add(objPan);
        objectifsARealiser.add(objJar);
        actionsTour = new Integer[]{0, 0, 0,0};
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de bambous verts récupérés
     * @return le nombre de bambous verts récupérés
     */
    public int getNombreBambousVerts() {
        int nombreBambouVerts = 0;
        for(SectionBambou sectionBambou: bambousManges){
            if(sectionBambou.couleur() == Couleur.VERT){
                nombreBambouVerts++;
            }
        }
        return nombreBambouVerts;
    }

    /**
     * Renvoie le nombre de bambous roses récupérés
     * @return le nombre de bambous roses récupérés
     */
    public int getNombreBambousRoses() {
        int nombreBambouRoses = 0;
        for(SectionBambou sectionBambou: bambousManges){
            if(sectionBambou.couleur() == Couleur.ROSE){
                nombreBambouRoses++;
            }
        }
        return nombreBambouRoses;
    }

    /**
     * Renvoie le nombre de bambous jaunes récupérés
     * @return le nombre de bambous jaunes récupérés
     */
    public int getNombreBambousJaunes() {
        int nombreBambouJaunes = 0;
        for(SectionBambou sectionBambou: bambousManges){
            if(sectionBambou.couleur() == Couleur.JAUNE){
                nombreBambouJaunes++;
            }
        }
        return nombreBambouJaunes;
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
     * Renvoie le nombre d'actions réalisées dans le tour
     * @return le nombre d'actions réalisées dans le tour
     */
    public int getNombreActionsTourRealisees() {
        int res = 0;
        for (Integer action : actionsTour) {
            if (action > 0) res += action;
        }
        return res;
    }

    /**
     * Renvoie le tableau des sections de Bambous Verts mangés par le Panda
     * @return le tableau des sections de Bambous Verts
     */
    public SectionBambou[] getSectionBambouVert(){
        SectionBambou[] listSectionBambous = new SectionBambou[getNombreBambousVerts()];
        for ( int i = 0;i<bambousManges.size();i++ ) {
            if(bambousManges.get(i).couleur() == Couleur.VERT){
                listSectionBambous[i] = bambousManges.get(i);
            }
        }
        return listSectionBambous;
    }

    /**
     * Renvoie le tableau des sections de Bambous Rose mangés par le Panda
     * @return le tableau des sections de Bambous Rose
     */
    public SectionBambou[] getSectionBambouRose(){
        SectionBambou[] listSectionBambous = new SectionBambou[getNombreBambousRoses()];
        for (int i = 0;i<bambousManges.size();i++) {
            if(bambousManges.get(i).couleur() == Couleur.ROSE) {
                listSectionBambous[i] = bambousManges.get(i);
            }
        }
        return listSectionBambous;
    }

    /**
     * Renvoie le tableau des sections de Bambous Jaune mangés par le Panda
     * @return le tableau des sections de Bambous Jaune
     */
    public SectionBambou[] getSectionBambouJaune(){
        SectionBambou[] listSectionBambousJaune = new SectionBambou[getNombreBambousJaunes()];
        for (int i = 0;i<bambousManges.size();i++) {
            if ( bambousManges.get(i).couleur() == Couleur.JAUNE ) {
                listSectionBambousJaune[i] = bambousManges.get(i);
            }
        }
        return listSectionBambousJaune;
    }

    /**
     * Supprime les sections Vertes de bambousManges
     * @param nombre Nombre de Section Vertes à supprimer
     * @return <code> true </code> si on peut supprimer toutes les sections BambouVertes donné en paramètre sinon <code> false </code>
     */
    public boolean deleteSectionBambouVert(int nombre){
        List<SectionBambou> sectionBambousCopy = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < bambousManges.size();i++) {
            SectionBambou sectionBambouAVerifier = bambousManges.get(i);
            if (j >= nombre || sectionBambouAVerifier.couleur() != Couleur.VERT) {
                sectionBambousCopy.add(sectionBambouAVerifier);
            }
            else j++;
        }
        if (j < nombre) return false;
        bambousManges.clear();
        bambousManges.addAll(sectionBambousCopy);
        return true;
    }

    /**
     * Supprime les sections Roses de bambousManges
     * @param nombre Nombre de Section Roses à supprimer
     * @return <code> true </code> si on peut supprimer toutes les sections BambouRoses donné en paramètre sinon <code> false </code>
     */
    public boolean deleteSectionBambouRose(int nombre){
        List<SectionBambou> sectionBambousCopy = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < bambousManges.size();i++) {
            SectionBambou sectionBambouAVerifier = bambousManges.get(i);
            if (j >= nombre || sectionBambouAVerifier.couleur() != Couleur.ROSE) {
                sectionBambousCopy.add(sectionBambouAVerifier);
            }
            else j++;
        }
        if (j < nombre) return false;
        bambousManges.clear();
        bambousManges.addAll(sectionBambousCopy);
        return true;
    }

    /**
     * Supprime les sections Jaune de bambousManges
     * @param nombre Nombre de Section Jaune à supprimer
     * @return <code> true </code> si on peut supprimer toutes les sections BambouJaune donné en paramètre sinon <code> false </code>
     */
    public boolean deleteSectionBambouJaune(int nombre){
        List<SectionBambou> sectionBambousCopy = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < bambousManges.size();i++) {
            SectionBambou sectionBambouAVerifier = bambousManges.get(i);
            if (j >= nombre || sectionBambouAVerifier.couleur() != Couleur.JAUNE) {
                sectionBambousCopy.add(sectionBambouAVerifier);
            }
            else j++;
        }
        if (j < nombre) return false;
        bambousManges.clear();
        bambousManges.addAll(sectionBambousCopy);
        return true;
    }


    /**
     * Renvoie tous les objectifs de parcelle à réaliser
     * @return un tableau d'objectifs de parcelle dans l'ordre inverse d'ajout
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
     * @return un tableau d'objectifs de panda dans l'ordre inverse d'ajout
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
     * @return un tableau d'objectifs de jardinier dans l'ordre inverse d'ajout
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
     * @return un tableau d'objectifs dans l'ordre inverse d'ajout
     */
    public Objectif[] getObjectifs() {
        int nbObjs = objectifsARealiser.size();
        Objectif[] objectifs = new Objectif[nbObjs];
        for (Objectif objectif : objectifsARealiser) {
            objectifs[--nbObjs] = objectif;
        }
        return objectifs;
    }

    /**
     * Renvoie les actions réalisées dans le tour
     * @return un tableau des actions réalisées
     * @implSpec le nombre d'actions réalisées doit être supérieur à 0
     */
    public ActionPossible[] getActionsTourRealisees() {
        int nbActions = getNombreActionsTourRealisees();

        ActionPossible[] actionsRealisees = new ActionPossible[nbActions];
        for (ActionPossible action : ActionPossible.values()) {
            for (int i=actionsTour[action.ordinal()]; i>0; i--) {
                actionsRealisees[--nbActions] = action;
            }
        }
        return actionsRealisees;
    }

    /**
     * Vérifie si l'action demandée a été réalisée dans le tour
     * @param action est l'action à vérifier
     * @return <code>true</code> si l'action a été réalisée dans le tour, <code>false</code> sinon
     */
    public boolean isActionRealisee(ActionPossible action) {
        return actionsTour[action.ordinal()] > 0;
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
     * @throws NombreObjectifsEnCoursException si on essaie d'ajouter un objectif
     *      alors que le nombre maximum d'objectifs en cours est atteint
     */
    public void ajouteObjectif(Objectif objectif) throws NombreObjectifsEnCoursException {
        if (objectifsARealiser.size() < NOMBRE_OBJECTIFS_MAX) objectifsARealiser.add(objectif);
        else throw new NombreObjectifsEnCoursException();
    }

    /**
     * Met l'action demandée en tant que réalisée
     * @param action est l'action à réaliser
     * @return <code>true</code> si l'action est mise en tant que réalisée, <code>false</code> si l'action a déjà été réalisée
     */
    public boolean realiseAction(ActionPossible action) {
        if (isActionRealisee(action)) return false;
        actionsTour[action.ordinal()]++;
        return true;
    }

    /**
     * Renvoie si l'objectif réalisé est supprimé de la Plaquette
     * @param objectif l'objectif à retirer
     * @return <code>true</code> si l'objectif a été supprimé de la liste, <code>false</code> sinon
     */
    public boolean supprimeObjectif(Objectif objectif) {
        return objectifsARealiser.remove(objectif);
    }

    public void reinitialiseActionsTour() {
        Arrays.fill(actionsTour, 0);
    }
}
