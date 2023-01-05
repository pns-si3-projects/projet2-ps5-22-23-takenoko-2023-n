package fr.cotedazur.univ.polytech.startingpoint;

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
    Plateau plateau;

    @BeforeEach
    void setUp(){
        arbitre = new Arbitre();
        piocheObjectif = new PiocheObjectif(new PiocheObjectifParcelle(new Random()),new PiocheObjectifPanda(new Random()),new PiocheObjectifJardinier(new Random()));
        plateau = new Plateau();
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
        while (!arbitre.checkFinDeJeu(joueur1,joueur2)){
            assertFalse(arbitre.checkFinDeJeu(joueur1,joueur2));
            joueur1.tour(piocheObjectif,plateau,arbitre);
            joueur2.tour(piocheObjectif,plateau,arbitre);
        }
        assertTrue(arbitre.checkFinDeJeu(joueur1,joueur2));
    }

    @Test
    void joueurGagnant() {
        assertTrue(arbitre.joueurGagnant(joueur1,joueur2).isEmpty());
        while (!arbitre.checkFinDeJeu(joueur1,joueur2)){
            joueur1.tour(piocheObjectif,plateau,arbitre);
            joueur2.tour(piocheObjectif,plateau,arbitre);
        }
        if(joueur1.getPoints() > joueur2.getPoints()){
            assertEquals(Optional.of(joueur1),arbitre.joueurGagnant(joueur1,joueur2));
        }
        else if(joueur1.getPoints() < joueur2.getPoints()){
            assertEquals(Optional.of(joueur2),arbitre.joueurGagnant(joueur1,joueur2));
        }
        else{
            assertTrue(arbitre.joueurGagnant().isEmpty());
        }
    }

    @Test
    void checkObjectifParcelleTermine() {
        ObjectifParcelle objectifParcelleACheck = new ObjectifParcelle(5,4);
        objectifParcelleACheck.setNombreParcellePresenteEnJeu(1);
        int i = 0;
        while(i < 4){
            assertFalse(arbitre.checkObjectifParcelleTermine(plateau.getParcelles(), objectifParcelleACheck));
            try {
                ParcelleCouleur parcelleCouleurAAdd = new ParcelleCouleur(plateau.getPositionsDisponible()[0]);
                plateau.addParcelle(parcelleCouleurAAdd);
            }
            catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
                assert false: "Ne doit pas renvoyer d'erreur normalement";
            }
            i++;
        }
        assertTrue(arbitre.checkObjectifParcelleTermine(plateau.getParcelles(), objectifParcelleACheck));
    }

    @Test
    void getNombreTour() {
        assertEquals(arbitre.getNombreTour(),1);
        arbitre.addTour();
        arbitre.addTour();
        assertEquals(arbitre.getNombreTour(),3);
    }
}