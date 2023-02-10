package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Empereur;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

/**
 * Initialise le jeu et gère les tours.
 * @author équipe N
 */
public class MaitreDuJeu {
    // Définition des attributs

    private final GestionTours gestionTours;
    private final Joueur[] joueurs;
    /** Le nombre d'objectifs demandé pour recevoir la carte empereur */
    private final int nombreObjectifsDemandes;


    // Définition des constructeurs

    /**
     * Initialise le jeu (joueurs, plateau, pioches)
     * @param joueurs les joueurs de la partie, entre 2 et 4 joueurs
     */
    public MaitreDuJeu(@NotNull Joueur ...joueurs) {
        int nbJoueurs = joueurs.length;
        if (nbJoueurs < 2 || nbJoueurs > 4) {
            throw new IllegalArgumentException(nbJoueurs < 2 ? "Il n'y a pas assez de joueurs" : "Il y a trop de joueurs");
        }

        gestionTours = new GestionTours();
        this.joueurs = Arrays.copyOf(joueurs, nbJoueurs);
        nombreObjectifsDemandes = nombreObjectifsDemandes(nbJoueurs);

        AfficheurJeu.initialisation(nbJoueurs, nombreObjectifsDemandes);
        gestionTours.initialiseJoueurs(this.joueurs);
    }

    /**
     * Calcul le nombre d'objectifs demandé pour recevoir la carte empereur
     * @param nbJoueurs le nombre de joueurs de la partie
     * @return le nombre d'objectifs demandé
     */
    private int nombreObjectifsDemandes(int nbJoueurs) {
        return switch (nbJoueurs) {
            case 2 -> 9;
            case 3 -> 8;
            case 4 -> 7;
            default -> {
                assert false : "Nombre de joueurs impossible";
                yield 0;
            }
        };
    }


    // Méthodes d'utilisation

    /**
     * Lance la partie du jeu et donne les tours aux joueurs
     */
    public Optional<Joueur> jeu() {
        Joueur joueurFinObjectifs = gestionTours.tours(joueurs, nombreObjectifsDemandes);
        joueurFinObjectifs.recoitEmpereur(new Empereur());
        AfficheurJeu.recoitEmpereur(joueurFinObjectifs);
        gestionTours.dernierTour(joueurs, joueurFinObjectifs);

        Optional<Joueur> joueurOpt = gestionTours.gagnant(joueurs);
        joueurOpt.ifPresent(AfficheurJeu::victoire);

        AfficheurJeu.victoire();
        return joueurOpt;
    }


    // Méthode toString

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        for (Joueur joueur : joueurs) {
            sB.append(joueur.toString()).append("\n");
        }
        return sB.toString();
    }
}
