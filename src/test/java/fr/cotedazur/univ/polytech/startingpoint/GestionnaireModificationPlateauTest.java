package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Set;

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
        gMP = new GestionnaireModificationPlateau(plateau);
        etang = plateau.getEtang();
        positionEtang = etang.getPosition();
        // en fait ici, je comprends pas pq tu veux refaire ça before each
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
        parcelleCouleurNonPose2 = new ParcelleCouleur(listParcelleDisponible[2].getPosition());
    }

    @Test
    void addParcelleVide() {
        for(int i = 0;i<6;i++){
            assertEquals(listParcelleDisponible[i],gMP.addParcelleVide(i,positionEtang));
        }

        try {
            gMP.addParcelleVide(6,positionEtang);
            assert false: "Doit renvoyer une erreur";
        }
        catch (IllegalArgumentException iAE){

        }

        try {
            gMP.addParcelleVide(-1,positionEtang);
            assert false: "Doit renvoyer une erreur";
        }
        catch (IllegalArgumentException iAE){

        }
    }

    /**
     * Renvoi les possibles voisins d'une parcelle qui peut etre poser
     */
    @Test
    void getParcelleVoisinSansExceptionUn() {
        try {
            List<Parcelle> listVoisinParcelle = gMP.getParcelleVoisin(parcelleCouleurNonPose2);
            assertEquals(1, listVoisinParcelle.size());
            assertEquals(etang,listVoisinParcelle.get(0));
        }
        catch (ParcelleExistanteException exception){
            assert false: "Ne doit normalement pas renvoyer d'erreur";
        }
    }

    @Test
    void getParcelleVoisinSansExceptionDeux(){
        ParcelleCouleur parcelleCouleurNonPose3 = new ParcelleCouleur(listParcelleDisponible[3].getPosition());

        try {
            plateau.addParcelle(parcelleCouleurNonPose2);
            Set<Parcelle> listParcelle = plateau.getListParcelle();
        }
        catch (ParcelleExistanteException | NombreParcelleVoisinException exception){
            assert false: "Ne doit pas renvoyer d'exception";
        }

        try {
            List<Parcelle> listVoisinParcelle = gMP.getParcelleVoisin(parcelleCouleurNonPose3);
            assertEquals(2, listVoisinParcelle.size());
            assertEquals(etang,listVoisinParcelle.get(0));
            assertEquals(parcelleCouleurNonPose2,listVoisinParcelle.get(1));
        }
        catch (ParcelleExistanteException exception){
            assert false: "Ne doit normalement pas renvoyer d'erreur";
        }
    }

    @Test
    void getParcelleVoisinAvecExceptionUn(){
        try {
            gMP.getParcelleVoisin(etang);
        }
        catch (ParcelleExistanteException pEE){
            assertEquals("La parcelle de position "+etang.getPosition()+" est deja existante",pEE.getMessage());
        }
    }

    @Test
    void getParcelleVoisinAvecExceptionDeux(){
        ParcelleCouleur parcelleCouleurNonPose2bis = new ParcelleCouleur(listPositionDisponible[2]);

        try {
            plateau.addParcelle(parcelleCouleurNonPose2);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisinException exception){
            assert false: "Ne doit pas renvoyer d'exception";
        }

        try {
            gMP.getParcelleVoisin(parcelleCouleurNonPose2bis);
        }
        catch (ParcelleExistanteException pEE){
            assertEquals("La parcelle de position "+parcelleCouleurNonPose2bis.getPosition()+" est deja existante",pEE.getMessage());
        }
    }

    @Test
    void addVoisinParcelleSansException() {
        try {
            List<Parcelle> parcellesVoisin = gMP.getParcelleVoisin(parcelleCouleurNonPose2);
            Parcelle[] listVoisinPotentiel = gMP.addVoisinParcelle(parcelleCouleurNonPose2,parcellesVoisin);
            assertTrue(listVoisinPotentiel.length == listParcelleDisponible.length);
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
        catch (ParcelleExistanteException | ParcelleNonVoisineException exception){
            assert false: "Ne doit pas renvoyer d'exception";
        }
    }

    @Test
    void addVoisinParcelleException(){
        ParcelleCouleur parcelleAAdd = new ParcelleCouleur(new Position(4,0));
        try {
            List<Parcelle>  listParcelleVoisin40 = gMP.getParcelleVoisin(parcelleAAdd);
            gMP.addVoisinParcelle(parcelleAAdd,listParcelleVoisin40);
        }
        catch (ParcelleExistanteException pEE){
            assert false: "Ne doit pas renvoyer d'exception";
        }
        catch (ParcelleNonVoisineException pNVE){
            assertEquals("La parcelle de position "+ parcelleAAdd.getPosition()+" n'existe pas dans la map",pNVE.getMessage());
        }

    }
}