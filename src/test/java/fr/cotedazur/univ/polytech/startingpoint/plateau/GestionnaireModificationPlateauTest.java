package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireModificationPlateauTest {
    Plateau plateau;
    GestionnaireModificationPlateau gMP;
    Etang etang;
    Position positionEtang;
    ParcelleDisponible parcelleDisponible0;
    ParcelleDisponible parcelleDisponible1;
    ParcelleDisponible parcelleDisponible2;
    ParcelleDisponible parcelleDisponible3;
    ParcelleDisponible parcelleDisponible4;
    ParcelleDisponible parcelleDisponible5;
    ParcelleDisponible[] listParcelleDisponible;
    Position[] listPositionDisponible;
    ParcelleCouleur parcelleCouleurNonPose2;


    @BeforeEach
    void setUp(){
        plateau = new Plateau();
        gMP = new GestionnaireModificationPlateau();
        etang = plateau.getEtang();
        positionEtang = etang.position();
        parcelleDisponible0 = new ParcelleDisponible(new Position(positionEtang.getX()+1,positionEtang.getY()+1));
        parcelleDisponible1 = new ParcelleDisponible(new Position(positionEtang.getX()+2,positionEtang.getY()));
        parcelleDisponible2 = new ParcelleDisponible(new Position(positionEtang.getX()+1,positionEtang.getY()-1));
        parcelleDisponible3 = new ParcelleDisponible(new Position(positionEtang.getX()-1,positionEtang.getY()-1));
        parcelleDisponible4 = new ParcelleDisponible(new Position(positionEtang.getX()-2,positionEtang.getY()));
        parcelleDisponible5 = new ParcelleDisponible(new Position(positionEtang.getX()-1,positionEtang.getY()+1));
        listParcelleDisponible = new ParcelleDisponible[6];
        listParcelleDisponible[0] = parcelleDisponible0;
        listParcelleDisponible[1] = parcelleDisponible1;
        listParcelleDisponible[2] = parcelleDisponible2;
        listParcelleDisponible[3] = parcelleDisponible3;
        listParcelleDisponible[4] = parcelleDisponible4;
        listParcelleDisponible[5] = parcelleDisponible5;
        listPositionDisponible = plateau.getPositionsDisponible();
        parcelleCouleurNonPose2 = new ParcelleCouleur(listParcelleDisponible[2].position(), Couleur.ROSE);
    }

    @Test
    void addParcelleVide() {
        for(int i = 0;i<6;i++){
            assertEquals(listParcelleDisponible[i],gMP.creeParcelleDisponible(i,positionEtang));
        }

        assertThrows(IllegalArgumentException.class, () -> gMP.creeParcelleDisponible(6,positionEtang));
        /*try {
            gMP.creeParcelleDisponible(6,positionEtang);
            assert false: "Doit renvoyer une erreur";
        }
        catch (IllegalArgumentException iAE){

        }*/

        assertThrows(IllegalArgumentException.class, () -> gMP.creeParcelleDisponible(-1,positionEtang));
        /*try {
            gMP.creeParcelleDisponible(-1,positionEtang);
            assert false: "Doit renvoyer une erreur";
        }
        catch (IllegalArgumentException iAE){

        }*/
    }

    /**
     * Renvoi les possibles voisins d'une parcelle qui peut etre poser
     */
    @Test
    void getParcelleVoisinSansExceptionUn() {
        try {
            List<Parcelle> listVoisinParcelle = gMP.chercheFuturesVoisines(parcelleCouleurNonPose2, plateau.getParcelles());
            assertEquals(1, listVoisinParcelle.size());
            assertEquals(etang,listVoisinParcelle.get(0));
        }
        catch (ParcelleExistanteException exception) {
            throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
        }
    }

    @Test
    void getParcelleVoisinSansExceptionDeux() {
        ParcelleCouleur parcelleCouleurNonPose3 = new ParcelleCouleur(listParcelleDisponible[3].position(), Couleur.JAUNE);
        SectionBambou secBam = new SectionBambou();

        try {
            plateau.addParcelle(parcelleCouleurNonPose2, secBam);
            plateau.getParcelles();
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
            throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
        }

        try {
            List<Parcelle> listVoisinParcelle = gMP.chercheFuturesVoisines(parcelleCouleurNonPose3, plateau.getParcelles());
            assertEquals(2, listVoisinParcelle.size());
            assertEquals(etang,listVoisinParcelle.get(0));
            assertEquals(parcelleCouleurNonPose2,listVoisinParcelle.get(1));
        }
        catch (ParcelleExistanteException exception) {
            throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
        }
    }

    @Test
    void getParcelleVoisinAvecExceptionUn() {
        assertThrows(ParcelleExistanteException.class, () -> gMP.chercheFuturesVoisines(etang, plateau.getParcelles()));
        /*try {
            gMP.chercheFuturesVoisines(etang, plateau.getParcelles());
        }
        catch (ParcelleExistanteException pEE){
            assertEquals("La parcelle de position "+etang.getPosition()+" est déjà existante",pEE.getMessage());
        }*/
    }

    @Test
    void getParcelleVoisinAvecExceptionDeux() {
        ParcelleCouleur parcelleCouleurNonPose2bis = new ParcelleCouleur(listPositionDisponible[2], Couleur.ROSE);
        SectionBambou secBam = new SectionBambou();

        try {
            plateau.addParcelle(parcelleCouleurNonPose2, secBam);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
            throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
        }

        assertThrows(ParcelleExistanteException.class, () -> gMP.chercheFuturesVoisines(parcelleCouleurNonPose2bis, plateau.getParcelles()));
        /*try {
            gMP.chercheFuturesVoisines(parcelleCouleurNonPose2bis, plateau.getParcelles());
        }
        catch (ParcelleExistanteException pEE){
            assertEquals("La parcelle de position "+parcelleCouleurNonPose2bis.getPosition()+" est déjà existante",pEE.getMessage());
        }*/
    }

    @Test
    void addVoisinParcelleSansException() {
        try {
            List<Parcelle> parcellesVoisin = gMP.chercheFuturesVoisines(parcelleCouleurNonPose2, plateau.getParcelles());
            Parcelle[] listVoisinPotentiel = gMP.addVoisinesParcelle(parcelleCouleurNonPose2,parcellesVoisin);
            assertEquals(listVoisinPotentiel.length, listParcelleDisponible.length);
            assertEquals(listVoisinPotentiel[5],etang);
            assertEquals(listVoisinPotentiel[0],listParcelleDisponible[1]);
            assertEquals(listVoisinPotentiel[4],listParcelleDisponible[3]);

            ParcelleDisponible parcelleDisponible0m2 = new ParcelleDisponible(new Position(0,-2));
            ParcelleDisponible parcelleDisponible2m2 = new ParcelleDisponible(new Position(2,-2));
            ParcelleDisponible parcelleDisponible3m1 = new ParcelleDisponible(new Position(3,-1));
            assertEquals(parcelleDisponible0m2,listVoisinPotentiel[3]);
            assertEquals(parcelleDisponible2m2,listVoisinPotentiel[2]);
            assertEquals(parcelleDisponible3m1,listVoisinPotentiel[1]);
        }
        catch (ParcelleExistanteException | ParcelleNonVoisineException exception) {
            throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
        }
    }

    @Test
    void addVoisinParcelleException() {
        ParcelleCouleur parcelleAAdd = new ParcelleCouleur(new Position(4,0), Couleur.JAUNE);
        try {
            List<Parcelle>  listParcelleVoisin40 = gMP.chercheFuturesVoisines(parcelleAAdd, plateau.getParcelles());
            gMP.addVoisinesParcelle(parcelleAAdd,listParcelleVoisin40);
        }
        catch (ParcelleExistanteException pEE) {
            throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
        }
        catch (ParcelleNonVoisineException pNVE) {
            assertEquals(ParcelleNonVoisineException.class, pNVE.getClass());
        }
    }
}