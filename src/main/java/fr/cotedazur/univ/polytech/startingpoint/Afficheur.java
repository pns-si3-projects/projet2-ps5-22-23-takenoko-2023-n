package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

import java.util.Optional;

/**
 * Classe représentant l'affichage du jeu
 * @author equipe N
 */
public class Afficheur {

    /**
     * Affiche le gagnant du jeu s'il y en a un
     * @param joueur le joueur gagnant
     */
    public void afficheGagnant(Optional<Joueur> joueur){
        if ( joueur.isEmpty() ) {
            System.out.println("Il y a eu une égalité");
        }
        else {
            Joueur joueurGagnant = joueur.get();
            System.out.println("Le joueur gagnant est le joueur " + joueurGagnant.getNom() + " avec " + joueurGagnant.getPoints() + " Points");
        }
    }

    /**
     * Affiche l'ajout d'une parcelle sur le plateau ainsi que sa position
     * @param parcelleCouleurAjoute la parcelle couleur ajoutée
     */
    public void afficheAjoutParcelle(ParcelleCouleur parcelleCouleurAjoute){
        if ( parcelleCouleurAjoute != null ) {
            System.out.println("La " + parcelleCouleurAjoute + " est posee avec un bambou");
        }
    }

    /**
     * Affiche le début d'un nouveau tour
     * @param nombreDeTour Le numéro du tour
     */
    public void afficheDebutTour(int nombreDeTour){
        System.out.println("Tour numero" + nombreDeTour);
    }

    /**
     * Affiche la fin d'un tour
     * @param nombreDeTour Le numéro du tour
     */
    public void afficheFinTour(int nombreDeTour){
        System.out.println("Tour numéro" + nombreDeTour + " terminé");
        System.out.println("\n-------------------------------------------------\n");
    }

    /**
     * Affiche le début du tour du joueur
     * @param joueur le joueur commençant son tour
     */
    public void afficheJoueurDebutTour(Joueur joueur){
        System.out.println("Au tour du joueur " + joueur.getNom());
    }

    /**
     * Affiche la fin du tour du joueur
     * @param joueur le joueur finissant son tour
     */
    public void afficheJoueurFinTour(Joueur joueur){
        System.out.println("Fin du tour du joueur " + joueur.getNom() + "\n");
    }

    /**
     * Affiche l'objectif pioché
     * @param objectif l'objectif pioché
     */
    public void affichePiocheCarte(Objectif objectif){
        String messageAAffiche;
        if ( objectif.getClass() == ObjectifParcelle.class ) {
            messageAAffiche = "Parcelle";
        }
        else if ( objectif.getClass() == ObjectifPanda.class ){
            messageAAffiche = "Panda";
        }
        else {
            messageAAffiche = "Jardinier";
        }
        System.out.println("Objectif " + messageAAffiche + " pioché");
    }

    /**
     * Affiche l'objectif terminé
     * @param objectif L'objectif qui vient de se terminer
     */
    public void afficheObjectifValide(Objectif objectif){
        System.out.println(objectif + " terminé");
    }
}
