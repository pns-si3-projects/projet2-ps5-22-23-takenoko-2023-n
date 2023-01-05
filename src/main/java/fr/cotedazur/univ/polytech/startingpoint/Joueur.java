package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Joueur automatique capable d'ajouter une parcelle ou piocher un objectif
 * @author équipe N
 */
public class Joueur {
    // Définition des attributs
    private final String nom;
    private final Plaquette plaquette;
    // Ajouter les objectifs terminés et les accesseurs nécessaires
    private final ArrayList<Objectif> listObjectifTermine;
    private final Random random;

    // Définition des constructeurs
    /**
     * Constructeur unique du Joueur
     * @param nom est le nom du Joueur
     */
    public Joueur(String nom, Random random,ObjectifParcelle objectifParcelle) {
        this.nom = nom;
        plaquette = new Plaquette();
        listObjectifTermine = new ArrayList<>();
        this.random = random;
        plaquette.ajouteObjectif(objectifParcelle);
    }

    /**
     * Effectue le tour du joueur
     * @param piocheObjectif La pioche d'objectif pour piocher les objectifs
     * @param plateau Le plateau pour ajouter les parcelles
     */
    public void tour(PiocheObjectif piocheObjectif, Plateau plateau, Arbitre arbitre){
        actionTour(piocheObjectif,plateau,arbitre);
        actionTour(piocheObjectif,plateau,arbitre);
    }

    /**
     * Méthode permettant d'effectuer une action d'un tour
     * @param piocheObjectif La pioche d'objectif pour piocher les objectifs
     * @param plateau Le plateau pour ajouter les parcelles
     */
    public void actionTour(PiocheObjectif piocheObjectif,Plateau plateau, Arbitre arbitre){
        if(plaquette.getNombreObjectifParcelle() == 0){
            Optional<ObjectifParcelle> objectifParcellePioche = piocheObjectifParcelle(piocheObjectif);
            if(objectifParcellePioche.isPresent()){
                ObjectifParcelle objectifParcelle = objectifParcellePioche.get();
                objectifParcelle.setNombreParcellePresenteEnJeu(plateau.getParcelles().length);
                plaquette.ajouteObjectif(objectifParcelle);
            }
        }
        else{
            boolean parcelleAjoute = false;
            while (!parcelleAjoute) {
                ParcelleCouleur parcelleCouleurChoisi = choisiParcellePlateau(plateau);
                parcelleAjoute = addParcellePlateau(plateau, parcelleCouleurChoisi);
            }
            gestionObjectif(plateau.getParcelles(),arbitre, plaquette.getObjectifsParcelle());
        }
    }

    /**
     * Méthode permettant de gerer les objectifs grâce à l'arbitre
     * @param listParcellesEtVoisines La liste de parcelle du plateau à donnée à l'arbitre pour vérifier si les parcelles ont été posé
     * @param arbitre L'arbitre qui doit vérifier si l'objectif est validé
     * @param objectifsParcelles La liste des objectifs parcelles
     */
    private void gestionObjectif(Parcelle[] listParcellesEtVoisines, Arbitre arbitre, ObjectifParcelle[] objectifsParcelles){
        assert objectifsParcelles != null && objectifsParcelles.length > 0 : "La plaquette contenant les objectifs parcelles ne doit pas être vide";
        for (ObjectifParcelle objectifParcelle : objectifsParcelles) {
            if (arbitre.checkObjectifParcelleTermine(listParcellesEtVoisines, objectifParcelle)) {
                if (plaquette.deleteObjectifParcelle(objectifParcelle)) {
                    listObjectifTermine.add(objectifParcelle);
                } else {
                    assert false : "L'objectif doit normalement existe";
                }
            }
        }
    }

    /**
     * Méthode qui pioche un objectifParcelle dans la pioche
     * @param piocheObjectif La pioche d'objectif
     * @return Renvoie un optionnal d'objectif (Optionnal empty si la pioche est vide
     */
    public Optional<ObjectifParcelle> piocheObjectifParcelle(PiocheObjectif piocheObjectif){
        if(piocheObjectif.isEmptyPiocheObjectifParcelle()) {
            return Optional.empty();
        }
        else {
            ObjectifParcelle objectifParcellePioche = piocheObjectif.piocheObjectifParcelle();
            assert objectifParcellePioche != null : "L'objectif ne doit pas etre null";
            return Optional.of(objectifParcellePioche);
        }
    }

    /**
     * Méthode renvoyant le choix de la Parcelle que le joueur veut poser sur le Plateau
     * @param plateau Le plateau de jeu, pour choisir la parcelle a poser parmis les liste de Position Disponible
     * @return La parcelle que le joueur veut poser
     */
    public ParcelleCouleur choisiParcellePlateau(Plateau plateau){
        Position[] listPositionDisponiblePlateau = plateau.getPositionsDisponible();
        int nombreAleatoire = random.nextInt(listPositionDisponiblePlateau.length);
        if(nombreAleatoire < 0 || nombreAleatoire >= listPositionDisponiblePlateau.length) throw new ArithmeticException("Erreur objet random");
        Position positionChoisi = listPositionDisponiblePlateau[nombreAleatoire];
        return new ParcelleCouleur(positionChoisi);
    }

    /**
     * Ajoute la parcelle au Plateau
     * @param plateau le plateau pour ajouter la parcelle
     * @param parcelleCouleur la Parcelle de Couleur à ajouté
     * @return Renvoie <code> true </code> si la parcelle a bien était posé
     */
    public boolean addParcellePlateau(Plateau plateau,ParcelleCouleur parcelleCouleur){
        try {
            plateau.addParcelle(parcelleCouleur);
            return true;
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
            System.out.println(exception);
            return false;
        }
    }


    // Accesseurs et méthode toString
    /**
     * Permet de renvoyer le nom du Joueur
     * @return le nom du Joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie la plaquette du Joueur
     * @return la plaquette du Joueur
     */
    public Plaquette getPlaquette() {
        return plaquette;
    }

    /**
     * Renvoie les points du Joueur
     * @return les points du joueurs
     */
    public int getPoint() {
        int nombrePoints = 0;
        for(Objectif objectif : listObjectifTermine){
            nombrePoints += objectif.getNombrePoints();
        }
        return nombrePoints;
    }

    @Override
    public String toString() {
        return nom;
    }


    // Méthodes de jeu
}
