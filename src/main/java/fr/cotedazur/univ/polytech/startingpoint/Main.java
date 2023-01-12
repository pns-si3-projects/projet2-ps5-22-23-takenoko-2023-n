package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionnairePossibilitePlateau;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.Random;

public class Main {
    // Définition des attributs
    public static final Plateau PLATEAU = new Plateau();
    public static final GestionnairePossibilitePlateau GESTIONNAIRE_PLATEAU = new GestionnairePossibilitePlateau(PLATEAU);
    public static final Afficheur AFFICHEUR = new Afficheur();
    private static final Random RANDOM = new Random();
    private static final PiocheObjectifPanda piocheObjectifPanda= new PiocheObjectifPanda(RANDOM);
    private static final PiocheObjectifParcelle piocheObjectifParcelle = new PiocheObjectifParcelle(RANDOM);
    private static final PiocheObjectifJardinier piocheObjectifJardinier = new PiocheObjectifJardinier(RANDOM);
    public static final PiocheObjectif piocheObjectif = new PiocheObjectif(piocheObjectifParcelle,piocheObjectifPanda,piocheObjectifJardinier);
    public static final PiocheBambou piocheBambou = new PiocheBambou(RANDOM);
    public static final Arbitre ARBITRE = new Arbitre();

    public static void main(String... args) {
        ObjectifParcelle objParJ1 = piocheObjectif.piocheObjectifParcelle();
        ObjectifPanda objPanJ1 = piocheObjectif.piocheObjectifPanda();
        ObjectifJardinier objJarJ1 = piocheObjectif.piocheObjectifJardinier();
        ObjectifParcelle objParJ2 = piocheObjectif.piocheObjectifParcelle();
        ObjectifPanda objPanJ2 = piocheObjectif.piocheObjectifPanda();
        ObjectifJardinier objJarJ2 = piocheObjectif.piocheObjectifJardinier();
        Joueur joueur1 = new Joueur("Robot1", RANDOM, objParJ1, objPanJ1, objJarJ1);
        Joueur joueur2 = new Joueur("Robot2", RANDOM, objParJ2, objPanJ2, objJarJ2);
        jeu(joueur1,joueur2);
    }

    /**
     * Permet de lancer la partie avec les affichages
     * @param joueurs sont les joueurs automatiques qui participent à la partie
     */
    public static void jeu(Joueur... joueurs){
        while (!ARBITRE.verifieFinDeJeu(joueurs)){
            AFFICHEUR.afficheDebutTour(ARBITRE.getNombreTour());
            for(Joueur joueur : joueurs){
                AFFICHEUR.afficheJoueurDebutTour(joueur);
                joueur.tour(piocheObjectif, piocheBambou, PLATEAU, ARBITRE,GESTIONNAIRE_PLATEAU);
                AFFICHEUR.afficheJoueurFinTour(joueur);
            }
            AFFICHEUR.afficheFinTour(ARBITRE.getNombreTour());
            ARBITRE.addTour();
        }
        AFFICHEUR.afficheGagnant(ARBITRE.joueurGagnant(joueurs));
    }
}
