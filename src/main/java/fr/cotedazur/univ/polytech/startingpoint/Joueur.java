package fr.cotedazur.univ.polytech.startingpoint;

import java.util.List;
import java.util.Optional;

/**
 * Classe du joueur qui effectue des actions
 * @author equipe N
 * @version 2.0
 */

public class Joueur {
    private String nom;
    private Optional<ObjectifParcelle> objectifParcelle;
    private int nombreDePoint;

    /**
     * Construction du joueur
     * @param nom Le nom du joueur
     */

    public Joueur(String nom){
        this.nom = nom;
        objectifParcelle = Optional.empty();
    }

    /**
     * Ajoute un objectif au joueur
     * @param objectifParcelle objectif a ajouter
     */
    public void addObjectif(ObjectifParcelle objectifParcelle){
        if(this.objectifParcelle.isEmpty()){
            this.objectifParcelle = Optional.of(objectifParcelle);
        }
    }

    /**
     * Permet au joueur de choisir une action (en ce moment de poser une parcelle) et renvoie un message de l'action faite
     * @return Renvoie le message de l'action effectuee
     */
    public String choisiAction(){
        String action = placeParcelleCouleur();
        return action;
    }

    /**
     * Place une parcelle autour de l'etang et renvoie le message de la parcelle placee
     * @return Renvoie le message de quelle parcelle a ete place
     */
    private String placeParcelleCouleur(){
        List<Position> listPositionDisponible = Jeu.plateau.getPositionDisponible();
        int nombreAleatoire = (int) (Math.random() * listPositionDisponible.size());
        ParcelleCouleur parcelleCouleur = new ParcelleCouleur(listPositionDisponible.get(nombreAleatoire));
        Jeu.plateau.addParcelle(parcelleCouleur);
        String message = "Le joueur "+ nom + " a ajoute une Parcelle a la position " + parcelleCouleur.getPosition();
        char avancee = avanceeObjectif(parcelleCouleur);
        return message + messageObjectif(avancee);
    }

    /**
     * Methode qui permet d'avancer l'objectif et donne un etat d'avancement
     * @param parcelle a verifier pour l'avancement
     * @return Retourne l'avancement de l'objectif
     */
    private char avanceeObjectif(Parcelle parcelle){
        if(objectifParcelle.isPresent()){
            ObjectifParcelle objectif = objectifParcelle.get();
            if(objectif.avanceObjectif(parcelle)) {
                if (objectif.checkObjectifTermine()) {
                    nombreDePoint += objectif.getPoint();
                    return 't';
                }
                else return 'a';
            }
        }
        return ' ';
    }

    /**
     * Renvoie le message de l'avancee d'un objectif ou la fin d'un objectif
     * @return Retourne le message de l'avancee d'un objectif ou la fin d'un objectif
     */
    private String messageObjectif(char status){
        if(objectifParcelle.isPresent()){
            if(status == 't'){
                String message = objectifParcelle.get().toString();
                objectifParcelle = Optional.empty();
                return "\n" + message + "est termine !\nLe nombre de points du joueur est de " + nombreDePoint;
            }
            else if(status == 'a'){
                return "\n" + objectifParcelle.get().etatObjectif();
            }
        }
        return "";
    }

    /**
     * Getter du Nom
     * @return Renvoie son Nom
     */

    public String getNom(){
        return nom;
    }

    /**
     * Setter du Nom
     * @param nom le nouveau nom du Joueur
     */
    public void setNom(String nom){
        this.nom = nom;
    }
}
