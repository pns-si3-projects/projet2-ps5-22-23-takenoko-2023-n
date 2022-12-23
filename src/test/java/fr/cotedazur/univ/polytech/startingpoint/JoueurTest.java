package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    Joueur joueur1;

    @BeforeEach
    void setUp(){
        joueur1 = new Joueur("Robot1");
    }

    @Test
    void getNom() {
        assertEquals("Robot1", joueur1.getNom());
    }
}