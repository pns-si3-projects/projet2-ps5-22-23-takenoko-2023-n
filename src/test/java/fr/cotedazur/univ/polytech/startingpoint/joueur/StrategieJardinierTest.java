package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StrategieJardinierTest {
    StrategieJardinier strategieJardinier;
    List<Objectif> objectifs;
    Plateau plateau;
    PiocheParcelle piocheParcelle;
    PiocheSectionBambou piocheSectionBambou;
    PiocheIrrigation piocheIrrigation;
    PiocheObjectifJardinier piocheObjectifJardinier;
    PiocheObjectifPanda piocheObjectifPanda;
    PiocheObjectifParcelle piocheObjectifParcelle;
    boolean[] piochesVides;


    @BeforeEach
    void setUp() {
        strategieJardinier = new StrategieJardinier();
        objectifs = new ArrayList<>();
        plateau = new Plateau();
        piocheParcelle = new PiocheParcelle(new Random());
        piocheSectionBambou = new PiocheSectionBambou();
        piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        piochesVides = new boolean[]{false, false, false, false, false};
    }


    @Test
    void choisiActionTour() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false, false};
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategieJardinier.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.PARCELLE.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategieJardinier.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategieJardinier.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.PANDA.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PANDA,
                strategieJardinier.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));
    }

    @Test
    void actionParcelle() {
        for (int i = 0; i < 6; i++) {
            strategieJardinier.actionParcelle(plateau, piocheParcelle, piocheSectionBambou, objectifs);
        }
        assertEquals(7, plateau.getParcelles().length);
    }

    @Test
    void actionIrrigation() {
        for (int i = 0; i < 4; i++) {
            strategieJardinier.actionParcelle(plateau, piocheParcelle, piocheSectionBambou, objectifs);

        }
        for (int j = 0; j < 2; j++) {
            strategieJardinier.actionIrrigation(plateau, piocheIrrigation, piocheSectionBambou);
        }
        assertEquals(2, plateau.getIrrigationsPosees().size());
    }

    @Test
    void actionObjectif() {
        assertFalse(piocheObjectifJardinier.isEmpty());
        strategieJardinier.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda, objectifs);
        assertEquals(1, objectifs.size());
        assertEquals(ObjectifJardinier.class, objectifs.get(0).getClass());
    }
}