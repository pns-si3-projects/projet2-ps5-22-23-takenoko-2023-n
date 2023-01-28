package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.MotifNonValideException;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
import fr.cotedazur.univ.polytech.startingpoint.plateau.NombreParcelleVoisineException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionnairePossibiliteMotifTest {

    @BeforeEach
    void setUp() {
    }

    /*@Test
    void getParcellePlusProcheObjectif2Parcelle() {
        ParcelleCouleur parcelleCouleurMotif00 = new ParcelleCouleur(new Position(), Couleur.VERT);
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

    }*/
}