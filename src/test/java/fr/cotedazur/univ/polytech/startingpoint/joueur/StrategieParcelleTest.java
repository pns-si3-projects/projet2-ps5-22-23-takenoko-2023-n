package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifDiagonale;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifLosange;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.ParcelleNonPoseeException;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertFalse(strategieParcelle.checkPossibiliteActionParcelle(objectifs, piochesVides));

        ObjectifParcelle objectifParcelle = new ObjectifParcelle(3,
                new MotifDiagonale(new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(0, 0), Couleur.VERTE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.VERTE)));

        objectifs.add(objectifParcelle);
        assertTrue(strategieParcelle.checkPossibiliteActionParcelle(objectifs, piochesVides));
    }

    @Test
    void checkPossibiliteActionIrrigation() {
        assertFalse(strategieParcelle.checkPossibiliteActionIrrigation(piochesVides, plateau.getIrrigationsDisponibles()));

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.VERTE);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), Couleur.JAUNE);
        plateau.poseParcelle(parcelleCouleur11);
        plateau.poseParcelle(parcelleCouleur20);
        assertTrue(strategieParcelle.checkPossibiliteActionIrrigation(piochesVides, plateau.getIrrigationsDisponibles()));

        piochesVides[4] = true;
        assertFalse(strategieParcelle.checkPossibiliteActionIrrigation(piochesVides, plateau.getIrrigationsDisponibles()));
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
    void choisiActionTour() {
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
        ObjectifJardinier objectifJardinier = new ObjectifJardinier(3, 3, Couleur.VERTE);
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), Couleur.VERTE);
        objectifs.add(objectifJardinier);
        plateau.poseParcelle(parcelleCouleur11);

        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), Couleur.JAUNE);
        plateau.poseParcelle(parcelleCouleur20);

        assertEquals(Plaquette.ActionPossible.IRRIGATION,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.IRRIGATION.ordinal()] = true;
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

        for (int i = 0; i < 15; i++) {
            strategieParcelle.actionObjectif(spyPiocheObjectifParcelle, spyPiocheObjectifJardinier,
                    spyPiocheObjectifPanda, objectifs);
            objectifs.remove(0);
        }

        verify(spyPiocheObjectifParcelle, times(15)).pioche();
        assertTrue(spyPiocheObjectifParcelle.isEmpty());

        for (int i = 0; i < 15; i++) {
            strategieParcelle.actionObjectif(spyPiocheObjectifParcelle, spyPiocheObjectifJardinier,
                    spyPiocheObjectifPanda, objectifs);
            objectifs.remove(0);
        }

        verify(spyPiocheObjectifJardinier, times(15)).pioche();
        assertTrue(spyPiocheObjectifJardinier.isEmpty());
        System.out.println(spyPiocheObjectifPanda);
        for (int i = 0; i < 15; i++) {
            strategieParcelle.actionObjectif(spyPiocheObjectifParcelle, spyPiocheObjectifJardinier,
                    spyPiocheObjectifPanda, objectifs);
            objectifs.remove(0);
        }

        verify(spyPiocheObjectifPanda, times(15)).pioche();
        assertTrue(spyPiocheObjectifPanda.isEmpty());

    }

    @Test
    void choisiActionIrrigation() {
        PiocheIrrigation spyPiocheIrrigation = spy(new PiocheIrrigation());
        Plateau spyPlateau = spy(new Plateau(new PiocheSectionBambou()));
        PiocheSectionBambou piocheSectionBambou = new PiocheSectionBambou();
        Position position11 = new Position(1, 1);
        Position position20 = new Position(2, 0);
        spyPlateau.poseParcelle(new ParcelleCouleur(position11, Couleur.JAUNE));
        spyPlateau.poseParcelle(new ParcelleCouleur(position20, Couleur.VERTE));
        strategieParcelle.actionIrrigation(spyPlateau, spyPiocheIrrigation, piocheSectionBambou);
        List<Position> listPosition = new ArrayList<>();
        listPosition.add(position20);
        listPosition.add(position11);

        verify(spyPiocheIrrigation, times(1)).pioche(listPosition);
        verify(spyPlateau, times(1)).poseIrrigation(new Irrigation(listPosition));
        assertEquals(1, spyPlateau.getIrrigationsPosees().length);
        assertEquals(0, spyPlateau.getIrrigationsDisponibles().length);
    }

    @Test
    void choisiActionJardinier() {
        PiocheSectionBambou piocheSectionBambou = new PiocheSectionBambou();
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));

        // Position
        Position position1_1 = new Position(1,1);
        Position position2_0 = new Position(2,0);
        Position position1_m1 = new Position(1,-1);
        Position positionm1_m1 = new Position(-1,-1);

        // pose des parcelles
        spyPlateau.poseParcelle(new ParcelleCouleur(position1_1, Couleur.VERTE));
        spyPlateau.poseParcelle(new ParcelleCouleur(position2_0, Couleur.JAUNE));
        spyPlateau.poseParcelle(new ParcelleCouleur(position1_m1, Couleur.ROSE));
        spyPlateau.poseParcelle(new ParcelleCouleur(positionm1_m1, Couleur.ROSE));

        //test
        Position positionInitial = spyPlateau.getJardinier().getPosition();;
        assertEquals(new Position(), positionInitial);

        strategieParcelle.actionJardinier(spyPlateau,piocheSectionBambou,objectifs);
        assertNotEquals(positionInitial, spyPlateau.getJardinier().getPosition());
        try {
            verify(spyPlateau, times(1)).deplacementJardinier(any(Position.class));
        } catch (ParcelleNonPoseeException e) {
            System.out.println(e);
        }
    }

    @Test
    void choisirActionPanda() {
        PiocheSectionBambou piocheSectionBambou = new PiocheSectionBambou();
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));
        Plaquette plaquette = new Plaquette();

        // Position
        Position position1_1 = new Position(1,1);
        Position position2_0 = new Position(2,0);
        Position position1_m1 = new Position(1,-1);
        Position positionm1_m1 = new Position(-1,-1);

        // pose des parcelles
        spyPlateau.poseParcelle(new ParcelleCouleur(position1_1, Couleur.VERTE));
        spyPlateau.poseParcelle(new ParcelleCouleur(position2_0, Couleur.JAUNE));
        spyPlateau.poseParcelle(new ParcelleCouleur(position1_m1, Couleur.ROSE));
        spyPlateau.poseParcelle(new ParcelleCouleur(positionm1_m1, Couleur.ROSE));

        //test
        Position positionInitial = spyPlateau.getPanda().getPosition();
        assertEquals(new Position(), positionInitial);

        strategieParcelle.actionPanda(spyPlateau,objectifs, plaquette);
        verify(spyPlateau, times(1)).deplacementPanda(any(Position.class));

    }
}