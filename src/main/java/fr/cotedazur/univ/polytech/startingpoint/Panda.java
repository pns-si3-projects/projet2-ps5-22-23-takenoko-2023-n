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

    public boolean deplacementPossible(Position position) {
        if (position.equals(this.position)) return false;
        int x = position.getX();
        int y = position.getY();
        return deplacementHorizontal(x, y) || deplacementDiagonaleDroite(x, y) || deplacementDiagonaleGauche(x, y);
    }

    /**
     * Renvoie si le deplacement horizontal est possible en ne regardant que les coordonnees
     * @param x est l'attribut x d'une position
     * @param y est l'attribut y d'une position
     * @return si le deplacement horizontal est possible en ne regardant que les coordonnees
     */
    private boolean deplacementHorizontal(int x, int y) {
        if (y == position.getY()) {
            return (x % 2 == position.getX() % 2) || (x % 2 == -position.getX() % 2);
        }
        return false;
    }

    /**
     * Renvoie si le deplacement en diagonale droite est possible en ne regardant que les coordonnees
     * @param x est l'attribut x d'une position
     * @param y est l'attribut y d'une position
     * @return si le deplacement en diagonale droite est possible en ne regardant que les coordonnees
     */
    private boolean deplacementDiagonaleDroite(int x, int y) {
        int xPanda = Math.abs(position.getX());
        int yPanda = Math.abs(position.getY());
        if (x < 0 && y < 0) {
            x = -x;
            y = -y;
        }
        return (xPanda - x == yPanda - y);
    }
    /**
     * Renvoie si le deplacement en diagonale gauche est possible en ne regardant que les coordonnees
     * @param x est l'attribut x d'une position
     * @param y est l'attribut y d'une position
     * @return si le deplacement en diagonale gauche est possible en ne regardant que les coordonnees
     */
    private boolean deplacementDiagonaleGauche(int x, int y) {
        int xPanda = Math.abs(position.getX());
        int yPanda = Math.abs(position.getY());
        if (x < 0) x = -x;
        else if (y < 0) y = -y;
        return (xPanda - x == yPanda - y);
    }

    public void deplacePanda(int x, int y) {
        position.deplace(x, y);
    }
}
