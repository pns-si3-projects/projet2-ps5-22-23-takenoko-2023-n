package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.ParcelleNonPoseeException;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    Plaquette plaquette;
    boolean[] piochesVides;


    @BeforeEach
    void setUp() {
        strategieJardinier = new StrategieJardinier();
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
            strategieJardinier.actionIrrigation(plateau, piocheIrrigation, plaquette);
        }
        assertEquals(2, plateau.getIrrigationsPosees().length);
    }

    @Test
    void actionJardinier(){
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));

        for (int i = 0; i < 6; i++) {
            strategieJardinier.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou, objectifs);
        }
        strategieJardinier.actionJardinier(spyPlateau, piocheSectionBambou, objectifs);
        try {
            verify(spyPlateau, times(1)).deplacementJardinier(any(Position.class));
        } catch (ParcelleNonPoseeException e) {
            System.out.println(e);
        }
    }

    @Test
    void actionPanda(){
        Plateau spyPlateau = spy(new Plateau(piocheSectionBambou));

        for (int i = 0; i < 6; i++){
            strategieJardinier.actionParcelle(spyPlateau, piocheParcelle, piocheSectionBambou, objectifs);
        }

        Position positionDepart = spyPlateau.getPanda().getPosition();
        assertEquals(new Position(), positionDepart);

        strategieJardinier.actionPanda(spyPlateau, objectifs, plaquette);

        Position positionFinale = spyPlateau.getPanda().getPosition();
        assertNotEquals(positionDepart, positionFinale);

        verify(spyPlateau, times(1)).deplacementPanda(any(Position.class));
    }

    @Test
    void actionObjectif() {
        assertFalse(piocheObjectifJardinier.isEmpty());
        strategieJardinier.actionObjectif(piocheObjectifParcelle, piocheObjectifJardinier, piocheObjectifPanda, objectifs);
        assertEquals(1, objectifs.size());
        assertEquals(ObjectifJardinier.class, objectifs.get(0).getClass());
    }
}