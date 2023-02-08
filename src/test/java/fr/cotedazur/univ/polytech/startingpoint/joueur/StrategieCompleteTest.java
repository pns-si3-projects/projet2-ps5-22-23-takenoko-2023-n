package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StrategieCompleteTest {
    StrategieComplete strategieComplete;
    List<Objectif> objectifs;
    Plateau plateau;
    PiocheParcelle piocheParcelle;
    PiocheSectionBambou piocheSectionBambou;
    PiocheIrrigation piocheIrrigation;
    PiocheObjectifJardinier piocheObjectifJardinier;
    PiocheObjectifPanda piocheObjectifPanda;
    PiocheObjectifParcelle piocheObjectifParcelle;
    Plaquette plaquette;
    boolean[] piochesVides;


    @BeforeEach
    void setup() {
        strategieComplete = new StrategieComplete();
        objectifs = new ArrayList<>();
        plateau = new Plateau(new PiocheSectionBambou());
        piocheParcelle = new PiocheParcelle(new Random());
        piocheSectionBambou = new PiocheSectionBambou();
        piocheObjectifJardinier = new PiocheObjectifJardinier(new Random());
        piocheObjectifPanda = new PiocheObjectifPanda(new Random());
        piocheObjectifParcelle = new PiocheObjectifParcelle(new Random());
        piocheIrrigation = new PiocheIrrigation();
        piochesVides = new boolean[]{false, false, false, false, false};
        plaquette = new Plaquette();

        objectifs.add(piocheObjectifPanda.pioche());
        objectifs.add(piocheObjectifParcelle.pioche());
        objectifs.add(piocheObjectifJardinier.pioche());
    }

    @Test
    void choisiActionTour() {
        boolean[] actionsRealiseesTour = new boolean[]{false, false, false, false, false};
        assertEquals(Plaquette.ActionPossible.OBJECTIF,
                strategieComplete.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.OBJECTIF.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.IRRIGATION,
                strategieComplete.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.IRRIGATION.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategieComplete.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));

        actionsRealiseesTour[Plaquette.ActionPossible.PANDA.ordinal()] = true;
        assertEquals(Plaquette.ActionPossible.PARCELLE,
                strategieComplete.choisiActionTour(actionsRealiseesTour, objectifs, plateau, piochesVides));
    }

    @Test
    void actionObjectif(){
        strategieComplete.actionObjectif(piocheObjectifParcelle,piocheObjectifJardinier,piocheObjectifPanda,objectifs);

        List<Objectif> objectifsPandaList = new ArrayList<>();
        List<Objectif> objectifParcelleList = new ArrayList<>();
        List<Objectif> objectifsJardinierList = new ArrayList<>();
        for (Objectif objectif : objectifs){
            if (objectif.getClass().equals(ObjectifPanda.class)) objectifsPandaList.add(objectif);
            else if (objectif.getClass().equals(ObjectifParcelle.class)) objectifParcelleList.add(objectif);
            else objectifsJardinierList.add(objectif);
        }

        assertEquals(2, objectifsPandaList.size());
        assertEquals(1, objectifParcelleList.size());
        assertEquals(1, objectifsJardinierList.size());

        strategieComplete.actionObjectif(piocheObjectifParcelle,piocheObjectifJardinier,piocheObjectifPanda,objectifs);

        List<Objectif> objectifsPandaList_2 = new ArrayList<>();
        List<Objectif> objectifParcelleList_2 = new ArrayList<>();
        List<Objectif> objectifsJardinierList_2 = new ArrayList<>();
        for (Objectif objectif : objectifs){
            if (objectif.getClass().equals(ObjectifPanda.class)) objectifsPandaList_2.add(objectif);
            else if (objectif.getClass().equals(ObjectifParcelle.class)) objectifParcelleList_2.add(objectif);
            else objectifsJardinierList_2.add(objectif);
        }
        
        assertEquals(2, objectifsPandaList_2.size());
        assertEquals(2, objectifParcelleList_2.size());
        assertEquals(1, objectifsJardinierList_2.size());
    }

}
