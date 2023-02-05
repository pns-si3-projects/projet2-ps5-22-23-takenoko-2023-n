package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.*;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe permettant de gérer la pioche des cartes objectif de parcelles
 * @author équipe N
 */
public class PiocheObjectifParcelle extends ArrayList<ObjectifParcelle> implements PiocheObjectifInterface {
    // Définition des attributs
    private final Random random;
    private Motif motifDiagonale;
    private Motif motifTriangle;
    private Motif motifLosange;

    private Motif motifDecale;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheObjectifParcelle(Random random) {
        ParcelleCouleur parcelleCouleur00 = new ParcelleCouleur(new Position(0,0), Couleur.VERT);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1,1), Couleur.VERT);
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.VERT);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), Couleur.VERT);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1,-1), Couleur.VERT);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), Couleur.VERT);
        motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        motifTriangle = new MotifTriangle(parcelleCouleur00, parcelleCouleur20, parcelleCouleur11);
        motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        motifDecale = new MotifDecale(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        creePiocheObjectifsParcelle();
        this.random = random;
    }

    /**
     * Permet d'initialiser la pioche en lui ajoutant les objectifs de parcelles
     */
    private void creePiocheObjectifsParcelle() {
        add(new ObjectifParcelle(3, motifTriangle));
        add(new ObjectifParcelle(3, motifTriangle));
        add(new ObjectifParcelle(3, motifTriangle));
        add(new ObjectifParcelle(2, motifDiagonale));
        add(new ObjectifParcelle(2, motifDiagonale));
        add(new ObjectifParcelle(2, motifDiagonale));
        add(new ObjectifParcelle(4, motifDecale));
        add(new ObjectifParcelle(4, motifDecale));
        add(new ObjectifParcelle(4, motifDecale));
        add(new ObjectifParcelle(5, motifLosange));
        add(new ObjectifParcelle(5, motifLosange));
        add(new ObjectifParcelle(5, motifLosange));
        add(new ObjectifParcelle(5, motifLosange));
        add(new ObjectifParcelle(5, motifLosange));
        add(new ObjectifParcelle(5, motifLosange));
    }


    // Accesseurs et méthode toString
    @Override
    public int getNombreObjectifsRestants() {
        return size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return "Pioche d'objectifs de parcelles : " + size() + " cartes.";
    }


    // Méthodes d'utilisation
    @Override
    public ObjectifParcelle pioche() {
        assert !isEmpty() : "La pioche d'objectifs de parcelles est vide";
        int positionCarte = random.nextInt(size());
        if (positionCarte < 0 || positionCarte >= size()) throw new ArithmeticException("Erreur objet random");
        return remove(positionCarte);
    }
}
