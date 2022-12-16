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
     * Permet au joueur de choisir une action (en ce moment de poser une parcelle) et renvoie un message de l'action faite
     * @return Renvoie le message de l'action effectuee
     */
    public String choisiAction(){
        if(objectifParcelle.isPresent()){
            return placeParcelleCouleur();
        }
        else {
            piocheObjectif();
            return "Le joueur"+ nom +" a pioche un nouveau objectif qui vaut " + objectifParcelle.get().getPoint();
        }
    }
    public void piocheObjectif(){
        int nombreAleatoire = (int) (Math.random() * Jeu.piocheObjectifParcelle.size());
        ObjectifParcelle objectifParcelle = Jeu.piocheObjectifParcelle.get(nombreAleatoire);
        Jeu.piocheObjectifParcelle.remove(nombreAleatoire);
        this.objectifParcelle = Optional.of(objectifParcelle);
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
     * Getter de points
     * @return Renvoie les points du joueur
     */
    public int getNombreDePoint(){
        return nombreDePoint;
    }

    /**
     * Permet au joueur de deplacer le Panda sur une autre Parcelle
     * @return Renvoie le message de l'action effectuee
     */
    public String deplacePanda(){
        String action = actionDeplacePanda();
        return action;
    }

    /**
     * Renvoie une liste de Parcelle possibles pour deplacer le Panda
     * @param panda qui dit où il pourrait se deplacer
     * @param parcelles qui est la liste des choix de deplacement
     * @return une liste de Parcelle possibles pour deplacer le Panda
     */
    private List<Parcelle> getParcellesDeplacement(Panda panda, List<ParcelleEtVoisines> parcelles) {
        List<Parcelle> parcellesDeplacement = new ArrayList<>();
        for (ParcelleEtVoisines parcelle : parcelles) {
            if (panda.deplacementPossible(parcelle.getParcelleCible().getPosition())) {
                parcellesDeplacement.add(parcelle.getParcelleCible());
            }
        }
        return parcellesDeplacement;
    }

    /**
     * Deplace le Panda sur une Parcelle aléatoire dans les possibles
     * @return le message du deplacement du Panda
     */
    private String actionDeplacePanda() {
        List<ParcelleEtVoisines> parcelles =  Jeu.plateau.getParcelles();
        Panda panda = Jeu.plateau.getPanda();
        List<Parcelle> parcelleDeplacement = getParcellesDeplacement(panda, parcelles);
        int nombreAleatoire = (int) (Math.random() * parcelleDeplacement.size());
        Parcelle parcelleChoisie = parcelleDeplacement.get(nombreAleatoire);

        int x = parcelleChoisie.getPosition().getX();
        int y = parcelleChoisie.getPosition().getY();
        int xPanda = panda.getPosition().getX();
        int yPanda = panda.getPosition().getY();
        panda.deplacePanda(x - xPanda, y - yPanda);
        return "Le joueur "+ nom + " deplace le Panda sur la parcelle " + parcelleChoisie.getPosition();
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
