package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.jeu.MaitreDuJeu;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Strategie;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(Main.class.getPackageName());


    // Méthode d'exécution

    public static void main(String... args) {
        configureLogger();

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


    // Méthodes d'utilisation

    /**
     * Permet de configurer tous les loggers (format et level affiché)
     */
    public static void configureLogger() {
        LOGGER.setUseParentHandlers(false);

        Handler handler = new ConsoleHandler();
        handler.setFormatter(new LoggerFormatter());
        LOGGER.addHandler(handler);

        LOGGER.setLevel(Level.INFO);
    }
}
