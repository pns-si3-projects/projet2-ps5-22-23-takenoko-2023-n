package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.*;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente la pioche des objectifs de parcelles.
 * @author équipe N
 */
public class PiocheObjectifParcelle implements PiocheObjectifInterface {
    // Définition des attributs

    private final List<ObjectifParcelle> objectifParcelleList;
    private final Random random;


    // Définition des constructeurs

    /**
     * Construit la pioche d'objectifs de parcelles
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheObjectifParcelle(@NotNull Random random) {
        objectifParcelleList = new ArrayList<>();

        creeObjectifMotifVert();
        creeObjectifMotifJaune();
        creeObjectifMotifRose();
        creeObjectifMotifDeuxCouleurs();

        this.random = random;
    }

    /**
     * Crée les objectifs de motifs de couleur verte
     */
    private void creeObjectifMotifVert() {
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), Couleur.VERTE);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.VERTE);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.VERTE);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), Couleur.VERTE);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE);
        Motif motifT = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Motif motifL = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Motif motifD = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Motif motifV = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);

        objectifParcelleList.add(new ObjectifParcelle(2, motifT));
        objectifParcelleList.add(new ObjectifParcelle(3, motifL));
        objectifParcelleList.add(new ObjectifParcelle(2, motifD));
        objectifParcelleList.add(new ObjectifParcelle(2, motifV));
    }

    /**
     * Crée les objectifs de motifs de couleur jaune
     */
    private void creeObjectifMotifJaune() {
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.JAUNE);
        Motif motifT = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Motif motifL = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Motif motifD = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Motif motifV = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);

        objectifParcelleList.add(new ObjectifParcelle(3, motifD));
        objectifParcelleList.add(new ObjectifParcelle(4, motifL));
        objectifParcelleList.add(new ObjectifParcelle(3, motifV));
        objectifParcelleList.add(new ObjectifParcelle(3, motifT));
    }

    /**
     * Crée les objectifs de motifs de couleur rose
     */
    private void creeObjectifMotifRose() {
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), Couleur.ROSE);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.ROSE);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.ROSE);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), Couleur.ROSE);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.ROSE);
        Motif motifT = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Motif motifL = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Motif motifD = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Motif motifV = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);

        objectifParcelleList.add(new ObjectifParcelle(4, motifV));
        objectifParcelleList.add(new ObjectifParcelle(4, motifT));
        objectifParcelleList.add(new ObjectifParcelle(5, motifL));
        objectifParcelleList.add(new ObjectifParcelle(4, motifD));
    }

    /**
     * Crée les objectifs de motifs de deux couleurs
     */
    private void creeObjectifMotifDeuxCouleurs() {
        ParcelleCouleur parcelleCouleur00R = new ParcelleCouleur(new Position(0, 0), Couleur.ROSE);
        ParcelleCouleur parcelleCouleur11V = new ParcelleCouleur(new Position(1, 1), Couleur.VERTE);
        ParcelleCouleur parcelleCouleurm11R = new ParcelleCouleur(new Position(-1, 1), Couleur.ROSE);
        ParcelleCouleur parcelleCouleur02V = new ParcelleCouleur(new Position(0, 2), Couleur.VERTE);
        ParcelleCouleur parcelleCouleur00J = new ParcelleCouleur(new Position(0, 0), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleur11R = new ParcelleCouleur(new Position(1, 1), Couleur.ROSE);
        ParcelleCouleur parcelleCouleurm11J = new ParcelleCouleur(new Position(-1, 1), Couleur.JAUNE);
        ParcelleCouleur parcelleCouleur02R = new ParcelleCouleur(new Position(0, 2), Couleur.ROSE);
        Motif motifLJR = new MotifLosange(parcelleCouleur00J, parcelleCouleur11R, parcelleCouleurm11J, parcelleCouleur02R);
        Motif motifLRV = new MotifLosange(parcelleCouleur00R, parcelleCouleur11V, parcelleCouleurm11R, parcelleCouleur02V);
        Motif motifLJV = new MotifLosange(parcelleCouleur00J, parcelleCouleur11V, parcelleCouleurm11J, parcelleCouleur02V);

        objectifParcelleList.add(new ObjectifParcelle(5, motifLJR));
        objectifParcelleList.add(new ObjectifParcelle(4, motifLRV));
        objectifParcelleList.add(new ObjectifParcelle(3, motifLJV));
    }


    // Accesseurs

    @Override
    public int getNombreObjectifsRestants() {
        return objectifParcelleList.size();
    }

    @Override
    public boolean isEmpty() {
        return objectifParcelleList.isEmpty();
    }


    // Méthodes d'utilisation

    @Override
    public ObjectifParcelle pioche() {
        if (isEmpty()) {
            throw new AssertionError("La pioche d'objectifs de parcelles est vide");
        }

        int taille = getNombreObjectifsRestants();
        int positionCarte = random.nextInt(taille);
        if (positionCarte < 0 || positionCarte >= taille) {
            throw new ArithmeticException("Erreur objet random");
        }

        AfficheurPioche.piocheObjectif(objectifParcelleList.get(positionCarte));
        return objectifParcelleList.remove(positionCarte);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'objectifs de parcelles : " + getNombreObjectifsRestants() + " objectifs";
    }
}
