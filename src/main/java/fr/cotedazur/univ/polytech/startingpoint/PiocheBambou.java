package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Random;

/**
 * Gère les pioches de bambous
 * @author équipe N
 */
public class PiocheBambou {
    // Définition des attributs
    private Random random;
    private int[] bambousList;


    /**
     * Constructeur par défaut, crée une pioche de 90 sections de bambous
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheBambou(Random random) {
        bambousList = new int[]{30, 24, 36};
        this.random = random;
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de sections de bambous que contient la pioche
     * @return le nombre de sections de bambous restantes dans la pioche
     */
    public int getNombreBambousRestants() {
        return bambousList[0] + bambousList[1] + bambousList[2];
    }

    /**
     * Renvoie si la pioche de bambous est vide
     * @return true la pioche est vide, false sinon
     */
    public boolean isEmpty() {
        return getNombreBambousRestants() == 0;
    }

    @Override
    public String toString() {
        return "Pioche de bambous : " + getNombreBambousRestants() + " sections.";
    }


    // Méthodes d'utilisation
    /**
     * Renvoie une section de bambou désignée dans la pioche
     * @return la section de bambou piochée
     * @implNote la pioche ne doit pas être vide
     */
    public SectionBambou pioche() {
        assert !isEmpty() : "La pioche de bambous est vide";
        int size = getNombreBambousRestants();
        int positionBambou = random.nextInt(size);
        if (positionBambou < 0 || positionBambou >= size) throw new RuntimeException();
        return prendSectionBambou(positionBambou);
    }

    /**
     * Cherche la section de bambou à créer, la renvoie et la retire de la pioche
     * @param position est la position du bambou entre tous les bambous restants de la pioche
     * @return la section de bambou désignée par la position
     * @implSpec la position doit être comprise entre 0 et "le nombre de bambous de la pioche - 1"
     */
    private SectionBambou prendSectionBambou(int position) {
        assert position>0 && position<getNombreBambousRestants() : "La position demandée dans la pioche est impossible";
        SectionBambou res = null;
        int somme = 0;
        for (int i=0; i<bambousList.length; i++) {
            somme += bambousList[i];
            if (position < somme) {
                bambousList[i]--;
                return creeSectionBambou(i);
            }
        }
        throw new IndexOutOfBoundsException("La position du bambou demandée est en dehors de la pioche");
    }

    /**
     * Crée la section de bambou désignée par la position et la renvoie
     * @param position est la position du bambou dans le tableau de la pioche
     * @return la section de bambou demandée
     * @implSpec la position doit être comprise entre 0 et 2
     */
    private SectionBambou creeSectionBambou(int position) {
        return switch (position) {
            case 0 -> new SectionBambou();
            case 1 -> new SectionBambou();
            case 2 -> new SectionBambou();
            default -> throw new IndexOutOfBoundsException("La section de bambou demandée est introuvable");
        };
    }
}
