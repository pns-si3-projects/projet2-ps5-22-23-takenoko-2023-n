package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    Random random;
    PiocheObjectifParcelle piocheObjectifParcelle;
    PiocheObjectifPanda piocheObjectifPanda;
    PiocheObjectifJardinier piocheObjectifJardinier;
    PiocheObjectif piocheObjectif;
    ObjectifParcelle objectifParcellePioche;

    Joueur joueur1;

    @BeforeEach
    void setUp(){
        random = new Random();
        piocheObjectifParcelle = new PiocheObjectifParcelle(random);
        piocheObjectifPanda = new PiocheObjectifPanda(random);
        piocheObjectifJardinier = new PiocheObjectifJardinier(random);
        piocheObjectif = new PiocheObjectif(piocheObjectifParcelle,piocheObjectifPanda,piocheObjectifJardinier);
        objectifParcellePioche = piocheObjectif.piocheObjectifParcelle();
        joueur1 = new Joueur("Robot1",random,objectifParcellePioche);
    }

    @Test
    void getNom() {
        assertEquals("Robot1", joueur1.getNom());
    }
}