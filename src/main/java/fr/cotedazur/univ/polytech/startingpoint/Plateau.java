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

    /**
     * Constructeur par default du plateau
     */
    public Plateau(){
        addEtang();
        addPositionDisponibleEtang();
    }

    /**
     * Méthode privé permettant d'ajouter l'Etang à la liste de parcelles ainsi que ces possibles voisins
     */
    private void addEtang(){
        Etang etang = new Etang();
        Position positionEtang = etang.getPosition();
        Parcelle[] listParcelle = new Parcelle[6];
       for(int i = 0;i<6;i++){
           listParcelle[i] = GESTIONNAIRE_MODIFICATION_PLATEAU.addParcelleVide(i,positionEtang);
       }
       LIST_PARCELLES_ET_VOISINES.put(etang,listParcelle);
    }

    /**
     * Méthode privé permettant d'ajouter les positions disponibles de parcelles à poser a côté de l'Etang
     */
    private void addPositionDisponibleEtang(){
        Parcelle[] listParcelleDisponibleEtang = LIST_PARCELLES_ET_VOISINES.get(0);
        for(int i = 0;i<listParcelleDisponibleEtang.length;i++){
            POSITIONS_DISPONIBLE.add(listParcelleDisponibleEtang[i].getPosition());
        }
    }
}
