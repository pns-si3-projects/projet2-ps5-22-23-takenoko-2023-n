package fr.cotedazur.univ.polytech.startingpoint.pioche;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente la pioche de parcelles.
 * @author équipe N
 */
public class PiocheParcelle {
    // Définition des attributs

    private static final String ERREUR_RANDOM = "Erreur objet Random";
    private final List<ParcellePioche> parcellePiocheList;
    private final Random random;
    private ParcellesPiochees parcellesPiochees;


    // Définition des constructeurs

    /**
     * Construit la pioche de parcelles
     * @param random un objet Random pour l'aléatoire de la pioche
     */
    public PiocheParcelle(@NotNull Random random) {
        parcellePiocheList = new ArrayList<>(27);
        creePiocheParcelles();

        this.random = random;
        parcellesPiochees = new ParcellesPiochees();
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
            parcellePiocheList.add(new ParcellePioche(vert));
        }
        // avec futur aménagement bassin
        parcellePiocheList.add(new ParcellePioche(vert));
        parcellePiocheList.add(new ParcellePioche(vert));
        // avec futur aménagement enclos
        parcellePiocheList.add(new ParcellePioche(vert));
        parcellePiocheList.add(new ParcellePioche(vert));
        // avec futur aménagement engrais
        parcellePiocheList.add(new ParcellePioche(vert));
    }

    /**
     * Ajoute les parcelles roses à la pioche
     */
    private void creeParcellesRosesPioche() {
        Couleur rose = Couleur.ROSE;
        for (int i=0; i<4; i++) {
            parcellePiocheList.add(new ParcellePioche(rose));
        }
        // avec futur aménagement bassin
        parcellePiocheList.add(new ParcellePioche(rose));
        // avec futur aménagement enclos
        parcellePiocheList.add(new ParcellePioche(rose));
        // avec futur aménagement engrais
        parcellePiocheList.add(new ParcellePioche(rose));
    }

    /**
     * Ajoute les parcelles jaunes à la pioche
     */
    private void creeParcellesJaunesPioche() {
        Couleur jaune = Couleur.JAUNE;
        for (int i=0; i<6; i++) {
            parcellePiocheList.add(new ParcellePioche(jaune));
        }
        // avec futur aménagement bassin
        parcellePiocheList.add(new ParcellePioche(jaune));
        // avec futur aménagement enclos
        parcellePiocheList.add(new ParcellePioche(jaune));
        // avec futur aménagement engrais
        parcellePiocheList.add(new ParcellePioche(jaune));
    }


    // Accesseurs

    /**
     * Renvoie le nombre de parcelles que contient la pioche
     * @return le nombre de parcelles restantes dans la pioche
     */
    public int getNombreParcellesRestantes() {
        return parcellePiocheList.size();
    }

    /**
     * Renvoie si la pioche de parcelles est vide
     * @return {@code true} si la pioche est vide
     */
    public boolean isEmpty() {
        return parcellePiocheList.isEmpty();
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
        if (!parcellesPiochees.isEmpty()) {
            throw new PiocheParcelleEnCoursException();
        }

        if (getNombreParcellesRestantes() <= 3) {
            return random1Parcelle();
        }

        return random3Parcelle();
    }

    /**
     * Renvoie un tableau de 3 fois la même parcelle, choisie aléatoirement dans la pioche
     * @return un tableau de 3 parcelles piochées
     */
    private ParcellePioche[] random1Parcelle() {
        int size = getNombreParcellesRestantes();

        int positionParcelle = random.nextInt(size);
        if (positionParcelle < 0 || positionParcelle >= size) {
            throw new ArithmeticException(ERREUR_RANDOM);
        }

        ParcellePioche parcellePiochee = parcellePiocheList.get(positionParcelle);
        parcellesPiochees.enregistreParcellePiochee(parcellePiochee);

        return new ParcellePioche[]{parcellePiochee, parcellePiochee, parcellePiochee};
    }

    /**
     * Renvoie un tableau de 3 parcelles différentes, choisies aléatoirement dans la pioche
     * @return un tableau de 3 parcelles piochées
     */
    private ParcellePioche[] random3Parcelle() {
        int[] positionParcelle = random3Positions();

        ParcellePioche parcellePiochee1 = parcellePiocheList.get(positionParcelle[0]);
        ParcellePioche parcellePiochee2 = parcellePiocheList.get(positionParcelle[1]);
        ParcellePioche parcellePiochee3 = parcellePiocheList.get(positionParcelle[2]);
        parcellesPiochees.enregistre3ParcellesPiochees(parcellePiochee1, parcellePiochee2, parcellePiochee3);

        return new ParcellePioche[]{
                parcellePiocheList.get(positionParcelle[0]),
                parcellePiocheList.get(positionParcelle[1]),
                parcellePiocheList.get(positionParcelle[2])};
    }

    /**
     * Renvoie un tableau de 3 positions de parcelle différentes, choisies aléatoirement dans la pioche
     * @return un tableau de 3 positions de parcelle piochées
     */
    private int[] random3Positions() {
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
        if (parcellesPiochees.isEmpty()) {
            throw new PiocheParcelleVideException();
        }

        if (parcellesPiochees.parcellePiochee(parcelleChoisie)) {
            parcellesPiochees.oublieParcellesPiochees();
            parcellePiocheList.remove(parcelleChoisie);

            return new ParcelleCouleur(position, parcelleChoisie.couleur());
        }
        throw new IllegalArgumentException("La parcelle choisie ne correspond à aucune de la pioche effectuée");
    }


    // Méthode toString

    @Override
    public String toString() {
        return "Pioche de parcelles : " + getNombreParcellesRestantes() + " parcelles.";
    }
}
