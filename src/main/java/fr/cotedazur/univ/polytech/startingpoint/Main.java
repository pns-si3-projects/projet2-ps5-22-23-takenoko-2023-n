package fr.cotedazur.univ.polytech.startingpoint;

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
    public static final Arbitre ARBITRE = new Arbitre();

    public static void main(String... args) {
        Joueur joueur1 = new Joueur("Robot1",RANDOM,piocheObjectif.piocheObjectifParcelle());
        Joueur joueur2 = new Joueur("Robot2",RANDOM,piocheObjectif.piocheObjectifParcelle());
        Jeu(joueur1,joueur2);
    }

    public static void Jeu(Joueur... joueurs){
        while (!ARBITRE.checkFinDeJeu(joueurs)){
            AFFICHEUR.afficheDebutTour(ARBITRE.getNombreTour());
            for(Joueur joueur : joueurs){
                AFFICHEUR.afficheJoueurDebutTour(joueur);
                joueur.tour(piocheObjectif,PLATEAU,ARBITRE);
                AFFICHEUR.afficheJoueurFinTour(joueur);
            }
            AFFICHEUR.afficheFinTour(ARBITRE.getNombreTour());
            ARBITRE.addTour();
        }
        AFFICHEUR.afficheGagnant(ARBITRE.joueurGagnant(joueurs));
    }
}
