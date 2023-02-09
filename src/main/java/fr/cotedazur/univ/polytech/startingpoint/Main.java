package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import fr.cotedazur.univ.polytech.startingpoint.jeu.AfficheurJeu;
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
        ArgumentPossibleMain argumentMain;

        if (args.length > 0) {
            ArgumentsMain argsMain = new ArgumentsMain();
            JCommander.newBuilder()
                    .addObject(argsMain)
                    .build()
                    .parse(args);
            argumentMain = argsMain.getArgument();
        }
        else {
            argumentMain = ArgumentPossibleMain.PRESENTATION;
        }

        configureLogger(argumentMain);
        appliqueModeJeu(argumentMain);
    }


    // Méthodes d'utilisation

    /**
     * Permet de configurer tous les loggers (format et level affiché)
     */
    public static void configureLogger(ArgumentPossibleMain argumentMain) {
        LOGGER.setUseParentHandlers(false);

        Handler handler = new ConsoleHandler();
        handler.setFormatter(new LoggerFormatter());
        LOGGER.addHandler(handler);

        if (argumentMain.isThousands()) {
            LOGGER.setLevel(Level.WARNING);
        } else {
            LOGGER.setLevel(Level.INFO);
        }
    }

    /**
     * Applique le mode de jeu demandé
     * @param argumentMain le mode de jeu demandé
     */
    private static void appliqueModeJeu(ArgumentPossibleMain argumentMain) {
        switch (argumentMain) {
            case THOUSANDS -> joue2Thousands();
            case DEMO -> joueDemo();
            case CSV -> joueCSV();
            case PRESENTATION -> jouePresentation();
        }
    }

    /**
     * Joue le mode de jeu de 2 × 1000 parties
     * 1000 parties entre le meilleur bot et les autres
     * 1000 parties entre le meilleur bot et lui-même
     */
    private static void joue2Thousands() {
        LOGGER.warning("Début mode 2thousands");
        Joueur joueur1 = new Joueur("joueurPar1", Strategie.StrategiePossible.PANDA);
        Joueur joueur2 = new Joueur("joueurPar2", Strategie.StrategiePossible.PARCELLE);
        Joueur joueur3 = new Joueur("joueurPar3", Strategie.StrategiePossible.COMPLET);
        Joueur joueur4 = new Joueur("joueurPar4", Strategie.StrategiePossible.JARDINIER);
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueur1, joueur2, joueur3, joueur4);
        maitreDuJeu.jeu();
        AfficheurJeu.etatJeu(maitreDuJeu);
        LOGGER.info("Points: "+ joueur1.nombrePoints());
        LOGGER.info("Points: "+ joueur2.nombrePoints());
        LOGGER.info("Points: "+ joueur3.nombrePoints());
        LOGGER.info("Points: "+ joueur4.nombrePoints());
    }

    /**
     * Joue une partie de demo entre plusieurs bots
     */
    private static void joueDemo() {
        Joueur joueur1 = new Joueur("joueurJar1", Strategie.StrategiePossible.JARDINIER);
        Joueur joueur2 = new Joueur("joueurJar2", Strategie.StrategiePossible.JARDINIER);
        Joueur joueur3 = new Joueur("joueurJar3", Strategie.StrategiePossible.JARDINIER);
        Joueur joueur4 = new Joueur("joueurJar4", Strategie.StrategiePossible.JARDINIER);
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueur1, joueur2, joueur3, joueur4);
        maitreDuJeu.jeu();
        AfficheurJeu.etatJeu(maitreDuJeu);
    }

    /**
     * Joue un nombre limité de parties en enregistrant des statistiques dans un fichier CSV
     */
    private static void joueCSV() {
        String totalPointPartiePrecedente = ReadCSV.main(null);
        Joueur joueur1 = new Joueur("joueurPar1", Strategie.StrategiePossible.PARCELLE);
        Joueur joueur2 = new Joueur("joueurPar2", Strategie.StrategiePossible.JARDINIER);
        Joueur joueur3 = new Joueur("joueurPar3", Strategie.StrategiePossible.PANDA);
        Joueur joueur4 = new Joueur("joueurPar4", Strategie.StrategiePossible.COMPLET);
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueur1, joueur2, joueur3, joueur4);
        maitreDuJeu.jeu();
        AfficheurJeu.etatJeu(maitreDuJeu);

        int nbrPtsJ1 = joueur1.nombrePoints();
        int nbrPtsJ2 = joueur2.nombrePoints();
        int nbrPtsJ3 = joueur3.nombrePoints();
        int nbrPtsJ4 = joueur4.nombrePoints();
        int totalPts = nbrPtsJ1 + nbrPtsJ2 +nbrPtsJ3 + nbrPtsJ4;
        int totalPointsToutesParties = Integer.parseInt(totalPointPartiePrecedente) + totalPts;
        WriteCSV.main(new String[]{String.valueOf(nbrPtsJ1), String.valueOf(nbrPtsJ2), String.valueOf(nbrPtsJ3),
                String.valueOf(nbrPtsJ4), String.valueOf(totalPts), String.valueOf(totalPointsToutesParties) });
    }

    /**
     * Joue une partie normale
     */
    private static void jouePresentation() {
        Joueur joueur1 = new Joueur("joueur1", Strategie.StrategiePossible.PARCELLE);
        Joueur joueur2 = new Joueur("joueur2", Strategie.StrategiePossible.JARDINIER);
        Joueur joueur3 = new Joueur("joueur3", Strategie.StrategiePossible.PANDA);
        Joueur joueur4 = new Joueur("joueur4",Strategie.StrategiePossible.COMPLET);
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueur1, joueur2, joueur3, joueur4);
        maitreDuJeu.jeu();
        AfficheurJeu.etatJeu(maitreDuJeu);
    }
}
