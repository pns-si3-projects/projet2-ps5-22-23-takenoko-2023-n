package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        try {
            Parcelle[] futuresVoisines =
                    GestionParcelles.futuresVoisines(parcelles, new ParcelleCouleur(new Position(-1, 1), jaune));

            assertEquals(new ParcelleDisponible(new Position(0, 2)), futuresVoisines[0]);
            assertEquals(pC1_1R, futuresVoisines[1]);
            assertEquals(etang, futuresVoisines[2]);
            assertEquals(new ParcelleDisponible(new Position(-2, 0)), futuresVoisines[3]);
            assertEquals(new ParcelleDisponible(new Position(-3, 1)), futuresVoisines[4]);
            assertEquals(new ParcelleDisponible(new Position(-2, 2)), futuresVoisines[5]);
        } catch (ParcelleExistanteException e) {
            throw new AssertionError(e);
        }
    }
}