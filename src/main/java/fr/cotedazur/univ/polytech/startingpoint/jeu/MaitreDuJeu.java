package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Empereur;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

/**
 * Initialise le jeu et gère les tours.
 * @author équipe N
 */
public class MaitreDuJeu {
    // Définition des attributs

    public static final Plateau PLATEAU = new Plateau();
    public static final PiocheParcelle PIOCHE_PARCELLE = new PiocheParcelle(new Random());
    public static final PiocheObjectifParcelle PIOCHE_OBJECTIF_PARCELLE = new PiocheObjectifParcelle(new Random());
    public static final PiocheObjectifPanda PIOCHE_OBJECTIF_PANDA = new PiocheObjectifPanda(new Random());
    public static final PiocheObjectifJardinier PIOCHE_OBJECTIF_JARDINIER = new PiocheObjectifJardinier(new Random());
    public static final PiocheSectionBambou PIOCHE_SECTION_BAMBOU = new PiocheSectionBambou(new Random());
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

        this.joueurs = Arrays.copyOf(joueurs, nbJoueurs);
        gestionTours = new GestionTours();
        nombreObjectifsDemandes = nombreObjectifsDemandes(joueurs.length);

        AfficheurJeu.initialisation();
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
    public void jeu() {
        Joueur joueurFinObjectifs = gestionTours.tours(joueurs, nombreObjectifsDemandes);
        joueurFinObjectifs.recoitEmpereur(new Empereur());

        gestionTours.dernierTour(joueurs, joueurFinObjectifs);

        Optional<Joueur> joueurOpt = gestionTours.gagnant(joueurs);
        if (joueurOpt.isPresent()) {
            AfficheurJeu.victoire(joueurOpt.get());
        } else {
            AfficheurJeu.victoire();
        }
    }
}
