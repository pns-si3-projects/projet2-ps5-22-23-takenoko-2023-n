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
    private Motif motifDiagonale;
    private Motif motifTriangle;
    private Motif motifLosange;
    private Motif motifDecale;


    // Définition des constructeurs

    /**
     * Construit la pioche d'objectifs de parcelles
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheObjectifParcelle(@NotNull Random random) {
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0, 0), Couleur.VERTE);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.VERTE);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), Couleur.VERTE);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.VERTE);
        objectifParcelleList = new ArrayList<>(15);
        motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        creePiocheObjectifsParcelle();

        this.random = random;
    }

    /**
     * Initialise la pioche en lui ajoutant les objectifs de parcelles
     */
    private void creePiocheObjectifsParcelle() {
        objectifParcelleList.add(new ObjectifParcelle(2, motifTriangle));
        objectifParcelleList.add(new ObjectifParcelle(3, motifDiagonale));
        objectifParcelleList.add(new ObjectifParcelle(5, motifLosange));
        objectifParcelleList.add(new ObjectifParcelle(4, motifDecale));
        objectifParcelleList.add(new ObjectifParcelle(3, motifDiagonale));
        objectifParcelleList.add(new ObjectifParcelle(3, motifDiagonale));
        objectifParcelleList.add(new ObjectifParcelle(4, motifDecale));
        objectifParcelleList.add(new ObjectifParcelle(3, motifDiagonale));
        objectifParcelleList.add(new ObjectifParcelle(2, motifTriangle));
        objectifParcelleList.add(new ObjectifParcelle(2, motifTriangle));
        objectifParcelleList.add(new ObjectifParcelle(4, motifDecale));
        objectifParcelleList.add(new ObjectifParcelle(4, motifDecale));
        objectifParcelleList.add(new ObjectifParcelle(5, motifLosange));
        objectifParcelleList.add(new ObjectifParcelle(4, motifDecale));
        objectifParcelleList.add(new ObjectifParcelle(3, motifDiagonale));
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
        assert !isEmpty() : "La pioche d'objectifs de parcelles est vide";

        int taille = getNombreObjectifsRestants();
        int positionCarte = random.nextInt(taille);
        if (positionCarte < 0 || positionCarte >= taille) {
            throw new ArithmeticException("Erreur objet random");
        }

        return objectifParcelleList.remove(positionCarte);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'objectifs de parcelles : " + getNombreObjectifsRestants() + " objectifs";
    }
}
