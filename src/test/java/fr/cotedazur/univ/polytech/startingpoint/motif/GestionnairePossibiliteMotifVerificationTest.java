package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.GestionnaireObjectifs;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GestionnairePossibiliteMotifVerificationTest {
    Etang etang;
    ParcelleCouleur parcelleCouleur11R;
    ParcelleCouleur parcelleCouleur11J;
    ParcelleCouleur parcelleCouleurm1m1;
    ParcelleCouleur parcelleCouleurm3m1;
    ParcelleCouleur parcelleCouleur02;
    ParcelleCouleur parcelleCouleurm11;
    ParcelleCouleur parcelleCouleur00J;
    Motif motifD;
    Motif motifL;

    @BeforeEach
    void setUp() {
        etang = new Etang();
        parcelleCouleurm11 = new ParcelleCouleur(new Position(-1, 1), Couleur.JAUNE);
        parcelleCouleur02 = new ParcelleCouleur(new Position(0, 2), Couleur.ROSE);
        parcelleCouleur11R = new ParcelleCouleur(new Position(1, 1), Couleur.ROSE);
        parcelleCouleur00J = new ParcelleCouleur(new Position(0, 0), Couleur.JAUNE);
        parcelleCouleurm3m1 = new ParcelleCouleur(new Position(-3, -1), Couleur.ROSE);
        parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.JAUNE);
        parcelleCouleur11J = new ParcelleCouleur(new Position(1, 1), Couleur.JAUNE);
        motifL = new MotifLosange(parcelleCouleur00J, parcelleCouleur11R, parcelleCouleurm11, parcelleCouleur02);
        motifD = new MotifDiagonale(parcelleCouleurm1m1, parcelleCouleur00J, parcelleCouleur11J);
    }

    @Test
    void checkCouleurAndIrrigationDiagonale() {
        ParcelleCouleur[] tableauParcellesCouleurMotif = motifD.getTableauParcelles();
        ParcelleCouleur[] parcelleCouleurRessemblant = new ParcelleCouleur[3];
        parcelleCouleurRessemblant[0] = new ParcelleCouleur(new Position(-2, 0), Couleur.JAUNE);
        parcelleCouleurRessemblant[1] = new ParcelleCouleur(new Position(-1, 1), Couleur.JAUNE);
        parcelleCouleurRessemblant[2] = new ParcelleCouleur(new Position(0, 2), Couleur.JAUNE);

        assertFalse(GestionnairePossibiliteMotifVerification.checkCouleurAndIrrigation(parcelleCouleurRessemblant,
                tableauParcellesCouleurMotif));

        parcelleCouleurRessemblant[0].setIrriguee(true);
        parcelleCouleurRessemblant[1].setIrriguee(true);
        parcelleCouleurRessemblant[2].setIrriguee(true);

        assertTrue(GestionnairePossibiliteMotifVerification.checkCouleurAndIrrigation(parcelleCouleurRessemblant,
                tableauParcellesCouleurMotif));

        parcelleCouleurRessemblant[2] = new ParcelleCouleur(new Position(0, 2), Couleur.VERTE);

        assertFalse(GestionnairePossibiliteMotifVerification.checkCouleurAndIrrigation(parcelleCouleurRessemblant,
                tableauParcellesCouleurMotif));
    }

    @Test
    void checkCouleurAndIrrigationLosange() {
        ParcelleCouleur[] tableauParcellesCouleurMotif = motifL.getTableauParcelles();
        ParcelleCouleur[] parcelleCouleurRessemblant = new ParcelleCouleur[4];
        parcelleCouleurRessemblant[0] = new ParcelleCouleur(new Position(2, 0), Couleur.JAUNE);
        parcelleCouleurRessemblant[1] = new ParcelleCouleur(new Position(1, 1), Couleur.ROSE);
        parcelleCouleurRessemblant[2] = new ParcelleCouleur(new Position(3, 1), Couleur.JAUNE);
        parcelleCouleurRessemblant[3] = new ParcelleCouleur(new Position(2, 2), Couleur.ROSE);

        assertFalse(GestionnairePossibiliteMotifVerification.checkCouleurAndIrrigation(parcelleCouleurRessemblant,
                tableauParcellesCouleurMotif));

        parcelleCouleurRessemblant[0].setIrriguee(true);
        parcelleCouleurRessemblant[1].setIrriguee(true);
        parcelleCouleurRessemblant[2].setIrriguee(true);
        parcelleCouleurRessemblant[3].setIrriguee(true);

        assertTrue(GestionnairePossibiliteMotifVerification.checkCouleurAndIrrigation(parcelleCouleurRessemblant,
                tableauParcellesCouleurMotif));

        parcelleCouleurRessemblant[3] = new ParcelleCouleur(new Position(2, 2), Couleur.VERTE);

        assertFalse(GestionnairePossibiliteMotifVerification.checkCouleurAndIrrigation(parcelleCouleurRessemblant,
                tableauParcellesCouleurMotif));
    }

    @Test
    void getMotifAFaireLosange() {
        ParcelleCouleur[] tableauParcellesCouleurMotif = motifL.getTableauParcelles();
        List<Parcelle> listParcellePlateau = new ArrayList<>();
        listParcellePlateau.add(etang);
        ParcelleCouleur parcelleCouleur1m1J = new ParcelleCouleur(new Position(1, -1), Couleur.JAUNE);
        listParcellePlateau.add(parcelleCouleur1m1J);
        assertEquals(Optional.empty(), GestionnairePossibiliteMotifVerification.
                getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]), parcelleCouleur1m1J,
                        tableauParcellesCouleurMotif));

        ParcelleCouleur parcelleCouleur20R = new ParcelleCouleur(new Position(2, 0), Couleur.ROSE);
        listParcellePlateau.add(parcelleCouleur20R);
        assertEquals(Optional.empty(), GestionnairePossibiliteMotifVerification.
                getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]), parcelleCouleur1m1J,
                        tableauParcellesCouleurMotif));

        ParcelleCouleur parcelleCouleur3m1R = new ParcelleCouleur(new Position(3, -1), Couleur.ROSE);
        listParcellePlateau.add(parcelleCouleur3m1R);
        assertEquals(Optional.empty(), GestionnairePossibiliteMotifVerification.
                getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]), parcelleCouleur1m1J,
                        tableauParcellesCouleurMotif));

        ParcelleCouleur parcelleCouleur2m2J = new ParcelleCouleur(new Position(2, -2), Couleur.JAUNE);
        listParcellePlateau.add(parcelleCouleur2m2J);
        assertTrue(GestionnairePossibiliteMotifVerification.getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]),
                parcelleCouleur2m2J, tableauParcellesCouleurMotif).isPresent());

        listParcellePlateau.remove(4);
        listParcellePlateau.add(new ParcelleCouleur(new Position(2, -2), Couleur.ROSE));
        assertEquals(Optional.empty(), GestionnairePossibiliteMotifVerification.
                getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]), parcelleCouleur1m1J,
                        tableauParcellesCouleurMotif));
    }

    @Test
    void getMotifAFaireDiagonale() {
        ParcelleCouleur[] tableauParcellesCouleurMotif = motifD.getTableauParcelles();
        List<Parcelle> listParcellePlateau = new ArrayList<>();
        listParcellePlateau.add(etang);
        ParcelleCouleur parcelleCouleur1m1J = new ParcelleCouleur(new Position(1, -1), Couleur.ROSE);
        listParcellePlateau.add(parcelleCouleur1m1J);
        assertEquals(Optional.empty(), GestionnairePossibiliteMotifVerification.
                getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]), parcelleCouleur1m1J,
                        tableauParcellesCouleurMotif));

        ParcelleCouleur parcelleCouleur20R = new ParcelleCouleur(new Position(2, 0), Couleur.ROSE);
        listParcellePlateau.add(parcelleCouleur20R);
        assertEquals(Optional.empty(), GestionnairePossibiliteMotifVerification.
                getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]), parcelleCouleur1m1J,
                        tableauParcellesCouleurMotif));

        ParcelleCouleur parcelleCouleur11J = new ParcelleCouleur(new Position(1, 1), Couleur.JAUNE);
        listParcellePlateau.add(parcelleCouleur11J);
        assertEquals(Optional.empty(), GestionnairePossibiliteMotifVerification.
                getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]), parcelleCouleur1m1J,
                        tableauParcellesCouleurMotif));

        ParcelleCouleur parcelleCouleur31R = new ParcelleCouleur(new Position(3, 1), Couleur.ROSE);
        listParcellePlateau.add(parcelleCouleur31R);
        assertTrue(GestionnairePossibiliteMotifVerification.getMotifAFaire(listParcellePlateau.toArray(new Parcelle[0]),
                parcelleCouleur1m1J, tableauParcellesCouleurMotif).isPresent());
    }

    @Test
    void checkMotifInBoardDiagonale() {
        ObjectifParcelle objectifParcelleD = new ObjectifParcelle(3, motifD);
        List<Parcelle> parcellesBoard = new ArrayList<>();
        parcellesBoard.add(etang);

        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleD));

        ParcelleCouleur parcelleCouleurm20J = new ParcelleCouleur(new Position(-2, 0), Couleur.JAUNE);
        parcellesBoard.add(parcelleCouleurm20J);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleD));

        ParcelleCouleur parcelleCouleur1m1J = new ParcelleCouleur(new Position(1, -1), Couleur.JAUNE);
        parcellesBoard.add(parcelleCouleur1m1J);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleD));

        ParcelleCouleur parcelleCouleurm1m1J = new ParcelleCouleur(new Position(-1, -1), Couleur.JAUNE);
        parcellesBoard.add(parcelleCouleurm1m1J);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleD));

        ParcelleCouleur parcelleCouleur0m2J = new ParcelleCouleur(new Position(0, -2), Couleur.JAUNE);
        parcellesBoard.add(parcelleCouleur0m2J);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleD));

        parcelleCouleurm20J.setIrriguee(true);
        parcelleCouleur1m1J.setIrriguee(true);
        parcelleCouleurm1m1J.setIrriguee(true);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleD));

        parcelleCouleur0m2J.setIrriguee(true);
        assertTrue(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleD));

        parcellesBoard.remove(4);
        parcellesBoard.add(new ParcelleCouleur(new Position(0, -2), Couleur.VERTE));
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleD));
    }

    @Test
    void checkMotifInBoardLosange() {
        ObjectifParcelle objectifParcelleL = new ObjectifParcelle(5, motifL);
        List<Parcelle> parcellesBoard = new ArrayList<>();
        parcellesBoard.add(etang);

        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        ParcelleCouleur parcelleCouleurm20R = new ParcelleCouleur(new Position(-2, 0), Couleur.ROSE);
        parcellesBoard.add(parcelleCouleurm20R);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        ParcelleCouleur parcelleCouleurm11J = new ParcelleCouleur(new Position(-1, 1), Couleur.JAUNE);
        parcellesBoard.add(parcelleCouleurm11J);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        ParcelleCouleur parcelleCouleurm31J = new ParcelleCouleur(new Position(-3, 1), Couleur.ROSE);
        parcellesBoard.add(parcelleCouleurm31J);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        ParcelleCouleur parcelleCouleurm22R = new ParcelleCouleur(new Position(-2, 2), Couleur.JAUNE);
        parcellesBoard.add(parcelleCouleurm22R);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        parcelleCouleurm20R.setIrriguee(true);
        parcelleCouleurm11J.setIrriguee(true);
        parcelleCouleurm31J.setIrriguee(true);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        parcelleCouleurm22R.setIrriguee(true);
        assertTrue(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        parcellesBoard.remove(4);
        parcellesBoard.add(new ParcelleCouleur(new Position(-2, 2), Couleur.VERTE));
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));
    }

    @Test
    void checkMotifInBoardLosangeDeux() {
        ObjectifParcelle objectifParcelleL = new ObjectifParcelle(5, motifL);
        List<Parcelle> parcellesBoard = new ArrayList<>();
        parcellesBoard.add(etang);

        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        ParcelleCouleur parcelleCouleur1m1J = new ParcelleCouleur(new Position(1, -1), Couleur.JAUNE);
        parcellesBoard.add(parcelleCouleur1m1J);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        ParcelleCouleur parcelleCouleur20J = new ParcelleCouleur(new Position(2, 0), Couleur.JAUNE);
        parcellesBoard.add(parcelleCouleur20J);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        ParcelleCouleur parcelleCouleur3m1R = new ParcelleCouleur(new Position(3, -1), Couleur.ROSE);
        parcellesBoard.add(parcelleCouleur3m1R);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        ParcelleCouleur parcelleCouleur40R = new ParcelleCouleur(new Position(4, 0), Couleur.ROSE);
        parcellesBoard.add(parcelleCouleur40R);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        parcelleCouleur1m1J.setIrriguee(true);
        parcelleCouleur20J.setIrriguee(true);
        parcelleCouleur3m1R.setIrriguee(true);
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        parcelleCouleur40R.setIrriguee(true);
        assertTrue(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));

        parcellesBoard.remove(4);
        parcellesBoard.add(new ParcelleCouleur(new Position(-2, 2), Couleur.VERTE));
        assertFalse(GestionnairePossibiliteMotifVerification.checkMotifInBoard(
                parcellesBoard.toArray(new Parcelle[0]), objectifParcelleL));
    }
}