package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.List;
import java.util.Set;

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
        if (!actionsRealiseesTour[panda.ordinal()] && plateau.getParcelles().length > 3
                && plateau.getBambous().length > 0) {
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
        return Plaquette.ActionPossible.JARDINIER;
    }

    @Override
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle,
                               PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) throws PiocheParcelleEnCoursException, PiocheParcelleVideException {
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
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation,
                                 PiocheSectionBambou piocheSectionBambou) {
        Set<Irrigation> irrigationDisponible = plateau.getIrrigationsDisponibles();
        List<Irrigation> irrigationDisponibleListe = irrigationDisponible.stream().toList();
        plateau.addIrrigation(irrigationDisponibleListe.get(0).getPositions().get(0), irrigationDisponibleListe.get(0).getPositions().get(1));
    }

    @Override
    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs) {

    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs) {

    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle,
                               PiocheObjectifJardinier piocheObjectifJardinier,
                               PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {

    }
}
