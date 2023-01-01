package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GestionnairePossibilitePlateauTest {
    Plateau plateau;
    GestionnairePossibilitePlateau gPP;
    ParcelleCouleur pC11;
    ParcelleCouleur pCm1m1;
    ParcelleCouleur pC20;
    ParcelleCouleur pCm20;
    ParcelleCouleur pCm11;
    ParcelleCouleur pC1m1;
    ParcelleCouleur pC3m1;
    ParcelleCouleur pC40;

    @BeforeEach
    void setUp(){
        plateau = new Plateau();
        gPP = new GestionnairePossibilitePlateau(plateau);
        pC11 = new ParcelleCouleur(new Position(1,1));
        pCm1m1 = new ParcelleCouleur(new Position(-1,-1));
        pC20 = new ParcelleCouleur(new Position(2,0));
        pCm20 = new ParcelleCouleur(new Position(-2,0));
        pCm11 = new ParcelleCouleur(new Position(-1,1));
        pC1m1 = new ParcelleCouleur(new Position(1,-1));
        pC3m1 = new ParcelleCouleur(new Position(3,-1));
        pC40 = new ParcelleCouleur(new Position(4,0));
        try {
            plateau.addParcelle(pC11);
            plateau.addParcelle(pCm1m1);
            plateau.addParcelle(pC20);
            plateau.addParcelle(pCm20);
            plateau.addParcelle(pCm11);
            plateau.addParcelle(pC1m1);
            plateau.addParcelle(pC3m1);
            plateau.addParcelle(pC40);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisinException exception){
            assert false : "Ne doit normalement pas renvoyer d'erreur";
        }
    }

    @Test
    void deplacementPossiblePersonnageDiagonaleDroite() {
        Panda panda = plateau.getPanda();
        List<Position> deplacementPossibleDiagonaleDroite = gPP.deplacementPossiblePersonnageDiagonaleDroite(panda);
        assertTrue(2 == deplacementPossibleDiagonaleDroite.size());
        assertEquals(pC11.getPosition(),deplacementPossibleDiagonaleDroite.get(0));
        assertEquals(pCm1m1.getPosition(),deplacementPossibleDiagonaleDroite.get(1));
    }

    @Test
    void deplacementPossiblePersonnageDiagonaleGauche() {
        Jardinier jardinier = new Jardinier();
        List<Position> deplacementPossibleDiagonaleGauche = gPP.deplacementPossiblePersonnageDiagonaleGauche(jardinier);
        assertTrue(2 == deplacementPossibleDiagonaleGauche.size());
        assertEquals(pCm11.getPosition(),deplacementPossibleDiagonaleGauche.get(0));
        assertEquals(pC1m1.getPosition(),deplacementPossibleDiagonaleGauche.get(1));
    }

    @Test
    void deplacementPossiblePersonnageHorizontal() {
        Panda panda = plateau.getPanda();
        List<Position> deplacementPossibleHorrizontal = gPP.deplacementPossiblePersonnageHorizontal(panda);
        assertTrue(3 == deplacementPossibleHorrizontal.size());
        assertEquals(pC20.getPosition(),deplacementPossibleHorrizontal.get(2));
        assertEquals(pCm20.getPosition(),deplacementPossibleHorrizontal.get(0));
        assertEquals(pC40.getPosition(),deplacementPossibleHorrizontal.get(1));
    }

    @Test
    void allDeplacement(){
        Jardinier jardinier = new Jardinier();
        List<Position> deplacementPossible = gPP.deplacementPossiblePersonnageDiagonaleGauche(jardinier);
        deplacementPossible.addAll(gPP.deplacementPossiblePersonnageHorizontal(jardinier));
        deplacementPossible.addAll(gPP.deplacementPossiblePersonnageDiagonaleDroite(jardinier));
        Set<Parcelle> setParcelle = plateau.getListParcelle();
        assertFalse(setParcelle.size() == deplacementPossible.size());
        Iterator<Parcelle> iterator = setParcelle.iterator();
        while (iterator.hasNext()){
            Parcelle parcelle = iterator.next();
            if(!parcelle.equals(pC3m1) && !parcelle.equals(plateau.getEtang())) {
                assertTrue(deplacementPossible.contains(parcelle.getPosition()));
            }
        }
    }
}