package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
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
    private Set<Irrigation> irrigationsDisponibles;
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

    /* *************************************************************************************************************
     * Ajouter getBambou(Position position)
     * Ajouter getBambousCouleur(Couleur couleur)
     **************************************************************************************************************/

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
    public Set<Irrigation> getIrrigationsPosees(){
        return irrigationsPosees;
    }

    /**
     * Renvoie les irrigations disponibles
     * @return le set des irrigations disponibles
     */
    public Set<Irrigation> getIrrigationsDisponibles(){
        return irrigationsDisponibles;
    }

    public void setIrrigationsDisponibles(Set<Irrigation> irrigationsDisponibles){
        this.irrigationsDisponibles = irrigationsDisponibles;
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
        Optional<Set<Irrigation>> newIrrigationsDisponible = GestionIrrigation.checkIrrigationsAutour(this.parcelleEtVoisinesList, parcelle, this.irrigationsDisponibles, irrigationsPosees);
        if (newIrrigationsDisponible.isPresent()) setIrrigationsDisponibles(newIrrigationsDisponible.get());
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
     * Ajoute une irrigation entre les parcelles aux positions données
     * @param position1 position de la 1ere parcelle
     * @param position2 position de la 2eme parcelle
     */
    public boolean poseIrrigation(Position position1, Position position2){
        Optional<Parcelle> parcelle1 = GestionParcelles.chercheParcelle(getParcelles(),position1);
        Optional<Parcelle> parcelle2 = GestionParcelles.chercheParcelle(getParcelles(),position2);
        boolean ajoute = false;
        if (position1 != position2 && parcelle1.isPresent() && parcelle2.isPresent() && parcelle1.get().getClass() != Etang.class && parcelle2.get().getClass() != Etang.class){
            ParcelleCouleur parcelleC1 = (ParcelleCouleur) parcelle1.get();
            ParcelleCouleur parcelleC2 = (ParcelleCouleur) parcelle2.get();
            List<Position> positions = new ArrayList<>();
            positions.add(position1);
            positions.add(position2);
            Irrigation irrigationAAdd = new Irrigation(positions);
            //pose l'irrigation si elle est présente dans les irrigations disponibles
            for (Irrigation irrigationDisponible : irrigationsDisponibles){
                if (irrigationAAdd.equals(irrigationDisponible)){
                    irrigationsPosees.add(irrigationAAdd);
                    ajoute = true;
                    AfficheurPieces.poseIrrigation(irrigationAAdd);
                    break;
                }
            }
            if (ajoute){
                //supprime l'irrigation posées du set des irrigations disponibles
                Set<Irrigation> setIrrigationsDispo = new HashSet<>();
                for (Irrigation irrigation : irrigationsDisponibles){
                    if (!(irrigation.equals(irrigationAAdd))) setIrrigationsDispo.add(irrigation);
                }
                irrigationsDisponibles = setIrrigationsDispo;
                //irrige et pose un bambou si la parcelle n'est pas irrigée
                if (!parcelleC1.isIrriguee()){
                    parcelleC1.setIrriguee(true);
                    poseBambou(parcelleC1, piocheBambou.pioche(parcelleC1.getCouleur()));
                }
                //irrige et pose un bambou si la parcelle n'est pas irrigée
                if (!parcelleC2.isIrriguee()){
                    parcelleC2.setIrriguee(true);
                    poseBambou(parcelleC2, piocheBambou.pioche(parcelleC2.getCouleur()));
                }
                //met a jour le set des irrigations disponibles avec les nouvelles possibilités
                Optional<Set<Irrigation>> irrigationsDisponoblesSet = GestionIrrigation.addIrrigationDisponible(parcelleEtVoisinesList, irrigationAAdd, irrigationsDisponibles, irrigationsPosees);
                if (irrigationsDisponoblesSet.isPresent()) setIrrigationsDisponibles(irrigationsDisponoblesSet.get());
            }
        }
        return ajoute;
    }

    /**
     * Pose du bambou uniquement si la parcelle est irriguée, ajoute une nouvelle section de bambou si il
     * existe déja un bambou sur la parcelle, sinon crée un nouveau bambou puis ajoute une section de bambou
     * @param parcelleCouleur parcelle sur laquelle on veut faire pousser du bambou
     * @param sectionBambou sectionBambou pioché dans la Pioche Bamboou
     * @return true si le bambou a bien été posé, false sinon
     */
    public boolean poseBambou(ParcelleCouleur parcelleCouleur, SectionBambou sectionBambou){
        if (parcelleCouleur.isIrriguee()){
            Optional<Bambou> optionalBambou = GestionBambous.chercheBambou(getBambous(), parcelleCouleur.getPosition());
            //1er cas: déja bambou
            if (optionalBambou.isPresent()){
                Bambou bambou = optionalBambou.get();
                try {
                    if (!bambou.isTailleMaximum()) bambou.ajouteSectionBambou(sectionBambou);
                    return true;
                } catch (AjoutCouleurException e) {
                    System.out.println(e);
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
                    System.out.println(e);
                }
            }
        }
        return false;
    }

    /**
     * eleve un bambou de la parcelle
     * @param bambou un bambou
     * @return une sectionBambou
     */
    public SectionBambou mangeBambou (Bambou bambou) {
        return bambou.prendSectionBambou();
    }

    /**
     * deplacement du Panda
     * @param position la position du panda
     */
    public void deplacementPanda(Position position) {
        panda.move(position);

        Optional<Parcelle> parcelle = GestionParcelles.chercheParcelle(getParcelles(),position);
        if(parcelle.isPresent()) {
            if (parcelle.get().getClass().equals(ParcelleCouleur.class)) {
                Optional<Bambou> bambou = GestionBambous.chercheBambou(getBambous(), position);
                if (bambou.isPresent() && !bambou.get().isEmpty()) {
                    mangeBambou(bambou.get());
                }
            }
        }
    }

    /**
     * Déplace le jardinier et ajoute le bambous sur la parcelle et ses voisins irriguées et de la même couleur
     * @param position position de la parcelle où on veut déplavcer le jardinier
     * @throws ParcelleNonPoseeException
     */
    public void deplacementJardinier(Position position) throws ParcelleNonPoseeException {
        // déplacement du jardinier
        jardinier.move(position);

        Optional<Parcelle> parcelleJardinier = GestionParcelles.chercheParcelle(getParcelles(),position);
        if (parcelleJardinier.isPresent() && parcelleJardinier.get().getClass().equals(ParcelleCouleur.class)){
            ParcelleCouleur parcelleCouleurJardinier = (ParcelleCouleur) parcelleJardinier.get();
            Couleur couleurParcelleJardinier = parcelleCouleurJardinier.getCouleur();

            //ajout du bambou sur la parcelle du Jardinier
            if (parcelleCouleurJardinier.isIrriguee()) poseBambou(parcelleCouleurJardinier, piocheBambou.pioche(parcelleCouleurJardinier.getCouleur()));

            //ajout du bambou sur les parcelles voisines irriguées
            Parcelle[] voisines = getVoisinesParcelle(parcelleCouleurJardinier);
            for (Parcelle parcelle : voisines){
                if (parcelle.getClass().equals(ParcelleCouleur.class)) {
                    ParcelleCouleur parcelleVoisine = (ParcelleCouleur) parcelle;
                    if (parcelleVoisine.isIrriguee() && parcelleVoisine.getCouleur().equals(couleurParcelleJardinier))
                        poseBambou(parcelleVoisine, piocheBambou.pioche(parcelleCouleurJardinier.getCouleur()));
                }
            }

        }
    }
}