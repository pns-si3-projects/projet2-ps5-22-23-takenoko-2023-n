package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.*;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    Plateau plateau;
    ParcelleCouleur pC20;
    ParcelleCouleur pC11;
    Bambou bambou2_0;
    Bambou bambou1_1;
    SectionBambou secBam2_0;
    SectionBambou secBam1_1;
    Couleur couleurBambouDefault = Couleur.VERT;

    @BeforeEach
    void setUp(){
        plateau = new Plateau();
        pC20 = new ParcelleCouleur(new Position(2,0), couleurBambouDefault);
        pC11 = new ParcelleCouleur(new Position(1,1), couleurBambouDefault);
        secBam2_0 = new SectionBambou(couleurBambouDefault);
        secBam1_1 = new SectionBambou(couleurBambouDefault);
        try {
            plateau.addParcelle(pC20, secBam2_0);
            bambou2_0 = new Bambou(pC20);
            bambou2_0.ajouteSectionBambou(secBam2_0);
            plateau.addParcelle(pC11, secBam1_1);
            bambou1_1 = new Bambou(pC11);
            bambou1_1.ajouteSectionBambou(secBam1_1);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException | AjoutCouleurException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }
    }

    @Test
    void getListParcelle() {
        List<Parcelle> listParcelle = new ArrayList<>();
        listParcelle.add(pC20);
        listParcelle.add(pC11);
        Parcelle[] parcelles = plateau.getParcelles();
        assertEquals(3, parcelles.length);
        for (Parcelle parcelle : parcelles) {
            if (!parcelle.getClass().equals(Etang.class)) {
                assertTrue(listParcelle.contains(parcelle));
            }
        }
    }

    @Test
    void getListParcelleDeux() {
        ParcelleCouleur pC31 = new ParcelleCouleur(new Position(3,1), couleurBambouDefault);
        SectionBambou secBam3_1 = new SectionBambou(couleurBambouDefault);
        List<Parcelle> listParcelle = new ArrayList<>();
        listParcelle.add(pC20);
        listParcelle.add(pC11);
        listParcelle.add(pC31);
        try {
            plateau.addParcelle(pC31, secBam3_1);
        }
        catch (NombreParcelleVoisineException | ParcelleExistanteException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }

        Parcelle[] parcelles = plateau.getParcelles();
        assertEquals(4, parcelles.length);
        for (Parcelle parcelle : parcelles) {
            if (!parcelle.getClass().equals(Etang.class)) {
                assertTrue(listParcelle.contains(parcelle));
            }
        }
    }

    @Test
    void getParcellesCouleur() {
        ParcelleCouleur pCm1_m1R = new ParcelleCouleur(new Position(-1, -1), Couleur.ROSE);
        ParcelleCouleur pCm2_0J = new ParcelleCouleur(new Position(-2, 0), Couleur.JAUNE);
        ParcelleCouleur pCm1_1J = new ParcelleCouleur(new Position(-1, 1), Couleur.JAUNE);
        try {
            plateau.addParcelle(pCm1_m1R, new SectionBambou(Couleur.ROSE));
        } catch (ParcelleExistanteException | NombreParcelleVoisineException e) {
            throw new AssertionError(e);
        }
        List<ParcelleCouleur> parcellesCouleurDefaut = plateau.getParcellesCouleur(couleurBambouDefault);
        assertFalse(parcellesCouleurDefaut.isEmpty());
        assertTrue(parcellesCouleurDefaut.contains(pC11));
        assertTrue(parcellesCouleurDefaut.contains(pC20));

        List<ParcelleCouleur> parcellesRoses = plateau.getParcellesCouleur(Couleur.ROSE);
        assertFalse(parcellesRoses.isEmpty());
        assertTrue(parcellesRoses.contains(pCm1_m1R));

        List<ParcelleCouleur> parcellesJaunes = plateau.getParcellesCouleur(Couleur.JAUNE);
        assertTrue(parcellesJaunes.isEmpty());
        try {
            plateau.addParcelle(pCm2_0J, new SectionBambou(Couleur.JAUNE));
            plateau.addParcelle(pCm1_1J, new SectionBambou(Couleur.JAUNE));
        } catch (ParcelleExistanteException | NombreParcelleVoisineException e) {
            throw new AssertionError(e);
        }
        parcellesJaunes = plateau.getParcellesCouleur(Couleur.JAUNE);
        assertFalse(parcellesJaunes.isEmpty());
        assertTrue(parcellesJaunes.contains(pCm2_0J));
        assertTrue(parcellesJaunes.contains(pCm1_1J));
    }

    @Test
    void getBambous() {
        Bambou[] plateauBambous = plateau.getBambous();
        if(plateauBambous[0].equals(bambou2_0)){
            assertEquals(bambou2_0, plateauBambous[0]);
            assertEquals(bambou1_1, plateauBambous[1]);
        }
        else{
            assertEquals(bambou1_1, plateauBambous[0]);
            assertEquals(bambou2_0, plateauBambous[1]);
        }
    }

    @Test
    void getBambou() {
        Optional<Bambou> optBambou2_0 = plateau.getBambou(new Position(2, 0));
        Optional<Bambou> optBambou1_1 = plateau.getBambou(new Position(1, 1));
        optBambou2_0.ifPresent(bambou -> assertEquals(bambou2_0, bambou)); // bambou est optBambou2_0.get()
        optBambou1_1.ifPresent(bambou -> assertEquals(bambou1_1, bambou)); // bambou est optBambou1_1.get()
    }

    @Test
    void getTableauVoisinSansException() {
        try {
            Parcelle[] listVoisin = plateau.getTableauVoisines(pC20);
            assertEquals(listVoisin[4],new Etang());
            assertEquals(listVoisin[0],new ParcelleDisponible(new Position(3,1)));
            assertEquals(listVoisin[1],new ParcelleDisponible(new Position(4,0)));
            assertEquals(listVoisin[2],new ParcelleDisponible(new Position(3,-1)));
            assertEquals(listVoisin[3],new ParcelleDisponible(new Position(1,-1)));
            assertEquals(listVoisin[5],pC11);

            listVoisin = plateau.getTableauVoisines(pC11);
            assertEquals(listVoisin[3],new Etang());
            assertEquals(listVoisin[2],pC20);
        }
        catch (ParcelleNonExistanteException exception){
            assert false: "Ne doit pas renvoyer d'exception";
        }
    }

    @Test
    void getTableauVoisinAvecException() {
        ParcelleCouleur pC40 = new ParcelleCouleur(new Position(4,0), Couleur.JAUNE);
        try {
            plateau.getTableauVoisines(pC40);
        }
        catch (ParcelleNonExistanteException pNEE){
            assertEquals("La parcelle de position "+ pC40.position()+" n'existe pas sur le plateau",pNEE.getMessage());
        }
    }

    @Test
    void getPositionsDisponible() {
        Position[] listPosition = plateau.getPositionsDisponible();
        assertEquals(5, listPosition.length);
        assertEquals(new Position(3,1),listPosition[4]);
        assertEquals(new Position(1,-1),listPosition[0]);
        assertEquals(new Position(-1,-1),listPosition[1]);
        assertEquals(new Position(-2,0),listPosition[2]);
        assertEquals(new Position(-1,1),listPosition[3]);

        try {
            plateau.addParcelle(new ParcelleCouleur(new Position(3,1),couleurBambouDefault), new SectionBambou(couleurBambouDefault));
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }
        listPosition = plateau.getPositionsDisponible();
        assertEquals(6, listPosition.length);
        assertEquals(new Position(4,0),listPosition[4]);
        assertEquals(new Position(2,2),listPosition[5]);
    }

    @Test
    void getEtang() {
        assertEquals(new Etang(),plateau.getEtang());
    }

    @Test
    void addParcelleSansException() {
        Parcelle[] parcelles = plateau.getParcelles();
        Bambou[] bambous = plateau.getBambous();
        List<Parcelle> listParcelle = new ArrayList<>();
        listParcelle.add(new Etang());
        listParcelle.add(pC20);
        listParcelle.add(pC11);
        List<Bambou> listBambou = new ArrayList<>();
        listBambou.add(bambou2_0);
        listBambou.add(bambou1_1);
        assertEquals(3, parcelles.length);
        assertEquals(2, bambous.length);

        ParcelleCouleur pC31 = new ParcelleCouleur(new Position(3,1), couleurBambouDefault);
        ParcelleCouleur pCm1m1 = new ParcelleCouleur(new Position(-1,-1), couleurBambouDefault);
        SectionBambou secBam3_1 = new SectionBambou(couleurBambouDefault);
        SectionBambou secBamm1_m1 = new SectionBambou(couleurBambouDefault);
        Bambou bambou3_1 = new Bambou(pC31);
        Bambou bamboum1_m1 = new Bambou(pCm1m1);
        try {
            plateau.addParcelle(pC31, secBam3_1);
            listParcelle.add(pC31);
            bambou3_1.ajouteSectionBambou(secBam3_1);
            listBambou.add(bambou3_1);

            plateau.addParcelle(pCm1m1, secBamm1_m1);
            listParcelle.add(pCm1m1);
            bamboum1_m1.ajouteSectionBambou(secBamm1_m1);
            listBambou.add(bamboum1_m1);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException | AjoutCouleurException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }
        parcelles = plateau.getParcelles();
        bambous = plateau.getBambous();

        assertEquals(5, parcelles.length);
        assertEquals(4, bambous.length);
        for(Parcelle parcelle : parcelles){
            assertTrue(listParcelle.contains(parcelle));
        }
        for (Bambou bambou : bambous) {
            assertTrue(listBambou.contains(bambou));
        }
    }

    @Test
    void addParcelleParcelleExistanteException(){
        ParcelleCouleur pC20bis = new ParcelleCouleur(new Position(2,0), couleurBambouDefault);
        SectionBambou secBam2_0_bis = new SectionBambou(couleurBambouDefault);
        try {
            plateau.addParcelle(pC20bis, secBam2_0_bis);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
            assertEquals("La parcelle de position "+pC20bis.position()+" est déjà existante",exception.getMessage());
        }
    }

    @Test
    void addParcelleNombreVoisinException(){
        ParcelleCouleur pC40 = new ParcelleCouleur(new Position(4,0), couleurBambouDefault);
        SectionBambou secBam4_0 = new SectionBambou(couleurBambouDefault);
        try {
            plateau.addParcelle(pC40, secBam4_0);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
            assertEquals("Il manque des voisines",exception.getMessage());
        }
    }

    @Test
    void addIrrigation(){
        Position position11 = new Position(1,1);
        Position position20 = new Position(2,0);
        Position position31 = new Position(3,1);
        Position position22 = new Position(2,2);
        plateau.addIrrigation(position11, position20);
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(0, plateau.getIrrigationsDisponibles().size());
        ParcelleCouleur pc31 = new ParcelleCouleur(position31, couleurBambouDefault);
        SectionBambou secBam3_1 = new SectionBambou(couleurBambouDefault);
        Bambou bambou3_1;
        try {
            plateau.addParcelle(pc31, secBam3_1);
            bambou3_1 = new Bambou(pc31);
            bambou3_1.ajouteSectionBambou(secBam3_1);
        } catch (ParcelleExistanteException | NombreParcelleVoisineException | AjoutCouleurException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());
        assertFalse(pc31.isIrriguee());

        ParcelleCouleur pc22 = new ParcelleCouleur(position22, couleurBambouDefault);
        SectionBambou secBam2_2 = new SectionBambou(couleurBambouDefault);
        Bambou bambou2_2;
        try {
            plateau.addParcelle(pc22, secBam2_2);
            bambou2_2 = new Bambou(pc22);
            bambou2_2.ajouteSectionBambou(secBam2_2);
        } catch (ParcelleExistanteException | NombreParcelleVoisineException | AjoutCouleurException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());
        assertFalse(pc22.isIrriguee());

        plateau.addIrrigation(position11,position31);
        assertEquals(2, plateau.getIrrigationsPosees().size());
        assertEquals(3, plateau.getIrrigationsDisponibles().size());
        assertTrue(pc31.isIrriguee());

        plateau.addIrrigation(position20, position31);
        assertEquals(3, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());
    }

    @Test
    void isIrrigeeVoisinEtang(){
        assertTrue(pC11.isIrriguee());
        assertTrue(pC20.isIrriguee());
    }

    @Test
    void addIrrigationNonPossible(){
        Position position11 = new Position(1,1);
        Position position20 = new Position(2,0);
        Position position31 = new Position(3,1);
        Position position22 = new Position(2,2);
        plateau.addIrrigation(position11, position20);
        ParcelleCouleur pc31 = new ParcelleCouleur(position31, couleurBambouDefault);
        SectionBambou secBam3_1 = new SectionBambou(couleurBambouDefault);
        Bambou bambou3_1;
        ParcelleCouleur pc22 = new ParcelleCouleur(position22, couleurBambouDefault);
        SectionBambou secBam2_2 = new SectionBambou(couleurBambouDefault);
        Bambou bambou2_2;
        try {
            plateau.addParcelle(pc31, secBam3_1);
            bambou3_1 = new Bambou(pc31);
            bambou3_1.ajouteSectionBambou(secBam3_1);
            plateau.addParcelle(pc22, secBam2_2);
            bambou2_2 = new Bambou(pc22);
            bambou2_2.ajouteSectionBambou(secBam2_2);
        } catch (ParcelleExistanteException | NombreParcelleVoisineException | AjoutCouleurException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }
        //Ajout de l'irrigation alors qu'il n'y a pas d'irrigation avant : donc l'ajout est impossible et les set ne sont pas modifiés
        plateau.addIrrigation(position11,position22);
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());
        assertTrue(pC20.isIrriguee());
        assertTrue(pC11.isIrriguee());
        assertFalse(pc31.isIrriguee());
        assertFalse(pc22.isIrriguee());
    }



/*
    @Test
    void jardinierAddBambousParcelleExistante() throws ParcelleExistanteException, NombreParcelleVoisineException {
        ParcelleCouleur pc40=new ParcelleCouleur(new Position(4,0));
        ParcelleCouleur pc3_1=new ParcelleCouleur(new Position(3,-1));
        ParcelleCouleur pc5_1=new ParcelleCouleur(new Position(5,-1));
        ParcelleCouleur pc60=new ParcelleCouleur(new Position(6,0));
        ParcelleCouleur pc51=new ParcelleCouleur(new Position(5,1));
        ParcelleCouleur pc31=new ParcelleCouleur(new Position(3,1));

        SectionBambou secBam4_0=new SectionBambou();
        SectionBambou secBam3__1=new SectionBambou();
        SectionBambou secBam5__1=new SectionBambou();
        SectionBambou secBam6_0=new SectionBambou();
        SectionBambou secBam5_1=new SectionBambou();
        SectionBambou secBam3_1=new SectionBambou();

            plateau.addParcelle(pc40,secBam4_0);
            plateau.addParcelle(pc3_1,secBam3__1);
            plateau.addParcelle(pc5_1,secBam5__1);
            plateau.addParcelle(pc60,secBam6_0);
            plateau.addParcelle(pc51,secBam5_1);
            plateau.addParcelle(pc31,secBam3_1);


        try {
            plateau.jardinierAddBambous(pc40);
            assertEquals(plateau.jardinierAddBambous(pc40),7);
        } catch (ParcelleNonExistanteException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void jardinierAddBambousParcelleNonExistante(){

    }

 */
}