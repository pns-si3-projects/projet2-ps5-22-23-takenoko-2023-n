package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GestionnairePossibiliteMotifTest {
    List<Parcelle> listParcelleMap;
    List<Position> listPositionDisponible;
    Etang etang;
    Position p11;
    Position p20;
    Position p1m1;
    Position pm1m1;
    Position pm20;
    Position pm11;
    ParcelleCouleur parcelleCouleur11;
    ParcelleCouleur parcelleCouleurm11;
    ParcelleCouleur parcelleCouleur02;
    ParcelleCouleur parcelleCouleur00;
    ParcelleCouleur parcelleCouleurm1m1;
    Couleur couleurDefaut = Couleur.VERTE;

    @BeforeEach
    void setUp() {
        listParcelleMap = new ArrayList<>();
        listPositionDisponible = new ArrayList<>();
        etang = new Etang();
        p11 = new Position(1,1);
        p20 = new Position(2,0);
        p1m1 = new Position(1,-1);
        pm1m1 = new Position(-1,-1);
        pm20 = new Position(-2, 0);
        pm11 = new Position(-1,1);
        parcelleCouleur11 = new ParcelleCouleur(p11, couleurDefaut);
        parcelleCouleurm11 = new ParcelleCouleur(pm11, couleurDefaut);
        parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), couleurDefaut);
        parcelleCouleur00 = new ParcelleCouleur(etang.getPosition(), couleurDefaut);
        parcelleCouleurm1m1 = new ParcelleCouleur(pm1m1, couleurDefaut);
        listParcelleMap.add(etang);
        addInListPosition(p11,p20,p1m1,pm1m1,pm20,pm11);
    }

    private Parcelle[] transformListParcelleInTab(List<Parcelle> parcelleMap){
        Parcelle[] tableauParcelle = new Parcelle[parcelleMap.size()];
        for(int i = 0; i < parcelleMap.size();i++) tableauParcelle[i] = parcelleMap.get(i);
        return tableauParcelle;
    }

    private Position[] transformListPositionInTab(List<Position> positionDisponible){
        Position[] tableauPosition = new Position[positionDisponible.size()];
        for(int i = 0; i < positionDisponible.size();i++) tableauPosition[i] = positionDisponible.get(i);
        return tableauPosition;
    }

    private void addInListParcelle(Parcelle... parcelles){
        listParcelleMap.addAll(Arrays.asList(parcelles));
    }

    private void addInListPosition(Position... positions){
        listPositionDisponible.addAll(Arrays.asList(positions));
    }

    @Test
    void getPlusProcheObjectifParcelleDiagonaleSansOrientation() {
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] parcellesMotifDiagonale = motifDiagonale.getTableauParcelles();
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        Parcelle[] tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifDiagonale)); // Car la Parcelle ne peut pas faire d'objectif si il y a l'etang au centre

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifDiagonale));


        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(pm11, couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifDiagonale)); // Car ajouté en premier

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifDiagonale));
    }

    @Test
    void getPlusProcheObjectifParcelleTriangleSansOrientation() {
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] parcellesMotifTriangle = motifTriangle.getTableauParcelles();
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        Parcelle[] tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifTriangle));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifTriangle));


        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur3m1);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifTriangle)); // Car ajouté en premier
    }

    @Test
    void getPlusProcheObjectifParcelleLosangeSansOrientation() {
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Parcelle[] parcellesMotifLosange= motifLosange.getTableauParcelles();
        listParcelleMap.add(parcelleCouleur11);
        Parcelle[] tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifLosange));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifLosange));


        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifLosange));

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(new Position(2, 2), couleurDefaut);
        listParcelleMap.add(parcelleCouleur22);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifLosange));
    }

    @Test
    void getPlusProcheObjectifParcelleDecaleSansOrientation() {
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] parcellesMotifDecale = motifDecale.getTableauParcelles();
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, Couleur.VERTE);
        listParcelleMap.add(parcelleCouleur1m1);
        Parcelle[] tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifDecale)); // Car la Parcelle ne peut pas faire d'objectif si il y a l'etang au centre

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifDecale));

        listParcelleMap.add(parcelleCouleur11);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifDecale));
    }

    @Test
    void getPlusProcheObjectifParcelleDiagonaleAvecOrientation() {
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] parcellesMotifDiagonale = motifDiagonale.getOrientation()[1];
        assertEquals(etang, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        listParcelleMap.add(parcelleCouleurm11);
        assertEquals(parcelleCouleurm11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        listParcelleMap.add(parcelleCouleur11);
        assertEquals(parcelleCouleurm11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        assertEquals(parcelleCouleurm11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        assertEquals(parcelleCouleurm11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));
    }

    @Test
    void getPlusProcheObjectifParcelleTriangleAvecOrientation() {
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm1m1, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] parcellesMotifDiagonale = motifTriangle.getOrientation()[4];
        assertEquals(etang, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        listParcelleMap.add(parcelleCouleurm1m1);
        assertEquals(parcelleCouleurm1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(pm20, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm20);
        assertEquals(parcelleCouleurm1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        ParcelleCouleur parcelleCouleurm3m1 = new ParcelleCouleur(new Position(-3, -1), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm3m1);
        assertEquals(parcelleCouleurm1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));
    }

    @Test
    void getPlusProcheObjectifParcelleLosangeAvecOrientation() {
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Parcelle[] parcellesMotifLosange = motifLosange.getOrientation()[1];
        assertEquals(etang, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifLosange));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifLosange));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifLosange));

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur3m1);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifLosange));

        ParcelleCouleur parcelleCouleur40 = new ParcelleCouleur(new Position(4, 0), couleurDefaut);
        listParcelleMap.add(parcelleCouleur40);
        assertEquals(parcelleCouleur1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifLosange));
    }

    @Test
    void getPlusProcheObjectifParcelleDecaleAvecOrientation() {
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] parcellesMotifDiagonale = motifDecale.getOrientation()[1];
        assertEquals(etang, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        listParcelleMap.add(parcelleCouleurm1m1);
        assertEquals(parcelleCouleurm1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        assertEquals(parcelleCouleurm1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        assertEquals(parcelleCouleurm1m1, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(transformListParcelleInTab(listParcelleMap), parcellesMotifDiagonale));
    }

    @Test
    void motifAFaireObjectifDiagonaleSansOrientation(){
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] motifParcelleDiagonale = motifDiagonale.getTableauParcelles();
        Parcelle[] motifRessemblantDiagonale = new Parcelle[3];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1,couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDiagonale);
        ParcelleDisponible parcelleDisponible20 = new ParcelleDisponible(p20);
        ParcelleDisponible parcelleDisponible31 = new ParcelleDisponible(new Position(3,1));
        motifRessemblantDiagonale[0] = parcelleCouleur1m1;
        motifRessemblantDiagonale[1] = parcelleDisponible20;
        motifRessemblantDiagonale[2] = parcelleDisponible31;
        assertArrayEquals(motifRessemblantDiagonale,motifFaitParMethode);

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(parcelleDisponible20.position(),couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifRessemblantDiagonale[1] = parcelleCouleur20;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(parcelleDisponible31.position(),couleurDefaut);
        addInListParcelle(parcelleCouleur11,parcelleCouleur31);
        motifRessemblantDiagonale[2] = parcelleCouleur31;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        motifRessemblantDiagonale[0] = motifRessemblantDiagonale[1];
        motifRessemblantDiagonale[1] = motifRessemblantDiagonale[2];
        motifRessemblantDiagonale[2] = new ParcelleDisponible(new Position(4,2));
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        motifRessemblantDiagonale[0] = motifRessemblantDiagonale[1];
        motifRessemblantDiagonale[1] = motifRessemblantDiagonale[2];
        motifRessemblantDiagonale[2] = new ParcelleDisponible(new Position(5,3));
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur31,motifParcelleDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);
    }

    @Test
    void motifAFaireObjectifTriangleSansOrientation(){
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleTriangle = motifTriangle.getTableauParcelles();
        Parcelle[] motifRessemblantTriangle = new Parcelle[3];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1,couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleTriangle);
        ParcelleDisponible parcelleDisponible3m1 = new ParcelleDisponible(new Position(3, -1));
        ParcelleDisponible parcelleDisponible20 = new ParcelleDisponible(p20);
        motifRessemblantTriangle[0] = parcelleCouleur1m1;
        motifRessemblantTriangle[1] = parcelleDisponible3m1;
        motifRessemblantTriangle[2] = parcelleDisponible20;
        assertArrayEquals(motifRessemblantTriangle, motifFaitParMethode);

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(parcelleDisponible20.position(),couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifRessemblantTriangle[2] = parcelleCouleur20;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleTriangle);
        assertArrayEquals(motifRessemblantTriangle, motifFaitParMethode);

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(parcelleDisponible3m1.position(),couleurDefaut);
        listParcelleMap.add(parcelleCouleur3m1);
        motifRessemblantTriangle[1] = parcelleCouleur3m1;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleTriangle);
        assertArrayEquals(motifRessemblantTriangle, motifFaitParMethode);
    }

    @Test
    void motifAFaireObjectifLosangeSansOrientation(){
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Parcelle[] parcellesMotifLosange = motifLosange.getTableauParcelles();
        Parcelle[] motifRessemblantLosange = new Parcelle[4];
        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(pm20, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm20);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm20, parcellesMotifLosange);
        ParcelleDisponible parcelleDisponiblem11 = new ParcelleDisponible(pm11);
        ParcelleDisponible parcelleDisponiblem31 = new ParcelleDisponible(new Position(-3,1));
        ParcelleDisponible parcelleDisponiblem22 = new ParcelleDisponible(new Position(-2,2));
        motifRessemblantLosange[0] = parcelleCouleurm20;
        motifRessemblantLosange[1] = parcelleDisponiblem11;
        motifRessemblantLosange[2] = parcelleDisponiblem31;
        motifRessemblantLosange[3] = parcelleDisponiblem22;
        assertArrayEquals(motifRessemblantLosange, motifFaitParMethode);

        listParcelleMap.add(parcelleCouleurm11);
        motifRessemblantLosange[1] = parcelleCouleurm11;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm20, parcellesMotifLosange);
        assertArrayEquals(motifRessemblantLosange, motifFaitParMethode);

        ParcelleCouleur parcelleCouleurm31 = new ParcelleCouleur(parcelleDisponiblem31.position(), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm31);
        motifRessemblantLosange[2] = parcelleCouleurm31;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm20, parcellesMotifLosange);
        assertArrayEquals(motifRessemblantLosange, motifFaitParMethode);

        ParcelleCouleur parcelleCouleurm22 = new ParcelleCouleur(parcelleDisponiblem22.position(), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm22);
        motifRessemblantLosange[3] = parcelleCouleurm22;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm20, parcellesMotifLosange);
        assertArrayEquals(motifRessemblantLosange, motifFaitParMethode);
    }

    @Test
    void motifAFaireObjectifDecaleSansOrientation(){
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleDiagonale = motifDecale.getTableauParcelles();
        Parcelle[] motifRessemblantDiagonale = new Parcelle[3];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDiagonale);
        ParcelleDisponible parcelleDisponible20 = new ParcelleDisponible(p20);
        ParcelleDisponible parcelleDisponible11 = new ParcelleDisponible(p11);
        motifRessemblantDiagonale[0] = parcelleCouleur1m1;
        motifRessemblantDiagonale[1] = parcelleDisponible20;
        motifRessemblantDiagonale[2] = parcelleDisponible11;
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        Parcelle parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifRessemblantDiagonale[1] = parcelleCouleur20;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        listParcelleMap.add(parcelleCouleur11);
        motifRessemblantDiagonale[2] = parcelleCouleur11;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);
    }

    @Test
    void motifAFaireObjectifDiagonaleAvecOrientation() {
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] motifParcellesDiagonale = motifDiagonale.getOrientation()[3];
        Parcelle[] motifRessemblantDiagonale = new Parcelle[3];
        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(pm11, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm11);
        ParcelleDisponible parcelleDisponiblem3m1 = new ParcelleDisponible(new Position(-3, -1));
        motifRessemblantDiagonale[0] = parcelleCouleurm11;
        motifRessemblantDiagonale[1] = new ParcelleDisponible(pm20);
        motifRessemblantDiagonale[2] = parcelleDisponiblem3m1;
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm11, motifParcellesDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(pm20, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm20);
        motifRessemblantDiagonale[1] = parcelleCouleurm20;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm11, motifParcellesDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        ParcelleCouleur parcelleCouleurm3m1 = new ParcelleCouleur(parcelleDisponiblem3m1.position(), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm3m1);
        motifRessemblantDiagonale[2] = parcelleCouleurm3m1;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm11, motifParcellesDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);
    }

    @Test
    void motifAFaireObjectifTriangleAvecOrientation() {
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcellesTriangle = motifTriangle.getOrientation()[3];
        Parcelle[] motifRessemblantTriangle = new Parcelle[3];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        motifRessemblantTriangle[0] = parcelleCouleur1m1;
        motifRessemblantTriangle[1] = new ParcelleDisponible(pm1m1);
        motifRessemblantTriangle[2] = new ParcelleDisponible(new Position(0, -2));
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesTriangle);
        assertArrayEquals(motifRessemblantTriangle, motifFaitParMethode);

        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(pm1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm1m1);
        motifRessemblantTriangle[1] = parcelleCouleurm1m1;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesTriangle);
        assertArrayEquals(motifRessemblantTriangle, motifFaitParMethode);

        ParcelleCouleur parcelleCouleur0m2 = new ParcelleCouleur(motifRessemblantTriangle[2].getPosition(), couleurDefaut);
        listParcelleMap.add(parcelleCouleur0m2);
        motifRessemblantTriangle[2] = parcelleCouleur0m2;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesTriangle);
        assertArrayEquals(motifRessemblantTriangle, motifFaitParMethode);
    }

    @Test
    void motifAFaireObjectifLosangeAvecOrientation() {
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11,parcelleCouleur02);
        Parcelle[] motifParcellesLosange = motifLosange.getOrientation()[1];
        Parcelle[] motifRessemblantLosange = new Parcelle[4];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        motifRessemblantLosange[0] = parcelleCouleur1m1;
        motifRessemblantLosange[1] = new ParcelleDisponible(new Position(3, -1));
        motifRessemblantLosange[2] = new ParcelleDisponible(p20);
        motifRessemblantLosange[3] = new ParcelleDisponible(new Position(4, 0));
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesLosange);
        assertArrayEquals(motifRessemblantLosange, motifFaitParMethode);

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifRessemblantLosange[2] = parcelleCouleur20;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesLosange);
        assertArrayEquals(motifRessemblantLosange, motifFaitParMethode);

        ParcelleCouleur parcelleCouleurm3m1 = new ParcelleCouleur(motifRessemblantLosange[1].getPosition(), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm3m1);
        motifRessemblantLosange[1] = parcelleCouleurm3m1;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesLosange);
        assertArrayEquals(motifRessemblantLosange, motifFaitParMethode);

        ParcelleCouleur parcelleCouleurm40 = new ParcelleCouleur(motifRessemblantLosange[3].getPosition(), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm40);
        motifRessemblantLosange[3] = parcelleCouleurm40;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesLosange);
        assertArrayEquals(motifRessemblantLosange, motifFaitParMethode);
    }

    @Test
    void motifAFaireObjectifDecaleAvecOrientation() {
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcellesDiagonale = motifDecale.getOrientation()[5];
        Parcelle[] motifRessemblantDiagonale = new Parcelle[3];
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifRessemblantDiagonale[0] = parcelleCouleur20;
        motifRessemblantDiagonale[1] = new ParcelleDisponible(p11);
        motifRessemblantDiagonale[2] = new ParcelleDisponible(pm11);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcellesDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        listParcelleMap.add(parcelleCouleur11);
        motifRessemblantDiagonale[1] = parcelleCouleur11;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcellesDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);

        listParcelleMap.add(parcelleCouleurm11);
        motifRessemblantDiagonale[2] = parcelleCouleurm11;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcellesDiagonale);
        assertArrayEquals(motifRessemblantDiagonale, motifFaitParMethode);
    }

    @Test
    void countParcelleCouleurMotifDiagonale() {
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] motifParcellemDiagonale = motifDiagonale.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcellemDiagonale);
        assertEquals(0, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellemDiagonale);
        assertEquals(1, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellemDiagonale);
        assertEquals(2, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3,1), couleurDefaut);
        addInListParcelle(parcelleCouleur31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellemDiagonale);
        assertEquals(3, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));
    }

    @Test
    void countParcelleCouleurMotifTriangle() {
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcellesTriangle = motifTriangle.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcellesTriangle);
        assertEquals(0, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesTriangle);
        assertEquals(1, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesTriangle);
        assertEquals(2, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        addInListParcelle(parcelleCouleur3m1);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesTriangle);
        assertEquals(3, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));
    }

    @Test
    void countParcelleCouleurMotifLosange() {
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Parcelle[] motifParcelleLosange = motifLosange.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcelleLosange);
        assertEquals(0, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleLosange);
        assertEquals(1, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        listParcelleMap.add(parcelleCouleur11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleLosange);
        assertEquals(2, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleLosange);
        assertEquals(3, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(new Position(2, 2), couleurDefaut);
        listParcelleMap.add(parcelleCouleur22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleLosange);
        assertEquals(4, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));
    }

    @Test
    void countParcelleCouleurMotifDecale() {
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcellesDecale = motifDecale.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcellesDecale);
        assertEquals(0, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesDecale);
        assertEquals(1, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesDecale);
        assertEquals(2, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        addInListParcelle(parcelleCouleur11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcellesDecale);
        assertEquals(3, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifDiagonaleSansOrientation(){
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] motifParcelleDiagonale = motifDiagonale.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcelleDiagonale);
        Position position11 = listPositionDisponible.get(0);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position11,optPosition.get()); // Cela permet de donner une position même si le motif à une parcelle

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11, couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(position11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(0), couleurDefaut);
        Position position31 = new Position(3,1);
        Position position22 = new Position(2,2);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31, couleurDefaut);
        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22, couleurDefaut);
        addInListParcelle(parcelleCouleur20,parcelleCouleur31,parcelleCouleur22);
        listPositionDisponible.remove(0);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Car il y a pas encore de position pour completer le motif

        Position position42 = new Position(4,2);
        listPositionDisponible.add(position42);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position42,optPosition.get());

        ParcelleCouleur parcelleCouleur42 = new ParcelleCouleur(position42,couleurDefaut);
        listParcelleMap.add(parcelleCouleur42);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Il ne renvoie rien car le motif est complet

        Position position33 = new Position(3,3);
        listPositionDisponible.add(position33);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position33,optPosition.get());

        ParcelleCouleur parcelleCouleur33 = new ParcelleCouleur(position33, couleurDefaut);
        listParcelleMap.add(parcelleCouleur33);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Il ne renvoie rien car le motif est complet
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifTriangleSansOrientation(){
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleTriangle = motifTriangle.getTableauParcelles();
        listParcelleMap.add(new ParcelleCouleur(p1m1, couleurDefaut));
        listPositionDisponible.remove(p1m1);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), listParcelleMap.get(1), motifParcelleTriangle);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p20, optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        Position position3m1 = new Position(3, -1);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(p20);
        listPositionDisponible.add(position3m1);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), listParcelleMap.get(1), motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position3m1, optPosition.get());

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(position3m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur3m1);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), listParcelleMap.get(1), motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Car le motif est complet
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifLosangeSansOrientation(){
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Parcelle[] motifParcelleLosange = motifLosange.getTableauParcelles();
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(p20);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleLosange);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p11, optPosition.get());

        Position position31 = new Position(3, 1);
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p20);
        listPositionDisponible.add(position31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position31, optPosition.get());

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31, couleurDefaut);
        Position position22 = new Position(2,2);
        listParcelleMap.add(parcelleCouleur31);
        listPositionDisponible.remove(position31);
        listPositionDisponible.add(position22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position22, optPosition.get());

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22, couleurDefaut);
        listParcelleMap.add(parcelleCouleur22);
        listPositionDisponible.remove(position22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifDecaleSansOrientation(){
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleDecale = motifDecale.getTableauParcelles();
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        listPositionDisponible.remove(p1m1);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDecale);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p20, optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(p20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDecale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p11, optPosition.get());

        listParcelleMap.add(parcelleCouleur11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur1m1, motifParcelleDecale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Car le motif est complet
    }
    @Test
    void cherchePositionPossibilitePourFaireMotifDiagonaleAvecOrientation() {
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] motifParcelleDiagonale = motifDiagonale.getOrientation()[4];
        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(p11, couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(listPositionDisponible.get(0));
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        Position positionm11 = listPositionDisponible.get(4);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(positionm11, optPosition.get());

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(positionm11, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm11);
        listPositionDisponible.add(new Position(0, 2));
        listPositionDisponible.remove(positionm11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(listPositionDisponible.get(3), couleurDefaut);
        Position positionm31 = new Position(-3, 1);
        listParcelleMap.add(parcelleCouleurm20);
        listPositionDisponible.add(positionm31);
        listPositionDisponible.remove(positionm11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(positionm31, optPosition.get());


        ParcelleCouleur parcelleCouleurm31 = new ParcelleCouleur(positionm31, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifTriangleAvecOrientation() {
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleTriangle = motifTriangle.getOrientation()[1];
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p11);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleTriangle);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p20, optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        Position position31 = new Position(3, 1);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.add(position31);
        listPositionDisponible.remove(p20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position31, optPosition.get());


        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31, couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifMoyenLosangeAvecOrientation() {
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleLosange = motifLosange.getOrientation()[5];
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(pm1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm1m1);
        listPositionDisponible.remove(pm1m1);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm1m1, motifParcelleLosange);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(pm20, optPosition.get());

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(pm20, couleurDefaut);
        Position positionm3m1 = new Position(-3, -1);
        listParcelleMap.add(parcelleCouleurm20);
        listPositionDisponible.add(positionm3m1);
        listPositionDisponible.remove(pm20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm1m1, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(positionm3m1, optPosition.get());

        ParcelleCouleur parcelleCouleurm3m1 = new ParcelleCouleur(positionm3m1, couleurDefaut);
        Position positionm40 = new Position(-4, 0);
        listParcelleMap.add(parcelleCouleurm3m1);
        listPositionDisponible.add(positionm40);
        listPositionDisponible.remove(positionm3m1);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm1m1, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(positionm40, optPosition.get());


        ParcelleCouleur parcelleCouleurm40 = new ParcelleCouleur(positionm40, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm40);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm1m1, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifDecaleAvecOrientation() {
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleDiagonale = motifDecale.getOrientation()[8];
        listParcelleMap.add(parcelleCouleurm11);
        listPositionDisponible.remove(pm11);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm11, motifParcelleDiagonale);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p11, optPosition.get());

        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p20, optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionARecupererMotifDiagonaleSansOrientation(){
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] motifParcelleDiagonale = motifDiagonale.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcelleDiagonale);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        Position position11 = listPositionDisponible.get(0);
        listPositionDisponible.remove(0);
        assertTrue(optPosition.isPresent());
        assertEquals(position11,optPosition.get());

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11, couleurDefaut);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(0), couleurDefaut);
        Position position31 = new Position(3,1);
        Position position22 = new Position(2,2);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31, couleurDefaut);
        addInListParcelle(parcelleCouleur20,parcelleCouleur31);
        listPositionDisponible.remove(0);
        listPositionDisponible.add(position22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position22,optPosition.get()); // Car la position 2,2 est présent dans le tableau des positions disponibles

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22,couleurDefaut);
        Position position42 = new Position(4,2);
        listParcelleMap.add(parcelleCouleur22);
        listPositionDisponible.add(position42);
        listPositionDisponible.remove(position22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position42,optPosition.get()); // Car la position 2,2 est présent dans le tableau des positions disponibles

        ParcelleCouleur parcelleCouleur42 = new ParcelleCouleur(position42,couleurDefaut);
        listParcelleMap.add(parcelleCouleur42);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Car le motif est complet
    }

    @Test
    void cherchePositionARecupererMotifTriangleSansOrientation(){
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleTriangle = motifTriangle.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcelleTriangle);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p20,optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(p20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());

        Position position31 = new Position(3, 1);
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.add(position31);
        listPositionDisponible.remove(p11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position31, optPosition.get());

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31, couleurDefaut);
        Position position40 = new Position(4, 0);
        listParcelleMap.add(parcelleCouleur31);
        listPositionDisponible.add(position40);
        listPositionDisponible.remove(position31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position40, optPosition.get());

        ParcelleCouleur parcelleCouleur40 = new ParcelleCouleur(position40, couleurDefaut);
        listParcelleMap.add(parcelleCouleur40);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionARecupererMotifLosangeSansOrientation(){
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Parcelle[] motifParcelleLosange = motifLosange.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcelleLosange);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p11,optPosition.get());

        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());

        listParcelleMap.add(parcelleCouleurm11);
        listPositionDisponible.add(parcelleCouleur02.getPosition());
        listPositionDisponible.remove(pm11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(parcelleCouleur02.getPosition(), optPosition.get());

        Position position22 = new Position(2, 2);
        listParcelleMap.add(parcelleCouleur02);
        listPositionDisponible.add(position22);
        listPositionDisponible.remove(parcelleCouleur02.getPosition());
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position22, optPosition.get());

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22, couleurDefaut);
        Position position13 = new Position(1,3);
        listParcelleMap.add(parcelleCouleur22);
        listPositionDisponible.add(position13);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position13, optPosition.get());

        ParcelleCouleur parcelleCouleur13 = new ParcelleCouleur(new Position(1, 3), couleurDefaut);
        listParcelleMap.add(parcelleCouleur13);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionARecupererMotifDecaleSansOrientation(){
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleDecale = motifDecale.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcelleDecale);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p11, optPosition.get());

        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleDecale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        Position position31 = new Position(3, 1);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.add(position31);
        listPositionDisponible.remove(p20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDecale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position31, optPosition.get());

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31, couleurDefaut);
        Position position22 = new Position(2, 2);
        listParcelleMap.add(parcelleCouleur31);
        listPositionDisponible.add(position22);
        listPositionDisponible.remove(position31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDecale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position22, optPosition.get());

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22, couleurDefaut);
        listParcelleMap.add(parcelleCouleur22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDecale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }



    @Test
    void cherchePositionARecupererMotifDiagonaleAvecOrientation() {
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        Parcelle[] motifParcelleDiagonale = motifDiagonale.getOrientation()[5];
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(parcelleCouleur20.getPosition());
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDiagonale);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        Position position11 = listPositionDisponible.get(0);
        assertTrue(optPosition.isPresent());
        assertEquals(position11,optPosition.get());

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11, couleurDefaut);
        Position positionm11 = listPositionDisponible.get(4);
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(position11);
        listPositionDisponible.add(new Position(3, 1));
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(positionm11,optPosition.get());

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(positionm11, couleurDefaut);
        Position position02 = new Position(0, 2);
        listParcelleMap.add(parcelleCouleurm11);
        listPositionDisponible.remove(positionm11);
        listPositionDisponible.add(position02);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position02,optPosition.get());

        ParcelleCouleur parcelleCouleurm02 = new ParcelleCouleur(position02, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm02);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleDiagonale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionARecupererMotifTriangleAvecOrientation() {
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleTriangle = motifTriangle.getOrientation()[5];
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(parcelleCouleur11.getPosition());
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleTriangle);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        Position position31 = new Position(3, 1);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(p20);
        listPositionDisponible.add(position31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position31, optPosition.get());

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelleTriangle);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionARecupererMotifLosangeAvecOrientation() {
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        Parcelle[] motifParcelleLosange = motifLosange.getOrientation()[5];
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p11);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(pm11, optPosition.get());

        listParcelleMap.add(parcelleCouleurm11);
        listPositionDisponible.remove(pm11);
        listPositionDisponible.add(parcelleCouleur02.getPosition());
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(parcelleCouleur02.getPosition(), optPosition.get());

        Position positionm22 = new Position(-2, 2);
        listParcelleMap.add(parcelleCouleur02);
        listPositionDisponible.add(positionm22);
        listPositionDisponible.remove(parcelleCouleur02.getPosition());
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(positionm22, optPosition.get());

        ParcelleCouleur parcelleCouleurm22 = new ParcelleCouleur(positionm22, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelleLosange);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void cherchePositionARecupererMotifDecaleAvecOrientation() {
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        Parcelle[] motifParcelleDecale = motifDecale.getOrientation()[1];
        listParcelleMap.add(parcelleCouleurm1m1);
        listPositionDisponible.remove(pm1m1);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm1m1, motifParcelleDecale);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p1m1, optPosition.get());

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        listPositionDisponible.remove(p1m1);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm1m1, motifParcelleDecale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(p20, optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleurm1m1, motifParcelleDecale);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible), motifFaitParMethode);
        assertTrue(optPosition.isEmpty());
    }

    @Test
    void positionPossiblePourPrendreMotifDiagonale(){
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        ObjectifParcelle objectifParcelleDiagonale = new ObjectifParcelle(4, motifDiagonale);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDiagonale);
        Position position11 = new Position(1, 1);
        assertTrue(optPosition.isPresent());
        assertEquals(position11, optPosition.get());

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11, couleurDefaut);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3,1), couleurDefaut);
        Position position22 = new Position(2,2);
        addInListParcelle(parcelleCouleur11, parcelleCouleur20, parcelleCouleur31);
        listPositionDisponible.add(position22);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDiagonale);
        assertTrue(optPosition.isPresent()); // Car l'objectif n'est pas fini, il y a deux parcelle sur le plateau
        assertEquals(position22, optPosition.get());

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22,couleurDefaut);
        listPositionDisponible.remove(position22);
        Position position42 = new Position(4,2);
        listParcelleMap.add(parcelleCouleur22);
        listPositionDisponible.add(position42);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDiagonale);
        assertTrue(optPosition.isPresent());
        assertEquals(position42, optPosition.get());

        ParcelleCouleur parcelleCouleur42 = new ParcelleCouleur(position42, couleurDefaut);
        listParcelleMap.add(parcelleCouleur42);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDiagonale);
        assertTrue(optPosition.isEmpty()); // Car ObjectifTermine
    }

    @Test
    void positionPossiblePourPrendreMotifTriangle(){
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        ObjectifParcelle objectifParcelleDiagonale = new ObjectifParcelle(3, motifTriangle);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDiagonale);
        assertTrue(optPosition.isPresent());
        assertEquals(p20, optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(p20);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDiagonale);
        assertTrue(optPosition.isPresent());
        assertEquals(p11, optPosition.get());

        Position position31 = new Position(3, 1);
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p11);
        listPositionDisponible.add(position31);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDiagonale);
        assertTrue(optPosition.isPresent());
        assertEquals(position31, optPosition.get());

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31, couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDiagonale);
        assertTrue(optPosition.isEmpty()); // Car ObjectifTermine
    }

    @Test
    void positionPossiblePourPrendreMotifLosange(){
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        ObjectifParcelle objectifParcelleLosange = new ObjectifParcelle(5, motifLosange);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleLosange);
        assertTrue(optPosition.isPresent());
        assertEquals(p11, optPosition.get());

        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p11);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleLosange);
        assertTrue(optPosition.isPresent());
        assertEquals(p20, optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        Position position31 = new Position(3, 1);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(p20);
        listPositionDisponible.add(position31);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleLosange);
        assertTrue(optPosition.isPresent());
        assertEquals(position31, optPosition.get());

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31, couleurDefaut);
        Position position40 = new Position(4, 0);
        Position position22 = new Position(2, 2);
        listParcelleMap.add(parcelleCouleur31);
        listPositionDisponible.remove(position31);
        listPositionDisponible.add(position40);
        listPositionDisponible.add(position22);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleLosange);
        assertTrue(optPosition.isPresent());
        assertEquals(position22, optPosition.get());

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22, couleurDefaut);
        listParcelleMap.add(parcelleCouleur22);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleLosange);
        assertTrue(optPosition.isEmpty()); // Car ObjectifTermine
    }

    @Test
    void positionPossiblePourPrendreMotifDecale(){
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        ObjectifParcelle objectifParcelleDecale = new ObjectifParcelle(4, motifDecale);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDecale);
        assertTrue(optPosition.isPresent());
        assertEquals(pm1m1, optPosition.get());

        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(pm1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm1m1);
        listPositionDisponible.remove(pm1m1);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDecale);
        assertTrue(optPosition.isPresent());
        assertEquals(pm20, optPosition.get());

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(pm20, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm20);
        listPositionDisponible.remove(pm20);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDecale);
        assertTrue(optPosition.isPresent());
        assertEquals(p11, optPosition.get());

        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(p11);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDecale);
        assertTrue(optPosition.isPresent());
        assertEquals(p20, optPosition.get());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(p20);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDecale);
        assertTrue(optPosition.isPresent());
        assertEquals(p1m1, optPosition.get());

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleDecale);
        assertTrue(optPosition.isEmpty()); // Car ObjectifTermine
    }

    @Test
    void checkMotifInBoardDiagonale() {
        Motif motifDiagonale = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00, parcelleCouleur11);
        ObjectifParcelle objectifParcelleMoyenDiagonale = new ObjectifParcelle(5, motifDiagonale);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleMoyenDiagonale));

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm11);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleMoyenDiagonale));

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1, 1), couleurDefaut);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2, 0), couleurDefaut);
        addInListParcelle(parcelleCouleur11, parcelleCouleur20);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleMoyenDiagonale));

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        assertTrue(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleMoyenDiagonale));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), couleurDefaut);
        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        addInListParcelle(parcelleCouleur1m1, parcelleCouleur3m1);
        listParcelleMap.remove(parcelleCouleur31);
        assertTrue(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleMoyenDiagonale));
    }

    @Test
    void checkMotifInBoardTriangle() {
        Motif motifTriangle = new MotifTriangle(parcelleCouleurm11, parcelleCouleur11, parcelleCouleur02);
        ObjectifParcelle objectifParcelleTriangle = new ObjectifParcelle(3, motifTriangle);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleTriangle));

        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(p1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleTriangle));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(p20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleTriangle));

        ParcelleCouleur parcelleCouleur3m1 = new ParcelleCouleur(new Position(3, -1), couleurDefaut);
        listParcelleMap.add(parcelleCouleur3m1);
        assertTrue(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleTriangle));
    }

    @Test
    void checkMotifInBoardLosange() {
        Motif motifLosange = new MotifLosange(parcelleCouleur00, parcelleCouleur11, parcelleCouleurm11, parcelleCouleur02);
        ObjectifParcelle objectifParcelleLosange = new ObjectifParcelle(5, motifLosange);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleLosange));

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(pm20, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm20);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleLosange));

        listParcelleMap.add(parcelleCouleurm11);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleLosange));

        ParcelleCouleur parcelleCouleurm31 = new ParcelleCouleur(new Position(-3, 1), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm31);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleLosange));

        ParcelleCouleur parcelleCouleurm40 = new ParcelleCouleur(new Position(-4, 0), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm40);
        assertTrue(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleLosange));
    }

    @Test
    void checkMotifInBoardDecale() {
        Motif motifDecale = new MotifV(parcelleCouleur00, parcelleCouleur11, parcelleCouleur02);
        ObjectifParcelle objectifParcelleTriangle = new ObjectifParcelle(4, motifDecale);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleTriangle));

        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(pm1m1, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm1m1);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleTriangle));

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(pm20, couleurDefaut);
        listParcelleMap.add(parcelleCouleurm20);
        assertFalse(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleTriangle));

        listParcelleMap.add(parcelleCouleurm11);
        assertTrue(GestionnairePossibiliteMotif.checkMotifInBoard(transformListParcelleInTab(listParcelleMap), objectifParcelleTriangle));
    }
}