package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.*;

import java.util.ArrayList;
import java.util.List;
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
    public static final String MOINS_DE_5_OBJECTIFS = "Il doit avoir moins de 5 objectifs";


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
    public void tour(PiocheObjectif piocheObjectif, PiocheBambou piocheBambou, PiocheParcelle piocheParcelle, Plateau plateau, Arbitre arbitre, GestionnairePossibilitePlateau gPP) {
        plaquette.reinitialiseActionsTour();
        actionTour(piocheObjectif, piocheBambou, piocheParcelle, plateau, arbitre, gPP);
        actionTour(piocheObjectif, piocheBambou, piocheParcelle, plateau, arbitre, gPP);
    }

    /**
     * Permet d'effectuer une action d'un tour
     * @param piocheObjectif la pioche d'objectif pour piocher les objectifs
     * @param piocheBambou la pioche de bambou pour piocher une section de bambou
     * @param plateau le plateau pour ajouter les parcelles
     * @param arbitre permet de vérifier les actions et les objectifs
     * @param gPP est le gestionnaire de possibilité de déplacements sur le plateau pour savoir où on peut déplacer le panda
     */
    private void actionTour(PiocheObjectif piocheObjectif, PiocheBambou piocheBambou, PiocheParcelle piocheParcelle,Plateau plateau, Arbitre arbitre, GestionnairePossibilitePlateau gPP){
        if(plaquette.getNombreObjectifs() == 5){
            if(!plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE)){
                actionParcelle(piocheBambou,piocheParcelle,plateau,arbitre);
                plaquette.realiseAction(Plaquette.ActionPossible.PARCELLE);
            }
            else {
                actionPanda(plateau,arbitre,gPP);
                plaquette.realiseAction(Plaquette.ActionPossible.PANDA);
            }
        }
        else if(!plaquette.isActionRealisee(Plaquette.ActionPossible.OBJECTIF)){
            actionPioche(piocheObjectif);
            plaquette.realiseAction(Plaquette.ActionPossible.OBJECTIF);
        }
        else {
            if(!plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE)){
                actionParcelle(piocheBambou,piocheParcelle,plateau,arbitre);
                plaquette.realiseAction(Plaquette.ActionPossible.PARCELLE);
            }
            else{
                actionPanda(plateau,arbitre,gPP);
                plaquette.realiseAction(Plaquette.ActionPossible.PANDA);
            }
        }
    }

    /**
     * Permet de piocher un objectif qui manque au joueur
     * @param piocheObjectif la pioche d'objectif pour piocher l'objectif souhaité
     */
    public void actionPioche(PiocheObjectif piocheObjectif){
        if(plaquette.getObjectifsParcelle().length < 2 && !piocheObjectif.isEmptyPiocheObjectifParcelle()){
            ObjectifParcelle objectifParcelle = piocheObjectif.piocheObjectifParcelle();
            try {
                plaquette.ajouteObjectif(objectifParcelle);
            }
            catch (NombreObjectifsEnCoursException nOECE){
                assert false : MOINS_DE_5_OBJECTIFS;
            }
            Main.AFFICHEUR.affichePiocheCarte(objectifParcelle);
        }
        else if(plaquette.getObjectifsPanda().length < 2 && !piocheObjectif.isEmptyPiocheObjectifPanda()){
            ObjectifPanda objectifPanda = piocheObjectif.piocheObjectifPanda();
            try {
                plaquette.ajouteObjectif(objectifPanda);
            }
            catch (NombreObjectifsEnCoursException nOECE){
                assert false : MOINS_DE_5_OBJECTIFS;
            }
            Main.AFFICHEUR.affichePiocheCarte(objectifPanda);
        }
        else{
            ObjectifJardinier objectifJardinier = piocheObjectif.piocheObjectifJardinier();
            try {
                plaquette.ajouteObjectif(objectifJardinier);
            }
            catch (NombreObjectifsEnCoursException nOECE){
                assert false : MOINS_DE_5_OBJECTIFS;
            }
            Main.AFFICHEUR.affichePiocheCarte(objectifJardinier);
        }
    }

    /**
     * Permet d'ajouter une parcelle
     * @param piocheBambou la pioche de bambou pour piocher un bambou et le poser sur la parcelle ajoutée
     * @param plateau le plateau pour ajouter les parcelles
     * @param arbitre permet de vérifier les actions et les objectifs
     */
    public void actionParcelle(PiocheBambou piocheBambou,PiocheParcelle piocheParcelle, Plateau plateau,Arbitre arbitre) {
        boolean parcelleAjoute = false;
        while (!parcelleAjoute) {

            Position positionParcelleChoisie = choisiParcellePlateau(plateau.getPositionsDisponible());
            Optional<ParcelleCouleur> optParcelleCouleurChoisie = choisiParcellePioche(piocheParcelle, positionParcelleChoisie);

            if (optParcelleCouleurChoisie.isPresent()) {

                ParcelleCouleur parcelleCouleurChoisie = optParcelleCouleurChoisie.get();
                SectionBambou sectionBambou = choisiSectionBambou(piocheBambou,parcelleCouleurChoisie.couleur());
                parcelleAjoute = addParcellePlateau(plateau, parcelleCouleurChoisie, sectionBambou);

                if (parcelleAjoute) Main.AFFICHEUR.afficheAjoutParcelle(parcelleCouleurChoisie);
            }

        }
        gestionObjectifParcelle(plateau.getParcelles(), arbitre, plaquette.getObjectifsParcelle());
    }

    private SectionBambou choisiSectionBambou(PiocheBambou piocheBambou, Couleur couleurParcelle){
        if(couleurParcelle.isVert()) return piocheBambou.piocheSectionBambouVert();
        else if(couleurParcelle.isJaune()) return piocheBambou.piocheSectionBambouJaune();
        else{
            return piocheBambou.piocheSectionBambouRose();
        }
    }

    /**
     * Choisi une Parcelle dans la Pioche Parcelle
     * @param piocheParcelle La pioche de Parcelle
     * @param positionParcelle La position de la parcelle qu'on veut ajouter
     * @return Renvoie la Parcelle Couleur si la pioche n'est pas vide
     */
    public Optional<ParcelleCouleur> choisiParcellePioche(PiocheParcelle piocheParcelle, Position positionParcelle){
        if (piocheParcelle.getNombreParcellesRestantes() <= 0) return Optional.empty();
        else{
            try {
                ParcellePioche[] parcellesAChoisir = piocheParcelle.pioche();
                int nombreAleatoire = random.nextInt(3);
                ParcelleCouleur parcelleChoisi = piocheParcelle.choisiParcelle(parcellesAChoisir[nombreAleatoire],positionParcelle);
                return Optional.of(parcelleChoisi);
            }
            catch (PiocheParcelleEnCoursException pPECE){
                assert false : "Ne doit pas être en cours d'exécution";
            }
            catch (PiocheParcelleVideException pPVE){
                assert false: "Condition de pioches impossibles à être vide";
            }
            return Optional.empty();
        }
    }

    /**
     * Permet de déplacer le panda
     * @param plateau le plateau pour ajouter les parcelles
     * @param arbitre permet de vérifier les actions et les objectifs
     * @param gPP est le gestionnaire de possibilité de déplacements sur le plateau pour savoir où on peut déplacer le panda
     */
    public void actionPanda(Plateau plateau, Arbitre arbitre, GestionnairePossibilitePlateau gPP){
        Position positionPanda = plateau.getPanda().position();
        boolean deplacementReussi = deplacementPanda(plateau,gPP.deplacementPossiblePersonnageDiagonaleDroite(positionPanda));
        if(!deplacementReussi){
            deplacementReussi = deplacementPanda(plateau,gPP.deplacementPossiblePersonnageDiagonaleGauche(positionPanda));
            if(!deplacementReussi){
                deplacementPanda(plateau,gPP.deplacementPossiblePersonnageHorizontal(positionPanda));
            }
        }
        gestionObjectifPanda(arbitre, plaquette.getObjectifsPanda());
    }

    /**
     * Déplace le panda en fonction de la liste de positions possibles
     * @param plateau le plateau pour avoir la liste des bambous
     * @param positionPossibleDeplacement est la liste des positions possibles pour déplacer le panda
     * @return <code>true</code> si le déplacement du panda est possible et effectué, <code>false</code> sinon
     */
    private boolean deplacementPanda(Plateau plateau, List<Position> positionPossibleDeplacement) {
        for (Position possibleDeplacement : positionPossibleDeplacement) {
            Optional<Bambou> optBambou = plateau.getBambou(possibleDeplacement);
            if (optBambou.isPresent()) {
                Bambou bambou = optBambou.get();
                if (!bambou.isEmptyBambou()) {
                    SectionBambou sectionBambou = bambou.prendSectionBambou();
                    plaquette.ajouteSectionBambou(sectionBambou);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gère les objectifs de parcelle grâce à l'arbitre
     * @param listParcellesEtVoisines la liste de parcelle du plateau à donner à l'arbitre pour vérifier si les parcelles ont été posées
     * @param arbitre L'arbitre qui doit vérifier si l'objectif est validé
     * @param objectifsParcelles La liste des objectifs parcelles
     */
    private void gestionObjectifParcelle(Parcelle[] listParcellesEtVoisines, Arbitre arbitre, ObjectifParcelle[] objectifsParcelles) {
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
     * Gère les objectifs de panda grâce à l'arbitre
     * @param arbitre L'arbitre qui doit vérifier si l'objectif est validé
     * @param objectifPandas La liste des objectifs panda
     */
    private void gestionObjectifPanda(Arbitre arbitre, ObjectifPanda[] objectifPandas){
        for(ObjectifPanda objectifPanda : objectifPandas) {
            SectionBambou[] sectionBambousCouleur = getTableauSectionBambouPourObjectifPanda(objectifPanda);

            if ( arbitre.checkObjectifPandaTermine(sectionBambousCouleur,objectifPanda) ) {
                if ( plaquette.supprimeObjectif(objectifPanda) ) {
                    if ( deleteSectionBambou(objectifPanda) ) {
                        objectifTermineList.add(objectifPanda);
                        Main.AFFICHEUR.afficheObjectifValide(objectifPanda);
                    }
                    else {
                        assert false : "Devrait supprimer toutes les sections car l'arbitre à vérifier";
                    }
                }
                else {
                    assert false : "L'objectif doit normalement existe";
                }
            }
        }
    }

    /**
     * Renvoie le tableau de Sections de Bambous de couleur correspondant à l'objectif donnée en paramètre
     * @param objectifPanda L'objectif panda qu'on veut vérifier pour arbitre
     * @return le tableau de Sections de Bambous
     */
    private SectionBambou[] getTableauSectionBambouPourObjectifPanda(ObjectifPanda objectifPanda){
        Couleur couleurBambouPourObjectif = objectifPanda.getCouleurBambousAManger();
        if (couleurBambouPourObjectif == Couleur.VERT) return plaquette.getSectionBambouVert();
        else if (couleurBambouPourObjectif == Couleur.JAUNE) return plaquette.getSectionBambouJaune();
        else if (couleurBambouPourObjectif == Couleur.ROSE) return plaquette.getSectionBambouRose();
        else {
            assert false : "Doit forcément être une des 3 autres couleurs";
            return null;
        }
    }

    /**
     * Supprime le nombre de Sections de Bambous en fonction du nombre de bambous demandé dans Objectif Panda et de sa couleur
     * @param objectifPanda objectif Panda qui vient de se terminé
     * @return <code> true </code> si les sections ont été bien enlevé sinon <code> false </code>
     */
    private boolean deleteSectionBambou(ObjectifPanda objectifPanda){
        int nombreBambouObjectif = objectifPanda.getNombreBambousAManger();
        Couleur couleurBambouPourObjectif = objectifPanda.getCouleurBambousAManger();

        if (couleurBambouPourObjectif == Couleur.VERT) return plaquette.deleteSectionBambouVert(nombreBambouObjectif);
        else if (couleurBambouPourObjectif == Couleur.JAUNE) return plaquette.deleteSectionBambouJaune(nombreBambouObjectif);
        else if (couleurBambouPourObjectif == Couleur.ROSE) return plaquette.deleteSectionBambouJaune(nombreBambouObjectif);
        else {
            assert false : "Doit forcément être une des 3 autres couleurs";
            return false;
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
    public Position choisiParcellePlateau(Position[] listPositionDisponible){
        int nombreAleatoire = random.nextInt(listPositionDisponible.length);
        if(nombreAleatoire < 0 || nombreAleatoire >= listPositionDisponible.length) throw new ArithmeticException("Erreur objet random");
        Position positionChoisie = listPositionDisponible[nombreAleatoire];
        return positionChoisie;
    }

    /**
     * Ajoute la parcelle au plateau
     * @param plateau le plateau pour ajouter la parcelle
     * @param parcelleCouleur la parcelle de couleur à ajouter
     * @return <code>true</code> si la parcelle a bien été posée, <code>false</code> sinon
     */
    public boolean addParcellePlateau(Plateau plateau, ParcelleCouleur parcelleCouleur, SectionBambou sectionBambou) {
        try {
            plateau.addParcelle(parcelleCouleur, sectionBambou);
            return true;
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
            System.err.println(exception);
            return false;
        }
    }
}
