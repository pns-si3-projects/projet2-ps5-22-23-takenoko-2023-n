package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PiocheSectionBambouTest {
    PiocheSectionBambou piocheSectionBambou;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        piocheSectionBambou = new PiocheSectionBambou();
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
    }


    @Test
    void piocheSectionBambouVert() {
        SectionBambou sectionBambouVert = new SectionBambou(verte);

        assertEquals(sectionBambouVert, piocheSectionBambou.pioche(verte));
    }

    @Test
    void piocheSectionBambouRose() {
        SectionBambou sectionBambouRose = new SectionBambou(rose);
        
        assertEquals(sectionBambouRose, piocheSectionBambou.pioche(rose));
    }

    @Test
    void piocheSectionBambouJaune() {
        SectionBambou sectionBambouJaune = new SectionBambou(jaune);

        assertEquals(sectionBambouJaune, piocheSectionBambou.pioche(jaune));
    }


    @Test
    void testToString() {
        assertEquals("Pioche de sections de bambou", piocheSectionBambou.toString());
    }
}