package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaquetteTest {
    Plaquette plaquette;

    @BeforeEach
    void setUp() {
        plaquette = new Plaquette();
        plaquette.ajouteSectionBambou(new SectionBambou()); // modifier pour vert
        plaquette.ajouteSectionBambou(new SectionBambou()); // modifier pour rose
        plaquette.ajouteSectionBambou(new SectionBambou()); // modifier pour jaune
        plaquette.ajouteObjectif(new ObjectifParcelle(2, 3));
        plaquette.ajouteObjectif(new ObjectifPanda(3, 2));
        plaquette.ajouteObjectif(new ObjectifJardinier(5, 4));
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
        plaquette.ajouteObjectif(new ObjectifParcelle(3, 4));
        plaquette.ajouteObjectif(new ObjectifPanda(4, 2));
        assertEquals(2, plaquette.getNombreObjectifParcelle());
    }

    @Test
    void getNombreObjectifPanda() {
        assertEquals(1, plaquette.getNombreObjectifPanda());
        plaquette.ajouteObjectif(new ObjectifParcelle(3, 4));
        plaquette.ajouteObjectif(new ObjectifPanda(4, 2));
        assertEquals(2, plaquette.getNombreObjectifPanda());
    }

    @Test
    void getNombreObjectifJardinier() {
        assertEquals(1, plaquette.getNombreObjectifJardinier());
        plaquette.ajouteObjectif(new ObjectifParcelle(3, 4));
        plaquette.ajouteObjectif(new ObjectifJardinier(6, 4));
        assertEquals(2, plaquette.getNombreObjectifJardinier());
    }

    @Test
    void getNombreObjectifs() {
        assertEquals(3, plaquette.getNombreObjectifs());
        plaquette.ajouteObjectif(new ObjectifParcelle(3, 4));
        assertEquals(4, plaquette.getNombreObjectifs());
        plaquette.ajouteObjectif(new ObjectifPanda(4, 2));
        assertEquals(5, plaquette.getNombreObjectifs());
    }

    @Test
    void getNombreObjectifsMax() {
        assertEquals(5, plaquette.getNombreObjectifsMax());
        assertEquals(Plaquette.NOMBRE_OBJECTIFS_MAX, plaquette.getNombreObjectifsMax());
    }

    @Test
    void getObjectifsParcelle() {
    }

    @Test
    void getObjectifsPanda() {
    }

    @Test
    void getObjectifsJardinier() {
    }

    @Test
    void getObjectifs() {
    }

    @Test
    void ajouteSectionBambou() {
    }

    @Test
    void ajouteObjectif() {
    }
}