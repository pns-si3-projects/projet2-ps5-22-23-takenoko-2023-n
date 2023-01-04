package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Classe représentant le Panda
 * @author equipe N
 */
public class Panda implements Personnage {
    // Définition des attributs
    private final Position position;


    // Définition des constructeurs
    /**
     * Constructeur par défaut du panda qui commence sur l'étang
     */
    public Panda() {
        position = new Position();
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie la position du Panda
     * @return la position
     */
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Panda en " + position;
    }
}
