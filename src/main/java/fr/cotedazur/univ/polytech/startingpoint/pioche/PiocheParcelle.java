package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Représente la pioche de parcelles.
 * @author équipe N
 */
public class PiocheParcelle extends ArrayList<ParcellePioche> {
    // Définition des attributs

    private static final String ERREUR_RANDOM = "Erreur objet Random";
    private final Random random;
    private Optional<ParcellePioche[]> parcellesPiochees;


    /**
     * Construit la pioche de parcelles
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheParcelle(@NotNull Random random) {
        creePiocheParcelles();
        this.random = random;
        parcellesPiochees = Optional.empty();
    }

    /**
     * Initialise la pioche en lui ajoutant les parcelles des différentes couleurs
     */
    private void creePiocheParcelles() {
        creeParcellesVertesPioche();
        creeParcellesRosesPioche();
        creeParcellesJaunesPioche();
    }

    /**
     * Ajoute les parcelles vertes à la pioche
     */
    private void creeParcellesVertesPioche() {
        Couleur vert = Couleur.VERT;
        for (int i=0; i<6; i++) {
            add(new ParcellePioche(vert));
        }
        // avec futur aménagement bassin
        add(new ParcellePioche(vert));
        add(new ParcellePioche(vert));
        // avec futur aménagement enclos
        add(new ParcellePioche(vert));
        add(new ParcellePioche(vert));
        // avec futur aménagement engrais
        add(new ParcellePioche(vert));
    }

    /**
     * Ajoute les parcelles roses à la pioche
     */
    private void creeParcellesRosesPioche() {
        Couleur rose = Couleur.ROSE;
        for (int i=0; i<4; i++) {
            add(new ParcellePioche(rose));
        }
        // avec futur aménagement bassin
        add(new ParcellePioche(rose));
        // avec futur aménagement enclos
        add(new ParcellePioche(rose));
        // avec futur aménagement engrais
        add(new ParcellePioche(rose));
    }

    /**
     * Ajoute les parcelles jaunes à la pioche
     */
    private void creeParcellesJaunesPioche() {
        Couleur jaune = Couleur.JAUNE;
        for (int i=0; i<6; i++) {
            add(new ParcellePioche(jaune));
        }
        // avec futur aménagement bassin
        add(new ParcellePioche(jaune));
        // avec futur aménagement enclos
        add(new ParcellePioche(jaune));
        // avec futur aménagement engrais
        add(new ParcellePioche(jaune));
    }


    // Accesseurs
    /**
     * Renvoie le nombre de parcelles que contient la pioche
     * @return le nombre de parcelles restantes dans la pioche
     */
    public int getNombreParcellesRestantes() {
        return size();
    }

    /**
     * Renvoie si la pioche de parcelles est vide
     * @return {@code true} si la pioche est vide
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    // Méthodes d'utilisation

    /**
     * Renvoie 3 parcelles désignées dans la pioche
     * @return un tableau de 3 parcelles piochées
     * @throws PiocheParcelleEnCoursException si une pioche de parcelles est déjà en cours
     * @implNote si {@code pioche <= 3} parcelles, elle renvoie 3 fois la même parcelle
     *      et la pioche ne doit pas être vide
     */
    public ParcellePioche[] pioche() throws PiocheParcelleEnCoursException {
        assert !isEmpty() : "La pioche de bambous est vide";
        if (parcellesPiochees.isPresent()) throw new PiocheParcelleEnCoursException();
        ParcellePioche[] parcelles;
        int size = getNombreParcellesRestantes();
        if (size <= 3) {
            int positionParcelle = random.nextInt(size);
            if (positionParcelle < 0 || positionParcelle >= size) throw new ArithmeticException(ERREUR_RANDOM);
            parcelles = new ParcellePioche[]{get(positionParcelle), get(positionParcelle), get(positionParcelle)};
            parcellesPiochees = Optional.of(parcelles);
            return parcelles;
        }
        int[] positions = random3Parcelles();
        parcelles = new ParcellePioche[]{get(positions[0]), get(positions[1]), get(positions[2])};
        parcellesPiochees = Optional.of(parcelles);
        return parcelles;
    }

    /**
     * Renvoie un tableau de 3 positions de parcelle différentes, choisies aléatoirement dans la pioche
     * @return un tableau de 3 positions de parcelle piochées
     */
    private int[] random3Parcelles() {
        int size = getNombreParcellesRestantes();
        int positionParcelle1 = random.nextInt(size);
        if (positionParcelle1 < 0 || positionParcelle1 >= size) throw new ArithmeticException(ERREUR_RANDOM);

        // Boucle pour positionParcelle2
        int positionParcelle2;
        do {
            positionParcelle2 = random.nextInt(size);
            if (positionParcelle2 < 0 || positionParcelle2 >= size) throw new ArithmeticException(ERREUR_RANDOM);
        } while (positionParcelle1 == positionParcelle2);

        // Boucle pour positionParcelle3
        int positionParcelle3;
        do {
            positionParcelle3 = random.nextInt(size);
            if (positionParcelle3 < 0 || positionParcelle3 >= size) throw new ArithmeticException(ERREUR_RANDOM);
        } while (positionParcelle1 == positionParcelle3 || positionParcelle2 == positionParcelle3);

        return new int[]{positionParcelle1, positionParcelle2, positionParcelle3};
    }

    /**
     * Renvoie une ParcelleCouleur créée en fonction de la parcelle choisie
     * @return la parcelle piochée avec la position demandée (ParcelleCouleur)
     * @throws PiocheParcelleVideException si aucune pioche n'a été effectuée, mais une parcelle est choisie
     */
    public ParcelleCouleur choisiParcelle(ParcellePioche parcelleChoisie, Position position) throws PiocheParcelleVideException {
        if (parcellesPiochees.isEmpty()) throw new PiocheParcelleVideException();
        ParcellePioche[] piochees = parcellesPiochees.get();
        if (piochees[0].equals(parcelleChoisie) || piochees[1].equals(parcelleChoisie) || piochees[2].equals(parcelleChoisie)) {
            parcellesPiochees = Optional.empty();
            remove(parcelleChoisie);
            return new ParcelleCouleur(position, parcelleChoisie.couleur());
        }
        throw new IllegalArgumentException("La parcelle donnée ne correspond à aucune donnée lors de la pioche");
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche de parcelles : " + getNombreParcellesRestantes() + " parcelles.";
    }
}
