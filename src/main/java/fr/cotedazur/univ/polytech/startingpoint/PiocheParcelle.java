package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Gère les pioches de parcelles
 * @author équipe N
 */
public class PiocheParcelle extends ArrayList<ParcellePioche> {
    // Définition des attributs
    private Random random;
    private Optional<ParcellePioche[]> parcellesPiochees;


    /**
     * Constructeur par défaut, crée une pioche de 27 parcelles
     * @param random est un objet Random qui va permettre de créer une pioche aléatoire
     */
    public PiocheParcelle(Random random) {
        creePiocheParcelles();
        this.random = random;
        parcellesPiochees = Optional.empty();
    }

    private void creePiocheParcelles() {
        creeParcellesVertesPioche();
        creeParcellesRosesPioche();
        creeParcellesJaunesPioche();
    }

    private void creeParcellesVertesPioche() {
        for (int i=0; i<6; i++) {
            add(new ParcellePioche());
        }
        // avec aménagement bassin
        add(new ParcellePioche());
        add(new ParcellePioche());
        // avec aménagement enclos
        add(new ParcellePioche());
        add(new ParcellePioche());
        // avec aménagement engrais
        add(new ParcellePioche());
    }

    private void creeParcellesRosesPioche() {
        for (int i=0; i<4; i++) {
            add(new ParcellePioche());
        }
        // avec aménagement bassin
        add(new ParcellePioche());
        // avec aménagement enclos
        add(new ParcellePioche());
        // avec aménagement engrais
        add(new ParcellePioche());
    }

    private void creeParcellesJaunesPioche() {
        for (int i=0; i<6; i++) {
            add(new ParcellePioche());
        }
        // avec aménagement bassin
        add(new ParcellePioche());
        // avec aménagement enclos
        add(new ParcellePioche());
        // avec aménagement engrais
        add(new ParcellePioche());
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie le nombre de parcelles que contient la pioche
     * @return le nombre de parcelles restantes dans la pioche
     */
    public int getNombreParcellesRestantes() {
        return size();
    }

    /**
     * Renvoie si la pioche de parcelles est vide
     * @return true la pioche est vide, false sinon
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return "Pioche de parcelles : " + getNombreParcellesRestantes() + " parcelles.";
    }


    // Méthodes d'utilisation
    /**
     * Renvoie 3 parcelles désignées dans la pioche
     * @throws PiocheParcelleEnCoursException quand une pioche de parcelles est déjà en cours
     * @return un tableau de 3 parcelles piochées
     * @implNote si la pioche <= 3 parcelles, elle renvoie 3 fois la même parcelle
     * @implNote la pioche ne doit pas être vide
     */
    public ParcellePioche[] pioche() throws PiocheParcelleEnCoursException {
        assert !isEmpty() : "La pioche de bambous est vide";
        if (parcellesPiochees.isPresent()) throw new PiocheParcelleEnCoursException();
        ParcellePioche[] parcelles;
        int size = getNombreParcellesRestantes();
        if (size <= 3) {
            int positionParcelle = random.nextInt(size);
            if (positionParcelle < 0 || positionParcelle >= size) throw new RuntimeException();
            parcelles = new ParcellePioche[]{get(positionParcelle), get(positionParcelle), get(positionParcelle)};
            parcellesPiochees = Optional.of(parcelles);
            return parcelles;
        }
        int[] positions = random3Parcelles();
        parcelles = new ParcellePioche[]{get(positions[0]), get(positions[1]), get(positions[2])};
        parcellesPiochees = Optional.of(parcelles);
        return parcelles;
    }

    private int[] random3Parcelles() {
        int size = getNombreParcellesRestantes();
        int positionParcelle1 = random.nextInt(size);
        if (positionParcelle1 < 0 || positionParcelle1 >= size) throw new RuntimeException();

        // Boucle pour positionParcelle2
        int positionParcelle2;
        do {
            positionParcelle2 = random.nextInt(size);
            if (positionParcelle2 < 0 || positionParcelle2 >= size) throw new RuntimeException();
        } while (positionParcelle1 == positionParcelle2);

        // Boucle pour positionParcelle3
        int positionParcelle3;
        do {
            positionParcelle3 = random.nextInt(size);
            if (positionParcelle3 < 0 || positionParcelle3 >= size) throw new RuntimeException();
        } while (positionParcelle1 == positionParcelle3 || positionParcelle2 == positionParcelle3);

        return new int[]{positionParcelle1, positionParcelle2, positionParcelle3};
    }

    /**
     * Renvoie la parcelle avec la position en fonction de celle choisie dans la pioche
     * @return la parcelle piochée avec la position demandée
     */
    public ParcelleCouleur choisiParcelle(ParcellePioche parcelleChoisie, Position position) {

        // Vérifier si parcelleChoisie est bien dans la liste des parcellesPiochees avec equals

        parcellesPiochees = Optional.empty();
        remove(parcelleChoisie);
        return new ParcelleCouleur(position);
    }
}
