package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Représente les pièces posées du jeu.
 * Contient les parcelles, le panda, le jardinier et les bambous.
 * @author équipe N
 */
public class Plateau {
    // Définition des attributs

    private final Map<Parcelle, Parcelle[]> parcelleEtVoisinesList;
    private final Set<Bambou> bambouList;
    private final Panda panda;
    private final Jardinier jardinier;


    // Définition des constructeurs

    /**
     * Construit le plateau du jeu
     * Place l'étang, le panda et le jardinier en (0,0)
     */
    public Plateau() {
        // Initialisation des attributs
        parcelleEtVoisinesList = new HashMap<>();
        bambouList = new HashSet<>();
        panda = new Panda();
        jardinier = new Jardinier();

        // Ajout de l'étang dans la liste des parcelles
        Etang etang = new Etang();
        // ----------------------------------------------------------------------------------------------
    }


    // Accesseurs

    /**
     * Renvoie les parcelles posées
     * @return un tableau des parcelles posées
     */
    public Parcelle[] getParcelles() {
        int nbParcelles = parcelleEtVoisinesList.size();
        Parcelle[] parcelles = new Parcelle[nbParcelles];
        Iterator<Parcelle> iterateurParcelles = parcelleEtVoisinesList.keySet().iterator();

        for (int i=0; i<nbParcelles; i++) {
            parcelles[i] = iterateurParcelles.next();
        }
        return parcelles;
    }

    /**
     * Renvoie les parcelles voisines de la parcelle demandée
     * @param parcelle la parcelle donnée pour chercher les voisines
     * @return un tableau des parcelles voisines demandées
     * @throws ParcelleNonPoseeException si la parcelle donnée ne se trouve pas sur le plateau
     */
    public Parcelle[] getParcellesVoisines(@NotNull Parcelle parcelle) throws ParcelleNonPoseeException {
        if (parcelleEtVoisinesList.containsKey(parcelle)) {
            return parcelleEtVoisinesList.get(parcelle);
        }
        throw new ParcelleNonPoseeException(parcelle);
    }

    /* *************************************************************************************************************
     * Ajouter getParcelle(Position position)
     * Ajouter getParcellesCouleur(Couleur couleur)
     **************************************************************************************************************/

    /**
     * Renvoie les bambous posés
     * @return un tableau des bambous posés
     */
    public Bambou[] getBambous() {
        int nbBambous = bambouList.size();
        Bambou[] bambous = new Bambou[nbBambous];
        Iterator<Bambou> iterateurBambous = bambouList.iterator();

        for (int i=0; i<nbBambous; i++) {
            bambous[i] = iterateurBambous.next();
        }
        return bambous;
    }

    /* *************************************************************************************************************
     * Ajouter getBambou(Position position)
     * Ajouter getBambousCouleur(Couleur couleur)
     **************************************************************************************************************/

    /**
     * Renvoie le panda
     * @return le panda
     */
    public Panda getPanda() {
        return panda;
    }

    /**
     * Renvoie le jardinier
     * @return le jardinier
     */
    public Jardinier getJardinier() {
        return jardinier;
    }
}