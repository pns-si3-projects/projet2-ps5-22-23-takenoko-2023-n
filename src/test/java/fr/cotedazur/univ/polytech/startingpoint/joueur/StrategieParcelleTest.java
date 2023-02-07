package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifDiagonale;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifLosange;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
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

class StrategieParcelleTest {
    StrategieParcelle strategieParcelle;
    List<Objectif> objectifs;
    Plateau plateau;
    boolean[] piochesVides;


    @BeforeEach
    void setUp() {
        strategieParcelle = new StrategieParcelle();
        objectifs = new ArrayList<>();
        plateau = new Plateau(new PiocheSectionBambou());
        piochesVides = new boolean[] {false, false, false, false, false};
    }
    @Test
    void checkPossibiliteActionParcelle() {
        assertFalse(strategieParcelle.checkPossibiliteActionParcelle(objectifs));

        ObjectifParcelle objectifParcelle = new ObjectifParcelle(3,
                new MotifDiagonale(new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(0, 0), Couleur.VERTE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.VERTE)));

        objectifs.add(objectifParcelle);
        assertTrue(strategieParcelle.checkPossibiliteActionParcelle(objectifs));
    }

    @Test
    void checkPossibiliteActionIrrigation() {
        assertTrue(strategieParcelle.checkPossibiliteActionIrrigation(piochesVides));

        piochesVides[4] = true;
        assertFalse(strategieParcelle.checkPossibiliteActionIrrigation(piochesVides));
    }

    @Test
    void checkPossibiliteActionJardinier() {
        assertFalse(strategieParcelle.checkPossibiliteActionJardinier(plateau, objectifs));

        ObjectifJardinier objectifJardinier = new ObjectifJardinier(3, 2, Couleur.VERTE);
        objectifs.add(objectifJardinier);
        assertFalse(strategieParcelle.checkPossibiliteActionJardinier(plateau, objectifs));

        ParcelleCouleur parcelleCouleur11J = new ParcelleCouleur(new Position(1, 1), Couleur.JAUNE);
        plateau.poseParcelle(parcelleCouleur11J);
        assertTrue(strategieParcelle.checkPossibiliteActionJardinier(plateau, objectifs));

        objectifs.remove(0);
        assertFalse(strategieParcelle.checkPossibiliteActionJardinier(plateau, objectifs));
    }


    @Test
    void choisiActionTourSansMock() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false, false};
        assertEquals(Plaquette.ActionPossible.OBJECTIF, strategieParcelle.choisiActionTour(actionsRealiseesTour,
                objectifs, plateau, piochesVides));

        ObjectifParcelle objectifParcelle = new ObjectifParcelle(3,
                new MotifDiagonale(new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(0, 0), Couleur.VERTE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.VERTE)));

        objectifs.add(objectifParcelle);
        assertEquals(Plaquette.ActionPossible.PARCELLE, strategieParcelle.choisiActionTour(actionsRealiseesTour,
                objectifs, plateau, piochesVides));

        objectifs.remove(0);
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.IRRIGATION,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.IRRIGATION.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.VERTE);
        plateau.poseParcelle(parcelleCouleur11);
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        ObjectifJardinier objectifJardinier = new ObjectifJardinier(3, 3, Couleur.VERTE);
        objectifs.add(objectifJardinier);
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        objectifs.remove(0);
        actionsRealiseesTour[Plaquette.ActionPossible.JARDINIER.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));
    }

    @Test
    void choisiActionParcelleUnObjectif() {
        PiocheParcelle spyPiocheParcelle = spy(new PiocheParcelle(new Random()));
        Plateau spyPlateau = spy(new Plateau(new PiocheSectionBambou()));
        ObjectifParcelle objectifParcelle = new ObjectifParcelle(3,
                new MotifDiagonale(new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(0, 0), Couleur.VERTE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.VERTE)));
        objectifs.add(objectifParcelle);

        strategieParcelle.actionParcelle(spyPlateau, spyPiocheParcelle, new PiocheSectionBambou(), objectifs);
        try {
            verify(spyPiocheParcelle, times(1)).pioche();
            verify(spyPiocheParcelle, times(1)).choisiParcelle(any(ParcellePioche.class), eq(new Position(1, 1)));
            verify(spyPlateau, times(1)).poseParcelle(any(ParcelleCouleur.class));
        }
        catch (PiocheParcelleEnCoursException | PiocheParcelleVideException pPECE) {
            assert false : "Erreur de pioche";
        }
    }

    @Test
    void choisiActionParcellePlusieursObjectif() {
        PiocheParcelle spyPiocheParcelle = spy(new PiocheParcelle(new Random()));
        Plateau spyPlateau = spy(new Plateau(new PiocheSectionBambou()));
        ObjectifParcelle objectifParcelle3 = new ObjectifParcelle(3,
                new MotifDiagonale(new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(0, 0), Couleur.VERTE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.VERTE)));
        objectifs.add(objectifParcelle3);

        ObjectifParcelle objectifParcelle2 = new ObjectifParcelle(2,
                new MotifDiagonale(new ParcelleCouleur(new Position(0, 0), Couleur.VERTE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(2, 2), Couleur.VERTE)));
        objectifs.add(objectifParcelle2);

        ObjectifParcelle objectifParcelle5 = new ObjectifParcelle(3,
                new MotifLosange(new ParcelleCouleur(new Position(0, 0), Couleur.VERTE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(-1, 1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(0, 2), Couleur.VERTE)));
        objectifs.add(objectifParcelle5);

        strategieParcelle.actionParcelle(spyPlateau, spyPiocheParcelle, new PiocheSectionBambou(), objectifs);
        try {
            verify(spyPiocheParcelle, times(1)).pioche();
            verify(spyPiocheParcelle, times(1)).choisiParcelle(any(ParcellePioche.class), eq(new Position(1, 1)));
            verify(spyPlateau, times(1)).poseParcelle(any(ParcelleCouleur.class));
        }
        catch (PiocheParcelleEnCoursException | PiocheParcelleVideException pPECE) {
            assert false : "Erreur de pioche";
        }
    }

    @Test
    void choisiActionObjectif() {
        PiocheObjectifParcelle spyPiocheObjectifParcelle = spy(new PiocheObjectifParcelle(new Random()));
        PiocheObjectifJardinier spyPiocheObjectifJardinier = spy(new PiocheObjectifJardinier(new Random()));
        PiocheObjectifPanda spyPiocheObjectifPanda = spy(new PiocheObjectifPanda(new Random()));

        for (int i = 0; i < 3; i++) {
            strategieParcelle.actionObjectif(spyPiocheObjectifParcelle, spyPiocheObjectifJardinier,
                    spyPiocheObjectifPanda, objectifs);
        }
        verify(spyPiocheObjectifParcelle, times(3)).pioche();

        strategieParcelle.actionObjectif(spyPiocheObjectifParcelle, spyPiocheObjectifJardinier,
                spyPiocheObjectifPanda, objectifs);
        verify(spyPiocheObjectifJardinier, times(1)).pioche();

        strategieParcelle.actionObjectif(spyPiocheObjectifParcelle, spyPiocheObjectifJardinier,
                spyPiocheObjectifPanda, objectifs);
        verify(spyPiocheObjectifPanda, times(1)).pioche();
    }
}