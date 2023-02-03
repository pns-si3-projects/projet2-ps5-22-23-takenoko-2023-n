package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GestionParcellesTest {
    Parcelle[] parcelles;
    Parcelle etang;
    Parcelle pC2_0V;
    Parcelle pC1_1R;
    Parcelle pC1_m1J;
    Parcelle pC3_m1R;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;

        etang = new Etang();
        pC2_0V = new ParcelleCouleur(new Position(2, 0), verte);
        pC1_1R = new ParcelleCouleur(new Position(1, 1), rose);
        pC1_m1J = new ParcelleCouleur(new Position(1, -1), jaune);
        pC3_m1R = new ParcelleCouleur(new Position(3, -1), rose);

        parcelles = new Parcelle[5];
        parcelles[0] = etang;
        parcelles[1] = pC2_0V;
        parcelles[2] = pC1_1R;
        parcelles[3] = pC1_m1J;
        parcelles[4] = pC3_m1R;
    }


    @Test
    void chercheParcelle() {
        Optional<Parcelle> parcelleOpt0_0 = GestionParcelles.chercheParcelle(parcelles, new Position(0, 0));
        Optional<Parcelle> parcelleOpt1_1 = GestionParcelles.chercheParcelle(parcelles, new Position(1, 1));
        Optional<Parcelle> parcelleOpt1_m1 = GestionParcelles.chercheParcelle(parcelles, new Position(1, -1));
        Optional<Parcelle> parcelleOptm1_m1 = GestionParcelles.chercheParcelle(parcelles, new Position(-1, -1));

        assertTrue(parcelleOpt0_0.isPresent());
        assertTrue(parcelleOpt1_1.isPresent());
        assertTrue(parcelleOpt1_m1.isPresent());
        assertTrue(parcelleOptm1_m1.isEmpty());
    }

    @Test
    void futuresVoisines() {
        assertThrows(ParcelleExistanteException.class, () -> GestionParcelles.futuresVoisines(parcelles, etang));
        assertThrows(ParcelleExistanteException.class, () -> GestionParcelles.futuresVoisines(parcelles, pC1_m1J));

        Parcelle pCm1_m1V = new ParcelleCouleur(new Position(-1, 1), verte);
        List<Parcelle> futuresVoisines;
        try {
            futuresVoisines = GestionParcelles.futuresVoisines(parcelles, pCm1_m1V);
        } catch (ParcelleExistanteException e) {
            throw new AssertionError(e);
        }

        assertTrue(futuresVoisines.contains(etang));
        assertTrue(futuresVoisines.contains(pC1_1R));
    }

    @Test
    void indiceVoisine() {
        Parcelle pDm1_1 = new ParcelleDisponible(new Position(-1, 1));
        Parcelle pD3_1 = new ParcelleDisponible(new Position(3, 1));
        Parcelle pD4_0 = new ParcelleDisponible(new Position(4, 0));

        assertEquals(-1, GestionParcelles.indiceVoisine(pC2_0V, pDm1_1));
        assertEquals(0, GestionParcelles.indiceVoisine(pC2_0V, pD3_1));
        assertEquals(1, GestionParcelles.indiceVoisine(pC2_0V, pD4_0));
        assertEquals(2, GestionParcelles.indiceVoisine(pC2_0V, pC3_m1R));
        assertEquals(3, GestionParcelles.indiceVoisine(pC2_0V, pC1_m1J));
        assertEquals(4, GestionParcelles.indiceVoisine(pC2_0V, etang));
        assertEquals(5, GestionParcelles.indiceVoisine(pC2_0V, pC1_1R));
    }

    @Test
    void ajouteVoisinesDisponibles() {
        Parcelle pCm1_m1V = new ParcelleCouleur(new Position(-1, 1), verte);
        List<Parcelle> futuresVoisines;
        try {
            futuresVoisines = GestionParcelles.futuresVoisines(parcelles, pCm1_m1V);
        } catch (ParcelleExistanteException e) {
            throw new AssertionError(e);
        }

        Parcelle[] voisines = GestionParcelles.ajouteVoisinesDisponibles(futuresVoisines, pCm1_m1V);
        Parcelle pD0_2 = new ParcelleDisponible(new Position(0, 2));
        Parcelle pDm2_0 = new ParcelleDisponible(new Position(-2, 0));
        Parcelle pDm3_1 = new ParcelleDisponible(new Position(-3, 1));
        Parcelle pDm2_2 = new ParcelleDisponible(new Position(-2, 2));

        assertEquals(pD0_2, voisines[0]);
        assertEquals(pC1_1R, voisines[1]);
        assertEquals(etang, voisines[2]);
        assertEquals(pDm2_0, voisines[3]);
        assertEquals(pDm3_1, voisines[4]);
        assertEquals(pDm2_2, voisines[5]);
    }

    @Test
    void positionsDisponibles() {
        Parcelle pCm1_m1V = new ParcelleCouleur(new Position(-1, 1), verte);
        List<Parcelle> futuresVoisines;
        try {
            futuresVoisines = GestionParcelles.futuresVoisines(parcelles, pCm1_m1V);
        } catch (ParcelleExistanteException e) {
            throw new AssertionError(e);
        }
        Parcelle[] voisines = GestionParcelles.ajouteVoisinesDisponibles(futuresVoisines, pCm1_m1V);

        Parcelle[] parcellesPlusPCm1_m1V = new Parcelle[6];
        System.arraycopy(parcelles, 0, parcellesPlusPCm1_m1V, 0, 5);
        parcellesPlusPCm1_m1V[5] = pCm1_m1V;

        Position p0_2 = new Position(0, 2);
        Position pm2_0 = new Position(-2, 0);
        Position pm3_1 = new Position(-3, 1);
        Position pm2_2 = new Position(-2, 2);

        List<Position> nouvellesPDis = GestionParcelles.positionsDisponibles(parcellesPlusPCm1_m1V, voisines);

        assertTrue(nouvellesPDis.contains(p0_2));
        assertTrue(nouvellesPDis.contains(pm2_0));
        assertFalse(nouvellesPDis.contains(pm3_1));
        assertFalse(nouvellesPDis.contains(pm2_2));
    }
}