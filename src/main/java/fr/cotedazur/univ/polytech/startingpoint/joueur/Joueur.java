package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.Arbitre;
import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Main;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.*;
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
    public void actionTour(PiocheObjectif piocheObjectif, PiocheBambou piocheBambou, PiocheParcelle piocheParcelle,Plateau plateau, Arbitre arbitre, GestionnairePossibilitePlateau gPP){
        if(gestionObjectifParcelle(plateau.getParcelles(), arbitre, plaquette.getObjectifsParcelle())){
            plaquette.realiseAction(Plaquette.ActionPossible.PARCELLE);
        }
        if(plaquette.getNombreObjectifs() < 5 && !plaquette.isActionRealisee(Plaquette.ActionPossible.OBJECTIF)){
            actionPioche(piocheObjectif);
            plaquette.realiseAction(Plaquette.ActionPossible.OBJECTIF);
        }
        else if(!plaquette.isActionRealisee(Plaquette.ActionPossible.PARCELLE)){
            actionParcelle(piocheBambou,piocheParcelle,plateau,arbitre,gPP);
            plaquette.realiseAction(Plaquette.ActionPossible.PARCELLE);
        }
        else if(!plaquette.isActionRealisee(Plaquette.ActionPossible.JARDINIER)){
            actionJardinier(plateau,arbitre,gPP);
            plaquette.realiseAction(Plaquette.ActionPossible.JARDINIER);
        }
        else{
            actionPanda(plateau,arbitre,gPP);
            plaquette.realiseAction(Plaquette.ActionPossible.PANDA);
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
     * Effectue une action d'ajout de Parcelle
     * @param piocheBambou La pioche de bambou qui permet de récupérer une section de bambou
     * @param piocheParcelle La pioche de parcelle qui permet de récupérer une parcelle
     * @param plateau Le plateau, pour ajouter une parcelle même si aucun objectif est possible
     * @param arbitre L'arbitre qui verifie si les objectifs sont bien réalisés
     * @param gPP Le gestionnaire de Possibilité de plateau qui permet d'aider le joueur à choisir une parcelle
     */
    public void actionParcelle(PiocheBambou piocheBambou,PiocheParcelle piocheParcelle,Plateau plateau, Arbitre arbitre, GestionnairePossibilitePlateau gPP) {
        Optional<Position> optPositionChoisi = gPP.positionPossiblePrendrePourMotif(plaquette.getObjectifsParcelle()[0]);
        Position positionChoisi;
        positionChoisi = optPositionChoisi.orElseGet(() -> plateau.getPositionsDisponible()[0]);

        if (piocheParcelle.getNombreParcellesRestantes() > 0) {
            try {
                ParcellePioche[] parcelles = piocheParcelle.pioche();
                ParcelleCouleur parcelleChoisi = piocheParcelle.choisiParcelle(parcelles[0], positionChoisi);
                SectionBambou sectionBambou = choisiSectionBambou(piocheBambou, parcelleChoisi.couleur());
                plateau.addParcelle(parcelleChoisi, sectionBambou);
                Main.AFFICHEUR.afficheAjoutParcelle(parcelleChoisi);
            } catch (ParcelleExistanteException | NombreParcelleVoisineException | PiocheParcelleVideException | PiocheParcelleEnCoursException e) {
                assert false : "Ne doit pas renvoyer d'erreur";
            }
            gestionObjectifParcelle(plateau.getParcelles(),arbitre, plaquette.getObjectifsParcelle());
        }
    }

    /**
     * Choisi une section de bambou de couleur dans la pioche de bambou
     * @param piocheBambou La pioche de bambou pour piocher un bambou
     * @param couleurParcelle La couleur de la Parcelle pour pioche le bambou de même couleur
     * @return une section de bambou de couleur
     */
    private SectionBambou choisiSectionBambou(PiocheBambou piocheBambou, Couleur couleurParcelle){
        if(couleurParcelle.isVert()) return piocheBambou.piocheSectionBambouVert();
        else if(couleurParcelle.isJaune()) return piocheBambou.piocheSectionBambouJaune();
        else return piocheBambou.piocheSectionBambouRose();
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
                    plateau.getPanda().move(possibleDeplacement);
                    SectionBambou sectionBambou = bambou.prendSectionBambou();
                    plaquette.ajouteSectionBambou(sectionBambou);
                    Main.AFFICHEUR.afficheDeplacementPanda(possibleDeplacement);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Permet de déplacer le jardinier
     * @param plateau le plateau pour ajouter les parcelles
     * @param arbitre permet de vérifier les actions et les objectifs
     * @param gPP est le gestionnaire de possibilité de déplacements sur le plateau pour savoir où on peut déplacer le jardinier
     */
    public void actionJardinier(Plateau plateau, Arbitre arbitre, GestionnairePossibilitePlateau gPP){
        Position positionJardinier = plateau.getJardinier().position();
        int deplacementReussi = deplacementJardinier(plateau,gPP.deplacementPossiblePersonnageDiagonaleDroite(positionJardinier));
        if(deplacementReussi == -1){
            deplacementReussi = deplacementJardinier(plateau,gPP.deplacementPossiblePersonnageDiagonaleGauche(positionJardinier));
            if(deplacementReussi == -1){
                deplacementJardinier(plateau,gPP.deplacementPossiblePersonnageHorizontal(positionJardinier));
            }
        }
        gestionObjectifJardinier(arbitre, plaquette.getObjectifsJardinier(), deplacementReussi);
    }

    /**
     * Déplace le jardinier en fonction de la liste de positions possibles
     * @param plateau le plateau pour ajouter les bambous
     * @param positionPossibleDeplacement est la liste des positions possibles pour déplacer le jardinier
     * @return <code>true</code> si le déplacement du jardinier est possible et effectué, <code>false</code> sinon
     */
    private int deplacementJardinier(Plateau plateau, List<Position> positionPossibleDeplacement) {
        int indexParcelleChoisie = random.nextInt(positionPossibleDeplacement.size());
        Position positionParcelle = positionPossibleDeplacement.get(indexParcelleChoisie);
        if(indexParcelleChoisie < 0 || indexParcelleChoisie >= positionPossibleDeplacement.size()) throw new ArithmeticException("Erreur objet random");
        plateau.getJardinier().move(positionParcelle);
        Main.AFFICHEUR.afficheDeplacementJardinier(positionParcelle);
        Optional<Parcelle> parcelleJardinier = plateau.getParcelle(positionParcelle);
        if (parcelleJardinier.isPresent()) {
            try {
                return plateau.jardinierAddBambous(parcelleJardinier.get());
            }
            catch (ParcelleNonExistanteException pnee){
                System.err.println(pnee);
            }
        }
        else {
            assert false : "La parcelle choisie doit être sur le plateau";
        }
        return -1;
    }

    /**
     * Gère les objectifs de parcelle grâce à l'arbitre
     * @param listParcellesEtVoisines la liste de parcelle du plateau à donner à l'arbitre pour vérifier si les parcelles ont été posées
     * @param arbitre L'arbitre qui doit vérifier si l'objectif est validé
     * @param objectifsParcelles La liste des objectifs parcelles
     */
    private boolean gestionObjectifParcelle(Parcelle[] listParcellesEtVoisines, Arbitre arbitre, ObjectifParcelle[] objectifsParcelles) {
        assert objectifsParcelles != null : "La plaquette contenant les objectifs parcelles ne doit pas être vide";
        boolean objectifValide = false;
        for (ObjectifParcelle objectifParcelle : objectifsParcelles) {
            if (arbitre.checkObjectifParcelleTermine(listParcellesEtVoisines, objectifParcelle)) {
                if (plaquette.supprimeObjectif(objectifParcelle)) {
                    objectifTermineList.add(objectifParcelle);
                    objectifValide = true;
                    Main.AFFICHEUR.afficheObjectifValide(objectifParcelle);
                } else {
                    assert false : "L'objectif doit normalement existe";
                }
            }
        }
        return objectifValide;
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
     * Gère les objectifs de jardinier grâce à l'arbitre
     * @param arbitre L'arbitre qui doit vérifier si l'objectif est validé
     * @param objectifJardiniers La liste des objectifs jardinier
     */
    private void gestionObjectifJardinier(Arbitre arbitre, ObjectifJardinier[] objectifJardiniers, int nombreBambousPoses){
        for(ObjectifJardinier objectifJardinier : objectifJardiniers){
            objectifJardinier.soustraitNombreBambousPoses(nombreBambousPoses);
            if(arbitre.checkObjectifJardinierTermine(objectifJardinier)){
                if(plaquette.supprimeObjectif(objectifJardinier)){
                    objectifTermineList.add(objectifJardinier);
                    Main.AFFICHEUR.afficheObjectifValide(objectifJardinier);
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
        switch (couleurBambouPourObjectif) {
            case VERT -> { return plaquette.getSectionBambouVert(); }
            case JAUNE -> { return plaquette.getSectionBambouJaune(); }
            case ROSE -> { return plaquette.getSectionBambouRose(); }
            default -> { assert false : "Doit forcément être une des 3 autres couleurs"; }
        }
        return new SectionBambou[0];
    }

    /**
     * Supprime le nombre de Sections de Bambous en fonction du nombre de bambous demandé dans Objectif Panda et de sa couleur
     * @param objectifPanda objectif Panda qui vient de se terminé
     * @return <code> true </code> si les sections ont été bien enlevé sinon <code> false </code>
     */
    private boolean deleteSectionBambou(ObjectifPanda objectifPanda){
        int nombreBambouObjectif = objectifPanda.getNombreBambousAManger();
        Couleur couleurBambouPourObjectif = objectifPanda.getCouleurBambousAManger();

        switch (couleurBambouPourObjectif) {
            case VERT -> { return plaquette.deleteSectionBambouVert(nombreBambouObjectif); }
            case JAUNE -> { return plaquette.deleteSectionBambouJaune(nombreBambouObjectif); }
            case ROSE -> { return plaquette.deleteSectionBambouRose(nombreBambouObjectif);  }
            default -> { assert false : "Doit forcément être une des 3 autres couleurs"; }
        }
        return false;
    }

}
