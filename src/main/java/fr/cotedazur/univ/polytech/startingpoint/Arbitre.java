package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Optional;

/**
 * Arbitre qui permet de donner le gagnant,valider un objectif, ainsi que le nombre de tour
 * @author équipe N
 */
public class Arbitre {
    // Définition des attributs
    private int nombreTour;

    // Définition de constructeur
    /**
     * Constructeur unique de l'Arbitre
     */
    public Arbitre() {
        nombreTour = 1;
    }

    /**
     * Verifie si un joueur à plus de 9 points, si oui, la partie est terminé
     * @param joueurs liste de joueurs
     * @return <code>true</code> si un des joueurs à plus de 9pts
     */
    public boolean checkFinDeJeu(Joueur... joueurs){
        for(Joueur joueur : joueurs){
            if(joueur.getPoint() >= 9){
                return true;
            }
        }
        return false;
    }

    /**
     * Renvoie le joueur Gagnant de la partie
     * @param joueurs Liste de joueurs
     * @return Le gagnant de la partie
     */
    public Optional<Joueur> joueurGagnant(Joueur... joueurs){
        int occurenceMaxPoint = 1;
        int indiceJoueurMax = 0;
        for(int i = 1;i< joueurs.length;i++){
            int pointJoueurAvecPlusDePoints = joueurs[indiceJoueurMax].getPoint();
            int pointJoueurIndiceI = joueurs[i].getPoint();

            if(pointJoueurAvecPlusDePoints < pointJoueurIndiceI){
                indiceJoueurMax = i;
                occurenceMaxPoint = 1;
            }
            else if(pointJoueurAvecPlusDePoints == pointJoueurIndiceI){
                occurenceMaxPoint++;
            }
        }
        return (occurenceMaxPoint > 1)? Optional.empty() : Optional.of(joueurs[indiceJoueurMax]);
    }

    /**
     * Méthode qui vérifie si l'objectif a été terminé
     * @param listParcellesEtVoisines Liste de parcelles et voisines
     * @param objectifParcelle
     * @return <code>true</code> si l'objectif est terminé sinon <code>false</code>
     */
    public boolean checkObjectifParcelleTermine(Parcelle[] listParcellesEtVoisines, ObjectifParcelle objectifParcelle){
        int nombreParcellePlateau = listParcellesEtVoisines.length;
        return objectifParcelle.getNombreParcelles() <=  nombreParcellePlateau - objectifParcelle.getNombreParcellePresenteEnJeu();
    }

    /**
     * Méthode permettant d'évoluer le nombre de tour
     */
    public void addTour(){
        nombreTour++;
    }

    // Accesseurs

    /**
     * Permet de renvoyer à quelle tour on est
     * @return le Nombre de tour
     */
    public int getNombreTour(){
        return nombreTour;
    }
}
