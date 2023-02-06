package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.GestionTours;
import fr.cotedazur.univ.polytech.startingpoint.jeu.MaitreDuJeu;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.List;

/**
 * Représente la stratégie de jeu favorisant la réalisation des objectifs de panda
 * @author equipe N
 */
public class StrategiePanda implements Strategie {
    // Méthodes d'utilisation

    @Override
    public Plaquette.ActionPossible choisiActionTour(boolean[] actionsRealiseesTour, List<Objectif> objectifs,
                                                     Plateau plateau, boolean[] piochesVides) {
        
        Plaquette.ActionPossible panda = Plaquette.ActionPossible.PANDA;
        if( plateau.getBambous().length > 0 ) {
            if (!actionsRealiseesTour[panda.ordinal()] && plateau.getParcelles().length > 3) {
                return panda;
            }
            Plaquette.ActionPossible objectif = Plaquette.ActionPossible.OBJECTIF;
            if (!actionsRealiseesTour[objectif.ordinal()] && (objectifs.size() < Joueur.NOMBRE_OBJECTIFS_MAX)) {
                return objectif;
            }
            Plaquette.ActionPossible parcelle = Plaquette.ActionPossible.PARCELLE;
            if (!actionsRealiseesTour[parcelle.ordinal()] && (plateau.getParcelles().length < 10))
                return parcelle;
            Plaquette.ActionPossible irrigation = Plaquette.ActionPossible.IRRIGATION;
            int irrigationPossable = plateau.getIrrigationsPosees().size()-plateau.getIrrigationsDisponibles().size();
            if (!actionsRealiseesTour[irrigation.ordinal()] && (irrigationPossable==3))
                return irrigation;
        }
        return Plaquette.ActionPossible.JARDINIER;
    }

    @Override
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle, PiocheSectionBambou piocheSectionBambou) throws PiocheParcelleEnCoursException, PiocheParcelleVideException {
        boolean parcellepose = false;
        ParcelleCouleur parcelleCouleur=null;
        ParcellePioche[] pioche = piocheParcelle.pioche() ;
        for ( ParcellePioche parcellePiochee : pioche ) {
            if ( GestionParcelles.chercheParcelleCouleur( plateau.getParcelles(), parcellePiochee.getCouleur()).isEmpty() && !parcellepose ) {
                parcelleCouleur = piocheParcelle.choisiParcelle( parcellePiochee, plateau.getPositionsDisponibles() [0]);
                parcellepose=true;
            }
        }
        if( !parcellepose ) {
            parcelleCouleur = piocheParcelle.choisiParcelle(pioche[0], plateau.getPositionsDisponibles()[0]);
        }
        irrigueParcelle(parcelleCouleur,plateau);
        plateau.poseParcelle(parcelleCouleur);
    }

    /**
     * pose un Bambou sir la parcelle est irriguee
     * @param parcelleCouleur une parcelle couleur a savoir sont etat
     * @param plateau le plateau
     */
    public void irrigueParcelle(ParcelleCouleur parcelleCouleur, Plateau plateau) {
        if (parcelleCouleur.isIrriguee()) {
            plateau.poseBambou(parcelleCouleur);
        }
    }

    @Override
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation, PiocheSectionBambou piocheSectionBambou) {

    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou) {

    }

    @Override
    public void actionPanda(Plateau plateau) {

    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle,
                               PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda) {

    }


}
