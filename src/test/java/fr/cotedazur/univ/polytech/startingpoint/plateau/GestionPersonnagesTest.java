package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheSectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestionPersonnagesTest {
    Plateau plateau;
    ParcelleCouleur pC2_0V;
    ParcelleCouleur pC1_1J;
    ParcelleCouleur pC3_1R;
    ParcelleCouleur pCm2_0J;
    ParcelleCouleur pC4_0J;
    ParcelleCouleur pC2_2V;
    ParcelleCouleur pC1_m1R;
    ParcelleCouleur pCm1_m1R;


    @BeforeEach
    void setUp() {
        plateau = new Plateau(new PiocheSectionBambou());
        pC2_0V = new ParcelleCouleur(new Position(2, 0), Couleur.VERTE);
        pC1_1J = new ParcelleCouleur(new Position(1, 1), Couleur.JAUNE);
        pC3_1R = new ParcelleCouleur(new Position(3, 1), Couleur.ROSE);
        pCm2_0J = new ParcelleCouleur(new Position(-2, 0), Couleur.JAUNE);
        pC4_0J = new ParcelleCouleur(new Position(4, 0), Couleur.JAUNE);
        pC2_2V = new ParcelleCouleur(new Position(2, 2), Couleur.VERTE);
        pC1_m1R = new ParcelleCouleur(new Position(1, -1), Couleur.ROSE);
        pCm1_m1R = new ParcelleCouleur(new Position(-1, -1), Couleur.ROSE);

        plateau.poseParcelle(pC2_0V);
        plateau.poseParcelle(pC1_1J);
        plateau.poseParcelle(pC3_1R);
        plateau.poseParcelle(pCm2_0J);
        plateau.poseParcelle(pC4_0J);
        plateau.poseParcelle(pC2_2V);
        plateau.poseParcelle(pC1_m1R);
        plateau.poseParcelle(pCm1_m1R);
    }


    @Test
    void deplacementsPossibles() {
        List<Position> positionsList = new ArrayList<>();
        positionsList.add(pC1_1J.getPosition());
        positionsList.add(pC2_2V.getPosition());
        positionsList.add(pC2_0V.getPosition());
        positionsList.add(pC4_0J.getPosition());
        positionsList.add(pC1_m1R.getPosition());
        positionsList.add(pCm1_m1R.getPosition());
        positionsList.add(pCm2_0J.getPosition());

        List<Position> positionsDeplacement = GestionPersonnages.deplacementsPossibles(
                plateau.getParcelleEtVoisinesList(), plateau.getJardinier().getPosition());
        assertEquals(positionsList.size(), positionsDeplacement.size());
        assertTrue(positionsList.containsAll(positionsDeplacement));
        assertTrue(positionsDeplacement.containsAll(positionsList));
    }
}