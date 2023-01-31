package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.Arbitre;
import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionnairePossibilitePlateau;
import fr.cotedazur.univ.polytech.startingpoint.plateau.Plateau;
import fr.cotedazur.univ.polytech.startingpoint.plateau.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JoueurTest {
    Plateau plateau;
    Random random;
    PiocheObjectifParcelle piocheObjectifParcelle;
    PiocheObjectifPanda piocheObjectifPanda;
    PiocheObjectifJardinier piocheObjectifJardinier;
    PiocheObjectif piocheObjectif;
    PiocheParcelle piocheParcelle;
    ObjectifParcelle objParJ1;
    ObjectifPanda objPanJ1;
    ObjectifJardinier objJarJ1;
    ObjectifParcelle objParJ2;
    ObjectifPanda objPanJ2;
    ObjectifJardinier objJarJ2;
    PiocheBambou piocheBambou;
    GestionnairePossibilitePlateau gPP;

    Joueur joueur1;
    Joueur joueur2;

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        random = new Random();
        piocheObjectifParcelle = new PiocheObjectifParcelle(random);
        piocheObjectifPanda = new PiocheObjectifPanda(random);
        piocheObjectifJardinier = new PiocheObjectifJardinier(random);
        piocheObjectif = new PiocheObjectif(piocheObjectifParcelle, piocheObjectifPanda, piocheObjectifJardinier);
        piocheParcelle = new PiocheParcelle(random);
        GestionnairePossibilitePlateau gPP = new GestionnairePossibilitePlateau(plateau);
        objParJ1 = piocheObjectif.piocheObjectifParcelle();
        objPanJ1 = piocheObjectif.piocheObjectifPanda();
        objJarJ1 = piocheObjectif.piocheObjectifJardinier();
        objParJ2 = piocheObjectif.piocheObjectifParcelle();
        objPanJ2 = piocheObjectif.piocheObjectifPanda();
        objJarJ2 = piocheObjectif.piocheObjectifJardinier();
        piocheBambou = new PiocheBambou(new Random());
        joueur1 = new Joueur("Robot1", random, objParJ1, objPanJ1, objJarJ1);
        joueur2 = new Joueur("Robot2", random, objParJ2, objPanJ2, objJarJ2);
    }

    @Test
    void getNom() {
        assertEquals("Robot1", joueur1.getNom());
        assertEquals("Robot2", joueur2.getNom());
    }

    @Test
    void getPlaquette() {
        Plaquette plaquetteJoueur1 = joueur1.getPlaquette();
        assertEquals(objJarJ1, plaquetteJoueur1.getObjectifs()[0]);

        Plaquette plaquetteJoueur2 = joueur2.getPlaquette();
        assertEquals(objJarJ2, plaquetteJoueur2.getObjectifs()[0]);
    }


    @Test
    void tourDeJeu() {
        Arbitre arbitre = new Arbitre();
        while (arbitre.verifieFinDeJeu(joueur1, joueur2)){
            joueur1.tour(piocheObjectif, piocheBambou, piocheParcelle, plateau, arbitre, gPP);
            joueur2.tour(piocheObjectif, piocheBambou, piocheParcelle, plateau, arbitre,gPP);
        }
        if (joueur1.getPoints() > joueur2.getPoints()) {
            assertEquals(Optional.of(joueur1), arbitre.joueurGagnant(joueur1, joueur2));
        }
        else if (joueur2.getPoints() < joueur2.getPoints()) {
            assertEquals(Optional.of(joueur2), arbitre.joueurGagnant(joueur1, joueur2));
        }
        else {
            assertEquals(Optional.empty(), arbitre.joueurGagnant(joueur1, joueur2));
        }
    }

}