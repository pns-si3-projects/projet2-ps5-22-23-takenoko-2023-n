package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StrategiePandaTest {
    StrategiePanda strategiePanda;
    List<Objectif> objectifs;


    @BeforeEach
    void setUp() {
        strategiePanda = new StrategiePanda();
        objectifs = new ArrayList<>();
    }


    @Test
    void choisiActionTour() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false};
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.PANDA.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs));

        actionsRealiseesTour[Plaquette.ActionPossible.PARCELLE.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs));
    }
}