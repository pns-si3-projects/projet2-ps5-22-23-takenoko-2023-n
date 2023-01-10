package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheObjectif;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheObjectifParcelle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheObjectifTest {
    PiocheObjectif piocheObjectif;

    @BeforeEach
    void setUp() {
        PiocheObjectifParcelle pOPar= new PiocheObjectifParcelle(new Random());
        PiocheObjectifPanda pOPan= new PiocheObjectifPanda(new Random());
        PiocheObjectifJardinier pOJar= new PiocheObjectifJardinier(new Random());
        piocheObjectif = new PiocheObjectif(pOPar, pOPan, pOJar);
        /*
        when(pOPar.pioche())
                .thenReturn(new ObjectifParcelle(4, 3))
                .thenReturn(new ObjectifParcelle(3, 4))
                .thenReturn(new ObjectifParcelle(2, 3));
        when(pOPan.pioche())
                .thenReturn(new ObjectifPanda(4, 2))
                .thenReturn(new ObjectifPanda(6, 3))
                .thenReturn(new ObjectifPanda(3, 2))
                .thenReturn(new ObjectifPanda(5, 2));
        when(pOJar.pioche())
                .thenReturn(new ObjectifJardinier(8, 12))
                .thenReturn(new ObjectifJardinier(5, 4))
                .thenReturn(new ObjectifJardinier(7, 9));
        piocheObjectifPar = new PiocheObjectif(pOPar, new PiocheObjectifPanda(new Random()), new PiocheObjectifJardinier(new Random()));
        piocheObjectifPan = new PiocheObjectif(new PiocheObjectifParcelle(new Random()), pOPan, new PiocheObjectifJardinier(new Random()));
        piocheObjectifPar = new PiocheObjectif(new PiocheObjectifParcelle(new Random()), new PiocheObjectifPanda(new Random()), pOJar);
        */
    }

    @Test
    void getNombreObjectifsParcelleRestants() {
        assertEquals(15, piocheObjectif.getNombreObjectifsParcelleRestants());
    }

    @Test
    void getNombreObjectifsPandaRestants() {
        assertEquals(15, piocheObjectif.getNombreObjectifsPandaRestants());
    }

    @Test
    void getNombreObjectifsJardinierRestants() {
        assertEquals(15, piocheObjectif.getNombreObjectifsJardinierRestants());
    }

    @Test
    void isEmptyPiocheObjectifParcelle() {
        assertFalse(piocheObjectif.isEmptyPiocheObjectifParcelle());
    }

    @Test
    void isEmptyPiocheObjectifPanda() {
        assertFalse(piocheObjectif.isEmptyPiocheObjectifPanda());
    }

    @Test
    void isEmptyPiocheObjectifJardinier() {
        assertFalse(piocheObjectif.isEmptyPiocheObjectifJardinier());
    }

    @Test
    void valeurToString() {
        assertEquals("Pioche d'objectifs de parcelles : 15 cartes.\n" +
                "Pioche d'objectifs de panda : 15 cartes.\n" +
                "Pioche d'objectifs de parcelles : 15 cartes.\n",
                piocheObjectif.toString());
    }

    @Test
    void piocheObjectifParcelle() {
        PiocheObjectifParcelle mockPOPar = mock(PiocheObjectifParcelle.class);
        when(mockPOPar.pioche())
                .thenReturn(new ObjectifParcelle(4, 3))
                .thenReturn(new ObjectifParcelle(3, 4))
                .thenReturn(new ObjectifParcelle(2, 3));
        PiocheObjectif pO = new PiocheObjectif(mockPOPar, new PiocheObjectifPanda(new Random()), new PiocheObjectifJardinier(new Random()));
        assertEquals(new ObjectifParcelle(4, 3), pO.piocheObjectifParcelle());
        assertEquals(new ObjectifParcelle(3, 4), pO.piocheObjectifParcelle());
        assertEquals(new ObjectifParcelle(2, 3), pO.piocheObjectifParcelle());
    }

    @Test
    void piocheObjectifPanda() {
        PiocheObjectifPanda mockPOPan = mock(PiocheObjectifPanda.class);
        when(mockPOPan.pioche())
                .thenReturn(new ObjectifPanda(4, 2))
                .thenReturn(new ObjectifPanda(6, 3))
                .thenReturn(new ObjectifPanda(3, 2))
                .thenReturn(new ObjectifPanda(5, 2));
        PiocheObjectif pO = new PiocheObjectif(new PiocheObjectifParcelle(new Random()), mockPOPan, new PiocheObjectifJardinier(new Random()));
        assertEquals(new ObjectifPanda(4, 2), pO.piocheObjectifPanda());
        assertEquals(new ObjectifPanda(6, 3), pO.piocheObjectifPanda());
        assertEquals(new ObjectifPanda(3, 2), pO.piocheObjectifPanda());
        assertEquals(new ObjectifPanda(5, 2), pO.piocheObjectifPanda());
    }

    @Test
    void piocheObjectifJardinier() {
        PiocheObjectifJardinier mockPOJar = mock(PiocheObjectifJardinier.class);
        when(mockPOJar.pioche())
                .thenReturn(new ObjectifJardinier(8, 12))
                .thenReturn(new ObjectifJardinier(5, 4))
                .thenReturn(new ObjectifJardinier(7, 9));
        PiocheObjectif pO = new PiocheObjectif(new PiocheObjectifParcelle(new Random()), new PiocheObjectifPanda(new Random()), mockPOJar);
        assertEquals(new ObjectifJardinier(8, 12), pO.piocheObjectifJardinier());
        assertEquals(new ObjectifJardinier(5, 4), pO.piocheObjectifJardinier());
        assertEquals(new ObjectifJardinier(7, 9), pO.piocheObjectifJardinier());
    }
}