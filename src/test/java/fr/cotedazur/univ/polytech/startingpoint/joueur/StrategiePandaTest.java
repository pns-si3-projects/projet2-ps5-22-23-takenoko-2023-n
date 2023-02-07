package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StrategiePandaTest {
    StrategiePanda strategiePanda;
    List<Objectif> objectifs;
    Plateau plateau;
    boolean[] piochesVides;
    ParcelleCouleur parcelleCouleur;
    PiocheParcelle piocheParcelle;
    PiocheSectionBambou piocheSectionBambou;
    PiocheIrrigation piocheIrrigation;
    PiocheObjectifPanda piocheObjectifPanda;
    PiocheObjectifJardinier piocheObjectifJardinier;
    PiocheObjectifParcelle piocheObjectifParcelle;


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
    }


    @Test
    void choisiActionTour() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false, false};
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.PARCELLE.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.JARDINIER,
                strategiePanda.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.JARDINIER.ordinal()] = true;
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
            strategiePanda.actionIrrigation(plateau,piocheIrrigation, piocheSectionBambou);
        }
        assertEquals(2,plateau.getIrrigationsPosees().size());
    }
    @Test
    void actionObjectif() {
        assertFalse(piocheObjectifPanda.isEmpty());
        strategiePanda.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda, objectifs);
        assertEquals(1, objectifs.size());
        assertEquals(ObjectifPanda.class, objectifs.get(0).getClass());
    }
}