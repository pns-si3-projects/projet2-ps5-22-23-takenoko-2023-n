package fr.cotedazur.univ.polytech.startingpoint.pioche;

import java.util.Random;

public class PiocheIrrigation {
    private int nombreIrrigation;
    private final Random random;

    // Définition des constructeurs
    /**
     * Constructeur par défaut, crée une pioche de 20 irrigation
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheIrrigation(Random random) {
        if (random == null) throw new NullPointerException("L'object random ne doit pas être null");
        this.nombreIrrigation = 20;
        this.random = random;
    }

    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre d'irrigation que contient la pioche
     * @return le nombre d'irrigation restantes dans la pioche
     */
    public int getNombreIrrigation() {
        return nombreIrrigation;
    }

    /**
     * Renvoie si la pioche d'irrigation est vide
     * @return <code>true</code> si la pioche est vide, <code>false</code> sinon
     */
    public boolean isEmptyIrrigation() {
        return nombreIrrigation == 0;
    }

    @Override
    public String toString() {
        return "Pioche d'irrigation' : " + nombreIrrigation +".";
    }

}
