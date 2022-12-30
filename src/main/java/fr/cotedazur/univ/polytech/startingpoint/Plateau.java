package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe du Plateau contenant le panda, la liste de Parcelles et leur voisins, le jardinier et la liste de Position Disponibles ainsi qu'un gestionnaire pour gérer les ajouts du plateau
 * @author equipe N
 * @version 1.0
 */
public class Plateau {
    private static final Map<Parcelle,Parcelle[]> LIST_PARCELLES_ET_VOISINES = new HashMap<>();
    private static final Panda PANDA = new Panda();
    private static final Jardinier JARDINIER = new Jardinier();
    private static final List<Position> POSITIONS_DISPONIBLE = new ArrayList<>();
    private static final GestionnaireModificationPlateau GESTIONNAIRE_MODIFICATION_PLATEAU = new GestionnaireModificationPlateau();
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
     * Methode renvoyant la map de Parcelle et leur voisines
     * @return Renvoie toutes les parcelles et leur voisines
     */
    public Map<Parcelle,Parcelle[]> getMap(){
        return LIST_PARCELLES_ET_VOISINES;
    }

    /**
     * Ajoute les positions disponibles grâce à la liste des voisins d'une parcelle
     * @param listVoisins La liste des voisins de la parcelle qu'on vient d'ajouter
     */
    private void addPosition(Parcelle[] listVoisins){
        for(int i = 0;i< listVoisins.length;i++){
            if(listVoisins[i].getClass() == ParcelleDisponible.class){
                POSITIONS_DISPONIBLE.add(listVoisins[i].getPosition());
            }
        }
    }

    /**
     * Permet d'avoir la premiere parcelle du Plateau pour pouvoir parcourir la map de parcelle sans passer par l'iterator
     * @return Renvoie l'Etang
     */
    public Etang getEtang(){
        return etang;
    }

    /**
     * Méthode permettant d'ajouter une parcelle au Plateau
     * @param parcelle La parcelle choisit dans la liste de Voisin Disponible
     * @throws ParcelleExistanteException Renvoi une exception si la parcelle est existante
     * @throws NombreParcelleVoisinException Renvoi une exception si le nombre de voisin est inférieur à 2 ou supérieur à 6
     */
    public void addParcelle(ParcelleCouleur parcelle) throws ParcelleExistanteException, NombreParcelleVoisinException{
        List<Parcelle> listParcelleVoisinAAjoute = GESTIONNAIRE_MODIFICATION_PLATEAU.getParcelleVoisin(parcelle);
        if(listParcelleVoisinAAjoute.size() < 2 || listParcelleVoisinAAjoute.size() > 6) throw new NombreParcelleVoisinException(listParcelleVoisinAAjoute.size());
        try {
            Parcelle[] listParcelleVoisin = GESTIONNAIRE_MODIFICATION_PLATEAU.addVoisinParcelle(parcelle,listParcelleVoisinAAjoute);
            LIST_PARCELLES_ET_VOISINES.put(parcelle,listParcelleVoisin);
            addPosition(listParcelleVoisin);
        }
        catch (ParcelleNonVoisineException pNVE){
            System.out.println(pNVE);
        }
    }
}