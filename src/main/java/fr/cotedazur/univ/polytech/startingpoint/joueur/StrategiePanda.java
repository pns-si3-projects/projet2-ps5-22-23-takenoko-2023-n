package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionPersonnages;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;

import java.util.List;
import java.util.Optional;
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

        int irrigationPossable = plateau.getIrrigationsPosees().length -plateau.getIrrigationsDisponibles().length;
        if (!actionsRealiseesTour[irrigation.ordinal()] && (irrigationPossable==3))
            return irrigation;

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
    public void actionIrrigation(Plateau plateau, PiocheIrrigation piocheIrrigation,
                                 PiocheSectionBambou piocheSectionBambou) {
        Irrigation[] irrigationsDisponibles = plateau.getIrrigationsDisponibles();
        if (irrigationsDisponibles.length > 0){
            Position positionIrrigation1 = irrigationsDisponibles[0].getPositions().get(0);
            Position positionIrrigation2 = irrigationsDisponibles[0].getPositions().get(1);

            if (!piocheIrrigation.isEmpty()) {
                Irrigation irrigationAAdd = piocheIrrigation.pioche(positionIrrigation1, positionIrrigation2);
                plateau.poseIrrigation(irrigationAAdd);
            }
        }
    }

    @Override

    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs)  {
        Jardinier jardinier = plateau.getJardinier();
        List<Position> listPositionPossible = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(),jardinier.getPosition());
        jardinier.move(listPositionPossible.get(listPositionPossible.size()-1));
        Optional<Parcelle> parcelleJardinier = GestionParcelles.chercheParcelle(plateau.getParcelles(),listPositionPossible.get(0));

        if(parcelleJardinier.isPresent() && parcelleJardinier.get().getClass().equals(ParcelleCouleur.class)) {
            ParcelleCouleur parcelleCouleurJardinier = (ParcelleCouleur) parcelleJardinier.get();
            plateau.poseBambou(parcelleCouleurJardinier,piocheSectionBambou.pioche(parcelleCouleurJardinier.getCouleur()));
        }
    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs) {

    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle, PiocheObjectifJardinier piocheObjectifJardinier, PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {
        Objectif objectif;
        if (!piocheObjectifPanda.isEmpty()) objectif = piocheObjectifPanda.pioche();
        else if (!piocheObjectifParcelle.isEmpty()) objectif = piocheObjectifParcelle.pioche();
        else objectif = piocheObjectifJardinier.pioche();
        objectifs.add(objectif);
    }


}
