package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
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
    Motif motif0011;
    Motif motifm1m10011;
    Couleur couleurDefaut = Couleur.VERT;

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
        listParcelleMap.add(etang);
        addInListPosition(p11,p20,p1m1,pm1m1,pm20,pm11);
        motif0011 = new MotifDiagonale(new ParcelleCouleur(new Position(0,0), couleurDefaut), new ParcelleCouleur(new Position(1,1), couleurDefaut));
        motifm1m10011 = new MotifDiagonale(new ParcelleCouleur(new Position(-1,-1), couleurDefaut), new ParcelleCouleur(new Position(0,0), couleurDefaut), new ParcelleCouleur(new Position(1,1), couleurDefaut));
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
    void testPlusProcheObjectifUneParcelle(){
        Parcelle[] parcellesMotif0011 = motif0011.getTableauParcelles();
        Parcelle[] tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(etang,GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap,parcellesMotif0011));

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(listPositionDisponible.get(0), couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap,parcellesMotif0011));
    }

    @Test
    void testPlusProcheObjectifAvecPlusieursParcelle(){
        Parcelle[] parcellesMotif0011 = motif0011.getTableauParcelles();
        Parcelle[] parcellesMotifm1m10011 = motifm1m10011.getTableauParcelles();
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(listPositionDisponible.get(3),couleurDefaut);
        listParcelleMap.add(parcelleCouleurm1m1);
        Parcelle[] tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(etang, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotif0011)); // Car la Parcelle ne peut pas faire d'objectif si il y a l'etang au centre

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(listPositionDisponible.get(0),couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotif0011));
        assertEquals(parcelleCouleur11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifm1m10011));


        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(1),couleurDefaut);
        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(listPositionDisponible.get(4),couleurDefaut);
        addInListParcelle(parcelleCouleur20,parcelleCouleurm20);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotif0011)); // Car ajouté en premier
        assertEquals(parcelleCouleur11, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifm1m10011)); // Car ajouté en premier

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(listPositionDisponible.get(5),couleurDefaut);
        listParcelleMap.add(parcelleCouleurm11);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleurm20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotif0011));
        assertEquals(parcelleCouleurm20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifm1m10011));

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3,1),couleurDefaut); // Possible car il y a 1,1 et 2,0
        listParcelleMap.add(parcelleCouleur31);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap,parcellesMotif0011)); // Car ajouté en premier
        assertEquals(parcelleCouleur20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifm1m10011)); // Car ajouté en premier

        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0,2),couleurDefaut); // Possible car il y a -1,1 et 1,1
        listParcelleMap.add(parcelleCouleur02);
        tabParcelleMap = transformListParcelleInTab(listParcelleMap);
        assertEquals(parcelleCouleur20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotif0011)); // Car ajouté en premier
        assertEquals(parcelleCouleurm20, GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(tabParcelleMap, parcellesMotifm1m10011));
    }


    @Test
    void motifAFaireObjectifSimple(){
        Parcelle[] motifParcelle0011 = motif0011.getTableauParcelles();
        Parcelle[] motifRessemblant = new Parcelle[2];
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(1),couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur20,motifParcelle0011);
        ParcelleDisponible parcelleDisponible31 = new ParcelleDisponible(new Position(3,1));
        motifRessemblant[0] = parcelleCouleur20;
        motifRessemblant[1] = parcelleDisponible31;
        assertArrayEquals(motifRessemblant,motifFaitParMethode);

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(listPositionDisponible.get(0),couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        motifRessemblant[0] = parcelleCouleur11;
        motifRessemblant[1] = new ParcelleDisponible(new Position(2,2));
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur11,motifParcelle0011);
        assertArrayEquals(motifRessemblant,motifFaitParMethode);

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3,1), couleurDefaut); // Ceci permet de faire un motif complet en 20 et 31
        listParcelleMap.add(parcelleCouleur31);
        motifRessemblant[0] = parcelleCouleur20;
        motifRessemblant[1] = parcelleCouleur31;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur20,motifParcelle0011);
        assertArrayEquals(motifRessemblant,motifFaitParMethode);
    }

    @Test
    void motifAFaireObjectifMoyen(){
        Parcelle[] motifParcellem1m10011 = motifm1m10011.getTableauParcelles();
        Parcelle[] motifRessemblant = new Parcelle[3];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(listPositionDisponible.get(2),couleurDefaut);
        listParcelleMap.add(parcelleCouleur1m1);
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur1m1,motifParcellem1m10011);
        ParcelleDisponible parcelleDisponible20 = new ParcelleDisponible(listPositionDisponible.get(1));
        ParcelleDisponible parcelleDisponible31 = new ParcelleDisponible(new Position(3,1));
        motifRessemblant[0] = parcelleCouleur1m1;
        motifRessemblant[1] = parcelleDisponible20;
        motifRessemblant[2] = parcelleDisponible31;
        assertArrayEquals(motifRessemblant,motifFaitParMethode);

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(parcelleDisponible20.position(),couleurDefaut);
        listParcelleMap.add(parcelleCouleur20);
        motifRessemblant[1] = parcelleCouleur20;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur1m1,motifParcellem1m10011);
        assertArrayEquals(motifRessemblant,motifFaitParMethode);

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(new Position(1,1),couleurDefaut);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(parcelleDisponible31.position(),couleurDefaut);
        addInListParcelle(parcelleCouleur11,parcelleCouleur31);
        motifRessemblant[2] = parcelleCouleur31;
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur1m1,motifParcellem1m10011);
        assertArrayEquals(motifRessemblant,motifFaitParMethode);

        motifRessemblant[0] = motifRessemblant[1];
        motifRessemblant[1] = motifRessemblant[2];
        motifRessemblant[2] = new ParcelleDisponible(new Position(4,2));
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur20,motifParcellem1m10011);
        assertArrayEquals(motifRessemblant,motifFaitParMethode);

        motifRessemblant[0] = motifRessemblant[1];
        motifRessemblant[1] = motifRessemblant[2];
        motifRessemblant[2] = new ParcelleDisponible(new Position(5,3));
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleur31,motifParcellem1m10011);
        assertArrayEquals(motifRessemblant,motifFaitParMethode);
    }

    @Test
    void checkMotifCompletSimple(){
        Parcelle[] motifParcelle0011 = motif0011.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),etang,motifParcelle0011);
        assertEquals(0, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(listPositionDisponible.get(0),couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),etang,motifParcelle0011);
        assertEquals(1, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode)); // C'est un motif complet mais comme il y a un etang donc il ne peut être un motif pour un objectifParcelle

        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(listPositionDisponible.get(4),couleurDefaut);
        listParcelleMap.add(parcelleCouleurm20);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleurm20,motifParcelle0011);
        assertEquals(1, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(listPositionDisponible.get(5), couleurDefaut); // Ceci permet de faire un motif complet en -2,0 et -1,1
        listParcelleMap.add(parcelleCouleurm11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleurm20,motifParcelle0011);
        assertEquals(2, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));
    }

    @Test
    void countParcelleCouleurMotifMoyenEtang() {
        Parcelle[] motifParcellem1m10011 = motifm1m10011.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcellem1m10011);
        assertEquals(0, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(listPositionDisponible.get(0), couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcellem1m10011);
        assertEquals(1, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(1), couleurDefaut);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3, 1), couleurDefaut);
        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(new Position(2, 2), couleurDefaut);
        addInListParcelle(parcelleCouleur20, parcelleCouleur31, parcelleCouleur22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcellem1m10011);
        assertEquals(2, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode)); // C'est un motif complet mais comme il y a un etang donc il ne peut être un motif pour un objectifParcelle
    }

    @Test
    void checkMotifCompletMoyen(){
        Parcelle[] motifParcellem1m10011 = motifm1m10011.getTableauParcelles();
        ParcelleCouleur parcelleCouleurm20 = new ParcelleCouleur(listPositionDisponible.get(4),couleurDefaut);
        listParcelleMap.add(parcelleCouleurm20);
        Parcelle[]  motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleurm20,motifParcellem1m10011);
        assertEquals(1, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));

        ParcelleCouleur parcelleCouleurm11 = new ParcelleCouleur(listPositionDisponible.get(5), couleurDefaut);
        listParcelleMap.add(parcelleCouleurm11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap),parcelleCouleurm20,motifParcellem1m10011);
        assertEquals(2, GestionnairePossibiliteMotif.countParcelleCouleurMotif(motifFaitParMethode));
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifSimple(){
        Parcelle[] motifParcelle0011 = motif0011.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcelle0011);
        Position position11 = listPositionDisponible.get(0);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position11,optPosition.get()); // Cela permet de donner une position même si le motif à une parcelle

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11,couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(position11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelle0011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        Position position22 = new Position(2,2);
        assertTrue(optPosition.isEmpty()); // Car la position 2,2 n'est pas présente dans la liste de Position Disponible

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(0),couleurDefaut);
        Position position31 = new Position(3,1);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31,couleurDefaut);
        addInListParcelle(parcelleCouleur20,parcelleCouleur31);
        addInListPosition(position31,position22);
        listPositionDisponible.remove(0);
        listPositionDisponible.remove(4);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelle0011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position22,optPosition.get());

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(listPositionDisponible.get(4),couleurDefaut);
        listParcelleMap.add(parcelleCouleur22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelle0011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Il ne renvoie rien car le motif est complet
    }

    @Test
    void cherchePositionPossibilitePourFaireMotifMoyen(){
        Parcelle[] motifParcellem1m10011 = motifm1m10011.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcellem1m10011);
        Position position11 = listPositionDisponible.get(0);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position11,optPosition.get()); // Cela permet de donner une position même si le motif à une parcelle

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11,couleurDefaut);
        listParcelleMap.add(parcelleCouleur11);
        listPositionDisponible.remove(position11);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty());

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(0),couleurDefaut);
        Position position31 = new Position(3,1);
        Position position22 = new Position(2,2);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31,couleurDefaut);
        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22,couleurDefaut);
        addInListParcelle(parcelleCouleur20,parcelleCouleur31,parcelleCouleur22);
        listPositionDisponible.remove(0);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Car il y a pas encore de position pour completer le motif

        Position position42 = new Position(4,2);
        listPositionDisponible.add(position42);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position42,optPosition.get());

        ParcelleCouleur parcelleCouleur42 = new ParcelleCouleur(position42,couleurDefaut);
        listParcelleMap.add(parcelleCouleur42);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Il ne renvoie rien car le motif est complet

        Position position33 = new Position(3,3);
        listPositionDisponible.add(position33);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position33,optPosition.get());

        ParcelleCouleur parcelleCouleur33 = new ParcelleCouleur(position33,couleurDefaut);
        listParcelleMap.add(parcelleCouleur33);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Il ne renvoie rien car le motif est complet
    }

    @Test
    void cherchePositionARecupererMotifSimple(){
        Parcelle[] motifParcelle0011 = motif0011.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcelle0011);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        Position position11 = listPositionDisponible.get(0);
        listPositionDisponible.remove(0);
        assertTrue(optPosition.isPresent());
        assertEquals(position11,optPosition.get());

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11,couleurDefaut);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelle0011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Car il n'y a pas de position à côté de la parcelle où on veut poser

        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(0),couleurDefaut); // On prendra donc une position de la liste en indice 0
        Position position31 = new Position(3,1);
        listParcelleMap.add(parcelleCouleur20);
        listPositionDisponible.remove(0);
        listPositionDisponible.add(position31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcelle0011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position31,optPosition.get()); // Car c'est le premier des voisins de la position 2,2 à être présent dans la liste de position disponible

        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31,couleurDefaut);
        listParcelleMap.add(parcelleCouleur31);
        listPositionDisponible.remove(position31);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcelle0011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Car le motif est complet
    }

    @Test
    void cherchePositionARecupererMotifMoyen(){
        Parcelle[] motifParcellem1m10011 = motifm1m10011.getTableauParcelles();
        Parcelle[] motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), etang, motifParcellem1m10011);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        Position position11 = listPositionDisponible.get(0);
        listPositionDisponible.remove(0);
        assertTrue(optPosition.isPresent());
        assertEquals(position11,optPosition.get());

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11,couleurDefaut);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(listPositionDisponible.get(0),couleurDefaut); // On prendra donc une position de la liste en indice 0
        Position position31 = new Position(3,1);
        Position position22 = new Position(2,2);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(position31,couleurDefaut);
        addInListParcelle(parcelleCouleur20,parcelleCouleur31);
        listPositionDisponible.remove(0);
        listPositionDisponible.add(position22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position22,optPosition.get()); // Car la position 2,2 est présent dans le tableau des positions disponibles

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22,couleurDefaut);
        Position position42 = new Position(4,2);
        listParcelleMap.add(parcelleCouleur22);
        listPositionDisponible.add(position42);
        listPositionDisponible.remove(position22);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur11, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isPresent());
        assertEquals(position42,optPosition.get()); // Car la position 2,2 est présent dans le tableau des positions disponibles

        ParcelleCouleur parcelleCouleur42 = new ParcelleCouleur(position42,couleurDefaut);
        listParcelleMap.add(parcelleCouleur42);
        motifFaitParMethode = GestionnairePossibiliteMotif.getMotifAFaire(transformListParcelleInTab(listParcelleMap), parcelleCouleur20, motifParcellem1m10011);
        optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(transformListPositionInTab(listPositionDisponible),motifFaitParMethode);
        assertTrue(optPosition.isEmpty()); // Car le motif est complet
    }

    @Test
    void positionPossiblePourPrendreMotif2Parcelles(){
        ObjectifParcelle objectifParcelleSimple2Parcelle = new ObjectifParcelle(3, motif0011);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleSimple2Parcelle);
        Position position11 = new Position(1,1);
        assertTrue(optPosition.isPresent());
        assertEquals(position11, optPosition.get());

        ParcelleCouleur parcelleCouleur11J = new ParcelleCouleur(position11, Couleur.JAUNE);
        Position position20 = new Position(2,0);
        listParcelleMap.add(parcelleCouleur11J);
        listPositionDisponible.remove(position11);
        listPositionDisponible.add(position20);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleSimple2Parcelle);
        assertTrue(optPosition.isPresent());
        assertEquals(position20, optPosition.get());

        ParcelleCouleur parcelleCouleur20V = new ParcelleCouleur(position20, couleurDefaut);
        listParcelleMap.add(parcelleCouleur20V);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleSimple2Parcelle);
        assertTrue(optPosition.isEmpty()); // Car Motif Complet en 1,1 et 2,0
    }

    @Test
    void positionPossiblePourPrendreMotif3Parcelles(){
        ObjectifParcelle objectifParcelleSimple3Parcelles = new ObjectifParcelle(3, motifm1m10011);
        Optional<Position> optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleSimple3Parcelles);
        Position position11 = new Position(1, 1);
        assertTrue(optPosition.isPresent());
        assertEquals(position11, optPosition.get());

        ParcelleCouleur parcelleCouleur11 = new ParcelleCouleur(position11, couleurDefaut);
        ParcelleCouleur parcelleCouleur20 = new ParcelleCouleur(new Position(2,0), couleurDefaut);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3,1), couleurDefaut);
        Position position22 = new Position(2,2);
        addInListParcelle(parcelleCouleur11, parcelleCouleur20, parcelleCouleur31);
        listPositionDisponible.add(position22);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleSimple3Parcelles);
        assertTrue(optPosition.isPresent()); // Car l'objectif n'est pas fini, il y a deux parcelle sur le plateau
        assertEquals(position22, optPosition.get());

        ParcelleCouleur parcelleCouleur22 = new ParcelleCouleur(position22,couleurDefaut);
        listPositionDisponible.remove(position22);
        Position position42 = new Position(4,2);
        listParcelleMap.add(parcelleCouleur22);
        listPositionDisponible.add(position42);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleSimple3Parcelles);
        assertTrue(optPosition.isPresent());
        assertEquals(position42, optPosition.get());

        ParcelleCouleur parcelleCouleur42 = new ParcelleCouleur(position42, couleurDefaut);
        listParcelleMap.add(parcelleCouleur42);
        optPosition = GestionnairePossibiliteMotif.positionPossiblePrendrePourMotif(transformListParcelleInTab(listParcelleMap), transformListPositionInTab(listPositionDisponible), objectifParcelleSimple3Parcelles);
        assertTrue(optPosition.isEmpty()); // Car ObjectifTermine
    }
}