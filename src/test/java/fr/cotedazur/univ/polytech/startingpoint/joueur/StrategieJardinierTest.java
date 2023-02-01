package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StrategieJardinierTest {
    StrategieJardinier strategieJardinier;
    List<Objectif> objectifs;


    @BeforeEach
    void setUp() {
        strategieJardinier = new StrategieJardinier();
        objectifs = new ArrayList<>();
    }


    @Test
    void choisiActionTour() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false};
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategieJardinier.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.JARDINIER.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategieJardinier.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategieJardinier.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.PARCELLE.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategieJardinier.choisiActionTour(actionsRealiseesTour, objectifs));
    }
}