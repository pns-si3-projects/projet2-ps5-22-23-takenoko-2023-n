package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Gère la pioche des cartes objectif de parcelles
 * @author équipe N
 */
public class PiocheObjectifParcelle extends ArrayList<ObjectifParcelle> implements PiocheObjectifInterface {
    // Définition des attributs
    private Random random;


    // Définition des constructeurs
    /**
     * Constructeur par défaut, crée la pioche des objectifs de parcelles
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheObjectifParcelle(Random random) {
        creePiocheObjectifsParcelle();
        this.random = random;
    }

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
    /**
     * Renvoie le nombre de cartes que contient la pioche
     * @return le nombre de cartes dans la pioche
     */
    @Override
    public int getNombreObjectifs() {
        return size();
    }

    @Override
    public String toString() {
        return "Pioche d'objectifs de parcelles : " + size() + " cartes.";
    }


    // Méthodes d'utilisation
    /**
     * Renvoie une carte objectif désignée dans la pioche
     * @return la carte objectif piochée
     * @implSpec la pioche ne doit pas être vide
     */
    @Override
    public Objectif pioche() {
        assert !isEmpty() : "La pioche d'objectifs de parcelles est vide";
        int positionCarte = random.nextInt(getNombreObjectifs());
        if (positionCarte < 0 || positionCarte >= size()) throw new RuntimeException();
        return remove(positionCarte);
    }
}
