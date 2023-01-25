package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.Afficheur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.Random;

/**
 * Initialise le jeu et gère les tours.
 * @author équipe N
 */
public class MaitreDuJeu {
    // Définition des attributs

    private final Plateau plateau;
    private final PiocheParcelle piocheParcelle;
    private final PiocheObjectifParcelle piocheObjectifParcelle;
    private final PiocheObjectifPanda piocheObjectifPanda;
    private final PiocheObjectifJardinier piocheObjectifJardinier;
    private final PiocheSectionBambou piocheSectionBambou;
    private final Joueur[] joueurs;
    private int nombreTour;


    // Définition des constructeurs

    /**
     * Initialise le jeu (joueurs, plateau, pioches)
     */
    public MaitreDuJeu(String nomJoueur1, String nomJoueur2, Random random) {
        plateau = new Plateau();
        piocheParcelle = new PiocheParcelle(random);
        piocheObjectifParcelle = new PiocheObjectifParcelle(random);
        piocheObjectifPanda = new PiocheObjectifPanda(random);
        piocheObjectifJardinier = new PiocheObjectifJardinier(random);
        piocheSectionBambou = new PiocheSectionBambou(random);
        joueurs = new Joueur[]{new Joueur(nomJoueur1), new Joueur(nomJoueur2)};
        nombreTour = 1;

        Afficheur.initialisation();
    }
}
