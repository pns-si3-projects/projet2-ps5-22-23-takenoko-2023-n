package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe du Plateau contenant le panda, la liste de Parcelles et leur voisins, le jardinier et la liste de Position Disponibles ainsi qu'un gestionnaire pour gérer les ajouts du plateau
 * @author equipe N
 * @version 1.0
 */
public class Plateau {
    private final Map<Parcelle,Parcelle[]> LIST_PARCELLES_ET_VOISINES = new HashMap<>();
    private static final Panda PANDA = new Panda();
    private static final Jardinier JARDINIER = new Jardinier();
    private final List<Position> POSITIONS_DISPONIBLE = new ArrayList<>();
    private final GestionnaireModificationPlateau GESTIONNAIRE_MODIFICATION_PLATEAU = new GestionnaireModificationPlateau(this);
    private Etang etang;

    /**
     * Constructeur par default du plateau
     */
    public Plateau(){
        addEtang();
    }

    /**
     * Méthode privé permettant d'ajouter l'Etang à la liste de parcelles ainsi que ces possibles voisins
     */
    private void addEtang(){
       etang = new Etang();
       Position positionEtang = etang.getPosition();
       Parcelle[] listParcelle = new Parcelle[6];
       for(int i = 0;i<6;i++){
           listParcelle[i] = GESTIONNAIRE_MODIFICATION_PLATEAU.addParcelleVide(i,positionEtang);
       }
       LIST_PARCELLES_ET_VOISINES.put(etang,listParcelle);
       addPositionDisponibleEtang();
    }

    /**
     * Méthode privé permettant d'ajouter les positions disponibles de parcelles à poser a côté de l'Etang
     */
    private void addPositionDisponibleEtang(){
        Parcelle[] listParcelleDisponibleEtang = LIST_PARCELLES_ET_VOISINES.get(etang);
        for(int i = 0;i<listParcelleDisponibleEtang.length;i++){
            POSITIONS_DISPONIBLE.add(listParcelleDisponibleEtang[i].getPosition());
        }
    }

    /**
     * Methode renvoyant la liste de Parcelle et leur voisines
     * @return Renvoie toutes les parcelles et leur voisines
     */
    public Set<Parcelle> getListParcelle(){
        return LIST_PARCELLES_ET_VOISINES.keySet();
    }

    /**
     * Methode pour obtenir les voisins d'une parcelle existante
     * @param parcelle La parcelle qui possede des voisins
     */
    public Parcelle[] getTableauVoisin(Parcelle parcelle) throws ParcelleNonExistanteException{
        if(LIST_PARCELLES_ET_VOISINES.containsKey(parcelle)){
            return LIST_PARCELLES_ET_VOISINES.get(parcelle);
        }
        else {
            throw new ParcelleNonExistanteException(parcelle);
        }
    }

    /**
     * Méthode privé de la méthode addPosition qui permet de savoir à l'indice du tableau si elle a une Parcelle existante, si oui renvoie true sinon false
     * @param indiceTab indice du tableau de voisin à regarder à côté
     * @param listVoisins liste des voisins de la Parcelle ajouté
     * @return Renvoie vrai si la parcelleDisponible possède à côté de lui une parcelle existante dans le plateau
     */
    private boolean checkVoisin(int indiceTab,Parcelle[] listVoisins){
        int indiceAvantTab = indiceTab - 1;
        int indiceApresTab = indiceTab + 1;
        if(indiceTab == 0){
            indiceAvantTab = 5;
        }
        else if(indiceTab == 5){
            indiceApresTab = 0;
        }
        return ((listVoisins[indiceAvantTab].getClass() == ParcelleCouleur.class || listVoisins[indiceAvantTab].getClass() == Etang.class)
                || (listVoisins[indiceApresTab].getClass() == ParcelleCouleur.class || listVoisins[indiceApresTab].getClass() == Etang.class));
    }

    /**
     * Ajoute les positions disponibles grâce à la liste des voisins d'une parcelle
     * @param listVoisins La liste des voisins de la parcelle qu'on vient d'ajouter
     */
    private void addPosition(Parcelle[] listVoisins){
        for(int i = 0;i< listVoisins.length;i++){
            if(listVoisins[i].getClass() == ParcelleDisponible.class && checkVoisin(i,listVoisins)){
                Boolean test = checkVoisin(i,listVoisins);
                Position positionVoisin = listVoisins[i].getPosition();
                if(!POSITIONS_DISPONIBLE.contains(positionVoisin)){
                    POSITIONS_DISPONIBLE.add(positionVoisin);
                }
            }
        }
    }

    /**
     * Méthode renvoyant un tableau de Position disponible
     * @return Renvoi un tableau de Position disponible
     */
    public Position[] getPositionsDisponible(){
        Position[] listPosition = new Position[POSITIONS_DISPONIBLE.size()];
        for(int i = 0;i<POSITIONS_DISPONIBLE.size();i++){
            listPosition[i] = POSITIONS_DISPONIBLE.get(i);
        }
        return listPosition;
    }

    /**
     * Permet d'avoir la premiere parcelle du Plateau pour pouvoir parcourir la map de parcelle sans passer par l'iterator
     * @return Renvoie l'Etang
     */
    public Etang getEtang(){
        return etang;
    }

    private void addParcelleVoisin(List<Parcelle> voisin,Parcelle parcelle){
        for(Parcelle voisinParcelle : voisin){
            Parcelle[] listVoisinParcelle = LIST_PARCELLES_ET_VOISINES.get(voisinParcelle);
            int indiceParcelleAAddTableau = GESTIONNAIRE_MODIFICATION_PLATEAU.positionTabVoisin(voisinParcelle.getPosition(),parcelle.getPosition());
            listVoisinParcelle[indiceParcelleAAddTableau] = parcelle;
        }
    }
    private void deletePositionList(Position position){
        POSITIONS_DISPONIBLE.remove(position);
    }

    /**
     * Méthode permettant d'ajouter une parcelle au Plateau
     * @param parcelle La parcelle choisit dans la liste de Voisin Disponible
     * @throws ParcelleExistanteException Renvoi une exception si la parcelle est existante
     * @throws NombreParcelleVoisinException Renvoi une exception si le nombre de voisin est inférieur à 2 ou supérieur à 6
     */
    public void addParcelle(ParcelleCouleur parcelle) throws ParcelleExistanteException, NombreParcelleVoisinException{
        List<Parcelle> listParcelleVoisinAAjoute = GESTIONNAIRE_MODIFICATION_PLATEAU.getParcelleVoisin(parcelle);
        if(listParcelleVoisinAAjoute.isEmpty() || listParcelleVoisinAAjoute.size() > 6 ) throw new NombreParcelleVoisinException(listParcelleVoisinAAjoute.size());
        else if(!listParcelleVoisinAAjoute.contains(etang) && listParcelleVoisinAAjoute.size() == 1) throw new NombreParcelleVoisinException(listParcelleVoisinAAjoute.size());
        try {
            Parcelle[] listParcelleVoisin = GESTIONNAIRE_MODIFICATION_PLATEAU.addVoisinParcelle(parcelle,listParcelleVoisinAAjoute);
            LIST_PARCELLES_ET_VOISINES.put(parcelle,listParcelleVoisin);
            addParcelleVoisin(listParcelleVoisinAAjoute,parcelle);
            addPosition(listParcelleVoisin);
            deletePositionList(parcelle.getPosition());
        }
        catch (ParcelleNonVoisineException pNVE){
            System.out.println(pNVE);
        }
    }

    /**
     * Getter du panda
     * @return Renvoie le Panda
     */
    public Panda getPanda(){
        return PANDA;
    }

    /**
     * Getter du jardinier
     * @return Renvoie le jardinier
     */
    public Jardinier getJardinier(){
        return JARDINIER;
    }
}