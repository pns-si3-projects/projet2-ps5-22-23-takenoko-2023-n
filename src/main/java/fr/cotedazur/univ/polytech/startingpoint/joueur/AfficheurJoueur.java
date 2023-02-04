package fr.cotedazur.univ.polytech.startingpoint.joueur;

/**
 * Affiche les changements effectués par le joueur.
 * @author equipe N
 */
public class AfficheurJoueur {
    private static final String SEPARATION = "\t\t\t";


    /**
     * Affiche le début du tour du joueur
     * @param nom le nom du joueur
     */
    public static void debutTour(String nom) {
        System.out.println("\n" + nom + " :");
    }

    /**
     * Affiche l'action choisie par le joueur
     * @param actionChoisie l'action choisie par le joueur
     */
    public static void actionChoisie(Plaquette.ActionPossible actionChoisie) {
        String str = SEPARATION + "Choix de faire l'action " + actionChoisie.toString().toLowerCase();
        System.out.println(str);
    }
}
