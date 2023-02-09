package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleDisponible;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Jardinier;
import fr.cotedazur.univ.polytech.startingpoint.personnage.Panda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Irrigation;
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
        assertEquals(0, plateau.getIrrigationsPosees().length);
        assertEquals(1, plateau.getIrrigationsDisponibles().length);

        Irrigation irrigation1120 = new Irrigation();
        irrigation1120.addPosition(position11, position20);
        assertTrue(plateau.poseIrrigation(irrigation1120));
        assertEquals(1, plateau.getIrrigationsPosees().length);
        assertEquals(0, plateau.getIrrigationsDisponibles().length);

        ParcelleCouleur pc31 = new ParcelleCouleur(position31, Couleur.VERTE);
        assertTrue(plateau.poseParcelle(pc31));
        assertFalse(pc31.isIrriguee());
        assertEquals(1, plateau.getIrrigationsPosees().length);
        assertEquals(2, plateau.getIrrigationsDisponibles().length);

        ParcelleCouleur pc22 =  new ParcelleCouleur(position22, Couleur.JAUNE);
        assertTrue(plateau.poseParcelle(pc22));
        assertFalse(pc22.isIrriguee());
        assertEquals(1, plateau.getIrrigationsPosees().length);
        assertEquals(2, plateau.getIrrigationsDisponibles().length);

        Irrigation irrigation1131 = new Irrigation();
        irrigation1131.addPosition(position11, position31);
        assertTrue(plateau.poseIrrigation(irrigation1131));
        assertTrue(pc31.isIrriguee());
        assertEquals(2, plateau.getIrrigationsPosees().length);
        assertEquals(3, plateau.getIrrigationsDisponibles().length);

        Irrigation irrigation2031 = new Irrigation();
        irrigation2031.addPosition(position20, position31);
        assertTrue(plateau.poseIrrigation(irrigation2031));
        assertEquals(3, plateau.getIrrigationsPosees().length);
        assertEquals(2, plateau.getIrrigationsDisponibles().length);
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

        Irrigation irrigation1120 = new Irrigation();
        irrigation1120.addPosition(position11, position20);
        plateau.poseIrrigation(irrigation1120);

        ParcelleCouleur pc31 = new ParcelleCouleur(position31, Couleur.VERTE);
        ParcelleCouleur pc22 = new ParcelleCouleur(position22, Couleur.JAUNE);
        plateau.poseParcelle(pc31);
        plateau.poseParcelle(pc22);
        Irrigation irrigation1122 = new Irrigation();
        irrigation1122.addPosition(position11, position22);

        //Ajout de l'irrigation alors qu'il n'y a pas d'irrigation avant : donc l'ajout est impossible et les set ne sont pas modifiés
        assertFalse(plateau.poseIrrigation(irrigation1122));
        assertEquals(1, plateau.getIrrigationsPosees().length);
        assertEquals(2, plateau.getIrrigationsDisponibles().length);
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
        Irrigation irrigation1120 = new Irrigation();
        irrigation1120.addPosition(position11, position20);

        Irrigation irrigation1131 = new Irrigation();
        irrigation1131.addPosition(position11, position31);

        // autour de l'étang donc automatiquement irrigué et donc automatiquement un bambou
        Optional<Bambou> optionalBambou11 = GestionBambous.chercheBambou(plateau.getBambous(), position11);
        optionalBambou11.ifPresent(bambou -> assertEquals(1, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou20 = GestionBambous.chercheBambou(plateau.getBambous(), position20);
        optionalBambou20.ifPresent(bambou -> assertEquals(1, bambou.getTailleBambou()));

        // n'ajoute pas de bambou si non irrigué
        assertFalse(plateau.poseBambou(pc31, new SectionBambou(pc31.getCouleur())));
        plateau.poseIrrigation(irrigation1120);
        plateau.poseIrrigation(irrigation1131);

        // ajout si irriguée
        assertTrue(pc31.isIrriguee());
        assertTrue(plateau.poseBambou(pc31, new SectionBambou(pc31.getCouleur())));

        Optional<Bambou> optionalBambou31 = GestionBambous.chercheBambou(plateau.getBambous(), position31);
        optionalBambou31.ifPresent(bambou -> assertEquals(2, bambou.getTailleBambou()));

        assertTrue(plateau.poseBambou(pc11, new SectionBambou(pc11.getCouleur())));
        Optional<Bambou> optionalBambou11_2 = GestionBambous.chercheBambou(plateau.getBambous(), position11);
        optionalBambou11_2.ifPresent(bambou -> assertEquals(2, bambou.getTailleBambou()));
    }

    @Test
    void deplacementPandaTest () {
        //position des pracelles
        Position position1_1 = new Position(1, 1);
        Position position2_0 = new Position(2, 0);
        Position position1_m1 = new Position(1, -1);
        Position positionm1_m1 = new Position(-1, -1);

        //parcelles
        ParcelleCouleur parcelleCouleur1_1 = new ParcelleCouleur(position1_1, Couleur.JAUNE);
        ParcelleCouleur parcelleCouleur2_0 = new ParcelleCouleur(position2_0, Couleur.ROSE);
        ParcelleCouleur parcelleCouleur1_m1 = new ParcelleCouleur(position1_m1, Couleur.ROSE);
        ParcelleCouleur parcelleCouleurm1_m1 = new ParcelleCouleur(positionm1_m1, Couleur.VERTE);

        //pose des parcelles
        plateau.poseParcelle(parcelleCouleur1_1);
        plateau.poseParcelle(parcelleCouleur2_0);
        plateau.poseParcelle(parcelleCouleur1_m1);
        plateau.poseParcelle(parcelleCouleurm1_m1);


        //deplacement du panda
        assertEquals(plateau.getPanda().getPosition(), new Position());

        Position positionInitial = plateau.getPanda().getPosition();
        plateau.deplacementPanda(position1_1);

        assertEquals(position1_1, plateau.getPanda().getPosition());
        Optional<Bambou> optBamboou1_1 = GestionBambous.chercheBambou(plateau.getBambous(), position1_1);
        optBamboou1_1.ifPresent(bambou -> assertEquals(0, bambou.getTailleBambou()));
    }

    @Test
    void deplacementJardinier(){
        //irrigation : 20 - 11 ; 11 - 31
        //positions des parcelles
        Position position11 = new Position(1,1);
        Position position20 = new Position(2, 0);
        Position position31 = new Position(3, 1);
        Position position22 = new Position(2, 2);
        Position positionM11 = new Position(-1, 1);
        Position position02 = new Position(0, 2);
        //parcelles
        ParcelleCouleur PC11 = new ParcelleCouleur(position11, Couleur.JAUNE);
        ParcelleCouleur PC20 = new ParcelleCouleur(position20, Couleur.JAUNE);
        ParcelleCouleur PC31 = new ParcelleCouleur(position31, Couleur.JAUNE);
        ParcelleCouleur PC22 = new ParcelleCouleur(position22, Couleur.JAUNE);
        ParcelleCouleur PCm11 = new ParcelleCouleur(positionM11, Couleur.ROSE);
        ParcelleCouleur PC02 = new ParcelleCouleur(position02, Couleur.VERTE);
        //pose les parcelles
        plateau.poseParcelle(PC11);
        plateau.poseParcelle(PC20);
        plateau.poseParcelle(PC31);
        plateau.poseParcelle(PC22);
        plateau.poseParcelle(PCm11);
        plateau.poseParcelle(PC02);
        //pose les irrigations
        Irrigation irrigation1120 = new Irrigation();
        irrigation1120.addPosition(position11, position20);
        Irrigation irrigation1131 = new Irrigation();
        irrigation1131.addPosition(position11, position31);

        plateau.poseIrrigation(irrigation1120);
        plateau.poseIrrigation(irrigation1131);

        //état des bambous avant le déplacement
        Optional<Bambou> optionalBambou11 = GestionBambous.chercheBambou(plateau.getBambous(), position11);
        optionalBambou11.ifPresent(bambou -> assertEquals(1, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou20 = GestionBambous.chercheBambou(plateau.getBambous(), position20);
        optionalBambou20.ifPresent(bambou -> assertEquals(1, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou31 = GestionBambous.chercheBambou(plateau.getBambous(), position31);
        optionalBambou31.ifPresent(bambou -> assertEquals(1, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou22 = GestionBambous.chercheBambou(plateau.getBambous(), position22);
        assertTrue(optionalBambou22.isEmpty());

        Optional<Bambou> optionalBambouM11 = GestionBambous.chercheBambou(plateau.getBambous(), positionM11);
        optionalBambouM11.ifPresent(bambou -> assertEquals(1, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou02 = GestionBambous.chercheBambou(plateau.getBambous(), position02);
        assertTrue(optionalBambou02.isEmpty());

        //déplacement du jardinier
        assertEquals(new Position(), plateau.getJardinier().getPosition());
        plateau.deplacementJardinier(position11);

        //Jardinier est bien déplacé
        assertEquals(position11, plateau.getJardinier().getPosition());

        //état des bambous après le déplacement du jardinier
        Optional<Bambou> optionalBambou11_apres = GestionBambous.chercheBambou(plateau.getBambous(), position11);
        optionalBambou11_apres.ifPresent(bambou -> assertEquals(2, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou20_apres = GestionBambous.chercheBambou(plateau.getBambous(), position20);
        optionalBambou20_apres.ifPresent(bambou -> assertEquals(2, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou31_apres = GestionBambous.chercheBambou(plateau.getBambous(), position31);
        optionalBambou31_apres.ifPresent(bambou -> assertEquals(2, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou22_apres = GestionBambous.chercheBambou(plateau.getBambous(), position22);
        assertTrue(optionalBambou22_apres.isEmpty());

        Optional<Bambou> optionalBambouM11_apres = GestionBambous.chercheBambou(plateau.getBambous(), positionM11);
        optionalBambouM11_apres.ifPresent(bambou -> assertEquals(1, bambou.getTailleBambou()));

        Optional<Bambou> optionalBambou02_apres = GestionBambous.chercheBambou(plateau.getBambous(), position02);
        assertTrue(optionalBambou02_apres.isEmpty());
    }

}