package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleExistanteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    SectionBambou secBam = new SectionBambou();

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        gPP = new GestionnairePossibilitePlateau(plateau);
        pC11 = new ParcelleCouleur(new Position(1, 1));
        pCm1m1 = new ParcelleCouleur(new Position(-1, -1));
        pC20 = new ParcelleCouleur(new Position(2, 0));
        pCm20 = new ParcelleCouleur(new Position(-2, 0));
        pCm11 = new ParcelleCouleur(new Position(-1, 1));
        pC1m1 = new ParcelleCouleur(new Position(1, -1));
        pC3m1 = new ParcelleCouleur(new Position(3, -1));
        pC40 = new ParcelleCouleur(new Position(4, 0));
        try {
            plateau.addParcelle(pC11, secBam);
            plateau.addParcelle(pCm1m1, secBam);
            plateau.addParcelle(pC20, secBam);
            plateau.addParcelle(pCm20, secBam);
            plateau.addParcelle(pCm11, secBam);
            plateau.addParcelle(pC1m1, secBam);
            plateau.addParcelle(pC3m1, secBam);
            plateau.addParcelle(pC40, secBam);
        }
        catch (ParcelleExistanteException | NombreParcelleVoisineException exception) {
            throw new AssertionError("Ne doit normalement pas renvoyer d'erreur");
        }
    }

    @Test
    void deplacementPossiblePersonnageDiagonaleDroite() {
        Panda panda = plateau.getPanda();
        List<Position> deplacementPossibleDiagonaleDroite = gPP.deplacementPossiblePersonnageDiagonaleDroite(panda.getPosition());
        assertEquals(2, deplacementPossibleDiagonaleDroite.size());
        assertEquals(pC11.getPosition(), deplacementPossibleDiagonaleDroite.get(0));
        assertEquals(pCm1m1.getPosition(), deplacementPossibleDiagonaleDroite.get(1));
    }

    @Test
    void deplacementPossiblePersonnageDiagonaleGauche() {
        Jardinier jardinier = new Jardinier();
        List<Position> deplacementPossibleDiagonaleGauche = gPP.deplacementPossiblePersonnageDiagonaleGauche(jardinier.getPosition());
        assertEquals(2, deplacementPossibleDiagonaleGauche.size());
        assertEquals(pC1m1.getPosition(), deplacementPossibleDiagonaleGauche.get(0));
        assertEquals(pCm11.getPosition(), deplacementPossibleDiagonaleGauche.get(1));
    }

    @Test
    void deplacementPossiblePersonnageHorizontal() {
        Panda panda = plateau.getPanda();
        List<Position> deplacementPossibleHorrizontal = gPP.deplacementPossiblePersonnageHorizontal(panda.getPosition());
        assertEquals(3, deplacementPossibleHorrizontal.size());
        assertEquals(pC20.getPosition(), deplacementPossibleHorrizontal.get(0));
        assertEquals(pC40.getPosition(), deplacementPossibleHorrizontal.get(1));
        assertEquals(pCm20.getPosition(), deplacementPossibleHorrizontal.get(2));
    }

    @Test
    void allDeplacement() {
        Jardinier jardinier = new Jardinier();
        List<Position> deplacementPossible = gPP.deplacementPossiblePersonnageDiagonaleGauche(jardinier.getPosition());
        deplacementPossible.addAll(gPP.deplacementPossiblePersonnageHorizontal(jardinier.getPosition()));
        deplacementPossible.addAll(gPP.deplacementPossiblePersonnageDiagonaleDroite(jardinier.getPosition()));
        Parcelle[] parcelles = plateau.getParcelles();
        assertNotEquals(parcelles.length, deplacementPossible.size());
        for (Parcelle parcelle : parcelles) {
            if (!parcelle.equals(pC3m1) && !parcelle.equals(plateau.getEtang())) {
                assertTrue(deplacementPossible.contains(parcelle.getPosition()));
            }
        }
    }
}