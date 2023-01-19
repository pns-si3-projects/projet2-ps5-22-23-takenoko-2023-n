package fr.cotedazur.univ.polytech.startingpoint.jeu;

import fr.cotedazur.univ.polytech.startingpoint.Afficheur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.Random;

/**
 * Initialise le jeu et gère les tours
 * @author équipe N
 */
public class MaitreDuJeu {
    // Définition des attributs

    private final Plateau plateau;
    private final PiocheParcelle piocheParcelle;
    private final PiocheObjectif piocheObjectif;
    private final PiocheBambou piocheBambou;
    private final Joueur[] joueurs;
    private int nombreTour;


    // Définition des constructeurs

    /**
     * Initialise le jeu (joueurs, plateau, pioches)
     */
    public MaitreDuJeu(String nomJoueur1, String nomJoueur2) {
        Random random = new Random();
        PiocheObjectifInterface pOPar = new PiocheObjectifParcelle(random);
        PiocheObjectifInterface pOPan = new PiocheObjectifPanda(random);
        PiocheObjectifInterface pOJar = new PiocheObjectifJardinier(random);

        plateau = new Plateau();
        piocheParcelle = new PiocheParcelle(random);
        piocheObjectif = new PiocheObjectif(pOPar, pOPan, pOJar);
        piocheBambou = new PiocheBambou(random);
        joueurs = new Joueur[]{new Joueur(nomJoueur1), new Joueur(nomJoueur2)};
        nombreTour = 1;

        Afficheur.initialisation();
    }
}
