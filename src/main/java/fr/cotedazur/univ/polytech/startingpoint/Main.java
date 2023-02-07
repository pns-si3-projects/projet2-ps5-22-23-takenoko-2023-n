package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.jeu.MaitreDuJeu;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Strategie;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    // Définition des attributs

    public static final LogManager LOG_MANAGER = LogManager.getLogManager();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getPackageName());


    public static void main(String... args) {
        LOG_MANAGER.getLogger(LOGGER.getName()).setLevel(Level.INFO);

        Joueur joueur1 = new Joueur("joueur1", Strategie.StrategiePossible.PARCELLE);
        Joueur joueur2 = new Joueur("joueur2", Strategie.StrategiePossible.JARDINIER);
        Joueur joueur3 = new Joueur("joueur3", Strategie.StrategiePossible.PANDA);
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueur1, joueur2, joueur3);

        /*
        ArgumentsMain argsMain = new ArgumentsMain();
        JCommander.newBuilder()
                .addObject(argsMain)
                .build()
                .parse(args);
        System.out.println(argsMain.getArgument());
        */
    }
}
