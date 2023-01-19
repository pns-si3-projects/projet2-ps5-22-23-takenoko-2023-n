package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.objectif.NombreObjectifsEnCoursException;
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
    ObjectifParcelle objParJ1;
    ObjectifPanda objPanJ1;
    ObjectifJardinier objJarJ1;
    ObjectifParcelle objParJ2;
    ObjectifPanda objPanJ2;
    ObjectifJardinier objJarJ2;
    PiocheBambou piocheBambou;
    GestionnairePossibilitePlateau gPP;
    Arbitre arbitre;

    Joueur joueur1;
    Joueur joueur2;

    @Mock
    Random mockRandom = mock(Random.class);

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        random = new Random();
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        piocheObjectifPanda = new PiocheObjectifPanda(random);
        piocheObjectifJardinier = new PiocheObjectifJardinier(random);
        piocheObjectif = new PiocheObjectif(piocheObjectifParcelle, piocheObjectifPanda, piocheObjectifJardinier);
        gPP = new GestionnairePossibilitePlateau(plateau);
        objParJ1 = piocheObjectif.piocheObjectifParcelle();
        objPanJ1 = piocheObjectif.piocheObjectifPanda();
        objJarJ1 = piocheObjectif.piocheObjectifJardinier();
        objParJ2 = piocheObjectif.piocheObjectifParcelle();
        objPanJ2 = piocheObjectif.piocheObjectifPanda();
        objJarJ2 = piocheObjectif.piocheObjectifJardinier();
        piocheBambou = new PiocheBambou(new Random());
        joueur1 = new Joueur("Robot1", mockRandom, objParJ1, objPanJ1, objJarJ1);
        joueur2 = new Joueur("Robot2", mockRandom, objParJ2, objPanJ2, objJarJ2);
        arbitre  = new Arbitre();
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
    void choisiParcellePlateauSansException() {
        when(mockRandom.nextInt(anyInt())).thenReturn(2, 2, 4);
        ParcelleCouleur[] listParcellesCouleursChoisis = new ParcelleCouleur[3];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1));
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1));
        ParcelleCouleur parcelle0m2 = new ParcelleCouleur(new Position(0, -2));
        listParcellesCouleursChoisis[0] = parcelleCouleur1m1;
        listParcellesCouleursChoisis[1] = parcelleCouleurm1m1;
        listParcellesCouleursChoisis[2] = parcelle0m2;

        for (ParcelleCouleur listParcellesCouleursChoisi : listParcellesCouleursChoisis) {
            ParcelleCouleur parcelleCouleurAAdd = joueur1.choisiParcellePlateau(plateau.getPositionsDisponible());
            SectionBambou secBamAAdd = new SectionBambou();
            try {
                assertEquals(listParcellesCouleursChoisi, parcelleCouleurAAdd);
                plateau.addParcelle(parcelleCouleurAAdd, secBamAAdd);
            } catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
                throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
            }
        }
    }

    @Test
    void choisiParcellePlateauAvecRandomTropGrand() {
        Position[] listPositionDisponible = plateau.getPositionsDisponible();
        when(mockRandom.nextInt(anyInt())).thenReturn(listPositionDisponible.length);
        assertThrows(ArithmeticException.class, () -> joueur2.choisiParcellePlateau(listPositionDisponible));
    }

    @Test
    void choisiParcellePlateauAvecRandomTropPetit() {
        Position[] listPositionDisponible = plateau.getPositionsDisponible();
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        assertThrows(ArithmeticException.class, () -> joueur2.choisiParcellePlateau(listPositionDisponible));
    }

    @Test
    void addParcellePlateauTrue() {
        ParcelleCouleur parcelleCouleurChoisiJoueur1 = joueur1.choisiParcellePlateau(plateau.getPositionsDisponible());
        SectionBambou secBam = new SectionBambou();
        assertTrue(joueur1.addParcellePlateau(plateau, parcelleCouleurChoisiJoueur1, secBam));

        ParcelleCouleur parcelleCouleurChoisiJoueur2 = joueur2.choisiParcellePlateau(plateau.getPositionsDisponible());
        assertTrue(joueur2.addParcellePlateau(plateau, parcelleCouleurChoisiJoueur2, secBam));
    }

    @Test
    void addParcellePlateauFalse() {
        ParcelleCouleur parcelleCouleurNonPossibleAAdd = new ParcelleCouleur(new Position(10, 10));
        SectionBambou secBam = new SectionBambou();
        assertFalse(joueur1.addParcellePlateau(plateau, parcelleCouleurNonPossibleAAdd, secBam));
        assertFalse(joueur2.addParcellePlateau(plateau, parcelleCouleurNonPossibleAAdd, secBam));
    }

    @Test
    void piocheObjectifParcelleRempli() {
        when(mockRandom.nextInt(anyInt())).thenReturn(10, 2);
        ObjectifParcelle objectifParcelleAPiocher = piocheObjectifParcelle.get(10);
        assertEquals(Optional.of(objectifParcelleAPiocher), joueur1.piocheObjectifParcelle(piocheObjectif));

        ObjectifParcelle objectifParcelleAPiocher2 = piocheObjectifParcelle.get(2);
        assertEquals(Optional.of(objectifParcelleAPiocher2), joueur2.piocheObjectifParcelle(piocheObjectif));
    }

    @Test
    void piocheObjectifParcelleVide(){
        for (int i=0; i<13; i++) {
            piocheObjectif.piocheObjectifParcelle();
        }
        assertEquals(Optional.empty(), joueur1.piocheObjectifParcelle(piocheObjectif));
        assertEquals(Optional.empty(), joueur2.piocheObjectifParcelle(piocheObjectif));
    }

    @Test
    void tourDeJeu() {
        Arbitre arbitre = new Arbitre();
        while (arbitre.verifieFinDeJeu(joueur1, joueur2)){
            joueur1.tour(piocheObjectif, piocheBambou, plateau, arbitre,gPP);
            joueur2.tour(piocheObjectif, piocheBambou, plateau, arbitre,gPP);
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

    @Test
    void gestionObjectifJardinier() {
        ObjectifJardinier objectifJardinier = new ObjectifJardinier(2, 1, "VERT",1);
        try {
            joueur1.getPlaquette().ajouteObjectif(objectifJardinier);
        } catch (NombreObjectifsEnCoursException e) {
            throw new RuntimeException(e);
        }

        joueur1.actionParcelle(piocheBambou,plateau,arbitre);
        joueur2.actionParcelle(piocheBambou,plateau,arbitre);
        joueur1.actionParcelle(piocheBambou,plateau,arbitre);
        joueur2.actionParcelle(piocheBambou,plateau,arbitre);

        joueur1.actionJardinier(plateau,arbitre,gPP);
    }
}