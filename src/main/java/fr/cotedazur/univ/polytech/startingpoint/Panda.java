package fr.cotedazur.univ.polytech.startingpoint;

public class Panda {
    private final Position position;

    /**
     * Constructeur par defaut
     * Lors de l'initialisation du Jeu, le panda est forcement place sur l'Etang soit en (0;0)
     */
    public Panda() {
        this.position = new Position();
    }

    /**
     * Renvoie la position du Panda
     * @return la position du Panda
     */
    public Position getPosition() {
        return position;
    }
}
