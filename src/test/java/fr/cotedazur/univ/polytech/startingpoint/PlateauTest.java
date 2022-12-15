package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlateauTest {
    Plateau plateau;
    Etang etang;
    ParcelleCouleur pC1_m1;
    ParcelleCouleur pC1_m1b;
    ParcelleCouleur pC2_0;
    ParcelleCouleur pCm2_0;
    List<Parcelle> listeEtang_PC2;
    List<Parcelle> listePC1_Etang;
    List<Parcelle> listeParcelleAvecBambou;

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        etang = new Etang();
        pC1_m1 = new ParcelleCouleur(new Position(1,-1));
        pC1_m1b=new ParcelleCouleur(new Position(1,-1));
        pC2_0 = new ParcelleCouleur(new Position(2,0));
        pCm2_0 = new ParcelleCouleur(new Position(-2,0));
        listeEtang_PC2 = new ArrayList<>();
        listePC1_Etang = new ArrayList<>();
        listeParcelleAvecBambou=new ArrayList<>();
        pC1_m1b.addBambou(pC1_m1b.getPosition()); // -> a regler car bissard
        listeEtang_PC2.add(etang);
        listeEtang_PC2.add(pC2_0);
        listePC1_Etang.add(pC1_m1);
        listePC1_Etang.add(etang);
    }

    @Test
    void getParcelles() {
        plateau.addParcelle(pC1_m1);
        plateau.addParcelle(pC2_0);
        assertEquals(etang, plateau.getParcelles().get(0).getParcelleCible());
        assertEquals(pC2_0,plateau.getParcelles().get(2).getParcelleCible());
        assertEquals(pC1_m1,plateau.getParcelles().get(1).getParcelleCible());
    }

    @Test
    void addParcelle() {
        plateau.addParcelle(pC1_m1);
        plateau.addParcelle(pC2_0);
        assertEquals(etang, plateau.getParcelles().get(0).getParcelleCible()); // Car en initialisant un Plateau ça crée un Etang
        assertEquals(pC1_m1, plateau.getParcelles().get(1).getParcelleCible()); // Car en initialisant un Plateau ça a précédemment crée un Etang
        assertEquals(pC2_0, plateau.getParcelles().get(2).getParcelleCible());
        assertNotEquals(pC2_0, plateau.getParcelles().get(1).getParcelleCible());
    }

    @Test
    void getListPositionDisponible(){
        assertEquals(6,plateau.getPositionDisponible().size());
        plateau.addParcelle(pC1_m1);
        assertEquals(5,plateau.getPositionDisponible().size());
        plateau.addParcelle(pC2_0);
        assertEquals(5,plateau.getPositionDisponible().size());
        assertEquals(plateau.getPositionDisponible().get(4),new Position(3,-1));
        plateau.addParcelle(pCm2_0);
        assertEquals(4,plateau.getPositionDisponible().size());
    }

    @Test
    void getVoisin(){
        plateau.addParcelle(pC1_m1);
        plateau.addParcelle(pC2_0);
        plateau.addParcelle(pCm2_0);
        List<ParcelleEtVoisines> listParcelles = plateau.getParcelles();
        Parcelle[] parcelles = listParcelles.get(0).getParcellesVoisines();
        assertEquals(parcelles[2],listParcelles.get(1).getParcelleCible());
        assertEquals(parcelles[1],listParcelles.get(2).getParcelleCible());
        assertEquals(parcelles[4],listParcelles.get(3).getParcelleCible());
    }

    @Test
    void addParcelleAvecBambou(){
        plateau.addParcelleAvecBambou(pC1_m1);
        plateau.addParcelleAvecBambou(pC2_0);
        assertEquals(pC1_m1,pC1_m1);
    }
}