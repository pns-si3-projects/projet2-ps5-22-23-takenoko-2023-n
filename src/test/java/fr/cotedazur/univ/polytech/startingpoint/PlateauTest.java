package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlateauTest {
    Plateau plateau;
    Etang etang;
    ParcelleEtVoisines pEVEtang;
    ParcelleCouleur pC1_m1;
    ParcelleCouleur pC2_0;
    List<Parcelle> listeEtang_PC2;
    List<Parcelle> listePC1_Etang;

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        etang = new Etang();
        pEVEtang = new ParcelleEtVoisines(etang);
        pC1_m1 = new ParcelleCouleur(new Position(1,-1));
        pC2_0 = new ParcelleCouleur(new Position(2,0));
        listeEtang_PC2 = new ArrayList<>();
        listePC1_Etang = new ArrayList<>();
        listeEtang_PC2.add(etang);
        listeEtang_PC2.add(pC2_0);
        listePC1_Etang.add(pC1_m1);
        listePC1_Etang.add(etang);
    }

    @Test
    void getParcelles() {
        assertEquals(etang, plateau.getParcelles().get(0).getParcelleCible());
    }

    @Test
    void addParcelle() {
        plateau.addParcelle(pEVEtang);
        plateau.addParcelle(pC1_m1, listeEtang_PC2);
        plateau.addParcelle(pC2_0, listePC1_Etang);
        assertEquals(etang, plateau.getParcelles().get(0).getParcelleCible()); // Car en initialisant un Plateau ça crée un Etang
        assertEquals(etang, plateau.getParcelles().get(1).getParcelleCible()); // Car en initialisant un Plateau ça a précédemment crée un Etang
        assertEquals(pC1_m1, plateau.getParcelles().get(2).getParcelleCible());
        assertEquals(pC2_0, plateau.getParcelles().get(3).getParcelleCible());
        assertNotEquals(pC2_0, plateau.getParcelles().get(2).getParcelleCible());
    }
}