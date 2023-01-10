package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;

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

    /**
     * Vérifie si la partie est terminée, soit si un joueur a plus de 9 points
     * @param joueurs la liste des joueurs
     * @return <code>true</code> si un des joueurs a plus de 9 points, <code>false</code> sinon
     */
    public boolean verifieFinDeJeu(Joueur... joueurs) {
        for (Joueur joueur : joueurs) {
            if (joueur.getPoints() >= 9) { // À modifier par le nombre d'objectifs
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
        for (int i = 1; i< joueurs.length; i++) {
            int pointJoueurAvecPlusDePoints = joueurs[indiceJoueurMax].getPoints();
            int pointJoueurIndiceI = joueurs[i].getPoints();

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
     * Méthode qui vérifie si l'objectif a été terminé
     * @param listParcellesEtVoisines la liste de parcelles et voisines
     * @param objectifParcelle l'objectif à vérifier
     * @return <code>true</code> si l'objectif est terminé, <code>false</code> sinon
     */
    public boolean checkObjectifParcelleTermine(Parcelle[] listParcellesEtVoisines, ObjectifParcelle objectifParcelle) {
        int nombreParcellePlateau = listParcellesEtVoisines.length;
        return objectifParcelle.getNombreParcelles() <= nombreParcellePlateau - objectifParcelle.getNombreParcellePresenteEnJeu();
    }

    public boolean checkObjectifPandaTermine(SectionBambou[] sectionBambous, ObjectifPanda objectifPanda){
        return sectionBambous.length >= objectifPanda.getNombreBambousAManger();
    }

    /**
     * Permet d'augmenter le nombre de tours
     */
    public void addTour() {
        nombreTour++;
    }
}
