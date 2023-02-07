package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.MotifDiagonale;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StrategieParcelleTest {
    StrategieParcelle strategieParcelle;
    List<Objectif> objectifs;
    Plateau plateau;
    boolean[] piochesVides;


    @BeforeEach
    void setUp() {
        strategieParcelle = new StrategieParcelle();
        objectifs = new ArrayList<>();
        plateau = new Plateau();
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
        assertFalse(strategieParcelle.checkPossibiliteActionJardinier(plateau, objectifs));

        ParcelleCouleur parcelleCouleurm1m1V = new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE);
        plateau.poseParcelle(parcelleCouleurm1m1V);
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
}