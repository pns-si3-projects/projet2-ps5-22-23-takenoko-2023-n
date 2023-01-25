package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Représente la pioche de sections de bambou.
 * @author équipe N
 */
public class PiocheSectionBambou {
    // Définition des attributs

    private final Random random;
    private int nombreSectionsBambouVert;
    private int nombreSectionsBambouRose;
    private int nombreSectionsBambouJaune;


    // Définition des constructeurs

    /**
     * Construit une pioche de 90 sections de bambous
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheSectionBambou(@NotNull Random random) {
        this.random = random;
        nombreSectionsBambouVert = 36;
        nombreSectionsBambouRose = 24;
        nombreSectionsBambouJaune = 30;
    }


    // Accesseurs

    /**
     * Renvoie le nombre de sections de bambou de la couleur demandée
     * @param couleur la couleur demandée
     * @return le nombre de sections de bambous de la couleur demandée
     */
    public int getNombreSectionsBambou(@NotNull Couleur couleur) {
        return switch (couleur) {
            case VERT -> nombreSectionsBambouVert;
            case ROSE -> nombreSectionsBambouRose;
            case JAUNE -> nombreSectionsBambouJaune;
        };
    }

    /**
     * Renvoie si la pioche n'a plus de sections de bambou de la couleur demandée
     * @param couleur la couleur demandée
     * @return {@code true} si la pioche n'a plus de sections de bambou de la couleur demandée
     */
    public boolean isEmpty(@NotNull Couleur couleur) {
        return getNombreSectionsBambou(couleur) == 0;
    }


    // Méthodes d'utilisation

    /**
     * Renvoie une section de bambou de la couleur désignée
     * @return la section de bambou piochée
     * @implNote la pioche de la couleur demandée ne doit pas être vide
     */
    public SectionBambou pioche(@NotNull Couleur couleur) {
        if (isEmpty(couleur)) {
            throw new AssertionError("La pioche de sections de bambou est vide");
        }

        int taille = getNombreSectionsBambou(couleur);
        int positionBambou = random.nextInt(taille);
        if (positionBambou < 0 || positionBambou >= taille) {
            throw new ArithmeticException();
        }

        return retireSectionBambou(couleur);
    }

    private SectionBambou retireSectionBambou(Couleur couleur) {
        switch (couleur) {
            case VERT -> --nombreSectionsBambouVert;
            case ROSE -> --nombreSectionsBambouRose;
            case JAUNE -> --nombreSectionsBambouJaune;
        }
        return new SectionBambou(couleur);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche de bambous : " + nombreSectionsBambouVert + " verts, "
                + nombreSectionsBambouRose + " roses et " + nombreSectionsBambouJaune + " jaunes.";
    }
}
