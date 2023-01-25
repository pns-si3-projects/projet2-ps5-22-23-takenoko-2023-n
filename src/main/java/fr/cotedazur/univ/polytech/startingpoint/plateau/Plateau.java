package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.*;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;

import java.util.*;

/**
 * Classe représentant le plateau du jeu, soit les parcelles, le panda, le jardinier
 * Il est possible d'avoir la liste des voisines d'une parcelle et la liste des positions disponibles sur le Plateau
 * @author equipe N
 */
public class Plateau {
    // Définition des attributs
    private final Map<Parcelle, Parcelle[]> parcelleEtVoisinesList;
    private final Set<Bambou> bambous;
    private final Panda panda;
    private final Jardinier jardinier;
    private final Etang etang;
    private final List<Position> positionsDisponibles;
    private static final GestionnaireModificationPlateau GESTIONNAIRE_MODIFICATION_PLATEAU = new GestionnaireModificationPlateau();
    private Set<Irrigation> irrigationsPosees;
    private Set<Irrigation> irrigationsDisponibles;

    // Définition des constructeurs
    /**
     * Constructeur par defaut, initialise le panda, le jardinier et l'étang
     */
    public Plateau() {
        // Initialisation des attributs
        parcelleEtVoisinesList = new HashMap<>();
        bambous = new HashSet<>();
        panda = new Panda();
        jardinier = new Jardinier();
        etang = new Etang();
        positionsDisponibles = new ArrayList<>();
        irrigationsPosees = new HashSet<>();
        irrigationsDisponibles = new HashSet<>();

        // Ajout des voisines de l'étang et des parcelles disponibles
        addVoisinesEtangEtDisponibles();
    }

    /**
     * Ajoute les voisines de l'étang ainsi que les premières ParcelleDisponible
     */
    private void addVoisinesEtangEtDisponibles() {
       Position positionEtang = etang.position();
       Parcelle[] listParcelle = new Parcelle[6];
       for (int i=0; i<6; i++) {
           listParcelle[i] = GESTIONNAIRE_MODIFICATION_PLATEAU.creeParcelleDisponible(i, positionEtang);
       }
       parcelleEtVoisinesList.put(etang, listParcelle);
       addPositionDisponibleEtang();
    }

    /**
     * Ajoute les premières parcelles disponibles lorsque l'étang est posé
     */
    private void addPositionDisponibleEtang(){
        Parcelle[] listParcelleDisponibleEtang = parcelleEtVoisinesList.get(etang);
        for (Parcelle parcelle : listParcelleDisponibleEtang) {
            assert (parcelle.getClass() == ParcelleDisponible.class) : "Voisine de l'étang pas disponible à l'initialisation";
            positionsDisponibles.add(parcelle.position());
        }
    }


    // Accesseurs et méthodes toString
    /**
     * Renvoie un tableau des parcelles présentes sur le plateau
     * @return un tableau des parcelles du plateau
     */
    public Parcelle[] getParcelles() {
        Parcelle[] parcelles = new Parcelle[parcelleEtVoisinesList.size()];
        Iterator<Parcelle> iterateurParcelles = parcelleEtVoisinesList.keySet().iterator();
        for (int i=0; i<parcelles.length; i++) {
            parcelles[i] = iterateurParcelles.next();
        }
        return parcelles;
    }

    /**
     * Renvoie un tableau des bambous du plateau
     * @return un tableau des bambous du plateau
     * @implNote il faut qu'au moins une parcelle de couleur soit posée sur le plateau
     */
    public Bambou[] getBambous() {
        Bambou[] bambousTab = new Bambou[bambous.size()];
        Iterator<Bambou> iterateurBambous = bambous.iterator();
        for (int i=0; i<bambousTab.length; i++) {
            bambousTab[i] = iterateurBambous.next();
        }
        return bambousTab;
    }

    /**
     * Renvoie le bambou à la position donnée
     * @return le bambou à la position donnée
     */
    public Optional<Bambou> getBambou(Position position) {
        Bambou[] bambousTab = getBambous();
        for (Bambou bambou : bambousTab) {
            if (bambou.position().equals(position)) {
                return Optional.of(bambou);
            }
        }
        return Optional.empty();
    }

    /**
     * Renvoie la parcelle étang
     * @return la parcelle étang
     */
    public Etang getEtang() {
        return etang;
    }

    /**
     * Renvoie le panda
     * @return le Panda
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
     * Renvoie un tableau des positions disponibles à l'ajout d'une parcelle
     * @return un tableau des positions disponibles à l'ajout d'une parcelle
     */
    public Position[] getPositionsDisponible(){
        Position[] listPosition = new Position[positionsDisponibles.size()];
        for (int i=0; i<positionsDisponibles.size(); i++) {
            listPosition[i] = positionsDisponibles.get(i);
        }
        return listPosition;
    }

    /**
     * Renvoie les parcelles voisines d'une parcelle donnée
     * @param parcelle est la parcelle ciblée pour connaitre ses voisines
     * @return un tableau de parcelles qui sont les voisines de la parcelle ciblée
     * @throws ParcelleNonExistanteException si la parcelle ciblée n'a pas été crée ou ajoutée au plateau
     */
    public Parcelle[] getTableauVoisines(Parcelle parcelle) throws ParcelleNonExistanteException {
        if (parcelleEtVoisinesList.containsKey(parcelle)) {
            return parcelleEtVoisinesList.get(parcelle);
        }
        throw new ParcelleNonExistanteException(parcelle);
    }

    /**
     * Renvoie la parcelle désignée par la position
     * @param position est la position de la parcelle demandée
     * @return la parcelle à la position demandée
     */
    public Optional<Parcelle> getParcelle(Position position){
        return GESTIONNAIRE_MODIFICATION_PLATEAU.getParcelle(getParcelles(),position);
    }

    /**
     * Renvoie la liste des parcelles de la couleur demandée
     * @param couleur est la couleur demandée
     * @return la liste des parcelles de la couleur demandée
     */
    public List<ParcelleCouleur> getParcellesCouleur(Couleur couleur) {
        List<ParcelleCouleur> parcellesCouleur = new ArrayList<>();
        for (Parcelle parcelle : getParcelles()) {
            if (!parcelle.getClass().equals(Etang.class)) {
                ParcelleCouleur parcelleCouleur = (ParcelleCouleur) parcelle;
                if (parcelleCouleur.couleur() == couleur) parcellesCouleur.add(parcelleCouleur);
            }
        }
        return parcellesCouleur;
    }

    /**
     * Renvoie la liste des positions où se trouvent des bambous de la couleur demandée
     * @param couleur est la couleur demandée
     * @return la liste des positions où se trouvent des bambous de la couleur demandée
     */
    public List<Position> getBambousCouleur(Couleur couleur) {
        List<Position> positionsBambousCouleur = new ArrayList<>();
        for (Bambou bambou : getBambous()) {
            if (bambou.couleur() == couleur && !bambou.isEmptyBambou()) {
                positionsBambousCouleur.add(bambou.position());
            }
        }
        return positionsBambousCouleur;
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

    /**
     * Ajoute une irrigation entre les parcelles aux positions données
     * @param position1 position de la 1ere parcelle
     * @param position2 position de la 2eme parcelle
     */
    public void addIrrigation(Position position1, Position position2){
        Optional<Parcelle> parcelle1 = getParcelle(position1);
        Optional<Parcelle> parcelle2 = getParcelle(position2);
        if (position1 != position2 && parcelle1.isPresent() && parcelle2.isPresent()){
            List<Position> positions = new ArrayList<>();
            positions.add(position1);
            positions.add(position2);
            Irrigation nouvelleIrrigation = new Irrigation(positions);
            this.irrigationsPosees.add(nouvelleIrrigation);
            Set<Irrigation> setIrrigationsDispo = new HashSet<>();
            for (Irrigation irrigation : this.irrigationsDisponibles){
                if (!(irrigation.getPositions().get(0).equals(position1) && irrigation.getPositions().get(1).equals(position2))) {
                    if (!(irrigation.getPositions().get(0).equals(position2) && irrigation.getPositions().get(1).equals(position1))) setIrrigationsDispo.add(irrigation);
                }
            }
            this.irrigationsDisponibles = setIrrigationsDispo;
            addIrrigationDisponible(nouvelleIrrigation);
        }
    }

    /**
     * Ajoute les irrigations à la suite de celle en paramètre au set des irrigations disponibles si elles sont possible d'être posées
     * @param irrigation l'irrigation à partir de laquelle il faut rechercher les nouvelles irrigations possible
     */
    public void addIrrigationDisponible(Irrigation irrigation) {
        List<Position> positionsIrrigation = irrigation.getPositions();
        Position position1 = positionsIrrigation.get(0);
        Position position2 = positionsIrrigation.get(1);
        int x = position1.getX() + position2.getX();
        int y = position1.getY() + position2.getY();
        Position position3 = new Position(x, y);
        Optional<Parcelle> parcelle3 = getParcelle(position3);
        if (parcelle3.isPresent()) {
            for (Position position : positionsIrrigation) {
                List<Position> positionsIrrigationDisponible = new ArrayList<>();
                positionsIrrigationDisponible.add(position);
                positionsIrrigationDisponible.add(position3);
                Irrigation irrigationDisponible = new Irrigation(positionsIrrigationDisponible);
                this.irrigationsDisponibles.add(irrigationDisponible);
            }
        }
    }

    /**
     * Vérifie si il y a des irrigations autour de la parcelle en paramètre puis ajoute des irrigations disponibles par rapport aux irrigations trouvées
     * @param parcelleCouleur parcelle pour laquelle on veut verifier si il y a des irrigations autour
     * @throws ParcelleNonExistanteException
     */
    public void checkIrrigationsAutour(ParcelleCouleur parcelleCouleur) throws ParcelleNonExistanteException {
        try {
            Parcelle[] voisins = getTableauVoisines(parcelleCouleur);
            for (int i = 0; i < voisins.length - 1; i++) {
                Optional<Irrigation> irrigation = chercheIrrigation(voisins[i].position(), voisins[i + 1].position());
                if (irrigation.isPresent()) addIrrigationDisponible(irrigation.get());
            }
            Optional<Irrigation> irrigation = chercheIrrigation(voisins[5].position(), voisins[0].position());
            if (irrigation.isPresent()) addIrrigationDisponible(irrigation.get());
        }
        catch (ParcelleNonExistanteException e){
            System.out.println(e);
        }
    }

    /**
     * Cherche si il y a une irrigation entre les positions données
     * @param position1 position de la 1ere parcelle entre laquelle on cherche une irrigation
     * @param position2 position de la 2eme parcelle entre laquelle on cherche une irrigation
     * @return un optional de l'irrigation trouvée, sinon un optional vide
     */
    public Optional<Irrigation> chercheIrrigation(Position position1, Position position2){
        for (Irrigation irrigation : this.irrigationsPosees){
            if (position1.equals(irrigation.getPositions().get(0)) | position1.equals(irrigation.getPositions().get(1))){
                if (position2.equals(irrigation.getPositions().get(0)) | position2.equals(irrigation.getPositions().get(1))) return Optional.of(irrigation);
            }
        }
        return Optional.empty();
    }

    /**
     * Ajoute les positions disponibles grâce à la liste des voisines de la parcelle ajoutée
     * @param listVoisines la liste des voisines de la parcelle qu'on vient d'ajouter
     */
    private void addPositionsDisponibles(Parcelle[] listVoisines){
        for (Parcelle parcelle : listVoisines) {
            if (parcelle.getClass().equals(ParcelleDisponible.class) && estPositionDisponible((ParcelleDisponible) parcelle)) {
                Position positionVoisin = parcelle.position();
                if (!positionsDisponibles.contains(positionVoisin)){
                    positionsDisponibles.add(positionVoisin);
                }
            }
        }
    }

    /**
     * Renvoie si la position de la parcelle disponible donnée peut recevoir une parcelle
     * @param parcelleDisponible est la parcelle disponible ciblée pour recueillir sa position
     * @return <code>true</code> si une parcelle peut être ajoutée à la même position que la percelle disponible, <code>false</code> sinon
     */
    private boolean estPositionDisponible(ParcelleDisponible parcelleDisponible) {
        try {
            List<Parcelle> parcellesVoisines = GESTIONNAIRE_MODIFICATION_PLATEAU.chercheFuturesVoisines(parcelleDisponible, getParcelles());
            if (parcellesVoisines.contains(etang)) return true;
            if (parcellesVoisines.size() >= 2) return true;
        } catch (ParcelleExistanteException e) {
            return false;
        }
        return false;
    }

    /**
     * Permet d'ajouter une nouvelle parcelle au Plateau
     * @param parcelle la parcelle à ajouter
     * @throws ParcelleExistanteException si la parcelle est déjà sur le Plateau
     * @throws NombreParcelleVoisineException si le nombre de voisines est inférieur à 2 ou supérieur à 6
     */
    public void addParcelle(ParcelleCouleur parcelle, SectionBambou sectionBambou) throws ParcelleExistanteException, NombreParcelleVoisineException {
        // On prend les voisines existantes sur le Plateau
        List<Parcelle> futuresVoisinesList = GESTIONNAIRE_MODIFICATION_PLATEAU.chercheFuturesVoisines(parcelle, getParcelles());
        int tailleListVoisines = futuresVoisinesList.size();

        // Cas nombre de voisines incorrecte ou ne contient pas l'étang
        if ((tailleListVoisines < 2 && !futuresVoisinesList.contains(etang)) || tailleListVoisines > 6) {
            throw new NombreParcelleVoisineException(tailleListVoisines);
        }

        try {
            // Se fait connaitre pour ses nouvelles voisines
            addParcelleVoisine(futuresVoisinesList,parcelle);
            // On prend toutes les voisines (dont les espaces vide en ParcelleDisponible)
            Parcelle[] toutesVoisinesList = GESTIONNAIRE_MODIFICATION_PLATEAU.addVoisinesParcelle(parcelle, futuresVoisinesList);
            parcelleEtVoisinesList.put(parcelle, toutesVoisinesList);
            addBambou(parcelle, sectionBambou);
            addPositionsDisponibles(toutesVoisinesList);
            // On enlève la position de la parcelle ajoutée aux possibilités d'ajout de parcelle
            deletePositionList(parcelle.position());
            checkIrrigationsAutour(parcelle);
        }
        catch (ParcelleNonVoisineException | ParcelleNonExistanteException e) {
            System.out.println(e);
        }
    }

    /**
     * Ajoute la parcelle à la liste des voisines des parcelles qui lui sont voisines
     * @param voisines la liste des voisines de la parcelle en cours d'ajout
     * @param parcelle la parcelle en cours d'ajout
     */
    private void addParcelleVoisine(List<Parcelle> voisines, Parcelle parcelle) {
        for (Parcelle voisineParcelle : voisines) {
            Parcelle[] listVoisineParcelle = parcelleEtVoisinesList.get(voisineParcelle);
            int indiceDansTableauVoisines = GESTIONNAIRE_MODIFICATION_PLATEAU.positionTabVoisin(voisineParcelle.position(), parcelle.position());
            listVoisineParcelle[indiceDansTableauVoisines] = parcelle;
        }
    }

    /**
     * Permet d'ajouter une section de bambou à la position d'une parcelle de couleur donnée
     * @param parcelleCouleur est la parcelle de couleur ciblée
      */
    public void addBambou(ParcelleCouleur parcelleCouleur, SectionBambou sectionBambou) {
        if(parcelleCouleur.isIrriguee()) {
            Optional<Bambou> optionalBambou = getBambou(parcelleCouleur.position());
            if (optionalBambou.isPresent()) {
                try {
                    optionalBambou.get().ajouteSectionBambou(sectionBambou);
                } catch (AjoutCouleurException aCE) {
                    assert false : "La couleur doit être la même que celle du bambou";
                }
            } else {
                Bambou bambou = new Bambou(parcelleCouleur);
                try {
                    bambou.ajouteSectionBambou(sectionBambou);
                } catch (AjoutCouleurException aCE) {
                    assert false : "La couleur doit être la même que celle du bambou";
                }
                bambous.add(bambou);
            }
        }
    }

    public int jardinierAddBambous(ParcelleCouleur parcelle) throws ParcelleNonExistanteException {
        Parcelle[] parcellesVoisine = getTableauVoisines(parcelle);
        int nombreBambousPoses = 0;
        addBambou(parcelle, new SectionBambou(parcelle.couleur()));
        nombreBambousPoses++;
        for(Parcelle p : parcellesVoisine){
            if(p.getClass() != ParcelleDisponible.class && p.getClass().equals(etang.getClass())){
                addBambou((ParcelleCouleur) p,new SectionBambou(parcelle.couleur()));
                nombreBambousPoses++;
            }
        }
        return nombreBambousPoses;
    }

    /**
     * Supprime une position disponible
     * @param position est la position précédemment disponible à retirer
     */
    private void deletePositionList(Position position){
        positionsDisponibles.remove(position);
    }
}