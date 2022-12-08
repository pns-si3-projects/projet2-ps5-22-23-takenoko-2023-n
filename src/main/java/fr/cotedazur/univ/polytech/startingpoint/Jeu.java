package fr.cotedazur.univ.polytech.startingpoint;

public class Jeu {
    private Joueur joueur;
    private Etang etang;

    public Jeu(Joueur joueur, Etang etang) {
        this.joueur = joueur;
        this.etang = etang;
    }

    public Joueur getJoueur() {
        return joueur;
    }
    public Etang getEtang() {
        return etang;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    public void setEtang(Etang etang) {
        this.etang = etang;
    }
}
