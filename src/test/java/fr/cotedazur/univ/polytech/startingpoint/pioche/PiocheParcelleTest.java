package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PiocheParcelleTest {
    PiocheParcelle piocheParcelle;
    @Mock
    Random mockRandom = mock(Random.class);

    @Test
    void getNombreParcellesRestantes() throws PiocheParcelleEnCoursException {
        piocheParcelle = new PiocheParcelle(new Random());
        assertEquals(27, piocheParcelle.getNombreParcellesRestantes());
        ParcellePioche[] parcelles = piocheParcelle.pioche();
        // Pour retirer un élément de la pioche, il faut le choisir
        assertEquals(27, piocheParcelle.getNombreParcellesRestantes());
        try {
            piocheParcelle.choisiParcelle(parcelles[0], new Position(1, 0));
        } catch (PiocheParcelleVideException e) {
            throw new AssertionError(e);
        }
        assertEquals(26, piocheParcelle.getNombreParcellesRestantes());
    }

    @Test
    void isEmpty() {
        piocheParcelle = new PiocheParcelle(new Random());
        assertFalse(piocheParcelle.isEmpty());
    }

    @Test
    void piocheValeurTropGrande() {
        when(mockRandom.nextInt(anyInt())).thenReturn(27);
        piocheParcelle = new PiocheParcelle(mockRandom);
        assertThrows(ArithmeticException.class, () -> {piocheParcelle.pioche();});
    }

    @Test
    void piocheValeurTropPetite() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        piocheParcelle = new PiocheParcelle(mockRandom);
        assertThrows(ArithmeticException.class, () -> {piocheParcelle.pioche();});
    }

    @Test
    void pioche() {
        when(mockRandom.nextInt(anyInt())).thenReturn(26, 26, 1, 1, 5, 8, 2, 2, 4);
        piocheParcelle = new PiocheParcelle(mockRandom);
        try {
            ParcellePioche[] parcelles = piocheParcelle.pioche();
            assertEquals(new ParcellePioche(Couleur.JAUNE), parcelles[0]);
            try {
                piocheParcelle.choisiParcelle(parcelles[1], new Position(2, 1));
            } catch (PiocheParcelleVideException e) {
                throw new AssertionError(e);
            }
            // Possible car une parcelle a été choisie
            parcelles = piocheParcelle.pioche();
            assertEquals(new ParcellePioche(Couleur.VERT), parcelles[1]);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError(e);
        }
        // Pioche déjà en cours donc erreur de pioche
        assertThrows(PiocheParcelleEnCoursException.class, () -> {piocheParcelle.pioche();});
    }

    @Test
    void creeParcelle() {
        when(mockRandom.nextInt(anyInt())).thenReturn(17, 3, 25, 12, 24, 20);
        piocheParcelle = new PiocheParcelle(mockRandom);
        ParcellePioche[] parcelles;
        try {
            parcelles = piocheParcelle.pioche();
            assertEquals(new ParcellePioche(Couleur.ROSE), parcelles[0]);
            Position position4_0 = new Position(4, 0);
            assertEquals(new ParcelleCouleur(position4_0, Couleur.JAUNE), piocheParcelle.choisiParcelle(parcelles[2], position4_0));
        } catch (PiocheParcelleEnCoursException | PiocheParcelleVideException e) {
            throw new AssertionError(e);
        }
        try {
            parcelles = piocheParcelle.pioche();
            assertEquals(new ParcellePioche(Couleur.JAUNE), parcelles[1]);
        } catch (PiocheParcelleEnCoursException e) {
            throw new AssertionError(e);
        } finally {
            ParcellePioche parcellePiocheVert = new ParcellePioche(Couleur.VERT);
            Position position2_0 = new Position(2, 0);
            assertThrows(IllegalArgumentException.class, () -> piocheParcelle.choisiParcelle(parcellePiocheVert, position2_0));
        }
        try {
            Position position2_0 = new Position(2, 0);
            assertEquals(new ParcelleCouleur(position2_0, Couleur.ROSE), piocheParcelle.choisiParcelle(parcelles[0], position2_0));
        } catch (PiocheParcelleVideException e) {
            throw new AssertionError(e);
        }
        ParcellePioche parcellePiocheJaune = new ParcellePioche(Couleur.JAUNE);
        Position position3_3 = new Position(3, 3);
        assertThrows(PiocheParcelleVideException.class, () -> piocheParcelle.choisiParcelle(parcellePiocheJaune, position3_3));
    }
}