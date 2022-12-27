package fr.cotedazur.univ.polytech.startingpoint;

public class ObjectifBambou {
    private final int nombreBambous;

    /**
     * Constructeur par défaut qui permet de créer un Objectif
     * avec un nombre de Bambous a manger
     * @param nombreBambous qui est le nombre de bambous a manger
     */
    public ObjectifBambou(int nombreBambous) {
        this.nombreBambous = nombreBambous;
    }

    /**
     * Getter du nombre de bambous a recuperer
     * @return le nombre de bambous de l'objectif
     */
    public int getNombreBambous() {
        return nombreBambous;
    }
}
