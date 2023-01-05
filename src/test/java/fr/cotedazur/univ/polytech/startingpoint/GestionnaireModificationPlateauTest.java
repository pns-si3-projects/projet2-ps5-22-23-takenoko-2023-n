package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireModificationPlateauTest {
    Plateau plateau;
    GestionnaireModificationPlateau gMP;
    Etang etang;
    Position positionEtang;
    ParcelleDisponible parcelleDisponible1_1;
    ParcelleDisponible parcelleDisponible2_0;
    ParcelleDisponible parcelleDisponible1_m1;
    ParcelleDisponible parcelleDisponiblem1_m1;
    ParcelleDisponible parcelleDisponiblem2_0;
    ParcelleDisponible parcelleDisponiblem1_1;
    ParcelleDisponible[] listParcelleDisponible;
    Position[] listPositionDisponible;
    ParcelleCouleur parcelleCouleurNonPosee;


    @BeforeEach
    void setUp(){
        plateau = new Plateau();
        gMP = new GestionnaireModificationPlateau();
        etang = plateau.getEtang();
        positionEtang = etang.getPosition();
        // en fait ici, je ne comprends pas pq tu veux refaire ça before each
        parcelleDisponible1_1 = new ParcelleDisponible(new Position(positionEtang.getX()+1, positionEtang.getY()+1));
        parcelleDisponible2_0 = new ParcelleDisponible(new Position(positionEtang.getX()+2, positionEtang.getY()));
        parcelleDisponible1_m1 = new ParcelleDisponible(new Position(positionEtang.getX()+1, positionEtang.getY()-1));
        parcelleDisponiblem1_m1 = new ParcelleDisponible(new Position(positionEtang.getX()-1, positionEtang.getY()-1));
        parcelleDisponiblem2_0 = new ParcelleDisponible(new Position(positionEtang.getX()-2, positionEtang.getY()));
        parcelleDisponiblem1_1 = new ParcelleDisponible(new Position(positionEtang.getX()-1, positionEtang.getY()+1));
        listParcelleDisponible = new ParcelleDisponible[6];
        listParcelleDisponible[0] = parcelleDisponible1_1;
        listParcelleDisponible[1] = parcelleDisponible2_0;
        listParcelleDisponible[2] = parcelleDisponible1_m1;
        listParcelleDisponible[3] = parcelleDisponiblem1_m1;
        listParcelleDisponible[4] = parcelleDisponiblem2_0;
        listParcelleDisponible[5] = parcelleDisponiblem1_1;
        listPositionDisponible = plateau.getPositionsDisponible();
        parcelleCouleurNonPosee = new ParcelleCouleur(listPositionDisponible[2]);
    }

    @Test
    void creeParcelleDisponible() {
        for (int i=0; i<6; i++) {
            assertEquals(listParcelleDisponible[i], gMP.creeParcelleDisponible(i, positionEtang));
        }

        // Cas indice incorrect
        assertThrows(IllegalArgumentException.class, () -> gMP.creeParcelleDisponible(6, positionEtang));
        assertThrows(IllegalArgumentException.class, () -> gMP.creeParcelleDisponible(-1, positionEtang));
    }

    @Test
    void chercheFuturesVoisinesSansException() {
        try {
            List<Parcelle> listVoisinesParcelle = gMP.chercheFuturesVoisines(parcelleCouleurNonPosee, plateau.getParcelles());
            assertEquals(1, listVoisinesParcelle.size());
            assertEquals(etang, listVoisinesParcelle.get(0));
        }
        catch (ParcelleExistanteException exception) {
            throw new AssertionError("Ne doit pas renvoyer d'exception");
        }

        try {
            plateau.addParcelle(parcelleCouleurNonPosee);
            plateau.getParcelles();
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
            throw new AssertionError("Ne doit pas renvoyer d'exception");
        }

        ParcelleCouleur parcelleCouleurNonPosee2 = new ParcelleCouleur(listPositionDisponible[3]);
        try {
            List<Parcelle> listVoisinParcelle = gMP.chercheFuturesVoisines(parcelleCouleurNonPosee2, plateau.getParcelles());
            assertEquals(2, listVoisinParcelle.size());
            assertEquals(etang, listVoisinParcelle.get(0));
            assertEquals(parcelleCouleurNonPosee, listVoisinParcelle.get(1));
        }
        catch (ParcelleExistanteException exception) {
            throw new AssertionError("Ne doit pas renvoyer d'exception");
        }
    }

    @Test
    void chercheFuturesVoisinesAvecExceptions() {
        assertThrows(ParcelleExistanteException.class,
                () -> gMP.chercheFuturesVoisines(etang, plateau.getParcelles()));

        try {
            plateau.addParcelle(parcelleCouleurNonPosee);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
            throw new AssertionError("Ne doit pas renvoyer d'exception");
        }

        ParcelleCouleur parcelleCouleurNonPoseeBis = new ParcelleCouleur(listPositionDisponible[2]);
        assertThrows(ParcelleExistanteException.class,
                () -> gMP.chercheFuturesVoisines(parcelleCouleurNonPoseeBis, plateau.getParcelles()));
    }

    @Test
    void addVoisinesParcelleSansException() {
        try {
            List<Parcelle> parcellesVoisine = gMP.chercheFuturesVoisines(parcelleCouleurNonPosee, plateau.getParcelles());
            Parcelle[] listVoisinePotentielle = gMP.addVoisinesParcelle(parcelleCouleurNonPosee, parcellesVoisine);
            assertEquals(listParcelleDisponible.length, listVoisinePotentielle.length);
            assertEquals(etang, listVoisinePotentielle[5]);
            assertEquals(listParcelleDisponible[1], listVoisinePotentielle[0]);
            assertEquals(listParcelleDisponible[3], listVoisinePotentielle[4]);

            ParcelleDisponible parcelleDisponible0_m2 = new ParcelleDisponible(new Position(0, -2));
            ParcelleDisponible parcelleDisponible2_m2 = new ParcelleDisponible(new Position(2, -2));
            ParcelleDisponible parcelleDisponible3_m1 = new ParcelleDisponible(new Position(3, -1));
            assertEquals(parcelleDisponible0_m2, listVoisinePotentielle[3]);
            assertEquals(parcelleDisponible2_m2, listVoisinePotentielle[2]);
            assertEquals(parcelleDisponible3_m1, listVoisinePotentielle[1]);
        }
        catch (ParcelleExistanteException | ParcelleNonVoisineException exception) {
            throw new AssertionError("Ne doit pas renvoyer d'exception");
        }
    }

    @Test
    void addVoisinesParcelleAvecException() {
        ParcelleCouleur parcelle4_0 = new ParcelleCouleur(new Position(4, 0));
        try {
            List<Parcelle>  listVoisinesParcelle4_0 = gMP.chercheFuturesVoisines(parcelle4_0, plateau.getParcelles());
            gMP.addVoisinesParcelle(parcelle4_0, listVoisinesParcelle4_0);
        }
        catch (ParcelleExistanteException pEE) {
            throw new AssertionError("Ne doit pas renvoyer d'exception");
        }
        catch (ParcelleNonVoisineException pNVE) {
            assertEquals(ParcelleNonVoisineException.class, pNVE.getClass());
        }
    }
}