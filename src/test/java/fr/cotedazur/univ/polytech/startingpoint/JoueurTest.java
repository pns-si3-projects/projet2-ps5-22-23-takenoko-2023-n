package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JoueurTest {
    Joueur joueur1;

    @BeforeEach
    void setUp() {
        ObjectifParcelle objPar2_3 = new ObjectifParcelle(2, 3);
        ObjectifPanda objPan3_2 = new ObjectifPanda(3, 2);
        ObjectifJardinier objJar3_2 = new ObjectifJardinier(3, 2);
        joueur1 = new Joueur("Robot1", new Random(), objPar2_3, objPan3_2, objJar3_2);
    }

    @Test
    void getNom() {
        assertEquals("Robot1", joueur1.getNom());
    }
}