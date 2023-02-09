package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.GestionnaireObjectifs;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.*;

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
            List<Position> positionIrrigation = irrigationsDisponibles[0].getPositions();

            if (!piocheIrrigation.isEmpty()) {
                Irrigation irrigationAAdd = piocheIrrigation.pioche();
                irrigationAAdd.addPosition(positionIrrigation.get(0), positionIrrigation.get(1));
                plateau.poseIrrigation(irrigationAAdd);
            }
        }
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs)  {
        Jardinier jardinier = plateau.getJardinier();
        List<Position> listPositionPossible = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(),jardinier.getPosition());
        try {
            plateau.deplacementJardinier(listPositionPossible.get(listPositionPossible.size()-1));
        } catch (ParcelleNonPoseeException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs, Plaquette plaquette) {
        Optional<SectionBambou> bambouMange ;
        Position positionADeplacer = null;
        List<ObjectifPanda> objectifsPandas = recupreObjectifPanda(objectifs);
        List<Position> deplacementPossible = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(), plateau.getPanda().getPosition());
        List<Position> listePositionAvecBambou = GestionBambous.positionAvecBambou(deplacementPossible, plateau);

        for (Position position : listePositionAvecBambou) {
            Optional<Parcelle> parcelle = GestionParcelles.chercheParcelle(plateau.getParcelles(), position);
            if(parcelle.isPresent() && parcelle.get().getClass().equals(ParcelleCouleur.class)) {
                
                ParcelleCouleur choixPossibleParcelleCouleur = (ParcelleCouleur) parcelle.get();
                if (choixPossibleParcelleCouleur.getCouleur().equals(couleurVoulue(objectifsPandas, plaquette))) {
                    positionADeplacer = position;
                    break;
                }
            }
        }

        if (positionADeplacer != null) {
            bambouMange = plateau.deplacementPanda(positionADeplacer);
        }
        else {
            bambouMange = plateau.deplacementPanda(listePositionAvecBambou.get(0));
        }
        if(bambouMange.isPresent()) {
            plaquette.mangeSectionBambou(bambouMange.get());
        }
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
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.VERTE) == 0) {return Couleur.VERTE; }
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.ROSE) == 0) {return Couleur.ROSE; }
        if (GestionnaireObjectifs.countCouleurSectionBambou(plaquette.getReserveBambousManges(), Couleur.JAUNE) == 0) {return Couleur.JAUNE; }
        return null;
    }

    /**
     * recupre la liste des ObjectifPanda que objectifs
     * @param objectifs les objectifs du Joueur
     * @return la liste des objectifPanda
     */
    public List<ObjectifPanda> recupreObjectifPanda(List<Objectif> objectifs) {
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
