package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Optional;

/**
 * Classe qui permet de donner le gagnant, valider un objectif ainsi que le nombre de tours
 * @author équipe N
 */
public class Arbitre {
    // Définition des attributs
    private int nombreTour;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     */
    public Arbitre() {
        nombreTour = 1;
    }


    // Accesseurs
    /**
     * Renvoie le nombre de tours actuel
     * @return le nombre de tours actuel
     */
    public int getNombreTour() {
        return nombreTour;
    }


    // Méthodes d'utilisation
    /**
     * Vérifie si la partie est terminée, soit si un joueur a plus de 9 points
     * @param joueurs la liste des joueurs
     * @return <code>true</code> si un des joueurs a plus de 9 points, <code>false</code> sinon
     */
    public boolean checkFinDeJeu(Joueur... joueurs) {
        for (Joueur joueur : joueurs) {
            if (joueur.getPoints() >= 9) {
                return true;
            }
        }
        return false;
    }

    /**
     * Renvoie le joueur gagnant de la partie
     * @param joueurs la liste des joueurs
     * @return le joueur gagnant de la partie
     */
    public Optional<Joueur> joueurGagnant(Joueur... joueurs) {
        int occurenceMaxPoint = 1;
        int indiceJoueurMax = 0;
        for(int i = 1;i< joueurs.length;i++){
            int pointJoueurAvecPlusDePoints = joueurs[indiceJoueurMax].getPoint();
            int pointJoueurIndiceI = joueurs[i].getPoint();

            if (pointJoueurAvecPlusDePoints < pointJoueurIndiceI) {
                indiceJoueurMax = i;
                occurenceMaxPoint = 1;
            }
            else if (pointJoueurAvecPlusDePoints == pointJoueurIndiceI) {
                occurenceMaxPoint++;
            }
        }
        return (occurenceMaxPoint > 1) ? Optional.empty() : Optional.of(joueurs[indiceJoueurMax]);
    }

    /**
     * Vérifie si l'objectif est terminé
     * @param listParcellesEtVoisines la liste des parcelles et voisines
     * @param objectifParcelle est l'objectif de parcelle à vérifier
     * @return <code>true</code> si l'objectif est terminé, <code>false</code> sinon
     */
    public boolean checkObjectifParcelleTermine(Parcelle[] listParcellesEtVoisines, ObjectifParcelle objectifParcelle) {
        int nombreParcellePlateau = listParcellesEtVoisines.length;
        return objectifParcelle.getNombreParcelles() <=  nombreParcellePlateau - objectifParcelle.getNombreParcellePresenteEnJeu();
    }

    /**
     * Augmente le nombre de tours
     */
    public void addTour() {
        nombreTour++;
    }
}
