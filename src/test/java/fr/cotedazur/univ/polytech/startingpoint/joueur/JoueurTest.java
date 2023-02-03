package fr.cotedazur.univ.polytech.startingpoint.joueur;

import fr.cotedazur.univ.polytech.startingpoint.Arbitre;
import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
import fr.cotedazur.univ.polytech.startingpoint.pioche.*;
import fr.cotedazur.univ.polytech.startingpoint.plateau.*;
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
    PiocheIrrigation piocheIrrigation;
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
        piocheParcelle = new PiocheParcelle(random);
        GestionnairePossibilitePlateau gPP = new GestionnairePossibilitePlateau(plateau);
        objParJ1 = piocheObjectif.piocheObjectifParcelle();
        objPanJ1 = piocheObjectif.piocheObjectifPanda();
        objJarJ1 = piocheObjectif.piocheObjectifJardinier();
        objParJ2 = piocheObjectif.piocheObjectifParcelle();
        objPanJ2 = piocheObjectif.piocheObjectifPanda();
        objJarJ2 = piocheObjectif.piocheObjectifJardinier();
        piocheBambou = new PiocheBambou(new Random());
        joueur1 = new Joueur("Robot1", mockRandom, objParJ1, objPanJ1, objJarJ1);
        joueur2 = new Joueur("Robot2", mockRandom, objParJ2, objPanJ2, objJarJ2);
        arbitre = new Arbitre();
        piocheIrrigation = new PiocheIrrigation(new Random());
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

    /*@Test
    void choisiParcellePlateauSansException() {
        when(mockRandom.nextInt(anyInt())).thenReturn(2, 2, 4);
        ParcelleCouleur[] listParcellesCouleursChoisis = new ParcelleCouleur[3];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1, -1), Couleur.VERT);
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1, -1), Couleur.VERT);
        ParcelleCouleur parcelle0m2 = new ParcelleCouleur(new Position(0, -2), Couleur.VERT);
        listParcellesCouleursChoisis[0] = parcelleCouleur1m1;
        listParcellesCouleursChoisis[1] = parcelleCouleurm1m1;
        listParcellesCouleursChoisis[2] = parcelle0m2;

        for (ParcelleCouleur listParcellesCouleursChoisi : listParcellesCouleursChoisis) {
            Position parcelleCouleurAAdd = joueur1.choisiPositionParcellePlateau(plateau.getPositionsDisponible());

            SectionBambou secBamAAdd = new SectionBambou(Couleur.VERT);
            try {
                assertEquals(listParcellesCouleursChoisi, parcelleCouleurAAdd);
                plateau.addParcelle(parcelleCouleurAAdd, secBamAAdd);
            } catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
                throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
            }
        }
    }*/

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

   /* @Test
    void addParcellePlateauTrue() {
        ParcelleCouleur parcelleCouleurChoisiJoueur1 = joueur1.choisiParcellePlateau(plateau.getPositionsDisponible());
        SectionBambou secBam = new SectionBambou(Couleur.VERT);
        assertTrue(joueur1.addParcellePlateau(plateau, parcelleCouleurChoisiJoueur1, secBam));

        ParcelleCouleur parcelleCouleurChoisiJoueur2 = joueur2.choisiParcellePlateau(plateau.getPositionsDisponible());
        assertTrue(joueur2.addParcellePlateau(plateau, parcelleCouleurChoisiJoueur2, secBam));
    }*/

    @Test
    void addParcellePlateauFalse() {
        ParcelleCouleur parcelleCouleurNonPossibleAAdd = new ParcelleCouleur(new Position(10, 10), Couleur.ROSE);
        SectionBambou secBam = new SectionBambou(Couleur.VERT);
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
            joueur1.tour(piocheObjectif, piocheBambou, piocheParcelle,piocheIrrigation,  plateau, arbitre, gPP);
            joueur2.tour(piocheObjectif, piocheBambou, piocheParcelle,piocheIrrigation,  plateau, arbitre,gPP);
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
    void actionIrrigation(){
        ParcelleCouleur pc20 = new ParcelleCouleur(new Position(2,0), Couleur.JAUNE);
        ParcelleCouleur pc11 = new ParcelleCouleur(new Position(1,1), Couleur.JAUNE);
        ParcelleCouleur pc31 = new ParcelleCouleur(new Position(3,1), Couleur.VERT);
        try {
            //Parcelle 2 0
            SectionBambou secBam2_0 = new SectionBambou(Couleur.JAUNE);
            plateau.addParcelle(pc20, secBam2_0);
            Bambou bambou2_0 = new Bambou(pc20);
            bambou2_0.ajouteSectionBambou(secBam2_0);
            //Parcelle 1 1
            SectionBambou secBam1_1 = new SectionBambou(Couleur.JAUNE);
            plateau.addParcelle(pc11, secBam1_1);
            Bambou bambou1_1 = new Bambou(pc11);
            bambou1_1.ajouteSectionBambou(secBam1_1);
            //Parcelle 3 1
            SectionBambou secBam3_1 = new SectionBambou(Couleur.VERT);
            plateau.addParcelle(pc31, secBam3_1);
            Bambou bambou3_1 = new Bambou(pc31);
            bambou3_1.ajouteSectionBambou(secBam3_1);
        } catch (ParcelleExistanteException | NombreParcelleVoisineException | AjoutCouleurException exception){
            assert false : "Ne doit pas renvoyer d'exception";
        }

        assertEquals(0, plateau.getIrrigationsPosees().size());
        assertEquals(1, plateau.getIrrigationsDisponibles().size());
        boolean ajout1 = joueur1.actionIrrigation(plateau, arbitre, piocheIrrigation, gPP);
        assertTrue(ajout1);
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());
        assertEquals(19, piocheIrrigation.getNombreIrrigation());
        boolean ajout2 = joueur2.actionIrrigation(plateau,arbitre,piocheIrrigation, gPP);
        assertTrue(ajout2);
        assertEquals(2, plateau.getIrrigationsPosees().size());
        assertEquals(1, plateau.getIrrigationsDisponibles().size());
        assertEquals(18, piocheIrrigation.getNombreIrrigation());
        boolean ajout3 = joueur1.actionIrrigation(plateau, arbitre, piocheIrrigation, gPP);
        assertTrue(ajout3);
        assertEquals(3, plateau.getIrrigationsPosees().size());
        assertEquals(0, plateau.getIrrigationsDisponibles().size());
        assertEquals(17, piocheIrrigation.getNombreIrrigation());
        boolean ajout4 = joueur2.actionIrrigation(plateau, arbitre, piocheIrrigation, gPP);
        assertFalse(ajout4);
        assertEquals(3, plateau.getIrrigationsPosees().size());
        assertEquals(0, plateau.getIrrigationsDisponibles().size());
        assertEquals(17, piocheIrrigation.getNombreIrrigation());
    }

}