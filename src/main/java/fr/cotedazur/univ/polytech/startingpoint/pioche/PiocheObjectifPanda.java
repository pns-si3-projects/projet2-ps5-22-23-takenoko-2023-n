package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
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
            objectifPandaList.add(new ObjectifPanda(3, 2, Couleur.VERT));
        }
        for (int i=0; i<4; i++) { // les 4 objectifs de panda jaune
            objectifPandaList.add(new ObjectifPanda(4, 2, Couleur.JAUNE));
        }
        for (int i=0; i<3; i++) { // les 3 objectifs de panda rose
            objectifPandaList.add(new ObjectifPanda(5, 2, Couleur.ROSE));
        }
        for (int i=0; i<3; i++) { // les 3 objectifs de panda des trois couleurs (à faire)
            objectifPandaList.add(new ObjectifPanda(6, 3, Couleur.VERT));
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
        assert !isEmpty() : "La pioche d'objectifs de panda est vide";

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
        return "Pioche d'objectifs de panda : " + getNombreObjectifsRestants() + " cartes.";
    }
}
