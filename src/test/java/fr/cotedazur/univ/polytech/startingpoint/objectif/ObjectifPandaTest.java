package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectifPandaTest {
    ObjectifPanda objP3_2;
    List<SectionBambou> bambous3_2;
    ObjectifPanda objP3_2Bis;
    List<SectionBambou> bambous3_2_bis;
    ObjectifPanda objP4_2;
    List<SectionBambou> bambous4_2;
    ObjectifPanda objP5_2;
    List<SectionBambou> bambous5_2;
    ObjectifPanda objP6_3;
    List<SectionBambou> bambous6_3;
    ObjectifPanda objP6_3_bis;
    List<SectionBambou> bambous6_3_bis;
    Couleur verte;
    Couleur rose;
    Couleur jaune;


    @BeforeEach
    void setUp() {
        verte = Couleur.VERTE;
        rose = Couleur.ROSE;
        jaune = Couleur.JAUNE;
        bambous3_2 = new ArrayList<>();
        bambous3_2_bis = new ArrayList<>();
        bambous4_2 = new ArrayList<>();
        bambous5_2 = new ArrayList<>();
        bambous6_3 = new ArrayList<>();
        bambous6_3_bis = new ArrayList<>();

        for (int i=0;i<2;i++) {
            bambous3_2.add(new SectionBambou(verte));
            bambous3_2_bis.add(new SectionBambou(verte));
            bambous4_2.add(new SectionBambou(jaune));
            bambous5_2.add(new SectionBambou(rose));
        }
        objP3_2 = new ObjectifPanda(3, bambous3_2);
        objP3_2Bis = new ObjectifPanda(3, bambous3_2_bis);
        objP4_2 = new ObjectifPanda(4, bambous4_2);
        objP5_2 = new ObjectifPanda(5, bambous5_2);

        bambous6_3.add(new SectionBambou(jaune));
        bambous6_3.add(new SectionBambou(rose));
        bambous6_3.add(new SectionBambou(verte));

        bambous6_3_bis.add(new SectionBambou(rose));
        bambous6_3_bis.add(new SectionBambou(verte));
        bambous6_3_bis.add(new SectionBambou(jaune));

        objP6_3 = new ObjectifPanda(6, bambous6_3);
        objP6_3_bis = new ObjectifPanda(6, bambous6_3_bis);

    }


    @Test
    void getNombreBambousAManger() {
        assertEquals(2, objP3_2.getBambousAManger().size());
        assertEquals(2, objP4_2.getBambousAManger().size());
        assertEquals(2, objP5_2.getBambousAManger().size());
        assertEquals(3, objP6_3.getBambousAManger().size());
    }

    @Test
    void getCouleur(){
        assertEquals(verte, objP3_2.getBambousAManger().get(0).getCouleur());
        assertEquals(jaune, objP4_2.getBambousAManger().get(0).getCouleur());
        assertEquals(rose, objP5_2.getBambousAManger().get(0).getCouleur());
    }

    @Test
    void getBambousAManger(){
        assertEquals(bambous3_2, objP3_2.getBambousAManger());
        assertEquals(bambous5_2, objP5_2.getBambousAManger());
        assertEquals(bambous4_2, objP4_2.getBambousAManger());
        assertEquals(bambous6_3, objP6_3.getBambousAManger());
    }


    @Test
    void testToString() {
        assertEquals("Objectif de 3 points pour 2 bambous de couleur verte à posséder",
                objP3_2.toString());
        assertEquals("Objectif de 4 points pour 2 bambous de couleur jaune à posséder",
                objP4_2.toString());
        assertEquals("Objectif de 6 points pour 1 bambou de chaque couleur à posséder",
                objP6_3.toString());
    }

    @Test
    void testEquals() {
        assertEquals(objP3_2, objP3_2Bis);
        assertNotEquals(objP3_2, objP4_2);
        assertNotEquals(objP4_2, objP5_2);
        assertNotEquals(objP5_2, objP6_3);
        assertNotEquals(objP3_2, objP6_3);
    }
}