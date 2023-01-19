package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionnairePossibilitePlateau;
import fr.cotedazur.univ.polytech.startingpoint.plateau.NombreParcelleVoisineException;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import fr.cotedazur.univ.polytech.startingpoint.plateau.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArbitreTest {
    Joueur joueur1;
    Joueur joueur2;
    Arbitre arbitre;
    PiocheObjectif piocheObjectif;
    PiocheBambou piocheBambou;
    Plateau plateau;
    GestionnairePossibilitePlateau gPP;

    @BeforeEach
    void setUp() {
        arbitre = new Arbitre();
        piocheObjectif = new PiocheObjectif(new PiocheObjectifParcelle(new Random()), new PiocheObjectifPanda(new Random()), new PiocheObjectifJardinier(new Random()));
        piocheBambou = new PiocheBambou(new Random());
        plateau = new Plateau();
        gPP = new GestionnairePossibilitePlateau(plateau);
        ObjectifParcelle objParJ1 = piocheObjectif.piocheObjectifParcelle();
        ObjectifPanda objPanJ1 = piocheObjectif.piocheObjectifPanda();
        ObjectifJardinier objJarJ1 = piocheObjectif.piocheObjectifJardinier();
        ObjectifParcelle objParJ2 = piocheObjectif.piocheObjectifParcelle();
        ObjectifPanda objPanJ2 = piocheObjectif.piocheObjectifPanda();
        ObjectifJardinier objJarJ2 = piocheObjectif.piocheObjectifJardinier();
        joueur1 = new Joueur("Robot1", new Random(), objParJ1, objPanJ1, objJarJ1);
        joueur2 = new Joueur("Robot2", new Random(), objParJ2, objPanJ2, objJarJ2);
    }

    @Test
    void checkFinDeJeu() {
        while (!arbitre.verifieFinDeJeu(joueur1, joueur2)) {
            assertFalse(arbitre.verifieFinDeJeu(joueur1, joueur2));
            joueur1.tour(piocheObjectif, piocheBambou, plateau, arbitre,gPP);
            joueur2.tour(piocheObjectif, piocheBambou, plateau, arbitre,gPP);
        }
        assertTrue(arbitre.verifieFinDeJeu(joueur1, joueur2));
    }

    @Test
    void joueurGagnant() {
        assertTrue(arbitre.joueurGagnant(joueur1, joueur2).isEmpty());
        while (!arbitre.verifieFinDeJeu(joueur1, joueur2)) {
            joueur1.tour(piocheObjectif, piocheBambou, plateau, arbitre,gPP);
            joueur2.tour(piocheObjectif, piocheBambou, plateau, arbitre,gPP);
        }
        if (joueur1.getPoints() > joueur2.getPoints()) {
            assertEquals(Optional.of(joueur1), arbitre.joueurGagnant(joueur1, joueur2));
        }
        else if (joueur1.getPoints() < joueur2.getPoints()) {
            assertEquals(Optional.of(joueur2), arbitre.joueurGagnant(joueur1, joueur2));
        }
        else {
            assertTrue(arbitre.joueurGagnant(joueur1, joueur2).isEmpty());
        }
    }

    @Test
    void checkObjectifParcelleTermine() {
        ObjectifParcelle objectifParcelleACheck = new ObjectifParcelle(5, 4);
        objectifParcelleACheck.setNombreParcellePresenteEnJeu(1);
        int i = 0;
        while (i < 4) {
            assertFalse(arbitre.checkObjectifParcelleTermine(plateau.getParcelles(), objectifParcelleACheck));
            try {
                ParcelleCouleur parcelleCouleurAAdd = new ParcelleCouleur(plateau.getPositionsDisponible()[0]);
                SectionBambou secBam = new SectionBambou();
                plateau.addParcelle(parcelleCouleurAAdd, secBam);
            }
            catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
                throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
            }
            i++;
        }
        assertTrue(arbitre.checkObjectifParcelleTermine(plateau.getParcelles(), objectifParcelleACheck));
    }

    @Test
    void getNombreTour() {
        assertEquals(arbitre.getNombreTour(), 1);
        arbitre.addTour();
        arbitre.addTour();
        assertEquals(arbitre.getNombreTour(), 3);
    }

    @Test
    void checkOjectifJardinierTermine(){
        ObjectifJardinier objectifJardinier = new ObjectifJardinier(2, 1, "VERT",1);
        joueur1.actionParcelle(piocheBambou,plateau,arbitre);
        joueur2.actionParcelle(piocheBambou,plateau,arbitre);
        joueur1.actionParcelle(piocheBambou,plateau,arbitre);
        joueur2.actionParcelle(piocheBambou,plateau,arbitre);

        joueur1.actionJardinier(plateau,arbitre,gPP);
        assertTrue(arbitre.checkObjectifJardinierTermine(objectifJardinier,plateau));
    }
}