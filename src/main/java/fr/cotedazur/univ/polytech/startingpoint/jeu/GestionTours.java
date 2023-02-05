package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Plaquette;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.*;

/**
 * Gère les tours pour le maitre du jeu et renvoie le gagnant
 * @author équipe N
 */
public class GestionTours {
    // Définition des attributs

    private final Plateau plateau;
    private final PiocheParcelle piocheParcelle;
    private final PiocheObjectifParcelle piocheObjectifParcelle;
    private final PiocheObjectifPanda piocheObjectifPanda;
    private final PiocheObjectifJardinier piocheObjectifJardinier;
    private final PiocheSectionBambou piocheSectionBambou;
    private final PiocheIrrigation piocheIrrigation;


    // Définition des constructeurs

    public GestionTours() {
        plateau = new Plateau();
        piocheParcelle = new PiocheParcelle(new Random());
        piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        piocheSectionBambou = new PiocheSectionBambou(new Random());
        piocheIrrigation = new PiocheIrrigation(new Random());
    }


    // Méthodes d'utilisation

    /**
     * Gère les tours de chaque joueur jusqu'à ce qu'un joueur finisse le nombre d'objectifs demandé
     * @param joueurs les joueurs de la partie
     * @return le joueur ayant terminé le nombre d'objectifs demandé
     */
    public Joueur tours(Joueur[] joueurs, int nombreObjectifsDemandes) {
        int nbTours = 1;
        while (true) {
            AfficheurJeu.debutTour(nbTours);
            for (Joueur joueur : joueurs) {
                joueTour(joueur);
                if (joueur.nombreObjectifsTermines() >= nombreObjectifsDemandes) {
                    return joueur;
                }
            }
            nbTours++;
        }
    }

    /**
     * Permet au joueur de choisir deux actions et de les effectuer
     * @param joueur le joueur pour qui c'est le tour
     */
    private void joueTour(Joueur joueur) {
        Plaquette.ActionPossible actionChoisie;

        for (int i=0; i<2; i++) {
            actionChoisie = joueur.choisiAction();

            switch (actionChoisie) {
                case PARCELLE -> joueur.joueParcelle(plateau, piocheParcelle);
                case IRRIGATION -> joueur.joueIrrigation(plateau, piocheIrrigation);
                case JARDINIER -> joueur.deplaceJardinier(plateau);
                case PANDA -> joueur.deplacePanda(plateau);
                case OBJECTIF -> joueur.piocheObjectif(piocheObjectifParcelle,
                        piocheObjectifJardinier, piocheObjectifPanda);
            }
        }

        joueur.finDeTour();
    }

    /**
     * Effectue le dernier tour de jeu sans le joueur ayant reçu la carte Empereur
     * @param joueurs les joueurs de la partie sans le joueur ayant reçu la carte Empereur
     */
    public void dernierTour(Joueur[] joueurs, Joueur joueurFinObjectifs) {
        AfficheurJeu.debutDernierTour();
        for (Joueur joueur : joueurs) {
            if (!joueur.equals(joueurFinObjectifs)) {
                joueur.choisiAction();
            }
        }
    }

    /**
     * Renvoie le joueur gagnant par le total des points puis par celui des objectifs de panda en cas d'égalité
     * @param joueurs les joueurs de la partie
     * @return le joueur gagnant
     */
    public Optional<Joueur> gagnant(Joueur[] joueurs) {
        return gagnant(joueurs, false);
    }

    /**
     * Renvoie le joueur gagnant par le total des points puis par celui des objectifs de panda en cas d'égalité
     * @param joueurs les joueurs de la partie
     * @param compPointsPanda {@code true} s'il faut comparer par le nombre de points des objectifs de panda
     * @return le joueur gagnant
     */
    private Optional<Joueur> gagnant(Joueur[] joueurs, boolean compPointsPanda) {
        Map<Integer, List<Joueur>> triJoueurs = new HashMap<>();
        int nbPoints = -1;
        List<Joueur> joueurList;

        // Tri les joueurs par leur nombre de points
        for (Joueur joueur : joueurs) {
            nbPoints = compPointsPanda ? joueur.nombrePointsObjectifsPanda() : joueur.nombrePoints();
            joueurList = triJoueurs.containsKey(nbPoints) ? triJoueurs.get(nbPoints) : new ArrayList<>(1);
            joueurList.add(joueur);
            triJoueurs.put(nbPoints, joueurList);
        }

        // Calcul le nombre de points maximum
        for (int points : triJoueurs.keySet().toArray(new Integer[0])) {
            if (points > nbPoints) {
                nbPoints = points;
            }
        }

        // Renvoie le joueur gagnant
        joueurList = triJoueurs.get(nbPoints);
        Joueur[] joueursPanda = joueurList.toArray(new Joueur[0]);
        if (joueurList.size() > 1) {
            if (compPointsPanda) { // Cas si joueurs déjà comparés par les points des objectifs de panda
                return Optional.empty();
            }
            return gagnant(joueursPanda, true); // Cas si joueurs comparés que par le total des points
        } else {
            return Optional.of(joueurList.get(0));
        }
    }
}
