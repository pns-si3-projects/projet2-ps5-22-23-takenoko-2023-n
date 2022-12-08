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

    /**
     * Construction du joueur
     * @param nom Le nom du joueur
     */

    public Joueur(String nom){
        this.nom = nom;
    }

    /**
     * Permet au joueur de choisir une action (en ce moment de poser une parcelle) et renvoie un message de l'action faite
     * @return Renvoie le message de l'action effectuee
     */
    public String choisiAction(){
        String message = placeParcelleCouleur();
        return message;
    }

    /**
     * Place une parcelle autour de l'etang et renvoie le message de la parcelle placee
     * @return Renvoie le message de quelle parcelle a ete place
     */
    private String placeParcelleCouleur(){
        List<Position> listPositionDisponible = ajoutPositionDisponibleEtang();
        int nombreAleatoire = (int) (Math.random() * listPositionDisponible.size());
        ParcelleCouleur parcelleCouleur = new ParcelleCouleur(listPositionDisponible.get(nombreAleatoire));
        Parcelle etang = Jeu.plateau.getParcelles().get(0).getParcelleCible();
        List<Parcelle> listParcelleVoisine = new ArrayList<>();
        listParcelleVoisine.add(etang);
        Jeu.plateau.addParcelle(parcelleCouleur,listParcelleVoisine);
        return "Le joueur "+ nom + " a ajoute une Parcelle a la position " + parcelleCouleur.getPosition();
    }

    /**
     * Ajoute les positions disponible autour de l'etang (methode temporaire)
     * @return Renvoie une liste de position disponible autour de l'etang
     */

    private List<Position> ajoutPositionDisponibleEtang(){
        List<Position> listPositionDisponible = new ArrayList<>();
        listPositionDisponible.add(new Position(2,0));
        listPositionDisponible.add(new Position(1,1));
        listPositionDisponible.add(new Position(-1,1));
        listPositionDisponible.add(new Position(-2,0));
        listPositionDisponible.add(new Position(-1,-1));
        listPositionDisponible.add(new Position(1,-1));
        return  listPositionDisponible;
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
