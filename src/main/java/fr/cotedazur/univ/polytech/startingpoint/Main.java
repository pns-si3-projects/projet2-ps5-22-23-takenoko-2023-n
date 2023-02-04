package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.jeu.MaitreDuJeu;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Strategie;

public class Main {

    public static void main(String... args) {
        Joueur joueur1 = new Joueur("joueur1", Strategie.StrategiePossible.PARCELLE);
        Joueur joueur2 = new Joueur("joueur2", Strategie.StrategiePossible.JARDINIER);
        Joueur joueur3 = new Joueur("joueur3", Strategie.StrategiePossible.PANDA);
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueur1, joueur2, joueur3);
    }
}
