package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente la pioche des objectifs de parcelles.
 * @author équipe N
 */
public class PiocheObjectifParcelle implements PiocheObjectifInterface {
    // Définition des attributs

    private final List<ObjectifParcelle> objectifParcelleList;
    private final Random random;


    // Définition des constructeurs

    /**
     * Construit la pioche d'objectifs de parcelles
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheObjectifParcelle(@NotNull Random random) {
        objectifParcelleList = new ArrayList<>(15);
        creePiocheObjectifsParcelle();

        this.random = random;
    }

    /**
     * Initialise la pioche en lui ajoutant les objectifs de parcelles
     */
    private void creePiocheObjectifsParcelle() {
        objectifParcelleList.add(new ObjectifParcelle(2, 3));
        objectifParcelleList.add(new ObjectifParcelle(3, 4));
        objectifParcelleList.add(new ObjectifParcelle(5, 4));
        objectifParcelleList.add(new ObjectifParcelle(4, 4));
        objectifParcelleList.add(new ObjectifParcelle(3, 4));
        objectifParcelleList.add(new ObjectifParcelle(3, 3));
        objectifParcelleList.add(new ObjectifParcelle(4, 4));
        objectifParcelleList.add(new ObjectifParcelle(3, 3));
        objectifParcelleList.add(new ObjectifParcelle(2, 3));
        objectifParcelleList.add(new ObjectifParcelle(2, 3));
        objectifParcelleList.add(new ObjectifParcelle(4, 3));
        objectifParcelleList.add(new ObjectifParcelle(4, 3));
        objectifParcelleList.add(new ObjectifParcelle(5, 4));
        objectifParcelleList.add(new ObjectifParcelle(4, 3));
        objectifParcelleList.add(new ObjectifParcelle(3, 3));
    }


    // Accesseurs

    @Override
    public int getNombreObjectifsRestants() {
        return objectifParcelleList.size();
    }

    @Override
    public boolean isEmpty() {
        return objectifParcelleList.isEmpty();
    }


    // Méthodes d'utilisation

    @Override
    public ObjectifParcelle pioche() {
        assert !isEmpty() : "La pioche d'objectifs de parcelles est vide";

        int taille = getNombreObjectifsRestants();
        int positionCarte = random.nextInt(taille);
        if (positionCarte < 0 || positionCarte >= taille) {
            throw new ArithmeticException("Erreur objet random");
        }

        return objectifParcelleList.remove(positionCarte);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'objectifs de parcelles : " + getNombreObjectifsRestants() + " cartes.";
    }
}
