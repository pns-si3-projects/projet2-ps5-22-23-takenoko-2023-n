package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Random;

/**
 * Gère la pioche des cartes objectif de panda
 * @author équipe N
 */
public class PiocheObjectifPanda {
    // Définition des attributs
    private Random random;
    private int[] objectifPandaList;


    // Définition des constructeurs
    /**
     * Constructeur par défaut, crée la pioche des objectifs de panda
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheObjectifPanda(Random random) {
        objectifPandaList = new int[4];
        creePiocheObjectifsPanda();
        this.random = random;
    }

    private void creePiocheObjectifsPanda() {
        objectifPandaList[0] = 5;
        objectifPandaList[1] = 4;
        objectifPandaList[2] = 3;
        objectifPandaList[3] = 3;
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de cartes que contient la pioche
     * @return le nombre de cartes dans la pioche
     */
    public int getNombreObjectifs() {
        return objectifPandaList[0] + objectifPandaList[1] + objectifPandaList[2] + objectifPandaList[3];
    }

    @Override
    public String toString() {
        return "Pioche d'objectifs de panda : " + getNombreObjectifs() + " cartes.";
    }


    // Méthodes d'utilisation
    /**
     * Renvoie une carte objectif désignée dans la pioche
     * @return la carte objectif piochée
     * @implNote La pioche ne doit pas être vide
     */
    public Objectif pioche() {
        assert getNombreObjectifs()>0 : "La pioche d'objectifs de panda est vide";
        int positionCarte = random.nextInt(getNombreObjectifs());
        if (positionCarte < 0 || positionCarte >= getNombreObjectifs()) throw new IndexOutOfBoundsException("La position demandée dans la pioche est impossible");
        return prendCarteObjectifPanda(positionCarte);
    }

    private ObjectifPanda prendCarteObjectifPanda(int position) {
        assert position>0 && position<getNombreObjectifs() : "La position demandée dans la pioche est impossible";
        ObjectifPanda res = null;
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

    private ObjectifPanda creeCarteObjectifPanda(int position) {
        return switch (position) {
            case 0 -> new ObjectifPanda(3, 2);
            case 1 -> new ObjectifPanda(4, 2);
            case 2 -> new ObjectifPanda(5, 2);
            case 3 -> new ObjectifPanda(6, 3);
            default -> throw new IndexOutOfBoundsException("La carte ObjectifPanda demandée est introuvable");
        };
    }
}
