package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void choisiActionTour() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false, false};
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.PARCELLE.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.JARDINIER.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategieParcelle.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));
    }
}