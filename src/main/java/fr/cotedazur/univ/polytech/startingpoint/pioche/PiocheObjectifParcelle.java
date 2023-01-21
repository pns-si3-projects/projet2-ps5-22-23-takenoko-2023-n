package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

/**
 * Représente la pioche des objectifs de parcelles.
 * @author équipe N
 */
public class PiocheObjectifParcelle extends ArrayList<ObjectifParcelle> implements PiocheObjectifInterface {
    // Définition des attributs

    private final Random random;


    // Définition des constructeurs

    /**
     * Construit la pioche d'objectifs de parcelles
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheObjectifParcelle(@NotNull Random random) {
        creePiocheObjectifsParcelle();
        this.random = random;
    }

    /**
     * Initialise la pioche en lui ajoutant les objectifs de parcelles
     */
    private void creePiocheObjectifsParcelle() {
        add(new ObjectifParcelle(2, 3));
        add(new ObjectifParcelle(3, 4));
        add(new ObjectifParcelle(5, 4));
        add(new ObjectifParcelle(4, 4));
        add(new ObjectifParcelle(3, 4));
        add(new ObjectifParcelle(3, 3));
        add(new ObjectifParcelle(4, 4));
        add(new ObjectifParcelle(3, 3));
        add(new ObjectifParcelle(2, 3));
        add(new ObjectifParcelle(2, 3));
        add(new ObjectifParcelle(4, 3));
        add(new ObjectifParcelle(4, 3));
        add(new ObjectifParcelle(5, 4));
        add(new ObjectifParcelle(4, 3));
        add(new ObjectifParcelle(3, 3));
    }


    // Accesseurs

    @Override
    public int getNombreObjectifsRestants() {
        return size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    // Méthodes d'utilisation

    @Override
    public ObjectifParcelle pioche() {
        assert !isEmpty() : "La pioche d'objectifs de parcelles est vide";
        int positionCarte = random.nextInt(size());
        if (positionCarte < 0 || positionCarte >= size()) throw new ArithmeticException("Erreur objet random");
        return remove(positionCarte);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'objectifs de parcelles : " + size() + " cartes.";
    }
}
