package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StrategiePandaTest {
    StrategiePanda strategiePanda;
    List<Objectif> objectifs;
    Plateau plateau;
    boolean[] piochesVides;
    PiocheParcelle piocheParcelle;
    PiocheSectionBambou piocheSectionBambou;
    PiocheIrrigation piocheIrrigation;
    PiocheObjectifPanda piocheObjectifPanda;
    PiocheObjectifJardinier piocheObjectifJardinier;
    PiocheObjectifParcelle piocheObjectifParcelle;
    Plaquette plaquette;


    @BeforeEach
    void setUp() {
        strategiePanda = new StrategiePanda();
        objectifs = new ArrayList<>();
        piocheParcelle = new PiocheParcelle(new Random());
        piocheSectionBambou = new PiocheSectionBambou();
        plateau = new Plateau(piocheSectionBambou);
        piochesVides = new boolean[] {false, false, false, false, false};
        piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        piocheIrrigation = new PiocheIrrigation();
        plaquette = new Plaquette();
    }


    @Test
    void choisiActionTour() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false, false};
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(1, plateau.getParcelles().length);
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.PARCELLE.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.JARDINIER.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.IRRIGATION.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));
    }

    @Test
    void actionParcelleTest() {
        for (int i = 0; i<6; i++) {
            strategiePanda.actionParcelle(plateau,piocheParcelle,piocheSectionBambou, objectifs);
        }
        assertEquals(7,plateau.getParcelles().length);
    }

    @Test
    void actionIrrigationTest () {
        for (int i =0; i<4; i++) {
            strategiePanda.actionParcelle(plateau,piocheParcelle,piocheSectionBambou,objectifs);
        }
        for (int j =0; j<2; j++) {
            strategiePanda.actionIrrigation(plateau,piocheIrrigation, plaquette);
        }
        assertEquals(2,plateau.getIrrigationsPosees().length);
    }
    @Test
    void actionObjectif() {
        assertFalse(piocheObjectifPanda.isEmpty());
        strategiePanda.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda, objectifs);
        assertEquals(1, objectifs.size());
        assertEquals(ObjectifPanda.class, objectifs.get(0).getClass());
    }

    @Test
    void ationJardinierTest () {
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));

        Position positionInitial = spyPlateau.getPanda().getPosition();
        for (int i =0; i<4; i++) {
            strategiePanda.actionParcelle(spyPlateau,piocheParcelle,piocheSectionBambou,objectifs);
        }
        strategiePanda.actionObjectif(piocheObjectifParcelle,piocheObjectifJardinier,piocheObjectifPanda,objectifs);
        for (int j =0; j<2; j++) {
            strategiePanda.actionJardinier(spyPlateau,piocheSectionBambou,objectifs);
            Position positionFinal = spyPlateau.getJardinier().getPosition();
            assertNotEquals(positionInitial,positionFinal);
            positionInitial = positionFinal;
        }
        verify(spyPlateau, times(2)).deplacementJardinier(any(Position.class));
    }

    @Test
    void actionPanda () {
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));

        Position positionInitial = spyPlateau.getJardinier().getPosition();
        for (int i =0; i<4; i++) {
            strategiePanda.actionParcelle(spyPlateau,piocheParcelle,piocheSectionBambou,objectifs);
        }
        strategiePanda.actionObjectif(piocheObjectifParcelle,piocheObjectifJardinier,piocheObjectifPanda,objectifs);
        strategiePanda.actionPanda(spyPlateau,objectifs,plaquette);
        Position positionFinal = spyPlateau.getPanda().getPosition();
        assertNotEquals(positionInitial,positionFinal);
        verify(spyPlateau, times(1)).deplacementPanda(any(Position.class));
    }

    @Test
    void plaquetteCouleurManquante() {
        Plaquette mockPlaquette = mock(Plaquette.class);
        SectionBambou sectionBambouVerte = new SectionBambou(Couleur.VERTE);
        SectionBambou sectionBambouRose = new SectionBambou(Couleur.ROSE);
        when(mockPlaquette.getReserveBambousManges()).thenReturn(new SectionBambou[0],
                new SectionBambou[]{sectionBambouVerte}, new SectionBambou[]{sectionBambouVerte, sectionBambouRose});

        assertEquals(Couleur.VERTE, strategiePanda.plaquetteCouleurManquante(mockPlaquette));
        assertEquals(Couleur.ROSE, strategiePanda.plaquetteCouleurManquante(mockPlaquette));
        assertEquals(Couleur.JAUNE, strategiePanda.plaquetteCouleurManquante(mockPlaquette));
    }
}