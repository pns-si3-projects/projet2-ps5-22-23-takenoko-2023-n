package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Classe représentant l'ensemble des sections de bambou pouvant être placées sur une parcelle de couleur
 * @author equipe N
 */
public class Bambou implements Positionable {
    // Définition des attributs
    private final Position position;
    private final SectionBambou[] sectionsBambou;
    private int tailleBambou;
    public static final int TAILLE_MAXIMUM_BAMBOU = 4;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     */
    public Bambou(Position position) {
        this.position = position;
        sectionsBambou = new SectionBambou[TAILLE_MAXIMUM_BAMBOU];
        tailleBambou = 0;
    }


    // Accesseur et méthode toString
    /**
     * Renvoie la position du Bambou
     * @return la position du Bambou
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Renvoie le nombre de sections de bambou
     * @return le nombre de sections de bambou
     */
    public int getTailleBambou() {
        return tailleBambou;
    }

    /**
     * Renvoie la taille maximum du Bambou
     * @return la taille maximum du Bambou
     */
    public int getTailleMaximumBambou() {
        return TAILLE_MAXIMUM_BAMBOU;
    }

    /**
     * Renvoie s'il n'y a plus de sections de bambou
     * @return <code>true</code> si le Bambou n'a plus de section, <code>false</code> sinon
     */
    public boolean isEmptyBambou() {
        return tailleBambou == 0;
    }

    /**
     * Renvoie si le Bambou a atteint sa taille maximum
     * @return <code>true</code> si le Bambou a atteint sa taille maximum, <code>false</code> sinon
     */
    public boolean isTailleMaximum() {
        return tailleBambou == TAILLE_MAXIMUM_BAMBOU;
    }

    @Override
    public String toString() {
        return "Bambou de " + tailleBambou + " sections de bambou";
    }


    // Méthodes d'utilisation
    /**
     * Renvoie une section de bambou
     * @return la section de bambou
     * @implNote le Bambou doit contenir au moins une section de bambou
     */
    public SectionBambou prendSectionBambou() {
        assert !isEmptyBambou() : "Le Bambou ne contient pas de section de bambou";
        return sectionsBambou[--tailleBambou];
    }

    /**
     * Ajoute une section de bambou
     * @implNote le Bambou ne doit pas être à sa taille maximum
     */
    public void ajouteSectionBambou(SectionBambou sectionBambou) {
        assert !isTailleMaximum() : "Le Bambou a atteint sa taille maximum";
        sectionsBambou[tailleBambou++] = sectionBambou;
    }
}
