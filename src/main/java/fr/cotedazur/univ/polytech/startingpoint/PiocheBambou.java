package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Gère les pioches de bambous
 * @author équipe N
 */
public class PiocheBambou {
    // Définition des attributs
    private int nombreBambous;


    /**
     * Constructeur par défaut, crée une pioche de 90 sections de bambous
     */
    public PiocheBambou() {
        nombreBambous = 90;
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de sections de bambous que contient la pioche
     * @return le nombre de sections de bambous restantes dans la pioche
     */
    public int getNombreBambousRestants() {
        return nombreBambous;
    }

    /**
     * Renvoie si la pioche de bambous est vide
     * @return true la pioche est vide, false sinon
     */
    public boolean isEmpty() {
        return nombreBambous == 0;
    }

    @Override
    public String toString() {
        return "Pioche de bambous : " + getNombreBambousRestants() + " sections.";
    }


    // Méthodes d'utilisation
    /**
     * Renvoie une section de bambou désignée dans la pioche
     * @return la section de bambou piochée
     * @implNote la pioche ne doit pas être vide
     */
    public SectionBambou pioche() {
        assert !isEmpty() : "La pioche de bambous est vide";
        nombreBambous--;
        return new SectionBambou();
    }
}
