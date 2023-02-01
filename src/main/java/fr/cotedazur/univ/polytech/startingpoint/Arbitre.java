package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotif;
import fr.cotedazur.univ.polytech.startingpoint.motif.Motif;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifDiagonale;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.plateau.SectionBambou;

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
            if (joueur.getNombreObjectifsTermines() >=7) {
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
     * Convertit le tableau de Parcelle en tableau de Parcelle Couleur pour faire un motif en vérifiant avant que le motif contient que des parcelles Couleur via la méthode checkMotifComplet
     * @param listParcelleMotif La liste des Parcelle pour créer le motif
     * @return Le nouveau motif avec le tableau de Parcelle
     */
    private Motif convertTabInMotifDiagonale(Parcelle[] listParcelleMotif) {
        ParcelleCouleur[] parcelleCouleursMotifs = new ParcelleCouleur[listParcelleMotif.length];
        for (int i = 0; i< listParcelleMotif.length; i++) {
            parcelleCouleursMotifs[i] = (ParcelleCouleur) listParcelleMotif[i];
        }
        return new MotifDiagonale(parcelleCouleursMotifs);
    }

    /**
     * Méthode qui vérifie si l'objectif a été terminé
     * @param listParcellesEtVoisines le tableau de parcelles et voisines
     * @param objectifParcelle l'objectif à vérifier
     * @return <code>true</code> si l'objectif est terminé, <code>false</code> sinon
     */
    public boolean checkObjectifParcelleTermine(Parcelle[] listParcellesEtVoisines, ObjectifParcelle objectifParcelle) {
        Parcelle parcelleAChercher = GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(listParcellesEtVoisines, objectifParcelle);
        Parcelle[] parcelleMotif = GestionnairePossibiliteMotif.getMotifAFaire(listParcellesEtVoisines, parcelleAChercher, objectifParcelle.getMotif().getTableauParcelles());

        if ( GestionnairePossibiliteMotif.checkMotifComplet(parcelleMotif) ) { // La méthode vérifie si c'est que des parcelles couleurs
            Motif motifAVerifier = convertTabInMotifDiagonale(parcelleMotif); // Donc aucun risque sur le Cast
            return objectifParcelle.getMotif().equals(motifAVerifier);
        }
        return false;
    }

    /**
     * Renvoie <code>true</code> si la couleur de toutes les section de bambous sont de même couleur
     * @param sectionBambous Le tableau des sections de bambous
     * @param couleur La couleur des sections de bambous
     * @return <code>true</code> si la couleur de toutes les section de bambous sont de même couleur
     */
    private boolean verifieCouleurBambou(SectionBambou[] sectionBambous, Couleur couleur) {
        for (SectionBambou sectionBambou : sectionBambous) {
            if (sectionBambou.couleur() != couleur) return false;
        }
        return true;
    }

    /**
     * Renvoie <code>true</code> si l'objectif Panda est terminé
     * @param sectionBambous Le tableau des sections de Bambous
     * @param objectifPanda L'objectif Panda à faire
     * @return <code>true</code> si l'objectif Panda est terminé
     */
    public boolean checkObjectifPandaTermine(SectionBambou[] sectionBambous, ObjectifPanda objectifPanda) {
        if ( !verifieCouleurBambou(sectionBambous,objectifPanda.getCouleurBambousAManger()) ) return false;
        return sectionBambous.length >= objectifPanda.getNombreBambousAManger();
    }

    /**
     * Renvoie <code>true</code> si l'objectif Jardinier est terminé
     * @param objectifJardinier L'objectif Jardinier à faire
     * @return <code>true</code> si l'objectif Jardinier est terminé
     */
    public boolean checkObjectifJardinierTermine(ObjectifJardinier objectifJardinier){
        return objectifJardinier.getNombreBambousRestant() <= 0;
    }

    /**
     * Permet d'augmenter le nombre de tours
     */
    public void addTour() {
        nombreTour++;
    }
}
