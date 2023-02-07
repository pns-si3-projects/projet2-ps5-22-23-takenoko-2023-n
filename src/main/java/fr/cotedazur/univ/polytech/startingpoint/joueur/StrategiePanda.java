package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
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
    public void actionParcelle(Plateau plateau, PiocheParcelle piocheParcelle, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs)  {
        boolean parcellepose = false;
        ParcelleCouleur parcelleCouleur=null;
        ParcellePioche[] pioche = new ParcellePioche[0];
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
        Set<Irrigation> irrigationsDisponibles = plateau.getIrrigationsDisponibles();
        Irrigation irrigationAAdd = null;
        for (Irrigation irrigation: irrigationsDisponibles){
            irrigationAAdd = irrigation;
            for (Position positionIrrigation : irrigation.getPositions()){
                Optional<Parcelle> optParcelle = GestionParcelles.chercheParcelle(plateau.getParcelles(), positionIrrigation);
                if (optParcelle.isPresent()) {
                    ParcelleCouleur pc = (ParcelleCouleur) optParcelle.get();
                    if (!pc.isIrriguee()) irrigationAAdd = irrigation;
                    break;
                }
            }
        }
        if(irrigationAAdd != null) plateau.poseIrrigation(irrigationAAdd.getPositions().get(0), irrigationAAdd.getPositions().get(1));
    }

    @Override

    public void actionJardinier(Plateau plateau, PiocheSectionBambou piocheSectionBambou, List<Objectif> objectifs)  {
        Jardinier jardinier = plateau.getJardinier();
        List<Position> listPositionPossible = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(),jardinier.getPosition());
        jardinier.move(listPositionPossible.get(listPositionPossible.size()-1));
        Optional<Parcelle> parcelleJardinier = GestionParcelles.chercheParcelle(plateau.getParcelles(),listPositionPossible.get(0));

        if(parcelleJardinier.isPresent()) {
            if(parcelleJardinier.get().getClass().equals(ParcelleCouleur.class)){
                ParcelleCouleur parcelleCouleurJardinier = (ParcelleCouleur) parcelleJardinier.get();
                plateau.poseBambou(parcelleCouleurJardinier,piocheSectionBambou.pioche(parcelleCouleurJardinier.getCouleur()));
            }
        }
    }

    public void deplacePossible() {

    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs,SectionBambou[] listeBambouMange) {
        List<Objectif> objectifPanda = recupreObjectifPanda(objectifs);
        List<Position> listePositionPossibleAvecBambou = new ArrayList<>();

        ObjectifPanda objectifPandaMaxPoint = getMaxObjectifPanda(objectifPanda);
        Couleur couleurAManger = objectifPandaMaxPoint.getCouleur();

        Panda  panda = plateau.getPanda();
        List<Position> listPositionPossibleDeplacement = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(),panda.getPosition());

        for ( Position positionPossible : listPositionPossibleDeplacement ) {
            if (GestionBambous.chercheBambou(plateau.getBambous(), positionPossible).isPresent()) {
                listePositionPossibleAvecBambou.add(positionPossible);
            }
        }
        for (Position position : listePositionPossibleAvecBambou) {
            Optional<Parcelle> parcelleRegarder = GestionParcelles.chercheParcelle(plateau.getParcelles(),position);
            if(parcelleRegarder.get().getClass().equals(ParcelleCouleur.class)) {
                ParcelleCouleur parcelleCouleur = (ParcelleCouleur) parcelleRegarder.get();
                if(parcelleCouleur.getCouleur().equals(couleurAManger)) {
                    panda.move(parcelleCouleur.getPosition());
                }
            }
        }
    }

    /**
     * recupre la liste des ObjectifPanda que objectifs
     * @param objectifs les objectifs du Joueur
     * @return la liste des objectifPanda
     */
    public List<Objectif> recupreObjectifPanda(List<Objectif> objectifs) {
        List<Objectif> objectifsPanda = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            if(objectif.getClass().equals(ObjectifPanda.class)) {
                objectifsPanda.add(objectif);
            }
        }
        return objectifsPanda;
    }

    /**
     * recupre l'objectidPanda valant le plus de point
     * @param objectifsPanda la liste des objectifPanda
     * @return l'objectifPanda valant le plus de point
     */
    private ObjectifPanda getMaxObjectifPanda(List<Objectif> objectifsPanda) {
        ObjectifPanda objectifPandaMax = null;

        for (Objectif objectif : objectifsPanda) {
            if (objectif.getClass().equals(ObjectifPanda.class)) {
                if (objectifPandaMax == null || objectifPandaMax.getNombrePoints() < objectif.getNombrePoints()) {
                    objectifPandaMax = (ObjectifPanda) objectif;
                }
            }
        }

        return objectifPandaMax;
    }

    @Override
    public void actionObjectif(PiocheObjectifParcelle piocheObjectifParcelle, PiocheObjectifJardinier piocheObjectifJardinier, PiocheObjectifPanda piocheObjectifPanda, List<Objectif> objectifs) {
        Objectif objectif = null;
        if (!piocheObjectifPanda.isEmpty()) objectif = piocheObjectifPanda.pioche();
        else if (!piocheObjectifParcelle.isEmpty()) objectif = piocheObjectifParcelle.pioche();
        else objectif = piocheObjectifJardinier.pioche();
        objectifs.add(objectif);
    }


}
