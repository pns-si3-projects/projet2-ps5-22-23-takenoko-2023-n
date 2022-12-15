package fr.cotedazur.univ.polytech.startingpoint;


/**
 * Classe qui contient le jeu, donc le plateau et le joueur
 * @author equipe N
 * @version 1.0
 */
public class Jeu {
    private Joueur joueur;
    protected static Plateau plateau;
    private final static String DEBUT_DE_TOUR = "Debut du tour numero ";
    private final static String FIN_DE_TOUR = "Fin du tour numero ";

    /**
     * Constructeur du joueur
     * @param joueur Necessite un joueur pour lancer la partie
     */

    public Jeu(Joueur joueur) {
        this.joueur = joueur;
        plateau = new Plateau();
    }

    /**
     * Fait un tour de jeu avec le joueur et affiche les actions du joueur
     */
    public void tour(){
        Afficheur affichage = new Afficheur();
        affichage.affichage(DEBUT_DE_TOUR+"1");
        String message = joueur.choisiAction();
        affichage.affichage(message);
        affichage.affichage(FIN_DE_TOUR+"1");
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
