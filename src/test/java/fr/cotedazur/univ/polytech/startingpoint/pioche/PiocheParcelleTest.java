package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.pioche.ParcellePioche;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheParcelle;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheParcelleEnCoursException;
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
        piocheParcelle.choisiParcelle(parcelles[0], new Position(1, 0));
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
        // retirer le ".getClass()" lorsque le equals de la classe ParcellePioche sera faisable et fait (avec la couleur)
        when(mockRandom.nextInt(anyInt())).thenReturn(26, 26, 1, 1, 5, 8, 2, 2, 4);
        piocheParcelle = new PiocheParcelle(mockRandom);
        try {
            ParcellePioche[] parcelles = piocheParcelle.pioche();
            assertEquals(new ParcellePioche().getClass(), parcelles[0].getClass());
            piocheParcelle.choisiParcelle(parcelles[1], new Position(2, 1));
            // Possible car une parcelle a été choisie
            parcelles = piocheParcelle.pioche();
            assertEquals(new ParcellePioche().getClass(), parcelles[1].getClass());
        } catch (PiocheParcelleEnCoursException e) {
            new AssertionError(e);
        }
        // Pioche déjà en cours donc erreur de pioche
        assertThrows(PiocheParcelleEnCoursException.class, () -> {piocheParcelle.pioche();});
    }

    @Test
    void creeParcelle() {
        piocheParcelle = new PiocheParcelle(new Random());
        ParcellePioche[] parcelles;
        try {
            parcelles = piocheParcelle.pioche();
            assertEquals(new ParcellePioche().getClass(), parcelles[0].getClass());
            Position position4_0 = new Position(4, 0);
            assertEquals(new ParcelleCouleur(position4_0), piocheParcelle.choisiParcelle(parcelles[1], position4_0));
        } catch (PiocheParcelleEnCoursException e) {
            new AssertionError(e);
        }
        /*

        A mettre quand exception sera faite

        ParcellePioche parcelleChoisie = new ParcellePioche();
        assertThrows(PiocheParcelleEnCoursException.class, () -> {
            piocheParcelle.choisiParcelle(parcelleChoisie, new Position(-3, -3));
        });
        */
    }
}