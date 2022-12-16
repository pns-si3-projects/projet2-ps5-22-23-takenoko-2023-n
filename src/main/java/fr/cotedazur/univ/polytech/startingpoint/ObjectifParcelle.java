package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Permet d'ajouter un objectif Parcelle
 * @version 1.0
 * @author equipe N
 */
public class ObjectifParcelle {
    private final int point;
    private final int nombreParcelleAposer;
    private int nombreRestantFinirObjectif;

    /**
     * Constructeur par defaut d'objectifParcelle
     * @param nombreDePoint un nombre de point a mettre sur la carte
     * @param nombreParcelleAposer un nombre de parcelle a poser sur le plateau
     */
    public ObjectifParcelle(int nombreDePoint,int nombreParcelleAposer){
        point = nombreDePoint;
        this.nombreParcelleAposer = nombreParcelleAposer;
        nombreRestantFinirObjectif = nombreParcelleAposer;
    }

    /**
     * Verifie si l'objectif est termine
     * @return Retourne true si l'objectif est termine sinon false
     */
    public boolean checkObjectifTermine(){
        return nombreRestantFinirObjectif == 0;
    }

    /**
     * Si on a avance dans l'objectif alors on affiche l'avancee sinon on affiche rien
     * @param parcelle verifie si la parcelle n'est pas une fausse parcelle ajouter
     * @return Retourne le message de l'avance si il y a de l'avancee
     */
    public boolean avanceObjectif(Parcelle parcelle){
        if(parcelle == null || parcelle.getClass() != ParcelleCouleur.class) return false;
        nombreRestantFinirObjectif--;
        return true;
    }

    /**
     * Renvoie l'etat de l'objectif soit ce qu'il reste a faire
     * @return Renvoie ce qui reste a faire dans l'objectif
     */
    public String etatObjectif(){
        return "Il reste " + nombreParcelleAposer + " parcelle(s) a poser";
    }

    /**
     * Getter de point
     * @return Retourne un nombre de Point
     */
    public int getPoint(){
        return point;
    }

    @Override
    public String toString(){
        return "Objectif: "+ nombreParcelleAposer + " Parcelles a posees ";
    }
}
