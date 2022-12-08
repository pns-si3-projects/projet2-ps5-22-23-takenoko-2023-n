package fr.cotedazur.univ.polytech.startingpoint;

public class Jeu {
    private Joueur joueur;
    private Plateau plateau;

    public Jeu(Joueur joueur) {
        this.joueur = joueur;
        plateau = new Plateau();
    }

    public Joueur getJoueur() {
        return joueur;
    }
    public Plateau getPlateau() {
        return plateau;
    }

    public String toString(){
        return "Un nouveau jeu commence !\nJoueur : " + joueur.getNom();
    }
}
