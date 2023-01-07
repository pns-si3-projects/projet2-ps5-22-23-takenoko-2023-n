package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BambouTest {
    Bambou bambou;
    SectionBambou sectionBambou1;
    SectionBambou sectionBambou2;
    SectionBambou sectionBambou3;
    SectionBambou sectionBambou4;

    @BeforeEach
    void setUp() {
        bambou = new Bambou(new Position(1, 2));
        sectionBambou1 = new SectionBambou();
        sectionBambou2 = new SectionBambou();
        sectionBambou3 = new SectionBambou();
        sectionBambou4 = new SectionBambou();
    }

    @Test
    void getPosition() {
        assertEquals(new Position(1, 2), bambou.getPosition());
    }

    @Test
    void getTailleBambou() {
        assertEquals(0, bambou.getTailleBambou());
        bambou.ajouteSectionBambou(sectionBambou1);
        assertEquals(1, bambou.getTailleBambou());
        bambou.ajouteSectionBambou(sectionBambou2);
        bambou.ajouteSectionBambou(sectionBambou3);
        assertEquals(3, bambou.getTailleBambou());
    }

    @Test
    void getTailleMaximumBambou() {
        assertEquals(4, bambou.getTailleMaximumBambou());
        assertEquals(Bambou.TAILLE_MAXIMUM_BAMBOU, bambou.getTailleMaximumBambou());
    }

    @Test
    void isEmptyBambou() {
        assertTrue(bambou.isEmptyBambou());
        bambou.ajouteSectionBambou(sectionBambou1);
        assertFalse(bambou.isEmptyBambou());
        bambou.prendSectionBambou();
        assertTrue(bambou.isEmptyBambou());
    }

    @Test
    void isTailleMaximum() {
        assertFalse(bambou.isTailleMaximum());
        bambou.ajouteSectionBambou(sectionBambou1);
        bambou.ajouteSectionBambou(sectionBambou2);
        bambou.ajouteSectionBambou(sectionBambou3);
        bambou.ajouteSectionBambou(sectionBambou4);
        assertTrue(bambou.isTailleMaximum());
        bambou.prendSectionBambou();
        assertFalse(bambou.isTailleMaximum());
    }

    @Test
    void prendSectionBambou() {
        bambou.ajouteSectionBambou(sectionBambou1);
        bambou.ajouteSectionBambou(sectionBambou2);
        assertEquals(sectionBambou2, bambou.prendSectionBambou());
        assertEquals(sectionBambou1, bambou.prendSectionBambou());
    }

    @Test
    void ajouteSectionBambou() {
        bambou.ajouteSectionBambou(sectionBambou1);
        bambou.ajouteSectionBambou(sectionBambou2);
        assertEquals(2, bambou.getTailleBambou());
        assertEquals(sectionBambou2, bambou.prendSectionBambou());
        assertEquals(sectionBambou1, bambou.prendSectionBambou());
    }
}