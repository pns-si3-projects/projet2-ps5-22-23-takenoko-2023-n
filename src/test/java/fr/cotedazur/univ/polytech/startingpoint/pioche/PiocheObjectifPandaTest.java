package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheObjectifPandaTest {
    PiocheObjectifPanda piocheObjectifPanda;
    @Mock
    Random mockRandom;


    @BeforeEach
    void setUp() {
        piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        mockRandom = mock(Random.class);
    }


    @Test
    void getNombreObjectifsRestants() {
        assertEquals(15, piocheObjectifPanda.getNombreObjectifsRestants());

        piocheObjectifPanda.pioche();
        assertEquals(14, piocheObjectifPanda.getNombreObjectifsRestants());

        piocheObjectifPanda.pioche();
        piocheObjectifPanda.pioche();
        assertEquals(12, piocheObjectifPanda.getNombreObjectifsRestants());
    }

    @Test
    void isEmpty() {
        assertFalse(piocheObjectifPanda.isEmpty());
        piocheObjectifPanda.pioche();
        assertFalse(piocheObjectifPanda.isEmpty());
    }


    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(15);
        piocheObjectifPanda = new PiocheObjectifPanda(mockRandom);

        assertThrows(ArithmeticException.class, () -> piocheObjectifPanda.pioche());
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheObjectifPanda = new PiocheObjectifPanda(mockRandom);

        assertThrows(ArithmeticException.class, () -> piocheObjectifPanda.pioche());
    }

    @Test
    void pioche() {
        when(mockRandom.nextInt(anyInt())).thenReturn(14, 1, 3, 3, 6);
        piocheObjectifPanda = new PiocheObjectifPanda(mockRandom);

        // Les objectifs avec 3 couleurs ne sont pas encore fait
        List<SectionBambou> bambousAManger6_3 = new ArrayList<>();
        bambousAManger6_3.add(new SectionBambou(Couleur.VERTE));
        bambousAManger6_3.add(new SectionBambou(Couleur.JAUNE));
        bambousAManger6_3.add(new SectionBambou(Couleur.ROSE));

        assertEquals(new ObjectifPanda(6, bambousAManger6_3), piocheObjectifPanda.pioche());

        List<SectionBambou> bambousAManger3_2 = new ArrayList<>();
        for (int j=0; j < 2;j++){
            bambousAManger3_2.add(new SectionBambou(Couleur.VERTE));
        }

        assertEquals(new ObjectifPanda(3, bambousAManger3_2), piocheObjectifPanda.pioche());
        // car en supprimant 1, les suivants sont décalés donc on prend ancien 4
        assertEquals(new ObjectifPanda(3, bambousAManger3_2), piocheObjectifPanda.pioche());

        List<SectionBambou> bambousAManger4_2 = new ArrayList<>();
        for (int j=0; j < 2;j++){
            bambousAManger4_2.add(new SectionBambou(Couleur.JAUNE));
        }
        assertEquals(new ObjectifPanda(4, bambousAManger4_2), piocheObjectifPanda.pioche());

        List<SectionBambou> bambousAManger5_2 = new ArrayList<>();
        for (int j=0; j < 2; j++){
            bambousAManger5_2.add(new SectionBambou(Couleur.ROSE));
        }
        assertEquals(new ObjectifPanda(5, bambousAManger5_2), piocheObjectifPanda.pioche());
    }


    @Test
    void testToString() {
        assertEquals("Pioche d'objectifs de panda : 15 objectifs", piocheObjectifPanda.toString());
    }
}