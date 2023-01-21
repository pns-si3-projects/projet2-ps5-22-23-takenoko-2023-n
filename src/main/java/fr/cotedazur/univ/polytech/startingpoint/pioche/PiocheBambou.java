package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.SectionBambou;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Représente la pioche de sections de bambou.
 * @author équipe N
 */
public class PiocheBambou {
    // Définition des attributs

    private int nombreBambousVerts;
    private int nombreBambousRoses;
    private int nombreBambousJaunes;
    private final Random random;


    // Définition des constructeurs

    /**
     * Construit une pioche de 90 sections de bambous
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheBambou(@NotNull Random random) {
        nombreBambousVerts = 36;
        nombreBambousRoses = 24;
        nombreBambousJaunes = 30;
        this.random = random;
    }


    // Accesseurs

    /**
     * Renvoie le nombre de sections de bambous verts que contient la pioche
     * @return le nombre de sections de bambous verts restantes dans la pioche
     */
    public int getNombreBambousVertsRestants() {
        return nombreBambousVerts;
    }

    /**
     * Renvoie le nombre de sections de bambous roses que contient la pioche
     * @return le nombre de sections de bambous roses restantes dans la pioche
     */
    public int getNombreBambousRosesRestants() {
        return nombreBambousRoses;
    }

    /**
     * Renvoie le nombre de sections de bambous jaunes que contient la pioche
     * @return le nombre de sections de bambous jaunes restantes dans la pioche
     */
    public int getNombreBambousJaunesRestants() {
        return nombreBambousJaunes;
    }

    /**
     * Renvoie si la pioche de bambous verts est vide
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmptyBambousVerts() {
        return nombreBambousVerts == 0;
    }

    /**
     * Renvoie si la pioche de bambous roses est vide
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmptyBambousRoses() {
        return nombreBambousRoses == 0;
    }

    /**
     * Renvoie si la pioche de bambous verts est vide
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmptyBambousJaunes() {
        return nombreBambousJaunes == 0;
    }


    // Méthodes d'utilisation

    /**
     * Renvoie une section de bambou vert désignée dans la pioche
     * @return la section de bambou piochée
     * @implNote la pioche ne doit pas être vide
     */
    public SectionBambou piocheSectionBambouVert() {
        assert !isEmptyBambousVerts() : "La pioche de bambous verts est vide";
        int positionBambou = random.nextInt(nombreBambousVerts);
        if (positionBambou < 0 || positionBambou >= nombreBambousVerts) throw new ArithmeticException();
        nombreBambousVerts--;
        return new SectionBambou(Couleur.VERT);
    }

    /**
     * Renvoie une section de bambou rose désignée dans la pioche
     * @return la section de bambou piochée
     * @implNote la pioche ne doit pas être vide
     */
    public SectionBambou piocheSectionBambouRose() {
        assert !isEmptyBambousRoses() : "La pioche de bambous roses est vide";
        int positionBambou = random.nextInt(nombreBambousRoses);
        if (positionBambou < 0 || positionBambou >= nombreBambousRoses) throw new ArithmeticException();
        nombreBambousRoses--;
        return new SectionBambou(Couleur.ROSE);
    }

    /**
     * Renvoie une section de bambou jaune désignée dans la pioche
     * @return la section de bambou piochée
     * @implNote la pioche ne doit pas être vide
     */
    public SectionBambou piocheSectionBambouJaune() {
        assert !isEmptyBambousJaunes() : "La pioche de bambous jaunes est vide";
        int positionBambou = random.nextInt(nombreBambousJaunes);
        if (positionBambou < 0 || positionBambou >= nombreBambousJaunes) throw new ArithmeticException();
        nombreBambousJaunes--;
        return new SectionBambou(Couleur.JAUNE);
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche de bambous : " + nombreBambousVerts + " verts, "
                + nombreBambousRoses + " roses et " + nombreBambousJaunes + " jaunes.";
    }
}
