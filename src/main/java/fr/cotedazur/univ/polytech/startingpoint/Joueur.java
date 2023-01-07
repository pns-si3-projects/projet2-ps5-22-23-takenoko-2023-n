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
    private final Random random;
    private final Plaquette plaquette;
    private final ArrayList<Objectif> objectifTermineList;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param nom est le nom du Joueur
     * @param random est un objet Random qui va permettre de créer des choix aléatoires
     * @param objPar est l'objectif de parcelle à ajouter
     * @param objPan est l'objectif de panda à ajouter
     * @param objJar est l'objectif de jardinier à ajouter
     */
    public Joueur(String nom, Random random, ObjectifParcelle objPar, ObjectifPanda objPan, ObjectifJardinier objJar) {
        this.nom = nom;
        this.random = random;
        plaquette = new Plaquette(objPar, objPan, objJar);
        objectifTermineList = new ArrayList<>();
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nom du Joueur
     * @return le nom du Joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le nombre d'objectifs que le Joueur a terminé
     * @return le nombre d'objectifs que le Joueur a terminé
     */
    public int getNombreObjectifsTermines() {
        return objectifTermineList.size();
    }

    /**
     * Renvoie la plaquette du joueur
     * @return la plaquette du joueur
     */
    public Plaquette getPlaquette() {
        return plaquette;
    }

    /**
     * Renvoie les points du Joueur
     * @return les points du Joueur
     */
    public int getPoints() {
        int nombrePoints = 0;
        for (Objectif objectif : objectifTermineList) {
            nombrePoints += objectif.getNombrePoints();
        }
        return nombrePoints;
    }

    @Override
    public String toString() {
        return nom + " : " + objectifTermineList.size()
                + " objectifs terminés, pour " + getPoints() + " points";
    }


    // Méthodes de jeu
    /**
     * Effectue le tour du joueur
     * @param piocheObjectif la pioche d'objectifs pour piocher les objectifs
     * @param plateau le plateau pour ajouter les parcelles
     * @param arbitre permet de vérifier les actions
     */
    public void tour(PiocheObjectif piocheObjectif, Plateau plateau, Arbitre arbitre) {
        actionTour(piocheObjectif, plateau, arbitre);
        actionTour(piocheObjectif, plateau, arbitre);
    }

    /**
     * Permet d'effectuer une action d'un tour
     * @param piocheObjectif la pioche d'objectif pour piocher les objectifs
     * @param plateau le plateau pour ajouter les parcelles
     * @param arbitre permet de vérifier les actions
     */
    private void actionTour(PiocheObjectif piocheObjectif,Plateau plateau, Arbitre arbitre) {
        gestionObjectif(plateau.getParcelles(), arbitre, plaquette.getObjectifsParcelle());
        if (plaquette.getNombreObjectifParcelle() == 0) {
            Optional<ObjectifParcelle> objectifParcellePioche = piocheObjectifParcelle(piocheObjectif);
            if (objectifParcellePioche.isPresent()) {
                ObjectifParcelle objectifParcelle = objectifParcellePioche.get();
                objectifParcelle.setNombreParcellePresenteEnJeu(plateau.getParcelles().length);
                try {
                    plaquette.ajouteObjectif(objectifParcelle);
                    Main.AFFICHEUR.affichePiocheCarte(objectifParcelle);
                }
                catch (NombreObjectifsEnCoursException nOEE) {
                    assert false : "Il doit avoir 0 Objectif";
                }
            }
        }
        else {
            boolean parcelleAjoute = false;
            while (!parcelleAjoute) {
                ParcelleCouleur parcelleCouleurChoisi = choisiParcellePlateau(plateau.getPositionsDisponible());
                parcelleAjoute = addParcellePlateau(plateau, parcelleCouleurChoisi);
                if(parcelleAjoute) Main.AFFICHEUR.afficheAjoutParcelle(parcelleCouleurChoisi);
            }
            gestionObjectif(plateau.getParcelles(), arbitre, plaquette.getObjectifsParcelle());
        }
    }

    /**
     * Gère les objectifs grâce à l'arbitre
     * @param listParcellesEtVoisines la liste de parcelle du plateau à donnée à l'arbitre pour vérifier si les parcelles ont été posé
     * @param arbitre L'arbitre qui doit vérifier si l'objectif est validé
     * @param objectifsParcelles La liste des objectifs parcelles
     */
    private void gestionObjectif(Parcelle[] listParcellesEtVoisines, Arbitre arbitre, ObjectifParcelle[] objectifsParcelles){
        assert objectifsParcelles != null : "La plaquette contenant les objectifs parcelles ne doit pas être vide";
        for (ObjectifParcelle objectifParcelle : objectifsParcelles) {
            if (arbitre.checkObjectifParcelleTermine(listParcellesEtVoisines, objectifParcelle)) {
                if (plaquette.supprimeObjectif(objectifParcelle)) {
                    objectifTermineList.add(objectifParcelle);
                    Main.AFFICHEUR.afficheObjectifValide(objectifParcelle);
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
     * Renvoie le choix de parcelle que le joueur veut poser sur le plateau
     * @param listPositionDisponible la liste des positions disponibles
     * @return la parcelle que le joueur veut poser
     */
    public ParcelleCouleur choisiParcellePlateau(Position[] listPositionDisponible){
        int nombreAleatoire = random.nextInt(listPositionDisponible.length);
        if(nombreAleatoire < 0 || nombreAleatoire >= listPositionDisponible.length) throw new ArithmeticException("Erreur objet random");
        Position positionChoisie = listPositionDisponible[nombreAleatoire];
        return new ParcelleCouleur(positionChoisie);
    }

    /**
     * Ajoute la parcelle au plateau
     * @param plateau le plateau pour ajouter la parcelle
     * @param parcelleCouleur la parcelle de couleur à ajouter
     * @return <code>true</code> si la parcelle a bien été posée, <code>false</code> sinon
     */
    public boolean addParcellePlateau(Plateau plateau, ParcelleCouleur parcelleCouleur) {
        try {
            plateau.addParcelle(parcelleCouleur);
            return true;
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
            System.err.println(exception);
            return false;
        }
    }
}
