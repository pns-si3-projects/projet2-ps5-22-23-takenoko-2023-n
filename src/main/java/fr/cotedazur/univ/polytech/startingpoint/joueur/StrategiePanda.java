package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.GestionnaireObjectifs;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionBambous;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionPersonnages;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Représente la stratégie de jeu favorisant la réalisation des objectifs de panda
 * @author equipe N
 */
public class StrategiePanda implements Strategie {
    // Méthodes d'utilisation

    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs,
                                                     Plateau plateau, boolean[] piochesVides) {

        Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
        if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size() < Joueur.NOMBRE_OBJECTIFS_MAX)) {
            return objectif;
        }

        Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
        if (!actionsRealiseesTour[panda.ordinal()] && plateau.getParcelles().length > 2
                && plateau.getBambous().length > 0) {
            return panda;
        }


        Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
        if (!actionsRealiseesTour[parcelle.ordinal()] && (plateau.getParcelles().length < 2))
            return parcelle;

        Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
        int irrigationPossable = plateau.getIrrigationsPosees().length -plateau.getIrrigationsDisponibles().length;
        if (!actionsRealiseesTour[irrigation.ordinal()] && (irrigationPossable==3)) {
            return irrigation;
        }

        return Plaquette.ActionPossible.JARDINIER;
    }

    @Override
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs)  {
        boolean parcellepose = false;
        ParcelleCouleur parcelleCouleur=null;
        ParcellePioche[] pioche;
        try {
            pioche = piocheParcelle.pioche();

            for ( ParcellePioche parcellePiochee : pioche ) {
                if ( GestionParcelles.chercheParcelleCouleur( plateau.getParcelles(), parcellePiochee.getCouleur()).isEmpty() && !parcellepose ) {
                    parcelleCouleur = piocheParcelle.choisiParcelle( parcellePiochee, plateau.getPositionsDisponibles() [0]);
                    parcellepose=true;
                }
            }
            if( !parcellepose ) {
                parcelleCouleur = piocheParcelle.choisiParcelle(pioche[0], plateau.getPositionsDisponibles()[0]);
            }
        } catch (PiocheParcelleEnCoursException | PiocheParcelleVideException e) {
            throw new AssertionError(e);
        }

        SectionBambou sectionBambou = piocheSectionBambou.pioche(parcelleCouleur.getCouleur());
        irrigueParcelle(parcelleCouleur, plateau, sectionBambou);
        plateau.poseParcelle(parcelleCouleur);
    }

    /**
     * pose un Bambou sir la parcelle est irriguee
     * @param parcelleCouleur une parcelle couleur a savoir sont etat
     * @param plateau le plateau
     */
    public void irrigueParcelle(ParcelleCouleur parcelleCouleur, Plateau plateau, SectionBambou sectionBambou) {
        if (parcelleCouleur.isIrriguee()) {
            plateau.poseBambou(parcelleCouleur, sectionBambou);
        }
    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, Plaquette plaquette) {
        Irrigation[] irrigationsDisponibles = plateau.getIrrigationsDisponibles();
        if (irrigationsDisponibles.length > 0){
            List<Position> positionIrrigation = irrigationsDisponibles[irrigationsDisponibles.length-1].getPositions();

            if (!piocheIrrigation.isEmpty()) {
                Irrigation irrigationAAdd = piocheIrrigation.pioche();
                irrigationAAdd.addPosition(positionIrrigation.get(0), positionIrrigation.get(1));
                plateau.poseIrrigation(irrigationAAdd);
            }
        }
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs)  {
        Position futurePosition;
        Jardinier jardinier = plateau.getJardinier();
        List<Position> deplacementPossible =
                GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(), jardinier.getPosition());
        List<Position> positionsAvecBambou = GestionBambous.positionAvecBambou(deplacementPossible, plateau, true);

        if (positionsAvecBambou.isEmpty()) {
            futurePosition = deplacementPossible.get(deplacementPossible.size()-1);
        } else {
            futurePosition = positionsAvecBambou.get(positionsAvecBambou.size()-1);
        }

        plateau.deplacementJardinier(futurePosition);
    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs, Plaquette plaquette) {
        List<ObjectifPanda> objectifsPandas = recupereObjectifPanda(objectifs);
        Couleur couleur = couleurVoulue(objectifsPandas, plaquette);
        Position positionVoulue;

        //recuperation des deplacement possible
        List<Position> deplacementPossible = GestionPersonnages
                .deplacementsPossibles(plateau.getParcelleEtVoisinesList(),plateau.getPanda().getPosition());
        List<Position> positionsAvecBambou = GestionBambous.positionAvecBambou(deplacementPossible, plateau, false);

        positionVoulue = GestionBambous.positionVoulueCouleur(plateau, deplacementPossible, positionsAvecBambou, couleur);

        Optional<SectionBambou> bambouMange = plateau.deplacementPanda(positionVoulue);
        bambouMange.ifPresent(plaquette::mangeSectionBambou);
    }

    /**
     * renvoie la couluer souhaiter pour complete un objectif
     * @param objectifPandas la liste des objectif Panda
     * @param plaquette la plaquette
     * @return la couleur souhaite
     */
    public Couleur couleurVoulue(List<ObjectifPanda> objectifPandas, Plaquette plaquette) {
        Couleur couleurRenvoyer = null;
        for (ObjectifPanda objectifPanda : objectifPandas) {
            if (objectifPanda.getBambousAManger().size() == 2) {
                couleurRenvoyer = objectifPanda.getBambousAManger().get(0).getCouleur();
            }
            else {
                couleurRenvoyer = plaquetteCouleurManquante(plaquette);
            }
        }
        return couleurRenvoyer;
    }

    /**
     * renvoie la couluer souhaiter pour ObjectifPanda de 3 section Bambou
     * @param plaquette la plaquette
     * @return la couleur voulue
     */
    public Couleur plaquetteCouleurManquante(Plaquette plaquette) {
        SectionBambou[] sectionBambous = plaquette.getReserveBambousManges();
        if (GestionnaireObjectifs.countCouleurSectionBambou(sectionBambous, Couleur.VERTE) == 0) return Couleur.VERTE;
        if (GestionnaireObjectifs.countCouleurSectionBambou(sectionBambous, Couleur.ROSE) == 0) return Couleur.ROSE;
        return Couleur.JAUNE;
    }

    /**
     * recupre la liste des ObjectifPanda que objectifs
     * @param objectifs les objectifs du Joueur
     * @return la liste des objectifPanda
     */
    public List<ObjectifPanda> recupereObjectifPanda(List<Objectif> objectifs) {
        List<ObjectifPanda> objectifsPanda = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            if(objectif.getClass().equals(ObjectifPanda.class)) {
                objectifsPanda.add((ObjectifPanda) objectif);
            }
        }
        return objectifsPanda;
    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle,
                               PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {
        Objectif objectif;
        if (!piocheObjectifPanda.isEmpty()) objectif = piocheObjectifPanda.pioche();
        else if (!piocheObjectifParcelle.isEmpty()) objectif = piocheObjectifParcelle.pioche();
        else objectif = piocheObjectifJardinier.pioche();
        objectifs.add(objectif);
    }
}
