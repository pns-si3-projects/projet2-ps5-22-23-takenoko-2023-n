package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
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
     * Cherche la Parcelle dans le plateau si elle existe
     * @param listParcelleEtVoisine Les parcelles du plateau
     * @param positionAChercher La position de la parcelle A chercher
     * @return la parcelle si elle existe dans le plateau
     */
    private Optional<Parcelle> getParcelleATrouver(Parcelle[] listParcelleEtVoisine, Position positionAChercher){
        if(positionAChercher.equals(new Position())) return Optional.empty(); // Si la position est l'étang

        for (Parcelle parcelle : listParcelleEtVoisine) {
            if (parcelle.position().equals(positionAChercher)) {
                return Optional.of(parcelle);
            }
        }
        return Optional.empty();
    }

    /**
     * Méthode permettant de créer un motif si c'est possible via une Parcelle et la liste de ParcelleEtVoisine
     * @param listParcelleEtVoisine la liste de ParcelleEtVoisine
     * @param parcelleCible La parcelle avec laquelle on cherche a faire un motif
     * @param motifAFaire Le motif qu'on cherche a faire
     * @return un motif si il est possible d'en faire un
     */
    public Optional<Motif> creeMotif(Parcelle[] listParcelleEtVoisine, Parcelle parcelleCible, Motif motifAFaire){
        ParcelleCouleur[] tableauParcelleMotif = motifAFaire.getTableauParcelles();
        ParcelleCouleur[] motifACreer = new ParcelleCouleur[tableauParcelleMotif.length];
        motifACreer[0] = (ParcelleCouleur) parcelleCible;

        for (int i = 1; i < tableauParcelleMotif.length; i++ ) {
            int differenceX = tableauParcelleMotif[0].position().getX() - tableauParcelleMotif[i].position().getX();
            int differenceY = tableauParcelleMotif[0].position().getY() - tableauParcelleMotif[i].position().getY();
            Position positionParcelleCible = parcelleCible.position();
            Position positionAChercher = new Position(positionParcelleCible.getX() + differenceX,positionParcelleCible.getY() + differenceY);

            Optional<Parcelle> optParcelleTrouver = getParcelleATrouver(listParcelleEtVoisine,positionAChercher);
            if(optParcelleTrouver.isPresent()) {
                motifACreer[i] = (ParcelleCouleur) optParcelleTrouver.get();
            }
            else {
                return Optional.empty();
            }
        }

        try {
            Motif motifCree = new Motif(motifACreer);
            return Optional.of(motifCree);
        }
        catch (MotifNonValideException mNVE){
            assert false: "Ne dois pas renvoyer d'erreur normalement";
            return Optional.empty();
        }
    }

    /**
     * Méthode qui vérifie si l'objectif a été terminé
     * @param listParcellesEtVoisines la liste de parcelles et voisines
     * @param objectifParcelle l'objectif à vérifier
     * @return <code>true</code> si l'objectif est terminé, <code>false</code> sinon
     */
    public boolean checkObjectifParcelleTermine(Parcelle[] listParcellesEtVoisines, ObjectifParcelle objectifParcelle) {
        Motif motifATrouver = objectifParcelle.getMotif();

        for (Parcelle parcelle : listParcellesEtVoisines) {
            Optional<Motif> optMotif = creeMotif(listParcellesEtVoisines,parcelle,motifATrouver);

            if (optMotif.isPresent()){
                if(optMotif.get().equals(motifATrouver)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkObjectifPandaTermine(SectionBambou[] sectionBambous, ObjectifPanda objectifPanda){
        if ( !verifieCouleurBambou(sectionBambous,objectifPanda.getCouleurBambousAManger()) ) return false;
        return sectionBambous.length >= objectifPanda.getNombreBambousAManger();
    }

    public boolean checkObjectifJardinierTermine(ObjectifJardinier objectifJardinier){
        return objectifJardinier.getNombreBambousRestant() <= 0;
    }

    private boolean verifieCouleurBambou(SectionBambou[] sectionBambous, Couleur couleur){
        for (SectionBambou sectionBambou : sectionBambous) {
            if (sectionBambou.couleur() != couleur) return false;
        }
        return true;
    }

    /**
     * Permet d'augmenter le nombre de tours
     */
    public void addTour() {
        nombreTour++;
    }
}
