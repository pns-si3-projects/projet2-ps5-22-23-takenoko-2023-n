package fr.cotedazur.univ.polytech.startingpoint;

public class Main {
    public static void main(String[] args){
        Joueur joueur = new Joueur("Robot1");
        Etang etang = new Etang();
        Jeu jeu = new Jeu(joueur, etang);
        System.out.println(jeu.toString());
    }

}
