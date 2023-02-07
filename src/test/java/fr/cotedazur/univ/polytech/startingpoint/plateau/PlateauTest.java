package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleDisponible;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import fr.cotedazur.univ.polytech.startingpoint.pioche.PiocheSectionBambou;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    Plateau plateau;


    @BeforeEach
    void setUp() {
        plateau = new Plateau(new PiocheSectionBambou());
    }


    @Test
    void getParcelles() {
        Parcelle[] parcelles = plateau.getParcelles();
        assertEquals(1, parcelles.length);
        assertEquals(GestionParcelles.ETANG, parcelles[0]);
    }

    @Test
    void getVoisinesParcelle() {
        assertThrows(ParcelleNonPoseeException.class,
                () -> plateau.getVoisinesParcelle(new ParcelleCouleur(new Position(2, 0), Couleur.VERTE)));

        Parcelle pD2_0 = new ParcelleDisponible(new Position(2, 0));
        Parcelle pD1_1 = new ParcelleDisponible(new Position(1, 1));
        Parcelle pDm2_0 = new ParcelleDisponible(new Position(-2, 0));
        Parcelle pD4_0 = new ParcelleDisponible(new Position(4, 0));

        Parcelle[] voisines;
        try {
            voisines = plateau.getVoisinesParcelle(GestionParcelles.ETANG);
        } catch (ParcelleNonPoseeException e) {
            throw new AssertionError(e);
        }
        List<Parcelle> voisinesList = new ArrayList<>(List.of(voisines));

        assertTrue(voisinesList.contains(pD2_0));
        assertTrue(voisinesList.contains(pD1_1));
        assertTrue(voisinesList.contains(pDm2_0));
        assertFalse(voisinesList.contains(pD4_0));
    }

    @Test
    void getPositionsDisponibles() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 0));
        positions.add(new Position(1, -1));
        positions.add(new Position(-1, -1));
        positions.add(new Position(-2, 0));
        positions.add(new Position(-1, 1));

        List<Position> positionsDisponibles = Arrays.stream(plateau.getPositionsDisponibles()).toList();
        assertEquals(6, positionsDisponibles.size());
        assertTrue(positionsDisponibles.containsAll(positions));
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


    @Test
    void poseParcelle() {
        Position p2_0 = new Position(2, 0);
        Position pm1_m1 = new Position(-1, -1);
        Position p1_1 = new Position(1, 1);
        Position p3_1 = new Position(3, 1);
        ParcelleCouleur pC4_0R = new ParcelleCouleur(new Position(4, 0), Couleur.ROSE);
        ParcelleCouleur pC2_0J = new ParcelleCouleur(p2_0, Couleur.JAUNE);
        ParcelleCouleur pCm1_m1V = new ParcelleCouleur(pm1_m1, Couleur.VERTE);
        ParcelleCouleur pC1_1R = new ParcelleCouleur(p1_1, Couleur.ROSE);
        List<Parcelle> parcelles;
        List<Position> positionsDisponibles;

        // Ajout de pC4_0R pas possible, car pas dans la liste des positions disponibles
        assertFalse(plateau.poseParcelle(pC4_0R));

        // Ajout de pC2_0J
        assertTrue(plateau.poseParcelle(pC2_0J));
        parcelles = Arrays.stream(plateau.getParcelles()).toList();
        positionsDisponibles = Arrays.stream(plateau.getPositionsDisponibles()).toList();
        assertEquals(2, parcelles.size());
        assertEquals(5, positionsDisponibles.size()); // Position de la parcelle ajoutée plus disponible
        assertTrue(parcelles.contains(pC2_0J));
        assertFalse(parcelles.contains(pC4_0R));
        assertFalse(positionsDisponibles.contains(p2_0)); // Position de pC2_0J plus disponible

        // Ajout de pCm1_m1V
        assertTrue(plateau.poseParcelle(pCm1_m1V));
        parcelles = Arrays.stream(plateau.getParcelles()).toList();
        positionsDisponibles = Arrays.stream(plateau.getPositionsDisponibles()).toList();
        assertEquals(3, parcelles.size());
        assertEquals(4, positionsDisponibles.size()); // Position de la parcelle ajoutée plus disponible
        assertTrue(parcelles.contains(pCm1_m1V));
        assertFalse(positionsDisponibles.contains(pm1_m1)); // Position de pCm1_m1V plus disponible

        // Ajout de pC1_1R
        assertTrue(plateau.poseParcelle(pC1_1R));
        parcelles = Arrays.stream(plateau.getParcelles()).toList();
        positionsDisponibles = Arrays.stream(plateau.getPositionsDisponibles()).toList();
        assertEquals(4, parcelles.size());
        // Position de la parcelle ajoutée plus disponible, mais ajout nouvelle position
        assertEquals(4, positionsDisponibles.size());
        assertTrue(parcelles.contains(pC1_1R));
        assertFalse(positionsDisponibles.contains(p1_1)); // Position de pC1_1R plus disponible
        // Position de pC1_1R plus disponible, mais ajout nouvelle position
        assertTrue(positionsDisponibles.contains(p3_1));
    }

    @Test
    void addIrrigation(){
        Position position11 = new Position(1,1);
        Position position20 = new Position(2,0);
        Position position31 = new Position(3,1);
        Position position22 = new Position(2,2);
        ParcelleCouleur pc11 = new ParcelleCouleur(position11, Couleur.JAUNE);
        ParcelleCouleur pc20 = new ParcelleCouleur(position20, Couleur.ROSE);
        assertTrue(plateau.poseParcelle(pc11));
        assertTrue(plateau.poseParcelle(pc20));
        assertTrue(pc11.isIrriguee());
        assertTrue(pc20.isIrriguee());
        assertEquals(0, plateau.getIrrigationsPosees().size());
        assertEquals(1, plateau.getIrrigationsDisponibles().size());

        assertTrue(plateau.poseIrrigation(position11, position20));
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(0, plateau.getIrrigationsDisponibles().size());

        ParcelleCouleur pc31 = new ParcelleCouleur(position31, Couleur.VERTE);
        assertTrue(plateau.poseParcelle(pc31));
        assertFalse(pc31.isIrriguee());
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());

        ParcelleCouleur pc22 =  new ParcelleCouleur(position22, Couleur.JAUNE);
        assertTrue(plateau.poseParcelle(pc22));
        assertFalse(pc22.isIrriguee());
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());

        assertTrue(plateau.poseIrrigation(position11, position31));
        assertTrue(pc31.isIrriguee());
        assertEquals(2, plateau.getIrrigationsPosees().size());
        assertEquals(3, plateau.getIrrigationsDisponibles().size());

        assertTrue(plateau.poseIrrigation(position20, position31));
        assertEquals(3, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());
    }

    @Test
    void isIrrigeeVoisinEtang(){
        Position position11 = new Position(1,1);
        Position position20 = new Position(2,0);
        ParcelleCouleur pc11 = new ParcelleCouleur(position11, Couleur.JAUNE);
        ParcelleCouleur pc20 = new ParcelleCouleur(position20, Couleur.ROSE);
        plateau.poseParcelle(pc11);
        plateau.poseParcelle(pc20);
        assertTrue(pc11.isIrriguee());
        assertTrue(pc20.isIrriguee());
    }

    @Test
    void addIrrigationNonPossible(){
        Position position11 = new Position(1,1);
        Position position20 = new Position(2,0);
        Position position31 = new Position(3,1);
        Position position22 = new Position(2,2);
        ParcelleCouleur pc11 = new ParcelleCouleur(position11, Couleur.JAUNE);
        ParcelleCouleur pc20 = new ParcelleCouleur(position20, Couleur.ROSE);
        plateau.poseParcelle(pc11);
        plateau.poseParcelle(pc20);
        plateau.poseIrrigation(position11, position20);

        ParcelleCouleur pc31 = new ParcelleCouleur(position31, Couleur.VERTE);
        ParcelleCouleur pc22 = new ParcelleCouleur(position22, Couleur.JAUNE);
        plateau.poseParcelle(pc31);
        plateau.poseParcelle(pc22);

        //Ajout de l'irrigation alors qu'il n'y a pas d'irrigation avant : donc l'ajout est impossible et les set ne sont pas modifiés
        assertFalse(plateau.poseIrrigation(position11,position22));
        assertEquals(1, plateau.getIrrigationsPosees().size());
        assertEquals(2, plateau.getIrrigationsDisponibles().size());
        assertTrue(pc20.isIrriguee());
        assertTrue(pc11.isIrriguee());
        assertFalse(pc31.isIrriguee());
        assertFalse(pc22.isIrriguee());
    }

    @Test
    void poseBambou(){
        Position position11 = new Position(1,1);
        Position position20 = new Position(2,0);
        Position position31 = new Position(3,1);
        ParcelleCouleur pc11 = new ParcelleCouleur(position11, Couleur.JAUNE);
        ParcelleCouleur pc20 = new ParcelleCouleur(position20, Couleur.ROSE);
        ParcelleCouleur pc31 = new ParcelleCouleur(position31, Couleur.VERTE);
        plateau.poseParcelle(pc11);

        plateau.poseParcelle(pc20);
        plateau.poseParcelle(pc31);

        //autour de l'étang donc automatiquement irriguée et donc automatiquement un bambou
        Optional<Bambou> optionalBambou11 = GestionBambous.chercheBambou(plateau.getBambous(), position11);
        if (optionalBambou11.isPresent()) assertEquals(1, optionalBambou11.get().getTailleBambou());

        Optional<Bambou> optionalBambou20 = GestionBambous.chercheBambou(plateau.getBambous(), position20);
        if (optionalBambou20.isPresent()) assertEquals(1, optionalBambou20.get().getTailleBambou());

        //n'ajoute pas de bambou si non irriguée
        assertFalse(plateau.poseBambou(pc31, new SectionBambou(pc31.getCouleur())));
        plateau.poseIrrigation(position11, position20);
        plateau.poseIrrigation(position11, position31);

        //ajout si irriguée
        assertTrue(pc31.isIrriguee());
        assertTrue(plateau.poseBambou(pc31, new SectionBambou(pc31.getCouleur())));

        Optional<Bambou> optionalBambou31 = GestionBambous.chercheBambou(plateau.getBambous(), position31);
        if (optionalBambou31.isPresent()) assertEquals(2, optionalBambou31.get().getTailleBambou());

        assertTrue(plateau.poseBambou(pc11, new SectionBambou(pc11.getCouleur())));
        Optional<Bambou> optionalBambou11_2 = GestionBambous.chercheBambou(plateau.getBambous(), position11);
        if (optionalBambou11_2.isPresent()) assertEquals(2, optionalBambou11_2.get().getTailleBambou());
    }

}