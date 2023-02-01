package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategieParcelleTest {
    StrategieParcelle strategieParcelle;
    List<Objectif> objectifs;


    @BeforeEach
    void setUp() {
        strategieParcelle = new StrategieParcelle();
        objectifs = new ArrayList<>();
    }


    @Test
    void choisiActionTour() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false};
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.PARCELLE.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.PANDA.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs));
    }
}