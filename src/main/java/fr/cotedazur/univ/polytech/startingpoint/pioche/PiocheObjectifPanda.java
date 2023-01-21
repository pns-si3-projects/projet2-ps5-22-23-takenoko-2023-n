package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Random;

/**
 * Représente la pioche des objectifs de panda.
 * @author équipe N
 */
public class PiocheObjectifPanda implements PiocheObjectifInterface {
    // Définition des attributs

    private final Random random;
    private final int[] objectifPandaList;


    // Définition des constructeurs

    /**
     * Construit la pioche d'objectifs de panda
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheObjectifPanda(@NotNull Random random) {
        objectifPandaList = new int[]{5, 4, 3, 3};
        this.random = random;
    }


    // Accesseurs

    @Override
    public int getNombreObjectifsRestants() {
        return objectifPandaList[0] + objectifPandaList[1] + objectifPandaList[2] + objectifPandaList[3];
    }

    @Override
    public boolean isEmpty() {
        return getNombreObjectifsRestants() == 0;
    }


    // Méthodes d'utilisation

    @Override
    public Objectif pioche() {
        assert !isEmpty() : "La pioche d'objectifs de panda est vide";
        int size = getNombreObjectifsRestants();
        int positionCarte = random.nextInt(size);
        if (positionCarte < 0 || positionCarte >= size) throw new ArithmeticException("Erreur objet random");
        return prendCarteObjectifPanda(positionCarte);
    }

    /**
     * Cherche l'objectif de panda à créer, le renvoie et le retire de la pioche
     * @param position la position de l'objectif de panda entre toutes les cartes restantes de la pioche
     * @return l'objectif de panda désigné par la position
     * @implSpec la position doit être comprise entre 0 et "le nombre de cartes de la pioche - 1"
     */
    private ObjectifPanda prendCarteObjectifPanda(int position) {
        assert position>=0 && position<getNombreObjectifsRestants() : "La position demandée dans la pioche est impossible";
        int somme = 0;
        for (int i=0; i<objectifPandaList.length; i++) {
            somme += objectifPandaList[i];
            if (position < somme) {
                objectifPandaList[i]--;
                return creeCarteObjectifPanda(i);
            }
        }
        throw new IndexOutOfBoundsException("La position de la carte demandée est en dehors de la pioche");
    }

    /**
     * Crée l'objectif de panda désigné par la position et le renvoie
     * @param indice la position de l'objectif de panda dans le tableau de la pioche
     * @return l'objectif de panda demandé
     */
    private ObjectifPanda creeCarteObjectifPanda(@Range(from = 0, to = 3) int indice) {
        return switch (indice) {
            case 0 -> new ObjectifPanda(3, 2, Couleur.VERT);
            case 1 -> new ObjectifPanda(4, 2, Couleur.VERT);
            case 2 -> new ObjectifPanda(5, 2, Couleur.ROSE);
            case 3 -> new ObjectifPanda(6, 3,Couleur.JAUNE);
            default -> throw new IndexOutOfBoundsException("L'objectif de panda demandé est introuvable");
        };
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche d'objectifs de panda : " + getNombreObjectifsRestants() + " cartes.";
    }
}
