package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlateauTest {
    // Attributs
    Plateau plateau;


    // Constructeur
    @BeforeEach
    void setUp() {
        plateau = new Plateau();
    }


    // Accesseurs
    @Test
    void getParcelles() {
        assertEquals(0, plateau.getParcelles().length);
    }

    @Test
    void getParcellesVoisines() {
        assertThrows(ParcelleNonPoseeException.class, () -> plateau.getParcellesVoisines(new Etang()));
    }

    @Test
    void getBambous() {
        assertEquals(0, plateau.getBambous().length);
    }

    @Test
    void getPanda() {
        assertEquals(Panda.class, plateau.getPanda().getClass());
    }

    @Test
    void getJardinier() {
        assertEquals(Jardinier.class, plateau.getJardinier().getClass());
    }
}