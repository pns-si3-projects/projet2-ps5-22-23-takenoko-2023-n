package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.Afficheur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Strategie;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

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
    private final Joueur[] joueurs;
    private int nombreTour;


    // Définition des constructeurs

    /**
     * Initialise le jeu (joueurs, plateau, pioches)
     */
    public MaitreDuJeu(String nomJoueur1, String nomJoueur2) {
        Joueur joueur1 = new Joueur(nomJoueur1, Strategie.StrategiePossible.PARCELLE);
        Joueur joueur2 = new Joueur(nomJoueur2, Strategie.StrategiePossible.PANDA);

        joueurs = new Joueur[]{joueur1, joueur2};
        nombreTour = 1;

        Afficheur.initialisation();
    }
}
