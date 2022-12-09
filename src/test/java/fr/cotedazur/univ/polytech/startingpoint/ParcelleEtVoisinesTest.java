package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelleEtVoisinesTest {
    ParcelleEtVoisines pEVEtang;
    ParcelleEtVoisines pEVC1;
    Etang etang;
    ParcelleCouleur pC1_1;
    ParcelleCouleur pCm2_0;
    ParcelleCouleur pC3_1;
    List<Parcelle> voisinesEtang;
    List<Parcelle> voisinesC1;

    @BeforeEach
    void setUp() {
        etang = new Etang();
        pEVEtang = new ParcelleEtVoisines(etang);
        pC1_1 = new ParcelleCouleur(new Position(1,1));
        pCm2_0 = new ParcelleCouleur(new Position(-2,0));
        pC3_1 = new ParcelleCouleur(new Position(3,1));
        voisinesC1 = new ArrayList<>();
        voisinesEtang = new ArrayList<>();
        voisinesC1.add(etang); // Etang est a cote de pC1_1
        try {
            pEVC1 = new ParcelleEtVoisines(pC1_1, voisinesC1);
        } catch (ParcelleNonVoisineException e) {
            throw new AssertionError(e);
        }
    }

    @Test
    void getParcelleCible() {
        assertEquals(etang, pEVEtang.getParcelleCible());
        assertEquals(pC1_1, pEVC1.getParcelleCible());
        assertNotEquals(etang, pEVC1.getParcelleCible());
    }

    @Test
    void getParcellesVoisines() {
        assertEquals(new ArrayList<Parcelle>(), pEVEtang.getParcellesVoisines());
        assertEquals(voisinesC1, pEVC1.getParcellesVoisines());
        assertNotEquals(new ArrayList<Parcelle>(), pEVC1.getParcellesVoisines());
    }

    @Test
    void peutEtreVoisine() {
        assertTrue(pEVEtang.peutEtreVoisine(pCm2_0));
        assertFalse(pEVEtang.peutEtreVoisine(etang));
        assertFalse(pEVEtang.peutEtreVoisine(pC3_1));
    }

    @Test
    void addVoisine() {
        try {
            pEVEtang.addVoisine(pCm2_0);
            voisinesEtang.add(pCm2_0);
            pEVEtang.addVoisine(pC1_1);
            voisinesEtang.add(pC1_1);
            pEVC1.addVoisine(pC3_1);
            voisinesC1.add(pC3_1);
        } catch (ParcelleNonVoisineException e) {
            throw new AssertionError(e);
        }
        assertEquals(voisinesEtang, pEVEtang.getParcellesVoisines());
        assertEquals(voisinesC1, pEVC1.getParcellesVoisines());
        try {
            pEVEtang.addVoisine(pC3_1);
        } catch (ParcelleNonVoisineException e) {
            assertTrue(true);
        }
        try {
            pEVC1.addVoisine(pCm2_0);
        } catch (ParcelleNonVoisineException e) {
            assertTrue(true);
        }
    }
}