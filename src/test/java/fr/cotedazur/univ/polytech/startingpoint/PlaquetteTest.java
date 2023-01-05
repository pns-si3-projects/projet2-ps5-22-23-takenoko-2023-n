package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaquetteTest {
    Plaquette plaquette;
    SectionBambou secBa;
    ObjectifParcelle objPar2_3;
    ObjectifParcelle objPar3_4;
    ObjectifPanda objPan3_2;
    ObjectifPanda objPan4_2;
    ObjectifJardinier objJar3_2;
    ObjectifJardinier objJar6_4;

    @BeforeEach
    void setUp() {
        plaquette = new Plaquette();
        secBa = new SectionBambou();
        objPar2_3 = new ObjectifParcelle(2, 3);
        objPar3_4 = new ObjectifParcelle(3, 4);
        objPan3_2 = new ObjectifPanda(3, 2);
        objPan4_2 = new ObjectifPanda(4, 2);
        objJar3_2 = new ObjectifJardinier(3, 2);
        objJar6_4 = new ObjectifJardinier(6, 4);
        plaquette.ajouteSectionBambou(new SectionBambou()); // modifier pour vert
        plaquette.ajouteSectionBambou(new SectionBambou()); // modifier pour rose
        plaquette.ajouteSectionBambou(new SectionBambou()); // modifier pour jaune
        try {
            plaquette.ajouteObjectif(objPar2_3);
            plaquette.ajouteObjectif(objPan3_2);
            plaquette.ajouteObjectif(objJar3_2);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
    }

    @Test
    void getNombreBambousVerts() {
        assertEquals(3, plaquette.getNombreBambousVerts());
        plaquette.ajouteSectionBambou(new SectionBambou());
        assertEquals(4, plaquette.getNombreBambousVerts());
    }

    @Test
    void getNombreBambousRoses() {
        assertEquals(3, plaquette.getNombreBambousRoses());
        plaquette.ajouteSectionBambou(new SectionBambou());
        plaquette.ajouteSectionBambou(new SectionBambou());
        assertEquals(5, plaquette.getNombreBambousRoses());
    }

    @Test
    void getNombreBambousJaunes() {
        assertEquals(3, plaquette.getNombreBambousJaunes());
        plaquette.ajouteSectionBambou(new SectionBambou());
        plaquette.ajouteSectionBambou(new SectionBambou());
        plaquette.ajouteSectionBambou(new SectionBambou());
        assertEquals(6, plaquette.getNombreBambousJaunes());
    }

    @Test
    void getNombreObjectifParcelle() {
        assertEquals(1, plaquette.getNombreObjectifParcelle());
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objPan4_2);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(2, plaquette.getNombreObjectifParcelle());
    }

    @Test
    void getNombreObjectifPanda() {
        assertEquals(1, plaquette.getNombreObjectifPanda());
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objPan4_2);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(2, plaquette.getNombreObjectifPanda());
    }

    @Test
    void getNombreObjectifJardinier() {
        assertEquals(1, plaquette.getNombreObjectifJardinier());
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objJar6_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(2, plaquette.getNombreObjectifJardinier());
    }

    @Test
    void getNombreObjectifs() {
        assertEquals(3, plaquette.getNombreObjectifs());
        try {
            plaquette.ajouteObjectif(objPan4_2);
            plaquette.ajouteObjectif(objJar6_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(5, plaquette.getNombreObjectifs());
    }

    @Test
    void getNombreObjectifsMax() {
        assertEquals(5, plaquette.getNombreObjectifsMax());
        assertEquals(Plaquette.NOMBRE_OBJECTIFS_MAX, plaquette.getNombreObjectifsMax());
    }

    @Test
    void getObjectifsParcelle() {
        assertEquals(objPar2_3, plaquette.getObjectifsParcelle()[0]);
        try {
            plaquette.ajouteObjectif(objPar3_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        // Parce que pris dans l'ordre inverse d'ajout
        assertNotEquals(objPar2_3, plaquette.getObjectifsParcelle()[0]);
        assertEquals(objPar3_4, plaquette.getObjectifsParcelle()[0]);
        assertEquals(objPar2_3, plaquette.getObjectifsParcelle()[1]);
    }

    @Test
    void getObjectifsPanda() {
        assertEquals(objPan3_2, plaquette.getObjectifsPanda()[0]);
        try {
            plaquette.ajouteObjectif(objPan4_2);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        // Parce que pris dans l'ordre inverse d'ajout
        assertNotEquals(objPan3_2, plaquette.getObjectifsPanda()[0]);
        assertEquals(objPan4_2, plaquette.getObjectifsPanda()[0]);
        assertEquals(objPan3_2, plaquette.getObjectifsPanda()[1]);
    }

    @Test
    void getObjectifsJardinier() {
        assertEquals(objJar3_2, plaquette.getObjectifsJardinier()[0]);
        try {
            plaquette.ajouteObjectif(objJar6_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        // Parce que pris dans l'ordre inverse d'ajout
        assertNotEquals(objJar3_2, plaquette.getObjectifsJardinier()[0]);
        assertEquals(objJar6_4, plaquette.getObjectifsJardinier()[0]);
        assertEquals(objJar3_2, plaquette.getObjectifsJardinier()[1]);
    }

    @Test
    void getObjectifs() {
        // Parce que pris dans l'ordre inverse d'ajout
        assertEquals(objPar2_3, plaquette.getObjectifs()[2]);
        assertEquals(objPan3_2, plaquette.getObjectifs()[1]);
        assertEquals(objJar3_2, plaquette.getObjectifs()[0]);
        try {
            plaquette.ajouteObjectif(objPan4_2);
            plaquette.ajouteObjectif(objJar6_4);
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertEquals(objJar6_4, plaquette.getObjectifs()[0]);
        assertEquals(objPan4_2, plaquette.getObjectifs()[1]);
        assertEquals(objPar2_3, plaquette.getObjectifs()[4]);
    }

    @Test
    void ajouteSectionBambou() {
        assertEquals(3, plaquette.getNombreBambousVerts());
        plaquette.ajouteSectionBambou(new SectionBambou()); // Modifier pour ajout bambou vert
        plaquette.ajouteSectionBambou(new SectionBambou()); // Modifier pour ajout bambou jaune
        plaquette.ajouteSectionBambou(new SectionBambou()); // Modifier pour ajout bambou rose
        plaquette.ajouteSectionBambou(new SectionBambou()); // Modifier pour ajout bambou vert
        plaquette.ajouteSectionBambou(new SectionBambou()); // Modifier pour ajout bambou vert
        plaquette.ajouteSectionBambou(new SectionBambou()); // Modifier pour ajout bambou jaune
        assertEquals(9, plaquette.getNombreBambousVerts()); // Modifier pour 6 verts
        assertEquals(9, plaquette.getNombreBambousRoses()); // Modifier pour 4 roses
        assertEquals(9, plaquette.getNombreBambousJaunes()); // Modifier pour 5 jaunes
    }

    @Test
    void ajouteObjectif() {
        try {
            plaquette.ajouteObjectif(objPar3_4);
            plaquette.ajouteObjectif(objPan4_2);
            assertEquals(5, plaquette.getNombreObjectifs());
        } catch (NombreObjectifsEnCoursException nOECE) {
            throw new AssertionError(nOECE);
        }
        assertThrows(NombreObjectifsEnCoursException.class, () -> {plaquette.ajouteObjectif(objJar6_4);});
    }
}