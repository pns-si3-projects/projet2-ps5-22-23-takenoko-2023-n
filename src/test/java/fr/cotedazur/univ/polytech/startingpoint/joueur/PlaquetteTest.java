package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaquetteTest {
    // Attributs
    Plaquette plaquette;


    // Constructeur
    @BeforeEach
    void setUp() {
        plaquette = new Plaquette();
    }


    // Accesseurs
    @Test
    void getReserveBambousManges() {
        assertEquals(new SectionBambou[0], plaquette.getReserveBambousManges());
    }

    @Test
    void isActionTour() {
    }

    @Test
    void nombreBambouCouleur() {
    }

    @Test
    void mangeSectionBambou() {
    }
}