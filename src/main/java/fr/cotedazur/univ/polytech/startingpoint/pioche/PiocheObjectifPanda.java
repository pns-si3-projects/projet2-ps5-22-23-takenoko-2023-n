package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.objectif.Objectif;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifPanda;

import java.util.Random;

/**
 * Classe permettant de gérer la pioche des cartes objectif de panda
 * @author équipe N
 */
public class PiocheObjectifPanda implements PiocheObjectifInterface {
    // Définition des attributs
    private final Random random;
    private final int[] objectifPandaList;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheObjectifPanda(Random random) {
        objectifPandaList = new int[]{5, 4, 3, 3};
        this.random = random;
    }


    // Accesseurs et méthode toString
    @Override
    public int getNombreObjectifsRestants() {
        return objectifPandaList[0] + objectifPandaList[1] + objectifPandaList[2] + objectifPandaList[3];
    }

    @Override
    public boolean isEmpty() {
        return getNombreObjectifsRestants() == 0;
    }

    @Override
    public String toString() {
        return "Pioche d'objectifs de panda : " + getNombreObjectifsRestants() + " cartes.";
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
     * Cherche l'objectifPanda à créer, le renvoie et le retire de la pioche
     * @param position est la position de l'objectifPanda entre toutes les cartes restantes de la pioche
     * @return l'objectifPanda désigné par la position
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
     * Crée l'objectifPanda désigné par la position et le renvoie
     * @param indice est la position de la carte objectifPanda dans le tableau de la pioche
     * @return la carte objectifPanda demandée
     * @implSpec la position doit être comprise entre 0 et 3
     */
    private ObjectifPanda creeCarteObjectifPanda(int indice) {
        return switch (indice) {
            case 0 -> new ObjectifPanda(3, 2);
            case 1 -> new ObjectifPanda(4, 2);
            case 2 -> new ObjectifPanda(5, 2);
            case 3 -> new ObjectifPanda(6, 3);
            default -> throw new IndexOutOfBoundsException("La carte ObjectifPanda demandée est introuvable");
        };
    }
}
