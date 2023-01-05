package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe permettant de gérer la pioche des cartes objectif de parcelles
 * @author équipe N
 */
public class PiocheObjectifParcelle extends ArrayList<ObjectifParcelle> implements PiocheObjectifInterface {
    // Définition des attributs
    private final Random random;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheObjectifParcelle(Random random) {
        creePiocheObjectifsParcelle();
        this.random = random;
    }

    /**
     * Permet d'initialiser la pioche en lui ajoutant les objectifs de parcelles
     */
    private void creePiocheObjectifsParcelle() {
        add(new ObjectifParcelle(2, 3));
        add(new ObjectifParcelle(3, 4));
        add(new ObjectifParcelle(5, 4));
        add(new ObjectifParcelle(4, 4));
        add(new ObjectifParcelle(3, 4));
        add(new ObjectifParcelle(3, 3));
        add(new ObjectifParcelle(4, 4));
        add(new ObjectifParcelle(3, 3));
        add(new ObjectifParcelle(2, 3));
        add(new ObjectifParcelle(2, 3));
        add(new ObjectifParcelle(4, 3));
        add(new ObjectifParcelle(4, 3));
        add(new ObjectifParcelle(5, 4));
        add(new ObjectifParcelle(4, 3));
        add(new ObjectifParcelle(3, 3));
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
