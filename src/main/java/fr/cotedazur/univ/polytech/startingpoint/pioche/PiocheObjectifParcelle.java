package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.Motif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.MotifNonValideException;
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
    private Motif motifParDefautSimple;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheObjectifParcelle(Random random) {
        try {
            motifParDefautSimple = new Motif(new ParcelleCouleur(new Position(0,0), Couleur.VERT),new ParcelleCouleur(new Position(1,1),Couleur.VERT));
        }
        catch (MotifNonValideException mNVE){
            assert false: "Ne doit pas renvoyer d'exception car ce motif ils sont tous voisin";
        }
        creePiocheObjectifsParcelle();
        this.random = random;
    }

    /**
     * Permet d'initialiser la pioche en lui ajoutant les objectifs de parcelles
     */
    private void creePiocheObjectifsParcelle() {
        add(new ObjectifParcelle(2, motifParDefautSimple));
        add(new ObjectifParcelle(3, motifParDefautSimple));
        add(new ObjectifParcelle(5, motifParDefautSimple));
        add(new ObjectifParcelle(4, motifParDefautSimple));
        add(new ObjectifParcelle(3, motifParDefautSimple));
        add(new ObjectifParcelle(3, motifParDefautSimple));
        add(new ObjectifParcelle(4, motifParDefautSimple));
        add(new ObjectifParcelle(3, motifParDefautSimple));
        add(new ObjectifParcelle(2, motifParDefautSimple));
        add(new ObjectifParcelle(2, motifParDefautSimple));
        add(new ObjectifParcelle(4, motifParDefautSimple));
        add(new ObjectifParcelle(4, motifParDefautSimple));
        add(new ObjectifParcelle(5, motifParDefautSimple));
        add(new ObjectifParcelle(4, motifParDefautSimple));
        add(new ObjectifParcelle(3, motifParDefautSimple));
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
