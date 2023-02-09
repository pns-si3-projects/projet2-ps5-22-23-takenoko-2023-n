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
    private static final Joueur joueurComplet = new Joueur("Joueur complet", Strategie.StrategiePossible.COMPLET);
    private static final Joueur joueurParcelle = new Joueur("Joueur parcelle", Strategie.StrategiePossible.PARCELLE);
    private static final Joueur joueurJardinier = new Joueur("Joueur jardinier", Strategie.StrategiePossible.JARDINIER);
    private static final Joueur joueurPanda = new Joueur("Joueur panda", Strategie.StrategiePossible.PANDA);


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
            argumentMain = ArgumentPossibleMain.DEMO;
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
        }
    }

    /**
     * Joue le mode de jeu de 2 × 1000 parties
     * 1000 parties entre le meilleur bot et les autres
     * 1000 parties entre le meilleur bot et lui-même
     */
    private static void joue2Thousands() {
        LOGGER.warning("Début mode 2thousands");
        for (int i=0; i<1000; i++){
            MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueurComplet, joueurParcelle, joueurJardinier, joueurPanda);
            maitreDuJeu.jeu();
            String nbJeu = Integer.toString(i+1);
            LOGGER.warning(nbJeu);
        }
    }

    /**
     * Joue une partie de demo entre plusieurs bots
     */
    private static void joueDemo() {
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueurComplet, joueurParcelle, joueurJardinier, joueurPanda);
        maitreDuJeu.jeu();
        AfficheurJeu.etatJeu(maitreDuJeu);
    }

    /**
     * Joue un nombre limité de parties en enregistrant des statistiques dans un fichier CSV
     */
    private static void joueCSV() {
        String totalPointPartiePrecedente = ReadCSV.read();
        Joueur joueurCom = joueurComplet;
        Joueur joueurPar = joueurParcelle;
        Joueur joueurJar = joueurJardinier;
        Joueur joueurPan = joueurPanda;
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueurCom, joueurPar, joueurJar, joueurPan);
        maitreDuJeu.jeu();
        AfficheurJeu.etatJeu(maitreDuJeu);

        int nbrPtsJ1 = joueurCom.nombrePoints();
        int nbrPtsJ2 = joueurPar.nombrePoints();
        int nbrPtsJ3 = joueurJar.nombrePoints();
        int nbrPtsJ4 = joueurPan.nombrePoints();
        int totalPts = nbrPtsJ1 + nbrPtsJ2 +nbrPtsJ3 + nbrPtsJ4;
        int totalPointsToutesParties = Integer.parseInt(totalPointPartiePrecedente) + totalPts;
        WriteCSV.main(new String[]{String.valueOf(nbrPtsJ1), String.valueOf(nbrPtsJ2), String.valueOf(nbrPtsJ3),
                String.valueOf(nbrPtsJ4), String.valueOf(totalPts), String.valueOf(totalPointsToutesParties) });
    }
}
