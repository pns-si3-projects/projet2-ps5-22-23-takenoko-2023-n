package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.jeu.MaitreDuJeu;

import java.util.Random;

public class Main {

    public static void main(String... args) {
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu("Joueur1", "Joueur2", new Random());
    }
}
