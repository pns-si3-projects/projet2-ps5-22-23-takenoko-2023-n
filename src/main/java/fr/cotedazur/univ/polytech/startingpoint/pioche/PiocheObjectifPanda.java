package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente la pioche des objectifs de panda.
 * @author équipe N
 */
public class PiocheObjectifPanda implements PiocheObjectifInterface {
    // Définition des attributs

    private final List<ObjectifPanda> objectifPandaList;
    private final Random random;


    // Définition des constructeurs

    /**
     * Construit la pioche d'objectifs de panda
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheObjectifPanda(@NotNull Random random) {
        objectifPandaList = new ArrayList<>(15);
        creePiocheObjectifsPanda();

        this.random = random;
    }

    /**
     * Initialise la pioche en lui ajoutant les objectifs de panda
     */
    private void creePiocheObjectifsPanda() {

        for (int i=0; i<5; i++) { // les 5 objectifs de panda vert
            List<SectionBambou> bambousAManger = new ArrayList<>();
            for (int j=0; j < 2; j++){
                bambousAManger.add(new SectionBambou(Couleur.VERTE));
            }
            objectifPandaList.add(new ObjectifPanda(3, bambousAManger));
        }

        for (int i=0; i<4; i++) { // les 4 objectifs de panda jaune
            List<SectionBambou> bambousAManger = new ArrayList<>();
            for (int j=0; j < 2;j++){
                bambousAManger.add(new SectionBambou(Couleur.JAUNE));
            }
            objectifPandaList.add(new ObjectifPanda(4, bambousAManger));
        }

        for (int i=0; i<3; i++) { // les 3 objectifs de panda rose
            List<SectionBambou> bambousAManger = new ArrayList<>();
            for (int j=0; j < 2;j++){
                bambousAManger.add(new SectionBambou(Couleur.ROSE));
            }
            objectifPandaList.add(new ObjectifPanda(5, bambousAManger));
        }

        for (int i=0; i<3; i++) { // les 3 objectifs de panda des trois couleurs
            List<SectionBambou> bambousAManger = new ArrayList<>();
            bambousAManger.add(new SectionBambou(Couleur.VERTE));
            bambousAManger.add(new SectionBambou(Couleur.JAUNE));
            bambousAManger.add(new SectionBambou(Couleur.ROSE));
            objectifPandaList.add(new ObjectifPanda(6, bambousAManger));
        }
    }


    // Accesseurs

    @Override
    public int getNombreObjectifsRestants() {
        return objectifPandaList.size();
    }

    @Override
    public boolean isEmpty() {
        return objectifPandaList.isEmpty();
    }


    // Méthodes d'utilisation

    @Override
    public Objectif pioche() {
        if (isEmpty()) {
            throw new AssertionError("La pioche d'objectifs de panda est vide");
        }

        int size = getNombreObjectifsRestants();
        int positionCarte = random.nextInt(size);
        if (positionCarte < 0 || positionCarte >= size) {
            throw new ArithmeticException("Erreur objet random");
        }

        return objectifPandaList.remove(positionCarte);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'objectifs de panda : " + getNombreObjectifsRestants() + " objectifs";
    }
}
