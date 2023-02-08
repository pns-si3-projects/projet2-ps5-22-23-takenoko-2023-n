package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.AfficheurObjectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifJardinier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente la pioche des objectifs de jardinier.
 * @author équipe N
 */
public class PiocheObjectifJardinier implements PiocheObjectifInterface {
    // Définition des attributs

    private final List<ObjectifJardinier> objectifJardinierList;
    private final Random random;


    // Définition des constructeurs

    /**
     * Construit la pioche d'objectifs de jardinier
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheObjectifJardinier(@NotNull Random random) {
        objectifJardinierList = new ArrayList<>(15);
        creePiocheObjectifsJardinier();

        this.random = random;
    }

    /**
     * Initialise la pioche en lui ajoutant les objectifs de jardinier
     */
    private void creePiocheObjectifsJardinier() {
        Couleur verte = Couleur.VERTE;
        Couleur rose = Couleur.ROSE;
        Couleur jaune = Couleur.JAUNE;
        objectifJardinierList.add(new ObjectifJardinier(5, 4, jaune));
        objectifJardinierList.add(new ObjectifJardinier(6, 4, jaune));
        objectifJardinierList.add(new ObjectifJardinier(6, 6, rose));
        objectifJardinierList.add(new ObjectifJardinier(7, 9, jaune));
        objectifJardinierList.add(new ObjectifJardinier(8, 12, verte));
        objectifJardinierList.add(new ObjectifJardinier(4, 4, verte));
        objectifJardinierList.add(new ObjectifJardinier(4, 4, verte));
        objectifJardinierList.add(new ObjectifJardinier(5, 4, verte));
        objectifJardinierList.add(new ObjectifJardinier(4, 4, jaune));
        objectifJardinierList.add(new ObjectifJardinier(5, 4, jaune));
        objectifJardinierList.add(new ObjectifJardinier(5, 4, rose));
        objectifJardinierList.add(new ObjectifJardinier(6, 4, rose));
        objectifJardinierList.add(new ObjectifJardinier(6, 4, rose));
        objectifJardinierList.add(new ObjectifJardinier(7, 4, rose));
        objectifJardinierList.add(new ObjectifJardinier(3, 4, verte));
    }


    // Accesseurs

    @Override
    public int getNombreObjectifsRestants() {
        return objectifJardinierList.size();
    }

    @Override
    public boolean isEmpty() {
        return objectifJardinierList.isEmpty();
    }


    // Méthodes d'utilisation

    @Override
    public Objectif pioche() {
        if (isEmpty()) {
            throw new AssertionError("La pioche d'objectifs de jardinier est vide");
        }

        int size = getNombreObjectifsRestants();
        int positionCarte = random.nextInt(size);
        if (positionCarte < 0 || positionCarte >= size) {
            throw new ArithmeticException("Erreur objet random");
        }

        AfficheurPioche.piocheObjectif(objectifJardinierList.get(positionCarte));
        return objectifJardinierList.remove(positionCarte);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'objectifs de jardinier : " + getNombreObjectifsRestants() + " objectifs";
    }
}
