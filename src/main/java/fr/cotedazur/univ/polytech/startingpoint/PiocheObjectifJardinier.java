package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe permettant de gérer la pioche des cartes objectif de jardinier
 * @author équipe N
 */
public class PiocheObjectifJardinier extends ArrayList<ObjectifJardinier> implements PiocheObjectifInterface {
    // Définition des attributs
    private final Random random;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheObjectifJardinier(Random random) {
        creePiocheObjectifsJardinier();
        this.random = random;
    }

    /**
     * Permet d'initialiser la pioche en lui ajoutant les objectifs de jardinier
     */
    private void creePiocheObjectifsJardinier() {
        add(new ObjectifJardinier(5, 4));
        add(new ObjectifJardinier(6, 4));
        add(new ObjectifJardinier(6, 6));
        add(new ObjectifJardinier(7, 9));
        add(new ObjectifJardinier(8, 12));
        add(new ObjectifJardinier(4, 4));
        add(new ObjectifJardinier(4, 4));
        add(new ObjectifJardinier(5, 4));
        add(new ObjectifJardinier(4, 4));
        add(new ObjectifJardinier(5, 4));
        add(new ObjectifJardinier(5, 4));
        add(new ObjectifJardinier(6, 4));
        add(new ObjectifJardinier(6, 4));
        add(new ObjectifJardinier(7, 4));
        add(new ObjectifJardinier(3, 4));
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
    public Objectif pioche() {
        assert !isEmpty() : "La pioche d'objectifs de jardinier est vide";
        int positionCarte = random.nextInt(size());
        if (positionCarte < 0 || positionCarte >= size()) throw new ArithmeticException("Erreur objet random");
        return remove(positionCarte);
    }
}
