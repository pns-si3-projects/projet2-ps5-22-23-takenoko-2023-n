package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifParcelleTest {
    ObjectifParcelle objectif1Parcelle;
    ObjectifParcelle objectif2Parcelle;
    ObjectifParcelle objectif3Parcelle;


    @BeforeEach
    void setUp() {
        objectif1Parcelle = new ObjectifParcelle(1,1);
        objectif2Parcelle = new ObjectifParcelle(2,2);
        objectif3Parcelle = new ObjectifParcelle(3,3);
    }

    @Test
    void checkObjectifTermine() {
        assertFalse(objectif1Parcelle.checkObjectifTermine());
        objectif1Parcelle.avanceObjectif(new ParcelleCouleur((new Position(1,3))));
        assertTrue(objectif1Parcelle.checkObjectifTermine());
    }

    @Test
    void avanceObjectif() {
        assertTrue(objectif1Parcelle.avanceObjectif(new ParcelleCouleur(new Position(1,2))));
        assertFalse(objectif2Parcelle.avanceObjectif(new ParcelleDisponible(new Position(0,0))));
    }

    @Test
    void getPoint() {
        assertEquals(1,objectif1Parcelle.getPoint());
        assertEquals(2,objectif2Parcelle.getPoint());
        assertEquals(3,objectif3Parcelle.getPoint());
    }
}