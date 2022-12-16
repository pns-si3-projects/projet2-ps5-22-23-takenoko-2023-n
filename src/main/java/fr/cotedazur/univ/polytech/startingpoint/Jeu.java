package fr.cotedazur.univ.polytech.startingpoint;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui contient le jeu, donc le plateau et le joueur
 * @author equipe N
 * @version 1.0
 */
public class Jeu {
    private Joueur joueur;
    private int nombredeTour;
    protected static Plateau plateau;
    protected static final List<ObjectifParcelle> piocheObjectifParcelle = new ArrayList<>();

    private final static String DEBUT_DE_TOUR = "Debut du tour numero ";
    private final static String FIN_DE_TOUR = "Fin du tour numero ";

    /**
     * Constructeur du joueur
     * @param joueur Necessite un joueur pour lancer la partie
     */

    public Jeu(Joueur joueur) {
        this.joueur = joueur;
        addPioche();
        joueur.piocheObjectif();
        plateau = new Plateau();
        nombredeTour = 1;
    }

    private void addPioche(){
        piocheObjectifParcelle.add(new ObjectifParcelle(1,1));
        piocheObjectifParcelle.add(new ObjectifParcelle(2,2));
        piocheObjectifParcelle.add(new ObjectifParcelle(3,3));
    }

    /**
     * Fait un tour de jeu avec le joueur et affiche les actions du joueur
     */
    public boolean tour(){
        Afficheur affichage = new Afficheur();
        affichage.affichage(DEBUT_DE_TOUR+nombredeTour);
        String message = joueur.choisiAction();
        affichage.affichage(message);
        String message2 = joueur.placeParcelle();
        affichage.affichage(message2);
        nombredeTour++;
        affichage.affichage(FIN_DE_TOUR+(nombredeTour-1));
        return joueur.getNombreDePoint() >= 4;
    }

    /**
     * Getter de joueur
     * @return Renvoie le joueur de la partie
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Getter de plateau
     * @return Renvoie le plateau de la partie
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Permet de renvoyer le message de fin de partie
     * @return Retourne le message de fin de partie
     */

    public String finDePartie(){
        return "Le joueur "+joueur.getNom()+" a gagne la partie !";
    }

    /**
     * Affiche l'initialisation du jeu
     * @return Retourne le message que la partie commence
     */
    @Override
    public String toString(){
        return "Un nouveau jeu commence !\nJoueur : " + joueur.getNom();
    }
}
