package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    Plateau plateau;
    ParcelleCouleur pC20;
    ParcelleCouleur pC11;
    @BeforeEach
    void setUp(){
        plateau = new Plateau();
        pC20 = new ParcelleCouleur(new Position(2,0));
        pC11 = new ParcelleCouleur(new Position(1,1));
        try {
            plateau.addParcelle(pC20);
            plateau.addParcelle(pC11);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
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
        ParcelleCouleur pC31 = new ParcelleCouleur(new Position(3,1));
        List<Parcelle> listParcelle = new ArrayList<>();
        listParcelle.add(pC20);
        listParcelle.add(pC11);
        listParcelle.add(pC31);
        try {
            plateau.addParcelle(pC31);
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
        ParcelleCouleur pC40 = new ParcelleCouleur(new Position(4,0));
        try {
            plateau.getTableauVoisines(pC40);
        }
        catch (ParcelleNonExistanteException pNEE){
            assertEquals("La parcelle de position "+ pC40.getPosition()+" n'existe pas sur le plateau",pNEE.getMessage());
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
            plateau.addParcelle(new ParcelleCouleur(new Position(3,1)));
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
        List<Parcelle> listParcelle = new ArrayList<>();
        listParcelle.add(new Etang());
        listParcelle.add(pC20);
        listParcelle.add(pC11);
        assertEquals(3, parcelles.length);

        ParcelleCouleur pC31 = new ParcelleCouleur(new Position(3,1));
        ParcelleCouleur pCm1m1 = new ParcelleCouleur(new Position(-1,-1));
        listParcelle.add(pC31);
        listParcelle.add(pCm1m1);
        try {
            plateau.addParcelle(pC31);
            plateau.addParcelle(pCm1m1);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }
        parcelles = plateau.getParcelles();
        assertEquals(5, parcelles.length);
        for(Parcelle parcelle : parcelles){
            assertTrue(listParcelle.contains(parcelle));
        }
    }

    @Test
    void addParcelleParcelleExistanteException(){
        ParcelleCouleur pC20bis = new ParcelleCouleur(new Position(2,0));
        try {
            plateau.addParcelle(pC20bis);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
            assertEquals("La parcelle de position "+pC20bis.getPosition()+" est déjà existante",exception.getMessage());
        }
    }

    @Test
    void addParcelleNombreVoisinException(){
        ParcelleCouleur pC40 = new ParcelleCouleur(new Position(4,0));
        try {
            plateau.addParcelle(pC40);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
            assertEquals("Il manque des voisines",exception.getMessage());
        }
    }
}