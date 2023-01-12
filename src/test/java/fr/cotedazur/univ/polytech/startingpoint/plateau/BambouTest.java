package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BambouTest {
    Bambou bambou;
    ParcelleCouleur pC1_2;
    SectionBambou sectionBambou1;
    SectionBambou sectionBambou2;
    SectionBambou sectionBambou3;
    SectionBambou sectionBambou4;

    @BeforeEach
    void setUp() {
        pC1_2 = new ParcelleCouleur(new Position(1, 2), Couleur.VERT);
        bambou = new Bambou(pC1_2);
        sectionBambou1 = new SectionBambou(Couleur.VERT);
        sectionBambou2 = new SectionBambou(Couleur.VERT);
        sectionBambou3 = new SectionBambou(Couleur.VERT);
        sectionBambou4 = new SectionBambou(Couleur.VERT);
    }

    @Test
    void couleur(){
        assertNotEquals(Couleur.JAUNE,bambou.couleur());
        assertEquals(Couleur.VERT,bambou.couleur());
    }
    @Test
    void cosition() {
        assertEquals(new Position(1, 2), bambou.position());
    }

    @Test
    void getTailleBambou() {
        assertEquals(0, bambou.getTailleBambou());
        try {
            bambou.ajouteSectionBambou(sectionBambou1);
            assertEquals(1, bambou.getTailleBambou());
            bambou.ajouteSectionBambou(sectionBambou2);
            bambou.ajouteSectionBambou(sectionBambou3);
            assertEquals(3, bambou.getTailleBambou());
        }
        catch (AjoutCouleurException aCE){
            assert false: "La section de Bambou devrait être de la même couleur";
        }

    }

    @Test
    void getTailleMaximumBambou() {
        assertEquals(4, bambou.getTailleMaximumBambou());
        assertEquals(Bambou.TAILLE_MAXIMUM_BAMBOU, bambou.getTailleMaximumBambou());
    }

    @Test
    void isEmptyBambou() {
        assertTrue(bambou.isEmptyBambou());
        try {
            bambou.ajouteSectionBambou(sectionBambou1);
            assertFalse(bambou.isEmptyBambou());
        }
        catch (AjoutCouleurException aCE){
            assert false: "La section de Bambou devrait être de la même couleur";
        }

        bambou.prendSectionBambou();
        assertTrue(bambou.isEmptyBambou());
    }

    @Test
    void isTailleMaximum() {
        assertFalse(bambou.isTailleMaximum());
        try {
            bambou.ajouteSectionBambou(sectionBambou1);
            bambou.ajouteSectionBambou(sectionBambou2);
            bambou.ajouteSectionBambou(sectionBambou3);
            bambou.ajouteSectionBambou(sectionBambou4);
        }
        catch (AjoutCouleurException aCE){
            assert false: "La section de Bambou devrait être de la même couleur";
        }

        assertTrue(bambou.isTailleMaximum());
        bambou.prendSectionBambou();
        assertFalse(bambou.isTailleMaximum());
    }

    @Test
    void prendSectionBambou() {
        try {
            bambou.ajouteSectionBambou(sectionBambou1);
            bambou.ajouteSectionBambou(sectionBambou2);
        }
        catch (AjoutCouleurException aCE){
            assert false: "La section de Bambou devrait être de la même couleur";
        }

        assertEquals(sectionBambou2, bambou.prendSectionBambou());
        assertEquals(sectionBambou1, bambou.prendSectionBambou());
    }

    @Test
    void ajouteSectionBambouSansException() {
        try {
            bambou.ajouteSectionBambou(sectionBambou1);
            bambou.ajouteSectionBambou(sectionBambou2);
        }
        catch (AjoutCouleurException aCE){
            assert false: "La section de Bambou devrait être de la même couleur";
        }

        assertEquals(2, bambou.getTailleBambou());
        assertEquals(sectionBambou2, bambou.prendSectionBambou());
        assertEquals(sectionBambou1, bambou.prendSectionBambou());
    }

    @Test
    void ajouteSectionBambouAvecException() {
        SectionBambou sectionBambouRose = new SectionBambou(Couleur.ROSE);
        assertThrows(AjoutCouleurException.class,() -> {bambou.ajouteSectionBambou(sectionBambouRose); } );
    }
}