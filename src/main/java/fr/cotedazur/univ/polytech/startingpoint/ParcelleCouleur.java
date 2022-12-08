package fr.cotedazur.univ.polytech.startingpoint;

public class ParcelleCouleur {
    private final Position position;

    /**
     * Constructeur de la classe ParcelleCouleur
     * @param p position unique de la parcelle
     */
    public ParcelleCouleur(Position p){
        if(p == null) assert false : "Ne doit pas etre vide";
        position = p;
    }

    /**
     * Renvoie la position de la parcelle
     * @return Retourne la position de la parcelle
     */
    public Position getPosition() {
        return position;
    }
}
