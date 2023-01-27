package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Motif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.MotifNonValideException;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionnairePossibilitePlateauTest {
    Plateau plateau;
    GestionnairePossibilitePlateau gPP;
    ParcelleCouleur pC11J;
    ParcelleCouleur pCm1m1R;
    ParcelleCouleur pC20V;
    ParcelleCouleur pCm20R;
    ParcelleCouleur pCm11R;
    ParcelleCouleur pC1m1J;
    ParcelleCouleur pC3m1V;
    ParcelleCouleur pC40V;
    SectionBambou secBamV = new SectionBambou(Couleur.VERT);
    SectionBambou secBamR = new SectionBambou(Couleur.ROSE);
    SectionBambou secBamJ = new SectionBambou(Couleur.JAUNE);

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        gPP = new GestionnairePossibilitePlateau(plateau);
        pC11J = new ParcelleCouleur(new Position(1, 1), Couleur.JAUNE);
        pCm1m1R = new ParcelleCouleur(new Position(-1, -1), Couleur.ROSE);
        pC20V = new ParcelleCouleur(new Position(2, 0), Couleur.VERT);
        pCm20R = new ParcelleCouleur(new Position(-2, 0), Couleur.ROSE);
        pCm11R = new ParcelleCouleur(new Position(-1, 1), Couleur.ROSE);
        pC1m1J = new ParcelleCouleur(new Position(1, -1), Couleur.JAUNE);
        pC3m1V = new ParcelleCouleur(new Position(3, -1), Couleur.VERT);
        pC40V = new ParcelleCouleur(new Position(4, 0), Couleur.VERT);
        try {
            plateau.addParcelle(pC11J, secBamJ);
            plateau.addParcelle(pCm1m1R, secBamR);
            plateau.addParcelle(pC20V, secBamV);
            plateau.addParcelle(pCm20R, secBamR);
            plateau.addParcelle(pCm11R, secBamR);
            plateau.addParcelle(pC1m1J, secBamJ);
            plateau.addParcelle(pC3m1V, secBamV);
            plateau.addParcelle(pC40V, secBamV);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
            throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
        }
    }

    @Test
    void deplacementPossiblePersonnageDiagonaleDroite() {
        Panda panda = plateau.getPanda();
        List<Position> deplacementPossibleDiagonaleDroite = gPP.deplacementPossiblePersonnageDiagonaleDroite(panda.position());
        assertEquals(2, deplacementPossibleDiagonaleDroite.size());
        assertEquals(pC11J.position(), deplacementPossibleDiagonaleDroite.get(0));
        assertEquals(pCm1m1R.position(), deplacementPossibleDiagonaleDroite.get(1));
    }

    @Test
    void deplacementPossiblePersonnageDiagonaleGauche() {
        Jardinier jardinier = new Jardinier();
        List<Position> deplacementPossibleDiagonaleGauche = gPP.deplacementPossiblePersonnageDiagonaleGauche(jardinier.position());
        assertEquals(2, deplacementPossibleDiagonaleGauche.size());
        assertEquals(pC1m1J.position(), deplacementPossibleDiagonaleGauche.get(0));
        assertEquals(pCm11R.position(), deplacementPossibleDiagonaleGauche.get(1));
    }

    @Test
    void deplacementPossiblePersonnageHorizontal() {
        Panda panda = plateau.getPanda();
        List<Position> deplacementPossibleHorrizontal = gPP.deplacementPossiblePersonnageHorizontal(panda.position());
        assertEquals(3, deplacementPossibleHorrizontal.size());
        assertEquals(pC20V.position(), deplacementPossibleHorrizontal.get(0));
        assertEquals(pC40V.position(), deplacementPossibleHorrizontal.get(1));
        assertEquals(pCm20R.position(), deplacementPossibleHorrizontal.get(2));
    }

    @Test
    void allDeplacement() {
        Jardinier jardinier = new Jardinier();
        List<Position> deplacementPossible = gPP.deplacementPossiblePersonnageDiagonaleGauche(jardinier.position());
        deplacementPossible.addAll(gPP.deplacementPossiblePersonnageHorizontal(jardinier.position()));
        deplacementPossible.addAll(gPP.deplacementPossiblePersonnageDiagonaleDroite(jardinier.position()));
        Parcelle[] parcelles = plateau.getParcelles();
        assertNotEquals(parcelles.length, deplacementPossible.size());
        for (Parcelle parcelle : parcelles) {
            if (!parcelle.equals(pC3m1V) && !parcelle.equals(plateau.getEtang())) {
                assertTrue(deplacementPossible.contains(parcelle.position()));
            }
        }
    }

    @Test
    void filtrePositionsParcelleCouleur() {
        List<Position> positionsDep = gPP.deplacementPossiblePersonnageDiagonaleDroite(new Position());
        assertTrue(positionsDep.contains(pC11J.position()));
        assertEquals(2, positionsDep.size());
        positionsDep = gPP.filtrePositionsParcelleCouleur(positionsDep, Couleur.JAUNE);
        assertEquals(1, positionsDep.size());
        assertTrue(positionsDep.contains(pC11J.position()));
    }



    @Test
    void filtrePositionsBambouCouleur() {
        List<Position> positionsDep = gPP.deplacementPossiblePersonnageHorizontal(new Position());
        assertTrue(positionsDep.contains(pCm20R.position()));
        assertEquals(3, positionsDep.size());

        List<Position> positionsDepRose = gPP.filtrePositionsParcelleCouleur(positionsDep, Couleur.ROSE);
        assertEquals(1, positionsDepRose.size());
        assertTrue(positionsDepRose.contains(pCm20R.position()));

        List<Position> positionsDepVert = gPP.filtrePositionsParcelleCouleur(positionsDep, Couleur.VERT);
        assertEquals(2, positionsDepVert.size());
        assertTrue(positionsDepVert.contains(pC20V.position()));
        assertTrue(positionsDepVert.contains(pC40V.position()));
    }

    @Test
    void getParcellePlusProcheObjectif2Parcelle() {
        ParcelleCouleur parcelleCouleurMotif00 = new ParcelleCouleur(new Position(),Couleur.VERT);
        ParcelleCouleur parcelleCouleurMotif11 = new ParcelleCouleur(new Position(1,1),Couleur.VERT);
        List<Parcelle> listParcellePossible = new ArrayList<>();
        listParcellePossible.add(pCm20R);
        listParcellePossible.add(pC1m1J);
        listParcellePossible.add(pC3m1V);
        Motif motif = null;
        try {
            motif = new Motif(parcelleCouleurMotif00,parcelleCouleurMotif11);
        } catch (MotifNonValideException e) {
            assert false : "Ne doit pas renvoyer d'exception";
        }
        ObjectifParcelle objectifParcelle = new ObjectifParcelle(3,motif);
        assertTrue(listParcellePossible.contains(gPP.getParcellePlusProcheObjectif(objectifParcelle))); // Car le hashCode a trie la liste de Parcelle et donc c'est la parcelle Couleur -2,0 est en première
    }

    @Test
    void getParcellePlusProcheObjectif3ParcellePasPossible() {
        ParcelleCouleur parcelleCouleurMotifm1m1 = new ParcelleCouleur(new Position(-1,-1),Couleur.VERT);
        ParcelleCouleur parcelleCouleurMotif00 = new ParcelleCouleur(new Position(),Couleur.VERT);
        ParcelleCouleur parcelleCouleurMotif11 = new ParcelleCouleur(new Position(1,1),Couleur.VERT);
        List<Parcelle> listParcellePossible = new ArrayList<>();
        listParcellePossible.add(pCm20R);
        listParcellePossible.add(pC1m1J);
        listParcellePossible.add(pC3m1V);
        Motif motif = null;
        try {
            motif = new Motif(parcelleCouleurMotif00, parcelleCouleurMotifm1m1, parcelleCouleurMotif11);
        } catch (MotifNonValideException e) {
            assert false : "Ne doit pas renvoyer d'exception";
        }
        ObjectifParcelle objectifParcelle = new ObjectifParcelle(3,motif);
        assertTrue(listParcellePossible.contains(gPP.getParcellePlusProcheObjectif(objectifParcelle))); // Car le hashCode a trie la liste de Parcelle et donc c'est la parcelle Couleur -2,0 est en première
    }

    @Test
    void getParcellePlusProcheObjectif3ParcellePossible() {
        ParcelleCouleur parcelleCouleurMotifm1m1 = new ParcelleCouleur(new Position(-1,-1),Couleur.VERT);
        ParcelleCouleur parcelleCouleurMotif00 = new ParcelleCouleur(new Position(),Couleur.VERT);
        ParcelleCouleur parcelleCouleurMotif11 = new ParcelleCouleur(new Position(1,1),Couleur.VERT);
        ParcelleCouleur parcelleCouleur31 = new ParcelleCouleur(new Position(3,1),Couleur.VERT);
        ParcelleCouleur parcelleCouleur02 = new ParcelleCouleur(new Position(0,2),Couleur.VERT);
        List<Parcelle> listParcellePossible = new ArrayList<>();
        listParcellePossible.add(pC1m1J);
        listParcellePossible.add(pCm20R);
        Motif motif = null;
        try {
            motif = new Motif(parcelleCouleurMotif00, parcelleCouleurMotifm1m1, parcelleCouleurMotif11);
        } catch (MotifNonValideException e) {
            assert false : "Ne doit pas renvoyer d'exception";
        }
        ObjectifParcelle objectifParcelle = new ObjectifParcelle(3,motif);
        try {
            plateau.addParcelle(parcelleCouleur31,secBamV);
            assertEquals(pC1m1J,gPP.getParcellePlusProcheObjectif(objectifParcelle));
            plateau.addParcelle(parcelleCouleur02,secBamV);
            assertTrue(listParcellePossible.contains(gPP.getParcellePlusProcheObjectif(objectifParcelle))); // Car le hashCode a trie la liste de Parcelle et donc c'est la parcelle Couleur -2,0 est en première
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException e) {
            assert false : "Ne doit pas renvoyer d'exception";
        }

    }
}