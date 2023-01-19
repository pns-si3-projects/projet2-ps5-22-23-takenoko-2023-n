package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.plateau.SectionBambou;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente la plaquette individuelle d'un joueur
 * Contient les actions du tour et la liste des bambous mangés
 * @author équipe N
 */
public class Plaquette {
    // Définition des attributs

    public enum ActionPossible {PARCELLE, JARDINIER, PANDA, OBJECTIF}
    private final List<SectionBambou> reserveBambousManges;
    private final Boolean[] actionsTour;


    // Définition des constructeurs

    /**
     * Construit la plaquette d'un joueur
     */
    public Plaquette() {
        reserveBambousManges = new ArrayList<>();
        actionsTour = new Boolean[]{false, false, false, false};
    }


    // Accesseurs

    /**
     * Renvoie les sections de bambou mangées
     * @return un tableau des sections de bambous mangées
     */
    public SectionBambou[] getReserveBambousManges() {
        int nbBambous = reserveBambousManges.size();
        SectionBambou[] bambous = new SectionBambou[nbBambous];
        for (int i=0; i<nbBambous; i++) {
            bambous[i] = reserveBambousManges.get(i);
        }
        return bambous;
    }

    /**
     * Renvoie si l'action est jouée dans le tour
     * @param actionPossible l'action à vérifier si jouée dans le tour
     * @return {@code true} si l'action est jouée dans le tour
     */
    public boolean isActionTour(@NotNull ActionPossible actionPossible) {
        return actionsTour[actionPossible.ordinal()];
    }


    // Autres méthodes

    /**
     * Renvoie le nombre sections de bambou mangées de la couleur demandée
     * @param couleurBambou la couleur demandée pour le bambou
     * @return le nombre sections de bambou mangées de la couleur demandée
     */
    public int nombreBambouCouleur(Couleur couleurBambou) {
        int somme = 0;
        for (SectionBambou sectionBambou : reserveBambousManges) {
            if (sectionBambou.couleur() == couleurBambou) {
                somme++;
            }
        }
        return somme;
    }

    /**
     * Ajoute la section de bambou mangée à la réserve dans la plaquette
     * @param sectionBambou la section de bambou à ajouter à réserve de la plaquette
     * @return {@code true} si la section de bambou est ajoutée
     */
    public boolean mangeSectionBambou(@NotNull SectionBambou sectionBambou) {
        return reserveBambousManges.add(sectionBambou);
    }
}
