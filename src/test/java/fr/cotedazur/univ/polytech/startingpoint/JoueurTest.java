package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JoueurTest {
    Plateau plateau;
    Random random;
    PiocheObjectifParcelle piocheObjectifParcelle;
    PiocheObjectifPanda piocheObjectifPanda;
    PiocheObjectifJardinier piocheObjectifJardinier;
    PiocheObjectif piocheObjectif;
    ObjectifParcelle objectifParcellePiocheJoueur1;
    ObjectifParcelle objectifParcellePiocheJoueur2;

    Joueur joueur1;
    Joueur joueur2;

    @Mock
    Random mockRandom = mock(Random.class);

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        random = new Random();
        piocheObjectifParcelle = new PiocheObjectifParcelle(mockRandom);
        piocheObjectifPanda = new PiocheObjectifPanda(random);
        piocheObjectifJardinier = new PiocheObjectifJardinier(random);
        piocheObjectif = new PiocheObjectif(piocheObjectifParcelle, piocheObjectifPanda, piocheObjectifJardinier);
        objectifParcellePiocheJoueur1 = piocheObjectif.piocheObjectifParcelle();
        objectifParcellePiocheJoueur2 = piocheObjectif.piocheObjectifParcelle();
        objectifPandaPiocheJoueur1 = piocheObjectif.piocheObjectifPanda();
        objectifPandaPiocheJoueur2 = piocheObjectif.piocheObjectifPanda();
        objectifJardinierPiocheJoueur1 = piocheObjectif.piocheObjectifJardinier();
        objectifJardinierPiocheJoueur2 = piocheObjectif.piocheObjectifJardinier();
        joueur1 = new Joueur("Robot1",mockRandom, objectifParcellePiocheJoueur1, objectifPandaPiocheJoueur1, objectifJardinierPiocheJoueur1);
        joueur2 = new Joueur("Robot2",mockRandom, objectifParcellePiocheJoueur2, objectifPandaPiocheJoueur2, objectifJardinierPiocheJoueur2);
    }

    @Test
    void getNom() {
        assertEquals("Robot1", joueur1.getNom());
        assertEquals("Robot2", joueur2.getNom());
    }

    @Test
    void getPlaquette(){
        Plaquette plaquetteJoueur1 = joueur1.getPlaquette();
        assertEquals(objectifParcellePiocheJoueur1, plaquetteJoueur1.getObjectifsParcelle()[0]);

        Plaquette plaquetteJoueur2 = joueur2.getPlaquette();
        assertEquals(objectifParcellePiocheJoueur2,plaquetteJoueur2.getObjectifsParcelle()[0]);
    }

    @Test
    void choisiParcellePlateauSansException(){
        when(mockRandom.nextInt(anyInt())).thenReturn(2,2,4);
        ParcelleCouleur[] listParcellesCouleursChoisis = new ParcelleCouleur[3];
        ParcelleCouleur parcelleCouleur1m1 = new ParcelleCouleur(new Position(1,-1));
        ParcelleCouleur parcelleCouleurm1m1 = new ParcelleCouleur(new Position(-1,-1));
        ParcelleCouleur parcelle0m2 = new ParcelleCouleur(new Position(0,-2));
        listParcellesCouleursChoisis[0] = parcelleCouleur1m1;
        listParcellesCouleursChoisis[1] = parcelleCouleurm1m1;
        listParcellesCouleursChoisis[2] = parcelle0m2;

        for(int i = 0;i < listParcellesCouleursChoisis.length;i++){
            ParcelleCouleur parcelleCouleurAAdd = joueur1.choisiParcellePlateau(plateau.getPositionsDisponible());
            try{
                plateau.addParcelle(parcelleCouleurAAdd);
            }
            catch (ParcelleExistanteException | NombreParcelleVoisineException exception){
                assert false: "Ne doit pas renvoyer d'erreur d'ajout au plateau";
            }
        }
    }

    @Test
    void choisiParcellePlateauAvecRandomTropGrand(){
        Position[] listPositionDisponible = plateau.getPositionsDisponible();
        when(mockRandom.nextInt(anyInt())).thenReturn(listPositionDisponible.length);
        assertThrows(ArithmeticException.class, () -> {joueur2.choisiParcellePlateau(listPositionDisponible);});
    }

    @Test
    void choisiParcellePlateauAvecRandomTropPetit(){
        Position[] listPositionDisponible = plateau.getPositionsDisponible();
        when(mockRandom.nextInt(anyInt())).thenReturn(-1);
        assertThrows(ArithmeticException.class, () -> {joueur2.choisiParcellePlateau(listPositionDisponible);});
    }

    @Test
    void addParcellePlateauTrue(){
        ParcelleCouleur parcelleCouleurChoisiJoueur1 = joueur1.choisiParcellePlateau(plateau.getPositionsDisponible());
        assertTrue(joueur1.addParcellePlateau(plateau,parcelleCouleurChoisiJoueur1));

        ParcelleCouleur parcelleCouleurChoisiJoueur2 = joueur2.choisiParcellePlateau(plateau.getPositionsDisponible());
        assertTrue(joueur2.addParcellePlateau(plateau,parcelleCouleurChoisiJoueur2));
    }

    @Test
    void addParcellePlateauFalse(){
        ParcelleCouleur parcelleCouleurNonPossibleAAdd = new ParcelleCouleur(new Position(10,10));
        assertFalse(joueur1.addParcellePlateau(plateau,parcelleCouleurNonPossibleAAdd));
        assertFalse(joueur2.addParcellePlateau(plateau,parcelleCouleurNonPossibleAAdd));
    }

    @Test
    void piocheObjectifParcelleRempli(){
        when(mockRandom.nextInt(anyInt())).thenReturn(10,2);
        ObjectifParcelle objectifParcelleAPiocher = piocheObjectifParcelle.get(10);
        assertEquals(Optional.of(objectifParcelleAPiocher),joueur1.piocheObjectifParcelle(piocheObjectif));

        ObjectifParcelle objectifParcelleAPiocher2 = piocheObjectifParcelle.get(2);
        assertEquals(Optional.of(objectifParcelleAPiocher2),joueur2.piocheObjectifParcelle(piocheObjectif));
    }

    @Test
    void piocheObjectifParcelleVide(){
        for(int i = 0;i < 13;i++){
            piocheObjectif.piocheObjectifParcelle();
        }
        assertEquals(Optional.empty(),joueur1.piocheObjectifParcelle(piocheObjectif));
        assertEquals(Optional.empty(),joueur2.piocheObjectifParcelle(piocheObjectif));
    }

    @Test
    void tourDeJeu(){
        Arbitre arbitre = new Arbitre();
        while (arbitre.checkFinDeJeu(joueur1,joueur2)){
            joueur1.tour(piocheObjectif,plateau,arbitre);
            joueur2.tour(piocheObjectif,plateau,arbitre);
        }
        if(joueur1.getPoint() > joueur2.getPoint()){
            assertEquals(Optional.of(joueur1),arbitre.joueurGagnant(joueur1,joueur2));
        }
        else if(joueur2.getPoint() < joueur2.getPoint()){
            assertEquals(Optional.of(joueur2),arbitre.joueurGagnant(joueur1,joueur2));
        }
        else{
            assertEquals(Optional.empty(),arbitre.joueurGagnant(joueur1,joueur2));
        }
    }

    @Test
    void getPoint(){
        Arbitre arbitre = new Arbitre();
        assertEquals(0,joueur1.getPoint());
        ObjectifParcelle premierObjectifJoueur1 = joueur1.getPlaquette().getObjectifsParcelle()[0];
        while(joueur1.getPoint() == 0){
            joueur1.tour(piocheObjectif,plateau,arbitre);
        }
        assertEquals(premierObjectifJoueur1.getNombrePoints(),joueur1.getPoint());

        ObjectifParcelle premierObjectifJoueur2 = joueur2.getPlaquette().getObjectifsParcelle()[0];
        while(joueur2.getPoint() == 0){
            joueur2.tour(piocheObjectif,plateau,arbitre);
        }
        assertEquals(premierObjectifJoueur2.getNombrePoints(),joueur2.getPoint());
    }
}