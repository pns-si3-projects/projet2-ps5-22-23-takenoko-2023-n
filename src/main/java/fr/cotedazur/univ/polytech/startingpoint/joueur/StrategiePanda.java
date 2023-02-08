package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
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
            Optional<List<Position>> positionIrrigation = irrigationsDisponibles[0].getPositions();

            if (!piocheIrrigation.isEmpty() && positionIrrigation.isPresent()) {
                Irrigation irrigationAAdd = piocheIrrigation.pioche(positionIrrigation.get());
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
            System.out.println(e);
        }
    }

    @Override
    public void actionPanda(Plateau plateau, List<Objectif> objectifs,SectionBambou[] listeBambouMange) {
        Position positionDeplacer=null;
        boolean estDeplacer = false;
        List<Objectif> objectifPanda = recupreObjectifPanda(objectifs);
        List<Position> listePositionPossibleAvecBambou = new ArrayList<>();

        Optional<ObjectifPanda> objectifPandaMaxPointOpitionale = getMaxObjectifPanda(objectifPanda);
        Couleur couleurAManger = Couleur.JAUNE;
        if(objectifPandaMaxPointOpitionale.isPresent()){
            ObjectifPanda objectifPandaMaxPoint = (ObjectifPanda) objectifPandaMaxPointOpitionale.get();
            if (objectifPandaMaxPoint.getBambousAManger().size()==2)
                couleurAManger = objectifPandaMaxPoint.getBambousAManger().get(0).getCouleur();
            else {
                couleurAManger = getCouleurManquante(listeBambouMange);
            }
        }

        Panda panda = plateau.getPanda();
        List<Position> listPositionPossibleDeplacement = GestionPersonnages.deplacementsPossibles(plateau.getParcelleEtVoisinesList(),panda.getPosition());

        listePositionPossibleAvecBambou = possitionDisponible(listPositionPossibleDeplacement,plateau);
        for (Position position : listePositionPossibleAvecBambou) {
            Optional<Parcelle> parcelleRegarder = GestionParcelles.chercheParcelle(plateau.getParcelles(), position);
            if (parcelleRegarder.isPresent() && parcelleRegarder.get().getClass().equals(ParcelleCouleur.class)) {
                ParcelleCouleur parcelleCouleur = (ParcelleCouleur) parcelleRegarder.get();
                if (parcelleCouleur.getCouleur().equals(couleurAManger)) {
                    positionDeplacer = position;
                    break;
                }
            }
        }
        if (!estDeplacer) {
            positionDeplacer = listPositionPossibleDeplacement.get(0);
            plateau.deplacementPanda(positionDeplacer);
        }
        else {
            plateau.deplacementPanda(listePositionPossibleAvecBambou.get(0));
        }
    }

    /**
     * recuper la lists des deplacement possibme
     * @param listPositionPossibleDeplacement la liste des deplacement possible
     * @param plateau le plateau
     * @return une liste de possition qu ne continet que des bambou de certaine couleur
     */
    public List<Position> possitionDisponible(List<Position> listPositionPossibleDeplacement, Plateau plateau) {
        List<Position> listePositionPossibleAvecBambou = new ArrayList<>();
        for (Position positionPossible : listPositionPossibleDeplacement) {
            if (GestionBambous.chercheBambou(plateau.getBambous(), positionPossible).isPresent()) {
                listePositionPossibleAvecBambou.add(positionPossible);
            }
        }
        return listePositionPossibleAvecBambou;
    }

    /**
     * Renvoie la couleur manquante dans la liste des bambous mangés
     * @param listeBambouManges liste de sections de bambous mangés
     * @return la couleur qu'il manque dans la liste
     */
    public Couleur getCouleurManquante(SectionBambou[] listeBambouManges){
        List<Couleur> couleurList = new ArrayList<>();
        couleurList.add(Couleur.VERTE);
        couleurList.add(Couleur.JAUNE);
        couleurList.add(Couleur.ROSE);

        for (Couleur couleur : couleurList){
            boolean dejaMange = false;
            for (SectionBambou sectionBambou : listeBambouManges){
                if (sectionBambou.getCouleur().equals(couleur)) {
                    dejaMange = true;
                    break;
                }
                if (!dejaMange) return couleur;
            }
        }
        return couleurList.get(0);
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
    private Optional<ObjectifPanda> getMaxObjectifPanda(List<Objectif> objectifsPanda) {
        ObjectifPanda objectifPandaMax = null;

        for (Objectif objectif : objectifsPanda) {
            if (objectif.getClass().equals(ObjectifPanda.class) &&
                    (objectifPandaMax == null || objectifPandaMax.getNombrePoints() < objectif.getNombrePoints())) {
                    objectifPandaMax = (ObjectifPanda) objectif;
                }
            }

        return Optional.ofNullable(objectifPandaMax);
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
