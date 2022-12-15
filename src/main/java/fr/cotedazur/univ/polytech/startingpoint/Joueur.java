package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * Classe du joueur qui effectue des actions
 * @author equipe N
 * @version 1.0
 */

public class Joueur {
    private String nom;
    private ObjectifParcelle objectifParcelle;

    /**
     * Construction du joueur
     * @param nom Le nom du joueur
     */

    public Joueur(String nom){
        this.nom = nom;
    }

    public void addObjectif(ObjectifParcelle objectifParcelle){
        this.objectifParcelle = objectifParcelle;
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
        return "Le joueur "+ nom + " a ajoute une Parcelle a la position " + parcelleCouleur.getPosition();
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
