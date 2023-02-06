package fr.cotedazur.univ.polytech.startingpoint.pioche;

public class PiocheIrrigation {
    // Définition des attributs

    private int nombreIrrigation;


    // Définition des constructeurs

    /**
     * Construit une pioche de 20 irrigations
     */
    public PiocheIrrigation() {
        nombreIrrigation = 20;
    }


    // Accesseurs

    /**
     * Renvoie le nombre d'irrigations que contient la pioche
     * @return le nombre d'irrigations restantes dans la pioche
     */
    public int getNombreIrrigationsRestantes() {
        return nombreIrrigation;
    }

    /**
     * Renvoie si la pioche d'irrigation est vide
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmpty() {
        return nombreIrrigation == 0;
    }


    // Méthodes d'utilisation

    /**
     * Renvoie une irrigation de la pioche
     */
    public void pioche(){
        nombreIrrigation--;
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'irrigation : " + nombreIrrigation + " irrigations";
    }
}
