package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.MaitreDuJeu;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
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
    private Set<Irrigation> irrigationsPosees;
    private Set<Irrigation> irrigationsDisponibles;


    // Définition des constructeurs

    /**
     * Construit le plateau du jeu
     * Place l'étang, le panda et le jardinier en (0,0)
     */
    public Plateau() {
        // Initialisation des attributs
        parcelleEtVoisinesList = new HashMap<>();
        positionsDisponibles = new HashSet<>(6);
        bambouList = new HashSet<>();
        panda = new Panda();
        jardinier = new Jardinier();
        irrigationsPosees = new HashSet<>();
        irrigationsDisponibles = new HashSet<>();

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
     * Renvoie les parcelles posées
     * @return un tableau des parcelles posées
     */
    public Parcelle[] getParcelles() {
        return parcelleEtVoisinesList.keySet().toArray(new Parcelle[0]);
    }

    /**
     * Renvoie les parcelles voisines de la parcelle demandée
     * @param parcelle la parcelle donnée pour chercher les voisines
     * @return un tableau des parcelles voisines demandées
     * @throws ParcelleNonPoseeException si la parcelle donnée ne se trouve pas sur le plateau
     */
    public Parcelle[] getVoisinesParcelle(@NotNull Parcelle parcelle) throws ParcelleNonPoseeException {
        if (parcelleEtVoisinesList.containsKey(parcelle)) {
            return parcelleEtVoisinesList.get(parcelle);
        }
        throw new ParcelleNonPoseeException(parcelle);
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
    public boolean addIrrigation(Position position1, Position position2){
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
                    SectionBambou sectionBambou = MaitreDuJeu.PIOCHE_SECTION_BAMBOU.pioche(parcelleC1.getCouleur());
                    //addBambou(parcelleC1,sectionBambou);
                }
                //irrige et pose un bambou si la parcelle n'est pas irrigée
                if (!parcelleC2.isIrriguee()){
                    parcelleC2.setIrriguee(true);
                    SectionBambou sectionBambou = MaitreDuJeu.PIOCHE_SECTION_BAMBOU.pioche(parcelleC2.getCouleur());
                    //addBambou(parcelleC2,sectionBambou);
                }
                //met a jour le set des irrigations disponibles avec les nouvelles possibilités
                //addIrrigationDisponible(irrigationAAdd);
            }
        }
        return ajoute;
    }
}