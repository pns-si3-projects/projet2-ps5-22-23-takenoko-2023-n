package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.motif.*;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.AjoutCouleurException;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GestionnaireObjectifsTest {
    @Mock
    Bambou bambouMockJaune1;
    Bambou bambouMockVert1;

    @BeforeEach
    void setUp() {
        bambouMockJaune1 = mock(Bambou.class);
        bambouMockVert1 = mock(Bambou.class);
    }

    @Test
    void checkObjectifSimpleJardinierVert() {
        when(bambouMockVert1.getTailleBambou()).thenReturn(1, 2, 4);
        when(bambouMockVert1.getCouleur()).thenReturn(Couleur.VERTE, Couleur.VERTE, Couleur.VERTE);
        when(bambouMockJaune1.getTailleBambou()).thenReturn(1, 4, 0);
        when(bambouMockJaune1.getCouleur()).thenReturn(Couleur.JAUNE, Couleur.JAUNE, Couleur.JAUNE);
        ObjectifJardinier objectifJardinierV = new ObjectifJardinier(4, 4, Couleur.VERTE);
        List<Bambou> listBambousPlateau = new ArrayList<>();
        listBambousPlateau.add(bambouMockVert1);
        assertFalse(GestionnaireObjectifs.checkObjectifJardinier(listBambousPlateau.toArray(new Bambou[0]),
                objectifJardinierV));

        listBambousPlateau.add(bambouMockJaune1);
        assertFalse(GestionnaireObjectifs.checkObjectifJardinier(listBambousPlateau.toArray(new Bambou[0]),
                objectifJardinierV)); // Ici on test la couleur car le Bambou de section Jaune est de taille 4

        assertTrue(GestionnaireObjectifs.checkObjectifJardinier(listBambousPlateau.toArray(new Bambou[0]),
                objectifJardinierV));
    }

    @Test
    void checkObjectifDifficileJardinier() {
        when(bambouMockVert1.getTailleBambou()).thenReturn(3, 1, 2, 2, 2, 4, 3);
        when(bambouMockVert1.getCouleur()).thenReturn(Couleur.VERTE, Couleur.VERTE, Couleur.VERTE, Couleur.VERTE,
                Couleur.VERTE, Couleur.VERTE, Couleur.VERTE);
        when(bambouMockJaune1.getTailleBambou()).thenReturn(1, 4, 4, 4, 3, 4, 1);
        when(bambouMockJaune1.getCouleur()).thenReturn(Couleur.JAUNE, Couleur.JAUNE, Couleur.JAUNE, Couleur.JAUNE,
                Couleur.JAUNE, Couleur.JAUNE, Couleur.JAUNE);
        ObjectifJardinier objectifJardinierV = new ObjectifJardinier(8, 12, Couleur.VERTE);

        Bambou bambouVert1 = new Bambou(new ParcelleCouleur(new Position(1, 1), Couleur.VERTE));
        Bambou bambouVert2 = new Bambou(new ParcelleCouleur(new Position(2, 0), Couleur.VERTE));
        Bambou bambouVert3 = new Bambou(new ParcelleCouleur(new Position(-2, 0), Couleur.VERTE));
        Bambou[] tableauBambou = new Bambou[5];
        tableauBambou[0] = bambouMockVert1;
        tableauBambou[1] = bambouMockJaune1;
        tableauBambou[2] = bambouVert1;
        tableauBambou[3] = bambouVert2;
        tableauBambou[4] = bambouVert3;

        assertFalse(GestionnaireObjectifs.checkObjectifJardinier(tableauBambou, objectifJardinierV));

        for (int i = 0; i < 3; i++) {
            for (int j = 2; j < 5; j++) {
                try {
                    tableauBambou[j].ajouteSectionBambou(new SectionBambou(Couleur.VERTE));
                } catch (AjoutCouleurException e) {
                    assert false : "Doit être de même couleur";
                }
            }

            assertFalse(GestionnaireObjectifs.checkObjectifJardinier(tableauBambou, objectifJardinierV));
        }

        // Pour test la couleur
        assertFalse(GestionnaireObjectifs.checkObjectifJardinier(tableauBambou, objectifJardinierV));
        // Pour tester si un bambou est de taille supérieur
        assertFalse(GestionnaireObjectifs.checkObjectifJardinier(tableauBambou, objectifJardinierV));
        assertTrue(GestionnaireObjectifs.checkObjectifJardinier(tableauBambou, objectifJardinierV));
    }

    @Test
    void checkObjectifParcelleDiagonale() {
        ObjectifParcelle objectifParcelleDiagonale = new ObjectifParcelle(3,
                new MotifDiagonale(new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE),
                        new ParcelleCouleur(new Position(0, 0), Couleur.VERTE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.VERTE)));

        List<Parcelle> listParcellesPlateau = new ArrayList<>(5);
        listParcellesPlateau.add(new Etang());
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleDiagonale));

        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.VERTE);
        listParcellesPlateau.add(parcelleCouleurm1m1);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleDiagonale));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), Couleur.VERTE);
        listParcellesPlateau.add(parcelleCouleur1m1);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleDiagonale));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), Couleur.VERTE);
        listParcellesPlateau.add(parcelleCouleur20);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleDiagonale));

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), Couleur.VERTE);
        listParcellesPlateau.add(parcelleCouleur3m1);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleDiagonale));

        parcelleCouleurm1m1.setIrriguee(true);
        parcelleCouleur1m1.setIrriguee(true);
        parcelleCouleur3m1.setIrriguee(true);
        parcelleCouleur20.setIrriguee(true);

        assertTrue(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleDiagonale));
    }

    @Test
    void checkObjectifParcelleTriangle() {
        ObjectifParcelle objectifParcelleTriangle = new ObjectifParcelle(2,
                new MotifTriangle(new ParcelleCouleur(new Position(0, 0), Couleur.JAUNE),
                        new ParcelleCouleur(new Position(2, 0), Couleur.JAUNE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.JAUNE)));

        List<Parcelle> listParcellesPlateau = new ArrayList<>(4);
        listParcellesPlateau.add(new Etang());
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleTriangle));

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(new Position(-2, 0), Couleur.JAUNE);
        listParcellesPlateau.add(parcelleCouleurm20);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleTriangle));

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.JAUNE);
        listParcellesPlateau.add(parcelleCouleurm11);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleTriangle));

        ParcelleCouleur parcelleCouleurm31 = new ParcelleCouleur(new Position(-3, 1), Couleur.JAUNE);
        listParcellesPlateau.add(parcelleCouleurm31);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleTriangle));

        parcelleCouleurm20.setIrriguee(true);
        parcelleCouleurm11.setIrriguee(true);
        parcelleCouleurm31.setIrriguee(true);

        assertTrue(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleTriangle));
    }

    @Test
    void checkObjectifParcelleV() {
        ObjectifParcelle objectifParcelleV = new ObjectifParcelle(2,
                new MotifV(new ParcelleCouleur(new Position(0, 0), Couleur.ROSE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.ROSE),
                        new ParcelleCouleur(new Position(0, 2), Couleur.ROSE)));

        List<Parcelle> listParcellesPlateau = new ArrayList<>(4);
        listParcellesPlateau.add(new Etang());
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleV));

        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.ROSE);
        listParcellesPlateau.add(parcelleCouleurm1m1);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleV));

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.ROSE);
        listParcellesPlateau.add(parcelleCouleurm11);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleV));

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(new Position(-2, 0), Couleur.ROSE);
        listParcellesPlateau.add(parcelleCouleurm20);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleV));

        parcelleCouleurm1m1.setIrriguee(true);
        parcelleCouleurm11.setIrriguee(true);
        parcelleCouleurm20.setIrriguee(true);

        assertTrue(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleV));
    }

    @Test
    void checkObjectifParcelleLosange() {
        ObjectifParcelle objectifParcelleLosange = new ObjectifParcelle(5,
                new MotifLosange(new ParcelleCouleur(new Position(0, 0), Couleur.JAUNE),
                        new ParcelleCouleur(new Position(1, 1), Couleur.ROSE),
                        new ParcelleCouleur(new Position(-1, 1), Couleur.JAUNE),
                        new ParcelleCouleur(new Position(0, 2), Couleur.ROSE)));

        List<Parcelle> listParcellesPlateau = new ArrayList<>(5);
        listParcellesPlateau.add(new Etang());
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleLosange));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), Couleur.JAUNE);
        listParcellesPlateau.add(parcelleCouleur1m1);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleLosange));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), Couleur.JAUNE);
        listParcellesPlateau.add(parcelleCouleur20);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleLosange));

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), Couleur.ROSE);
        listParcellesPlateau.add(parcelleCouleur3m1);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleLosange));

        ParcelleCouleur parcelleCouleur40 = new ParcelleCouleur(new Position(4, 0), Couleur.ROSE);
        listParcellesPlateau.add(parcelleCouleur40);
        assertFalse(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleLosange));

        parcelleCouleur1m1.setIrriguee(true);
        parcelleCouleur20.setIrriguee(true);
        parcelleCouleur3m1.setIrriguee(true);
        parcelleCouleur40.setIrriguee(true);

        assertTrue(GestionnaireObjectifs.checkObjectifParcelle(listParcellesPlateau.toArray(new Parcelle[0]),
                objectifParcelleLosange));
    }

    @Test
    void checkObjectifPanda() {
        SectionBambou sectionBambouRose = new SectionBambou(Couleur.ROSE);
        SectionBambou sectionBambouJaune = new SectionBambou(Couleur.JAUNE);
        SectionBambou sectionBambouVert = new SectionBambou(Couleur.VERTE);

        List<SectionBambou> sectionBambouList3Couleur = new ArrayList<>();
        sectionBambouList3Couleur.add(sectionBambouVert);
        sectionBambouList3Couleur.add(sectionBambouRose);
        sectionBambouList3Couleur.add(sectionBambouJaune);
        ObjectifPanda objectifPanda6pt = new ObjectifPanda(6, sectionBambouList3Couleur);

        List<SectionBambou> sectionBambousList2Couleur = new ArrayList<>();
        sectionBambousList2Couleur.add(sectionBambouJaune);
        sectionBambousList2Couleur.add(sectionBambouJaune);
        ObjectifPanda objectifPanda4pt = new ObjectifPanda(4, sectionBambousList2Couleur);

        SectionBambou[] reservesBambous = new SectionBambou[3];
        reservesBambous[0] = sectionBambouJaune;
        reservesBambous[1] = sectionBambouVert;
        reservesBambous[2] = sectionBambouVert;

        assertFalse(GestionnaireObjectifs.checkObjectifPanda(reservesBambous, objectifPanda4pt));
        assertFalse(GestionnaireObjectifs.checkObjectifPanda(reservesBambous, objectifPanda6pt));

        reservesBambous[1] = sectionBambouJaune;
        assertTrue(GestionnaireObjectifs.checkObjectifPanda(reservesBambous, objectifPanda4pt));
        assertFalse(GestionnaireObjectifs.checkObjectifPanda(reservesBambous, objectifPanda6pt));

        reservesBambous[1] = sectionBambouVert;
        reservesBambous[2] = sectionBambouRose;
        assertFalse(GestionnaireObjectifs.checkObjectifPanda(reservesBambous, objectifPanda4pt));
        assertTrue(GestionnaireObjectifs.checkObjectifPanda(reservesBambous, objectifPanda6pt));
    }
}