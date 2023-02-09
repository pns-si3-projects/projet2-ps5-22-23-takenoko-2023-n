package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.*;
import fr.cotedazur.univ.polytech.startingpoint.personnage.AfficheurPersonnage;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.*;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheSectionBambou;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Représente les pièces posées du jeu.
 * Contient les parcelles, le panda, le jardinier et les bambous.
 * @author équipe N
 */
public class Plateau {
    // Définition des attributs

    private final Map<Parcelle, Parcelle[]> parcelleEtVoisinesList;
    private final Set<Position> positionsDisponibles;
    private final Set<Bambou> bambouList;
    private final Panda panda;
    private final Jardinier jardinier;
    private final Set<Irrigation> irrigationsPosees;
    private final Set<Irrigation> irrigationsDisponibles;
    private final PiocheSectionBambou piocheBambou;


    // Définition des constructeurs

    /**
     * Construit le plateau du jeu
     * Place l'étang, le panda et le jardinier en (0,0)
     */
    public Plateau(PiocheSectionBambou piocheSectionBambou) {
        // Initialisation des attributs
        parcelleEtVoisinesList = new HashMap<>();
        positionsDisponibles = new HashSet<>(6);
        bambouList = new HashSet<>();
        panda = new Panda();
        jardinier = new Jardinier();
        irrigationsPosees = new HashSet<>();
        irrigationsDisponibles = new HashSet<>();
        piocheBambou = piocheSectionBambou;
        // Ajout de l'étang dans la liste des parcelles
        poseEtang();
    }

    /**
     * Pose l'étang sur le plateau
     */
    private void poseEtang() {
        Parcelle etang = GestionParcelles.ETANG;
        Parcelle[] voisinesEtang = GestionParcelles.ajouteVoisinesDisponibles(new ArrayList<>(), etang);

        parcelleEtVoisinesList.put(etang, voisinesEtang);
        positionsDisponibles.addAll(GestionParcelles.positionsDisponibles(getParcelles(), voisinesEtang));
    }


    // Accesseurs

    /**
     * Renvoie les parcelles et leurs voisines
     * @return une map des parcelles et leurs voisines
     */
    public Map<Parcelle, Parcelle[]> getParcelleEtVoisinesList() {
        return new HashMap<>(parcelleEtVoisinesList);
    }

    /**
     * Renvoie les parcelles posées
     * @return un tableau des parcelles posées
     */
    public Parcelle[] getParcelles() {
        return GestionParcelles.getParcelles(getParcelleEtVoisinesList());
    }

    /**
     * Renvoie les parcelles voisines de la parcelle demandée
     * @param parcelle la parcelle donnée pour chercher les voisines
     * @return un tableau des parcelles voisines demandées
     * @throws ParcelleNonPoseeException si la parcelle donnée ne se trouve pas sur le plateau
     */
    public Parcelle[] getVoisinesParcelle(@NotNull Parcelle parcelle) throws ParcelleNonPoseeException {
        return GestionParcelles.getVoisinesParcelle(getParcelleEtVoisinesList(), parcelle);
    }

    /**
     * Renvoie les positions disponibles
     * @return un tableau des positions disponibles
     */
    public Position[] getPositionsDisponibles() {
        return positionsDisponibles.toArray(new Position[0]);
    }

    /**
     * Renvoie les bambous posés
     * @return un tableau des bambous posés
     */
    public Bambou[] getBambous() {
        return bambouList.toArray(new Bambou[0]);
    }

    /**
     * Renvoie le panda
     * @return le panda
     */
    public Panda getPanda() {
        return panda;
    }

    /**
     * Renvoie le jardinier
     * @return le jardinier
     */
    public Jardinier getJardinier() {
        return jardinier;
    }

    /**
     * Renvoie les irrigations posées sur le plateau
     * @return le set des irrigations posées sur le plateau
     */
    public Irrigation[] getIrrigationsPosees(){
        return irrigationsPosees.toArray(new Irrigation[0]);
    }

    /**
     * Renvoie les irrigations disponibles
     * @return le set des irrigations disponibles
     */
    public Irrigation[] getIrrigationsDisponibles(){
        return irrigationsDisponibles.toArray(new Irrigation[0]);
    }


    // Méthodes d'utilisation

    /**
     * Permet de poser une parcelle sur le plateau
     * @param parcelle la parcelle à poser
     * @return {@code true} si la parcelle a été posée
     */
    public boolean poseParcelle(ParcelleCouleur parcelle) {
        if (!positionsDisponibles.contains(parcelle.getPosition())) {
            return false;
        }

        // On prend les voisines posées
        List<Parcelle> voisinesParcelle;
        try {
            voisinesParcelle = GestionParcelles.futuresVoisines(getParcelles(), parcelle);
        } catch (ParcelleExistanteException e) {
            return false;
        }
        int nbVoisines = voisinesParcelle.size();

        // Si pas d'étang et inférieur à 2 voisines, ou supérieur à 6 voisines
        if ((nbVoisines < 2 && !voisinesParcelle.contains(new Etang())) || nbVoisines > 6) {
            throw new AssertionError("Nombre incorrecte de voisines");
        }

        // Se fait connaitre pour ses nouvelles voisines
        addParcelleVoisine(voisinesParcelle, parcelle);

        // On ajoute la parcelle au plateau ainsi que toutes ses voisines
        Parcelle[] toutesVoisinesParcelle = GestionParcelles.ajouteVoisinesDisponibles(voisinesParcelle, parcelle);
        AfficheurParcelle.parcellePose(parcelle);
        parcelleEtVoisinesList.put(parcelle, toutesVoisinesParcelle);

        // On modifie les positions disponibles
        positionsDisponibles.addAll(GestionParcelles.positionsDisponibles(getParcelles(), toutesVoisinesParcelle));
        positionsDisponibles.remove(parcelle.getPosition());

        // Si voisine de l'étang, la parcelle est irriguée et a une section de bambou
        if (voisinesParcelle.contains(GestionParcelles.ETANG)){
            parcelle.setIrriguee(true);
            poseBambou(parcelle, piocheBambou.pioche(parcelle.getCouleur()));
        }

        // On met à jour le set d'irrigations disponible
        Set<Irrigation> newIrrigationsDisponible =
                GestionIrrigation.checkIrrigationsAutour(parcelleEtVoisinesList, parcelle, irrigationsPosees);
        irrigationsDisponibles.addAll(newIrrigationsDisponible);
        return true;
    }

    /**
     * Ajoute la parcelle ciblée à la liste des voisines des parcelles qui lui sont voisines
     * @param voisines la liste des voisines de la parcelle ciblée
     * @param parcelle la parcelle ciblée
     */
    private void addParcelleVoisine(List<Parcelle> voisines, Parcelle parcelle) {
        for (Parcelle parcelleVoisine : voisines) {
            Parcelle[] voisinesVoisine = parcelleEtVoisinesList.get(parcelleVoisine);
            int indiceVoisine = GestionParcelles.indiceVoisine(parcelleVoisine, parcelle);
            voisinesVoisine[indiceVoisine] = parcelle;
            parcelleEtVoisinesList.put(parcelleVoisine, voisinesVoisine);
        }
    }

    /**
     * Renvoie si l'irrigation donnée est posée
     * @param irrigation irrigation à poser
     * @return {@code true} si l'irrigation est posée
     */
    public boolean poseIrrigation(Irrigation irrigation) {
        if (!ajouteIrrigation(irrigation)) {
            return false;
        }
        irrigueParcelles(irrigation);

        // Met à jour les irrigations disponibles avec les nouvelles possibilités
        Set<Irrigation> nouvIrrigationsDispo =
                GestionIrrigation.addIrrigationDisponible(parcelleEtVoisinesList, irrigation, irrigationsPosees);
        irrigationsDisponibles.addAll(nouvIrrigationsDispo);

        return true;
    }

    /**
     * Renvoie si l'irrigation est posée sur le plateau
     * @param irrigation l'irrigation à poser
     * @return {@code true} si l'irrigation est posée sur le plateau
     */
    private boolean ajouteIrrigation(Irrigation irrigation) {
        if (irrigation.getPositions().isEmpty()) {
            return false;
        }

        for (Irrigation irrigationDisponible : irrigationsDisponibles) {
            if (irrigation.equals(irrigationDisponible)){
                irrigationsPosees.add(irrigation);
                irrigationsDisponibles.remove(irrigation); // Supprime l'irrigation posée de la liste des disponibles

                AfficheurPieces.poseIrrigation(irrigation);
                return true;
            }
        }
        return false;
    }

    /**
     * Irrigue les parcelles de l'irrigation
     * @param irrigation l'irrigation posée
     */
    private void irrigueParcelles(Irrigation irrigation) {
        List<Position> positions = irrigation.getPositions();
        Optional<Parcelle> optParcelle1 = GestionParcelles.chercheParcelle(getParcelles(), positions.get(0));
        Optional<Parcelle> optParcelle2 = GestionParcelles.chercheParcelle(getParcelles(), positions.get(1));
        if (optParcelle1.isEmpty() || optParcelle2.isEmpty()) {
            throw new AssertionError("Parcelle introuvable");
        }

        Parcelle parcelle1 = optParcelle1.get();
        Parcelle parcelle2 = optParcelle2.get();
        if (!parcelle1.getClass().equals(Etang.class) && !parcelle2.getClass().equals(Etang.class)) {
            ParcelleCouleur parcelleC1 = (ParcelleCouleur) parcelle1;
            ParcelleCouleur parcelleC2 = (ParcelleCouleur) parcelle2;

            if (!parcelleC1.isIrriguee()) {
                parcelleC1.setIrriguee(true);
                poseBambou(parcelleC1, piocheBambou.pioche(parcelleC1.getCouleur()));
            }
            if (!parcelleC2.isIrriguee()) {
                parcelleC2.setIrriguee(true);
                poseBambou(parcelleC2, piocheBambou.pioche(parcelleC2.getCouleur()));
            }
        }
    }

    /**
     * Pose du bambou uniquement si la parcelle est irriguée
     * @param parcelleCouleur parcelle sur laquelle on veut faire pousser du bambou
     * @param sectionBambou sectionBambou pioché dans la Pioche Bamboou
     * @return true si le bambou a bien été posé, false sinon
     */
    public boolean poseBambou(ParcelleCouleur parcelleCouleur, SectionBambou sectionBambou) {
        if (parcelleCouleur.isIrriguee()){
            Optional<Bambou> optionalBambou = GestionBambous.chercheBambou(getBambous(), parcelleCouleur.getPosition());

            //1er cas: déja bambou
            if (optionalBambou.isPresent()){
                Bambou bambou = optionalBambou.get();
                try {
                    if (!bambou.isTailleMaximum()) {
                        bambou.ajouteSectionBambou(sectionBambou);
                        return true;
                    }
                } catch (AjoutCouleurException e) {
                    throw new AssertionError(e);
                }
            }

            //2eme cas: pas encore de bambou
            else {
                Bambou bambou = new Bambou(parcelleCouleur);
                try {
                    bambou.ajouteSectionBambou(sectionBambou);
                    bambouList.add(bambou);
                    return true;
                }
                catch (AjoutCouleurException e) {
                    throw new AssertionError(e);
                }
            }
        }
        return false;
    }

    /**
     * Déplacement du Panda
     * @param position la nouvelle position du panda
     * @return la section de bambou mangée
     */
    public Optional<SectionBambou> deplacementPanda(Position position) {
        panda.move(position);
        AfficheurPersonnage.deplacePersonnage(panda);

        Optional<Parcelle> parcelle = GestionParcelles.chercheParcelle(getParcelles(), position);
        if (parcelle.isPresent() && parcelle.get().getClass().equals(ParcelleCouleur.class)) {
            Optional<Bambou> bambou = GestionBambous.chercheBambou(getBambous(), position);

            if (bambou.isPresent() && !bambou.get().isEmpty()) {
                 return Optional.of(mangeBambou(bambou.get()));
            }
        }
        return Optional.empty();
    }

    /**
     * Mange une section de bambou de la parcelle
     * @param bambou un bambou
     * @return une sectionBambou
     */
    private SectionBambou mangeBambou (Bambou bambou) {
        return bambou.prendSectionBambou();
    }

    /**
     * Déplace le jardinier et ajoute le bambou sur la parcelle et ses voisines irriguées et de la même couleur
     * @param position la position de la parcelle où déplacer le jardinier
     */
    public void deplacementJardinier(Position position) {
        // déplacement du jardinier
        jardinier.move(position);
        AfficheurPersonnage.deplacePersonnage(jardinier);

        Optional<Parcelle> parcelleJardinier = GestionParcelles.chercheParcelle(getParcelles(),position);
        if (parcelleJardinier.isPresent() && parcelleJardinier.get().getClass().equals(ParcelleCouleur.class)){
            ParcelleCouleur parcelleCouleurJardinier = (ParcelleCouleur) parcelleJardinier.get();
            poseBambousParcelle(parcelleCouleurJardinier);
        }
    }

    /**
     * Pose les bambous sur la parcelle donnée (si irriguée) et les voisines irriguées de même couleur
     * @param parcelleCouleur la parcelle de départ
     */
    private void poseBambousParcelle(ParcelleCouleur parcelleCouleur) {
        Couleur couleurParcelleJardinier = parcelleCouleur.getCouleur();

        //ajout du bambou sur la parcelle du Jardinier
        if (parcelleCouleur.isIrriguee()) {
            poseBambou(parcelleCouleur, piocheBambou.pioche(parcelleCouleur.getCouleur()));
        }

        //ajout du bambou sur les parcelles voisines irriguées
        Parcelle[] voisines;
        try {
            voisines = getVoisinesParcelle(parcelleCouleur);
        } catch (ParcelleNonPoseeException e) {
            throw new AssertionError(e);
        }

        for (Parcelle parcelle : voisines) {
            if (parcelle.getClass().equals(ParcelleCouleur.class)) {
                ParcelleCouleur parcelleVoisine = (ParcelleCouleur) parcelle;

                if (parcelleVoisine.isIrriguee() && parcelleVoisine.getCouleur().equals(couleurParcelleJardinier)) {
                    poseBambou(parcelleVoisine, piocheBambou.pioche(parcelleCouleur.getCouleur()));
                }
            }
        }
    }
}