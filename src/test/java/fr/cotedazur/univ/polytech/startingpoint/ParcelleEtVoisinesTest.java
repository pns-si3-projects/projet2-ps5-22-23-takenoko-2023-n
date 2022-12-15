package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelleEtVoisinesTest {
    ParcelleEtVoisines pEVEtang;
    ParcelleEtVoisines pEVC1;
    ParcelleEtVoisines pEVC2;
    Etang etang;
    ParcelleCouleur pC1_1;
    ParcelleCouleur pCm2_0;
    ParcelleCouleur pC3_1;
    List<ParcelleEtVoisines> voisinesEtang;

    @BeforeEach
    void setUp() {
        etang = new Etang();
        pEVEtang = new ParcelleEtVoisines(etang);
        pC1_1 = new ParcelleCouleur(new Position(1,1));
        pCm2_0 = new ParcelleCouleur(new Position(-2,0));
        pC3_1 = new ParcelleCouleur(new Position(3,1));
        voisinesEtang = new ArrayList<>();
        voisinesEtang.add(pEVEtang);
        try {
            pEVC1 = new ParcelleEtVoisines(pC1_1, voisinesEtang);
            pEVC2 = new ParcelleEtVoisines(pCm2_0,voisinesEtang);
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
        assertEquals(pC1_1, pEVEtang.getParcellesVoisines()[0]);
        assertEquals(pCm2_0, pEVEtang.getParcellesVoisines()[4]);
        assertNotEquals(etang, pEVC1.getParcellesVoisines()[0]);
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
        } catch (ParcelleNonVoisineException e) {
            throw new AssertionError(e);
        }
        assertEquals(pEVEtang.getParcellesVoisines()[4], pCm2_0);
        assertEquals(pEVC1.getParcellesVoisines()[3], etang);
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