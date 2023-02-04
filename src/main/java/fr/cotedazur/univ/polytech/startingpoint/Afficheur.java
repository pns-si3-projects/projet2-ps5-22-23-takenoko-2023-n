package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;

/**
 * Affiche les changements dans le jeu.
 * @author equipe N
 */
public class Afficheur {
    /**
     * Affiche l'état d'initialisation du jeu
     */
    public static void initialisation() {
        System.out.println("Initialisation du jeu : étang, panda et jardinier en (0,0).");
    }

    /**
     * Affiche la victoire du joueur gagnant
     * @param joueur le joueur gagnant
     */
    public static void victoire(Joueur joueur) {
        System.out.println("\n\nBravo à " + joueur.getNom() + " d'avoir remporté la partie avec "
            + joueur.nombreObjectifsTermines() + " objectifs terminés pour " + joueur.nombrePoints() + " points !");
    }

    /**
     * Affiche le partage de la victoire par tous les joueurs
     */
    public static void victoire() {
        System.out.println("\n\nBravo à tous les joueurs d'avoir participé et à bientôt pour une autre partie !");
    }
}
