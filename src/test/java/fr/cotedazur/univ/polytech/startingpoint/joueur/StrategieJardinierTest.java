package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
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
    boolean[] piochesVides;


    @BeforeEach
    void setUp() {
        strategieJardinier = new StrategieJardinier();
        objectifs = new ArrayList<>();
        plateau = new Plateau();
        piocheParcelle = new PiocheParcelle(new Random());
        piocheSectionBambou = new PiocheSectionBambou();
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
        try {
            for (int i=0; i<6; i++){
                strategieJardinier.actionParcelle(plateau, piocheParcelle, piocheSectionBambou, objectifs);
            }
        } catch (PiocheParcelleEnCoursException | PiocheParcelleVideException e) {
            System.out.println(e);
        }
        assertEquals(7, plateau.getParcelles().length);
    }

    @Test
    void actionIrrigation(){
        try {
            for (int i=0; i<4; i++){
                strategieJardinier.actionParcelle(plateau, piocheParcelle, piocheSectionBambou, objectifs);
            }
        } catch (PiocheParcelleEnCoursException | PiocheParcelleVideException e) {
            System.out.println(e);
        }

        for (int i=0;i<2;i++){
            strategieJardinier.actionIrrigation(plateau, piocheIrrigation, piocheSectionBambou);
        }
        assertEquals(2, plateau.getIrrigationsPosees().size());
    }
}