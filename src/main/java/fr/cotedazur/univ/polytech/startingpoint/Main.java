package fr.cotedazur.univ.polytech.startingpoint;

public class Main {
    public static void main(String[] args){
        Joueur joueur = new Joueur("Robot1");
        Jeu jeu = new Jeu(joueur);
        System.out.println(jeu);
        Boolean etatjeu = false;
        while (!etatjeu){
            etatjeu = jeu.tour();
        }
        System.out.println(jeu.finDePartie());
    }
}
