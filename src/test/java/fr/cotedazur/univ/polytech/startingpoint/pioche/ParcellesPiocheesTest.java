package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcellesPiocheesTest {
    ParcellesPiochees parcellesPiochees;
    ParcellePioche parcellePiocheV;
    ParcellePioche parcellePiocheR;
    ParcellePioche parcellePiocheJ;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        parcellesPiochees = new ParcellesPiochees();
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
        parcellePiocheV = new ParcellePioche(verte);
        parcellePiocheR = new ParcellePioche(rose);
        parcellePiocheJ = new ParcellePioche(jaune);
    }


    @Test
    void getParcellesPiochees() {
        try {
            parcellesPiochees.enregistreParcellePiochee(parcellePiocheV);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError("Aucune pioche ne devrait être effectuée");
        }

        ParcellePioche[] parcelles = parcellesPiochees.getParcellesPiochees();
        assertEquals(parcellePiocheV, parcelles[0]);
        assertEquals(parcellePiocheV, parcelles[2]);
    }

    @Test
    void isEmpty() {
        assertTrue(parcellesPiochees.isEmpty());

        try {
            parcellesPiochees.enregistreParcellePiochee(parcellePiocheV);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError("Aucune pioche ne devrait être effectuée");
        }

        assertFalse(parcellesPiochees.isEmpty());
    }


    @Test
    void enregistre3ParcellesPiocheesAvecException() {
        try {
            parcellesPiochees.enregistre3ParcellesPiochees(parcellePiocheV, parcellePiocheJ, parcellePiocheR);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError("Aucune pioche ne devrait être effectuée");
        }

        assertThrows(PiocheParcelleEnCoursException.class,
                () -> parcellesPiochees.enregistre3ParcellesPiochees(parcellePiocheJ,
                        parcellePiocheR, parcellePiocheR));
        assertThrows(PiocheParcelleEnCoursException.class,
                () -> parcellesPiochees.enregistre3ParcellesPiochees(parcellePiocheV,
                        parcellePiocheJ, parcellePiocheR));
    }


    @Test
    void enregistre3ParcellesPiochees() {
        try {
            parcellesPiochees.enregistre3ParcellesPiochees(parcellePiocheV, parcellePiocheJ, parcellePiocheR);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError("Aucune pioche ne devrait être effectuée");
        }

        ParcellePioche[] parcelles = parcellesPiochees.getParcellesPiochees();
        assertEquals(parcellePiocheV, parcelles[0]);
        assertEquals(parcellePiocheJ, parcelles[1]);
        assertEquals(parcellePiocheR, parcelles[2]);
    }

    @Test
    void enregistreParcellePiocheeAvecException() {
        try {
            parcellesPiochees.enregistreParcellePiochee(parcellePiocheR);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError("Aucune pioche ne devrait être effectuée");
        }

        assertThrows(PiocheParcelleEnCoursException.class,
                () -> parcellesPiochees.enregistreParcellePiochee(parcellePiocheR));
        assertThrows(PiocheParcelleEnCoursException.class,
                () -> parcellesPiochees.enregistreParcellePiochee(parcellePiocheJ));
        assertThrows(PiocheParcelleEnCoursException.class,
                () -> parcellesPiochees.enregistreParcellePiochee(parcellePiocheV));
    }

    @Test
    void enregistreParcellePiochee() {
        try {
            parcellesPiochees.enregistreParcellePiochee(parcellePiocheR);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError("Aucune pioche ne devrait être effectuée");
        }

        ParcellePioche[] parcelles = parcellesPiochees.getParcellesPiochees();
        assertEquals(parcellePiocheR, parcelles[0]);
        assertEquals(parcellePiocheR, parcelles[1]);
        assertEquals(parcellePiocheR, parcelles[2]);
    }

    @Test
    void parcellePiochee() {
        try {
            parcellesPiochees.enregistreParcellePiochee(parcellePiocheJ);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError("Aucune pioche ne devrait être effectuée");
        }

        assertFalse(parcellesPiochees.parcellePiochee(parcellePiocheV));
        assertFalse(parcellesPiochees.parcellePiochee(parcellePiocheR));
        assertTrue(parcellesPiochees.parcellePiochee(parcellePiocheJ));
    }

    @Test
    void oublieParcellesPiochees() {
        try {
            parcellesPiochees.enregistreParcellePiochee(parcellePiocheV);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError("Aucune pioche ne devrait être effectuée");
        }
        assertFalse(parcellesPiochees.isEmpty());

        parcellesPiochees.oublieParcellesPiochees();
        assertTrue(parcellesPiochees.isEmpty());

        parcellesPiochees.oublieParcellesPiochees();
        assertTrue(parcellesPiochees.isEmpty());
    }
}